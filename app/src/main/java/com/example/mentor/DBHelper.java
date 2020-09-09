package com.example.mentor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "mentorapp.db";
    private static final int VERSAO = 2;

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // MENTORADO
        String sql_mentorado = "DROP TABLE IF EXISTS "+TABELA_MENTORADO;
        sqLiteDatabase.execSQL(sql_mentorado);

        // ESPECIALIDADE
        String sql_especialidade = "DROP TABLE IF EXISTS "+TABELA_ESPECIALIDADE;
        sqLiteDatabase.execSQL(sql_especialidade);
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

    //------------------ MENTORADO ------------------

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
                list.add(titulo);
            }
        }

        return list;
    }
}
