package com.maibo.lvyongsheng.xianhui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maibo.lvyongsheng.xianhui.R;
import com.maibo.lvyongsheng.xianhui.TaskPeopleDetailActivity;
import com.maibo.lvyongsheng.xianhui.entity.People;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LYS on 2016/11/25.
 */

public class TaskProgressDetailAdapter extends BaseAdapter{

    List<People> data;
    Context context;
    Activity activity;
    public TaskProgressDetailAdapter(Context context, Activity activity, List<People> people_list){
        this.data=people_list;
        this.context=context;
        this.activity=activity;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=View.inflate(context, R.layout.style_have_right_arrow_image,null);
            holder.name= (TextView) view.findViewById(R.id.name);
            holder.number= (TextView) view.findViewById(R.id.numbers);
            holder.iv_arrow= (ImageView) view.findViewById(R.id.iv_arrow);
            holder.ll_cards_type= (LinearLayout) view.findViewById(R.id.ll_cards_type);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        final People peo=data.get(i);
        holder.name.setText(peo.getName());
        holder.number.setText(peo.getAmount());

        //由于待入界面较简单且数据类型一致，故在此跳转
        if (peo.getAmount().equals("0")){
            holder.iv_arrow.setVisibility(View.INVISIBLE);
        }else{
            holder.ll_cards_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, TaskPeopleDetailActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("name",peo.getName());
                    bundle.putSerializable("details", (Serializable) peo.getDetail());
                    intent.putExtras(bundle);
                    activity.startActivity(intent);
                }
            });
        }

        return view;
    }

    class ViewHolder{
        LinearLayout ll_cards_type;
        TextView name,number;
        ImageView iv_arrow;
    }
}
