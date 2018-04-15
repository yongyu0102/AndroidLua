package com.example.zhangpeng.androidlua.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhangpeng.androidlua.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        initView();
        initData();
        initListener();
    }


    abstract int getContentViewId();

  protected     void initView(){}

   protected   void initListener(){}

   protected void initData(){}
}
