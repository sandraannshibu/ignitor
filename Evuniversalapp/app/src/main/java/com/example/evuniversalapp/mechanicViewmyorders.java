package com.example.evuniversalapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class mechanicViewmyorders extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    SharedPreferences sh;
    String[] product_name, firstname, image, quantity, statu, total, value, user_id, sparepart_id, date, order_id, longitude, latitude;

public static String oid,amt,stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanicviewmyorders);


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView) findViewById(R.id.list);
        l1.setOnItemClickListener(this);
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) mechanicViewmyorders.this;
        String q = "/mechanicViewordereditems?log_id=" + sh.getString("log_id", "");
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


                date = new String[ja1.length()];
                statu = new String[ja1.length()];
                value = new String[ja1.length()];
                order_id = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    product_name[i] = ja1.getJSONObject(i).getString("product_name");

                    firstname[i] = ja1.getJSONObject(i).getString("firstname");
                    image[i] = ja1.getJSONObject(i).getString("img");
                    quantity[i] = ja1.getJSONObject(i).getString("quantity");

                    total[i] = ja1.getJSONObject(i).getString("total");



                    date[i] = ja1.getJSONObject(i).getString("date");

                    statu[i] = ja1.getJSONObject(i).getString("status");
                    order_id[i] = ja1.getJSONObject(i).getString("order_id");






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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        oid = order_id[i];
        amt = total[i];
        stat = statu[i];


        if (stat.equalsIgnoreCase("pending")) {
            final CharSequence[] items = {"Make Payment"};

            AlertDialog.Builder builder = new AlertDialog.Builder(mechanicViewmyorders.this);
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {

                    if (items[item].equals("Make Payment")) {


                        startActivity(new Intent(getApplicationContext(), mechanicMakepayment.class));


                    }
                }

            });
            builder.show();
        }

    }
}