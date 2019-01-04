package demo.bling.pluginlibrary;

import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by dongliang on 2019/1/1.
 */

public interface IActivityProxy extends Iproxy {

    void onCreate(Bundle bundle);

    void onRestart();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState);

    void onDestory();


}
