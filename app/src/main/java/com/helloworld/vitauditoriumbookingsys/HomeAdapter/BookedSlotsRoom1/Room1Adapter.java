package com.helloworld.vitauditoriumbookingsys.HomeAdapter.BookedSlotsRoom1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helloworld.vitauditoriumbookingsys.Model.RoomB;
import com.helloworld.vitauditoriumbookingsys.R;

import java.util.ArrayList;

public class Room1Adapter extends RecyclerView.Adapter<Room1Adapter.Room1ViewHolder>{

    Context context;
    ArrayList<RoomB> roomAlist;

    public Room1Adapter(Context context, ArrayList<RoomB> roomAlist) {
        this.context = context;
        this.roomAlist = roomAlist;

    }

    @NonNull
    @Override
    public Room1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.room1_booked_slots_cardview,parent,false);
        return new Room1ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Room1ViewHolder holder, int position) {
        RoomB roomA = roomAlist.get(position);
        holder.title.setText(roomA.getTitle());
        holder.date.setText(roomA.getDate());

    }

    @Override
    public int getItemCount() {
        return roomAlist.size();
    }

    public class Room1ViewHolder extends RecyclerView.ViewHolder{

        TextView title, date;

        public Room1ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.room1_booked_slots_title);
            date = itemView.findViewById(R.id.room1_booked_slots_date);

        }
    }
}
