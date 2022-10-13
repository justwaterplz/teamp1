package com.example.mjucampusguide;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

<<<<<<< HEAD
=======

>>>>>>> b277f41ed8ac6d83655f894d3cb15b995b61f009
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
<<<<<<< HEAD
=======

>>>>>>> b277f41ed8ac6d83655f894d3cb15b995b61f009
import androidx.annotation.NonNull;
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

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.LinkedList;


=======
import java.util.LinkedList;

import java.util.ArrayList;
import java.util.LinkedList;

>>>>>>> b277f41ed8ac6d83655f894d3cb15b995b61f009
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, NaverMap.OnMapClickListener {
    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private NaverMap nMap;
//  naver import
    private MapView mapView;
    private LinkedList<FC> from_db;
    private FirebaseDatabase db;
    private DatabaseReference dbr;
<<<<<<< HEAD
=======

>>>>>>> b277f41ed8ac6d83655f894d3cb15b995b61f009
    //위치 반환 구현체
    private FusedLocationSource mLocationSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NaverMapSdk.getInstance(this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("hqrwc9phrm"));

        setContentView(R.layout.activity_maps_naver);

        mapView = findViewById(R.id.map_view);
        
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


//      파이어베이스 연동구문

        from_db = new LinkedList<>();//FC를 데이터베이스에거 받아올 리스트

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


        //위치 반환하는 구현체 생성
        mLocationSource = new FusedLocationSource(this,PERMISSION_REQUEST_CODE);
        FusedLocationSource mLocationSource = this.mLocationSource;



    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
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
}


