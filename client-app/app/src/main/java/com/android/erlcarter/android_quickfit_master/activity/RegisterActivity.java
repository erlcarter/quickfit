package com.android.erlcarter.android_quickfit_master.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.data.Member;
import com.android.erlcarter.android_quickfit_master.utils.DBUtils;
import com.android.erlcarter.android_quickfit_master.utils.LogUtil;
import com.android.erlcarter.android_quickfit_master.utils.SharedPreferencesUtil;
import com.android.erlcarter.android_quickfit_master.utils.encryptUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author erlcarter
 * @date 2020/02/06
 * @update 2020/03/21 21:56
 */
public class RegisterActivity extends AppCompatActivity {

    private TextView tv_main_title,tv_back;

    private EditText et_reigster_username,et_reigster_psw,et_reigster_psw_again;

    private Button btn_register;

    private String uname,psw,pswagain,path;

    private CircleImageView img_photo;

    private Member member;

    private DBUtils dbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //初始化控件
        initView();
    }

    private void initView() {
        //查找对象
        et_reigster_username  = findViewById(R.id.et_reigster_username);
        et_reigster_psw = findViewById(R.id.et_reigster_psw);
        et_reigster_psw_again = findViewById(R.id.et_reigster_psw_again);
        btn_register = findViewById(R.id.btn_register);
        img_photo = findViewById(R.id.img_photo);
        //设置返回按钮
/*        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });*/
        //注册按钮功能
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入内容
                getInputString();
                //约束
                if (TextUtils.isEmpty(uname)){
                    Toast.makeText(RegisterActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }else  if (TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else  if (TextUtils.isEmpty(pswagain)){
                    Toast.makeText(RegisterActivity.this,"再次密码不能为空",Toast.LENGTH_SHORT).show();
                }else if (!psw.equals(pswagain)){
                    Toast.makeText(RegisterActivity.this,"密码不一致",Toast.LENGTH_SHORT).show();
                }else if (isExitUserName(uname)) {//你的用户名是否已经存在
                    Toast.makeText(RegisterActivity.this,"用户名已存在",Toast.LENGTH_SHORT).show();
                }else {
                    //注册成功
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    //将当前的用户名、密码和头像路径保存进loginInfo.xml的文件中
                    saveRegiserInfo(uname,psw);
                    //保存到数据库
                    member = new Member();
                    saveSQLiteData(member);
                    //跳转到登录界面
                    Intent intent = new Intent();
                    intent.putExtra("username",uname);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
        //头像上传
        img_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.img_photo:
                        startActivityForResult(new Intent(RegisterActivity.this,MyPhotoActivity.class),0x04);
                        break;
                }
            }
        });
    }

    private void saveSQLiteData(Member member) {
        dbUtils = DBUtils.getInstance(this);
        member.setMamberUserName(uname);
        member.setMamberPassWord(psw);
        member.setMemberImagePath(path);
        dbUtils.saveMemberInfo(member);
    }

    private void saveRegiserInfo(String uname, String psw) {
        //MD5加密
        String MD5psw = encryptUtils.encryptStr("MD5",psw);
        //将uname和psw保存到loginInfo.xml文件中
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        //获取编辑器
        SharedPreferences.Editor editor = sp.edit();
        //以username为key，password为value
        editor.putString(uname,MD5psw);
        editor.commit();
    }

    private boolean isExitUserName(String uname) {
        boolean flag = false;
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spName = sp.getString(uname,"");
        if (!TextUtils.isEmpty(spName)){
            flag = true;
        }
        return flag;
    }

    private void getInputString() {
        uname = et_reigster_username.getText().toString().trim();
        psw = et_reigster_psw.getText().toString().trim();
        pswagain = et_reigster_psw_again.getText().toString().trim();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0x04:
                if(resultCode == MyPhotoActivity.FINSH_RESULT&&data!=null){
                    path = data.getStringExtra("image");
                    LogUtil.d("erlcarter"," -----Register------" + path);
                    img_photo.setImageBitmap(BitmapFactory.decodeFile(path));
//                    SharedPreferencesUtil.putData("userImage",path);
                    SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("usesImage",path);//用户头像
                    editor.commit();
                }
                break;
        }
    }

}
