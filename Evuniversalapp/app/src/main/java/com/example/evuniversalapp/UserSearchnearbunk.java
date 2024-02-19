package com.example.evuniversalapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserSearchnearbunk extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {

    EditText e1;
    ListView l1;
    String[] name,place,value,bunk_id,latitude,longitude,countrequest,statu;
    String search,status,method;
    public static String bid,tlati,tlongi,count;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_searchnearbunk);

        e1=(EditText)findViewById(R.id.search);
        l1=(ListView) findViewById(R.id.list);
        l1.setOnItemClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) UserSearchnearbunk.this;
        String q = "/viewbunk";
        q = q.replace(" ", "%20");
        JR.execute(q);

        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                search=e1.getText().toString();


                if (search.equalsIgnoreCase("recharge station")) {
                    String uri = "http://www.google.com/maps/search/recharge station/@" + LocationService.lati + "," + LocationService.logi;
                    startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));


                }else {

                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) UserSearchnearbunk.this;
                    String q = "/searchbunk?&search=" + search;
                    q = q.replace(" ", "%20");
                    JR.execute(q);

                }

            }
        });

    }

    @Override
    public void response(JSONObject jo) {
        try {


            method = jo.getString("method");



            if (method.equalsIgnoreCase("viewbunk")) {


                status = jo.getString("status");

                Log.d("pearlssssss", status);


                if (status.equalsIgnoreCase("success")) {
                    l1.setVisibility(View.VISIBLE);
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    name = new String[ja1.length()];
                    place = new String[ja1.length()];
                    bunk_id = new String[ja1.length()];


                    latitude = new String[ja1.length()];
                    longitude = new String[ja1.length()];

                    statu= new String[ja1.length()];
                    value = new String[ja1.length()];

                    for (int i = 0; i < ja1.length(); i++) {
                        name[i] = ja1.getJSONObject(i).getString("name");
                        place[i] = ja1.getJSONObject(i).getString("place");
                        bunk_id[i] = ja1.getJSONObject(i).getString("bunk_id");

                        latitude[i] = ja1.getJSONObject(i).getString("latitude");
                        longitude[i] = ja1.getJSONObject(i).getString("longitude");
                        statu[i] = ja1.getJSONObject(i).getString("status");


                        value[i] = "Bunk name:" + name[i] + "\nPlace:" + place[i] + "\nStatus:" + statu[i];

                    }
                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                    l1.setAdapter(ar);


                } else {
                    Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_LONG).show();
                    l1.setVisibility(View.GONE);
                }

            } else  if (method.equalsIgnoreCase("count")) {

                status = jo.getString("status");
                if (status.equalsIgnoreCase("success")) {

                    String count = jo.getString("countrequest");

                    final CharSequence[] items = { "ALL Requests ="+count, "Recharge request","Location","Cancel"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(UserSearchnearbunk.this);
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {

                            if (items[item].equals("Recharge request")) {


                                JsonReq JR = new JsonReq();
                                JR.json_response = (JsonResponse) UserSearchnearbunk.this;
                                String q = "/Rechargerequest?log_id=" +sh.getString("log_id", "") +"&bid="+bid;
                                q = q.replace(" ", "%20");
                                JR.execute(q);

                                Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_LONG).show();



                            }



                          else  if (items[item].equals("Location")) {


                                //                    startActivity(new Intent(getApplicationContext(),UserHotelRoomBooking.class));
                                String url = "http://www.google.com/maps?saddr=" + LocationService.lati + "" + "," + LocationService.logi + "" + "&&daddr=" + tlati + "," + tlongi;

                                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(in);




                            }



                            else if (items[item].equals("Cancel")) {


                                dialog.dismiss();
                            }
                        }

                    });
                    builder.show();



                }
            }


        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        bid=bunk_id[i];

        tlati=latitude[i];
        tlongi=longitude[i];


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) UserSearchnearbunk.this;
        String q = "/count?bkid="+bid;
        q = q.replace(" ", "%20");
        JR.execute(q);



    }
}