package demo.bling.pluginlibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class BasePluginActivity extends Activity implements IActivityProxy{

    protected Activity pluginContext;
    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base_plugin);


    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRestart() {

    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {

    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {

    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onPause() {

    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {

    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestory() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void attachContext(Context context) {
        this.pluginContext=(Activity) context;

    }

    @Override
    public <T extends View> T findViewById(int id) {
        return pluginContext.findViewById(id);
    }

    @Override
    public void setContentView(int res) {
        pluginContext.setContentView(res);
    }

    @Override
    public WindowManager getWindowManager() {
        return pluginContext.getWindowManager();
    }

    @Override
    public void startActivity(Intent intent) {
        pluginContext.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        pluginContext.startActivityForResult(intent, requestCode);
    }
}
