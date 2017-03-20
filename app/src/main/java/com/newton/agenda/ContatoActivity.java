package com.newton.agenda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        findView();

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

            }
        });



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
}
