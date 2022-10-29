package com.example.mjucampusguide;

//import static com.naver.maps.map.g.a.v;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Bundle;

import android.util.Log;

import androidx.annotation.NonNull;

import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


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

    //button(category)
    private Button categoryBtn;

    //print cs atm restaurant ra etc button
    private Button printBtn;
    private Button csBtn;
    private Button atmBtn;
    private Button restaurantBtn;
    private Button raBtn;
    private Button etcBtn;



    //clicked
    private boolean clicked = false;

    /*//  파이어베이스
    private ArrayList<FC> from_db;
    private FirebaseDatabase db;
    private DatabaseReference dbr;*/

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

        categoryBtn = (Button)findViewById(R.id.categoryBtn);

        /*      파이어베이스 연동구문

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
        });*/





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

//      버튼 역할 부여
        categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this,page2nd.class);
                //intent.putExtra("list", from_db);
                startActivity(intent);
            }
        });

        //위치 반환하는 구현체 생성
        mLocationSource = new FusedLocationSource(this,PERMISSION_REQUEST_CODE);
        FusedLocationSource mLocationSource = this.mLocationSource;






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

