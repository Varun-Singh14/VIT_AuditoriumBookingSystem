package com.helloworld.vitauditoriumbookingsys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.helloworld.vitauditoriumbookingsys.Common.Common;
import com.helloworld.vitauditoriumbookingsys.HomeAdapter.AllBookingTicketsHelperClass;

import java.util.ArrayList;

public class AllBookingTickets extends AppCompatActivity {

    String userRegNo;
    ArrayList<AllBookingTicketsHelperClass> allTicketsList;
    RecyclerView rv;
    AllBookingTicketsAdapter adtv;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_booking_tickets);

        allTicketsList = new ArrayList<>();
        userRegNo = Common.currentUser.getRegNo();
        rv = findViewById(R.id.AllBookingsRecyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adtv = new AllBookingTicketsAdapter(AllBookingTickets.this, allTicketsList);

        db = FirebaseDatabase.getInstance().getReference();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allTicketsList.clear();
                for (DataSnapshot fds: snapshot.getChildren()){
                    if (fds.getKey().startsWith("Room")){
                        for (DataSnapshot sds: fds.getChildren()){
                            String dbRegNo = sds.child("regNo").getValue().toString();
                            if (dbRegNo.equals(userRegNo)){
                                AllBookingTicketsHelperClass abthc = sds.getValue(AllBookingTicketsHelperClass.class);
                                abthc.setDate(sds.getKey());
                                abthc.setRoomName(fds.getKey());
                                allTicketsList.add(abthc);
                            }
                        }
                    }
                }
                adtv.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        rv.setAdapter(adtv);
    }
}