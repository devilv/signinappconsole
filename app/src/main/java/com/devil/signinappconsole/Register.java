package com.devil.signinappconsole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by devil on 11/27/2016.
 */

public class Register extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        final EditText edt1=(EditText)findViewById(R.id.editText6);
        final EditText edt2=(EditText)findViewById(R.id.editText7);
        final EditText edt3=(EditText)findViewById(R.id.editText8);
        final EditText edt4=(EditText)findViewById(R.id.editText9);
        final Button btn =(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=edt1.getText().toString();
                final String username=edt2.getText().toString();
                final int age=Integer.parseInt(edt4.getText().toString());
                final String password=edt3.getText().toString();
                Response.Listener<String> responselistner=new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            if(success){
                                Intent i=new Intent(Register.this,Firstpage.class);
                                Register.this.startActivity(i);
                            }
                            else{
                                AlertDialog.Builder builder=new AlertDialog.Builder(Register.this);
                                builder.setMessage("Registeration failed")
                                        .setNegativeButton("retry",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                RegisterRequest registerRequest= new RegisterRequest(name, username,age , password,responselistner);
                RequestQueue queue= Volley.newRequestQueue(Register.this);
                queue.add(registerRequest);
            }
        });

    }
}
