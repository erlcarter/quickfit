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
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.bumptech.glide.load.engine.Resource;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
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

    private TextView tv_login_title,tv_login_signature,tv_login_forgotpassword;

    private EditText et_login_username,et_login_password;

    private Button qrb_login;

    private int unameCount,pswCount,selectionStart,selectionEnd;

    private String title,signature;

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
        tv_login_title = (TextView) findViewById(R.id.tv_login_title);
        tv_login_signature = (TextView) findViewById(R.id.tv_login_signature);
        tv_login_forgotpassword = (TextView) findViewById(R.id.tv_login_forgotpassword);
        et_login_username = (EditText) findViewById(R.id.et_login_username);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        qrb_login = (Button) findViewById(R.id.qrb_login);
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
        //用户名
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
        //密码
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
                String uname = et_login_username.getText().toString();
                String upsw = et_login_password.getText().toString();
                //2.对et_login_password加密编码（这里采用MD5加密）
                upsw = encryptUtils("MD5",upsw);
                //3.校验et_login_username 和 et_login_password
                flag = matchUserData(uname,upsw);
                //4.登录成功则跳转至主页
                if (flag) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(context,"用户名或密码不对，请重新输入",Toast.LENGTH_SHORT);
                }
            }
        });
        //忘记密码
        tv_login_forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转至找回密码页面
                Intent intent = new Intent(LoginActivity.this,FindPswActivity.class);
                startActivity(intent);
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
            case 1:
                Toast.makeText(LoginActivity.this,"获取云端数据成功!",Toast.LENGTH_LONG).show();
                membersDataList=(LinkedList<Member>)msg.obj;
                //更新SQLite
                for (int i=0;i<membersDataList.size();i++){
                    DBUtils.getInstance(context).saveMemberInfo(membersDataList.get(i));
                }
                break;
            default:Toast.makeText(LoginActivity.this,"获取云端数据失败!",Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void saveLoginStatus(boolean status, String username) {
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin",status);
        editor.putString("LoginUserName",username);
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
        //获取前台表单数据
        loginDatas.put("memberUserName",uname);
        loginDatas.put("memberPassWord",encryptPSW);
        //校验数据合法性

        //这就是访问后台的局域网地址+项目名+你的SSM框架中的controller方法，得加转义字符
        String url="http:////192.168.1.103:8099//MyNote//MemberController//queryMemberByUnameAndPsw";
        //把要传递的数据格式化成网络传输要求的格式
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : loginDatas.keySet()) {
            //追加表单信息
            builder.add(key, loginDatas.get(key));
        }
        //封装为RequsetBody
        RequestBody requestBody = builder.build();
        OkHttpUtils.sendMessage(url, requestBody, new Callback() {//重写Callback
            @Override
            public void onFailure(Call call, IOException e) {//发送失败
                Message msg=new Message();
                //给个发送失败的的标志给主线程
                msg.what=0;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {//处理发送成功
                String responseData= URLDecoder.decode(response.body().string(), "utf-8");//可能产生编码方式不同造成乱码
                Gson gson=new Gson();
                //用Gson把数据转换为对象数组格式
                LinkedList<Member> members=gson.fromJson(responseData,new TypeToken<LinkedList<Member>>(){}.getType());
                LogUtil.d("取出来的数据1:", members.get(0).toString());
                saveLoginStatus(true,members.get(0).getMamberUserName());
                Message msg=new Message();
                msg.what=1;
                //把对象数组发给主线程，然后更新数据库
                msg.obj=members;
                handler.sendMessage(msg);
            }
        });
        return flag;
    }

    /**
     * password进行明文加密
     * 目前可使用的加密算法：MD5,SHA256
     * @param encryptType
     * @param psw
     */
    protected String encryptUtils(String encryptType,String psw){
        switch (encryptType){
            case "MD5":
                encryptUtils(encryptType,psw);
                break;
            case "sha256":
                encryptUtils(encryptType,psw);
                break;
            default:Toast.makeText(context,"非法加密类型，请联系后台管理人员",Toast.LENGTH_SHORT);
                LogUtil.e("Please use legalEncryptType[MD5,sha256].",new Exception());
                break;
        }
        return psw;
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

}
