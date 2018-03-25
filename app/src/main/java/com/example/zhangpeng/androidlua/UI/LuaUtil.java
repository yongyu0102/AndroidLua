package com.example.zhangpeng.androidlua.UI;

import android.util.Log;

import com.example.zhangpeng.androidlua.MainApplication;

import org.keplerproject.luajava.LuaState;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * good
 * Created by zhangpeng on 2018/3/25.
 */

public class LuaUtil {

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
}
