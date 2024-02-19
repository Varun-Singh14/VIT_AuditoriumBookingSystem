package com.helloworld.vitauditoriumbookingsys.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.helloworld.vitauditoriumbookingsys.AllEventsHelperClass;
import com.helloworld.vitauditoriumbookingsys.ConferenceRoomCategory;
import com.helloworld.vitauditoriumbookingsys.Model.RoomA;
import com.helloworld.vitauditoriumbookingsys.R;

import java.util.ArrayList;

public class Admins_Approved_Request_Adapter extends RecyclerView.Adapter<Admins_Approved_Request_Adapter.Admins_Approved_Request_AdapterViewHolder> {

    Context context;
    ArrayList<AllEventsHelperClass> eventsList;

    DatabaseReference dr;

    public Admins_Approved_Request_Adapter(Context context, ArrayList<AllEventsHelperClass> eventsList) {
        this.context = context;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public Admins_Approved_Request_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.approved_req_card, parent, false);
        return new Admins_Approved_Request_AdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Admins_Approved_Request_AdapterViewHolder holder, int position) {
        AllEventsHelperClass aehc = eventsList.get(position);
        holder.evRegNum.setText(aehc.getRegNo());
        holder.evRoomName.setText(aehc.getRoomName());
        holder.evbkDate.setText(aehc.getDate());
        holder.evEVTitle.setText(aehc.getTitle());
        holder.evStrtTym.setText(aehc.getStartTime());
        holder.evEndTym.setText(aehc.getEndTime());
//
//        dr = FirebaseDatabase.getInstance().getReference(aehc.getRoomNameDB());
//        if (aehc.getStatus().equals("Approved")) {
//            dr.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    RoomA roomA = new RoomA(aehc.getRegNo(), aehc.getName(), aehc.getTitle(), aehc.getDescription(), aehc.getStartTime(), aehc.getEndTime(), aehc.getStatus());
//                    dr.child(aehc.getDate()).setValue(roomA);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }
//        else {
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class Admins_Approved_Request_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView  evRegNum, evRoomName, evbkDate, evEVTitle, evStrtTym, evEndTym;

        public Admins_Approved_Request_AdapterViewHolder(View itemView) {
            super(itemView);

            evRegNum = itemView.findViewById(R.id.approved_regNo);
            evRoomName = itemView.findViewById(R.id.approved_RoomName);
            evbkDate = itemView.findViewById(R.id.approved_date);
            evEVTitle = itemView.findViewById(R.id.approved_title);
            evStrtTym = itemView.findViewById(R.id.approved_StartTym);
            evEndTym = itemView.findViewById(R.id.approved_endTym);
        }
    }
}
