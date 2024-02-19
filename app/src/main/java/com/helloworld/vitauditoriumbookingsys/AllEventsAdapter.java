package com.helloworld.vitauditoriumbookingsys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllEventsAdapter extends RecyclerView.Adapter<AllEventsAdapter.AllEventsViewHolder> {

    Context context;
    ArrayList<AllEventsHelperClass> eventsList;

    public AllEventsAdapter(Context context, ArrayList<AllEventsHelperClass> eventsList) {
        this.context = context;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public AllEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_all_events_cards, parent, false);
        return new AllEventsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllEventsViewHolder holder, int position) {
        AllEventsHelperClass aehc = eventsList.get(position);
        holder.evRoomName.setText(aehc.getRoomName());
        holder.evbkDate.setText(aehc.getDate());
        holder.evEVTitle.setText(aehc.getTitle());
        holder.evStrtTym.setText(aehc.getStartTime());
        holder.evEndTym.setText(aehc.getEndTime());
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class AllEventsViewHolder extends RecyclerView.ViewHolder{

        TextView evRoomName, evbkDate, evEVTitle, evStrtTym, evEndTym;

        public AllEventsViewHolder(@NonNull View itemView) {
            super(itemView);

            evRoomName = itemView.findViewById(R.id.AllEventsCard_RoomName);
            evbkDate = itemView.findViewById(R.id.AllEventsCard_DateBooked);
            evEVTitle = itemView.findViewById(R.id.AllEventsCard_EventTitle);
            evStrtTym = itemView.findViewById(R.id.AllEventsCard_StartTym);
            evEndTym = itemView.findViewById(R.id.AllEventsCard_eventsEndTym);

        }
    }
}
