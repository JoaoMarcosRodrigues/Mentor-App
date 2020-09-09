package com.example.mentor.especialidade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mentor.R;
import com.example.mentor.helper.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class CadastroEspecialidadeActivity extends AppCompatActivity {

    Button salvarEspecialidade;
    TextInputEditText editTitulo;

    ArrayAdapter<String> adapter;
    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_especialidade);

        salvarEspecialidade = findViewById(R.id.btnSalvarEspecialidade);
        editTitulo = findViewById(R.id.editTitulo);

        // AÇÃO DO BOTÃO DE SALVAR ESPECIALIDADE
        salvarEspecialidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = editTitulo.getText().toString();

                if(titulo.equals("")){
                    editTitulo.setError("Campo obrigatório!");
                }else {
                    Especialidade e = new Especialidade();
                    e.setTitulo(titulo);
                    addEspecialidade(e);
                }
            }
        });
    }

    private void addEspecialidade(Especialidade especialidade) {
        boolean insert = dbHelper.addEspecialidade(especialidade);

        if(insert)
            Toast.makeText(this,"Especialidade cadastrada com sucesso!",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Ops, houve um erro =(",Toast.LENGTH_SHORT).show();
    }

}