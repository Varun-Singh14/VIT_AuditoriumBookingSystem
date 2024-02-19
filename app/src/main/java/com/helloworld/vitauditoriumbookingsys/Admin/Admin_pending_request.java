package com.helloworld.vitauditoriumbookingsys.Admin;

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
import com.helloworld.vitauditoriumbookingsys.AllEventsHelperClass;
import com.helloworld.vitauditoriumbookingsys.R;

import java.util.ArrayList;

public class Admin_pending_request extends AppCompatActivity {

//    String userRegNum;
    ArrayList<AllEventsHelperClass> allTicketsList;
    RecyclerView rv;
    Admins_Pending_Request_Adapter adtv;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pending_request);

        allTicketsList = new ArrayList<>();
//        userRegNum = Common.currentUser.getRegNo();
        rv = findViewById(R.id.pending_request_recyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adtv = new Admins_Pending_Request_Adapter(Admin_pending_request.this, allTicketsList);

        db = FirebaseDatabase.getInstance().getReference();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allTicketsList.clear();
                for (DataSnapshot fds: snapshot.getChildren()){
                    if (fds.getKey().startsWith("Room")){
                        for (DataSnapshot sds: fds.getChildren()) {
                            AllEventsHelperClass abthc = sds.getValue(AllEventsHelperClass.class);
                            assert abthc != null;
                            if (abthc.getStatus().equals("pending")) {
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