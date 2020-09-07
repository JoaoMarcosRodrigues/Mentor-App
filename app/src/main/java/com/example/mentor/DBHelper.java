package com.example.mentor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static int VERSAO = 1;
    private static String NOME_BANCO = "mentorapp.db";
    // MENTORADO
    private static final String TABELA_MENTORADO = "mentorado";
    private static final String EMAIL_MENTORADO = "email";
    private static final String NOME_MENTORADO = "nome";
    private static final String TELEFONE_MENTORADO = "telefone";
    private static final String SENHA_MENTORADO = "senha";

    public DBHelper(Context context){
        super(context,NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA_MENTORADO+" (\n" +
                EMAIL_MENTORADO+" TEXT  UNIQUE NOT NULL PRIMARY KEY,\n" +
                NOME_MENTORADO+" TEXT  NOT NULL,\n" +
                TELEFONE_MENTORADO+" TEXT  UNIQUE NULL,\n" +
                SENHA_MENTORADO+" TEXT  NOT NULL\n" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql_mentorado = "DROP TABLE IF EXISTS "+TABELA_MENTORADO;
        sqLiteDatabase.execSQL(sql_mentorado);
        onCreate(sqLiteDatabase);
    }

    public boolean addMentorado(String email, String nome, String telefone ,String senha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EMAIL_MENTORADO,email);
        contentValues.put(NOME_MENTORADO,nome);
        contentValues.put(TELEFONE_MENTORADO,telefone);
        contentValues.put(SENHA_MENTORADO,senha);

        long result = db.insert(TABELA_MENTORADO,null,contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
