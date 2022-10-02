package com.example.mjucampusguide;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

public class MapsActivity extends Activity implements OnMapReadyCallback {

//  naver import
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NaverMapSdk.getInstance(this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("hqrwc9phrm"));

        setContentView(R.layout.activity_maps_naver);
        mapView = findViewById(R.id.map_view);
        mapView.getMapAsync(this);

//      카메라 포지션 설정
        CameraPosition cameraPosition = new CameraPosition(
                new LatLng(37.222866, 127.190195),16);

        NaverMapOptions options = new NaverMapOptions()
                .camera(cameraPosition)
                .compassEnabled(false)
                .scaleBarEnabled(false);

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

//      카메라 위치 업데이트(이거를 해야 초기화면 학교로뜸)
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.222866, 127.190195));
        naverMap.moveCamera(cameraUpdate);

//      초기화면 마커 위치설정 (명지대학교 자연캠퍼스)
        Marker marker = new Marker();
        marker.setPosition(new LatLng(37.222866, 127.190195));
        marker.setMap(naverMap);
    }
}
