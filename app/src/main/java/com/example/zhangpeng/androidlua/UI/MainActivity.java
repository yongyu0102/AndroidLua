package com.example.zhangpeng.androidlua.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhangpeng.androidlua.R;
import com.example.zhangpeng.androidlua.luajava.LuaJavaHelper;
import com.example.zhangpeng.androidlua.luajava.LuaUtil;

import org.keplerproject.luajava.LuaState;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private LuaState mLuaState;
    private LuaJavaHelper luaJavaHelper;
    private Button btnCreateView;
    private Button btnRecyclerView;
    private Button btnAndroidLua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLua();
    }

    @Override
    int getContentViewId() {
        return (R.layout.activity_main);
    }


    private void initLua() {
         luaJavaHelper = new LuaJavaHelper();
         mLuaState=luaJavaHelper.getLuaState();
        LuaUtil.doStringFromAssets("main_activity.lua",mLuaState);
    }

    protected void initView(){
        btnCreateView = (Button) findViewById(R.id.btn_create_view);
        btnRecyclerView = (Button) findViewById(R.id.btn_recyclerView);
        btnAndroidLua = (Button) findViewById(R.id.btn_android_lua);

    }

    protected void initListener(){
        btnCreateView.setOnClickListener(this);
        btnRecyclerView.setOnClickListener(this);
        btnAndroidLua.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_create_view :
                startCreateViewActivity();
                break;
            case R.id.btn_recyclerView :
                startRecyclerVIewActivity();
                break;
            case R.id.btn_android_lua :
                Intent intent=new Intent(MainActivity.this,AndroidLuaActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void startRecyclerVIewActivity() {
        Intent intent=new Intent(MainActivity.this,RecyclerViewActivity.class);
        startActivity(intent);
    }

    private void startCreateViewActivity() {
        // 获取 lua startCreateViewActivity 函数
        mLuaState.getGlobal("startCreateViewActivity");
        //将 MainActivity.this 对象压栈
        mLuaState.pushJavaObject(this);
        // 调用 lua startCreateViewActivity 函数 ，并传入一个参数，返回值 0 个
        mLuaState.call(1, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        luaJavaHelper.closeLua();
    }
}
