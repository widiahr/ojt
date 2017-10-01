package com.example.april.approval;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Loginnya extends AppCompatActivity {

    private static final String TAG = Loginnya.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginnya);
        Button login = (Button) findViewById(R.id.btnlogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(Loginnya.this, MainActivity.class);
                startActivity(menu);
            }
        });

        Button regis = (Button) findViewById(R.id.btnRegis);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regis = new Intent(Loginnya.this, RegisActivity.class);
                startActivity(regis);



            }
        });


    }
}