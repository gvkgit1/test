package com.example.osos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewItemAdapter extends RecyclerView.Adapter<RecyclerviewItemAdapter.MyViewHolder> {

    ArrayList<getdatanew> mArrayUri;
    RecyclerView recyclerView1;
    private List<getdata> itemsList;

    public static OnBindCallback onBind;

    RecyclerviewItemAdapter(List<getdata> mItemList){
        this.itemsList = mItemList;
    }

    @Override
    public RecyclerviewItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new MyViewHolder(view);
    }



    @Override public void onBindViewHolder(RecyclerviewItemAdapter.MyViewHolder holder, int position) {
        if (onBind != null) {
            onBind.onViewBound(holder, position);

            final getdata item = itemsList.get(position);
            holder.name.setText(item.getName());
        }
    }


    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public LinearLayout itemLayout;
        public ImageButton button;


        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
            itemLayout =  itemView.findViewById(R.id.ll);
            button=itemView.findViewById(R.id.imageButton);
            recyclerView1=itemView.findViewById(R.id.recyclerview1);
            mArrayUri = new ArrayList<>();
        }
    }
}