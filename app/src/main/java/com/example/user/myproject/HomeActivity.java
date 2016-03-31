package com.example.user.myproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HomeActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout tab1Layout, tab2Layout, tab3Layout;
    private int index = 1;
    private FragmentManager fragmentManager;
    private Fragment tab1Fragment, tab2Fragment, tab3Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentManager = getFragmentManager();
        init();
    }

    private void init(){
        tab1Layout = (LinearLayout) findViewById(R.id.tab1_layout);
        tab2Layout = (LinearLayout) findViewById(R.id.tab2_layout);
        tab3Layout = (LinearLayout) findViewById(R.id.tab3_layout);

        tab1Layout.setOnClickListener(this);
        tab2Layout.setOnClickListener(this);
        tab3Layout.setOnClickListener(this);

        setDefaultFragment();
    }

    private void setDefaultFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        tab1Fragment = new ItemFragment();
        transaction.replace(R.id.content_layout, tab1Fragment);
        transaction.commit();
    }

    private void replaceFragment(Fragment newFragemnt){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!newFragemnt.isAdded()){
            transaction.replace(R.id.content_layout, newFragemnt);
            transaction.commit();
        }
        else {
            transaction.show(newFragemnt);
        }
    }

    private void clearStatus(){
        if (index == 1) {
            tab1Layout.setBackgroundColor(getResources().getColor(R.color.tab));
        } else if (index == 2) {
            tab2Layout.setBackgroundColor(getResources().getColor(R.color.tab));
        } else if (index == 3) {
            tab3Layout.setBackgroundColor(getResources().getColor(R.color.tab));
        }
    }

    @Override
    public void onClick(View v) {
        clearStatus();
        switch (v.getId()) {
            case R.id.tab1_layout:
                if (tab1Fragment == null) {
                    //tab1Fragment = new Tab1Fragment();
                    tab1Fragment = new ItemFragment(); //注意import的是android.app.Fragment;
                }
                replaceFragment(tab1Fragment);
                tab1Layout.setBackgroundColor(getResources().getColor(
                        R.color.tab_down));
                index = 1;
                break;
            case R.id.tab2_layout:
                if (tab2Fragment == null) {
                    tab2Fragment = new Tab2Fragment();
                }
                replaceFragment(tab2Fragment);
                tab2Layout.setBackgroundColor(getResources().getColor(
                        R.color.tab_down));
                index = 2;
                break;
            case R.id.tab3_layout:
                if (tab3Fragment == null) {
                    tab3Fragment = new Tab3Fragment();
                }
                replaceFragment(tab3Fragment);
                tab3Layout.setBackgroundColor(getResources().getColor(
                        R.color.tab_down));
                index = 3;
                break;
        }

    }
}
