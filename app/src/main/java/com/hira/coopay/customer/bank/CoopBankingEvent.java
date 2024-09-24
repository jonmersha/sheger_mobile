package com.hira.coopay.customer.bank;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hira.coopay.customer.R;


public class CoopBankingEvent {
    
    View view;
    CoopBankingFragment coopBankingFragment;

    public CoopBankingEvent(View view, CoopBankingFragment coopBankingFragment) {
        this.view = view;
        this.coopBankingFragment = coopBankingFragment;

    }
    public void bnkBalance(){

        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.com_pin_requst, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);
        final EditText editText = (EditText) promptView.findViewById(R.id.pinnumber);
        final TextView tv=promptView.findViewById(R.id.tvpinid);
        tv.setText(R.string.bank_balance);
        // setup a dialog window
        final String astr="*";
        alertDialogBuilder.setCancelable(false)

                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String pass=editText.getText().toString();
                        String ussd=astr+841+astr+pass+astr+8+astr+1+ Uri.encode("#");
                        coopBankingFragment.sendUSSD(ussd);
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
    //=====================================================bank to wallet money=================================

    public void bnkWithdraw() {
        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.bnk_bank_to_mm, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);
        final EditText userpin = (EditText) promptView.findViewById(R.id.pinnumber);
        final EditText withdrawamount = (EditText) promptView.findViewById(R.id.bwamount);
        final EditText coment = (EditText) promptView.findViewById(R.id.bwcomment);
        // setup a dialog window

        final String astr="*";
        alertDialogBuilder.setCancelable(false)

                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String pass=userpin.getText().toString();

                        int withdrawAmount=0;

                        try {
                            withdrawAmount= Integer.parseInt(withdrawamount.getText().toString());

                        }
                        catch (Exception e){

                        }
                        String comment=coment.getText().toString();

                        String ussd=astr+841+astr+pass+astr+8+astr+3+astr+withdrawAmount+Uri.encode("#");

                        String message= withdrawAmount+"Birr will be deducted from Your bank account and \n to Continue press Ok or press cancel to Stop";
                        confirmationMessage(ussd,message);
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
   ///====================================================Wallet to own account Transfer==========================

    public void walletToBank() {
        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.wallet_to_your_bank, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);
        final EditText userpin = (EditText) promptView.findViewById(R.id.pinnumber);
        final EditText withdrawamount = (EditText) promptView.findViewById(R.id.wbamount);
        final EditText coment = (EditText) promptView.findViewById(R.id.wbcomment);
        // setup a dialog window

        final String astr="*";
        alertDialogBuilder.setCancelable(false)

                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String pass=userpin.getText().toString();

                        int tranferAmount= 0;
                        try{
                            tranferAmount= Integer.parseInt(withdrawamount.getText().toString());
                        }catch (Exception e){}
                        String comment=coment.getText().toString();
                        String ussd=astr+841+astr+pass+astr+8+astr+2+astr+tranferAmount+Uri.encode("#");
                        String message= tranferAmount+" Birr will be deducted from your mobile Account and \n to Continue press Ok or press cancel to Stop";
                        confirmationMessage(ussd,message);
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
    //=============================================MMT to other Account holder transfer=============

    public void walletToBeneficiaries() {
        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.bnk_wallet_to_bank_tranfer, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);

        final EditText userpin = (EditText) promptView.findViewById(R.id.mmtpinnumber);
        final EditText tranferamount = (EditText) promptView.findViewById(R.id.mmtamount);
        final EditText beneficiries = (EditText) promptView.findViewById(R.id.mmtbeneficiery);

        final EditText coment = (EditText) promptView.findViewById(R.id.mmtcomment);
        // setup a dialog window

        final String astr="*";
        alertDialogBuilder.setCancelable(false)

                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String pass=userpin.getText().toString();

                        int transferAmount= 0;
                        try {

                            transferAmount=Integer.parseInt(tranferamount.getText().toString());

                        }catch (Exception e){}

                        String beneficiery=beneficiries.getText().toString();
                        String comment=coment.getText().toString();
//                        String ussd=astr+841+astr+pass+astr+8+astr+4+astr+beneficiery+astr+comment+astr+transferAmount+astr+1+Uri.encode("#");
                            String ussd=astr+841+astr+pass+astr+8+astr+4+astr+beneficiery+Uri.encode("#");
                        String message= transferAmount+" Birr will be sent to the beneficiary account number = " +beneficiery+" \n to Continue press Ok or Press cancel to Stop";
                        confirmationMessage(ussd,message);
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

    //======================================Account to account transfer =====================================
    public void AccountToAccount() {
        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.bnk_account_to_account, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);
        final EditText userpin = (EditText) promptView.findViewById(R.id.acpinnumber);
        final EditText amounts = (EditText) promptView.findViewById(R.id.acamount);
        final EditText beneficiaries  = (EditText) promptView.findViewById(R.id.acben);
        final EditText description = (EditText) promptView.findViewById(R.id.acdesc);
        // setup a dialog window

        final String astr="*";
        alertDialogBuilder.setCancelable(false)

                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String pass=userpin.getText().toString();
                        int tranferAmount=0;
                        try {
                            tranferAmount= Integer.parseInt(amounts.getText().toString());
                        }catch (Exception e){}
                        String ben=beneficiaries.getText().toString();
                        String comment=description.getText().toString();

                       // String ussd=astr+841+astr+pass+astr+8+astr+5+astr+ben+astr+amount+astr+comment+astr+1+Uri.encode("#");
                        String ussd=String.format("*841*%s*8*5*%s*%s%s",pass,ben,tranferAmount,Uri.encode("#"));
                        String message= tranferAmount+ " Birr will be sent to Account Number "+ben+" \n To Continue press Ok or press Cancel to Stop";
                        confirmationMessage(ussd,message);
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
    //==================================================Bank mini Statement============
    public void shortBankTransactionReport() {

        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.com_pin_requst, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);
        final EditText editText = (EditText) promptView.findViewById(R.id.pinnumber);
        alertDialogBuilder.setCancelable(false)

                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String pass=editText.getText().toString();
                        String ussd=String.format("*841*%s*8*6%s",pass,Uri.encode("#"));
                        coopBankingFragment.sendUSSD(ussd);
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
    public void confirmationMessage(final String USSd, String msg){
        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.com_confirm_operation, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());

        alertDialogBuilder.setView(promptView);
        TextView tv=promptView.findViewById(R.id.confirmmsg);
        tv.setText(msg);

        alertDialogBuilder.setCancelable(false)

                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        coopBankingFragment.sendUSSD(USSd);
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
