package com.example.mjucampusguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FCAdapter extends RecyclerView.Adapter<FCAdapter.FCViewHolder> {
    private ArrayList<FC> arrayList;
    private Context context;

    public FCAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fc, parent, false);
        FCViewHolder holder = new FCViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FCViewHolder holder, int position) {
        holder.tv_name.setText(arrayList.get(position).getName());
        int i = arrayList.get(position).getAddress();
        if(i == 2){
            holder.tv_bd.setText("학관");
        }else if(i == 3){
            holder.tv_bd.setText("복지동");
        }else if(i == 5){
            holder.tv_bd.setText("명덕관,명현관");
        }else if(i == 7){
            holder.tv_bd.setText("1공,5공학관");
        }else if(i == 8){
            holder.tv_bd.setText("명진당");
        }else if(i == 10){
            holder.tv_bd.setText("신학협력관,방목기념관");
        }else if(i == 11){
            holder.tv_bd.setText("함박관");
        }else if(i == 12){
            holder.tv_bd.setText("차세대 과학관");
        }else if(i == 13){
            holder.tv_bd.setText("건축관,자연조형센터,디자인관");
        }else if(i == 14){
            holder.tv_bd.setText("3공학관");
        }
        holder.tv_etc.setText(arrayList.get(position).getComment());

    }

    public void setArrayList(ArrayList<FC> a){
        this.arrayList = a;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class FCViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_bd;
        TextView tv_etc;
        public FCViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_name = itemView.findViewById(R.id.tv_name_id);
            this.tv_bd = itemView.findViewById(R.id.tv_bilding_id);
            this.tv_etc = itemView.findViewById(R.id.tv_etc_id);
        }

    }

}
