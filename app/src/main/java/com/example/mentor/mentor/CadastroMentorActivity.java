package com.example.mentor.mentor;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mentor.helper.DBHelper;
import com.example.mentor.especialidade.Especialidade;
import com.example.mentor.R;
import com.google.android.material.textfield.TextInputEditText;
import com.santalu.maskedittext.MaskEditText;

import java.util.ArrayList;

public class CadastroMentorActivity extends AppCompatActivity {

    private static final String TAG = "CadastroMentor";
    TextInputEditText editEmail,editNome,editSenha;
    MaskEditText editTelefone;
    Button btnCadastrar;
    Spinner spinner;

    ArrayAdapter<String> adapter;
    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_mentor);

        editEmail = findViewById(R.id.email);
        editNome = findViewById(R.id.nome);
        editTelefone = findViewById(R.id.telefone);
        editSenha = findViewById(R.id.senha);
        spinner = findViewById(R.id.spinnerEspecialidades);
        btnCadastrar = findViewById(R.id.cadastrar);

        // LISTA DE ESPECIALIDADES CADASTRADAS
        final ArrayList<String> listEspecialidades = dbHelper.todasEspecialidades();
        adapter = new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txtTitulo,listEspecialidades);
        spinner.setAdapter(adapter);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString();
                String nome = editNome.getText().toString();
                String telefone = editTelefone.getText().toString();
                String senha = editSenha.getText().toString();
                String especialidade = spinner.getSelectedItem().toString();

                if(email.equals(""))
                    editEmail.setError("Campo obrigatório!");
                else if(nome.equals(""))
                    editNome.setError("Campo obrigatório!");
                else if(telefone.equals(""))
                    telefone = "Nenhum";
                else if(senha.equals(""))
                    editSenha.setError("Campo obrigatório!");
                else if(especialidade.equals(""))
                    Toast.makeText(CadastroMentorActivity.this,"Selecione uma especialidade!",Toast.LENGTH_SHORT).show();
                else{
                    Mentor m = new Mentor();
                    Especialidade e = new Especialidade();
                    m.setEmail(email);
                    m.setNome(nome);
                    m.setTelefone(telefone);
                    m.setSenha(senha);
                    m.setEspecialidade(spinner.getSelectedItem().toString());
                    adicionarMentor(m);
                    todosMentores();
                    listEspecialidades.add(e.getTitulo());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void adicionarMentor(Mentor m) {
        boolean insert = dbHelper.addMentor(m);

        if(insert)
            Toast.makeText(this,"Mentor cadastrado com sucesso!",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Ops, houve um erro =(",Toast.LENGTH_SHORT).show();
    }

    private void todosMentores(){
        Cursor cursor = dbHelper.todosMentores();

        while (cursor.moveToNext()){
            Log.d(TAG,cursor.getString(cursor.getColumnIndex("email")) + " - "+
                    cursor.getString(cursor.getColumnIndex("nome"))+" - "+
                    cursor.getString(cursor.getColumnIndex("telefone"))+" - "+
                    cursor.getString(cursor.getColumnIndex("senha"))+" - "+
                    cursor.getString(cursor.getColumnIndex("id_especialidade")));
        }
    }
}