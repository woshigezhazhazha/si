package com.tizz.signin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private EditText et1;
    private EditText et2;
    private Button yes;
    private TextView reg;
    private String name,psw;
    private int isStu=-1;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView(){
        reg=(TextView)findViewById(R.id.textView);
        reg.setOnClickListener(this);
        yes=(Button)findViewById(R.id.button);
        yes.setOnClickListener(this);
        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);

    }

    @Override
    public void onClick(View view){
        if(view==reg){
            showSingleAlertDialog(view);
        }
        else if(view==yes){
            if(inputHasNull()){
                Toast.makeText(Login.this, "先完善登录信息！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean inputHasNull(){
        name=et1.getText().toString();
        psw=et2.getText().toString();
        if(name.equals("") || psw.equals(""))
            return true;
        return false;
    }

    public void showSingleAlertDialog(View view){
        isStu=-1;
        final String items[]={"学生","老师"};
        AlertDialog.Builder alertBuilder=new AlertDialog.Builder(this);
        alertBuilder.setTitle("请选择身份");
        alertBuilder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0)
                    isStu=0;
                else if(which==1)
                    isStu=1;
            }
        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(isStu==0){
                    alertDialog.dismiss();
                    Intent intent=new Intent(Login.this, StuRegister.class);
                    startActivity(intent);
                }
                else if(isStu==1){
                    alertDialog.dismiss();
                    Intent intent=new Intent(Login.this,TeacherRegister.class);
                    startActivity(intent);
                }
                else if(isStu==-1){
                    alertDialog.show();
                    Toast.makeText(Login.this,"请先选择身份！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertDialog=alertBuilder.create();
        alertDialog.show();
    }

}
