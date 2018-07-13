package tools.wpfang.myalarmapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.TooManyListenersException;

public class MainActivity extends AppCompatActivity {
    ToggleButton mAlarmButton;
    AlarmManager alarmManager;
    private static final String NOTIFY_ACTION="tools.wpfang.myalarmapp.receiver.NOTIFY";
    MyReceiver myReceiver;
    IntentFilter intentFilter;
    PendingIntent pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAlarmButton=findViewById(R.id.toggleButton);
        alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
        myReceiver=new MyReceiver();
        intentFilter=new IntentFilter(NOTIFY_ACTION);
        registerReceiver(myReceiver,intentFilter);
        Intent it=new Intent(NOTIFY_ACTION);
        pd=PendingIntent.getBroadcast(this,0,it,PendingIntent.FLAG_UPDATE_CURRENT);
        mAlarmButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                   long triggerTime = SystemClock.elapsedRealtime();
                   long repeatInterval= SystemClock.elapsedRealtime()+2000;
                   alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,triggerTime,repeatInterval,pd);
                    Toast.makeText(MainActivity.this,"Alarm set",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    alarmManager.cancel(pd);
                    Toast.makeText(MainActivity.this,"Alarm off",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
