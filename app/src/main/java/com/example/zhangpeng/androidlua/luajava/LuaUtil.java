package com.example.zhangpeng.androidlua.luajava;

import android.content.Context;
import android.support.annotation.RawRes;
import android.util.Log;

import com.example.zhangpeng.androidlua.MainApplication;

import org.keplerproject.luajava.LuaState;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * good
 * Created by pz on 2018/3/25.
 */

public class LuaUtil {
    private static final String TAG="Log";

    public static Map<String,String> parseLuaMap(LuaState luaState){
        Map<String,String> map=new HashMap<>();
        if(luaState==null)return map;
        int top=luaState.getTop();
        if(top>=3){
//            fun
            int keyType = luaState.type(-1);
            String fun=luaState.typeName(keyType);
//            name
            keyType = luaState.type(-2);
            String funName = luaState.typeName(keyType);
            if("string".equals(funName)||"function".equals(fun)){
//                key
                String key=luaState.toString(-2);
//                fun
                int value=luaState.Lref(LuaState.LUA_REGISTRYINDEX);
                map.put(key,String.valueOf(value));
            }
        }
        return map;
    }


    public static Object parseLuaTable(LuaState luaState){
        Object luaData=null;
//        取出栈里面元素个数
        int top = luaState.getTop();
//        判断栈定是否为表元素
        if (luaState.isTable(top)) {
//            向栈顶部 push 一个 nil 元素
            luaState.pushNil();
            while (luaState.next(top) != 0) {
//                key type
//                -2 位置为 key
                int keyType = luaState.type(-2);
                String keyName=luaState.typeName(keyType);
//                -1 位置为值，即栈顶了
                int valueType = luaState.type(-1);
                String valueName = luaState.typeName(valueType);
                //数组
                if (keyName.equals("number")) {
                    if(luaData==null){
                        luaData=new ArrayList<>();
                    }
                    if(luaData instanceof List){
                        parseTableValueList(luaState, valueName, (List<Object>) luaData);
                    }else {
                        parseTableValueMap(luaState, valueName, (Map<String, Object>) luaData);
                    }

                    //map 键值对
                }
                else if (keyName.equals("string")) {
                    if(luaData==null){
                        luaData = new HashMap<>();
                    }
                    if(luaData instanceof Map){
                        parseTableValueMap(luaState, valueName, (Map<String, Object>) luaData);
                    }else {
                        parseTableValueList(luaState, valueName, (List<Object>) luaData);
                    }
                }
                if(!"function".equals(valueName)){
                    luaState.pop(1);
                }
            }
        }
        return luaData;
    }




    private static void parseTableValueMap(LuaState mLuaState, String valueName, Map<String, Object> objectMap) {
        switch (valueName) {
            case "null":
                break;
            case "number":
                objectMap.put(mLuaState.toString(-2), mLuaState.toNumber(-1));
                break;
            case "string":
                objectMap.put(mLuaState.toString(-2), mLuaState.toString(-1));
                break;
            case "boolean":
                objectMap.put(mLuaState.toString(-2), mLuaState.toBoolean(-1));
                break;
            case "int":
            case "integer":
                objectMap.put(mLuaState.toString(-2), mLuaState.toInteger(-1));
                break;
            case "function" :
                String key=mLuaState.toString(-2);
                int value=mLuaState.Lref(LuaState.LUA_REGISTRYINDEX);
                objectMap.put(key,value);
                break;
            case "table":
                objectMap.put(mLuaState.toString(-2), parseLuaTable(mLuaState));
                break;
            default:
                break;
        }

    }

    private static void parseTableValueList(LuaState mLuaState, String valueName, List<Object> objectMap) {
        switch (valueName) {
            case "null":
                break;
            case "number":
                objectMap.add( mLuaState.toNumber(-1));
                break;
            case "string":
                objectMap.add(mLuaState.toString(-1));
                break;
            case "boolean":
                objectMap.add( mLuaState.toBoolean(-1));
                break;
            case "int":
            case "integer":
                objectMap.add(mLuaState.toInteger(-1));
                break;
            case "function" :
                int value=mLuaState.Lref(LuaState.LUA_REGISTRYINDEX);
                objectMap.add(value);
                break;
            case "table":
                objectMap.add(parseLuaTable(mLuaState)) ;
                break;
            default:
                break;
        }
    }

    /**
     *
     * @param mLuaState lua
     * @param obj map or list data
     */
    public static void pushDataToLua(LuaState mLuaState, Object obj) {
        try {
            int top = mLuaState.getTop();
            if (mLuaState.isFunction(top)) {
                if (obj instanceof Map) {
                    Map<String, Object> map = (Map<String, Object>) obj;
//                    创建一个新表， lua 中存储数据的结果为表
                    mLuaState.newTable();
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        mLuaState.pushString(entry.getKey());
                        mLuaState.pushObjectValue(entry.getValue());
                        mLuaState.setTable(-3);
                    }
                } else if (obj instanceof List) {
                    List<Object> list = (List<Object>) obj;
//                    键表
                    mLuaState.createTable(0, 0);
                    for (int i = 0; i < list.size(); i++) {
                        //将数组中的第 i个数据入栈
                        mLuaState.pushObjectValue(list.get(i));
                        //将刚刚入栈在栈顶的数据存入表中（-2 表示表在栈内的位置，（i+1)表示数据在表中存储位置），同时这个数据会自动从栈顶pop
                        mLuaState.rawSetI(-2, i + 1);
                    }
                }
                //          调用函数，  一个参数，0 个返回值
                mLuaState.call(1, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean doStringFromAssets(String fileName,LuaState luaState){
        try {
            InputStream is = MainApplication.getMainApplication().getAssets().open(fileName);
            String lua=readStream(is);
            return luaState.LdoString(lua) == 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String readStream(InputStream is) {
        try {
            int len;
            byte[] b = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = is.read(b)) != -1) {
                baos.write(b, 0, len);
            }
            return baos.toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ReadStream", "读取文件流失败");
            return "";
        }

    }


    public static String loadRaw(Context context, @RawRes int res) {
        return readStream(context.getResources().openRawResource(res));
    }

    public static String loadAssets(Context context, String fileName) {
        try {
            return readStream(context.getAssets().open(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String loadFile(Context context, File file) {
        try {
            return readStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
