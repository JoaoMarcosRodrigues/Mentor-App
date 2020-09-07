package com.example.mentor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.santalu.maskedittext.MaskEditText;

public class CadastroMentoradoActivity extends AppCompatActivity {

    TextInputEditText editEmail,editNome,editSenha;
    MaskEditText editTelefone;
    Button btnCadastrar;

    DBHelper dbHelper;

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

                if(nome.equals(""))
                    editNome.setError("Campo obrigatório!");
                else if(email.equals(""))
                    editEmail.setError("Campo obrigatório!");
                else if(senha.equals(""))
                    editSenha.setError("Campo obrigatório!");
                else{
                    cadastrarMentorado(email,nome,telefone,senha);
                    //Toast.makeText(CadastroMentoradoActivity.this,"Qualquer coisa",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void cadastrarMentorado(String email, String nome, String telefone, String senha){
        boolean insert = dbHelper.addMentorado(email,nome,telefone,senha);

        if(insert){
            Toast.makeText(this,"Usuário cadastrado com sucesso!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Desculpe, houve um erro!",Toast.LENGTH_SHORT).show();
        }
    }
}