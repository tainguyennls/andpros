package com.example.nguyenhuutai.studentapp.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.models.CheckNetwork;


public class NavActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private NewsFragment newsFragment;
    private AboutFragment aboutFragment;
    private Dialog dialog;
    private CheckNetwork checkNetwork;


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
                    aboutFragment = new AboutFragment();
                    replaceFragment(aboutFragment);
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

         checkNetwork = new CheckNetwork();

         if(!checkNetwork.checkNetworkState(this)){
             showDialog();
         }

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



    @Override
    public void onBackPressed() {
        // do not back
    }

    public void showDialog(){
        dialog = new Dialog(this);
        dialog.setTitle("Attention!");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.network_dialog);
        dialog.show();

        Button btnOk = dialog.findViewById(R.id.ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


}
