package com.example.shopx.itemManage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopx.ItemModel;
import com.example.shopx.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class Update_item extends AppCompatActivity {

    EditText iName;
    EditText iPrice;
    EditText iNote;
    EditText iDes;

    Button Update_btn;

    private String key;
    private String IName;
    private String IPrice;
    private String INote;
    private String IDes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);
        getSupportActionBar().hide();

        key=getIntent().getStringExtra("key");
        IName =getIntent().getStringExtra("title");
        IPrice =getIntent().getStringExtra("price");
        INote =getIntent().getStringExtra("contact");
        IDes =getIntent().getStringExtra("description");


        iName =(EditText)findViewById(R.id.et_upitme_name);
        iName.setText(IName);
        iPrice =(EditText)findViewById(R.id.et_upitme_price);
        iPrice.setText(IPrice);
        iNote =(EditText)findViewById(R.id.et_upitme_about);
        iNote.setText(INote);
        iDes =(EditText)findViewById(R.id.et_upitme_des);
        iDes.setText(IDes);

        Update_btn=(Button)findViewById(R.id.bt_upitme_update);

        Update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemModel item=new ItemModel();
                item.setTitle(iName.getText().toString());
                item.setPrize(iPrice.getText().toString());
                item.setContact(iNote.getText().toString());
                item.setContact(iDes.getText().toString());

                new ItemHelper().deleteItems(key, item, new ItemHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<ItemModel> items, List<String> stringList) {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(Update_item.this,"Item Deleted Successfully", Toast.LENGTH_LONG).show();
                        finish();return;

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });




    }




    //Ignore this for test purpose
    public void updateItem(View view) {

    }
}
