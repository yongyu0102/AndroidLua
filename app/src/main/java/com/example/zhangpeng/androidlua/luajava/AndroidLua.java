package com.example.zhangpeng.androidlua.luajava;

import android.util.Log;

import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * author：pz
 * 时间：2018/4/15 :11:43
 */

public class AndroidLua {
    private static final String TAG="Log";

    /**
     *  lua 传递数据给 android
     * @param luaObject
     */
    public void getDataFromLua(LuaObject luaObject){
        LuaState luaState=luaObject.getLuaState();
        Map<String, String> stringStringMap = (Map<String, String>) LuaUtil.parseLuaTable(luaState);
        for (Map.Entry<String, String> entry :stringStringMap.entrySet()){
            Log.d(TAG,entry.getKey());
            Log.d(TAG,entry.getValue());
        }
    }


    public void getDataWithFun(final LuaObject luaObject){
        final Object objectMap = LuaUtil.parseLuaTable(luaObject.getLuaState());
        if (objectMap == null) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    延迟 500ms，模拟网络请求回调成功，哈哈
                    Thread.sleep(500);
                    if(objectMap instanceof ArrayList){
                        ArrayList o = ((ArrayList) objectMap);
                        for(int i=0;i<o.size();i++){
                            Object ob=o.get(i);
                            if(ob!=null&&ob instanceof Map){
                                Map map1= (Map) ob;
                                Object index= map1.get("success");
                                if(index!=null){
                                    int position= (int) index;
                                    LuaState luaState = luaObject.getLuaState();
                                    luaState.rawGetI(LuaState.LUA_REGISTRYINDEX,position);
                                    Map<String,String> map2 = new HashMap<>();
                                    map2.put("网络请求", "网络请求值成功");
                                    LuaUtil.pushDataToLua(luaState, map2);
                                }

                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
