package com.tizz.signin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private EditText userName;
    private EditText userId;
    private EditText userPsw;
    private EditText userRepsw;
    private TextView title;
    private String name,psw,repsw,userID;
    private Button yes;
    private RadioButton student;
    private RadioButton teacher;
    private LinearLayout back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView(){
        title=(TextView)findViewById(R.id.tv_common);
        title.setText("注册");
        back=(LinearLayout)findViewById(R.id.ll_back);
        back.setOnClickListener(this);
        userName=(EditText) findViewById(R.id.et_name);
        userId=(EditText) findViewById(R.id.et_idnum);
        userPsw=(EditText) findViewById(R.id.et_psw);
        userRepsw=(EditText)findViewById(R.id.et_repsw);
        yes=(Button)findViewById(R.id.bt_register);
        yes.setOnClickListener(this);
        student=(RadioButton)findViewById(R.id.rt_student);
        teacher=(RadioButton)findViewById(R.id.rb_teacher);
    }

    @Override
    public void onClick(View view){
        if(view==yes){
            if(inputHasNull()){
                Toast.makeText(Register.this, "先完善注册信息！", Toast.LENGTH_SHORT).show();
            }
            else{
                if(pswNotSame()){
                    Toast.makeText(Register.this, "前后密码不一致！", Toast.LENGTH_SHORT).show();
                }
                else if(!isRadiobtnChecked()){
                    Toast.makeText(Register.this,"请选择身份！",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(student.isChecked()){
                        Toast.makeText(Register.this,"学生",Toast.LENGTH_SHORT).show();
                    }
                    else if(teacher.isChecked()){
                        Toast.makeText(Register.this,"老师",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        else if(view==back){
            Register.this.finish();
        }
    }

    private boolean inputHasNull(){
        name=userName.getText().toString();
        psw=userPsw.getText().toString();
        repsw=userRepsw.getText().toString();
        userID=userId.getText().toString();
        if(name.equals("") || psw.equals("")  || repsw.equals("") ||userID.equals(""))
            return true;
        return false;
    }

    private boolean pswNotSame(){
        if(psw.equals(repsw))
            return false;
        return true;
    }

    private boolean isRadiobtnChecked(){
        if(student.isChecked() || teacher.isChecked())
            return true;
        return false;
    }

}
