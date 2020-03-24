package com.android.erlcarter.android_quickfit_master.baseconfig;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.erlcarter.android_quickfit_master.utils.LogUtil;

/**
 * @author erlcarter
 * @date 2020/02/06
 * @update todo
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 对应的activity
     * */
    protected BaseActivity baseActivity;

    /**
     * 对应FrameLayout当前显示的Fragment
     * */
    private SparseArray<android.app.Fragment> fragmentArray;

    /**
     * LogUtil 日志管理工具
     * */
    protected LogUtil logUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = (BaseActivity) getActivity();
        fragmentArray = new SparseArray<android.app.Fragment>();
        logUtil = baseActivity.log;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(initLayoutRes(),container,false);
        initUi(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    /**
     *用于返回布局文件ID
     * @return ID
     */
    protected abstract int initLayoutRes();

    /**
     *UI初始化
     * @param view
     */
    protected abstract void initUi(View view);

    /**
     *数据初始化
     */
    protected abstract void initData();

    /**
     *初始化接口
     */
    protected abstract void initListener();

    /**
     * 显示Toast(可以在子线程调用)
     * @param str
     */
    public void showToast(String str){
        baseActivity.showToast(str);
    }

    /**
     * 显示Toast(可以在子线程调用)
     * @param strId
     */
    public void showToast(int strId){
        baseActivity.showToast(strId);
    }

    /**
     * 替换fragment
     * @param frameId
     * @param showFragment
     */
    public void replaceFragment(int frameId, android.app.Fragment showFragment){
        Fragment currentFragment = fragmentArray.get(frameId);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //用于fragment的显示与隐藏
        if(currentFragment != null && currentFragment!=showFragment){
            if (!showFragment.isAdded()){
                /*隐藏当前fragment,add下一个Activity*/
                transaction.hide(currentFragment).add(frameId, showFragment).commit();
            }else {
                /*隐藏当前的fragment,显示下一个*/
                transaction.hide(currentFragment).show(showFragment).commit();
            }
            fragmentArray.put(frameId,showFragment);
        }else if (currentFragment == null){
            if (!showFragment.isAdded()){
                /*隐藏当前fragment,add下一个Activity*/
                transaction.hide(currentFragment).add(frameId,showFragment).commit();
            }else{
                /*隐藏当前的fragment,显示下一个*/
                transaction.hide(currentFragment).show(showFragment).commit();
            }
            fragmentArray.put(frameId,showFragment);
        }
    }

    /**
     * Activity跳转
     * @param target
     * @param bundle
     * @param isCloseSelf
     */
    public void openActivity(Class<?> target,Bundle bundle,Boolean isCloseSelf){
        baseActivity.openActivity(target,bundle,isCloseSelf);
    }

    /**
     * Activity跳转(不需要传值)
     * @param target
     */
    public void openActivity(Class<?> target){
        baseActivity.openActivity(target,null,false);
    }

}
