package com.newton.agenda.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PAS12 on 14/03/2017.
 */

public class BancoDAO extends SQLiteOpenHelper {

    private static final String NOMEBANCO = "Agenda";
    private static final int VERSAOBANCO = 1;
    private static final String TABLECONTATO = "CREATE TABLE Contato(id INTEGER PRIMARY KEY , " +
            "nome TEXT, email TEXT, telefone TEXT);";

    public BancoDAO(Context context) {
        super(context, NOMEBANCO, null, VERSAOBANCO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLECONTATO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Contato;");
        db.execSQL(TABLECONTATO);

    }
}
