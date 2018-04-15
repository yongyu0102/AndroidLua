-- 注意 lua 存储数据的结构是只有一种就是表
--1 android 给 lua 传递 map 数据
TAG="Log";
function getMapDataFromAndroid(map)
    for k, v in pairs(map) do
        Log(TAG, k)
        Log(TAG, v)
    end
end

--2 lua push data to android
function pushDataToAndroid()
    local luaData={fromLua1="来自 lua 1",fromLua2="来自 lua 2"}
    androidLua:getDataFromLua(luaData);
end



mapDataWithFunction = {
    aaa = 234,
    bbbb = 23232,
    23,
    true,
    {
        success = function(map)
            for k, v in pairs(map) do
                Log(TAG, k)
                Log(TAG, v)
            end
        end,
        failed = function(map)
            for k, v in pairs(map) do
                Log(TAG, k)
                Log(TAG, v)
            end
        end
    },
    {
        23, "sdfsdf",
        { aaa = 234, addd = "23233jjjjsdOK" }
    }
}

 function pushMapDataWithFun()
   androidLua:getDataWithFun(mapDataWithFunction)
 end


function Log(k,v)
    Logger = luajava.bindClass("android.util.Log")
    Logger:d(k, v)
end