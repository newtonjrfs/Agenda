package com.newton.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.newton.agenda.model.Contato;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public long insere(Contato contato){
        dao = new BancoDAO(context);
        SQLiteDatabase db = dao.getWritableDatabase();
        ContentValues dados = pegaDadosDoContato(contato);
        long inserir = db.insert(BANCONOME,null,dados);
        db.close();
        Log.i(BANCONOME,inserir + " ");
        return inserir;
    }

    public List<Contato> buscarContato(){

        String sql = "SELECT * FROM " + BANCONOME;
        dao = new BancoDAO(context);
        SQLiteDatabase db = dao.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        List<Contato> contatos = new ArrayList<Contato>();

        while (c.moveToNext()){

            Contato contato = new Contato();
            contato.setId(c.getInt(c.getColumnIndex("id")));
            contato.setNome(c.getString(c.getColumnIndex("nome")));
            contato.setEmail(c.getString(c.getColumnIndex("email")));
            contato.setTelefone(c.getString(c.getColumnIndex("telefone")));

            contatos.add(contato);
        }

        c.close();

        return  contatos;
    }

    public void alterar(Contato contato){

        dao = new BancoDAO(context);
        SQLiteDatabase db = dao.getWritableDatabase();
        ContentValues dados = pegaDadosDoContato(contato);
        String [] params = {String.valueOf(contato.getId())};
        db.update("Contato",dados,"id=?",params);

    }

    public void deletar(Contato contato){
        dao = new BancoDAO(context);
        SQLiteDatabase db = dao.getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs = new String []{String.valueOf(contato.getId())};
        db.delete(BANCONOME,whereClause,whereArgs);
        db.close();
    }

    private ContentValues pegaDadosDoContato(Contato contato){

        ContentValues dados = new ContentValues();
        dados.put("nome", contato.getNome());
        dados.put("email",contato.getEmail());
        dados.put("telefone",contato.getTelefone());
        return dados;

    }

}