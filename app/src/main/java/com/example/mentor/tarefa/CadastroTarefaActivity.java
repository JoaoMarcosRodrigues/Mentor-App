package com.example.mentor.tarefa;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mentor.R;
import com.example.mentor.helper.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroTarefaActivity extends AppCompatActivity {
    private static final String TAG = "CadastroTarefa";
    TextInputEditText editTitulo,editDescricao;
    Button btnCadastrar;
    Switch switch_status;
    RadioGroup radioGroup;
    RadioButton radioButton;

    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefa);

        editTitulo = findViewById(R.id.titulo);
        editDescricao = findViewById(R.id.descricao);
        btnCadastrar = findViewById(R.id.cadastrar);
        switch_status = findViewById(R.id.switchStatus);
        radioGroup = findViewById(R.id.radioGroup);

        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = editTitulo.getText().toString();
                String descricao = editDescricao.getText().toString();
                boolean status;
                int valorRadio = Integer.parseInt(radioButton.getText().toString());

                if(switch_status.isChecked())
                    status = true;
                else
                    status = false;

                if(titulo.equals(""))
                    editTitulo.setError("Campo obrigatório!");
                else if(descricao.equals(""))
                    editDescricao.setError("Campo obrigatório!");
                else{
                    Tarefa t = new Tarefa();
                    t.setTitulo(titulo);
                    t.setDescricao(descricao);
                    t.setNivel(valorRadio);
                    t.setStatus(status);
                    //t.setEmail_mentor("joao@gmail.com");
                    //t.setEmail_mentorado("pedro@gmail.com");
                    addTarefa(t);
                }
            }
        });
    }

    private void addTarefa(Tarefa t) {
        boolean insert = dbHelper.addTarefa(t);

        if(insert)
            Toast.makeText(this,"Tarefa adicionada com sucesso!",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Ops, houve um erro =(",Toast.LENGTH_SHORT).show();
    }

    private void todasTarefas(){
        Cursor cursor = dbHelper.todasTarefas();

        while(cursor.moveToNext()){
            Log.d(TAG,cursor.getString(cursor.getColumnIndex("id"))+" - "+
                            cursor.getString(cursor.getColumnIndex("email_mentor"))+" - "+
                    cursor.getString(cursor.getColumnIndex("titulo"))+" - "+
                    cursor.getString(cursor.getColumnIndex("descricao"))+" - "+
                    cursor.getString(cursor.getColumnIndex("status"))+" - "+
                    cursor.getString(cursor.getColumnIndex("nivel")));
        }
    }
}