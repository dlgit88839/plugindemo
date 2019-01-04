package demo.bling.pluginproxydemo.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

/**
 * Created by dongliang on 2019/1/1.
 */

public class Plugin {
    private ClassLoader classLoader;
    private AssetManager assetManager;
    private Resources resources;

    public Plugin(ClassLoader classLoader, AssetManager assetManager, Resources resources) {
        this.classLoader = classLoader;
        this.assetManager = assetManager;
        this.resources = resources;
    }


    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
