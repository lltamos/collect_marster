package com.master.server;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.master.app.SynopsisObj;

import java.util.Date;

/**
 * <p>Title:${type_inName}<p/>
 * <p>Description:<p/>
 * <p>Company: </p>
 *
 * @author litao
 * @mail llsmpsvn@gmail.com
 * @date on 2016/12/5
 */
public class TrackServer implements Runnable {

    public LocationManager loctionManager;

    public String provider = null;//位置提供器

    public void intiLocationManager(int t) {

        try {
            String contextService = Context.LOCATION_SERVICE;
            //通过系统服务，取得LocationManager对象
            loctionManager = (LocationManager) SynopsisObj.getAppContext()
                    .getSystemService(contextService);
            //使用标准集合，让系统自动选择可用的最佳位置提供器，提供位置
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);//高精度
            criteria.setAltitudeRequired(true);//要求海拔
            criteria.setBearingRequired(true);//要求方位
            criteria.setCostAllowed(true);//允许有花费
            criteria.setSpeedRequired(true);
            criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);//功耗
            //从可用的位置提供器中，匹配以上标准的最佳提供器
            provider = loctionManager.getBestProvider(criteria, true);
            loctionManager.requestLocationUpdates(provider, t * 1000, 0,
                    locationListener);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 关闭位置监控
     */
    public void delLocationManager() {
        try {
            loctionManager.removeUpdates(locationListener);
            loctionManager = null;
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }


    class locaDate {
        private double lat;//纬度
        private double lng;//经度
        private double alt;//高度
        private double speed;//速读
        private Date time;//时间
    }


    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            locaDate loc;
            if (location != null) {
                loc = new locaDate();
                loc.lat = location.getLatitude();
                loc.lng = location.getLongitude();
                loc.alt = location.getAltitude();
                loc.speed = location.getSpeed();
                Date lo_time = new Date(location.getTime());

            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

}
