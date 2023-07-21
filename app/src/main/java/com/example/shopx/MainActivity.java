package com.example.shopx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shopx.feedback.AddRate;
import com.example.shopx.itemManage.AddItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void order(View view) {
        Intent i = new Intent(getApplicationContext(), AddItem.class);
        startActivity(i);
    }

    public void feedbak(View view) {
        Intent i = new Intent(getApplicationContext(), AddRate.class);
        startActivity(i);
    }
}