package com.helloworld.vitauditoriumbookingsys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.helloworld.vitauditoriumbookingsys.Room.Room4;
import com.helloworld.vitauditoriumbookingsys.Room.Room5;

public class ConferenceRoomCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_room_category);
    }

    public void ConferenceRoom1Loader(View view) {
        Intent aIntent = new Intent(this, Room4.class);
        startActivity(aIntent);
    }

    public void RUBYLoader(View view) {
        Intent bIntent = new Intent(this, Room5.class);
        startActivity(bIntent);
    }
}