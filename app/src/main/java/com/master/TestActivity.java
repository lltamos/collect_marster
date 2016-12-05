package com.master;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.master.app.Manager.FieldWordsDirs;
import com.master.bean.Fields;
import com.master.dao.MapDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    String json = "Catsic_Codes.json";


    @BindView(R.id.button)
    Button button;
    private int i;
    private MapDao m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        i = 10001;
    }

    @OnClick(R.id.button)
    public void onClick() {
        Fields fieldWordsDirs = FieldWordsDirs.get().fieldsFormFName("AJCHNXF");
        String fNameCHS = fieldWordsDirs.getFNameCHS();
        System.out.println(fNameCHS);
    }
}
