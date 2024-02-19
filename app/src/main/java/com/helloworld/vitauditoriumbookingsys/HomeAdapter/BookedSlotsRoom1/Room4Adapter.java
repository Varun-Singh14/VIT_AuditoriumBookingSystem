package com.helloworld.vitauditoriumbookingsys.HomeAdapter.BookedSlotsRoom1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helloworld.vitauditoriumbookingsys.Model.RoomC;
import com.helloworld.vitauditoriumbookingsys.Model.RoomD;
import com.helloworld.vitauditoriumbookingsys.R;

import java.util.ArrayList;

public class Room4Adapter extends RecyclerView.Adapter<Room4Adapter.Room4ViewHolder>{

    Context context;
    ArrayList<RoomD> roomDlist;

    public Room4Adapter(Context context, ArrayList<RoomD> roomDlist) {
        this.context = context;
        this.roomDlist = roomDlist;

    }

    @NonNull
    @Override
    public Room4ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.room1_booked_slots_cardview,parent,false);
        return new Room4ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Room4ViewHolder holder, int position) {
        RoomD roomD = roomDlist.get(position);
        holder.title.setText(roomD.getTitle());
        holder.date.setText(roomD.getDate());

    }

    @Override
    public int getItemCount() {
        return roomDlist.size();
    }

    public class Room4ViewHolder extends RecyclerView.ViewHolder{

        TextView title, date;

        public Room4ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.room1_booked_slots_title);
            date = itemView.findViewById(R.id.room1_booked_slots_date);

        }
    }
}
