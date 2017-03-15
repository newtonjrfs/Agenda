package com.newton.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.newton.agenda.model.Contato;

/**
 * Created by PAS12 on 15/03/2017.
 */

public class ContatoDAO {

    Context context;
    BancoDAO dao;

    private static final String BANCONOME = "Contato";

    public ContatoDAO(Context context){
        this.context = context;
    }

    public void insere(Contato contato){
        dao = new BancoDAO(context);
        SQLiteDatabase db = dao.getWritableDatabase();
        ContentValues dados = pegaDadosDoContato(contato);
        long inserir = db.insert(BANCONOME,null,dados);
        db.close();
        Log.i(BANCONOME,inserir + " ");
    }

    private ContentValues pegaDadosDoContato(Contato contato){

        ContentValues dados = new ContentValues();
        dados.put("nome", contato.getNome());
        dados.put("email",contato.getEmail());
        dados.put("telefone",contato.getTelefone());
        return dados;

    }

}