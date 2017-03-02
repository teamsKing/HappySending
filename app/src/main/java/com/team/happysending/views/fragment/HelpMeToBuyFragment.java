package com.team.happysending.views.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.team.happysending.R;
import com.team.happysending.utils.AMapUtil;
import com.team.happysending.utils.ToastUtil;
import com.team.happysending.utils.dt.Inter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoshihao on 2017/2/23.
 */

public class HelpMeToBuyFragment extends Fragment implements GeocodeSearch.OnGeocodeSearchListener, AMap.OnMarkerClickListener, AMapLocationListener
        , Inputtips.InputtipsListener, AMap.OnMapClickListener {
    private ProgressDialog progDialog = null;
    private GeocodeSearch geocoderSearch;
    private String addressName;
    private Inter inter = new Inter(getActivity());
    private AMap aMap;
    private MapView mapView;
    private LatLonPoint latLonPoint;
    private Marker regeoMarker;
    private AMapLocationClient mMLocationClient;
    private AMapLocationClientOption mMLocationOption;
    private double mLatitude;
    private double mLongitude;
    private View view;
    private PopupWindow mPopupWindow;
    private Spinner shipingaddress;
    private List<String> lists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         /*
         * 设置离线地图存储目录，在下载离线地图或初始化地图设置;
         * 使用过程中可自行设置, 若自行设置了离线地图存储的路径，
         * 则需要在离线地图下载和使用地图页面都进行路径设置
         * */
        //Demo中为了其他界面可以使用下载的离线地图，使用默认位置存储，屏蔽了自定义设置
//        MapsInitializer.sdcardDir =OffLineMapUtils.getSdCacheDir(this);
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragments, null);
        mapView = (MapView) view.findViewById(R.id.maps);
        mapView.onCreate(savedInstanceState);// 此方法必须重0写

        return view;
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
        geocoderSearch = new GeocodeSearch(getActivity());
        geocoderSearch.setOnGeocodeSearchListener(this);
        progDialog = new ProgressDialog(getActivity());
        lists = new ArrayList<String>();
        aMap.setOnMapClickListener(this);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
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
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            init();
            startmap();
            // 设置定位监听
            aMap.setLocationSource(inter);
        }
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
                aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        AMapUtil.convertToLatLng(latLonPoint), 15));
                regeoMarker.setPosition(AMapUtil.convertToLatLng(latLonPoint));
                ToastUtil.show(getActivity(), addressName);
                // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
//		aMap.setMyLocationEnabled(true);
// 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
//		aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
//第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
                lists.add(addressName);
                InputtipsQuery inputquery = new InputtipsQuery(result.getRegeocodeAddress().getTownship(), "北京");
                inputquery.setCityLimit(true);
                Inputtips inputTips = new Inputtips(getActivity(), inputquery);
                inputTips.setInputtipsListener(this);
                inputTips.requestInputtipsAsyn();
                String provider;

                LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                List<String> list = locationManager.getProviders(true);
                if (list.contains(LocationManager.GPS_PROVIDER)) {
                    //是否为GPS位置控制器
                    provider = LocationManager.GPS_PROVIDER;
                } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
                    //是否为网络位置控制器
                    provider = LocationManager.NETWORK_PROVIDER;

                } else {
                    Toast.makeText(getActivity(), "请检查网络或GPS是否打开",
                            Toast.LENGTH_LONG).show();
                    return;
                }
            } else {
            }
        } else {
            ToastUtil.showerror(getActivity(), rCode);
        }
    }

    private Handler msgHandler = new Handler() {
        public void handleMessage(Message msg) {
            ToastUtil.showerror(getActivity(), msg.arg1);
        }
    };

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return false;
    }

    private void startmap() {
        //初始化定位
        mMLocationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //设置定位回调监听，这里要实现AMapLocationListener接口，AMapLocationListener接口只有onLocationChanged方法可以实现，用于接收异步返回的定位结果，参数是AMapLocation类型。
        mMLocationClient.setLocationListener(this);
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

        latLonPoint = new LatLonPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude());
        getAddress(latLonPoint);
        mMLocationClient.stopLocation();
    }

    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        //显示地址
        showPopupWindow();
        lists.clear();
        for (Tip t : list) {
            lists.add(t.getName());
        }
        //设置收货地址
        shipingaddress.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, lists));

    }

    private void showPopupWindow() {
        View views = View.inflate(getActivity(), R.layout.dialog_address, null);
        initchilView(views);
        AlertDialog.Builder builder;
        AlertDialog dialog;
        builder = new AlertDialog.Builder(getActivity());
        builder.setView(views);
        dialog = builder.create();
//        dialog.setCanceledOnTouchOutside(false);
        Window win = dialog.getWindow();
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        WindowManager wm = getActivity().getWindowManager();
        int height = wm.getDefaultDisplay().getHeight();
        int y = (int) height / 4;
        params.y = y;//设置y坐标
        params.height = 400;
        win.setAttributes(params);
        dialog.show();

    }

    private void initchilView(View view) {
        shipingaddress = (Spinner) view.findViewById(R.id.shippingaddress);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        regeoMarker.remove();
        MarkerOptions icon = new MarkerOptions().position(new LatLng(latLng.latitude, latLng.longitude))
                .draggable(true)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        regeoMarker = aMap.addMarker(icon);
        latLonPoint.setLatitude(latLng.latitude);
        latLonPoint.setLongitude(latLng.longitude);
        getAddress(latLonPoint);
    }
}
