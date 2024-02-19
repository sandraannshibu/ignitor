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

public class Comments extends AppCompatActivity implements JsonResponse {
    EditText e1;
    Button b1;
    String amount,noofstations,latitude,longitude,email;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1=(EditText) findViewById(R.id.amount);


        b1=(Button) findViewById(R.id.add);
//        JsonReq JR = new JsonReq();

//        JR.json_response = (JsonResponse) addstations.this;
//        String q = "/ViewBunkmanageprofile?lid="+sh.getString("log_id", "");
//        q = q.replace(" ", "%20");
//        JR.execute(q);




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                amount=e1.getText().toString();


                if(amount.equalsIgnoreCase(""))
                {
                    e1.setError("Enter your  comments");
                    e1.setFocusable(true);
                }

                else {


                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Comments.this;
                    String q = "/Comments?login_id=" + sh.getString("log_id", "") + "&comment=" + amount + "&bid=" + Viewblog.bid;
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
            else if(method.equalsIgnoreCase("Comments"))
            {
                try {
                    String status=jo.getString("status");
                    Log.d("pearl",status);


                    if(status.equalsIgnoreCase("success")){


                        Toast.makeText(getApplicationContext(), " SUCCESSFULLY", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),Viewblog.class));

                    }
                    else
                    {
                        startActivity(new Intent(getApplicationContext(),Comments.class));
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