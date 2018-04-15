


function createTextViewByLua(context,layout,des,color)
    --创建 TextView 对象
    tv = luajava.newInstance("android.widget.TextView",context)
    --调用方法
    tv:setText(des.."..追加中文")
    layout:addView(tv)
    --负数的负数，这个值为绿色，也就是说二进制取反+1再加上负号
    tv:setTextColor(-16711936)
    return tv
end

function startAnimation(animationView)
    --bindClass 接受一个class类. 然后返回一个对象可以访问该对象的静态知道和该类的方法
    ObjectAnimator = luajava.bindClass("com.example.zhangpeng.androidlua.ObjectAnimator")
    local animator = ObjectAnimator:ofFloat(animationView, "rotation",0,360)
    animator:setDuration(1000)
    animator:start()
end
