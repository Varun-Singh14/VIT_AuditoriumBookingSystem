package com.helloworld.vitauditoriumbookingsys.HomeAdapter.BookedSlotsRoom1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helloworld.vitauditoriumbookingsys.Model.RoomE;
import com.helloworld.vitauditoriumbookingsys.R;

import java.util.ArrayList;

public class Room5Adapter extends RecyclerView.Adapter<Room5Adapter.Room5ViewHolder>{

    Context context;
    ArrayList<RoomE> roomElist;

    public Room5Adapter(Context context, ArrayList<RoomE> roomElist) {
        this.context = context;
        this.roomElist = roomElist;

    }

    @NonNull
    @Override
    public Room5ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.room1_booked_slots_cardview,parent,false);
        return new Room5ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Room5ViewHolder holder, int position) {
        RoomE roomE = roomElist.get(position);
        holder.title.setText(roomE.getTitle());
        holder.date.setText(roomE.getDate());

    }

    @Override
    public int getItemCount() {
        return roomElist.size();
    }

    public class Room5ViewHolder extends RecyclerView.ViewHolder{

        TextView title, date;

        public Room5ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.room1_booked_slots_title);
            date = itemView.findViewById(R.id.room1_booked_slots_date);

        }
    }
}
