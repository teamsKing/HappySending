package com.team.happysending.views.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team.happysending.R;
import com.team.happysending.views.activity.PlaceAnOrderActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 樊、先生 on 2017/1/10.
 */

public class KindPopupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final PlaceAnOrderActivity mActivity;

    //private MyRecyclerLinister mMyRecyclerLinister;
    private String mStr;

    public KindPopupAdapter(PlaceAnOrderActivity activity) {
        mActivity = activity;

    }
    private List<View> mViewList = new ArrayList<>();
    private ArrayList<String> mDatas = new ArrayList<>();
    public ArrayList<String> getDatas(){
        mDatas.add("休闲食品");
        mDatas.add("生鲜果蔬");
        mDatas.add("办公/居家");
        mDatas.add("其他");
        mDatas.add("鲜花");
        mDatas.add("蛋糕");
        mDatas.add("大件物品");

        return mDatas;
    }
   /* //自定义接口
    public interface MyRecyclerLinister {
        void onRecyclerListener(View v, String pos);
    }

    public void setOnMyRecyclerClick(MyRecyclerLinister onMyRecyclerLinister) {
        mMyRecyclerLinister = onMyRecyclerLinister;
    }*/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(mActivity, R.layout.item_txt_layout,null);
        mViewList.add(v);
        return new MyAdapter(v);
    }
    public String getTxt(){
        return mStr;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final MyAdapter holder1 = (MyAdapter)holder;
        final String str = getDatas().get(position);
        holder1.txt.setText(str);
        holder1.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mMyRecyclerLinister.onRecyclerListener(view,str);
                for (View v :mViewList){
                    TextView t = (TextView) v.findViewById(R.id.txt_item_txt);
                    t.setTextColor(Color.BLACK);
                    t.setBackgroundResource(R.drawable.mmp580);
                }
                mStr = getDatas().get(position);
                holder1.txt.setTextColor(Color.WHITE);
                holder1.txt.setBackgroundResource(R.drawable.mmp581);


            }
        });

    }

    @Override
    public int getItemCount() {
        return 7;
    }
    class MyAdapter extends RecyclerView.ViewHolder{
        TextView txt;
        public MyAdapter(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.txt_item_txt);
        }
    }
}
