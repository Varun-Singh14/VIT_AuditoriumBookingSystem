package com.helloworld.vitauditoriumbookingsys.HomeAdapter.BookedSlotsRoom1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helloworld.vitauditoriumbookingsys.Model.RoomE;
import com.helloworld.vitauditoriumbookingsys.Model.RoomF;
import com.helloworld.vitauditoriumbookingsys.R;

import java.util.ArrayList;

public class Room6Adapter extends RecyclerView.Adapter<Room6Adapter.Room6ViewHolder>{

    Context context;
    ArrayList<RoomF> roomFlist;

    public Room6Adapter(Context context, ArrayList<RoomF> roomFlist) {
        this.context = context;
        this.roomFlist = roomFlist;

    }

    @NonNull
    @Override
    public Room6ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.room1_booked_slots_cardview,parent,false);
        return new Room6ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Room6ViewHolder holder, int position) {
        RoomF roomF = roomFlist.get(position);
        holder.title.setText(roomF.getTitle());
        holder.date.setText(roomF.getDate());

    }

    @Override
    public int getItemCount() {
        return roomFlist.size();
    }

    public class Room6ViewHolder extends RecyclerView.ViewHolder{

        TextView title, date;

        public Room6ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.room1_booked_slots_title);
            date = itemView.findViewById(R.id.room1_booked_slots_date);

        }
    }
}
