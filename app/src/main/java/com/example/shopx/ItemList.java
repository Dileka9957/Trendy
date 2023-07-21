package com.example.shopx;

        import android.os.Bundle;
        import android.view.View;

        import com.example.shopx.ItemHelper2;

        import java.util.List;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.RecyclerView;

public class ItemList extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_additems);
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView_Item);
        getSupportActionBar().hide();


        new ItemHelper2().readItems(new ItemHelper2.DataStatus() {
            @Override
            public void DataIsLoaded(List<ItemModel> items, List<String> keys) {
                findViewById(R.id.Loading).setVisibility(View.GONE);
                new RecyclerView_config().setConfig(mRecyclerView, com.example.shopx.ItemList.this, items,keys);
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

