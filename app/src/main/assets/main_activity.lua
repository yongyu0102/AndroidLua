

--启动 CreateViewActivity
function startCreateViewActivity(context)
    --创建一个新java对象,同时返回一个真正的java对象的引用
    intent = luajava.newInstance("android.content.Intent")
    --第一個參數是類名字，其他參數是這個類的構造函數需要的參數
    componentName = luajava.newInstance("android.content.ComponentName","com.example.zhangpeng.androidlua","com.example.zhangpeng.androidlua.UI.CreateViewAndAnimationActivity")
    intent:setFlags(intent.FLAG_ACTIVITY_NEW_TASK)
    intent:setComponent(componentName)
    context:startActivity(intent)
end




