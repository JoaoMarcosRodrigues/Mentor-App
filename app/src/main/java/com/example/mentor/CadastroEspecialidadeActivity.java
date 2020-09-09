package com.example.mentor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CadastroEspecialidadeActivity extends AppCompatActivity {

    Button salvarEspecialidade;
    TextInputEditText editTitulo;
    Spinner spinner;

    ArrayAdapter<String> adapter;
    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_especialidade);

        salvarEspecialidade = findViewById(R.id.btnSalvarEspecialidade);
        editTitulo = findViewById(R.id.editTitulo);
        spinner = findViewById(R.id.spinnerEspecialidades);

        // LISTA DE ESPECIALIDADES CADASTRADAS
        final ArrayList<String> listTitulos = dbHelper.todasEspecialidades();
        adapter = new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txtTitulo,listTitulos);
        spinner.setAdapter(adapter);

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
                    listTitulos.add(e.getTitulo());
                    adapter.notifyDataSetChanged();
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