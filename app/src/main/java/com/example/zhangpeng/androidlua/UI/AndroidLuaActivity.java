package com.example.zhangpeng.androidlua.UI;

import android.view.View;
import android.widget.Button;

import com.example.zhangpeng.androidlua.luajava.AndroidLua;
import com.example.zhangpeng.androidlua.R;
import com.example.zhangpeng.androidlua.luajava.LuaJavaHelper;
import com.example.zhangpeng.androidlua.luajava.LuaUtil;

import org.keplerproject.luajava.LuaState;

import java.util.HashMap;
import java.util.Map;

public class AndroidLuaActivity extends BaseActivity implements View.OnClickListener {

    private LuaState luaState;
    private Button btnPushMap;
    private Button btnGetMap;
    private Button btnGetMapFun;

    @Override
    int getContentViewId() {
        return R.layout.activity_android_lua;
    }

    @Override
    protected void initData() {
        LuaJavaHelper luaJavaHelper = new LuaJavaHelper();
        luaState = luaJavaHelper.getLuaState();
        LuaUtil.doStringFromAssets("android_lua.lua", luaState);
        AndroidLua androidLua = new AndroidLua();
        luaJavaHelper.registerObject(androidLua, "androidLua");
    }

    @Override
    protected void initView() {
        btnPushMap = (Button) findViewById(R.id.btn_push_map);
        btnGetMap = (Button) findViewById(R.id.btn_get_map);
        btnGetMapFun = (Button) findViewById(R.id.btn_get_map_fun);
    }

    @Override
    protected void initListener() {
        btnPushMap.setOnClickListener(this);
        btnGetMap.setOnClickListener(this);
        btnGetMapFun.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_push_map:
                pushAndroidMapDataToLua();
                break;
            case R.id.btn_get_map:
                getDataFromLua();
                break;
            case R.id.btn_get_map_fun:
                getDataFromLuaWithFuncion();
                break;
        }
    }


    /**
     * push android map data to lua
     */
    private void pushAndroidMapDataToLua() {
        luaState.getGlobal("getMapDataFromAndroid");
        Map<String, String> mapDta = new HashMap<>();
        mapDta.put("formAndroid1", "I am from Android map1");
        mapDta.put("formAndroid2", "I am from Android map2");
        mapDta.put("formAndroid3", "I am from Android map3");
        LuaUtil.pushDataToLua(luaState, mapDta);
    }


    /**
     *  lua push data to android
     */
    private void getDataFromLua() {
        luaState.getGlobal("pushDataToAndroid");
        luaState.call(0, 0);
    }

    private void getDataFromLuaWithFuncion(){
        luaState.getGlobal("pushMapDataWithFun");
        luaState.call(0,0);
    }


}
