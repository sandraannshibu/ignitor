package com.example.evuniversalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class UserOrderNow extends AppCompatActivity implements JsonResponse {

    EditText e1,e2,e3;
    Button b1;

    String amount,hours,total;

    String [] cycle_id,location,value;
    SharedPreferences sh;
    public static String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order_now);

        e1=(EditText) findViewById(R.id.Amonut);
        e2=(EditText) findViewById(R.id.hours);
        e3=(EditText) findViewById(R.id.total);
        b1=(Button) findViewById(R.id.booknow);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1.setText(UserViewporoduct.rat);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hours=e2.getText().toString();
                total=e3.getText().toString();



//                Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!"+sid, Toast.LENGTH_LONG).show();
                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) UserOrderNow.this;
                String q = "/BookCycle?login_id=" + sh.getString("log_id", "") +"&quantity="  + hours +"&total=" +total +"&sid="+UserViewporoduct.sid +"&pid=" + UserViewporoduct.pid;
                q = q.replace(" ", "%20");
                JR.execute(q);


            }
        });


        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(e2.getText().toString().equalsIgnoreCase(""))
                {

                }
                else if(e2.getText().toString().equalsIgnoreCase("."))
                {

                }
                else
                {
                    Integer s=Integer.parseInt(e1.getText().toString())*Integer.parseInt(e2.getText().toString());
                    e3.setText(s+"");
                }

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

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

        }
    }
}