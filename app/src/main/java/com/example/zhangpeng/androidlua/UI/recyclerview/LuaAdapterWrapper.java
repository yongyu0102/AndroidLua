package com.example.zhangpeng.androidlua.UI.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * description:
 * author：pz
 * 时间：2018/3/25 :23:16
 */
public class LuaAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    LuaRecyclerAdapter adapter;

    public LuaAdapterWrapper(LuaRecyclerAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        adapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount();
    }

//    @Override
//    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
//        adapter.getItemViewType(position);
//    }

}
