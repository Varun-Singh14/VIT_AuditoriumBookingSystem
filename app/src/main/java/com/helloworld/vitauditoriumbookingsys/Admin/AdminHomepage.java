package com.helloworld.vitauditoriumbookingsys.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.helloworld.vitauditoriumbookingsys.R;

public class AdminHomepage extends AppCompatActivity {


    RelativeLayout pending_button, approved_button, users_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);

        pending_button = findViewById(R.id.pending_card_button);
        approved_button = findViewById(R.id.approved_card_button);
        users_button = findViewById(R.id.users_button);

        pending_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomepage.this, Admin_pending_request.class);
                startActivity(intent);
                finish();
            }
        });

        approved_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomepage.this, Admin_approved_request.class);
                startActivity(intent);
                finish();
            }
        });

        users_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomepage.this, Admin_pending_request.class);
                startActivity(intent);
                finish();
            }
        });
    }
}