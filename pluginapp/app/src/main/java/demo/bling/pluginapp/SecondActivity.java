package demo.bling.pluginapp;

import android.os.Bundle;

import demo.bling.pluginlibrary.BasePluginActivity;

public class SecondActivity extends BasePluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
