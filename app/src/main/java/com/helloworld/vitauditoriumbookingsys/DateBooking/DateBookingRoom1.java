package com.helloworld.vitauditoriumbookingsys.DateBooking;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.helloworld.vitauditoriumbookingsys.Common.Common;
import com.helloworld.vitauditoriumbookingsys.Dashboard;
import com.helloworld.vitauditoriumbookingsys.Model.RoomA;
import com.helloworld.vitauditoriumbookingsys.Model.RoomB;
import com.helloworld.vitauditoriumbookingsys.R;
import com.helloworld.vitauditoriumbookingsys.Room.Room1;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;
import java.util.Locale;

public class DateBookingRoom1  extends AppCompatActivity {

    EditText etDate, stTime, etTime;
    TextView usrRegno, usrName;
    EditText titleEvent, descEvent;
    ImageView starttym, endtym, selDate;
    Button btnbook;

    FirebaseDatabase database;
    DatabaseReference booking_roomA;
    String userRegNo;

    int sthour, stminute, ethour, etminute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_booking_room1);

        //Initializing Variables
        etDate = (MaterialEditText) findViewById(R.id.et_date);
        stTime = (MaterialEditText) findViewById(R.id.booking_event_startTime);
        etTime = (MaterialEditText) findViewById(R.id.booking_event_endTime);
        usrRegno = findViewById(R.id.booking_user_regno);
        usrName = findViewById(R.id.booking_user_name);
        titleEvent = (MaterialEditText) findViewById(R.id.booking_event_title);
        descEvent = (MaterialEditText) findViewById(R.id.booking_event_desc);
        starttym = findViewById(R.id.startTime);
        endtym = findViewById(R.id.endTime);
        selDate = findViewById(R.id.book_calendar);

        //Initializing Book Button
        btnbook = (Button) findViewById(R.id.btn_book_step);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        selDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DateBookingRoom1.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month+1;
                                String date = day+"-"+month+"-"+year;
                                etDate.setText(date);

                                booking_roomA.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        //Check if same date booking already exists or not
                                        if(snapshot.child(etDate.getText().toString()).exists())
                                        {
                                            alert("Date already Booked\nPlease choose another date");
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                        },year,month,day);

                datePickerDialog.show();


            }


        });


        userRegNo = Common.currentUser.getRegNo();
        //Init Firebase
        database = FirebaseDatabase.getInstance();
        booking_roomA = database.getReference("RoomA");

        usrRegno.setText(Common.currentUser.getRegNo());
        usrName.setText(Common.currentUser.getName());


        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog mDialog = new ProgressDialog(DateBookingRoom1.this);
                mDialog.setMessage("Do not press Back button...");
                mDialog.show();

                booking_roomA.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        mDialog.dismiss();
                        RoomA roomA = new RoomA(usrRegno.getText().toString(),usrName.getText().toString(),titleEvent.getText().toString(),
                                descEvent.getText().toString(),stTime.getText().toString(),etTime.getText().toString(), "pending");
                        booking_roomA.child(etDate.getText().toString()).setValue(roomA);

                        alertBookConfirm("REQUEST SENT");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }

    public void endTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int selectedMinute) {
                ethour = hourOfDay;
                etminute = selectedMinute;
                etTime.setText(String.format(Locale.getDefault(), "%02d:%02d", ethour, etminute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(DateBookingRoom1.this, onTimeSetListener, ethour, etminute, true);
        timePickerDialog.show();
    }

    public void startTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int selectedMinute) {
                sthour = hourOfDay;
                stminute = selectedMinute;
                stTime.setText(String.format(Locale.getDefault(), "%02d:%02d", sthour, stminute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(DateBookingRoom1.this, onTimeSetListener, sthour, stminute, true);
        timePickerDialog.show();
    }

    public void alert(String message){
        AlertDialog dlg = new AlertDialog.Builder(DateBookingRoom1.this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(getBaseContext(), Room1.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .create();
        dlg.show();
    }

    public void alertBookConfirm(String message){
        AlertDialog dlg = new AlertDialog.Builder(DateBookingRoom1.this)
                .setMessage(message)
                .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent dashboardIntent = new Intent(getBaseContext(),Dashboard.class);
                        startActivity(dashboardIntent);
                        dialog.dismiss();
                        finish();
                    }
                })
                .create();
        dlg.show();
    }
}
