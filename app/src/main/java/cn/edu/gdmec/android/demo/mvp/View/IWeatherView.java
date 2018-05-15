package cn.edu.gdmec.android.demo.mvp.View;

import cn.edu.gdmec.android.demo.WeatherBean;

/**
 * Created by apple on 18/5/15.
 */

public interface IWeatherView {
    void showProgress();
    void hideProgress();
    void showWeatherData(WeatherBean weatherBean);
    void showLoadFaillMsg(Exception e);
}
