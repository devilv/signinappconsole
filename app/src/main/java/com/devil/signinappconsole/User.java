package com.devil.signinappconsole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by devil on 11/27/2016.
 */

public class User extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        final EditText edt = (EditText) findViewById(R.id.editText2);
        final EditText edt1 = (EditText) findViewById(R.id.editText2);
        final TextView txt=(TextView)findViewById(R.id.textView4);
        Intent i=getIntent();
        String name=i.getStringExtra("name");
        String username=i.getStringExtra("name");
        int age=i.getIntExtra("age",-1);
        String message=name + "welcome to the user area";
        txt.setText(message);
        edt.setText(username);
        edt1.setText(age+ "");
    }
}