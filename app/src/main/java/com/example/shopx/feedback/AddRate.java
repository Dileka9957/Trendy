package com.example.shopx.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopx.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddRate extends AppCompatActivity {

    FirebaseDatabase firebase;
    DatabaseReference reference;
    EditText rname, rdetail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_add);

        firebase = FirebaseDatabase.getInstance();
        reference = firebase.getReference().child("rates");

        rname = (EditText) findViewById(R.id.rname);
        rdetail = (EditText) findViewById(R.id.rdetail);
    }

    public void Post(View view) {

        String name = rname.getText().toString().trim();
        String detail = rdetail.getText().toString().trim();

        if (name.isEmpty()||detail.isEmpty()){

            Toast.makeText(getApplicationContext(), "Fill given All", Toast.LENGTH_SHORT).show();

        }else{
            Rate rate = new Rate(name, detail);
            reference.child(name).setValue(rate);

            Toast.makeText(getApplicationContext(), "Rated about Item", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), View_Rates.class);
            startActivity(intent);
        }

    }
}
