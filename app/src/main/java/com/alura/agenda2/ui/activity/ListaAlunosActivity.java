package com.alura.agenda2.ui.activity;

import static com.alura.agenda2.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alura.agenda2.R;
import com.alura.agenda2.dao.AlunoDAO;
import com.alura.agenda2.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {


    private final AlunoDAO alunoDAO = new AlunoDAO();
    public static final String TITULO_APPBAR = "Lista de Alunos";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraFABNovoAluno();
    }

    private void configuraFABNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioModoInsereAluno();
            }
        });
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_lista_de_alunos_listview);
        final List<Aluno> alunos = alunoDAO.todos();
        configuraAdapter(listaAlunos, alunos);
        configuraListenerDeCliquePorItem(listaAlunos);
        listaAlunos.setOnItemClickListener((parent, view, position, id) -> {
            Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
            alunoDAO.remove(alunoEscolhido);
        });
    }

    private void configuraListenerDeCliquePorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener((parent, view, position, id) -> {
            Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
            abreFormularioModoEditaAluno(alunoEscolhido);
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent vaiParaFormulario = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormulario.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormulario);
    }

    private void configuraAdapter(ListView listaAlunos, List<Aluno> alunos) {
        listaAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunos
        ));
    }
}
