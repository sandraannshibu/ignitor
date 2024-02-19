package com.example.evuniversalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Userprofile extends AppCompatActivity implements JsonResponse{
    EditText e1,e2,e3,e4,e5,e6,e7;
    Button b1;
    String name,lname,place,phone,email,vehicle,vehicledetails;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText) findViewById(R.id.fname);
        e2=(EditText) findViewById(R.id.lname);
        e3=(EditText) findViewById(R.id.place);
        e4=(EditText) findViewById(R.id.phone);
        e5=(EditText)findViewById(R.id.email) ;
        e6=(EditText) findViewById(R.id.vehicle);
        e7=(EditText)findViewById(R.id.vehicledetails) ;


        b1=(Button) findViewById(R.id.update);
        JsonReq JR = new JsonReq();

        JR.json_response = (JsonResponse) Userprofile.this;
        String q = "/ViewUserprofile?lid="+sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=e1.getText().toString();
                lname=e2.getText().toString();
                place=e3.getText().toString();
                phone=e4.getText().toString();
                email=e5.getText().toString();
                vehicle=e6.getText().toString();
                vehicledetails=e7.getText().toString();

                if(name.equalsIgnoreCase(""))
                {
                    e1.setError("Enter your  first name");
                    e1.setFocusable(true);
                }
                else if(lname.equalsIgnoreCase(""))
                {
                    e2.setError("Enter your  last name");
                    e2.setFocusable(true);
                }
                else if(place.equalsIgnoreCase(""))
                {
                    e3.setError("Enter your  place");
                    e3.setFocusable(true);
                }

                else if(phone.equalsIgnoreCase(""))
                {
                    e4.setError("Enter your  phone");
                    e4.setFocusable(true);
                }
                else if(email.equalsIgnoreCase(""))
                {
                    e5.setError("Enter your  email");
                    e5.setFocusable(true);
                }

                else if(vehicle.equalsIgnoreCase(""))
                {
                    e6.setError("Enter your  vehicle");
                    e6.setFocusable(true);
                }
                else if(vehicledetails.equalsIgnoreCase(""))
                {
                    e7.setError("Enter your  vehicledetails");
                    e7.setFocusable(true);
                }
                else {


                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Userprofile.this;
                    String q = "/Userprofile?login_id=" + sh.getString("log_id", "") + "&name=" + name + "&place=" + place + "&Phone=" + phone + "&email=" + email + "&lname=" + lname+"&vehicle="+vehicle+"&vehicledetails="+vehicledetails;
                    q = q.replace(" ", "%20");
                    JR.execute(q);

                }
            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {
            String method=jo.getString("method");

            if(method.equalsIgnoreCase("ViewUserprofile")) {
                String status = jo.getString("status");
                Log.d("pearl", status);

                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    e1.setText(ja1.getJSONObject(0).getString("first_name"));
                    e2.setText(ja1.getJSONObject(0).getString("last_name"));
                    e3.setText(ja1.getJSONObject(0).getString("place"));
                    e4.setText(ja1.getJSONObject(0).getString("phone"));
                    e5.setText(ja1.getJSONObject(0).getString("email"));
                    e6.setText(ja1.getJSONObject(0).getString("vehicle"));
                    e7.setText(ja1.getJSONObject(0).getString("detials"));





                    SharedPreferences.Editor e = sh.edit();
                    //e.putString("log_id", logid);
                    e.commit();
                }
            }
            else if(method.equalsIgnoreCase("Userprofile"))
            {
                try {
                    String status=jo.getString("status");
                    Log.d("pearl",status);


                    if(status.equalsIgnoreCase("success")){

                        Toast.makeText(getApplicationContext(), "UPDATED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),Userprofile.class));

                    }
                    else
                    {
                        startActivity(new Intent(getApplicationContext(),Userprofile.class));
                        Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

            }
//            else {
//                Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(getApplicationContext(), Login.class));
//            }
        } catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}