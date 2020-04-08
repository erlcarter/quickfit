package com.android.erlcarter.android_quickfit_master.baseconfig;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.SparseArray;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.erlcarter.android_quickfit_master.utils.LogUtil;
import com.badoo.mobile.util.WeakHandler;

/**
 * @author erlcarter
 * @date 2020/02/06
 * @update 2020/02/07 20:23
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * Handle:可以避免内存泄露的Handle库 https://github.com/badoo/android-weak-handler
     */
    public WeakHandler handler;

    /**
     * Toast 间隔时间
     */
    private static long TOAST_INTERVALS = 1000;

    /**
     * Toast 上次显示时间
     */
    private long showToastTime = 0;

    /**
     * 对应FragmentLayout的当前Fragment (使用稀疏数组保存)
     */
    private SparseArray<Fragment> fragmentArray;

    /**
     * LogUtil 日志管理工具
     */
    protected LogUtil log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag,flag);
        //初始化log
        log = LogUtil.getInstance();
        //初始化fragmentArray
        fragmentArray = new SparseArray<>();
        //初始化UI
        initUi();
        //view初始化完成
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                //初始化数据
                initData();
                //初始化Listener
                initListener();
            }
        });
    }

    /**
     * 获取子类的context
     * @return
     */
    protected abstract Context getContext();

    /**
     * 初始化UI
     */
    protected abstract void initUi();

    /**
     * UI初始化完毕 -> 初始化数据
     */
    protected abstract void initData();

    /**
     * 数据初始化完毕 -> 初始化接口
     */
    protected abstract void initListener();

    /**
     * 子类要处理Handler的消息，重写该方法
     * @param msg 要处理的消息
     */
    protected abstract void handleMessage(Message msg);

    /**
     * 初始化Handler(不使用请忽略)
     */
    public void initHandler(){
        handler = new WeakHandler();
    }

    /**
     * 显示Toast(可以在子线程调用)
     */
    public void showToast(final String str){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //避免多次调用
                if (System.currentTimeMillis() - showToastTime > TOAST_INTERVALS){
                    Toast.makeText(BaseActivity.this,str,Toast.LENGTH_LONG).show();
                    //更新显示时间
                    showToastTime = System.currentTimeMillis();
                }
            }
        });
    }

    /**
     * 显示Toast(可以在子线程调用)
     */
    public void showToast(int strId){
        showToast(getString(strId));
    }

    /**
     * 替换Fragment
     * @param frameId frameLayout id
     * @param showFragment 需要显示的Fragment
     */
    public void replaceFragment(int frameId,Fragment showFragment){
        Fragment currentFragment = fragmentArray.get(frameId);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //用于fragment的显示与隐藏
        if (currentFragment!=null && currentFragment!=showFragment){
            if (!showFragment.isAdded()){
                /*隐藏当前的fragment,add下一个*/
                transaction.hide(currentFragment).add(frameId,showFragment).commit();
            }else{
                /*隐藏当前的fragment,显示下一个*/
                transaction.hide(currentFragment).show(showFragment).commit();
            }
            fragmentArray.put(frameId,showFragment);
        }else if (currentFragment == null){
            if (!showFragment.isAdded()){
                /*隐藏当前的fragment,add下一个Activity中*/
                transaction.add(frameId,showFragment).commit();
            }else{
                /*隐藏当前的fragment,显示下一个*/
                transaction.show(showFragment).commit();
            }
            fragmentArray.put(frameId,showFragment);
        }
    }

    /**
     * Activity跳转
     * @param target 需要跳转的Activity
     * @param bundle bundle
     * @param isCloseSelf 是否关闭当前的Activity
     */
    public void openActivity(Class<?> target,Bundle bundle,boolean isCloseSelf){
        Intent intent = new Intent();
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (isCloseSelf){
            finish();
        }
    }

    /**
     * Activity跳转 不需要传值
     * @param target 需要跳转的Activity
     */
    public void openActivity(Class<?> target){
        openActivity(target,null,false);
    }

}
