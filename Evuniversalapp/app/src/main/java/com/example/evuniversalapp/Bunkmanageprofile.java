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

public class Bunkmanageprofile extends AppCompatActivity implements JsonResponse {
    EditText e1,e2,e3,e4,e5;
    Button b1;
    String name,lname,place,phone,email,statu;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunkmanageprofile);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText) findViewById(R.id.fname);

        e3=(EditText) findViewById(R.id.place);


        e4=(EditText)findViewById(R.id.status);

        b1=(Button) findViewById(R.id.update);
        JsonReq JR = new JsonReq();

        JR.json_response = (JsonResponse) Bunkmanageprofile.this;
        String q = "/ViewBunkmanageprofile?lid="+sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=e1.getText().toString();

                place=e3.getText().toString();


                statu=e4.getText().toString();

                if(name.equalsIgnoreCase(""))
                {
                    e1.setError("Enter your  name");
                    e1.setFocusable(true);
                }
                else if(place.equalsIgnoreCase(""))
                {
                    e3.setError("Enter your  place");
                    e3.setFocusable(true);
                }
                else if(statu.equalsIgnoreCase(""))
                {
                    e4.setError("Enter your  statu");
                    e4.setFocusable(true);
                }
                else {


                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Bunkmanageprofile.this;
                    String q = "/Bunkmanageprofile?login_id=" + sh.getString("log_id", "") + "&name=" + name + "&place=" + place + "&status=" + statu;
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

                    e3.setText(ja1.getJSONObject(0).getString("place"));

                    e4.setText(ja1.getJSONObject(0).getString("status"));




                    SharedPreferences.Editor e = sh.edit();
                    //e.putString("log_id", logid);
                    e.commit();
                }
            }
            else if(method.equalsIgnoreCase("Bunkmanageprofile"))
            {
                try {
                    String status=jo.getString("status");
                    Log.d("pearl",status);


                    if(status.equalsIgnoreCase("success")){

                        Toast.makeText(getApplicationContext(), "UPDATED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),Bunkmanageprofile.class));

                    }
                    else
                    {
                        startActivity(new Intent(getApplicationContext(),Bunkmanageprofile.class));
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