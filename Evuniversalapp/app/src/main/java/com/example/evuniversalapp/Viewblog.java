package com.example.evuniversalapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Viewblog extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    SharedPreferences sh;
    String[] title,description,first_name,image,comments,value,blog_id;
    public static String bid, amt, oid, tlati, tlongi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewblog);

        l1=(ListView) findViewById(R.id.list);
        l1.setOnItemClickListener(this);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewblog.this;
        String q = "/Userblog?log_id=" + sh.getString("log_id", "");
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
                title = new String[ja1.length()];
                first_name = new String[ja1.length()];
                description = new String[ja1.length()];
                image = new String[ja1.length()];
                comments = new String[ja1.length()];
                blog_id = new String[ja1.length()];


                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    title[i] = ja1.getJSONObject(i).getString("title");

                    first_name[i] = ja1.getJSONObject(i).getString("first_name");
                    description[i] = ja1.getJSONObject(i).getString("details");
                    image[i] = ja1.getJSONObject(i).getString("image");
                    comments[i] = ja1.getJSONObject(i).getString("comments");



                    blog_id[i] = ja1.getJSONObject(i).getString("blog_id");


                    value[i] = " title:" + title[i] + "\nfirst name: " + first_name[i] + "\n description: " + description[i] + "\ncomments: " + comments[i]   ;

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                l1.setAdapter(ar);



                Custimage5 a = new Custimage5(this, title, first_name, description, comments,image);
                l1.setAdapter(a);


            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "no blogs", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        bid=blog_id[i];


        final CharSequence[] items = {"Comments","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Viewblog.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Comments")) {


                    startActivity(new Intent(getApplicationContext(), Comments.class));



                }



                else if (items[item].equals("Cancel")) {


                    dialog.dismiss();
                }
            }

        });
        builder.show();
    }
    }
