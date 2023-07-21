package com.example.shopx.feedback;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class RateHolder {

    private FirebaseDatabase firebase;
    private DatabaseReference reference;
    private List<Rate> rates =new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Rate> rates, List<String> keys);
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public RateHolder() {
        firebase = FirebaseDatabase.getInstance();
        reference = firebase.getReference("rates");

    }

    public void rateRead(final DataStatus dataStatus){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rates.clear();
                List<String> keys=new ArrayList<>();
                for (DataSnapshot keyNode:dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Rate rate=keyNode.getValue(Rate.class);
                    rates.add(rate);
                }
                dataStatus.DataIsLoaded(rates,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void rateUpdate(String key, Rate rates, final DataStatus dataStatus){
        reference.child(key).setValue(rates).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void rateDelete(String key, final DataStatus dataStatus){
        reference.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }



}
