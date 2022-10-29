package com.example.mjucampusguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
    private Spinner spinner;
    private RecyclerView recyclerView;
    private FCAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Category category;

    private ArrayList<FC> from_db;
    //private FirebaseDatabase db;
    //private DatabaseReference dbr;

    private Category Print;
    private Category CS;
    private Category ATM;
    private Category Restaurant;
    private Category RA;
    private Category ETC;
    private Category Cafe;

    private int B_num = 0;
    private int C_num;

    private boolean isFirstSelected = true;

    //  최단거리 이차원 배열 1~14번까지
    private int [][] min_way ={
            {0, 73, 335, 235, 435, 205, 155, 185, 253, 248, 253, 265, 363, 649},
            {73, 0, 356, 162, 456, 132, 82, 112, 180, 175, 180, 192, 290, 576},
            {335, 356, 0, 430, 100, 400, 350, 380, 448, 443, 448, 460, 470, 550},
            {235, 162, 430, 0, 530, 130, 80, 50, 84, 125, 138, 113, 288, 574},
            {435, 456, 100, 530, 0, 497, 450, 480, 548, 543, 548, 560, 570, 505},
            {205, 132, 400, 130, 497, 0, 50, 80, 148, 78, 110, 160, 158, 444},
            {155, 82, 350, 80, 450, 50, 0, 30, 98, 93, 98, 110, 208, 494},
            {185, 112, 380, 50, 480, 80, 30, 0, 68, 96, 88, 84, 238, 524},
            {253, 180, 448, 84, 548, 148, 98, 68, 0, 78, 116, 66, 263, 549},
            {255, 182, 450, 168, 550, 70, 100, 118, 135, 0, 45, 95, 185, 471},
            {227, 154, 422, 125, 522, 118, 72, 75, 90, 50, 0, 50, 235, 521},
            {235, 162, 430, 100, 530, 82, 80, 50, 40, 12, 50, 0, 197, 483},
            {350, 277, 420, 275, 502, 145, 195, 225, 293, 188, 233, 283, 0, 286},
            {648, 575, 485, 573, 503, 443, 493, 523, 591, 477, 522, 572, 298, 0}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler);
        //      파이어베이스 연동구문
        from_db = new ArrayList<>();//FC를 데이터베이스에거 받아올 리스트

        //db = FirebaseDatabase.getInstance();//파이어베이스 DB연동

        //dbr = db.getReference("FC");
        /*db.getReference("FC").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //파이어베이스 데이터 받아오는 곳
                from_db.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    FC fc = snapshot1.getValue(FC.class);
                    from_db.add(fc);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("RecyclerActivity", String.valueOf(error.toException()));//에러문 출력
            }
        });*/




        Print = new Category("Print");
        CS = new Category("CS");
        Restaurant = new Category("Restaurant");
        ATM = new Category("ATM");
        RA = new Category("RA");
        ETC = new Category("ETC");
        Cafe = new Category("Cafe");
        category = new Category("C");
        addValues();

        classification_by_Category(from_db);

        Intent i = getIntent();
        C_num = i.getIntExtra("Category", 0);
        B_num = i.getIntExtra("B_num", 0);
        if(C_num == 0){
            category = Print;
        }else if(C_num == 1){
            category = CS;
        }else if(C_num == 2){
            category = ATM;
        }else if(C_num == 3){
            category = Restaurant;
        }else if(C_num == 4){
            category = RA;
        }else if(C_num == 5){
            category = ETC;
        }
        category.CategorySort(min_building(B_num));

        recyclerView = (RecyclerView)findViewById(R.id.FCrecycler_view);

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FCAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.setArrayList(category.getList());





        spinner = (Spinner) findViewById(R.id.Bildingspinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirstSelected) {
                    isFirstSelected = false;
                } else {
                    // 로직

                    int i = position-1;
                    Intent intent = new Intent(RecyclerActivity.this ,RecyclerActivity.class);
                    intent.putExtra("B_num", i);
                    intent.putExtra("Category", C_num);
                    startActivity(intent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Button prevBtn = (Button)findViewById(R.id.prevBtn_r);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerActivity.this,page2nd.class);
                startActivity(intent);
            }
        });
    }

    public void addValues(){
        from_db.add(new FC("세븐일레븐편의점(학관)", 2, "CS", "1층"));
        from_db.add(new FC("버거307", 2, "Restaurant", "1층"));
        from_db.add(new FC("학생식당(학관)", 2, "Restaurant", "1층"));
        from_db.add(new FC("하나은행ATM(학관)", 2, "ATM", "1층"));
        from_db.add(new FC("우편취급국", 2, "ETC", "1층, 운영시간 : 평일(9시~18시), 점심시간(12시~13시)"));
        from_db.add(new FC("명지view 안경점", 2, "ETC", "2층"));
        from_db.add(new FC("STEFF 핫도그", 2, "Restaurant", "2층"));
        from_db.add(new FC("한솔문구", 2, "ETC", "2층"));
        from_db.add(new FC("젤라또", 2, "Restaurant", "2층"));
        from_db.add(new FC("복사실(5공, 1공)", 7, "Print", "1층"));
        from_db.add(new FC("5공 라운지", 7, "RA", "1층"));
        from_db.add(new FC("하나은행ATM(명진당)", 8, "ATM", "1층"));
        from_db.add(new FC("세븐일레븐편의점(명진당)", 8, "CS", "지하1층,유.무인 24시"));
        from_db.add(new FC("우체국예금ATM(명진당)", 8, "ATM", "1층"));
        from_db.add(new FC("휴게공간,스터디룸(명진당)", 8, "RA", "4층"));
        from_db.add(new FC("복사실(명진당)", 8, "Print", "4층"));
        from_db.add(new FC("도서관", 8, "ETC", "2층, 3층"));
        from_db.add(new FC("복사실(차세대 과학관)", 12, "Print", "5층"));
        from_db.add(new FC("폴주니어", 11, "Restaurant", "3층"));
        from_db.add(new FC("세븐일레븐편의점(함박관)", 11, "CS", "3층"));
        from_db.add(new FC("교직원식당(방목기념관)", 10, "Restaurant", "2층"));
        from_db.add(new FC("학생식당(복지동)", 3, "Restaurant", "1층"));
        from_db.add(new FC("이마트편의점(복지동)", 3, "CS", "1층"));
        from_db.add(new FC("하나은행ATM(복지동)", 3, "ATM", "1층"));
        from_db.add(new FC("명지대 당구.카페", 3, "Cafe", "2층"));
        from_db.add(new FC("명덕카페", 5, "Cafe", "1층"));
        from_db.add(new FC("세븐일레븐편의점(명덕관)", 5, "CS", "1층"));
        from_db.add(new FC("Grazie", 14, "Cafe", "1층"));
        from_db.add(new FC("Cafeing", 13, "Cafe", "1층"));
        from_db.add(new FC("학생휴개실(건축학관)", 13, "RA", "1층"));
        from_db.add(new FC("남학생휴개실(학관)", 2, "RA", "2층"));
        from_db.add(new FC("하나은행", 2, "ETC", "1층"));

    }

    //  데이터 분류 함수
    public void classification_by_Category(ArrayList<FC> arrayList){
        for(int i = 0; i<arrayList.size();i++){
            FC fc = arrayList.get(i);
            String s = fc.getCategory();
            if(s == "Print"){
                Print.Add(fc);
            }else if(s == "CS") {
                CS.Add(fc);
            }else if(s == "ATM"){
                ATM.Add(fc);
            }else if(s == "Restaurant"){
                Restaurant.Add(fc);
            }else if(s == "RA"){
                RA.Add(fc);
            }else if(s == "ETC"){
                ETC.Add(fc);
            }else if(s == "Cafe"){
                Cafe.Add(fc);
            }
        }
    }


    // 최단거리 반환 함수
    public int[] min_building(int b_num){
        int []arr = {0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int []min_b = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        int temp =0;
        int temp2 =0;

        for(int i =0; i<14; i++){
            arr[i] += min_way[b_num][i];
        }

        for(int i = 0; i < 14; i++) {
            for(int j = i+1; j < 13; j++) {
                if(arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    temp2 = min_b[i];
                    min_b[i] = min_b[j];
                    min_b[j]= temp2;

                }
            }
        }


        return min_b;
    }
}