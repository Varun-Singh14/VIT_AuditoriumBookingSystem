package com.helloworld.vitauditoriumbookingsys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.helloworld.vitauditoriumbookingsys.Admin.AdminHomepage;
import com.helloworld.vitauditoriumbookingsys.Common.Common;
import com.helloworld.vitauditoriumbookingsys.Model.Admin;
import com.rengwuxian.materialedittext.MaterialEditText;

public class AdminSignIn extends AppCompatActivity {

    EditText edtRegNo, edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtRegNo = (MaterialEditText) findViewById(R.id.Reg_no);
        edtPassword = (MaterialEditText) findViewById(R.id.Pass);
        btnSignIn = (Button) findViewById(R.id.btn_SignIn);


        //Init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_admin = database.getReference("Admin");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog mDialog = new ProgressDialog(AdminSignIn.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                table_admin.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //Check if user exist in Database or not
                        if (snapshot.child(edtRegNo.getText().toString()).exists()){

                            //Get User Information
                            mDialog.dismiss();
                            Admin admin = snapshot.child(edtRegNo.getText().toString()).getValue(Admin.class);
                            if (admin.getPassword().equals(edtPassword.getText().toString()))
                            {
                                admin.setAdminID(edtRegNo.getText().toString());
                                Intent dashboardIntent = new Intent(getBaseContext(), AdminHomepage.class);
                                Common.currentAdmin = admin;
                                startActivity(dashboardIntent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(AdminSignIn.this, "Wrong Password !!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(AdminSignIn.this, "User not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}