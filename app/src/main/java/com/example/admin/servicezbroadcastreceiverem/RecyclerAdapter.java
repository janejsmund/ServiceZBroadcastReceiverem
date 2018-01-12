package com.example.admin.servicezbroadcastreceiverem;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 12/5/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewholder> {

    private ArrayList<IncomingMessage> arrayList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<IncomingMessage> arrayList) {

        this.arrayList = arrayList;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);

        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {

        holder.id.setText(Integer.toString(arrayList.get(position).getId()));
        holder.message.setText(arrayList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {

        TextView id, message;

        public MyViewholder(View itemView) {

            super(itemView);

            id = (TextView) itemView.findViewById(R.id.txtId);
            message = (TextView) itemView.findViewById(R.id.txtMessage);
        }
    }
}
