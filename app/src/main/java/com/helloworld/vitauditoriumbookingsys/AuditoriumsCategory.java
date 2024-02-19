package com.helloworld.vitauditoriumbookingsys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.helloworld.vitauditoriumbookingsys.Room.Room1;
import com.helloworld.vitauditoriumbookingsys.Room.Room2;
import com.helloworld.vitauditoriumbookingsys.Room.Room3;

public class AuditoriumsCategory extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auditoriums_category);



    }

    public void MahatmaAudiLoader(View view) {
        Intent aIntent = new Intent(this, Room1.class);
        startActivity(aIntent);
    }

    public void NethajiAudiLoader(View view) {
        Intent bIntent = new Intent(this, Room2.class);
        startActivity(bIntent);
    }

    public void VOCAudiLoader(View view) {
        Intent cIntent = new Intent(this, Room3.class);
        startActivity(cIntent);
    }
}
