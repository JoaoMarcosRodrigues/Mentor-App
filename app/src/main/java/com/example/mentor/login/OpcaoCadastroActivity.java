package com.example.mentor.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mentor.R;
import com.example.mentor.mentor.CadastroMentorActivity;
import com.example.mentor.mentorado.CadastroMentoradoActivity;

public class OpcaoCadastroActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButtonMentorado,radioButtonMentor;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcao_cadastro);

        radioGroup = findViewById(R.id.radioGroup);
        imageButton = findViewById(R.id.imageButton);
        radioButtonMentorado = findViewById(R.id.radio_mentorado);
        radioButtonMentor = findViewById(R.id.radio_mentor);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valorRadio = "";
                if(radioButtonMentorado.isChecked())
                    valorRadio = "MENTORADO";
                if(radioButtonMentor.isChecked())
                    valorRadio = "MENTOR";

                if(valorRadio.equals("MENTORADO")){
                    Intent intent = new Intent(getApplicationContext(),CadastroMentoradoActivity.class);
                    startActivity(intent);
                }else if(valorRadio.equals("MENTOR")){
                    Intent intent = new Intent(getApplicationContext(), CadastroMentorActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Selecione uma das opções!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}