package com.example.zhangpeng.androidlua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

public class MainActivity extends AppCompatActivity {
    private LuaState mLuaState;//Lua解析和执行由此对象完成
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLua();
    }




    /**
     * 只是在第一次调用，如果升级脚本也不需要重复初始化
     */
    private void initLua(){
        mLuaState = LuaStateFactory.newLuaState();
        mLuaState.openLibs();
        //为了lua能使用系统日志，传入Log
        try {
            //push一个对象到对象到栈中
            mLuaState.pushObjectValue(Log.class);
            //设置为全局变量
            mLuaState.setGlobal("Log");
        } catch (LuaException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
