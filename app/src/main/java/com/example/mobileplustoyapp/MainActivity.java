package com.example.mobileplustoyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyService myService;
    boolean isService = false;

    static float ans = 0;

    final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            myService = binder.getService();
            isService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isService = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bmul = (Button) findViewById(R.id.button_mul);
        Button badd = (Button) findViewById(R.id.button_add);
        Button bsub = (Button) findViewById(R.id.button_sub);
        Button bdiv = (Button) findViewById(R.id.button_div);
        Button bcalc = (Button) findViewById(R.id.button_calc);
        Button bbind = (Button) findViewById(R.id.button_bind);
        Button bubind = (Button) findViewById(R.id.button_unbind);

        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        TextView textView_result = (TextView) findViewById(R.id.textView_result);
        TextView textView_sym = (TextView) findViewById((R.id.textView_symb));

        bbind.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                Toast.makeText(getApplicationContext(),
                        "Service start", Toast.LENGTH_LONG).show();
            }
        });

        bubind.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                unbindService(mConnection);
                isService = false;
                Toast.makeText(getApplicationContext(),
                        "Service closing", Toast.LENGTH_LONG).show();
            }
        });

        badd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(!isService){
                    Toast.makeText(getApplicationContext(),
                            "Not in service", Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        textView_result.setText("");
                        textView_sym.setText("+");
                        int num1 = Integer.parseInt(editText1.getText().toString());
                        int num2 = Integer.parseInt(editText2.getText().toString());
                        ans = myService.ADD(num1, num2);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });

        bsub.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(!isService){
                    Toast.makeText(getApplicationContext(),
                            "Not in service", Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        textView_result.setText("");
                        textView_sym.setText("-");
                        int num1 = Integer.parseInt(editText1.getText().toString());
                        int num2 = Integer.parseInt(editText2.getText().toString());
                        ans = myService.SUB(num1, num2);
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });

        bmul.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(!isService) {
                    Toast.makeText(getApplicationContext(),
                            "Not in service", Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        textView_result.setText("");
                        textView_sym.setText("X");
                        int num1 = Integer.parseInt(editText1.getText().toString());
                        int num2 = Integer.parseInt(editText2.getText().toString());
                        ans = myService.MUL(num1, num2);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });

        bdiv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(!isService){
                    Toast.makeText(getApplicationContext(),
                            "Not in service", Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        textView_result.setText("");
                        textView_sym.setText("/");
                        int num1 = Integer.parseInt(editText1.getText().toString());
                        int num2 = Integer.parseInt(editText2.getText().toString());
                        ans = myService.DIV(num1, num2);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });

        bcalc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (!isService) {
                    Toast.makeText(getApplicationContext(),
                            "Not in service", Toast.LENGTH_LONG).show();
                    textView_result.setText(" ");
                }
                else{
                    try {
                        textView_result.setText(Float.toString(ans));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}