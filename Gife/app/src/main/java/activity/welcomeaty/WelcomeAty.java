package activity.welcomeaty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yangshenglong.gife.R;

import java.util.Timer;
import java.util.TimerTask;

import activity.MainActivity;

public class WelcomeAty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_aty);


        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(WelcomeAty.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(task, 2500);//设置时间为2.5秒
    }
}
