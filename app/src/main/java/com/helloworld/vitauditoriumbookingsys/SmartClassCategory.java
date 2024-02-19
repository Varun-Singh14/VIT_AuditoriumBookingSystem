package com.helloworld.vitauditoriumbookingsys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.helloworld.vitauditoriumbookingsys.Room.Room6;

public class SmartClassCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_class_category);
    }

    public void SmartClassLoader(View view) {
        Intent sIntent = new Intent(this, Room6.class);
        startActivity(sIntent);
    }
}