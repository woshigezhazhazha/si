package com.tizz.signin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Setting extends AppCompatActivity implements View.OnClickListener {

    private TextView title;
    private LinearLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView(){
        title=(TextView)findViewById(R.id.tv_common);
        title.setText("设置");
        back=(LinearLayout)findViewById(R.id.ll_back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_back:
                Setting.this.finish();
                break;
        }
    }
}
