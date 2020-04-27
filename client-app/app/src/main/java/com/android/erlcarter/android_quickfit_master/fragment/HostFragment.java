package com.android.erlcarter.android_quickfit_master.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.activity.FoodRecordActivity;
import com.android.erlcarter.android_quickfit_master.activity.WeightRecordActivity;
import com.android.erlcarter.android_quickfit_master.utils.AnalysisUtils;
import com.android.erlcarter.android_quickfit_master.utils.SharedPreferencesUtil;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HostFragment extends Fragment {

    private RelativeLayout rl_food_reord;
    private CardView cv_host_userinfo_show;
    private CircleImageView iv_host_user_img;
    private TextView tv_host_user_uid;
    private View view;
    private boolean loginState;
    private String loginUserName;

    public HostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_host, container, false);
        initui();//初始化UI
        initData();//初始化数据
        initLinstener();//初始化监听器
        return view;
    }

    private void initui(){
        cv_host_userinfo_show = view.findViewById(R.id.cv_host_userinfo_show);
        rl_food_reord = view.findViewById(R.id.rl_food_reord);
        tv_host_user_uid = view.findViewById(R.id.tv_host_user_uid);
        iv_host_user_img = view.findViewById(R.id.iv_host_user_img);
    }

    private void initData(){
        //获取登录状态和当前登录名
        loginState = readLoginState();
        loginUserName = AnalysisUtils.readLoginUserName(getContext());
        //设置初始状态
        setInitState(loginState,loginUserName);
        //设置用户头像
        String path = AnalysisUtils.readUserImage(getContext());
//        System.out.println(path);
        if (!TextUtils.isEmpty(path)) {
            iv_host_user_img.setImageBitmap(BitmapFactory.decodeFile(path));
        }
    }

    private void initLinstener(){
        cv_host_userinfo_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WeightRecordActivity.class);
                startActivity(intent);//跳转到体重记录
            }
        });
        rl_food_reord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FoodRecordActivity.class);
                startActivity(intent);//跳转到食物记录
            }
        });
    }

    public boolean readLoginState(){
        SharedPreferences sp = getActivity().getSharedPreferences("loginInfo", MODE_PRIVATE);
        loginState = sp.getBoolean("isLogin", false);
        return loginState;
    }

    private void setInitState(boolean loginState, String loginUserName) {
        if(loginState){
            //登录后
            //绑定用户名
            tv_host_user_uid.setText(loginUserName);
        }else{
            Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
        }
    }

}
