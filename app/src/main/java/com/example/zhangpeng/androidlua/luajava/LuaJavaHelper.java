package com.example.zhangpeng.androidlua.luajava;

import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

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


    public void registerObject(Object object,String name) {
        try {
            luaState.pushObjectValue(object);
            luaState.setGlobal(name);
        } catch (LuaException e) {
            e.printStackTrace();
        }
    }


    public void closeLua(){
        if (luaState != null && !luaState.isClosed()) {
            //只能在退出应用时才调用
            luaState.close();
          luaState=null;
        }
    }




}
