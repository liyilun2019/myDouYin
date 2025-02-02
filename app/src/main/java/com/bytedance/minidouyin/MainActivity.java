package com.bytedance.minidouyin;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.bytedance.minidouyin.bean.Feed;
import com.bytedance.minidouyin.db.FeedDBHelper;
import com.bytedance.minidouyin.message.MessageFragment;
import com.bytedance.minidouyin.newtork.FetchFeedService;
import com.bytedance.minidouyin.newtork.FetchFeedThreads;
import com.bytedance.minidouyin.newtork.MyResultReceiver;
import com.bytedance.minidouyin.showVideo.VideoFragment;
import com.bytedance.minidouyin.showVideo.VideoPlayFragment;
import com.bytedance.minidouyin.takeVideo.loginFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int state=-1;
    private static final String TAG = "Main";
    private FloatingActionButton button;

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            if(msg.what== FetchFeedThreads.FETCH_BACK){
                Toast.makeText(MainActivity.this,
                        "refresh to database ok!",Toast.LENGTH_LONG).show();
            }else if(msg.what== MyResultReceiver.RECEIVE_FEED){
                Toast.makeText(MainActivity.this,
                        "refresh to database ok!",Toast.LENGTH_LONG).show();
            }
        }
    }
    private Handler handler = new MyHandler();
    MyResultReceiver resultReceiver;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(state!=0){
                        VideoFragment videoPlayFragment = VideoFragment.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(
                                R.id.palceholder,videoPlayFragment).commit();
                        state=0;
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if(state!=1){
                        MessageFragment videoPlayFragment = MessageFragment.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(
                                R.id.palceholder,videoPlayFragment).commit();
                        state=1;
                    }
                    return true;
                case R.id.navigation_notifications:
                    if(state!=2){
                        loginFragment loginFragment = new loginFragment();
                        getSupportFragmentManager().beginTransaction().replace(
                                R.id.palceholder,loginFragment).commit();
                        state=2;
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setSelectedItemId(R.id.navigation_dashboard);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        MessageFragment videoPlayFragment = MessageFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(
                R.id.palceholder,videoPlayFragment).commit();
        state=1;

        resultReceiver = new MyResultReceiver(handler);

        button = findViewById(R.id.floatingActionRefresh);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FetchFeedThreads.getInstance().fetch_request(handler,MainActivity.this);
                FetchFeedService.startFetchFeed(MainActivity.this,resultReceiver,null);
            }
        });
    }

}
