package com.example.nguyenhuutai.studentapp.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nguyenhuutai.studentapp.AboutFragment;
import com.example.nguyenhuutai.studentapp.PostFragment;


public class FragmentUserProfile extends FragmentStatePagerAdapter {

    private AboutFragment aboutFragment;
    private PostFragment postFragment;
    private String [] tabs = {"Thông tin cá nhân","Đăng bài"};

    public FragmentUserProfile(FragmentManager fm) {
        super(fm);

        aboutFragment = new AboutFragment();
        postFragment = new PostFragment();
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:return aboutFragment;
            case 1:return postFragment;
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}

