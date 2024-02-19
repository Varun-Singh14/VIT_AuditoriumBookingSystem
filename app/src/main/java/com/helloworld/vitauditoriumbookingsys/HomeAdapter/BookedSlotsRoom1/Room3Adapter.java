package com.helloworld.vitauditoriumbookingsys.HomeAdapter.BookedSlotsRoom1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helloworld.vitauditoriumbookingsys.Model.RoomC;
import com.helloworld.vitauditoriumbookingsys.R;

import java.util.ArrayList;

public class Room3Adapter extends RecyclerView.Adapter<Room3Adapter.Room3ViewHolder>{

    Context context;
    ArrayList<RoomC> roomClist;

    public Room3Adapter(Context context, ArrayList<RoomC> roomClist) {
        this.context = context;
        this.roomClist = roomClist;

    }

    @NonNull
    @Override
    public Room3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.room1_booked_slots_cardview,parent,false);
        return new Room3ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Room3ViewHolder holder, int position) {
        RoomC roomC = roomClist.get(position);
        holder.title.setText(roomC.getTitle());
        holder.date.setText(roomC.getDate());

    }

    @Override
    public int getItemCount() {
        return roomClist.size();
    }

    public class Room3ViewHolder extends RecyclerView.ViewHolder{

        TextView title, date;

        public Room3ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.room1_booked_slots_title);
            date = itemView.findViewById(R.id.room1_booked_slots_date);

        }
    }
}
