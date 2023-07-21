package com.example.shopx.userManage;

import android.os.Bundle;
import android.widget.EditText;

import com.example.shopx.R;

import androidx.appcompat.app.AppCompatActivity;

public class Customer_profile_up extends AppCompatActivity {
    EditText adminUname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile_up);

        getSupportActionBar().hide();

        adminUname = findViewById(R.id.et_adminlog_uname);
    }
}