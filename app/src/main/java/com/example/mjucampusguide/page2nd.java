package com.example.mjucampusguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class page2nd extends AppCompatActivity {

    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2nd);

        Button prevBtn = (Button)findViewById(R.id.prevBtn);

        //버튼 객체화
        //print cs atm restaurant RA ETC 6개
        Button printBtn1 = (Button)findViewById(R.id.printBtn);
        Button csBtn1 = (Button)findViewById(R.id.csBtn);
        Button atmBtn1 = (Button)findViewById(R.id.atmBtn);
        Button restaurantBtn1 = (Button)findViewById(R.id.restaurantBtn);
        Button RABtn1 = (Button)findViewById(R.id.RABtn);
        Button ETCBtn1 = (Button)findViewById(R.id.etcBtn);

        //각 버튼마다 클릭리스너 등록
        //print
        printBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(page2nd.this , RecyclerActivity.class);
                intent.putExtra("B_num", 0);
                startActivity(intent);
            }
        });

        //CS
        csBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(page2nd.this ,RecyclerActivity.class);
                //intent.putExtra("Category", CS);
                startActivity(intent);
            }
        });

        //atm
        atmBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(page2nd.this ,RecyclerActivity.class);
                //intent.putExtra("Category", ATM);
                startActivity(intent);
            }
        });

        //restaurant
        restaurantBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(page2nd.this ,RecyclerActivity.class);
                //intent.putExtra("Category", Restaurant);
                startActivity(intent);
            }
        });

        //RA
        RABtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(page2nd.this ,RecyclerActivity.class);
                //intent.putExtra("Category", RA);
                startActivity(intent);
            }
        });

        //ETC
        ETCBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(page2nd.this ,RecyclerActivity.class);
                //intent.putExtra("Category", ETC);
                startActivity(intent);
            }
        });


        //이전
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });


    }






}
