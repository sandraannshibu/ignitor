package com.example.evuniversalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Viewratings extends AppCompatActivity implements JsonResponse {
    ListView l1;
    SharedPreferences sh;
    String[] rating,date,first_name,value,request_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewratings);

        l1=(ListView) findViewById(R.id.list);





        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewratings.this;
        String q = "/Viewratings?log_id=" +sh.getString("log_id", "") + "&vid="+UserViewvehicle.vid;
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                rating = new String[ja1.length()];
                first_name = new String[ja1.length()];

                date = new String[ja1.length()];
                request_id = new String[ja1.length()];

                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    rating[i] = ja1.getJSONObject(i).getString("rating");

                    first_name[i] = ja1.getJSONObject(i).getString("first_name");

                    date[i] = ja1.getJSONObject(i).getString("date");
                    request_id[i] = ja1.getJSONObject(i).getString("requested_id");





                    value[i] = "first_name:" + first_name[i] + "\nrating: " + rating[i] + "\n date: " + date[i]  ;

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                l1.setAdapter(ar);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "no ratings", Toast.LENGTH_LONG).show();

        }
    }
}