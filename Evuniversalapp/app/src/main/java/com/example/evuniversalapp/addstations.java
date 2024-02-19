package com.example.evuniversalapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class addstations extends AppCompatActivity implements JsonResponse {
    EditText e1,e2,e3,e4,e5;
    Button b1;
    String noofvehicle,noofstations,latitude,longitude,email;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstations);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e3=(EditText)findViewById(R.id.latitude) ;
        e4=(EditText)findViewById(R.id.longitude) ;
        e1=(EditText) findViewById(R.id.noofvehicle);
        e5=(EditText) findViewById(R.id.output);

        e2=(EditText) findViewById(R.id.noofstations);

        b1=(Button) findViewById(R.id.dbregistration);
        JsonReq JR = new JsonReq();

//        JR.json_response = (JsonResponse) addstations.this;
//        String q = "/ViewBunkmanageprofile?lid="+sh.getString("log_id", "");
//        q = q.replace(" ", "%20");
//        JR.execute(q);


        e3.setText(LocationService.lati);
        e4.setText(LocationService.logi);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                latitude=e3.getText().toString();
                longitude=e4.getText().toString();


                noofvehicle=e1.getText().toString();

                noofstations=e2.getText().toString();

                if(noofvehicle.equalsIgnoreCase(""))
                {
                    e1.setError("Enter your no of vehicle");
                    e1.setFocusable(true);
                }
                else if(noofstations.equalsIgnoreCase(""))
                {
                    e2.setError("Enter your no of stations");
                    e2.setFocusable(true);
                }
                else {


                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) addstations.this;
                    String q = "/addstations?login_id=" + sh.getString("log_id", "") + "&latitude=" + latitude + "&longitude=" + longitude + "&noofvehicle=" + noofvehicle + "&noofstations=" + noofstations;
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

            if(method.equalsIgnoreCase("ViewBunkmanageprofile")) {
                String status = jo.getString("status");
                Log.d("pearl", status);

                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    e1.setText(ja1.getJSONObject(0).getString("name"));




                    SharedPreferences.Editor e = sh.edit();
                    //e.putString("log_id", logid);
                    e.commit();
                }
            }
            else if(method.equalsIgnoreCase("addstations"))
            {
                try {
                    String status=jo.getString("status");
                    Log.d("pearl",status);


                    if(status.equalsIgnoreCase("success")){

                        e5.setText(jo.getString("data"));
                        Toast.makeText(getApplicationContext(), "UPDATED SUCCESSFULLY", Toast.LENGTH_LONG).show();
//                        startActivity(new Intent(getApplicationContext(),Bunkmanageprofile.class));

                    }
                    else
                    {
//                        startActivity(new Intent(getApplicationContext(),Bunkmanageprofile.class));
//                        Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
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