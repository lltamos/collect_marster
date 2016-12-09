package com.master.ui.fragment.home;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.esri.android.map.FeatureLayer;
import com.esri.android.map.ags.ArcGISFeatureLayer;
import com.esri.android.map.ags.ArcGISImageServiceLayer;
import com.esri.android.map.ags.ArcGISLocalTiledLayer;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.core.geodatabase.Geodatabase;
import com.esri.core.geodatabase.GeodatabaseFeatureTable;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.map.CallbackListener;
import com.esri.core.map.Feature;
import com.esri.core.map.FeatureResult;
import com.esri.core.tasks.SpatialRelationship;
import com.esri.core.tasks.query.QueryParameters;
import com.master.R;
import com.master.app.SynopsisObj;
import com.master.app.tools.CommonUtils;
import com.master.model.LayerModel;
import com.master.presenter.LayerPresenter;
import com.master.ui.activity.map.MrLayerActivity;
import com.master.ui.adapter.AttributeListAdapter;
import com.master.ui.fragment.MvpFragment;
import com.master.ui.viewer.LayerView;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.runtime.ArcGISRuntime;
import android.app.AlertDialog.Builder;
import android.widget.PopupWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * @param
 * @author Litao-pc on 2016/10/27.
 *         ~
 */
public class LayerFragment extends MvpFragment<LayerPresenter> implements LayerView {

    @BindString(R.string.basemap_url)
    String BASEURL;
    @BindView(R.id.map)
    MapView mMapView;
    public static LayerFragment S_LayerFragment;
    Geodatabase geodatabase =null;
    int j=0;
    Boolean ifidentify = false;
    ArrayList maplayerinfo=new ArrayList();
    @Override
    protected LayerPresenter createPresenter() {
        return new LayerPresenter(new LayerModel());
    }
    @Override
    public void bindView(Bundle savedInstanceState) {
        S_LayerFragment = this;

        //去除水印
        ArcGISRuntime.setClientId("1eFHW78avlnRUPHm");
        ArcGISTiledMapServiceLayer dynamicLayout = new ArcGISTiledMapServiceLayer(BASEURL);
        mMapView.addLayer(dynamicLayout);
        Envelope envelope=new Envelope();
        envelope.setXMin(8176078.237520734);
        envelope.setYMin(379653.9541849808);
        envelope.setXMax(15037685.88562758);
        envelope.setYMax(7086873.419584352);
        mMapView.setExtent(envelope);
        // Set tap listener for MapView
        mMapView.setOnSingleTapListener(new OnSingleTapListener() {
            private static final long serialVersionUID = 1L;

            public void onSingleTap(float x, float y) {
                if (ifidentify&&geodatabase!=null) {
                    j = 0;
                    // convert event into screen click
                    Point pointClicked = mMapView.toMapPoint(x, y);
                    Polygon pg = GeometryEngine.buffer(pointClicked, mMapView.getSpatialReference(), 500, mMapView.getSpatialReference().getUnit()); //null表示采用地图单位
                    List getdatatables = geodatabase.getGeodatabaseTables();
                    GeodatabaseFeatureTable geodatabaseFeatureTable = null;
                    FeatureLayer featureLayer = null;
                    QueryParameters query = new QueryParameters();
                    query.setGeometry(pg);
                    query.setReturnGeometry(true);
                    query.setSpatialRelationship(SpatialRelationship.INTERSECTS);
                    query.setInSpatialReference(mMapView.getSpatialReference());
                    query.setOutFields(new String[]{"*"});
                    query.setMaxAllowableOffset(10000);
                    for (int i = 0; i < getdatatables.size(); i++) {
                        //create a feature layer
                        geodatabaseFeatureTable = (GeodatabaseFeatureTable) getdatatables.get(i);
                        featureLayer = new FeatureLayer(geodatabaseFeatureTable);
                        if ((featureLayer.getMinScale() > mMapView.getScale() || featureLayer.getMinScale() == 0) && featureLayer.isVisible()) {
                            Log.e("e", "featureLayer.getName()" + featureLayer.getName() + "d");
                            geodatabaseFeatureTable.queryFeatures(query, new CallbackListener<FeatureResult>() {
                                public void onCallback(FeatureResult featureSet) {
                                    Log.e("TAG", "Feature featureCount=" + featureSet.featureCount());
                                    Feature feature = null;
                                    Iterator iter = featureSet.iterator();
                                    while (iter.hasNext()) {
                                        feature = (Feature) iter.next();
                                        break;
                                    }
                                    if (feature != null) {
                                        Log.e("e", "feature" + feature.getId() + "d");
                                        ArrayList arraylist = new ArrayList();
                                        Map attrs = feature.getAttributes();
                                        Set set = attrs.entrySet();
                                        Iterator keyiter = set.iterator();
                                        while (keyiter.hasNext()) {
                                            ArrayList arraylist1 = new ArrayList();
                                            Map.Entry entry = (Map.Entry) keyiter.next();
                                            arraylist1.add(0, entry.getKey());
                                            arraylist1.add(1, entry.getValue().toString());
                                            arraylist.add(arraylist1);
                                        }
                                        // show the editor dialog.
                                        getActivity().runOnUiThread(new Runnable() {
                                            public void run() {
                                                // show the editor dialog.
                                                onCreatePopupWindow(arraylist);
                                            }
                                        });
                                    }

                                    if (featureSet.featureCount() > 0) {
                                        j = getdatatables.size() + 1;
                                    }
                                }

                                // handle any errors
                                public void onError(Throwable e) {
                                    Log.e("e", "Select Features Error" + e.getLocalizedMessage());
                                }
                            });
                        }
                        if (j > i) {
                            i = j;
                        }
                    }

                }
            }
        });

        // TODO handle rotation
    }

    /**
     * Overidden method from Activity class - this is the recommended way of creating dialogs
     */
    private void onCreatePopupWindow( ArrayList data) {
        View listLayout = View.inflate(getActivity(), R.layout.attribute_list_activity, null);
        ListView listView = (ListView) listLayout.findViewById(R.id.listview);
        AttributeListAdapter myAdapter = new AttributeListAdapter(mContext, data);
        listView.setAdapter(myAdapter);
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半

        int height = windowManager.getDefaultDisplay().getHeight() / 2;
        int width = windowManager.getDefaultDisplay().getWidth();

        PopupWindow popupWindow = new PopupWindow(listLayout, width, height);
        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(mMapView,0,-height);
    }


    public void loadMap(String filename, String maptype,String servertype) throws FileNotFoundException {
        switch (maptype) {
            case "bg":
                loadMap_bg(filename);
                break;
            case "gl":
                loadMap_gl(filename);
                break;
            case "online":
               loadMap_online(filename,servertype);
               break;
        }
    }
    public void loadMap_gl(String filename) throws FileNotFoundException {
        Log.d("init loadMap","init loadMap");
        ArcGISRuntime.setClientId("1eFHW78avlnRUPHm");
        File demoDataFile;
        String offlineDataSDCardDirName;
        demoDataFile = Environment.getExternalStorageDirectory();
        offlineDataSDCardDirName = this.getResources().getString(R.string.offline_dir_gl);
        // create the path
        String basemap = demoDataFile + File.separator + offlineDataSDCardDirName + File.separator + filename;
        //Open the geodatabase file
        geodatabase = new Geodatabase(basemap);

        List getdatatables=geodatabase.getGeodatabaseTables();
        for(int i=0;i<getdatatables.size();i++) {
            //create a feature layer
            GeodatabaseFeatureTable geodatabaseFeatureTable=(GeodatabaseFeatureTable)getdatatables.get(i);
            FeatureLayer featureLayer = new FeatureLayer(geodatabaseFeatureTable);
            ArrayList maplayer=new ArrayList();
            maplayer.add(0,featureLayer.getID());
            maplayer.add(1,featureLayer.getName());
            maplayer.add(2,featureLayer.getMinScale());
            maplayerinfo.add(mMapView.getScale());
            maplayerinfo.add(maplayer);
            // add the layer
            mMapView.addLayer(featureLayer);
        }

        if(getdatatables.size()>0) {
            FeatureLayer featureLayer1 = new FeatureLayer((GeodatabaseFeatureTable)getdatatables.get(0));
            mMapView.setExtent(featureLayer1.getFullExtent());
        }
        // enable panning over date line
        mMapView.enableWrapAround(true);
    }
    public void loadMap_bg(String filename){
        mMapView.removeAll();
        Log.d("init loadMap","init loadMap");
        ArcGISRuntime.setClientId("1eFHW78avlnRUPHm");
        File demoDataFile;
        String offlineDataSDCardDirName;
        ArcGISLocalTiledLayer localTiledLayer;
        demoDataFile = Environment.getExternalStorageDirectory();
        offlineDataSDCardDirName = this.getResources().getString(R.string.offline_dir_bg);
        // create the url
        String basemap = demoDataFile + File.separator + offlineDataSDCardDirName + File.separator + filename;
        // create the local tpk
        localTiledLayer = new ArcGISLocalTiledLayer(basemap);
        // add the layer
        mMapView.addLayer(localTiledLayer);
        mMapView.setExtent(localTiledLayer.getFullExtent());
        // enable panning over date line
        mMapView.enableWrapAround(true);
        // set Esri logo
        mMapView.setEsriLogoVisible(true);
    }
    public void loadMap_online(String filename,String servertype) throws FileNotFoundException {
        mMapView.removeAll();
        ArcGISRuntime.setClientId("1eFHW78avlnRUPHm");
        if(servertype.equals("mapserver")) {
            ArcGISTiledMapServiceLayer dynamicLayout = new ArcGISTiledMapServiceLayer(filename);
            mMapView.addLayer(dynamicLayout);
        }else if(servertype.equals("imageserver")){
            ArcGISTiledMapServiceLayer dynamicLayout = new ArcGISTiledMapServiceLayer(filename);
            mMapView.addLayer(dynamicLayout);
        }
    }
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_layer;
    }


    @OnClick({R.id.image_tuceng, R.id.image_identify, R.id.image_measure, R.id.image_zoomcenter})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.image_tuceng:
                Intent intent_tuceng= new Intent(mContext, MrLayerActivity.class);
                bundle.putStringArrayList("maplayerinfo", maplayerinfo);
                intent_tuceng.putExtras(bundle);
                CommonUtils.toActivity(mContext,intent_tuceng);
                break;
            case R.id.image_identify:
                if(ifidentify){
                    ifidentify=false;
                }else{
                    ifidentify=true;
                }
                break;
            case R.id.image_measure:
                break;
            case R.id.image_zoomcenter:
                break;
        }
    }
}
