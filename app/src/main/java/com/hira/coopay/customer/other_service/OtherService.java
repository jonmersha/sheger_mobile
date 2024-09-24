package com.hira.coopay.customer.other_service;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.hira.coopay.customer.R;


public class OtherService extends Fragment {
    View view;
    Button mbalance, callaCeenter,dstv;


    public OtherService() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.telecom_service,container,false);

        mbalance=view.findViewById(R.id.mbalance);
        callaCeenter=view.findViewById(R.id.call_center_button);
        dstv=view.findViewById(R.id.btn_ds_tv);


        mbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ServiceEvent(view, OtherService.this).mBalanceRequest();

            }
        });
        callaCeenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ServiceEvent(view, OtherService.this).callCenter();

            }
        });
        dstv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ServiceEvent(view, OtherService.this).dsTv();
            }
        });

        return view;
    }




    public void callCenter(String ussd){
        Intent callintent=new Intent(Intent.ACTION_CALL);
        callintent.setData(Uri.parse("tel:" + ussd));
        if(ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            return;
        }
        startActivity(callintent);

    }




}
