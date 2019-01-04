package demo.bling.pluginapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import demo.bling.pluginlibrary.BasePluginActivity;

public class PluginMainActivity extends BasePluginActivity {

    private Button btnJump;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin_main);
        btnJump=this.findViewById(R.id.btn_jump);
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                //在Intent中传入 约定的内容 如classname 和启动模式等
                intent.putExtra("className","demo.bling.pluginapp.SecondActivity");
                intent.putExtra("launchMode","standard");
                startActivity(intent);
            }
        });
    }
}
