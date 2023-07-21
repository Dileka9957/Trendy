package com.example.shopx.itemManage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.shopx.R;
import com.example.shopx.View_Additems;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddItem extends AppCompatActivity {

    private static final  int Gallery_Code=1;
    Uri imageUrl=null;
    ProgressDialog progressDialog;

    EditText itemTitle, itemDes, itemPrize,itemContact;
    ImageButton imageButton;
    Button addItem;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("items");
        firebaseStorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);

        itemTitle = (EditText) findViewById(R.id.et_titleF);
        itemDes = (EditText) findViewById(R.id.et_descriptionF);
        itemPrize = (EditText) findViewById(R.id.et_price);
        itemContact = (EditText) findViewById(R.id.et_contactF);
        addItem = (Button) findViewById(R.id.et_btnF);
        imageButton = (ImageButton) findViewById(R.id.imageButton);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, Gallery_Code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Code && requestCode == RESULT_OK){
            imageUrl= data.getData();
            imageButton.setImageURI(imageUrl);
        }
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = itemTitle.getText().toString().trim();
                String description = itemDes.getText().toString().trim();
                String prize = itemPrize.getText().toString().trim();
                String contact = itemContact.getText().toString().trim();

                if (!title.isEmpty()|| !description.isEmpty() || !prize.isEmpty()  || !contact.isEmpty() || imageUrl!=null){
                    progressDialog.setTitle("Uploading....");
                    progressDialog.show();
                    imageUrl= data.getData();

                    StorageReference filepath= firebaseStorage.getReference().child("images").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadUrl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t=task.getResult().toString();
                                    DatabaseReference newPost= databaseReference.push();

                                    newPost.child("title").setValue(title);
                                    newPost.child("description").setValue(description);
                                    newPost.child("prize").setValue(prize);
                                    newPost.child("contact").setValue(contact);
                                    newPost.child("image").setValue(task.getResult().toString());
                                    progressDialog.dismiss();

                                    Intent intent = new Intent(getApplicationContext(), View_Additems.class);
                                    startActivity(intent);

                                }
                            });
                        }
                    });
                }


            }

        });

    }
}
