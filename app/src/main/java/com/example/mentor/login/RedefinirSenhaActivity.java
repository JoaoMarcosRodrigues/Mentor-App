package com.example.mentor.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mentor.R;
import com.example.mentor.helper.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

public class RedefinirSenhaActivity extends AppCompatActivity {

    TextInputEditText editEmail,editSenha,editResenha;
    Button btnRedefinir;

    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha);

        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        editResenha = findViewById(R.id.editResenha);
        btnRedefinir = findViewById(R.id.btnRedefinir);

        btnRedefinir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String senha = editSenha.getText().toString();
                String resenha = editResenha.getText().toString();
                String email = editEmail.getText().toString();

                if(email.length() == 0){
                    editEmail.setError("Campo obrigatório");
                }else if(senha.length() == 0){
                    editSenha.setError("Campo obrigatório");
                }else if(!senha.equals(resenha)){
                    editResenha.setError("Senha não confere! Tente novamente");
                }else{
                    redefinirSenha(email,senha);
                }
            }
        });
    }

    // VERIFICAR SE ESTÁ CERTO
    private void redefinirSenha(String email, String senha) {
        if(dbHelper.buscaEmail(email) == "mentorado") {
            boolean ok = dbHelper.redefinirSenhaMentorado(email, senha);
            if(ok){
                Toast.makeText(this,"Senha alterada!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Houve um erro! Tente mais tarde.",Toast.LENGTH_SHORT).show();
            }
        } else if(dbHelper.buscaEmail(email) == "mentor") {
            boolean ok = dbHelper.redefinirSenhaMentor(email, senha);
            if(ok){
                Toast.makeText(this,"Senha alterada!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Houve um erro! Tente mais tarde.",Toast.LENGTH_SHORT).show();
            }
        } else
            Toast.makeText(this,"Usuário não encontrado!",Toast.LENGTH_SHORT).show();
    }
}