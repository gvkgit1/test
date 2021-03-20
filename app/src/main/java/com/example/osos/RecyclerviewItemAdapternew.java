package com.example.osos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerviewItemAdapternew extends RecyclerView.Adapter<RecyclerviewItemAdapternew.MyViewHolder> {

    private List<getdatanew> itemsList;


    RecyclerviewItemAdapternew(List<getdatanew> mItemList){
        this.itemsList = mItemList;
    }

    @Override
    public RecyclerviewItemAdapternew.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsnew,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerviewItemAdapternew.MyViewHolder holder, final int position) {
        final getdatanew item = itemsList.get(position);
        holder.imageView.setImageURI(item.getImageurl());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout itemLayout;
        public ImageView imageView;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemLayout =  itemView.findViewById(R.id.ll);
            imageView=itemView.findViewById(R.id.imageView);


        }
    }
}