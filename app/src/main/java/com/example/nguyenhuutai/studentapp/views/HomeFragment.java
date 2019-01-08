package com.example.nguyenhuutai.studentapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.nguyenhuutai.studentapp.LoginActivity;
import com.example.nguyenhuutai.studentapp.R;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private LinearLayout llGV;
    private LinearLayout llDK;
    private LinearLayout llVL;
    private LinearLayout llDH;
    private LinearLayout llLG;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_trang_chu, container, false);

        llGV = v.findViewById(R.id.gv);
        llDK = v.findViewById(R.id.dk);
        llVL = v.findViewById(R.id.vl);
        llDH = v.findViewById(R.id.ts);
        llLG = v.findViewById(R.id.lg);

        llGV.setOnClickListener(this);
        llDK.setOnClickListener(this);
        llVL.setOnClickListener(this);
        llDH.setOnClickListener(this);
        llLG.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gv:

                startActivity(new Intent(getActivity(),LecturerActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                break;

            case R.id.dk:
                startActivity(new Intent(getActivity(),GroupActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                break;

            case R.id.vl:
                startActivity(new Intent(getActivity(),JobActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                break;

            case R.id.ts:
                startActivity(new Intent(getActivity(),TeachActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                break;

            case R.id.lg:
                startActivity(new Intent(getActivity(),LoginActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                break;

        }
    }
}
