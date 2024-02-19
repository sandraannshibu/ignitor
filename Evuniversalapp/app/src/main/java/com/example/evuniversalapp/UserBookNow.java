package com.example.evuniversalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class UserBookNow extends AppCompatActivity implements JsonResponse {
    EditText e1;
    Button b1;
    String amount;
     SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_book_now);
        e1=(EditText) findViewById(R.id.amount);
        b1=(Button) findViewById(R.id.booknow);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1.setText(UserViewvehicle.amt);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) UserBookNow.this;
                String q = "/UserBookNow?login_id=" + sh.getString("log_id", "")+ "&amount=" + UserViewvehicle.amt +"&vid="+UserViewvehicle.vid ;
                q = q.replace(" ", "%20");
                JR.execute(q);


            }
        });

    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                Toast.makeText(getApplicationContext(), " SUCCESS", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Userhome.class));

            }

            else if (status.equalsIgnoreCase("already")) {
                Toast.makeText(getApplicationContext(), "NO AMOUNT IN WALLET", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Userhome.class));

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}