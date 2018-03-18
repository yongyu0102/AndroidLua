package com.example.zhangpeng.androidlua.luajava;

import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

/**
 * good
 * Created by zhangpeng on 2018/3/18.
 */

public class LuaJava {

    private  LuaState luaState;

    private static final class  LuaJavaHolder{
        private static  LuaJava LUA_JAVA=new LuaJava();
    }

    private LuaJava(){
        luaState=LuaStateFactory.newLuaState();
        luaState.openLibs();
    }


    public static LuaJava getLuaJavaInstance(){
        return LuaJavaHolder.LUA_JAVA;
    }

    public  LuaState getLuaState(){
        return  luaState;
    }

    public void closeLua(){
        if (luaState != null && !luaState.isClosed()) {
            //只能在退出应用时才调用
            luaState.close();
            LuaJavaHolder.LUA_JAVA=null;
        }
    }



}
