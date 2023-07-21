package com.example.shopx.delivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shopx.MainActivity;
import com.example.shopx.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Track  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
    }

    public void BackHome(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
