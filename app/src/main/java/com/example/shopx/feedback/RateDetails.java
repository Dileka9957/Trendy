package com.example.shopx.feedback;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopx.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class RateDetails extends AppCompatActivity {

    FirebaseDatabase firebase;
    DatabaseReference reference;
    EditText rname, rdetail;

    private String key;
    private String name;
    private String detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_update);
        getSupportActionBar().hide();

        key=getIntent().getStringExtra("key");
        name =getIntent().getStringExtra("Rname");
        detail =getIntent().getStringExtra("Rdetail");


        rname =(EditText)findViewById(R.id.uname);
        rname.setText(name);
        rdetail =(EditText)findViewById(R.id.udetails);
        rdetail.setText(detail);

    }


    public void Update(View view) {

        Rate rate=new Rate(name,detail);
        rate.setName(rname.getText().toString());
        rate.setDetails(rdetail.getText().toString());

        new RateHolder().rateUpdate(key, rate, new RateHolder.DataStatus() {
            @Override
            public void DataIsLoaded(List<Rate> rates, List<String> stringList) {

            }

            @Override
            public void DataIsUpdated() {
                Toast.makeText(RateDetails.this,"Rate Updated !", Toast.LENGTH_LONG).show();


            }

            @Override
            public void DataIsDeleted() {


            }
        });
    }

    public void Delete(View view) {
        new RateHolder().rateDelete(key, new RateHolder.DataStatus() {
            @Override
            public void DataIsLoaded(List<Rate> rates, List<String> stringList) {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {
                Toast.makeText(RateDetails.this,"Rate Distroyed", Toast.LENGTH_LONG).show();
                finish();return;


            }
        });

    }
}
