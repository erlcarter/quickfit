package com.android.erlcarter.android_quickfit_master.fragment;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.erlcarter.android_quickfit_master.R;
import com.android.erlcarter.android_quickfit_master.view.CircleProgressView;

import yanzhikai.ruler.BooheeRuler;
import yanzhikai.ruler.KgNumberLayout;
import yanzhikai.ruler.Utils.RulerStringUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class RateFragment extends Fragment {

    private View view;
    private CircleProgressView circleProgressView;
    private Window window;
    private WindowManager.LayoutParams wl;
    private PopupWindow popupWindow;
    private Button btn_host_weight_record,btn_save_weight_record;
    private BooheeRuler br_top_head;
    private KgNumberLayout knl_top_head;
    private ImageView iv_popwindow_dismess,iv_rate_back;

    public RateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_rate, container, false);
        initui();
        initdata();
        initListener();
        return view;
    }

 /*   public interface RulerCallback {
        //选取刻度变化的时候回调
        //注意，scale值不一定是实际值，大概值是实际值/factor
        void onScaleChanging(float scale);
    }

    public void onScaleChanging(float scale) {
        if (mRuler != null) {
            tv_scale.setText(RulerStringUtil.resultValueOf(scale, mRuler.getFactor()));
        }
    }*/

    private void initListener() {
        //体重记录
        btn_host_weight_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"体重记录",Toast.LENGTH_SHORT).show();
                showPopupWindow();
            }
        });
        //返回主界面
        iv_rate_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    private void initdata() {
        circleProgressView.setmTxtHint1("已减去");
        circleProgressView.setmTxtHint2("目标00.0");
    }

    private void initui() {
        circleProgressView = view.findViewById(R.id.circleProgressbar);
        btn_host_weight_record = view.findViewById(R.id.btn_host_weight_record);
        iv_rate_back = view.findViewById(R.id.iv_rate_back);
    }

    //弹窗
    private void showPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupWindowView = inflater.inflate(R.layout.popwindow_item_weight_record, null, false);
        popupWindow = new
                PopupWindow(popupWindowView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //引入依附的布局
        View parentView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main, null);
        //相对于父控件的位置
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
        //获取焦点，否则无法点击
        popupWindow.setFocusable(true);
        //背景变暗
        window=getActivity().getWindow();
        wl = window.getAttributes();
//        wl.flags=WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        wl.alpha=0.6f;   //这句就是设置窗口里崆件的透明度的．０.０全透明．１.０不透明．
        window.setAttributes(wl);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT) );
        //加载控件
        br_top_head = popupWindowView.findViewById(R.id.br_top_head);
        knl_top_head = popupWindowView.findViewById(R.id.knl_bottom_head);
        iv_popwindow_dismess = popupWindowView.findViewById(R.id.iv_popwindow_dismess);
        btn_save_weight_record = popupWindowView.findViewById(R.id.btn_save_weight_record);
        knl_top_head.bindRuler(br_top_head);
        //按钮点击事件,此处获取按钮的id不同，因为按钮来自popupwindow的视图
        // 关闭弹窗
        iv_popwindow_dismess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wl.alpha=1.0f;//恢复原样
                window.setAttributes(wl);
                popupWindow.dismiss();
            }
        });
        //拍照按钮
       /* Button takeBtn = popupWindowView.findViewById(R.id.take_photo);
        takeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"拍照设置头像",Toast.LENGTH_SHORT).show();
                //调用拍照函数
                popupWindow.dismiss();
                takePhoto();
            }
        });*/

    }
}
