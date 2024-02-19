package com.example.evuniversalapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserViewrechargerequests extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    SharedPreferences sh;
    String[] name,first_name,amount,date,statu,vehicle_id,value,request_id;
    public static String amt,rid,stat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_viewrechargerequests);
        l1=(ListView) findViewById(R.id.list);
        l1.setOnItemClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) UserViewrechargerequests.this;
        String q = "/UserViewrechargerequests?log_id=" +sh.getString("log_id", "");
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
                name = new String[ja1.length()];
                first_name = new String[ja1.length()];
                amount = new String[ja1.length()];
                date = new String[ja1.length()];
                request_id = new String[ja1.length()];
                statu = new String[ja1.length()];




                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    name[i] = ja1.getJSONObject(i).getString("name");

                    first_name[i] = ja1.getJSONObject(i).getString("first_name");
                    amount[i] = ja1.getJSONObject(i).getString("amount");
                    date[i] = ja1.getJSONObject(i).getString("date");
                    request_id[i] = ja1.getJSONObject(i).getString("request_id");
                    statu[i] = ja1.getJSONObject(i).getString("status");





                    value[i] = " Bunk name:" + name[i] + "\nUser Name: " + first_name[i] + "\n amount: " + amount[i] + "\ndate: " + date[i]  + "\nstatus: " + statu[i]  ;

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                l1.setAdapter(ar);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "no request", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        rid=request_id[i];
        stat = statu[i];

        amt= amount[i];


        if (stat.equalsIgnoreCase("Requested")) {
            final CharSequence[] items = {"Rating", "Cancel"};

            AlertDialog.Builder builder = new AlertDialog.Builder(UserViewrechargerequests.this);
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {


                    if (items[item].equals("Rating")) {


                        startActivity(new Intent(getApplicationContext(), UserRechargeRating.class));

                    }



                    else if (items[item].equals("Cancel")) {


                        JsonReq JR = new JsonReq();
                        JR.json_response = (JsonResponse) UserViewrechargerequests.this;
                        String q = "/Cancelrequest?log_id=" +sh.getString("log_id", "") +"&rid="+rid;
                        q = q.replace(" ", "%20");
                        JR.execute(q);
                        Toast.makeText(getApplicationContext(),"Canceled Request",Toast.LENGTH_LONG).show();
                    }
                }

            });
            builder.show();
        } else if (stat.equalsIgnoreCase("Amountaddred")) {

            final CharSequence[] items = {"Rating", "Make Payment"};

            AlertDialog.Builder builder = new AlertDialog.Builder(UserViewrechargerequests.this);
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {


               if (items[item].equals("Rating")) {


                        startActivity(new Intent(getApplicationContext(), UserRechargeRating.class));


                    }


               else if (items[item].equals("Make Payment")) {


                        startActivity(new Intent(getApplicationContext(), Bunkuserpayment.class));


                    }
                }

            });
            builder.show();
        } else if (stat.equalsIgnoreCase("Paid")) {

            final CharSequence[] items = {"Rating"};

            AlertDialog.Builder builder = new AlertDialog.Builder(UserViewrechargerequests.this);
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {

                     if (items[item].equals("Rating")) {


                        startActivity(new Intent(getApplicationContext(), UserRechargeRating.class));


                    }

                }

            });
            builder.show();

        }
    }
}