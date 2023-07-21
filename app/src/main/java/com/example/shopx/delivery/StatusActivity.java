package com.example.shopx.delivery;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopx.MainActivity;
import com.example.shopx.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StatusActivity extends AppCompatActivity {
    TextView dname, dphone, daddress, dloction;
    TextView dorderId;
    Button button;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("delivery");

        dname = findViewById(R.id.rname);
        dorderId = findViewById(R.id.rID);
        dphone = findViewById(R.id.rTime);
        daddress = findViewById(R.id.raddress);
        dloction = findViewById(R.id.rfee);
        button = findViewById(R.id.rdelete);

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

        Call(dloction);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(dname.getContext());
                builder.setTitle("Are you sure Delete this?");
                builder.setMessage("Delete data can not be recovered");

                builder.setPositiveButton("delete", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        String orderNum = dorderId.getText().toString().trim();

                        databaseReference.child(orderNum).setValue(null);
                        Toast.makeText(StatusActivity.this, "Delivery Cancelled ", Toast.LENGTH_LONG).show();
                        dorderId.setText("");
                        dname.setText("");
                        dorderId.setText("");
                        dphone.setText("");
                        dloction.setText("");

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }


                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(dorderId.getContext(), "cancelled.", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });
    }


    public void delete(View view) {



        String orderNum = dorderId.getText().toString().trim();

        databaseReference.child(orderNum).setValue(null);
        Toast.makeText(StatusActivity.this, "Delivery Cancelled ", Toast.LENGTH_LONG).show();
        dorderId.setText("");
        dname.setText("");
        dorderId.setText("");
        dphone.setText("");
        dloction.setText("");

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void Track(View view) {
        Intent intent = new Intent(getApplicationContext(), Track.class);
        startActivity(intent);

    }

    private void Call(TextView dloction) {
        int chargeperKM=50;

        int value=Integer.parseInt(dloction.getText().toString());
        value=value*chargeperKM;
        dloction.setText(String.valueOf(value));


    }
}
