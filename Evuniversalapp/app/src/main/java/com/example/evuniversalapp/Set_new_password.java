package com.example.evuniversalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class Set_new_password extends AppCompatActivity implements JsonResponse {
    EditText e1,e2;
    Button b1;
    String password,c_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
        e1 = (EditText) findViewById(R.id.etpassword);
        e2 = (EditText) findViewById(R.id.etconfirm);
        b1 = (Button) findViewById(R.id.btset);
b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        password = e1.getText().toString();
        c_password = e2.getText().toString();


        if (password.length() < 6) {
            e1.setError("password must greater than 6 charecter");
            e1.setFocusable(true);
        }
        if (c_password.length() < 6) {
            e2.setError("New Password And Confirm Password Not Match");
            e2.setFocusable(true);
        }

//        if (password.equalsIgnoreCase("")) {
//
//            e1.setError("enter password");
//            e1.setFocusable(true);
//
//        }
//        else if (c_password.equalsIgnoreCase("")) {
//
//            e2.setError("enter Confirm Password");
//            e2.setFocusable(true);
//
//        }
        else {
            JsonReq jr = new JsonReq();
            jr.json_response = (JsonResponse) Set_new_password.this;
            String q = "/Set_new_password?emails=" + Forget_password.emails+"&unames=" + Forget_password.unames+"&password="+password+"&c_password="+c_password;
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

                Toast.makeText(getApplicationContext(), "Password Set Successfully...!! ", Toast.LENGTH_LONG).show();
                Intent next = new Intent(getApplicationContext(), login.class);
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