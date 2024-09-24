package com.hira.coopay.customer.bank;

import android.Manifest.permission;
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


public class CoopBankingFragment extends Fragment {
    Button bnkbalnce,banktowalet,walettobank,ac2ac,waltotherAccount,bnkshortrepoer;
    View view;
    public CoopBankingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.bnk_banking_fragment,container, false);
        bnkbalnce=view.findViewById(R.id.bnkbalance);
        banktowalet=view.findViewById(R.id.bnkbanktowalet);
        walettobank=view.findViewById(R.id.bnkwalettobank);
        ac2ac=view.findViewById(R.id.bnkac2ac);
        waltotherAccount=view.findViewById(R.id.bnktoAother);
        bnkshortrepoer=view.findViewById(R.id.bnkshotrn);

        bnkbalnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CoopBankingEvent(view,CoopBankingFragment.this).bnkBalance();

            }
        });

        banktowalet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CoopBankingEvent(view,CoopBankingFragment.this).bnkWithdraw();

            }
        });
        walettobank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CoopBankingEvent(view,CoopBankingFragment.this).walletToBank();

            }
        });
        waltotherAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CoopBankingEvent(view,CoopBankingFragment.this).walletToBeneficiaries();

            }
        });
        ac2ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CoopBankingEvent(view,CoopBankingFragment.this).AccountToAccount();
            }
        });
        bnkshortrepoer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CoopBankingEvent(view,CoopBankingFragment.this).shortBankTransactionReport();

            }
        });

        return view;
    }
    public void sendUSSD(String ussd){
        Intent callintent=new Intent(Intent.ACTION_CALL);
        callintent.setData(Uri.parse(Uri.parse("tel:" + ussd)+Uri.encode("#")));
        if(ActivityCompat.checkSelfPermission(view.getContext(), permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            return;

        }
        startActivity(callintent);

    }
}
