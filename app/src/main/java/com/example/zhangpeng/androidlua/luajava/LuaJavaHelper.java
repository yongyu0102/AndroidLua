package com.example.zhangpeng.androidlua.luajava;

import android.util.Log;

import com.example.zhangpeng.androidlua.MainApplication;

import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * good
 * Created by zhangpeng on 2018/3/18.
 */

public class LuaJavaHelper {

    private  LuaState luaState;


    public LuaJavaHelper(){
        luaState=LuaStateFactory.newLuaState();
        luaState.openLibs();
    }

    public LuaState getLuaState(){
        return luaState;
    }


    public void closeLua(){
        if (luaState != null && !luaState.isClosed()) {
            //只能在退出应用时才调用
            luaState.close();
          luaState=null;
        }
    }



}
