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

public class Bunkviewpayment extends AppCompatActivity implements JsonResponse {

    ListView l1;
    SharedPreferences sh;
    String[] requestedfor,first_name,amount,date,statu,value,request_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunkviewpayment);
        l1=(ListView) findViewById(R.id.list);





        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Bunkviewpayment.this;
        String q = "/Bunkviewpayment?log_id=" +sh.getString("log_id", "") + "&request_id="+Bunkviewrequest.bid;
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
                requestedfor = new String[ja1.length()];
                first_name = new String[ja1.length()];
                amount = new String[ja1.length()];
                date = new String[ja1.length()];
                request_id = new String[ja1.length()];
                statu = new String[ja1.length()];
                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    requestedfor[i] = ja1.getJSONObject(i).getString("requestedfor");

                    first_name[i] = ja1.getJSONObject(i).getString("first_name");
                    amount[i] = ja1.getJSONObject(i).getString("pamo");
                    date[i] = ja1.getJSONObject(i).getString("pdate");
                    request_id[i] = ja1.getJSONObject(i).getString("request_id");
                    statu[i] = ja1.getJSONObject(i).getString("status");





                    value[i] = "requestedfor:" + requestedfor[i] + "\nUser Name: " + first_name[i] + "\n amount: " + amount[i] + "\ndate: " + date[i]  + "\nstatus: " + statu[i]  ;

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                l1.setAdapter(ar);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"no payment details", Toast.LENGTH_LONG).show();

        }
    }
}