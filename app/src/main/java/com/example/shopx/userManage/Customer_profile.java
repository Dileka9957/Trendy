package com.example.shopx.userManage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopx.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class Customer_profile extends AppCompatActivity {

    EditText name, email, address, phone, password;
    Button signUp;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        getSupportActionBar().hide();


        name = findViewById(R.id.et_cusprofile_name);
        email = findViewById(R.id.et_cusprofile_email);
        address = findViewById(R.id.et_cusprofile_address);
        phone = findViewById(R.id.et_cusprofile_phone);
        password = findViewById(R.id.et_cusprofile_pw);
        signUp = findViewById(R.id.bt_cusprofile_submit);
        //regToLoginBtn = findViewById(R.id.reg_login_btn);
//Save data in FireBase on button click
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");
//Get all the values

                String username = name.getText().toString();
                String useremail = email.getText().toString();
                String useraddress = address.getText().toString();
                String phoneNo = phone.getText().toString();
                String userpassword = Customer_profile.this.password.getText().toString();

                if(username.isEmpty() || useremail.isEmpty() || useraddress.isEmpty() || phoneNo.isEmpty() || userpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Fill all Fields", Toast.LENGTH_SHORT).show();
                }else {
                    User userclass = new User(username, useremail, useraddress, phoneNo, userpassword);
                    reference.child(username).setValue(userclass);

                    Toast.makeText(getApplicationContext(), "Successfully User Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Customer_profile.this, Login.class);
                    startActivity(intent);
                }
            }
        });//Re

        // gister Button method end
    }
}