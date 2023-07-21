package com.example.shopx.feedback;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shopx.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RateRecyclerView {

    private Context mContext;
    private ItemAdapter rateadapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Rate> rates, List<String> keys){
        mContext=context;
        rateadapter =new ItemAdapter(rates,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(rateadapter);
    }

    class ItemView extends RecyclerView.ViewHolder{
        private TextView ratename;
        private TextView ratedetail;

        private String key;

        public ItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.add_list_rate,parent,false));


            ratename =(TextView)itemView.findViewById(R.id.Item_Name);
            ratedetail =(TextView)itemView.findViewById(R.id.Item_Price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, RateDetails.class);
                    intent.putExtra("key",key);
                    intent.putExtra("Rname", ratename.getText().toString());
                    intent.putExtra("Rdetail", ratedetail.getText().toString());

                    mContext.startActivity(intent);
                }
            });
        }
        public void bind(Rate rate, String key){
            ratename.setText(rate.getName());
            ratedetail.setText(rate.getDetails());
            this.key=key;

        }
    }
    class ItemAdapter extends RecyclerView.Adapter<ItemView>{
        private List<Rate> rateList;
        private List<String> stringList;

        public ItemAdapter(List<Rate> rateList, List<String> stringList) {
            this.rateList = rateList;
            this.stringList = stringList;
        }

        @NonNull
        @Override
        public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemView holder, int position) {
            holder.bind(rateList.get(position), stringList.get(position));

        }

        @Override
        public int getItemCount() {
            return rateList.size();
        }
    }
}
