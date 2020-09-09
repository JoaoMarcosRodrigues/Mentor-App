package com.example.mentor;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.santalu.maskedittext.MaskEditText;

public class CadastroMentoradoActivity extends AppCompatActivity {

    private static final String TAG = "CadastroMentorado";
    TextInputEditText editEmail,editNome,editSenha;
    MaskEditText editTelefone;
    Button btnCadastrar;

    DBHelper dbHelper = new DBHelper(this);
    Mentorado m = new Mentorado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_mentorado);

        editEmail = findViewById(R.id.email);
        editNome = findViewById(R.id.nome);
        editSenha = findViewById(R.id.senha);
        editTelefone = findViewById(R.id.telefone);
        btnCadastrar = findViewById(R.id.cadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = editNome.getText().toString();
                String email = editEmail.getText().toString();
                String senha = editSenha.getText().toString();
                String telefone = editTelefone.getRawText();

                if(email.equals(""))
                    editEmail.setError("Campo obrigatório!");
                else if(nome.equals(""))
                    editNome.setError("Campo obrigatório!");
                else if(senha.equals(""))
                    editSenha.setError("Campo obrigatório!");
                else{
                    m.setEmail(email);
                    m.setNome(nome);
                    m.setTelefone(telefone);
                    m.setSenha(senha);
                    addMentorado(m);
                    //pegarMentorados();
                }
            }
        });
    }

    private void addMentorado(Mentorado m) {
        boolean insert = dbHelper.addMentorado(m);

        if(insert)
            Toast.makeText(this,"Mentorado cadastrado com sucesso!",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Ops, houve um erro no cadastro =(",Toast.LENGTH_SHORT).show();
    }

    private void pegarMentorados(){
        Cursor cursor = dbHelper.todosMentorados();
        while (cursor.moveToNext()){
            Log.d(TAG,cursor.getString(cursor.getColumnIndex("email"))+" - "+
                    cursor.getString(cursor.getColumnIndex("nome"))+" - "+
                    cursor.getString(cursor.getColumnIndex("telefone"))+" - "+
                    cursor.getString(cursor.getColumnIndex("senha")));
        }
    }
}