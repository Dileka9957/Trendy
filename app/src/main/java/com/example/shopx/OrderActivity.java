package com.example.shopx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shopx.R;
import com.example.shopx.delivery.AddDelivery;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    TextView iName;
    TextView iPrice;
    TextView iNote;
    TextView iDes;

    private String key;
    private String IName;
    private String IPrice;
    private String INote;
    private String IDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item);
        getSupportActionBar().hide();

        key=getIntent().getStringExtra("key");
        IName =getIntent().getStringExtra("title");
        IPrice =getIntent().getStringExtra("price");
        INote =getIntent().getStringExtra("contact");
        IDes =getIntent().getStringExtra("description");


        iName = findViewById(R.id.textView2);
        iName.setText(IName);
        iPrice =findViewById(R.id.textView5);
        iPrice.setText(IPrice);
        iNote =findViewById(R.id.textView3);
        iNote.setText(INote);
        iDes =findViewById(R.id.textView7);
        iDes.setText(IDes);
    }


    public void AddDelivery(View view) {
        Intent i = new Intent(getApplicationContext(), AddDelivery.class);
        startActivity(i);
    }
}