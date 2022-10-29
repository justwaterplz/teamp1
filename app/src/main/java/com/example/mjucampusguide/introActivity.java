package com.example.mjucampusguide;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class introActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        introThread introthread = new introThread(handler);
        introthread.start();
    }

   Handler handler = new Handler(Looper.getMainLooper()){
       @Override
       public void handleMessage( Message msg) {
           if (msg.what==1){
               Intent intent = new Intent(introActivity.this,MapsActivity.class);
               startActivity(intent);
           }
       }
   };




}



