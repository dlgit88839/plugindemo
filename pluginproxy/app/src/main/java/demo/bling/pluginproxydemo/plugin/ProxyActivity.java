package demo.bling.pluginproxydemo.plugin;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import demo.bling.pluginlibrary.IActivityProxy;
import demo.bling.pluginlibrary.Iproxy;
/**
 * 代理Activity 可创建多个 用来处理launchMode 或者主题不同的Activity
 * created by dongliang
 * 2019/1/4    16:51
 */
public class ProxyActivity extends Activity {
    private String  className;
    private IActivityProxy proxy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        className = intent.getStringExtra("className");
        Class activityClass = null ;
        try {
            activityClass = getClassLoader().loadClass(className);
            Constructor constructor = activityClass.getConstructor();
            proxy  = (IActivityProxy)activityClass.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();

        }

        proxy.attachContext(this);
        proxy.onCreate(savedInstanceState);
    }

    @Override
    public void startActivity(Intent intent) {

      String launchMode= intent.getStringExtra("launchMode");
      if (TextUtils.equals(launchMode,"standard")){
          intent.setClass(this,ProxyActivity.class);
      }
        super.startActivity(intent);
    }

    @Override
    public ClassLoader getClassLoader() {
        ClassLoader classLoader=  PluginManager.getInstance().getCurrentPlugin().getClassLoader();
        if (classLoader==null){
            Log.e("1","classloader null");
        }
        return classLoader==null?super.getClassLoader():classLoader;
    }

    @Override
    public Resources getResources() {
        Resources resources=  PluginManager.getInstance().getCurrentPlugin().getResources();
        return resources==null?super.getResources():resources;
    }

    @Override
    public AssetManager getAssets() {
        AssetManager assetManager=  PluginManager.getInstance().getCurrentPlugin().getAssetManager();
        return assetManager==null?super.getAssets():assetManager;
    }
}
