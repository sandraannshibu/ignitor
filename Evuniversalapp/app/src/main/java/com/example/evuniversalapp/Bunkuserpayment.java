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

public class Bunkuserpayment extends AppCompatActivity implements JsonResponse {
    EditText e1,e2,e3,e4;
    Button b1;
    String card,cvv,amount,name;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunkuserpayment);
        e1=(EditText)findViewById(R.id.card);
        e2=(EditText) findViewById(R.id.cvv);
        e3=(EditText)findViewById(R.id.amount) ;
        e4=(EditText)findViewById(R.id.name) ;
        b1=(Button)findViewById(R.id.payment);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e3.setText(UserViewrechargerequests.amt);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card=e1.getText().toString();
                cvv=e2.getText().toString();
                name=e4.getText().toString();

                if(card.equalsIgnoreCase("")|| card.length()!=16)
                {
                    e1.setError("Enter your 16 digits card number");
                    e1.setFocusable(true);
                }

                else if(cvv.equalsIgnoreCase("")|| cvv.length()!=3)
                {
                    e2.setError("Enter your 3 digits C V V ");
                    e2.setFocusable(true);
                }

                else {




                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Bunkuserpayment.this;
                    String q = "/Bunkuserpayment?login_id=" + sh.getString("log_id", "")+ "&amount=" + UserViewrechargerequests.amt +"&rid=" +UserViewrechargerequests.rid +"&name="+ name ;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
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