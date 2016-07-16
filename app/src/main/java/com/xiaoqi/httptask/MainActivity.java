package com.xiaoqi.httptask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpParamsSettings setting = new HttpParamsSettings();
        setting.put("PKCity", "89b2d14f-39de-45ae-9f99-4d6118b22b70");
        setting.setUrl("http://www.fooww.com/service/mobile/GetSyncServerByCity.ashx");
        setting.setMethod(HttpParamsSettings.GET);
        MyAsyncTask myTask = new MyAsyncTask(setting) {

            @Override
            public void onSuccess(Response response) {
                System.out.println("onSuccess");
            }

        };
        myTask.execute();
    }
}
