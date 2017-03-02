package com.team.happysending.views.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.team.happysending.R;
import com.team.happysending.model.net.FirstEvent;
import com.team.happysending.utils.AMapUtil;
import com.team.happysending.utils.ToastUtil;
import com.team.happysending.utils.dt.Inter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 地理编码与逆地理编码功能介绍
 */
public class ReGeocoderActivity extends Activity implements OnGeocodeSearchListener, OnMarkerClickListener
        , AMapLocationListener, Inputtips.InputtipsListener, AMap.OnMapClickListener, AMap.OnMarkerDragListener {
    private ProgressDialog progDialog = null;
    private GeocodeSearch geocoderSearch;
    private String addressName;
    private Inter inter = new Inter(this);
    private AMap aMap;
    private MapView mapView;
    private LatLonPoint latLonPoint;
    private Marker regeoMarker;
    private AMapLocationClient mMLocationClient;
    private AMapLocationClientOption mMLocationOption;
    private double mLatitude;
    private double mLongitude;
    private ListView mListView;
    private EditText editText;
    //两点之间距离
    private LatLng latlngB;
    private Marker makerB;
    private boolean flag = true;
    private String startAddress;

    private List<String> lists;
    private double mDistance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geocoder);
        /*
         * 设置离线地图存储目录，在下载离线地图或初始化地图设置;
         * 使用过程中可自行设置, 若自行设置了离线地图存储的路径，
         * 则需要在离线地图下载和使用地图页面都进行路径设置
         * */
        //Demo中为了其他界面可以使用下载的离线地图，使用默认位置存储，屏蔽了自定义设置
//        MapsInitializer.sdcardDir =OffLineMapUtils.getSdCacheDir(this);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重0写
        init();
        startmap();
        // 设置定位监听
        aMap.setLocationSource(inter);
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            regeoMarker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            aMap.setOnMarkerClickListener(this);
        }
        //给地图设置点击事件获得点击位置的经纬度
        aMap.setOnMapClickListener(this);
        mListView = (ListView) findViewById(R.id.DtListview);
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        progDialog = new ProgressDialog(this);
        editText = (EditText) findViewById(R.id.selectaddress);
        Button bt = (Button) findViewById(R.id.selectbuut);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeocodeQuery query = new GeocodeQuery(editText.getText().toString(), "010");// 第一个参数表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode，
                geocoderSearch.getFromLocationNameAsyn(query);
                startAddress=editText.getText().toString();


                InputtipsQuery inputquery = new InputtipsQuery(editText.getText().toString(), "北京");
                inputquery.setCityLimit(true);
                Inputtips inputTips = new Inputtips(ReGeocoderActivity.this, inputquery);
                inputTips.setInputtipsListener(ReGeocoderActivity.this);
                inputTips.requestInputtipsAsyn();

            }
        });
        getStartAddress();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    /**
     * 显示进度条对话框
     */
    public void showDialog() {
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage("正在获取地址");
        progDialog.show();
    }

    /**
     * 隐藏进度条对话框
     */
    public void dismissDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

    /**
     * 响应逆地理编码
     */
    public void getAddress(final LatLonPoint latLonPoint) {
        mLatitude=latLonPoint.getLatitude();
        mLongitude=latLonPoint.getLongitude();
        //设置中心点和缩放比例
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLatitude,
                mLongitude), 15));
        showDialog();
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,
                GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        geocoderSearch.getFromLocationAsyn(query);// 设置异步逆地理编码请求
    }

    /**
     * 地理编码查询回调
     */
    @Override
    public void onGeocodeSearched(GeocodeResult result, int rCode) {
        double latitude = result.getGeocodeAddressList().get(0).getLatLonPoint().getLatitude();
        double longitude = result.getGeocodeAddressList().get(0).getLatLonPoint().getLongitude();
        latLonPoint = new LatLonPoint(latitude, longitude);
        getAddress(latLonPoint);
        mMLocationClient.stopLocation();
    }
    /**
     * 逆地理编码回调
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        dismissDialog();
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {

            if (result != null && result.getRegeocodeAddress() != null
                    && result.getRegeocodeAddress().getFormatAddress() != null) {
                addressName = result.getRegeocodeAddress().getFormatAddress()
                        + "附近";
                if (flag) {
                    aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                            AMapUtil.convertToLatLng(latLonPoint), 15));
                    flag = false;
                }
                RegeocodeAddress a = result.getRegeocodeAddress();
                regeoMarker.setPosition(AMapUtil.convertToLatLng(latLonPoint));
                ToastUtil.show(ReGeocoderActivity.this, addressName);
                // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
//		aMap.setMyLocationEnabled(true);
// 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
//		aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
//第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
                String[] add = addressName.split("-");
                String address = addressName.substring(0, 6);
                InputtipsQuery inputquery = new InputtipsQuery(result.getRegeocodeAddress().getTownship(), "北京");
                inputquery.setCityLimit(true);
                Inputtips inputTips = new Inputtips(ReGeocoderActivity.this, inputquery);
                inputTips.setInputtipsListener(this);
                inputTips.requestInputtipsAsyn();
                String provider;

                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                List<String> list = locationManager.getProviders(true);
                if (list.contains(LocationManager.GPS_PROVIDER)) {
                    //是否为GPS位置控制器
                    provider = LocationManager.GPS_PROVIDER;
                } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
                    //是否为网络位置控制器
                    provider = LocationManager.NETWORK_PROVIDER;

                } else {
                    Toast.makeText(this, "请检查网络或GPS是否打开",
                            Toast.LENGTH_LONG).show();
                    return;
                }
            } else {
            }
        } else {
            ToastUtil.showerror(this, rCode);
        }
    }

    private Handler msgHandler = new Handler() {
        public void handleMessage(Message msg) {
            ToastUtil.showerror(ReGeocoderActivity.this, msg.arg1);
        }
    };

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return false;
    }

    private void startmap() {
        //初始化定位
        mMLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听，这里要实现AMapLocationListener接口，AMapLocationListener接口只有onLocationChanged方法可以实现，用于接收异步返回的定位结果，参数是AMapLocation类型。
        mMLocationClient.setLocationListener(ReGeocoderActivity.this);
        //初始化定位参数
        mMLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mMLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mMLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mMLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mMLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mMLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mMLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mMLocationClient.setLocationOption(mMLocationOption);
        //启动定位
        mMLocationClient.startLocation();

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        //得到纬度
        mLatitude = aMapLocation.getLatitude();
        //得到经度
        mLongitude = aMapLocation.getLongitude();
        latLonPoint = new LatLonPoint(mLatitude, mLongitude);
        getAddress(latLonPoint);
        mMLocationClient.stopLocation();
    }

    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        lists = new ArrayList<String>();
        for (Tip t : list) {
            lists.add(t.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReGeocoderActivity.this, android.R.layout.simple_list_item_1, lists);
        mListView.setAdapter(adapter);

    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (makerB != null) {
            makerB.remove();
        }
        latlngB = new LatLng(latLng.latitude, latLng.longitude);
        MarkerOptions icon = new MarkerOptions().position(latlngB)
                .draggable(true)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

        makerB = aMap.addMarker(icon);
        Log.d("TAG", latLng.latitude + "---------------********************----------" + latLng.longitude);
        getAddress(new LatLonPoint(latLng.latitude, latLng.longitude));
        aMap.setOnMarkerDragListener(this);
        //设置中心点和缩放比例
//		aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLatitude,
//				mLongitude), 15));
        mDistance=AMapUtils.calculateLineDistance(regeoMarker.getPosition(), makerB.getPosition());
        Log.d("TAG", "两点之间的距离" + AMapUtils.calculateLineDistance(regeoMarker.getPosition(), makerB.getPosition()));
    }
    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
    }
    private void getStartAddress(){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startAddress=lists.get(position);
                Intent intent = getIntent();
                int falg = intent.getIntExtra("falg", 0);
                //发送消息
                if(falg == 1){
                    EventBus.getDefault().post(new FirstEvent(startAddress,1));

                }else if(falg == 2){

                   EventBus.getDefault().post(new FirstEvent(startAddress,2));
                }
                finish();
            }
        });
    }
    //两点之间的距离
    public double getDistance(){
            return mDistance;
    }
    //返回需要的帮我忙需要的起始和结束地点
    public String getAddressName(){
        return startAddress;
    }
}