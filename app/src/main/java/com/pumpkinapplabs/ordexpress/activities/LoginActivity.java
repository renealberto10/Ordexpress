package com.pumpkinapplabs.ordexpress.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.pumpkinapplabs.ordexpress.R;
import com.pumpkinapplabs.ordexpress.data.model.LoginPost;
import com.pumpkinapplabs.ordexpress.data.remote.RetrofitAPI;
import com.pumpkinapplabs.ordexpress.data.remote.ServicesAPI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    TextView txtregister, txtforgetpass;
    Button btnlogin;
    SignInButton btngoogle;

    GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "SignInActivity";
    private  static final int RC_SIGN_IN = 9001;

    ProgressDialog progress;
    private SharedPreferences preferencias;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DesignUI();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    preferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
    txtregister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            registerform();
        }
    });
    txtforgetpass.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            recoverypass();
        }
    });
    btnlogin.setOnClickListener(this);
    btngoogle.setOnClickListener(this);

    }
    //Method to click buttom
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.buttonlogin:
                hideKeyboard();
                progress = new ProgressDialog(LoginActivity.this);
                progress.setTitle("Validando credenciales");
                progress.setMessage("Espere, procesando informacion...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.show();
                login();
                break;
            case R.id.googlesign:
                signIn();
                break;
        }
    }

    //Method Sign with Google
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                Intent i = new Intent(this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                this.finish();


            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.d(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    //Method super Toast
    @SuppressLint("WrongConstant")
    public void message (String messages){
        final String MATERIAL_COLOR = "f95d5d";
        SuperActivityToast.create(this, new Style(), Style.TYPE_BUTTON)
                //.setButtonText("UNDO")
                //.setButtonIconResource(R.drawable.ic_buscar)
                //.setOnButtonClickListener("good_tag_name", null, onButtonClickListener)
                .setProgressBarColor(Color.WHITE)
                .setText(messages)
                .setDuration(Style.DURATION_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                //.setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_LIGHT_BLUE))
                .setColor(PaletteUtils.getSolidColor(MATERIAL_COLOR))
                .setAnimations(Style.ANIMATIONS_POP).show();
    }
    //UX var declare into method
    private void DesignUI()
    {
        txtforgetpass = findViewById(R.id.txtforgetpass);
        txtregister = findViewById(R.id.txtregisterform);
        btnlogin = findViewById(R.id.buttonlogin);
        btngoogle = findViewById(R.id.googlesign);
        TextView textView = (TextView) btngoogle.getChildAt(0);
        textView.setText("Ingresar con Google");
    }

//Redirect to register form
    private void  registerform()
    {
        Intent r = new Intent(this, RegisterActivity.class);
        r.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(r);
        this.finish();
    }
    //Redirect to recovery password
    private void  recoverypass()
    {
        Intent p = new Intent(this, PassrecoveryActivity.class);
        p.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(p);
        this.finish();
    }
    //Validate to email is correct
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    //Validate to password is correct
    public boolean validatePassword(String password) {
        return password.length() > 5;
    }
    //Hide Keyboard
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //Method to validate user and password
    private void login()
    {
        TextInputLayout emailtxt = findViewById(R.id.txtemail);
        TextInputLayout passwordtxt = findViewById(R.id.txtpassword);
        String stremail = emailtxt.getEditText().getText().toString().trim();
        String strpassword = passwordtxt.getEditText().getText().toString().trim();
        validateEmail(stremail);
        validatePassword(strpassword);

        if(!validateEmail(stremail)){
            String msj = "Direccion de correo no valida.";
            message(msj);
            progress.dismiss();

        }else if(!validatePassword(strpassword)){
            String msj = "Contraseña debe ser mayor de 5 caracteres.";
            message(msj);
            progress.dismiss();
        }
        else {

         sendPost(stremail,strpassword);
        }

    }

    //Metodo donde se envia los parametros para ejecutar el post a auth/login

    public void sendPost(String stremail, String strpassword) {

        ServicesAPI servicesAPI = RetrofitAPI.getClient();
        servicesAPI.savePost(stremail,strpassword).enqueue(new Callback<LoginPost>() {

            @Override
            public void onResponse(Call<LoginPost> call, Response<LoginPost> response) {

                if(response.isSuccessful()) {
                    if(response.body() !=null){
                        Log.i("Conexion exitosa", response.body().toString());
                        LoginPost jsonresponse = response.body();
                        Log.d("",response.body().toString());
                        loginUser(jsonresponse);
                    }
                    else{
                        Log.i("Conexion fallo", "Body() null");

                    }

                }
            }
            @Override
            public void onFailure(Call<LoginPost> call, Throwable t) {
                progress.dismiss();
                String msj = "Usuario o Contraseña incorrecto";
                message(msj);
                //Toast.makeText(LoginActivity.this, "Usuario o Contraseña incorrecto", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void loginUser(LoginPost response){
        saveLoginUser(response);
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        progress.dismiss();
        this.finish();
    }

    public void saveLoginUser(LoginPost response){
        SharedPreferences.Editor saveinfo = preferencias.edit();
        saveinfo.putInt("user_id", response.getUserid());
        saveinfo.putString("token", response.getToken());
        saveinfo.putInt("rol", response.getRol());
        saveinfo.apply();

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}