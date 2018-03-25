package com.example.zhangpeng.androidlua.UI.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * description:
 * author：pz
 * 时间：2018/3/25 :23:15
 */
public class LuaRecyclerView extends RecyclerView {
    public LuaRecyclerView(Context context) {
        super(context);
    }

    public LuaRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LuaRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setAdapter(LuaRecyclerAdapter adapter) {
        setAdapter(new LuaAdapterWrapper(adapter));
    }
}
