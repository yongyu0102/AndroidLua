package com.example.zhangpeng.androidlua.UI.recyclerview;

import android.content.Context;
import android.support.annotation.RawRes;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * lua文件加载工具
 * Created by zhangfan on 16-8-9.
 */
public class LuaUtils {

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

    private static String readStream(InputStream is) {
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
