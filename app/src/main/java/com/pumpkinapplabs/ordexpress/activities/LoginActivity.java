package com.pumpkinapplabs.ordexpress.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pumpkinapplabs.ordexpress.MainActivity;
import com.pumpkinapplabs.ordexpress.R;

public class LoginActivity extends AppCompatActivity {
TextView txtregister, txtforgetpass;
Button btnlogin;
ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    DesignUI();
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
    btnlogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            progress = new ProgressDialog(LoginActivity.this);
            progress.setTitle("Validando credenciales");
            progress.setMessage("Espere, procesando informacion...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.show();
            login();
        }
    });
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
    //Method to validate user and password
    private void login()
    {
        TextInputLayout emailtxt = findViewById(R.id.txtemail);
        TextInputLayout passwordtxt = findViewById(R.id.txtpassword);
        String stremail = emailtxt.getEditText().getText().toString().trim();
        String strpassword = passwordtxt.getEditText().getText().toString().trim();

        if(TextUtils.isEmpty(stremail)){
            String msj = "Debe de digitar su correo electronico";
            message(msj);
            progress.dismiss();

        }else if(TextUtils.isEmpty(strpassword)){
            String msj = "Debe de digitar su contraseña";
            message(msj);
            progress.dismiss();
        }
        else {
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            progress.dismiss();
            this.finish();

        }

    }
}