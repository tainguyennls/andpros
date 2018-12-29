package com.example.nguyenhuutai.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.nguyenhuutai.studentapp.view.HomeScreen;


public class TrangChuFragment extends Fragment implements View.OnClickListener {

    private LinearLayout llGV;
    private LinearLayout llDK;
    private LinearLayout llDH;
    private LinearLayout llHB;
    private LinearLayout llVL;
    private LinearLayout llTS;


    public TrangChuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_trang_chu, container, false);

        llGV = v.findViewById(R.id.gv);
        llDK = v.findViewById(R.id.dk);
        llDH = v.findViewById(R.id.dh);
        llHB = v.findViewById(R.id.hb);
        llVL = v.findViewById(R.id.vl);
        llTS = v.findViewById(R.id.ts);

        llGV.setOnClickListener(this);
        llDK.setOnClickListener(this);
        llDH.setOnClickListener(this);
        llHB.setOnClickListener(this);
        llVL.setOnClickListener(this);
        llTS.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gv:
                startActivity(new Intent(getActivity(),HomeScreen.class));
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                break;

            case R.id.dk:
                startActivity(new Intent(getActivity(),DoanHoiActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                break;

            case R.id.dh:
                startActivity(new Intent());
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                break;

            case R.id.hb:
                startActivity(new Intent());
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                break;

            case R.id.vl:
                startActivity(new Intent());
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                break;

            case R.id.ts:
                startActivity(new Intent());
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                break;

        }
    }
}
