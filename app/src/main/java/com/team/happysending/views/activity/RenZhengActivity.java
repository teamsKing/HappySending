package com.team.happysending.views.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.team.happysending.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 樊、先生 on 2017/2/26.
 * 实名认证类
 */

public class RenZhengActivity extends AppCompatActivity {
    @BindView(R.id.renzheng_backBtn)
    ImageView mRenzhengBackBtn;
    @BindView(R.id.renzheng_etName)
    EditText mRenzhengEtName;
    @BindView(R.id.renzheng_etNum)
    EditText mRenzhengEtNum;
    @BindView(R.id.renzheng_btn)
    Button mRenzhengBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renzheng);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.renzheng_backBtn, R.id.renzheng_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.renzheng_backBtn:
                finish();
                break;
            case R.id.renzheng_btn:
                String name = mRenzhengEtName.getText().toString().trim();
                String num = mRenzhengEtNum.getText().toString().trim();
                break;
        }
    }
}
