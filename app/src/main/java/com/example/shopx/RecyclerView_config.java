package com.example.shopx;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopx.itemManage.Update_item;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerView_config {

    private Context mContext;
    private ItemAdapter mFeedBackAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<ItemModel> items, List<String> keys){
        mContext=context;
        mFeedBackAdapter =new ItemAdapter(items,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mFeedBackAdapter);
    }

    class ItemView extends RecyclerView.ViewHolder{
        FirebaseDatabase firebase;
        DatabaseReference reference;
        private Button delete;
        private TextView itemTitle;
        private TextView itemPrice;
        private TextView itemContact;
        private TextView itemDes;
        private ImageView imageView;



        private String key;

        public ItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.order_list_items,parent,false));



            itemTitle =(TextView)itemView.findViewById(R.id.Item_Name);
            itemPrice =(TextView)itemView.findViewById(R.id.Item_Price);
            itemContact =(TextView)itemView.findViewById(R.id.Item_Note);
            itemDes =(TextView)itemView.findViewById(R.id.Item_des);

            imageView=(ImageView)itemView.findViewById(R.id.imageView4);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, OrderActivity.class);
                    intent.putExtra("key",key);
                    intent.putExtra("title", itemTitle.getText().toString());
                    intent.putExtra("price", itemPrice.getText().toString());
                   intent.putExtra("contact", itemContact.getText().toString());
                   intent.putExtra("description", itemDes.getText().toString());

                    mContext.startActivity(intent);
                }
            });



            firebase = FirebaseDatabase.getInstance();
            reference = firebase.getReference("items");


        }
        public void bind(ItemModel item, String key){
            itemTitle.setText(item.getTitle());
            itemPrice.setText(item.getPrize());
            itemContact.setText(item.getContact());
            itemDes.setText(item.getDescription());
            String imageUri=null;
            imageUri=item.getImage();
            Picasso.get().load(imageUri).into(imageView);
            this.key=key;

        }
    }
    class ItemAdapter extends RecyclerView.Adapter<ItemView>{
        private List<ItemModel> itemList;
        private List<String> mKeys;

        public ItemAdapter(List<ItemModel> itemList, List<String> mKeys) {
            this.itemList = itemList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemView holder, int position) {
            holder.bind(itemList.get(position),mKeys.get(position));

        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }
    }
}

