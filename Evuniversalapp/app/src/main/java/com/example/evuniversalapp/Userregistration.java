

package com.example.evuniversalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Calendar;

public class Userregistration extends AppCompatActivity implements JsonResponse {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12;
    Button b1;
    String fname,lname,Dateofpurchase,place,servicedate,pincode,phone,email,username,password,longitude,vehicle,vehicledetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregistration);


        e1=(EditText) findViewById(R.id.fname);
        e2=(EditText) findViewById(R.id.lname);
        e3=(EditText) findViewById(R.id.place);
        e4=(EditText) findViewById(R.id.phone);

        e5=(EditText) findViewById(R.id.email);
        e6=(EditText) findViewById(R.id.uname);
        e7=(EditText) findViewById(R.id.password);
        e8=(EditText)findViewById(R.id.vehicle);

        e9=(EditText)findViewById(R.id.vehicledetails);
        e10=(EditText)findViewById(R.id.Dateofpurchase);
        e11=(EditText)findViewById(R.id.servicedate);


        b1=(Button) findViewById(R.id.userregistration);

        final Calendar calendar=Calendar.getInstance() ;
        final int year = calendar.get(calendar.YEAR);
        final int month =calendar.get(calendar.MONTH);
        final  int day =calendar.get(calendar.DAY_OF_MONTH);


        e9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog =new DatePickerDialog(Userregistration.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date =day+"/"+month+"/"+year;
                        e9.setText(date);

                    }
                },year, month,day);
                dialog.show();
            }
        });

        e10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog =new DatePickerDialog(Userregistration.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date =day+"/"+month+"/"+year;
                        e10.setText(date);

                    }
                },year, month,day);
                dialog.show();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fname=e1.getText().toString();
                lname=e2.getText().toString();
                place=e3.getText().toString();
                phone=e4.getText().toString();
                email=e5.getText().toString();
                username=e6.getText().toString();
                password=e7.getText().toString();

                vehicle=e8.getText().toString();
                vehicledetails=e9.getText().toString();
                Dateofpurchase=e10.getText().toString();

                servicedate=e11.getText().toString();



                if(fname.equalsIgnoreCase("")|| !fname.matches("[a-zA-Z ]+"))
                {
                    e1.setError("Enter your firstname");
                    e1.setFocusable(true);
                }
                else if(lname.equalsIgnoreCase("")|| !lname.matches("[a-zA-Z ]+"))
                {
                    e2.setError("Enter your lastname");
                    e2.setFocusable(true);
                }


                else if(vehicle.equalsIgnoreCase("")|| !vehicle.matches("[a-zA-Z ]+"))
                {
                    e8.setError("Enter your firstname");
                    e8.setFocusable(true);
                }
                else if(vehicledetails.equalsIgnoreCase("")|| !vehicledetails.matches("[a-zA-Z ]+"))
                {
                    e9.setError("Enter your lastname");
                    e9.setFocusable(true);
                }


                else if(place.equalsIgnoreCase("")|| !place.matches("[a-zA-Z ]+"))
                {
                    e3.setError("Enter your place");
                    e3.setFocusable(true);
                }


                else if(phone.equalsIgnoreCase("") || phone.length()!=10)
                {
                    e4.setError("Enter your phone");
                    e4.setFocusable(true);
                }
                else if(email.equalsIgnoreCase("") || !email.matches("[A-Za-z0-9._%+-]+@(?:[A-Za-z0-9-]+\\.)+(?:com|in|yahoo)"))
                {
                    e5.setError("Enter your email");
                    e5.setFocusable(true);
                }




                else if(username.equalsIgnoreCase(""))
                {
                    e6.setError("Enter your username");
                    e6.setFocusable(true);
                }
                else if(password.equalsIgnoreCase("") || password.length()!=8)
                {
                    e7.setError("Enter your password");
                    e7.setFocusable(true);
                }else {


                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Userregistration.this;
                    String q = "/Userregistration?fname=" + fname + "&lname=" + lname + "&place=" + place + "&phone=" + phone + "&email=" + email + "&username=" + username + "&password=" + password + "&lati=" + LocationService.lati + "&logi=" + LocationService.logi+"&vehicle="+vehicle+"&vehicledetails="+vehicledetails+"&Dateofpurchase="+Dateofpurchase+"&servicedate="+servicedate;
                    q = q.replace(" ", "%20");
                    JR.execute(q);

                }

            }
        });

    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                Toast.makeText(getApplicationContext(), "REGISTRATION SUCCESS", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), login.class));

            } else if (status.equalsIgnoreCase("duplicate")) {
                startActivity(new Intent(getApplicationContext(), Userregistration.class));
                Toast.makeText(getApplicationContext(), "Username and Password already Exist...", Toast.LENGTH_LONG).show();

            }else if (status.equalsIgnoreCase("already")) {
                Toast.makeText(getApplicationContext(), "Username Or Password ALREADY EXIST", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Userregistration.class));

            }else {
                startActivity(new Intent(getApplicationContext(), Userregistration.class));

                Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}