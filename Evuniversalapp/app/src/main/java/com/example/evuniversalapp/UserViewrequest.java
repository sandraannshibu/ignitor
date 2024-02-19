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

public class UserViewrequest extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {

    ListView l1;
    SharedPreferences sh;
    String[] firstname,first_name,servicceamount,date,mechanic_id,statu,value,latitude,longitude;
    public static String amt,mid,tlati,tlongi,stat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_viewrequest);
        l1=(ListView) findViewById(R.id.list);
        l1.setOnItemClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) UserViewrequest.this;
        String q = "/UserViewrequest?log_id=" +sh.getString("log_id", "");
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
                firstname = new String[ja1.length()];
                first_name = new String[ja1.length()];
                servicceamount = new String[ja1.length()];
                date = new String[ja1.length()];
                mechanic_id = new String[ja1.length()];
                statu = new String[ja1.length()];
                latitude = new String[ja1.length()];
                longitude = new String[ja1.length()];





                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    firstname[i] = ja1.getJSONObject(i).getString("firstname");

                    first_name[i] = ja1.getJSONObject(i).getString("first_name");
                    servicceamount[i] = ja1.getJSONObject(i).getString("servicceamount");
                    date[i] = ja1.getJSONObject(i).getString("date");
                    mechanic_id[i] = ja1.getJSONObject(i).getString("mrequest_id");
                    statu[i] = ja1.getJSONObject(i).getString("status");
                    latitude[i] = ja1.getJSONObject(i).getString("latitude");
                    longitude[i] = ja1.getJSONObject(i).getString("longitude");






                    value[i] = " user name:" + firstname[i] + "\nMechanic Name: " + first_name[i] + "\n servicceamount: " + servicceamount[i] + "\ndate: " + date[i]  + "\nstatus: " + statu[i]   ;

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

       mid=mechanic_id[i];
        tlati=latitude[i];
        tlongi=longitude[i];

        stat=statu[i];

        amt=servicceamount[i];



        if (stat.equalsIgnoreCase("Requested")) {

            final CharSequence[] items = {"Location", "Rating", "Cancel Request"};

            AlertDialog.Builder builder = new AlertDialog.Builder(UserViewrequest.this);
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {

                    if (items[item].equals("Location")) {


                        //                    startActivity(new Intent(getApplicationContext(),UserHotelRoomBooking.class));
                        String url = "http://www.google.com/maps?saddr=" + LocationService.lati + "" + "," + LocationService.logi + "" + "&&daddr=" + tlati + "," + tlongi;

                        Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(in);


                    } else if (items[item].equals("Rating")) {


                        startActivity(new Intent(getApplicationContext(), UserMechanicRating.class));
                    } else if (items[item].equals("Cancel Request")) {


                        JsonReq JR = new JsonReq();
                        JR.json_response = (JsonResponse) UserViewrequest.this;
                        String q = "/Cancelrequest?log_id=" + sh.getString("log_id", "") + "&mid=" + mid;
                        q = q.replace(" ", "%20");
                        JR.execute(q);
                        Toast.makeText(getApplicationContext(), "Canceled Request", Toast.LENGTH_LONG).show();
                    }
                }

            });
            builder.show();


        }
        if (stat.equalsIgnoreCase("Amountaddred")) {


            final CharSequence[] items = {"Location","Payment", "Rating"};

            AlertDialog.Builder builder = new AlertDialog.Builder(UserViewrequest.this);
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {

                    if (items[item].equals("Location")) {


                        //                    startActivity(new Intent(getApplicationContext(),UserHotelRoomBooking.class));
                        String url = "http://www.google.com/maps?saddr=" + LocationService.lati + "" + "," + LocationService.logi + "" + "&&daddr=" + tlati + "," + tlongi;

                        Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(in);


                    } else if (items[item].equals("Rating")) {


                        startActivity(new Intent(getApplicationContext(), UserMechanicRating.class));
                    } else if (items[item].equals("Payment")) {

                        startActivity(new Intent(getApplicationContext(), Mechanicpayment.class));

                    }
                }

            });
            builder.show();

        }
        }
    }
