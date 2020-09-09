package com.example.mentor.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mentor.especialidade.Especialidade;
import com.example.mentor.mentorado.Mentorado;
import com.example.mentor.mentor.Mentor;
import com.example.mentor.tarefa.Tarefa;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "mentorapp.db";
    private static final int VERSAO = 3;

    // MENTORADO
    private static final String TABELA_MENTORADO = "mentorado";
    private static final String EMAIL_MENTORADO = "email";
    private static final String NOME_MENTORADO = "nome";
    private static final String TELEFONE_MENTORADO = "telefone";
    private static final String SENHA_MENTORADO = "senha";

    // ESPECIALIDADE
    private static final String TABELA_ESPECIALIDADE = "especialidade";
    private static final String ID_ESPECIALIDADE = "id";
    private static final String TITULO = "titulo";

    // MENTOR
    private static final String TABELA_MENTOR = "mentor";
    private static final String EMAIL_MENTOR = "email";
    private static final String ID_ESPECIALIDADE_MENTOR = "id_especialidade";
    private static final String NOME_MENTOR = "nome";
    private static final String TELEFONE_MENTOR = "telefone";
    private static final String SENHA_MENTOR = "senha";

    // TAREFA
    private static final String TABELA_TAREFA = "tarefa";
    private static final String ID_TAREFA = "id";
    private static final String EMAIL_MENTOR_TAREFA = "email_mentor";
    private static final String TITULO_TAREFA = "titulo";
    private static final String DESCRICAO_TAREFA = "descricao";
    private static final String NIVEL_TAREFA = "nivel";
    private static final String STATUS_TAREFA = "status";

    public DBHelper(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // MENTORADO
        String sql_mentorado = "CREATE TABLE "+TABELA_MENTORADO+" (\n" +
                EMAIL_MENTORADO+" TEXT PRIMARY KEY,\n" +
                NOME_MENTORADO+" TEXT  NOT NULL,\n" +
                TELEFONE_MENTORADO+" TEXT NULL,\n" +
                SENHA_MENTORADO+" TEXT  NOT NULL\n" +
                ")";

        sqLiteDatabase.execSQL(sql_mentorado);

        // ESPECIALIDADE
        String sql_especialidade = "CREATE TABLE "+TABELA_ESPECIALIDADE+" (\n" +
                ID_ESPECIALIDADE+" INTEGER  NOT NULL PRIMARY KEY,\n" +
                TITULO+" TEXT  NOT NULL\n" +
                ")";

        sqLiteDatabase.execSQL(sql_especialidade);

        // MENTOR
        String sql_mentor = "CREATE TABLE "+TABELA_MENTOR+" (\n" +
                EMAIL_MENTOR+" TEXT  UNIQUE NOT NULL PRIMARY KEY,\n" +
                ID_ESPECIALIDADE_MENTOR+" INTEGER  NOT NULL,\n" +
                NOME_MENTOR+" TEXT  NOT NULL,\n" +
                TELEFONE_MENTOR+" TEXT  NULL,\n" +
                SENHA_MENTOR+" TEXT  NOT NULL,\n" +
                "FOREIGN KEY ("+ID_ESPECIALIDADE_MENTOR+") REFERENCES "+TABELA_ESPECIALIDADE+" ("+ID_ESPECIALIDADE+")\n" +
                ")";

        sqLiteDatabase.execSQL(sql_mentor);

        // MENTOR
        String sql_tarefa = "CREATE TABLE "+TABELA_TAREFA+" (\n" +
                ID_TAREFA+" INTEGER  NOT NULL PRIMARY KEY,\n" +
                EMAIL_MENTOR_TAREFA+" TEXT  UNIQUE NOT NULL,\n" +
                TITULO_TAREFA+" TEXT  NOT NULL,\n" +
                DESCRICAO_TAREFA+" TEXT  NOT NULL,\n" +
                STATUS_TAREFA+" BOOLEAN  NOT NULL,\n" +
                NIVEL_TAREFA+" INTEGER  NOT NULL,\n" +
                "FOREIGN KEY ("+EMAIL_MENTOR_TAREFA+") REFERENCES "+TABELA_MENTOR+"("+EMAIL_MENTOR+")\n" +
                ")";

        sqLiteDatabase.execSQL(sql_tarefa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // MENTORADO
        String sql_mentorado = "DROP TABLE IF EXISTS "+TABELA_MENTORADO;
        sqLiteDatabase.execSQL(sql_mentorado);

        // ESPECIALIDADE
        String sql_especialidade = "DROP TABLE IF EXISTS "+TABELA_ESPECIALIDADE;
        sqLiteDatabase.execSQL(sql_especialidade);

        // MENTOR
        String sql_mentor = "DROP TABLE IF EXISTS "+TABELA_MENTOR;
        sqLiteDatabase.execSQL(sql_mentor);

        // MENTOR
        String sql_tarefa = "DROP TABLE IF EXISTS "+TABELA_TAREFA;
        sqLiteDatabase.execSQL(sql_tarefa);

        onCreate(sqLiteDatabase);
    }

    //------------------ MENTORADO ------------------

    // ADICIONANDO UM MENTORADO
    public boolean addMentorado(Mentorado mentorado){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL_MENTORADO,mentorado.getEmail());
        contentValues.put(NOME_MENTORADO,mentorado.getNome());
        contentValues.put(TELEFONE_MENTORADO,mentorado.getTelefone());
        contentValues.put(SENHA_MENTORADO,mentorado.getSenha());

        long result = db.insert(TABELA_MENTORADO,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    // SELECT EM TODOS OS MENTORADOS
    public Cursor todosMentorados(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA_MENTORADO;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        return cursor;
    }

    //------------------ ESPECIALIDADES ------------------

    // ADICIONANDO UMA ESPECIALIDADE
    public boolean addEspecialidade(Especialidade especialidade){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TITULO,especialidade.getTitulo());

        long result = db.insert(TABELA_ESPECIALIDADE,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    // SELECT EM TODAS AS ESPECIALIDADES
    public ArrayList<String> todasEspecialidades(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA_ESPECIALIDADE;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
                Especialidade e = new Especialidade();
                e.setTitulo(titulo);
                list.add(e.getTitulo());
            }
        }

        return list;
    }

    //------------------ MENTOR ------------------

    // ADICIONANDO UM MENTOR
    public boolean addMentor(Mentor mentor){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL_MENTOR,mentor.getEmail());
        contentValues.put(ID_ESPECIALIDADE_MENTOR,mentor.getEspecialidade());
        contentValues.put(NOME_MENTOR,mentor.getNome());
        contentValues.put(TELEFONE_MENTOR,mentor.getTelefone());
        contentValues.put(SENHA_MENTOR,mentor.getSenha());

        long result = db.insert(TABELA_MENTOR,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    // SELECT EM TODAS OS MENTORES
    public Cursor todosMentores(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA_MENTOR;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        return cursor;
    }

    //------------------ TAREFA ------------------

    // ADICIONANDO UMA TAREFA
    public boolean addTarefa(Tarefa tarefa){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TITULO_TAREFA,tarefa.getTitulo());
        contentValues.put(DESCRICAO_TAREFA,tarefa.getDescricao());
        contentValues.put(STATUS_TAREFA,tarefa.isStatus());
        contentValues.put(NIVEL_TAREFA,tarefa.getNivel());
        contentValues.put(EMAIL_MENTOR_TAREFA,tarefa.getEmail_mentor());

        long result = db.insert(TABELA_TAREFA,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    // SELECT EM TODAS AS TAREFAS
    public Cursor todasTarefas(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA_TAREFA;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        return cursor;
    }
}
