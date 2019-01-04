package demo.bling.pluginproxydemo.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import dalvik.system.DexClassLoader;

/**
 * Created by dongliang on 2019/1/1.
 */

public class PluginManager {
    private static String tag="plugin manager";
    private volatile static PluginManager pluginManager;
    private Context context;
    //可能存在多个插件，所以用pluginMap保存
    private HashMap<String ,Plugin> pluginMap=new HashMap<>();
    private String currentPluginPath;

    public static PluginManager getInstance() {
        if (pluginManager == null) {
            synchronized (PluginManager.class) {
                if (pluginManager == null) {
                    pluginManager = new PluginManager();
                }
            }
        }
        return pluginManager;

    }

    public void init(Context context, String path) {
        if (pluginMap.get(path)!=null){
            Log.e(tag,"该插件已经初始化");
        }
        this.context = context;
        ClassLoader classLoader = createClassLoader(path);
        Plugin plugin = null;
        try {
            AssetManager assetManager = AssetManager.class.newInstance();

            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            if (method != null) {
                method.setAccessible(true);
                method.invoke(assetManager, path);
                Resources resources = new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
                plugin = new Plugin(classLoader, assetManager, resources);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            pluginMap.put(path,plugin);
        }

    }

    private ClassLoader createClassLoader(String path) {
        File output = context.getDir("dex", Context.MODE_PRIVATE);
        ClassLoader classLoader = new DexClassLoader(path, output.getAbsolutePath(), null, context.getClassLoader());
        return classLoader;
    }

    public Plugin getPluginByPath(String path){
       Plugin plugin= pluginMap.get(path);
       if (plugin==null){
           throw new RuntimeException("can't find the plugin by path "+path);
       }
        return plugin;
    }

    public String getCurrentPluginPath() {
        return currentPluginPath;
    }
    public Plugin getCurrentPlugin(){

     return   getPluginByPath(currentPluginPath);

    }

    /**
     *设置当前要使用的插件Apk
     * created by dongliang
     * 2019/1/4    16:52
     */
    public void setCurrentPluginPath(String currentPluginPath) {
        this.currentPluginPath = currentPluginPath;
    }
}
