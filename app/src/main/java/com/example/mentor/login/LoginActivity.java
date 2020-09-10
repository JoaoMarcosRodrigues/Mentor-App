package com.example.mentor.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mentor.R;
import com.example.mentor.helper.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText editEmail,editSenha;
    Button btnCadastrar,btnEntrar;
    TextView tvRedefinirSenha;

    CheckBox checkLembrar;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnCadastrar = findViewById(R.id.btnCadastrese);
        btnEntrar = findViewById(R.id.btnEntrar);
        tvRedefinirSenha = findViewById(R.id.viewRedefinirSenha);
        checkLembrar = findViewById(R.id.checkLembrar);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // sharedPreferences = getSharedPreference("mydatabase",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        checkSharedPreferences();

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
                Intent intent = new Intent(getApplicationContext(),RedefinirSenhaActivity.class);
                startActivity(intent);
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString();
                String senha = editSenha.getText().toString();

                if(checkLembrar.isChecked()){
                    editor.putString(getString(R.string.checkbox),"True");
                    editor.commit();

                    editor.putString(getString(R.string.email),email);
                    editor.commit();

                    editor.putString(getString(R.string.senha),senha);
                    editor.commit();
                }else{
                    editor.putString(getString(R.string.checkbox),"False");
                    editor.commit();

                    editor.putString(getString(R.string.email),"");
                    editor.commit();

                    editor.putString(getString(R.string.senha),"");
                    editor.commit();
                }

                if(email.equals(""))
                    editEmail.setError("Campo obrigatório!");
                else if(senha.equals(""))
                    editSenha.setError("Campo obrigatório!");
                else
                    entrar(email,senha);
            }
        });
    }

    private void checkSharedPreferences() {
        String checkbox = sharedPreferences.getString(getString(R.string.checkbox),"False");
        String email = sharedPreferences.getString(getString(R.string.email),"");
        String senha = sharedPreferences.getString(getString(R.string.senha),"");

        editEmail.setText(email);
        editSenha.setText(senha);

        if(checkbox.equals("True"))
            checkLembrar.setChecked(true);
        else
            checkLembrar.setChecked(false);
    }

    private void entrar(String email, String senha) {
        boolean entrar = dbHelper.autenticaUsuario(email,senha);

        if(entrar)
            Toast.makeText(this,"Login efetuado com sucesso!",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Ops, houve um erro! Tente outro email ou senha.",Toast.LENGTH_SHORT).show();
    }
}