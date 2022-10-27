package com.example.mjucampusguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class FCAdapter extends RecyclerView.Adapter<FCAdapter.FCViewHolder> {
    private LinkedList<FC> linkedList;
    private Context context;

    public FCAdapter(LinkedList<FC> linkedList, Context context) {
        this.linkedList = linkedList;
        this.context = context;
    }

    @NonNull
    @Override
    public FCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fc,parent, false);
        FCViewHolder holder = new FCViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FCViewHolder holder, int position) {
        holder.tv_name.setText(linkedList.get(position).getName());
        if(linkedList.get(position).getAddress() == 2){
            holder.tv_name.setText("학관");
        }else if(linkedList.get(position).getAddress() == 3){
            holder.tv_name.setText("복지동");
        }else if(linkedList.get(position).getAddress() == 5){
            holder.tv_name.setText("명덕관,명현관");
        }else if(linkedList.get(position).getAddress() == 7){
            holder.tv_name.setText("1공,5공학관");
        }else if(linkedList.get(position).getAddress() == 8){
            holder.tv_name.setText("명진당");
        }else if(linkedList.get(position).getAddress() == 10){
            holder.tv_name.setText("신학협력관,방목기념관");
        }else if(linkedList.get(position).getAddress() == 11){
            holder.tv_name.setText("함박관");
        }else if(linkedList.get(position).getAddress() == 12){
            holder.tv_name.setText("차세대 과학관");
        }else if(linkedList.get(position).getAddress() == 13){
            holder.tv_name.setText("건축관,자연조형센터,디자인관");
        }else if(linkedList.get(position).getAddress() == 14){
            holder.tv_name.setText("3공학관");
        }

        holder.tv_etc.setText(linkedList.get(position).getComment());

    }

    @Override
    public int getItemCount() {
        return (linkedList != null ? linkedList.size() : 0);
    }

    public class FCViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_bilding;
        TextView tv_etc;
        public FCViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_name = itemView.findViewById(R.id.tv_name_id);
            this.tv_bilding = itemView.findViewById(R.id.tv_bilding_id);
            this.tv_etc = itemView.findViewById(R.id.tv_etc_id);
        }
    }
}
