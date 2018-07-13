package tools.wpfang.myalarmapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ALARM","alarm record");
        Toast.makeText(context,"receive alarm",Toast.LENGTH_SHORT).show();
    }
}
