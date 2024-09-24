package com.hira.coopay.customer.payments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.hira.coopay.customer.R;
import com.hira.coopay.customer.mobile.MobileMoneyFragment;


public class Payments {
    View view;
    MobileMoneyFragment mobileMoney;

    public Payments(View view, MobileMoneyFragment mobileMoneyFragment) {

        this.view=view;
        this.mobileMoney=mobileMoneyFragment;
        
    }

    public void payInformation() {
        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.payment_merchant, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);

        final EditText pin = (EditText) promptView.findViewById(R.id.pinnumber);
        final EditText merchantId = (EditText) promptView.findViewById(R.id.merchant_id);
        final EditText invoiceNumber = (EditText) promptView.findViewById(R.id.invoice_number);

        final EditText payAmmount = (EditText) promptView.findViewById(R.id.paymount);

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String PIN=pin.getText().toString();
                        String merchantIds=merchantId.getText().toString();
                        String invoiceNumbers=invoiceNumber.getText().toString();
                        if(invoiceNumbers.length()<4)
                        {
                            invoiceNumbers="121212";

                        }
                        int mamountToPay=0;
                        try {
                            mamountToPay = Integer.parseInt(payAmmount.getText().toString());
                        }catch (Exception e){

                        }
                        String ussd=String.format("*%s*%s*%s*%s*%s*%s*%s%s",841,PIN,4,1,merchantIds,invoiceNumbers,mamountToPay,Uri.encode("#"));
                        mobileMoney.sendUSSD(ussd);
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

    public void payToMerchant() {

        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.payment_merchant, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);

        final EditText pin = (EditText) promptView.findViewById(R.id.pinnumber);
        final EditText merchantId = (EditText) promptView.findViewById(R.id.merchant_id);
        final EditText invoiceNumber = (EditText) promptView.findViewById(R.id.invoice_number);
        final EditText payAmmount = (EditText) promptView.findViewById(R.id.paymount);



        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String PIN=pin.getText().toString();
                        String merchantIds=merchantId.getText().toString();
                        String invoiceNumbers=invoiceNumber.getText().toString();


                        int mamountToPay=0;
                        try {
                            mamountToPay = Integer.parseInt(payAmmount.getText().toString());
                        }catch (Exception e){

                        }

                        String ussd=String.format("*%s*%s*%s*%s*%s*%s*%s%s",841,PIN,4,1,merchantIds,invoiceNumbers,mamountToPay,Uri.encode("#"));
                        mobileMoney.sendUSSD(ussd);

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
