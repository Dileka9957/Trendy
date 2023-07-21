package com.example.shopx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shopx.feedback.AddRate;
import com.example.shopx.itemManage.AddItem;
import com.example.shopx.userManage.Customer_profile_up;

import androidx.appcompat.app.AppCompatActivity;



public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityhome);
    }



    public void feedbak(View view) {
        Intent i = new Intent(getApplicationContext(), AddRate.class);
        startActivity(i);
    }

    public void Profilepage(View view) {
        Intent i = new Intent(getApplicationContext(), Customer_profile_up.class);
        startActivity(i);
    }

    public void GetItems(View view) {
        Intent i = new Intent(getApplicationContext(), ItemList.class);
        startActivity(i);
    }
}
