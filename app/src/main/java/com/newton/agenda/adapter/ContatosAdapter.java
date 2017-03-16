package com.newton.agenda.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newton.agenda.R;
import com.newton.agenda.model.Contato;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Newton on 15/03/2017.
 */

public class ContatosAdapter extends RecyclerView.Adapter<ContatosAdapter.MyViewHolder> {

    private List<Contato> contatoList;

    public ContatosAdapter(List<Contato> contatoList){
        this.contatoList= contatoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contato_list_row, parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contato contato = contatoList.get(position);
        holder.nome.setText(contato.getNome());
        holder.email.setText(contato.getEmail());
        holder.telefone.setText(contato.getTelefone());
    }

    @Override
    public int getItemCount() {
        return contatoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nome, telefone,email;

        public MyViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.nomeId);
            telefone = (TextView) itemView.findViewById(R.id.telefoneId);
            email = (TextView) itemView.findViewById(R.id.emailId);
        }
    }
}
