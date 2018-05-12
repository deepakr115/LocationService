package com.example.deepak.serv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class clicked extends AppCompatActivity implements View.OnClickListener {

    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked);
        back=findViewById(R.id.backB);
        back.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "OnPAuse", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v == back){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }
}
