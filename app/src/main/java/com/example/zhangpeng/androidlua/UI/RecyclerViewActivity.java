package com.example.zhangpeng.androidlua.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;


import com.example.zhangpeng.androidlua.R;
import com.example.zhangpeng.androidlua.luajava.LuaUtil;
import com.example.zhangpeng.androidlua.luajava.LuaJavaHelper;

import org.keplerproject.luajava.LuaState;

/**
 * description:
 * author：pz
 * 时间：2018/3/25 :23:16
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private LuaState luaState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        LuaJavaHelper luaJavaHelper = new LuaJavaHelper();
        luaState=luaJavaHelper.getLuaState();
        luaState.LdoString(LuaUtil.loadAssets(this, "RecyclerViewActivity.lua"));

        luaState.getGlobal( "onCreate");
        luaState.pushJavaObject(this);
        luaState.pushJavaObject(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0));
        luaState.pushJavaObject(savedInstanceState);
        luaState.call(3, 0);


    }
}
