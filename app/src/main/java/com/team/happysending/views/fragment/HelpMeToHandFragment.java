package com.team.happysending.views.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shitou.googleplay.lib.randomlayout.StellarMap;
import com.team.happysending.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by zhaoshihao on 2017/2/23.
 */

    public class HelpMeToHandFragment extends Fragment {
        private StellarMap stellarMap;
        private ArrayList<String> list = new ArrayList<String>();
        private View view;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.sjshu, null);
            return view;

        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            // 简单的设置要显示的文字源
            for (int i = 0; i < 11; i++) {
                list.add("模拟数据"+i);
            }
            Log.d("TAG",list.size()+"");
            stellarMap = new StellarMap(getActivity());
            // 1.设置内部的TextView距离四周的内边距
            int padding = 15;
            stellarMap.setInnerPadding(padding, padding, padding, padding);
            stellarMap.setAdapter(new StellarMapAdapter());
            // 设置默认显示第几组的数据
            stellarMap.setGroup(0, true);// 这里默认显示第0组
            // 设置x和y方向上的显示的密度
            stellarMap.setRegularity(15, 15);// 如果值设置的过大，有可能造成子View摆放比较稀疏

            // 把fragment显示至界面,new出fragment对象
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.f1);
            fl.addView(stellarMap);
            final TextView t  = (TextView) view.findViewById(R.id.jp);
            final TextView yy=(TextView)view.findViewById(R.id.yy);
            final EditText jp=(EditText)view.findViewById(R.id.wz);
            final InputMethodManager s = (InputMethodManager) jp.getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG",s.toString());
                    s.showSoftInput(jp,0);
                    if(t.getText().toString().equals("键盘")){
                        yy.setVisibility(View.GONE);
                        jp.setVisibility(View.VISIBLE);
                        t.setText("语音");
                        s.showSoftInput(jp,0);
                    }else if(t.getText().toString().equals("语音")){
                        yy.setVisibility(View.VISIBLE);
                        jp.setVisibility(View.GONE);
                        t.setText("键盘");

                        try {
                            //获取输入法的服务
                            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            //判断是否在激活状态
                            if (imm.isActive()) {
                                //隐藏输入法!!,
                                imm.hideSoftInputFromWindow(jp.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                            }

                        } catch (Exception e) {
                        } finally {
                        }
                    }


                }
            });
        }

        class StellarMapAdapter implements StellarMap.Adapter {
            /**
             * 返回多少组数据
             */
            @Override
            public int getGroupCount() {
                return 1;
            }

            /**
             * 每组多少个数据
             */
            @Override
            public int getCount(int group) {
                return 11;
            }

            /**
             * group: 当前是第几组 position:是当前组的position
             */
            @Override
            public View getView(int group, final int position, View convertView) {
                final TextView textView = new TextView(getActivity());
                // 根据group和组中的position计算出对应的在list中的位置
                int listPosition = group * getCount(group) + position;
                textView.setText(list.get(listPosition));

                // 1.设置随机的字体大小(随机大小)
                Random random = new Random();
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                        random.nextInt(15) + 14);// 14-29
                // 2.上色，设置随机的字体颜色
                // 如果三原色的值过大会偏白色，过小会偏黑色，所以应该随机一个中间的颜色的值
                int red = random.nextInt(150) + 50;// 50-199
                int green = random.nextInt(150) + 50;// 50-199
                int blue = random.nextInt(150) + 50;// 50-199
                int textColor = Color.rgb(red, green, blue);// 在rgb三原色的基础上混合出一种新的颜色
                textView.setTextColor(textColor);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("TAG",list.get(position));
                    }
                });
                return textView;
            }

            /**
             * 虽然定义了，但是并没有什么乱用
             */
            @Override
            public int getNextGroupOnPan(int group, float degree) {
                return 0;
            }

            /**
             * 当前组缩放完成之后下一组加载哪一组的数据 group： 表示当前是第几组
             */
            @Override
            public int getNextGroupOnZoom(int group, boolean isZoomIn) {
                // 0->1->2->0
                return (group + 1) % getGroupCount();
            }


        }
    }

