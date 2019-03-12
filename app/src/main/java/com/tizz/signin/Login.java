package com.tizz.signin;

import android.content.Intent;
import android.os.Bundle;
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
            Intent intent=new Intent(Login.this,Register.class);
            startActivity(intent);
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

}
