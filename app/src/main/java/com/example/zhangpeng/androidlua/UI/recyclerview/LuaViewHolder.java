package com.example.zhangpeng.androidlua.UI.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;

/**
 * description:
 * author：pz
 * 时间：2018/3/25 :23:15
 */
public class LuaViewHolder extends RecyclerView.ViewHolder {

    private HashMap<String, View> viewMap = new HashMap<>();

    public void putView(String name, View view){
        viewMap.put(name, view);
    }

    public View getView(String name){
        return viewMap.get(name);
    }

    public LuaViewHolder(View itemView) {
        super(itemView);
    }
}
