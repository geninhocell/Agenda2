package com.alura.agenda2;

import android.app.Application;

import com.alura.agenda2.dao.AlunoDAO;
import com.alura.agenda2.model.Aluno;

public class Agenda2Application extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        AlunoDAO alunoDAO = new AlunoDAO();
//        for (int i = 0; i < 10; i++) {
        alunoDAO.salva(new Aluno("Alex", "6258569858", "alex@gmail.com"));
        alunoDAO.salva((new Aluno("Gui", "6478589656", "gui@alura.com.br")));
//        }
    }
}
