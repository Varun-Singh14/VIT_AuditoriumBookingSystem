package com.helloworld.vitauditoriumbookingsys.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.helloworld.vitauditoriumbookingsys.AllEventsHelperClass;
import com.helloworld.vitauditoriumbookingsys.Model.RoomA;
import com.helloworld.vitauditoriumbookingsys.R;

import java.util.ArrayList;

public class Admins_Pending_Request_Adapter extends RecyclerView.Adapter<Admins_Pending_Request_Adapter.Admins_Pending_Request_AdapterViewHolder> {


    Context context;
    ArrayList<AllEventsHelperClass> eventsList;

    DatabaseReference dr;

    public Admins_Pending_Request_Adapter(Context context, ArrayList<AllEventsHelperClass> eventsList) {
        this.context = context;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public Admins_Pending_Request_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.pending_req_card, parent, false);
        return new Admins_Pending_Request_AdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Admins_Pending_Request_AdapterViewHolder holder, int position) {
        AllEventsHelperClass aehc = eventsList.get(position);
        holder.evRoomName.setText(aehc.getRoomName());
        holder.evRegNum.setText(aehc.getRegNo());
        holder.evbkDate.setText(aehc.getDate());
        holder.evEVTitle.setText(aehc.getTitle());
        holder.evStrtTym.setText(aehc.getStartTime());
        holder.evEndTym.setText(aehc.getEndTime());

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aehc.setStatus("Approved");
                dr = FirebaseDatabase.getInstance().getReference(aehc.getRoomNameDB());

                dr.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        RoomA roomA = new RoomA(aehc.getRegNo(),aehc.getName(), aehc.getTitle(), aehc.getDescription(), aehc.getStartTime(), aehc.getEndTime(), aehc.getStatus());
                        dr.child(aehc.getDate()).setValue(roomA);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aehc.setStatus("Rejected");
                dr = FirebaseDatabase.getInstance().getReference(aehc.getRoomNameDB());

                dr.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        RoomA roomA = new RoomA(aehc.getRegNo(),aehc.getName(), aehc.getTitle(), aehc.getDescription(), aehc.getStartTime(), aehc.getEndTime(), aehc.getStatus());
                        dr.child(aehc.getDate()).setValue(roomA);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class Admins_Pending_Request_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView evRegNum, evRoomName, evbkDate, evEVTitle, evStrtTym, evEndTym;

        RelativeLayout accept, reject;

        public Admins_Pending_Request_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            evRegNum = itemView.findViewById(R.id.pend_regno);
            evRoomName = itemView.findViewById(R.id.pend_Room_name);
            evbkDate = itemView.findViewById(R.id.pend_date);
            evEVTitle = itemView.findViewById(R.id.pend_title);
            evStrtTym = itemView.findViewById(R.id.pend_startTime);
            evEndTym = itemView.findViewById(R.id.pend_endTym);
            accept = itemView.findViewById(R.id.approve_button);
            reject = itemView.findViewById(R.id.decline_button);
        }
    }
}
