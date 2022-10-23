package com.example.mjucampusguide;

//import static com.naver.maps.map.g.a.v;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Bundle;

import android.util.Log;

import androidx.annotation.NonNull;

//import com.google.android.material.animation.AnimationUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;


import java.util.ArrayList;
import java.util.Iterator;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, NaverMap.OnMapClickListener {
    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private NaverMap nMap;

//  naver import
    private MapView mapView;

    //fab(category)
    private FloatingActionButton fabCategory;

    //fab(CVS)
    private FloatingActionButton fabCVS;
    //fab(Print)
    private FloatingActionButton fabPrint;
    //fab(Cafeteria)
    private FloatingActionButton fabCafeteria;
    //fab(Rest)
    private FloatingActionButton fabRest;

    //fab animations
    private Animation fabOpen;
    private Animation fabClose;
    private Animation toBottom;
    private Animation fromBottom;

   //
   // private Animation fabClose;
   // private AnimationUtils toBottom;
   // private AnimationUtils fromBottom;

    //clicked
    private boolean clicked = false;

    //  파이어베이스
    private ArrayList<FC> from_db;
    private FirebaseDatabase db;
    private DatabaseReference dbr;

//  카테고리
    private Category Print;
    private Category CS;
    private Category ATM;
    private Category Restaurant;
    private Category RA;
    private Category ETC;

//  건물
    private Bilding B_2;
    private Bilding B_3;
    private Bilding B_5;
    private Bilding B_7;
    private Bilding B_8;
    private Bilding B_10;
    private Bilding B_11;
    private Bilding B_12;
    private Bilding B_14;
    private Bilding B_13;


    //위치 반환 구현체
    private FusedLocationSource mLocationSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NaverMapSdk.getInstance(this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("hqrwc9phrm"));

        setContentView(R.layout.activity_maps_naver);

        mapView = findViewById(R.id.map_view);

        //카테고리 fab
        fabCategory = findViewById(R.id.fabCategory);
        
        //NaverMap 객체 받기
        mapView.getMapAsync(this);

        //카메라 포지션 설정
        CameraPosition cameraPosition = new CameraPosition(
                new LatLng(37.222866, 127.190195),16);

        //맵 옵션
        NaverMapOptions options = new NaverMapOptions()
                .camera(cameraPosition)
                .compassEnabled(false)
                .scaleBarEnabled(false);

        //애니메이션 
        fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open_anim);
        fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close_anim);
        toBottom = AnimationUtils.loadAnimation(this,R.anim.to_bottom_anim);
        fromBottom = AnimationUtils.loadAnimation(this,R.anim.to_bottom_anim);

        //카테고리버튼 + 액션리스너
        fabCategory = findViewById(R.id.fabCategory);
        fabCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCategoryBtnClicked();
            }
        });

        //편의점
        fabCVS = findViewById(R.id.fabCategory);
        fabCVS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이후 이벤트 처리
                //onCVSBtnClicked();
            }
        });
        //식당
        fabCafeteria = findViewById(R.id.fabCafeteria);
        fabCafeteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이후 이벤트 처리
                //onCafeteriaBtnClicked();
            }
        });
        //복사실
        fabPrint = findViewById(R.id.fabPrint);
        fabPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이후 이벤트 처리
                //onPrintBtnClicked();
            }
        });
        //휴게실
        fabRest = findViewById(R.id.fabRest);
        fabRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이후 이벤트 처리
                //onRestBtnClicked();
            }
        });


//      파이어베이스 연동구문

        from_db = new ArrayList<>();//FC를 데이터베이스에거 받아올 리스트

        db = FirebaseDatabase.getInstance();//파이어베이스 DB연동

        dbr = db.getReference("FC");
        dbr.addListenerForSingleValueEvent(new ValueEventListener() {
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
                Log.e("MapsActivity", String.valueOf(error.toException()));//에러문 출력
            }
        });
//  카테고리 생성 및 분류
        Print = new Category("Print");
        CS = new Category("CS");
        Restaurant = new Category("Restaurant");
        ATM = new Category("ATM");
        RA = new Category("RA");
        ETC = new Category("ETC");

        classification_by_Category(from_db);
//  건물 생성 및 분류
        B_2 = new Bilding("학관", 2);
        B_3 = new Bilding("복지동", 3);
        B_5 = new Bilding("명덕관, 명현관", 5);
        B_7 = new Bilding("1공,5공학관", 7);
        B_8 = new Bilding("명진당", 8);
        B_10 = new Bilding("신학협력관, 방목기념관", 10);
        B_11 = new Bilding("함박관", 11);
        B_12 = new Bilding("차세대 과학관", 12);
        B_13 = new Bilding("건축관, 자연조형센터 , 디자인관", 13);
        B_14 = new Bilding("3공학관", 14);

        classification_by_Bliding(from_db);

//  최단거리 이차원 배열 1~14번까지
        int [14][14] minway={
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

        //위치 반환하는 구현체 생성
        mLocationSource = new FusedLocationSource(this,PERMISSION_REQUEST_CODE);
        FusedLocationSource mLocationSource = this.mLocationSource;

        fabCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //카테고리 클릭 시 버튼 위로 펼쳐짐

            }
        });





    }

    private void onCategoryBtnClicked(){
        setVisibility(clicked);
        setAnimation(clicked);
        clicked = !clicked;
    }

    private void setVisibility(boolean clicked){
        //on
        if(!clicked){
            fabCVS.setVisibility(fabCategory.VISIBLE);
            fabCafeteria.setVisibility(fabCategory.VISIBLE);
            fabPrint.setVisibility(fabCategory.VISIBLE);
            fabRest.setVisibility(fabCategory.VISIBLE);
        }
        else{
            fabCVS.setVisibility(fabCategory.INVISIBLE);
            fabCafeteria.setVisibility(fabCategory.INVISIBLE);
            fabPrint.setVisibility(fabCategory.INVISIBLE);
            fabRest.setVisibility(fabCategory.INVISIBLE);
        }
    }

    //애니메이션효과
    private void setAnimation(boolean clicked){
        if(!clicked){
            fabCVS.startAnimation(fromBottom);
            fabCafeteria.startAnimation(fromBottom);
            fabPrint.startAnimation(fromBottom);
            fabRest.startAnimation(fromBottom);

            fabCategory.startAnimation(fabOpen);
        }
        else{
            fabCVS.startAnimation(toBottom);
            fabCafeteria.startAnimation(toBottom);
            fabPrint.startAnimation(toBottom);
            fabRest.startAnimation(toBottom);

            fabCategory.startAnimation(fabClose);
        }
    }


    @Override
    public void onMapReady(NaverMap naverMap) {

//      카메라 위치 업데이트(이거를 해야 초기화면 학교로뜸) +경도 수정 0.0018뺌
        CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(new LatLng(37.222866, 127.188395),15.6);
        naverMap.moveCamera(cameraUpdate);

//      초기화면 마커 위치설정 (명지대학교 자연캠퍼스)  +나중에 지울 예정
        Marker marker = new Marker();
        marker.setPosition(new LatLng(37.222866, 127.190195));
        marker.setMap(naverMap);

        //네이버맵 객체에 위치 소스 지정
        nMap = naverMap;
        nMap.setLocationSource(mLocationSource);

        //권한확인 하고 결관느 onRequestPermissionResult 콜백 메소드 호출
        ActivityCompat.requestPermissions(this,PERMISSIONS,PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                nMap.setLocationTrackingMode(LocationTrackingMode.NoFollow);
            }
        }
    }

//  데이터 분류 함수
    public void classification_by_Category(ArrayList<FC> arrayList){
        Iterator<FC> fc = arrayList.iterator();
        while(fc.hasNext()){
            FC i = fc.next();
            if(i.getCategory() == "Print"){
                Print.Add(i);
            }else if(i.getCategory() == "CS") {
                CS.Add(i);
            }else if(i.getCategory() == "ATM"){
                ATM.Add(i);
            }else if(i.getCategory() == "Restaurant"){
                Restaurant.Add(i);
            }else if(i.getCategory() == "RA"){
                RA.Add(i);
            }else if(i.getCategory() == "ETC"){
                ETC.Add(i);
            }
        }
    }

    public void classification_by_Bliding(ArrayList<FC> init){
        Iterator<FC> in = init.iterator();
        while(in.hasNext()){
            FC i = in.next();
            if(i.getAddress() == 2){
                B_2.add(i);
            }else if(i.getAddress() == 3){
                B_3.add(i);
            }
            else if(i.getAddress() == 5){
                B_5.add(i);
            }
            else if(i.getAddress() == 7){
                B_7.add(i);
            }
            else if(i.getAddress() == 8){
                B_8.add(i);
            }
            else if(i.getAddress() == 10){
                B_10.add(i);
            }
            else if(i.getAddress() == 11){
                B_11.add(i);
            }
            else if(i.getAddress() == 12){
                B_12.add(i);
            }
            else if(i.getAddress() == 13){
                B_13.add(i);
            }
            else if(i.getAddress() == 14){
                B_14.add(i);
            }
        }
    }
}

// 최단거리 반환 함수
    public int min_building(int bnum){
        int arr[14];
        int [] minb = {0,1,2,3,4,5,6,7,8,9,10,11,12,13};
        int temp =0;
        int temp2 =0;

        for(int i =0; i<14; i++){
            arr[i] = minway[bnum][i];
        }

        for(int i = 0; i < 14; i++) {
            for(int j = 0; j < 13; j++) {
                if(arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    temp2 = minb[j];
                    minb[j] = minb[j + 1];
                    minb[j + 1]= temp2;

                }
            }
        }

        return minb;
    }
