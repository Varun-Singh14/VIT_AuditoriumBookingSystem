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
import com.helloworld.vitauditoriumbookingsys.Model.User;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.jar.Attributes;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtRegNo, edtName, edtPhone, edtPassword;
    Button btnSignUp;
    boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtRegNo = (MaterialEditText) findViewById(R.id.Reg_no);
        edtName = (MaterialEditText) findViewById(R.id.user_name);
        edtPhone = (MaterialEditText) findViewById(R.id.user_phone);
        edtPassword = (MaterialEditText) findViewById(R.id.user_pass);

        btnSignUp = (Button) findViewById(R.id.btn_SignUp);

        //Init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                checkField(edtRegNo);
//                checkField(edtName);
//                checkField(edtPhone);
//                checkField(edtPassword);

                if(checkField(edtRegNo) && checkField(edtName) && checkField(edtPhone) && checkField(edtPassword)){
                    final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                    mDialog.setMessage("Please wait...");
                    mDialog.show();

                    table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //Check if user already exists
                            if (snapshot.child(edtRegNo.getText().toString()).exists())
                            {
                                mDialog.dismiss();
                                Toast.makeText(SignUp.this, "User already registered", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {
                                mDialog.dismiss();
                                User user = new User(edtName.getText().toString(),edtPhone.getText().toString(),edtPassword.getText().toString());
                                table_user.child(edtRegNo.getText().toString()).setValue(user);
                                Toast.makeText(SignUp.this, "Sign up successful !", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                } else {
                    Toast.makeText(SignUp.this, "Some Field is Empty", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    public boolean checkField(MaterialEditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("This Field is mandatory");
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }
}