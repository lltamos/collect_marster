package com.master.ui.activity.map;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.master.R;
import com.master.app.tools.ActionBarManager;
import com.master.app.tools.FileUtils;
import com.master.app.weight.SearchListView;
import com.master.contract.BaseActivity;
import com.master.ui.adapter.MapListAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import butterknife.BindView;

import static com.master.ui.activity.MainActivity.S_MainActivity;
import static com.master.ui.fragment.home.LayerFragment.S_LayerFragment;

/**
 * @author Litao-pc on 2016/11/7.
 *         ~
 */
public class UncapMapActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listview)
    SearchListView mListView;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        ActionBarManager.initBackTitle(getSupportActionBar());
        title.setText("打开地图");
        Bundle bundle = this.getIntent().getExtras();
        String maptype = bundle.getString("maptype");
        ArrayList items = new ArrayList<String>();

        File demoDataFile;
        demoDataFile = Environment.getExternalStorageDirectory();
        String offlineDataSDCardDirName = "Collect_for_ArcGIS/region";
        if (maptype.equals("bg") || maptype.equals("gl")) {
            if (maptype.equals("bg")) {
                offlineDataSDCardDirName = this.getResources().getString(R.string.offline_dir_bg);
            } else if (maptype.equals("gl")) {
                offlineDataSDCardDirName = this.getResources().getString(R.string.offline_dir_gl);
            }
            String basemap = demoDataFile + File.separator + offlineDataSDCardDirName;
            File f = new File(basemap);
            File[] files = f.listFiles();
            for (int j = 0; j < files.length; j++) {
                ArrayList item = new ArrayList<String>();
                item.add(files[j].getName());
                item.add(FileUtils.FormetFileSize(files[j].length()));
                item.add("");
                item.add("mapserver");
                items.add(item);
                Log.d("filename", files[j].getName());
            }
        } else if (maptype.equals("online")) {
            ArrayList item1 = new ArrayList<>();
            item1.add(this.getResources().getString(R.string.basemap_url));
            item1.add(R.drawable.vectormap);
            item1.add("全球矢量地图服务");
            item1.add("mapserver");
            ArrayList item2 = new ArrayList<String>();
            item2.add(this.getResources().getString(R.string.baseimage_url));
            item2.add(R.drawable.imagemap);
            item2.add("全球影像地图服务");
            item2.add("imageserver");
            items.add(item1);
            items.add(item2);
        }

        mListView.setOnItemClickListener((parent, view, position, id) -> {
            //点击事件执行
            String filename = "";
            String servertype = "";
            if (items != null && items.size() > 0) {
                filename = (String) ((ArrayList) items.get(position)).get(0);
                servertype = (String) ((ArrayList) items.get(position)).get(3);
            }
            try {
                S_LayerFragment.loadMap(filename, maptype, servertype);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Log.d("loadMap end ", "loadMap end");
            S_MainActivity.changetabs(0);
            this.finish();

        });
        MapListAdapter myAdapter = new MapListAdapter(this, items, maptype);
        mListView.setAdapter(myAdapter);
        mListView.setEnableRefresh(false);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.map_list_activity;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    // 菜单项被选择事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }


}
