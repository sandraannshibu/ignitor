package com.example.evuniversalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class Forget_password extends AppCompatActivity implements JsonResponse {
    EditText e1, e2, e3;
    Button b1;
    String uname, email, phone;
    public static String logid,emails,unames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        e2 = (EditText) findViewById(R.id.etuname);
        e1 = (EditText) findViewById(R.id.etemail);
//        e3 = (EditText) findViewById(R.id.etphone);
        b1 = (Button) findViewById(R.id.btconfirm);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = e1.getText().toString();
                uname = e2.getText().toString();
                if (email.equalsIgnoreCase("")) {

                    e1.setError("enter valid email");
                    e1.setFocusable(true);
                }
                else if (uname.equalsIgnoreCase("")) {

                    e2.setError("enter valid username");
                    e2.setFocusable(true);
                }
                else {
                    JsonReq jr = new JsonReq();
                    jr.json_response = (JsonResponse) Forget_password.this;
                    String q = "/Forgot_password?&email=" + email+"&uname=" + uname ;
                    jr.execute(q);


                }
            }
        });

    }

    @Override
    public void response(JSONObject jo) {
        // TODO Auto-generated method stub
        try {
            String status = jo.getString("status");
            if (status.equalsIgnoreCase("success")) {
                JSONArray ja = (JSONArray) jo.getJSONArray("data");
//                logid = ja.getJSONObject(0).getString("login_id");
                emails = ja.getJSONObject(0).getString("email");
                unames = ja.getJSONObject(0).getString("username");
//

//                Toast.makeText(getApplicationContext(), "success ", Toast.LENGTH_LONG).show();
                Intent next = new Intent(getApplicationContext(), Set_new_password.class);
                startActivity(next);
            } else {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "exp : " + e, Toast.LENGTH_LONG).show();
        }
    }

    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),login.class);
        startActivity(b);
    }

}