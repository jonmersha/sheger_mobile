package com.hira.coopay.customer.other_service;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hira.coopay.customer.MainActivity;
import com.hira.coopay.customer.R;



public class ServiceEvent {
    int purtchase_type=1;
    String purchase="Premium";
    int source=1;
    String payment_Source="Wallet";
    MainActivity act;
    View view;
    int defaultlanguge=1;
    OtherService telecomeService;

    public ServiceEvent(View view, OtherService telecomeService) {
        this.view = view;
        this.telecomeService=telecomeService;
    }
    //=====================================Mobile Balance request==============================//
    public void mBalanceRequest(){
        String ussd =String.format("*%s%s",804,Uri.encode("#"));
         telecomeService.callCenter(ussd);
    }
    //=====================================Send Mobile money==============================//
    public void callCenter(){


       final Dialog callSupport=new Dialog(this.telecomeService.view.getContext());
        callSupport.setContentView(R.layout.call_ceneter_layout);

        RadioButton amharicLang=callSupport.findViewById(R.id.amlang);
        RadioButton orolang=callSupport.findViewById(R.id.orolang);


        Button sysSupport=callSupport.findViewById(R.id.system_support);
        Button networkSupport=callSupport.findViewById(R.id.netwoking);
        Button hawalaSupport=callSupport.findViewById(R.id.hawala);
        Button eBankingSupport=callSupport.findViewById(R.id.e_banking);
        amharicLang.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                defaultlanguge=1;
            }


        });
        orolang.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                defaultlanguge=2;

            }
        });

        sysSupport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String ussd =String.format("%s,%s,%s",8518,defaultlanguge,1);
                telecomeService.callCenter(ussd);

            }

        });
        networkSupport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String ussd =String.format("%s,%s,%s",8518,defaultlanguge,2);
                telecomeService.callCenter(ussd);

            }


        });
        hawalaSupport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String ussd =String.format("%s,%s,%s",8518,defaultlanguge,3);
                telecomeService.callCenter(ussd);

            }

        });
        eBankingSupport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String ussd =String.format("%s,%s,%s",8518,defaultlanguge,4);
                telecomeService.callCenter(ussd);

            }


        });
        callSupport.show();

    }

    //=====================================ds TV==============================//
    public void dsTv(){
        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        final View promptView = layoutInflater.inflate(R.layout.dstv, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);


        final EditText pinn_number = (EditText) promptView.findViewById(R.id.pinn_number);
        final EditText ref_number = (EditText) promptView.findViewById(R.id.card_number);
        final EditText amount = (EditText) promptView.findViewById(R.id.card_payamount);



        final RadioButton premium =  promptView.findViewById(R.id.premium);
        final RadioButton compat_plus =  promptView.findViewById(R.id.comact_pluse);
        final RadioButton compact =  promptView.findViewById(R.id.compact);
        final RadioButton family =  promptView.findViewById(R.id.family);
        final RadioButton access =  promptView.findViewById(R.id.access);
        final RadioButton fromWallet =  promptView.findViewById(R.id.from_wallet);
        final RadioButton fromBank =  promptView.findViewById(R.id.from_bank);



        premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purchase="Premium";
                purchaseType(1);
            }
        });
        compat_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purchase="Compact +";
                purchaseType(2);
            }
        });
        compact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purchase="Compact";
                purchaseType(3);
            }
        });
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purchase="Family";
                purchaseType(4);
            }
        });
        access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purchase="Access";
                purchaseType(5);
            }
        });

        fromWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payment_Source="Wallet";
                fundSource(1);
            }
        });

        fromBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payment_Source="Bank Account";
                fundSource(2);
            }
        });













        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String pass=pinn_number.getText().toString();
                        String card_number=ref_number.getText().toString();
                        String payment_amount=amount.getText().toString();
                       // String ussd=String.format("*%s*%s*%s*%s*%s*%s*%s*%s*%s%s",841,pass,4,2,1,5,1,2,card_number,Uri.encode("#"));
                       String ussd=String.format("*%s*%s*%s*%s*%s*%s*%s*%s*%s*%s%s",841,pass,4,2,5,1,purtchase_type,card_number,payment_amount,source,Uri.encode("#"));
                        //telecomeService.callCenter(ussd);

                        String message=String.format("Reference Number:-%S\nAmount:-%S Birr\n Package Type:-%S\n Source Account:-%S",card_number,payment_amount,purchase,payment_Source);
                        midleView(ussd,message);

                    }
                })
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
    public void purchaseType(int type){
        this.purtchase_type=type;

    }
    public void fundSource(int source){
        this.source=source;

    }

    public void midleView(final String ussd ,String msg){

        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.com_confirm_operation, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());

        alertDialogBuilder.setView(promptView);
        TextView tv=promptView.findViewById(R.id.confirmmsg);
        tv.setText(msg);

        alertDialogBuilder.setCancelable(false)

                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                      //  mobileMoney.sendUSSD(ussd);
                        telecomeService.callCenter(ussd);
                    }
                })
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }
}
