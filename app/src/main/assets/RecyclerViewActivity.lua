function onCreate(activity, rootView)
    activity:setTitle("使用Lua脚本构建RecyclerView")
    context = activity
    local recyclerView = luajava.newInstance("com.example.zhangpeng.androidlua.UI.recyclerview.LuaRecyclerView", activity)
   -- local recyclerView = luajava.newInstance("android.support.v7.widget.RecyclerView", activity)
    local linearManager = luajava.newInstance("android.support.v7.widget.LinearLayoutManager", activity)
    recyclerView:setLayoutManager(linearManager)
    rootView:addView(recyclerView)

    -- The function createProxy returns a java Object reference that can be used as an implementation of the given interface.
   -- createProxy receives a string that contain the names of the interfaces to be implemented, separated by a comma(,),
    --and a lua object that is the interface implementation.
    -- 这里之所以是自己写一个接口去代理，是因为RecyclerView.Adapter 是一个抽象类，不是接口，无法直接用代理类
    --类去创建，所以自己封装一层接口去实现
    local adapter = luajava.createProxy("com.example.zhangpeng.androidlua.UI.recyclerview.LuaRecyclerAdapter", rec_adapter)
    --错误写法
    --local adapter = luajava.createProxy("android.support.v7.widget.RecyclerView$Adapter", rec_adapter)
    recyclerView:setAdapter(adapter)
end

rec_adapter = {}
function rec_adapter.onCreateViewHolder(parent, viewType)
    local button1 = luajava.newInstance("android.widget.TextView", context)
    button1:setPadding(50, 50, 50, 50)
    button1:setText("根据网络Lua脚本动态生成的button.....一可赛艇")
    --parent:addView(button1)
    local holder = luajava.newInstance("com.example.zhangpeng.androidlua.UI.recyclerview.LuaViewHolder", button1)
    holder:putView("btn1", button1)
    return holder
end

function rec_adapter.onBindViewHolder(holder, position)
    holder:getView("btn1"):setText("yoo" .. position)
end

function rec_adapter.getItemCount()
    return 100
end
