package com.android.erlcarter.android_quickfit_master.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.activity.MemberDetailInfoActivity;
import com.android.erlcarter.android_quickfit_master.utils.AnalysisUtils;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends Fragment {

    private View view;
    private TextView tv_userinfo_uname;
    private CircleImageView iv_userinfo_user_img;
    private CardView cv_user_info;
    private boolean loginState;
    private String loginUserName;

    public UserInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_info, container, false);
        initui();
        initdata();
        initlistener();
        return view;
    }

    private void initlistener() {
        cv_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MemberDetailInfoActivity.class);
                startActivity(intent);//编辑个人信息
            }
        });
    }

    private void setInitState(boolean loginState, String loginUserName) {
        if(loginState){
            //登录后
            //绑定用户名
            tv_userinfo_uname.setText(loginUserName);
        }else{
            Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
        }
    }

    private void initdata() {
        //获取登录状态和当前登录名
        loginState = readLoginState();
        loginUserName = AnalysisUtils.readLoginUserName(getContext());
        //设置初始状态
        setInitState(loginState,loginUserName);
        //设置用户头像
        String path = AnalysisUtils.readUserImage(getContext());
//        System.out.println(path);
        if (!TextUtils.isEmpty(path)) {
            iv_userinfo_user_img.setImageBitmap(BitmapFactory.decodeFile(path));
        }
    }

    private void initui() {
        tv_userinfo_uname = view.findViewById(R.id.tv_userinfo_uname);
        iv_userinfo_user_img = view.findViewById(R.id.iv_userinfo_user_img);
        cv_user_info = view.findViewById(R.id.cv_user_info);
    }

    public boolean readLoginState(){
        SharedPreferences sp = getActivity().getSharedPreferences("loginInfo", MODE_PRIVATE);
        loginState = sp.getBoolean("isLogin", false);
        return loginState;
    }

}
