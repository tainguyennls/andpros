package com.example.nguyenhuutai.studentapp;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class BottomNavigationActivity extends AppCompatActivity {

    private TrangChuFragment trangChuFragment;
    private BangTinFragment bangTinFragment;
    private HoSoFragment hoSoFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    trangChuFragment = new TrangChuFragment();
                    replaceFragment(trangChuFragment);
                    break;
                case R.id.navigation_dashboard:
                    bangTinFragment = new BangTinFragment();
                    replaceFragment(bangTinFragment);
                    break;
                case R.id.navigation_notifications:
                    hoSoFragment = new HoSoFragment();
                    replaceFragment(hoSoFragment);
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        replaceFragment(new TrangChuFragment());
    }

    public void replaceFragment(Fragment  fragment){
        FragmentManager fragmentManager  = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_replace_trans,fragment);
        fragmentTransaction.commit();
    }

}
