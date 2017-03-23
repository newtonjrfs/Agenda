package com.newton.agenda;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.newton.agenda.dao.BancoDAO;
import com.newton.agenda.dao.ContatoDAO;
import com.newton.agenda.model.Contato;

/**
 * Created by PAS12 on 20/03/2017.
 */

public class ContatoActivity extends AppCompatActivity {

    private EditText contato_edtnome;
    private EditText contato_edtemail;
    private EditText contato_edttelefone;

    private Button contato_BtnSalvar;
    private LinearLayout activity_contato;
    private ProgressBar contato_progressBar;

    private String nome, email, telefone;

    private Contato contato;
    private ContatoDAO dao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        findView();

        Intent intent = getIntent();
        contato = (Contato) intent.getSerializableExtra("contato");
        if(contato!=null){
            contato_edtnome.setText(contato.getNome());
            contato_edtemail.setText(contato.getEmail());
            contato_edttelefone.setText(contato.getTelefone());
        }else{
            contato = new Contato();

        }

        contato_BtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pegarValores();

                habilitarProgress(View.VISIBLE,false);

                if(TextUtils.isEmpty(nome)){
                    Snackbar.make(activity_contato,"Digite seu nome",Snackbar.LENGTH_SHORT).show();
                    habilitarProgress(View.GONE,true);
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Snackbar.make(activity_contato,"Digite seu email",Snackbar.LENGTH_SHORT).show();
                    habilitarProgress(View.GONE,true);
                    return;
                }
                if(TextUtils.isEmpty(telefone)){
                    Snackbar.make(activity_contato,"Digite seu telefone",Snackbar.LENGTH_SHORT).show();
                    habilitarProgress(View.GONE,true);
                    return;
                }

                contato.setNome(nome);
                contato.setEmail(email);
                contato.setTelefone(telefone);

                dao = new ContatoDAO(getApplicationContext());
                if(contato.getId()>0){
                    editar();
                }else{
                    inserir();
                }


            }
        });



    }

    private void editar() {

        dao.alterar(contato);
        Toast.makeText(getApplicationContext(),"Salvo com sucesso",Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }


    private void inserir() {
        if(dao.insere(contato)>0){
            Toast.makeText(getApplicationContext(),"Salvo com sucesso ! ",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }else
        {
            Snackbar.make(activity_contato,"nao foi posssivel salvar",Snackbar.LENGTH_SHORT).show();
            habilitarProgress(View.GONE,true);
        }
    }

    private void habilitarProgress(int visible, boolean b) {

        contato_progressBar.setVisibility(visible);
        contato_BtnSalvar.setClickable(b);


    }

    private void pegarValores() {
        nome = contato_edtnome.getText().toString();
        email = contato_edtemail.getText().toString();
        telefone = contato_edttelefone.getText().toString();
    }

    private void findView() {
        contato_edtnome = (EditText) findViewById(R.id.edtNome);
        contato_edtemail= (EditText) findViewById(R.id.edtEmail);
        contato_edttelefone= (EditText) findViewById(R.id.edtTelefone);
        contato_BtnSalvar = (Button) findViewById(R.id.btnSalvar);
        activity_contato = (LinearLayout) findViewById(R.id.activityContato);
        contato_progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contato,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.contato_ligar:
                Intent chamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contato.getTelefone()));
                if (ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)==
                        PackageManager.PERMISSION_GRANTED){
                    startActivity(chamada);
                }
                return true;
            case R.id.contato_apagar:
                Toast.makeText(getApplicationContext(),R.string.apagar,Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
