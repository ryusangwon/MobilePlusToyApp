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

    ServiceConnection mConnection = new ServiceConnection() {
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

        String text1 = editText1.getText().toString();
        String text2 = editText2.getText().toString();

        //int num1 = Integer.parseInt(text1);
        //int num2 = Integer.parseInt(text2);

        bbind.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            }
        });

        bubind.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                unbindService(mConnection);
                isService = false;
            }
        });

        badd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                textView_sym.setText("+");
            }
        });

        bsub.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                textView_sym.setText("-");
            }
        });

        bmul.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                textView_sym.setText("X");
            }
        });

        bdiv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                textView_sym.setText("/");
            }
        });

        bcalc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (!isService){
                    Toast.makeText(getApplicationContext(),
                            "서비스 중이 아님", Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            text1, Toast.LENGTH_LONG).show();
                }
                //int Ans = myService.getAns(num1, num2);
               // textView_result.setText(Ans);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}