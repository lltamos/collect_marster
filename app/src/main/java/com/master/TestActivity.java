package com.master;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.master.app.view.EditTextFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    String json = "Catsic_Codes.json";

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.ll_container)
    LinearLayout ll_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button)
    public void onClick() {
        EditTextFactory edit=new EditTextFactory();
        try {
            List<View> viewsFromJson = edit.getViewsFromJson("nihao", this, "hint", null);
            System.out.println("执行");
            ll_container.addView(viewsFromJson.get(0));
        } catch (Exception e) {
            e.printStackTrace();




        }

    }
}
