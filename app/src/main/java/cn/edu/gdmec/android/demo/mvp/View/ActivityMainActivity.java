package cn.edu.gdmec.android.demo.mvp.View;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TimerTask;

import cn.edu.gdmec.android.demo.R;
import cn.edu.gdmec.android.demo.WeatherBean;
import cn.edu.gdmec.android.demo.mvp.presenter.WeatherPresenter;

/**
 * Created by apple on 18/5/15.
 */

public class ActivityMainActivity extends Activity implements IWeatherView, View.OnClickListener{
    private TextView tvWeather;
    private TextView tvWeatherYesterday;
    private ProgressDialog progressDialog;
    private WeatherPresenter presenter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_search).setOnClickListener(this);
        findViewById(R.id.btn_beijing_search).setOnClickListener(this);
        tvWeather=(TextView)findViewById(R.id.tv_weather);
        tvWeatherYesterday=(TextView)findViewById(R.id.tv_weather_yesterday);
        presenter=new WeatherPresenter(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_search:
                presenter.loadWeather("广州");
                break;
            case R.id.btn_beijing_search:
                presenter.loadWeather("北京");
                break;
        }

    }

    @Override
    public void showProgress() {
        if (progressDialog !=null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        progressDialog=ProgressDialog.show(ActivityMainActivity.this,
                "","正在获取");

    }

    @Override
    public void hideProgress() {
        if (progressDialog!=null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }

    }

    @Override
    public void showWeatherData(final WeatherBean weatherBean) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                if (weatherBean.getStatus()==304){
                    Toast.makeText(ActivityMainActivity.this,weatherBean.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }else {
                    tvWeather.setText("城市:"+weatherBean.getCity()+"日期:"+weatherBean.getData()
                    +"温度"+weatherBean.getData().getWendu());
                    tvWeatherYesterday.setText("昨天天气"+weatherBean.getData().getYesterday().getLow()+
                            "b"+weatherBean.getData().getYesterday().getHigh());
                }
            }
        });

    }

    @Override
    public void showLoadFaillMsg(final Exception e) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                tvWeather.setText("加载数据失败："+ e.toString());
            }
        });

    }
}
