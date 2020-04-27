package com.android.erlcarter.android_quickfit_master.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.baseconfig.BaseActivity;
import com.android.erlcarter.android_quickfit_master.data.Colors;
import com.android.erlcarter.android_quickfit_master.data.Member;
import com.android.erlcarter.android_quickfit_master.data.MembersData;
import com.android.erlcarter.android_quickfit_master.data.UserData;
import com.android.erlcarter.android_quickfit_master.utils.DBUtils;
import com.android.erlcarter.android_quickfit_master.utils.LogUtil;
import com.android.erlcarter.android_quickfit_master.utils.OkHttpUtils;
import com.android.erlcarter.android_quickfit_master.utils.SharedPreferencesUtil;
import com.android.erlcarter.android_quickfit_master.utils.encryptUtils;
import com.bumptech.glide.load.engine.Resource;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author erlcarter
 * @date 2020/02/06
 * @update 2020/03/21 21:56
 */

public class LoginActivity extends BaseActivity {

    protected Context context;

    private TextView tv_login_title,tv_login_signature,
            tv_login_forgotpassword,tv_login_reigster;

    private ImageView publish_user_cancel,publish_psw_cancel;

    private EditText et_login_username,et_login_password;

    private Button qrb_login;

    private int unameCount,pswCount,selectionStart,selectionEnd;

    private String title,signature,uname,upsw;

    private Boolean flag,uTextState,pTextState;

    private HashMap<String, Boolean> btnEnable;

    private Colors colorsMap;

    private CharSequence wordNum;

    private MembersData mbd;

    private LinkedList<Member> membersDataList;

    private Map<String,String> loginDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * 获取Context
     * @return this
     */
    @Override
    protected Context getContext() {
        return context = this;
    }

    /**
     * 初始化UI
     */
    @Override
    protected void initUi() {
        //.ToDo 修改样式：沉浸式状态栏
        //.ToDo 添加刷新功能：个性化刷新样式
    }

    /**
     * 初始化Data
     */
    @Override
    protected void initData() {
        //初始化视图对象
        tv_login_title = findViewById(R.id.tv_login_title);
        tv_login_signature = findViewById(R.id.tv_login_signature);
        tv_login_forgotpassword = findViewById(R.id.tv_login_forgotpassword);
        tv_login_reigster = findViewById(R.id.tv_login_reigster);
        publish_user_cancel = findViewById(R.id.publish_user_cancel);
        publish_psw_cancel = findViewById(R.id.publish_psw_cancel);
        et_login_username = findViewById(R.id.et_login_username);
        et_login_password = findViewById(R.id.et_login_password);
        qrb_login = findViewById(R.id.qrb_login);
        //基本信息
        mbd = MembersData.getInstance();
        loginDatas = new HashMap<>();
        btnEnable = new HashMap<>();            //登录框状态
        colorsMap = new Colors(this);  //颜色选择器
        unameCount = 0;                        //username输入总和
        pswCount = 0;                          //password输入总和
        selectionStart = 0;                    //输入开始下标
        selectionEnd = 0;                      //输入结束下标
        flag = false;                          //参数验证标志
        btnEnable.put("uname",false);          //初始化btnEnable - arg1 uname
        btnEnable.put("upsw",false);           //初始化btnEnable - arg2 upsw
        title = mbd.getLogin_title();          //主标题
        signature = mbd.getLogin_signature();  //副标题
        LogUtil.i("UIData",("data:["+"title:"+title+",signatue:"+signature+"]"));
        //修改组件参数
        tv_login_title.setText(title);
        tv_login_signature.setText(signature);
    }

    /**
     * 初始化Listener
     */
    @Override
    protected void initListener() {
        //用户名输入框监听
        et_login_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int end, int count) {
                // null
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int end, int count) {
                wordNum = s;
                if (wordNum.length()==0) {
                   uTextState = false;
                }else{
                    uTextState = true;
                }
                //若文本框内容不为空，修改username为1
                btnEnable.put("uname", uTextState);
//              LogUtil.d("et_login_username onTextChanged State",btnEnable.get("uname").toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!uTextState)setLoginBtnOff(); //通知登录按钮修改状态为禁用
                notifyLoginBtn();
            }
        });
        //密码输入框监听
        et_login_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int end, int count) {
                //null
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int end, int count) {
                wordNum = s;
                if (wordNum.length()==0) {
                    pTextState = false;
                }else{
                    pTextState = true;
                }
                //若文本框内容不为空，修改username为1
                btnEnable.put("upsw", pTextState);
//               LogUtil.d("et_login_username onTextChanged State",btnEnable.get("uname").toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!pTextState) setLoginBtnOff(); //通知登录按钮修改状态为禁用
                notifyLoginBtn();
            }
        });
        //登录按钮
        qrb_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        //1.获取et_login_username 和 et_login_password
                        uname = et_login_username.getText().toString();
                        upsw = et_login_password.getText().toString();
                        //2.校验et_login_username 和 et_login_password
                        flag = matchUserData(uname,encryptUtils.encryptStr("MD5",upsw));
                        //3.登录成功则跳转至主页
                        if (flag) {
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                            //保存登录状态
                            saveLoginStatus(true,uname);
                            //跳转
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            //销毁LoginActivity
                            finish();
                        }else{
                            Toast.makeText(getContext(),"用户名或密码不对，请重新输入",Toast.LENGTH_SHORT);
                            //清空输入
                            et_login_username.setText("");
                            et_login_password.setText("");
                        }
            }
        });
        //注册账号
        tv_login_reigster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转至找回注册页面
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });
        //忘记密码
        tv_login_forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转至找回密码页面
                Intent intent = new Intent(LoginActivity.this,FindPswActivity.class);
                startActivityForResult(intent,2);
            }
        });
        //清除用户输入框
        publish_user_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_login_username.setText("");
            }
        });
        //清除密码输入框
        publish_psw_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_login_password.setText("");
            }
        });
    }

    /**
     * 消息处理器
     * @param msg 要处理的消息
     */
    @Override
    protected void handleMessage(Message msg) {
        //用来处理子线程发来的数据
        switch (msg.what){
            case 0: Toast.makeText(LoginActivity.this, "密码与用户名不一致", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private String readPassword(String username) {
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        return sp.getString(username,"");
    }

    /**
     * 保存用户登录状态
     * @param status
     * @param uname
     */
    private void saveLoginStatus(boolean status, String uname) {
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin",status);//是否登录
        editor.putString("LoginUserName",uname);//用户登录名
        editor.commit();
    }

    /**
     * 检验用户输入的username和password是否符合标准：
     *  1.用户名是否存在
     *  2.密码是否正确
     *  3.检测邮箱是否符合邮箱格式 @xx.com
     * @param uname
     * @param encryptPSW
     */
   protected Boolean matchUserData(String uname,String encryptPSW){
       String spPsw = readPassword(uname);
       Message msg = new Message();
        //检测用户名是否合法
//        System.out.println("spPsw"+spPsw);
//        System.out.println("encryptPSW"+encryptPSW);
       //检测用户名和密码是否匹配
       if (spPsw!= null && !TextUtils.isEmpty(spPsw)&&!encryptPSW.equals(spPsw)) {
           msg.what = 0;
           handleMessage(msg);
       }else if(encryptPSW.equals(spPsw)){
           return flag = true;
       }
        return flag;
    }

    /**
     * .ToDo 待优化
     * 控制登录按钮状态 0：禁用 | 1：启用
     * 满足uname和upsw的值同时为1时，方可唤起该方法
     */
    protected void notifyLoginBtn(){
        if ( btnEnable.get("uname") && btnEnable.get("upsw"))
            setLoginBtnOn();
    }

    /**
     * .ToDo 待优化
     * button style（Blue，White）
     * 启用按钮 on
     */
    protected void setLoginBtnOn(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            qrb_login.setBackground(ContextCompat.getDrawable(this, R.drawable.loginbutton_selected));
            qrb_login.setTextColor(Integer.valueOf(colorsMap.getColorWhite()));
            qrb_login.setEnabled(true);
        }
    }

    /**
     * .ToDo 待优化
     * button style（Gray8，Gray6）
     * 禁用按钮 off
     */
    protected void setLoginBtnOff(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            qrb_login.setBackground(ContextCompat.getDrawable(this,R.drawable.loginbutton));
            qrb_login.setTextColor(Integer.valueOf(colorsMap.getColorGray6()));
            qrb_login.setEnabled(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            String username = data.getStringExtra("username");
            if (!TextUtils.isEmpty(username)){
                et_login_username.setText(username);
                et_login_username.setSelection(username.length());
            }
        }
    }
}
