package com.master.ui.activity.map;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.master.R;
import com.master.app.tools.ActionBarManager;
import com.master.app.tools.StringUtils;
import com.master.bean.MapInfo;
import com.master.contract.BaseActivity;
import com.master.dao.MapDao;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class NewMapActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_name)
    EditText tv_name;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        ActionBarManager.initBackTitle(getSupportActionBar());
        title.setText("新建地图");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_new_map;
    }


    @OnClick(R.id.btn_submit)
    public void onClick() {
        String mapName = tv_name.getText().toString();
        if (StringUtils.isNotEmpty(mapName)) {
            MapInfo bean = new MapInfo(new Date(), mapName, false);
            boolean inserts = MapDao.get().inserts(bean);
            if (inserts) Toast.makeText(this, "新建地图成功", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "请输入名称", Toast.LENGTH_SHORT).show();

    }
}
