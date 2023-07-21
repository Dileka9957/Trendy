package com.example.shopx.delivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopx.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    TextInputEditText dname, dphone, daddress, dloction;
    TextView dorderId;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dname = findViewById(R.id.updatename);
        dorderId = findViewById(R.id.updateid);
        dphone = findViewById(R.id.updatephone);
        daddress = findViewById(R.id.updateaddress);
        dloction = findViewById(R.id.updatelocation);

        String name,id,phone,address,loction;

        name =getIntent().getStringExtra("name");
        id =getIntent().getStringExtra("orderID");
        phone =getIntent().getStringExtra("phone");
        address =getIntent().getStringExtra("address");
        loction =getIntent().getStringExtra("location");

        dname.setText(name);
        dorderId.setText(id);
        dphone.setText(phone);
        daddress.setText(address);
        dloction.setText(loction);


    }

    public void GetStatus(View view) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("delivery");

        String orderName = dname.getText().toString();
        String orderID = dorderId.getText().toString();
        String phone = dphone.getText().toString();
        String address = daddress.getText().toString();
        String location = dloction.getText().toString();

        Delivery delivery = new Delivery(orderName, orderID, phone, address, location);

        databaseReference.child(orderID).setValue(delivery);
        Toast.makeText(UpdateActivity.this, "Delivery Details Updated Sucessfully", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), StatusActivity.class);
        intent.putExtra("name", dname.getText().toString());
        intent.putExtra("orderID", dorderId.getText().toString());
        intent.putExtra("phone", dphone.getText().toString());
        intent.putExtra("address", daddress.getText().toString());
        intent.putExtra("location", dloction.getText().toString());
        startActivity(intent);
    }


}
