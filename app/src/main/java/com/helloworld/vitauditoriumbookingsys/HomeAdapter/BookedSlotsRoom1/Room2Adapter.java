package com.helloworld.vitauditoriumbookingsys.HomeAdapter.BookedSlotsRoom1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helloworld.vitauditoriumbookingsys.Model.RoomB;
import com.helloworld.vitauditoriumbookingsys.R;

import java.util.ArrayList;

public class Room2Adapter extends RecyclerView.Adapter<Room2Adapter.Room2ViewHolder>{

    Context context;
    ArrayList<RoomB> roomBlist;

    public Room2Adapter(Context context, ArrayList<RoomB> roomBlist) {
        this.context = context;
        this.roomBlist = roomBlist;

    }

    @NonNull
    @Override
    public Room2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.room1_booked_slots_cardview,parent,false);
        return new Room2ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Room2ViewHolder holder, int position) {
        RoomB roomB = roomBlist.get(position);
        holder.title.setText(roomB.getTitle());
        holder.date.setText(roomB.getDate());

    }

    @Override
    public int getItemCount() {
        return roomBlist.size();
    }

    public class Room2ViewHolder extends RecyclerView.ViewHolder{

        TextView title, date;

        public Room2ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.room1_booked_slots_title);
            date = itemView.findViewById(R.id.room1_booked_slots_date);

        }
    }
}
