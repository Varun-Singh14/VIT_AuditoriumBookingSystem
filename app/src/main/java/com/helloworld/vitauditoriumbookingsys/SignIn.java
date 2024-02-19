package com.helloworld.vitauditoriumbookingsys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.helloworld.vitauditoriumbookingsys.Admin.AdminHomepage;
import com.helloworld.vitauditoriumbookingsys.Common.Common;
import com.helloworld.vitauditoriumbookingsys.Model.User;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.net.MalformedURLException;
import java.net.URL;

public class SignIn extends AppCompatActivity {

    EditText edtRegNo, edtPassword;
    Button btnSignIn, okbutton;
    String admin;
    Dialog login_popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtRegNo = (MaterialEditText) findViewById(R.id.Reg_no);
        edtPassword = (MaterialEditText) findViewById(R.id.Pass);
        btnSignIn = (Button) findViewById(R.id.btn_SignIn);


        admin = "admin";

        //Init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_admin = database.getReference("Admin");
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                if(edtRegNo.getText().toString().equals("ADMIN")) {
                    if(edtPassword.getText().toString().equals("admin")) {
                        mDialog.dismiss();
                        Intent dashboardIntent = new Intent(getBaseContext(), AdminHomepage.class);
                        startActivity(dashboardIntent);
                        finish();

                    } else {
                        Toast.makeText(SignIn.this, "Wrong Admin Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    table_user.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //Check if user exist in Database or not
                            if (snapshot.child(edtRegNo.getText().toString()).exists()) {

                                //Get User Information
                                mDialog.dismiss();
                                User user = snapshot.child(edtRegNo.getText().toString()).getValue(User.class);
                                if (user.getPassword().equals(edtPassword.getText().toString())) {
                                    user.setRegNo(edtRegNo.getText().toString());
                                    Intent dashboardIntent = new Intent(getBaseContext(), Dashboard.class);
                                    Common.currentUser = user;
                                    startActivity(dashboardIntent);
                                    Toast.makeText(SignIn.this, "Login Sucess", Toast.LENGTH_LONG).show();
                                } else {
//                                    Toast.makeText(SignIn.this, "Wrong Password !!!", Toast.LENGTH_SHORT).show();
                                    emptyPass();
                                }
                            } else {
                                mDialog.dismiss();
                                Toast.makeText(SignIn.this, "User not found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }



            }
        });
    }

    private void emptyPass() {
        AlertDialog dlg = new AlertDialog.Builder(SignIn.this)
                .setTitle("ERROR")
                .setMessage("Incorrect Username or Password...!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .create();
        dlg.show();
    }

    private void success() {
        setContentView(R.layout.login_success_popup);

        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }



}