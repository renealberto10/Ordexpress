package com.pumpkinapplabs.ordexpress.data.SQLiteHelper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RegisterSQLIte extends SQLiteOpenHelper {

    //create table into sqlite database
    String sqlcreate = "CREATE TABLE Register (iduser INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, email TEXT, password TEXT)";

    public RegisterSQLIte(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlcreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //eliminar la version anterior
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Register");

        //se crea la nueva version
        sqLiteDatabase.execSQL(sqlcreate);
    }
}
