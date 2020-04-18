package com.android.erlcarter.android_quickfit_master.baseconfig;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.IdRes;

import java.util.List;

/**
 * @author erlcarter
 * @date 2020/02/06
 * @update todo
 * @param <T>
 */
public abstract class CommonBaseAdapter<T> extends BaseAdapter {

    private List<T> list;
    private Context context;

    public CommonBaseAdapter(List<T> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = View.inflate(context,getLayoutResId(),null);
            viewHolder = new ViewHolder();
            //将控件与ViewHolder绑定
            int[] viewIdArray = bindView();
            for (int viewId : viewIdArray) {
                viewHolder.bindViewById(convertView,viewId);
            }
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        initData(viewHolder,list.get(position),position);
        return convertView;
    }

    /**
     * 获取layout文件ID
     * @return layout文件ID
     */
    protected abstract int getLayoutResId();

    /**
     * 将控件与ViewHolder绑定
     * @return 需要绑定的控件ID数组
     */
    protected abstract int[] bindView();

    /**
     * 绑定显示数据，增加回调监听等功能
     * @param viewHolder
     * @param t
     * @param position
     */
    protected abstract void initData(ViewHolder viewHolder,T t,int position);

    protected class ViewHolder {
        private SparseArray<View> viewSparseArray;

        ViewHolder() {
            this.viewSparseArray = new SparseArray<View>();
        }

        /**
         * 通过id获取View
         * @param id View ID
         * @param <E> View的类型
         * @return 对应的View
         */
        public<E extends View>E getViewById(@IdRes int id){
            return (E) viewSparseArray.get(id);
        }

        /**
         * 通过id获取View并绑定到ViewHolder
         * @param view 布局文件View
         * @param id View ID
         */
        public void bindViewById(View view,@IdRes int id){
            viewSparseArray.put(id,view.findViewById(id));
        }

    }
}
