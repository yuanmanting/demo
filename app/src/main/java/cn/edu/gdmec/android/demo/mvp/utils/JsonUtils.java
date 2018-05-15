package cn.edu.gdmec.android.demo.mvp.utils;

import com.google.gson.Gson;

import cn.edu.gdmec.android.demo.WeatherBean;

/**
 * Created by apple on 18/5/15.
 */

public class JsonUtils {
    public static WeatherBean getWeather(String res){
        Gson gson=new Gson();
        WeatherBean weatherBean=gson.fromJson(res,WeatherBean.class);
        return weatherBean;
    }

}
