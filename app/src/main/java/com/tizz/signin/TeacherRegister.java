package com.tizz.signin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherRegister extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText psw;
    private EditText repsw;
    private Button reg;
    private LinearLayout back;
    private String uName;
    private String uPsw;
    private String uRePsw;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);
        initView();
    }

    private void initView(){
        title=(TextView)findViewById(R.id.tv_common);
        title.setText("教师注册");
        name=(EditText)findViewById(R.id.et_name);
        psw=(EditText)findViewById(R.id.et_psw);
        repsw=(EditText)findViewById(R.id.et_repsw);
        reg=(Button)findViewById(R.id.btn_reg);
        reg.setOnClickListener(this);
        back=(LinearLayout)findViewById(R.id.ll_back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_reg:
                if(inputHasNull()){
                    Toast.makeText(TeacherRegister.this,"先完善注册信息！",Toast.LENGTH_SHORT)
                            .show();
                }
                else{
                    if(pswNotSame()){
                        Toast.makeText(TeacherRegister.this, "前后密码不一致！", Toast.LENGTH_SHORT)
                                .show();
                    }
                }
                break;
            case R.id.ll_back:
                TeacherRegister.this.finish();
                break;
        }
    }

    private boolean inputHasNull(){
        uName=name.getText().toString();
        uPsw=psw.getText().toString();
        uRePsw=repsw.getText().toString();
        if(uName.equals("")||uPsw.equals("")||uRePsw.equals(""))
            return true;
        return false;
    }

    private boolean pswNotSame(){
        if(uRePsw.equals(uPsw))
            return false;
        return true;
    }

}
