package com.example.mentor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.mentor.mentor.CadastroMentorActivity;
import com.example.mentor.mentorado.CadastroMentoradoActivity;
import com.example.mentor.tarefa.CadastroTarefaActivity;

public class MainActivity extends AppCompatActivity {

    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);

        setSingleEvent(gridLayout);
    }

    private void setSingleEvent(GridLayout gridLayout) {
        for(int i=0; i<gridLayout.getChildCount(); i++){
            final CardView cardView = (CardView) gridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(cardView.getCardBackgroundColor().getDefaultColor() == -1){
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        //Toast.makeText(MainActivity.this,"State clicked: True",Toast.LENGTH_SHORT).show();
                    }else{
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        //Toast.makeText(MainActivity.this,"State clicked: False",Toast.LENGTH_SHORT).show();
                    }

                    Intent intent;
                    switch (finalI){
                        case 0:
                            intent = new Intent(MainActivity.this, CadastroMentoradoActivity.class);
                            startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent(MainActivity.this, CadastroMentorActivity.class);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(MainActivity.this, CadastroTarefaActivity.class);
                            startActivity(intent);
                            break;
                        case 3:
                            Toast.makeText(MainActivity.this,"Ajuda em construção!",Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Toast.makeText(MainActivity.this,"Sobre em construção!",Toast.LENGTH_SHORT).show();
                            break;
                        case 5:
                            Toast.makeText(MainActivity.this,"Mentoria em construção!",Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }
    }
}