package com.example.biji;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.biji.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import tally.adapter.RecordPagerAdapter;
import tally.frag_record.IncomeFragment;
import tally.frag_record.OutcomeFragment;
/**可以滑动的支出和收入界面**/
public class RecordActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        //查找控件
        tabLayout = findViewById(R.id.record_tabs);
        viewPager = findViewById(R.id.record_vp);
        //设置ViewPager加载页面，加载fragment
        initPager();
    }

    private void initPager() {
//        初始化ViewPager页面的集合
        List<Fragment> fragmentList = new ArrayList<>();
//        创建收支页面，放在Fragment中
        OutcomeFragment outFrag = new OutcomeFragment(); //支出
        IncomeFragment inFrag = new IncomeFragment(); //收入
        fragmentList.add(outFrag);
        fragmentList.add(inFrag);

//        创建适配器
        RecordPagerAdapter pagerAdapter = new RecordPagerAdapter(getSupportFragmentManager(), fragmentList);
//        设置适配器
        viewPager.setAdapter(pagerAdapter);
        //将TabLayout和ViwePager进行关联
        tabLayout.setupWithViewPager(viewPager);
    }

    /* 点击事件*/
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.record_iv_back:
                finish();
                break;
        }
    }
}
