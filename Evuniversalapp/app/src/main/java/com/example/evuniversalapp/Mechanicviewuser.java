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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Mechanicviewuser extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {

    EditText e1,e2,e3,e4,e5;
    ListView l1;
    Button b1;
    String [] name,lname,place,phone,email,longitude,latitude,value,vehicle,details;
    SharedPreferences sh;
    public static String  tlongi,tlati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanicviewuser);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView) findViewById(R.id.list);
        l1.setOnItemClickListener(this);

        JsonReq JR = new JsonReq();

        JR.json_response = (JsonResponse) Mechanicviewuser.this;
        String q = "/Mechanicviewuser?lid="+sh.getString("log_id", "") +"&mid=" +Mechanicviewrequest.mid;
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
                lname = new String[ja1.length()];
                place = new String[ja1.length()];
                phone = new String[ja1.length()];
                email = new String[ja1.length()];
                longitude = new String[ja1.length()];
                latitude = new String[ja1.length()];
                value = new String[ja1.length()];
                vehicle = new String[ja1.length()];
                details = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    name[i] = ja1.getJSONObject(i).getString("first_name");

                    lname[i] = ja1.getJSONObject(i).getString("last_name");
                    place[i] = ja1.getJSONObject(i).getString("place");
                    phone[i] = ja1.getJSONObject(i).getString("phone");
                    email[i] = ja1.getJSONObject(i).getString("email");
                    longitude[i] = ja1.getJSONObject(i).getString("longitude");
                    latitude[i] = ja1.getJSONObject(i).getString("latitude");
                    vehicle[i] = ja1.getJSONObject(i).getString("vehicle");
                    details[i] = ja1.getJSONObject(i).getString("detials");



                    value[i] = "First name:" + name[i] + "\nlast name: " + lname[i] + "\n place: " + place[i] + "\nphone: " + phone[i]  + "\nemail: " + email[i] + "\nvehicle: " + vehicle[i]  + "\ndetails: " + details[i]  ;

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                l1.setAdapter(ar);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "no users", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        tlati=latitude[i];
        tlongi=longitude[i];

        final CharSequence[] items = {"Location","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Mechanicviewuser.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Location")) {


                    //                    startActivity(new Intent(getApplicationContext(),UserHotelRoomBooking.class));
                    String url = "http://www.google.com/maps?saddr=" + LocationService.lati + "" + "," + LocationService.logi + "" + "&&daddr=" + tlati + "," + tlongi;

                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(in);




                } else if (items[item].equals("Cancel")) {


                    dialog.dismiss();
                }



            }

        });
        builder.show();
    }
}