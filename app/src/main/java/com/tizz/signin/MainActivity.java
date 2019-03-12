package com.tizz.signin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView settings;
    private RelativeLayout rlSettings;
    private Button signIn;
    private TextView currentTime;
    private TextView signinTime;
    private TextView signinNum;

    private static final int UPDATE_TIME=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message message){
            switch (message.what){
                case UPDATE_TIME:
                    currentTime.setText("当前时间:"+TimeUtils.getSysTime());
                    break;
                default:
                    break;
            }
        }
    };

    public void initViews(){
        rlSettings=(RelativeLayout) findViewById(R.id.rl_settings);
        rlSettings.setOnClickListener(this);
        signIn=(Button)findViewById(R.id.btn_signIn);
        signIn.setOnClickListener(this);
        currentTime=(TextView)findViewById(R.id.tv_currentTime);
        signinTime=(TextView)findViewById(R.id.tv_signinTime);
        signinNum=(TextView)findViewById(R.id.tv_signinNum);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(1000);
                        Message message=new Message();
                        message.what=UPDATE_TIME;
                        handler.sendMessage(message);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_signIn:
                signinTime.setText("签到时间:"+TimeUtils.getSysTime());
                signinTime.setTextSize(24);
                currentTime.setTextSize(16);
                signinTime.setVisibility(View.VISIBLE);
                signinNum.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_settings:
                Intent intent=new Intent(MainActivity.this, Setting.class);
                startActivity(intent);
                break;
        }
    }



}
