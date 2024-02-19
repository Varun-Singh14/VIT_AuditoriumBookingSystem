package com.helloworld.vitauditoriumbookingsys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helloworld.vitauditoriumbookingsys.HomeAdapter.AllBookingTicketsHelperClass;

import java.util.ArrayList;

public class AllBookingTicketsAdapter extends RecyclerView.Adapter<AllBookingTicketsAdapter.AllBookingViewHolder> {

    Context context;
    ArrayList<AllBookingTicketsHelperClass> bookingList;

    public AllBookingTicketsAdapter(Context context, ArrayList<AllBookingTicketsHelperClass> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public AllBookingTicketsAdapter.AllBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.activity_all_booking_tickets_cards,parent,false);
        return new AllBookingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllBookingTicketsAdapter.AllBookingViewHolder holder, int position) {
        AllBookingTicketsHelperClass albthc = bookingList.get(position);
        holder.roomName.setText(albthc.getRoomName());
        holder.bkDate.setText(albthc.getDate());
        holder.evtTitle.setText(albthc.getTitle());
        holder.strttym.setText(albthc.getStartTime());
        holder.endtym.setText(albthc.getEndTime());
        holder.sts.setText(albthc.getStatus());

    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class AllBookingViewHolder extends RecyclerView.ViewHolder {

        TextView roomName, bkDate, evtTitle, strttym, endtym, sts;

        public AllBookingViewHolder(@NonNull View itemView) {
            super(itemView);

            roomName = itemView.findViewById(R.id.AllBookingsCard_RoomName);
            bkDate = itemView.findViewById(R.id.AllBookingsCard_DateBooked);
            evtTitle = itemView.findViewById(R.id.AllBookingsCard_EventTitle);
            strttym = itemView.findViewById(R.id.AllBookingsCard_StartTym);
            endtym = itemView.findViewById(R.id.AllBookingsCard_EndTym);
            sts = itemView.findViewById(R.id.status_show);
        }
    }
}
