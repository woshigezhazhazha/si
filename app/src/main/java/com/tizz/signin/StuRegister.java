package com.tizz.signin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StuRegister extends AppCompatActivity implements View.OnClickListener {

    private EditText userName;
    private EditText userId;
    private EditText userPsw;
    private EditText userRepsw;
    private TextView title;
    private String name,psw,repsw,userID;
    private Button yes;
    private LinearLayout back;
    private Spinner spMajor;
    private String major;
    private ArrayList<String> majors=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_register);
        initView();
    }

    private void initView(){
        title=(TextView)findViewById(R.id.tv_common);
        title.setText("学生注册");
        back=(LinearLayout)findViewById(R.id.ll_back);
        back.setOnClickListener(this);
        userName=(EditText) findViewById(R.id.et_name);
        userId=(EditText) findViewById(R.id.et_idnum);
        userPsw=(EditText) findViewById(R.id.et_psw);
        userRepsw=(EditText)findViewById(R.id.et_repsw);
        yes=(Button)findViewById(R.id.bt_register);
        yes.setOnClickListener(this);
        spMajor=(Spinner)findViewById(R.id.sp_major);
        initAdapter();
        spMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                major=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onClick(View view){
        if(view==yes){
            if(inputHasNull()){
                Toast.makeText(StuRegister.this, "先完善注册信息！", Toast.LENGTH_SHORT).show();
            }
            else{
                if(pswNotSame()){
                    Toast.makeText(StuRegister.this, "前后密码不一致！", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if(view==back){
            StuRegister.this.finish();
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

    private void initAdapter(){
        majors.add("哲学");
        majors.add("经济学");
        majors.add("法学");
        majors.add("教育学");
        majors.add("文学");
        majors.add("历史学");
        majors.add("理学");
        majors.add("工学");
        majors.add("农学");
        majors.add("医学");
        majors.add("军事学");
        majors.add("管理学");
        majors.add("艺术学");
        majors.add("院系大类");

        MajorAdapter majorAdapter=new MajorAdapter(this,R.layout.support_simple_spinner_dropdown_item,majors);
        spMajor.setAdapter(majorAdapter);
        spMajor.setSelection(majors.size()-1,true);
    }

}
