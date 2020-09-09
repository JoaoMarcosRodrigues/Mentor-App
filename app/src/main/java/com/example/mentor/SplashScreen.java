package com.example.mentor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mentor.login.OpcaoCadastroActivity;
import com.example.mentor.login.RedefinirSenhaActivity;
import com.example.mentor.mentor.CadastroMentorActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, RedefinirSenhaActivity.class);
                startActivity(i);

                finish();
            }
        },3000);
    }
}