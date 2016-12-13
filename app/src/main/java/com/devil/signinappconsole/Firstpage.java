package com.devil.signinappconsole;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Firstpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);
        final Button btn1=(Button)findViewById(R.id.button2);
        final EditText edt1=(EditText)findViewById(R.id.editText5);
        final EditText edt2=(EditText)findViewById(R.id.editText10);
        final TextView txt=(TextView) findViewById(R.id.textView2);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Firstpage.this,Register.class);
                Firstpage.this.startActivity(i);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username=edt1.getText().toString();
                final String password=edt2.getText().toString();
                Response.Listener<String> responselistner=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            if(success){
                                String name=jsonResponse.getString("name");
                                int age=jsonResponse.getInt("age");
                                Intent i=new Intent(Firstpage.this,User.class);
                                i.putExtra("name",name);
                                i.putExtra("age",age);
                                i.putExtra("username",username);
                                Firstpage.this.startActivity(i);
                                }
                            else{
                                AlertDialog.Builder builder=new AlertDialog.Builder(Firstpage.this);
                                builder.setMessage("Login failed")
                                        .setNegativeButton("retry",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                LoginRequest LoginRequest= new LoginRequest(username,password,responselistner);
                RequestQueue queue= Volley.newRequestQueue(Firstpage.this);
                queue.add(LoginRequest);

            }
        });

    }
}
