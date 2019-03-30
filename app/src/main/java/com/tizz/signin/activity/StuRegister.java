package com.tizz.signin.activity;

import android.content.Intent;
import android.os.AsyncTask;
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

import com.tizz.signin.MajorAdapter;
import com.tizz.signin.R;
import com.tizz.signin.utils.ProgressDialogUtils;
import com.tizz.signin.utils.SocketUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
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
    private DataOutputStream outputStream;
    private DataInputStream inputStream;


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
            if(inputHasNull() || !majorChoosen()){
                Toast.makeText(StuRegister.this, "先完善注册信息！", Toast.LENGTH_SHORT).show();
            }
            else{
                if(pswNotSame()){
                    Toast.makeText(StuRegister.this, "前后密码不一致！", Toast.LENGTH_SHORT).show();
                }
                else{
                    new RegTask().execute();
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

    private boolean majorChoosen(){
        if(major.equals("院系专业") || major==null)
            return false;
        return true;
    }

    class RegTask extends AsyncTask<Void,Void,Integer> {

        ProgressDialogUtils pd=new ProgressDialogUtils();

        public void onPreExecute(){
            pd.showProgressDialog(StuRegister.this,"注册","注册中...");
        }

        @Override
        public Integer doInBackground(Void... params){
            try{
                Socket socket=new Socket(SocketUtils.ip,6000);
                socket.setSoTimeout(5*1000);
                if(socket==null)
                    return -1;
                inputStream=new DataInputStream(socket.getInputStream());
                outputStream=new DataOutputStream(socket.getOutputStream());
                outputStream.writeUTF("studentRegister");
                outputStream.writeUTF(name);
                outputStream.writeUTF(psw);
                outputStream.writeUTF(userID);
                outputStream.writeUTF(major);
                String result=inputStream.readUTF();
                if(result.equals("succed"))
                    return 1;
                socket.close();
            }catch (UnknownHostException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        public void onPostExecute(Integer result){
            pd.finishProgressDialog();
            switch (result){
                case 1:
                    Intent intent=new Intent(StuRegister.this,Login.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                    break;
                case -1:
                    Toast.makeText(StuRegister.this,"无法连接网络！",Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    Toast.makeText(StuRegister.this,"注册失败！",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
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
        majors.add("院系专业");

        MajorAdapter majorAdapter=new MajorAdapter(this,R.layout.support_simple_spinner_dropdown_item,majors);
        spMajor.setAdapter(majorAdapter);
        spMajor.setSelection(majors.size()-1,true);
    }

}
