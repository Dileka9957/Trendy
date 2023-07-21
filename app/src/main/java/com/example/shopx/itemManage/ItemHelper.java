package com.example.shopx.itemManage;

import com.example.shopx.feedback.RateHolder;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class ItemHelper {

    private FirebaseDatabase mdata;
    private DatabaseReference mRef;
    private List<com.example.shopx.ItemModel> itemModels =new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<com.example.shopx.ItemModel> items, List<String> keys);
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public ItemHelper() {
        mdata= FirebaseDatabase.getInstance();
        mRef=mdata.getReference("items");

    }

    public void readItems(final DataStatus dataStatus){
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemModels.clear();
                List<String> keys=new ArrayList<>();
                for (DataSnapshot keyNode:dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    com.example.shopx.ItemModel itemModel=keyNode.getValue(com.example.shopx.ItemModel.class);
                    itemModels.add(itemModel);
                }
                dataStatus.DataIsLoaded(itemModels,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deleteItems(String key, com.example.shopx.ItemModel items, final DataStatus dataStatus){
        mRef.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }




}
