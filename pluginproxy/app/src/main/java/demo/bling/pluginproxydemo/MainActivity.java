package demo.bling.pluginproxydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import demo.bling.pluginproxydemo.plugin.PluginManager;
import demo.bling.pluginproxydemo.plugin.ProxyActivity;

public class MainActivity extends AppCompatActivity {
   //plugin.apk的存储路径 可根据情况更改
     String path="/sdcard/plugin/plugin.apk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().init(getApplicationContext(),path);
        findViewById(R.id.tv_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PluginManager.getInstance().setCurrentPluginPath(path);
                Intent intent=new Intent(MainActivity.this, ProxyActivity.class);
                //主页面入口className可由服务器返回
                intent.putExtra("className","demo.bling.pluginapp.PluginMainActivity");
                startActivity(intent);
            }
        });
    }
}
