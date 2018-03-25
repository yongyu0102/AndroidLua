package com.example.zhangpeng.androidlua;

/**
 * good
 * Created by zhangpeng on 2018/3/25.
 */

public class ObjectAnimator {
//    这里简单封装一层，因为 android.animation.ObjectAnimator ofFloat 方法传入参数为不定个数， 而 lua 调用的只需要传入固定两个参数
//    否则直接调用会找不到方法
    public static android.animation.ObjectAnimator ofFloat(Object target, String propertyName,float v1,float v2) {
        return  android.animation.ObjectAnimator.ofFloat(target, propertyName, v1, v2);
    }
}
