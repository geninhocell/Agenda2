package com.alura.agenda2.dao;

import androidx.annotation.Nullable;

import com.alura.agenda2.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private static int contadorIds = 1;
    private final static List<Aluno> alunos = new ArrayList<>();

    public void salva(Aluno alunoCriado) {
        alunoCriado.setId(contadorIds);
        alunos.add(alunoCriado);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorIds++;
    }

    public void edita(Aluno aluno){
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);
        if(alunoEncontrado != null){
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPeloId(Aluno aluno) {
        for(Aluno a : alunos){
            if(a.getId() == aluno.getId()){
                return a;
            }

        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = buscaAlunoPeloId(aluno);
        if(alunoDevolvido != null) {
            alunos.remove(alunoDevolvido);
        }
    }
}
