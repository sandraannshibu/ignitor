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

public class UserViewporoduct extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    SharedPreferences sh;
    String[] product_name,firstname,images,rate,stock,sparepart_id,value,product_id;
    public static String pid,rat,sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_viewporoduct);
        l1=(ListView) findViewById(R.id.list);
        l1.setOnItemClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) UserViewporoduct.this;
        String q = "/UserViewporoduct?log_id=" +sh.getString("log_id", "")+"&spid="+UserSearchspareparts.sid;
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
                images = new String[ja1.length()];
                rate = new String[ja1.length()];
                stock = new String[ja1.length()];
                sparepart_id = new String[ja1.length()];
                product_id = new String[ja1.length()];


                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    product_name[i] = ja1.getJSONObject(i).getString("product_name");

                    firstname[i] = ja1.getJSONObject(i).getString("firstname");
                    images[i] = ja1.getJSONObject(i).getString("img");
                    rate[i] = ja1.getJSONObject(i).getString("rate");
                    stock[i] = ja1.getJSONObject(i).getString("stock");
                    sparepart_id[i] = ja1.getJSONObject(i).getString("sparepart_id");
                    product_id[i] = ja1.getJSONObject(i).getString("product_id");



                    value[i] = "product_name:" + product_name[i] + "\nCompany name: " + firstname[i] + "\n rate: " + rate[i] + "\nstock: " + stock[i]  ;

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                l1.setAdapter(ar);

                Custimage a = new Custimage(this, product_name, firstname,images,rate);
                l1.setAdapter(a);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"no products", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        rat=rate[i];
        pid=product_id[i];
        sid=sparepart_id[i];
        final CharSequence[] items = {"Order Now","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(UserViewporoduct.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Order Now")) {


                    startActivity(new Intent(getApplicationContext(), UserOrderNow.class));



                }



                else if (items[item].equals("Cancel")) {


                    dialog.dismiss();
                }
            }

        });
        builder.show();
    }
}