package com.pumpkinapplabs.ordexpress.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.google.android.material.textfield.TextInputLayout;
import com.pumpkinapplabs.ordexpress.MainActivity;
import com.pumpkinapplabs.ordexpress.R;
import com.pumpkinapplabs.ordexpress.data.SQLiteHelper.RegisterSQLIte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    Button btnregister;

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    private RegisterSQLIte registerHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //create database in read mode
        registerHelper = new RegisterSQLIte(this, "Ordexpress", null, 1);
        db = registerHelper.getWritableDatabase();

        btnregister = findViewById(R.id.buttonregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                register();
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
    //Validate to email is correct
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    //Validate to password is correct
    public boolean validatePassword(String password) {
        return password.length() > 5;
    }
    //Validate to password is correct
    public boolean validateName(String name) {
        return name.length() > 1;
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
    private void register()
    {
        TextInputLayout nametxt = findViewById(R.id.txtnameregister);
        TextInputLayout passwordtxt = findViewById(R.id.txtpasswordregister);
        TextInputLayout emailtxt = findViewById(R.id.txtemailregister);
        String stremail = emailtxt.getEditText().getText().toString().trim();
        String strname = nametxt.getEditText().getText().toString().trim();
        String strpassword = passwordtxt.getEditText().getText().toString().trim();
        validateEmail(stremail);
        validatePassword(strpassword);

        if(!validateName(strname)){
            String msj = "Nombre no puede estar vacio y debe ser mayor de 1 caracter.";
            message(msj);
        }else if(!validateEmail(stremail)){
            String msj = "Direccion de correo no valida.";
            message(msj);
        }
        else if(!validatePassword(strpassword)){
            String msj = "Contraseña debe ser mayor de 5 caracteres.";
            message(msj);
        }
        else {
            sqlsaveregister(strname, stremail,strpassword);
        }

    }

    public void sqlsaveregister (String name, String email, String password){
        if (db != null){
            ContentValues newregister = new ContentValues();
            newregister.put("name", name);
            newregister.put("email", email);
            newregister.put("password", password);

            //insert data in databases
            db.insert("Register", null, newregister);
            db.close();

            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }
}