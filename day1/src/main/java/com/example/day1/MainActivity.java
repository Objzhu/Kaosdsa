package com.example.day1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText phone;
    private EditText pass;
    private Button bt_login;
    private Button bt_zhuche;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        bt_login = findViewById(R.id.bt_login);
        bt_zhuche = findViewById(R.id.bt_zhuche);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gphone = phone.getText().toString();
                String gpass = pass.getText().toString();
                if (gphone.length()==11||gpass.length()>=6){
                    Toast.makeText(MainActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
                    intent = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                }

            }
        });
        bt_zhuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                startActivity(intent);
            }
        });

    }
}
