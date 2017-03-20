package com.newton.agenda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

        contato_edtnome = (EditText) findViewById(R.id.edtNome);
        contato_edtemail= (EditText) findViewById(R.id.edtEmail);
        contato_edttelefone= (EditText) findViewById(R.id.edtTelefone);
        contato_BtnSalvar = (Button) findViewById(R.id.btnSalvar);
        activity_contato = (LinearLayout) findViewById(R.id.activityContato);
        contato_progressBar = (ProgressBar) findViewById(R.id.progressBar);





    }
}
