package com.hira.coopay.customer.mobile;

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

import com.hira.coopay.customer.MainActivity;
import com.hira.coopay.customer.R;
import com.hira.coopay.customer.payments.Payments;


public class MobileMoneyFragment extends Fragment {
    View view;

    Button mbalance;
    Button transfer;
    Button withdaraw;
    Button transaction;
    Button topUP;
    Button toNonRegistered;


    Button merchantPayment;

   // Button billPayment;
 //   Button traffic_penality;

   // Button waterBill;

    public MainActivity act;

    public MobileMoneyFragment() {
    }

    public MobileMoneyFragment fragmentSet(MainActivity main){
        act=main;
        return this;

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.wallet_mobile_fragment,container,false);
        mbalance=view.findViewById(R.id.mbalance);
        transaction=view.findViewById(R.id.transaction);
        transfer =view.findViewById(R.id.mtranfer);

        withdaraw=view.findViewById(R.id.mwithdraw);
        topUP=view.findViewById(R.id.topup);
        toNonRegistered =view.findViewById(R.id.nonregistered);

        merchantPayment =view.findViewById(R.id.merchant_payment);
       // paymentInformation=view.findViewById(R.id.payment_information);
       // billPayment=view.findViewById(R.id.bill_payment);
      //  traffic_penality=view.findViewById(R.id.traffic_pennality);
        //waterBill=view.findViewById((R.id.watter_payment));
//        waterBill.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //return false;
//                new MobileMoneyEvent(view, MobileMoneyFragment.this).watterBill();
//            }
//        });



        withdaraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MobileMoneyEvent(view, MobileMoneyFragment.this).withdrawMoney();
            }
        });


        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MobileMoneyEvent(view, MobileMoneyFragment.this).sendMoney();
            }
        });

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MobileMoneyEvent(view, MobileMoneyFragment.this).showTransaction();
            }
        });
        mbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MobileMoneyEvent(view, MobileMoneyFragment.this).mBalanceRequest();

            }
        });
        topUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // new MobileMoneyEvent(view, MobileMoneyFragment.this).topUp();
                new MobileMoneyEvent(view, MobileMoneyFragment.this).topUpNew();

            }
        });
        toNonRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MobileMoneyEvent(view, MobileMoneyFragment.this).toNonCustomer();
            }
        });
        merchantPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Payments(view, MobileMoneyFragment.this).payToMerchant();
            }
        });


//        billPayment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                DialogFragment dialog=new BillPaymentTab().setValues(act);
//                dialog.setCancelable(true);
//                dialog.show(getChildFragmentManager(),"PaymentMenu");
//
//            }
//        });
//
//
//        return view;
//    }

//        traffic_penality.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                DialogFragment dialog=new BillPaymentTab().setValues(act);
////                dialog.setCancelable(true);
////                dialog.show(getChildFragmentManager(),"PaymentMenu");
//                new MobileMoneyEvent(view, MobileMoneyFragment.this).trafficPenality();
//            }
//        });


        return view;
    }



//traffic_pennality

    public void sendUSSD(String ussd){
        Intent callintent=new Intent(Intent.ACTION_CALL);
        callintent.setData(Uri.parse("tel:" + ussd));
        if(ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            return;
        }
        startActivity(callintent);

    }


}
