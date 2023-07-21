package com.example.shopx.feedback;

import android.os.Bundle;
import android.view.View;

import com.example.shopx.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class View_Rates extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rates);
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView_Item);


        new RateHolder().rateRead(new RateHolder.DataStatus() {
            @Override
            public void DataIsLoaded(List<Rate> rates, List<String> keys) {
                findViewById(R.id.Loading).setVisibility(View.GONE);
                new RateRecyclerView().setConfig(mRecyclerView, View_Rates.this, rates,keys);
            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
