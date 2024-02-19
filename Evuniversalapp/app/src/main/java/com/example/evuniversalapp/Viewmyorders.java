package com.example.evuniversalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Viewmyorders extends AppCompatActivity implements JsonResponse {
    ListView l1;
    SharedPreferences sh;
    String[] product_name, firstname, image, quantity, statu, total, value, user_id, sparepart_id, date, order_id, longitude, latitude;
    public static String sta, amt, oid, tlati, tlongi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmyorders);
        l1=(ListView) findViewById(R.id.list);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewmyorders.this;
        String q = "/UserViewordereditemsmy?log_id=" + sh.getString("log_id", "");
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
                product_name = new String[ja1.length()];
                firstname = new String[ja1.length()];
                image = new String[ja1.length()];
                quantity = new String[ja1.length()];
                total = new String[ja1.length()];
                user_id = new String[ja1.length()];
                sparepart_id = new String[ja1.length()];
                date = new String[ja1.length()];
                statu = new String[ja1.length()];
                value = new String[ja1.length()];
                order_id = new String[ja1.length()];
                longitude = new String[ja1.length()];
                latitude = new String[ja1.length()];


                for (int i = 0; i < ja1.length(); i++) {
                    product_name[i] = ja1.getJSONObject(i).getString("product_name");
                    firstname[i] = ja1.getJSONObject(i).getString("firstname");
                    image[i] = ja1.getJSONObject(i).getString("img");
                    quantity[i] = ja1.getJSONObject(i).getString("quantity");
                    total[i] = ja1.getJSONObject(i).getString("total");
                    user_id[i] = ja1.getJSONObject(i).getString("user_id");
                    sparepart_id[i] = ja1.getJSONObject(i).getString("sparepart_id");
                    date[i] = ja1.getJSONObject(i).getString("date");
                    statu[i] = ja1.getJSONObject(i).getString("status");
                    order_id[i] = ja1.getJSONObject(i).getString("order_id");
                    longitude[i] = ja1.getJSONObject(i).getString("longitude");
                    latitude[i] = ja1.getJSONObject(i).getString("latitude");
                    value[i] = " Product Name:" + product_name[i] + "\nSpareparts Name: " + firstname[i] + "\n quantity: " + quantity[i] + "\nAmount: " + total[i] + "\nstatus: " + statu[i] + "\ndate: " + date[i];

                }

                Custimage1 a = new Custimage1(this, product_name, firstname, image, quantity, statu, total, date);
                l1.setAdapter(a);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "no orders", Toast.LENGTH_LONG).show();

        }

    }
}