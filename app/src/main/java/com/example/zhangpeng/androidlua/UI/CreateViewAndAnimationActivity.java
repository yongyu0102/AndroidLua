package com.example.zhangpeng.androidlua.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhangpeng.androidlua.R;
import com.example.zhangpeng.androidlua.luajava.LuaJavaHelper;

import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaState;

public class CreateViewAndAnimationActivity extends BaseActivity implements View.OnClickListener {
    private Button btnCreateView;
    private LinearLayout llParent;
    private Button btnStartAnimation;

    private LuaState mLuaState;
    private LuaJavaHelper luaJavaHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLua();
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_create_view_and_animation;
    }

    private void initLua(){
        luaJavaHelper = new LuaJavaHelper();
        mLuaState=luaJavaHelper.getLuaState();
        LuaUtil.doStringFromAssets("create_view_animation_activity.lua",mLuaState);
    }

    @Override
    protected void initView() {
        llParent = (LinearLayout) findViewById(R.id.ll_parent);
        btnCreateView = (Button) findViewById(R.id.btn_create_view);
        btnStartAnimation = (Button) findViewById(R.id.btn_start_animation);
    }

    @Override
    protected void initListener() {
            btnCreateView.setOnClickListener(this);
            btnStartAnimation.setOnClickListener(this);
    }

   private void createTextViewByLua(){
       // 将 lua createTextViewByLua 函数压如入栈顶
       mLuaState.getGlobal("createTextViewByLua");
       //将 lua 函数需要参数压栈
       mLuaState.pushJavaObject(getApplicationContext());
       mLuaState.pushJavaObject(llParent);
       mLuaState.pushString("android 传递到 lua 的数据");
       //调用 createTextViewByLua 函数，该函数共需要 3 个参数，并且有一个返回值
       mLuaState.call(3, 1);
       try {
//           取出 lua 返回值 即 返回的 TextView,-1 位置为栈顶
           TextView tv = (TextView) mLuaState.toJavaObject(-1);
           tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
       } catch (LuaException e) {
           e.printStackTrace();
       }
   }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_create_view :
                createTextViewByLua();
                break;
            case R.id.btn_start_animation :
                startAnimationByLua();
                break;
        }
    }

    private void startAnimationByLua() {
       mLuaState.getGlobal("startAnimation");
       mLuaState.pushJavaObject(btnStartAnimation);
       mLuaState.call(1,0);
    }
}
