package com.alura.agenda2.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.alura.agenda2.dao.AlunoDAO;
import com.alura.agenda2.model.Aluno;
import com.alura.agenda2.ui.adapter.ListaAlunoAdapter;

public class ListaAlunosView {
    private final ListaAlunoAdapter adapter;
    private final AlunoDAO dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunoAdapter(this.context);
        this.dao = new AlunoDAO();
    }

    public void confirmaRemocao(@NonNull MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo um aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                    removeAluno(alunoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaAlunos() {
        adapter.atualiza(this.dao.todos());
    }

    public void removeAluno(Aluno alunoEscolhido) {
        this.dao.remove(alunoEscolhido);
        adapter.remove(alunoEscolhido);
    }

    public void configuraAdapter(ListView listaAlunos) {
        listaAlunos.setAdapter(adapter);
    }
}
