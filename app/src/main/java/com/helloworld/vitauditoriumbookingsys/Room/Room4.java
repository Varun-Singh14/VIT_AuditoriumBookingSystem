package com.helloworld.vitauditoriumbookingsys.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.helloworld.vitauditoriumbookingsys.DateBooking.DateBookingRoom4;
import com.helloworld.vitauditoriumbookingsys.HomeAdapter.BookedSlotsRoom1.Room3Adapter;
import com.helloworld.vitauditoriumbookingsys.HomeAdapter.BookedSlotsRoom1.Room4Adapter;
import com.helloworld.vitauditoriumbookingsys.Model.RoomC;
import com.helloworld.vitauditoriumbookingsys.Model.RoomD;
import com.helloworld.vitauditoriumbookingsys.R;

import java.util.ArrayList;

public class Room4  extends AppCompatActivity {

    Room4Adapter room4Adapter;
    RecyclerView room4recycler;

    DatabaseReference databaseReference;

    ArrayList<RoomD> roomDList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room4);

        Button next4;

        databaseReference = FirebaseDatabase.getInstance().getReference("RoomD");
        room4recycler = findViewById(R.id.booked_slots_room4_date_recyclerView);
        room4recycler.setHasFixedSize(true);
        room4recycler.setLayoutManager(new LinearLayoutManager(this));
        roomDList = new ArrayList<>();
        room4Adapter = new Room4Adapter(getApplicationContext(), roomDList);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                roomDList.clear();
                for (DataSnapshot ds:snapshot.getChildren()) {
                    RoomD roomD = ds.getValue(RoomD.class);
                    roomD.setDate(ds.getKey());
                    roomDList.add(roomD);
                }
                room4Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        room4recycler.setAdapter(room4Adapter);

        next4 = (Button) findViewById(R.id.btn_next_step_room4);

        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Room4.this, DateBookingRoom4.class);
                startActivity(intent);
            }
        });

    }
}
