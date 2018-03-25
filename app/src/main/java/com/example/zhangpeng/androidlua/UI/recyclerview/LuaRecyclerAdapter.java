package com.example.zhangpeng.androidlua.UI.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * description:
 * author：pz
 * 时间：2018/3/25 :23:15
 */
public interface LuaRecyclerAdapter {
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    int getItemCount();
}
