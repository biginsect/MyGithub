package com.lipeng.mygithub.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lipeng.mygithub.R;
import com.lipeng.mygithub.homepage.model.ProjectListUsersBean;

import java.util.List;

/**
 * RecyclerView（用户信息列表）的适配器
 * @author lipeng
 * @date 2017/12/26
 */

public class ProjectsListRecyclerAdapter extends RecyclerView.Adapter<ProjectsListRecyclerAdapter.ProjectListViewHolder>
        implements View.OnClickListener{

    /**监听器，用于列表项的点击事件*/
    private OnItemClickListener mItemListener = null;
    private Context mContext;
    private List<ProjectListUsersBean> usersBeanList;

    public ProjectsListRecyclerAdapter(Context context, List<ProjectListUsersBean> usersBeanList){
        this.mContext = context;
        this.usersBeanList = usersBeanList;
    }

    @Override
    public void onClick(View v) {
        if (mItemListener != null){
            mItemListener.onItemClick(v);
        }
    }

    /**设置监听器*/
    public void setItemListener(OnItemClickListener mItemListener){
        this.mItemListener = mItemListener;
    }

    @Override
    public ProjectListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.homepage_project_lists_item, parent, false);

        return new ProjectListViewHolder(view);
    }

    /**
     * 加载新的数据，更新视图
     * 在更新视图时需保证数据已经获取完全
     * */
    public void addData(List<ProjectListUsersBean> data){
        usersBeanList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 绑定列表项数据内容,在UI上显示结果
     * 未完成
     * */
    @Override
    public void onBindViewHolder(ProjectListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return usersBeanList.size();
    }

    /**
     * ViewHolder
     * */
     class ProjectListViewHolder extends RecyclerView.ViewHolder{
        /**用户头像*/
        SimpleDraweeView userPicture;
        /**用户名*/
        TextView userName;
        /**用户动态信息的日期，以days为单位，超过30 days 用日期格式显示*/
        TextView userUpdateDate;
        /**用户动态信息*/
        TextView userDynamic;

        public ProjectListViewHolder(View view){
            super(view);
            userPicture = view.findViewById(R.id.other_picture);
            userName = view.findViewById(R.id.other_name);
            userUpdateDate = view.findViewById(R.id.other_update_date);
            userDynamic = view.findViewById(R.id.his_dynamic);
        }
    }

    /**
     * 用于RecyclerView 子项的点击事件的监听
     * */
    public interface OnItemClickListener{
        /**
         * 用于页面的跳转
         * @param view 当前点击的view
         * */
        void onItemClick(View view);
    }
}