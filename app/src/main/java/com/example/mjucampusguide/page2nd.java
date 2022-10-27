package com.example.mjucampusguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              이전버튼 누르면 전페이지인 초기화면 나옴
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

        //CS
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              이전버튼 누르면 전페이지인 초기화면 나옴
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

        //atm
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              이전버튼 누르면 전페이지인 초기화면 나옴
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

        //restaurant
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              이전버튼 누르면 전페이지인 초기화면 나옴
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

        //RA
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              이전버튼 누르면 전페이지인 초기화면 나옴
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

        //ETC
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              이전버튼 누르면 전페이지인 초기화면 나옴
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              이전버튼 누르면 전페이지인 초기화면 나옴
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });


    }
}
