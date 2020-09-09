package com.example.mentor.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mentor.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText editEmail,editSenha;
    Button btnCadastrar,btnEntrar;
    TextView tvRedefinirSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnCadastrar = findViewById(R.id.btnCadastrese);
        btnEntrar = findViewById(R.id.btnEntrar);
        tvRedefinirSenha = findViewById(R.id.viewRedefinirSenha);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OpcaoCadastroActivity.class);
                startActivity(intent);
            }
        });

        tvRedefinirSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OpcaoCadastroActivity.class);
                startActivity(intent);
            }
        });
    }
}