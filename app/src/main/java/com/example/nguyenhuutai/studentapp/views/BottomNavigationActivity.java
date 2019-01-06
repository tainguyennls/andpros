package com.example.nguyenhuutai.studentapp.views;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.nguyenhuutai.studentapp.R;


public class BottomNavigationActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private NewsFragment newsFragment;
    private AboutFragment hoSoFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    homeFragment = new HomeFragment();
                    replaceFragment(homeFragment);
                    break;
                case R.id.navigation_dashboard:
                    newsFragment = new NewsFragment();
                    replaceFragment(newsFragment);
                    break;
                case R.id.navigation_notifications:
                    hoSoFragment = new AboutFragment();
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
        replaceFragment(new HomeFragment());
    }

    public void replaceFragment(Fragment  fragment){
        FragmentManager fragmentManager  = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_replace_trans,fragment);
        fragmentTransaction.commit();
    }

}
