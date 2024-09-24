package com.hira.coopay.customer.mobile;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hira.coopay.customer.R;

public class MobileMoneyEvent {
    View view;
    MobileMoneyFragment mobileMoney;
    int water_bill_flag=2;

    public MobileMoneyEvent(View view, MobileMoneyFragment mobileMoney) {
        this.view = view;
        this.mobileMoney=mobileMoney;
    }
    //=====================================Mobile Balance request==============================//
    public void mBalanceRequest(){
                LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
                View promptView = layoutInflater.inflate(R.layout.com_pin_requst, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                alertDialogBuilder.setView(promptView);
                final EditText editText = (EditText) promptView.findViewById(R.id.pinnumber);
                final TextView tv=promptView.findViewById(R.id.tvpinid);
                tv.setText(R.string.wallet_balance_com);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)

                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String pass=editText.getText().toString();
                                String ussd= String.format("*%s*%s*%s%s",841,pass,1,Uri.encode("#"));
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
    //=====================================Send Mobile money==============================//
    public void sendMoney(){
        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.wallet_mobile_send_money, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);

        final EditText pin = (EditText) promptView.findViewById(R.id.pinnumber);
        final EditText mob1 = (EditText) promptView.findViewById(R.id.mobile1);
        final EditText mob2 = (EditText) promptView.findViewById(R.id.mobile2);
        final EditText amount = (EditText) promptView.findViewById(R.id.amount);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                            String pass=pin.getText().toString();
                            String num1=mob1.getText().toString();
                            String num2=mob2.getText().toString();

                            int amountToSend=0;
                            try {
                                amountToSend= Integer.parseInt(amount.getText().toString());
                            }
                            catch (Exception e){


                            }
                            String ussd= "*"+841+"*"+pass+"*"+2+"*"+num1+"*"+num2+"*"+amountToSend+"*"+Uri.encode("#");
                            String message= amountToSend + " Birr will Sent to Subscriber number "+num1+"\n to Continue press Ok or Cancel to Stop";
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
    //======================================Withdraw Money==============================//
    public void withdrawMoney() {

        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.wallet_mobile_withdraw, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);


        final EditText pin = (EditText) promptView.findViewById(R.id.pinnumber);
        final EditText agent1 = (EditText) promptView.findViewById(R.id.wagentno1);
        final EditText agent2 = (EditText) promptView.findViewById(R.id.wagentno2);
        final EditText pamount = (EditText) promptView.findViewById(R.id.wamount);

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                            String pass=pin.getText().toString();
                            String num1=agent1.getText().toString();
                            String num2=agent2.getText().toString();


                        int withdrawAmount=0;
                        try {
                            withdrawAmount = Integer.parseInt(pamount.getText().toString());
                        }catch (Exception e){

                        }


                            String ussd = "*"+841+"*"+pass+"*"+3+"*"+num1+"*"+num2+"*"+withdrawAmount+"*"+1+Uri.encode("#");
                            String message= withdrawAmount + " Birr will be withdrawn from Agent number "+num1+"\n to Continue press Ok or Cancel to Stop";
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
    //======================================Pay Bill==============================//
    public void payBill() {

        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.wallet_pay_bill, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);

        final EditText pin = (EditText) promptView.findViewById(R.id.pinnumber);
        final EditText agent1 = (EditText) promptView.findViewById(R.id.agent1);
        final EditText agent2 = (EditText) promptView.findViewById(R.id.agent2);
        final EditText pamount = (EditText) promptView.findViewById(R.id.paymount);



        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                            String pass=pin.getText().toString();
                            String num1=agent1.getText().toString();
                            String num2=agent2.getText().toString();


                        int mamountToPay=0;
                        try {
                            mamountToPay = Integer.parseInt(pamount.getText().toString());
                        }catch (Exception e){

                        }

                            String ussd = "*"+841+"*"+pass+"*"+4+"*"+num1+"*"+num2+"*"+mamountToPay+"*"+1+Uri.encode("#");
                           String message= mamountToPay + " Birr will be paid to Agent number "+num1+"\n press Ok to Continue  or press cancel to Stop";

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
    //======================================Show Transaction==============================//
    public void showTransaction(){
        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.wallet_transaction_requst, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);
        final int[] selection = {1};

        final EditText editText = (EditText) promptView.findViewById(R.id.pinnumber);
        final RadioButton single=promptView.findViewById(R.id.single);
        final RadioButton min=promptView.findViewById(R.id.min);
        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection[0] =1;
            }
        });
        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection[0] =2;
            }
        });

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String pass=editText.getText().toString();
                        String ussd="*"+841+"*"+pass+"*"+5+"*"+selection[0]+Uri.encode("#");
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
    //====================================Mobile TopUP==============================//
    public void topUp(){

        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.wallet_topup, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);


        final EditText pinn = (EditText) promptView.findViewById(R.id.pinnumber);
        final EditText friendno = (EditText) promptView.findViewById(R.id.frendno);
        final EditText rechageamount = (EditText) promptView.findViewById(R.id.rechargeamount);


        final boolean[] selfrecharge = {true};
        final RadioButton self=promptView.findViewById(R.id.self);
        final RadioButton friend=promptView.findViewById(R.id.friend);
        self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selfrecharge[0] =true;

            }
        });
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selfrecharge[0] =false;
            }
        });

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                    String message;

                    public void onClick(DialogInterface dialog, int id) {
                        String pass=pinn.getText().toString();
                        String ussd;
                        String frno=friendno.getText().toString();

                        String rechargeAmount="0";
                        try {
                            rechargeAmount = rechageamount.getText().toString();

                        }catch (Exception e){

                        }

                        if(selfrecharge[0]){

                        ussd=String.format("*%s*%s*%s*%s*%s*%s%s",841,pass,6,1,rechargeAmount,1,Uri.encode("#"));
                        message="You are about recharge your balance by "+rechargeAmount+" Birr \n To conform press Ok";
                        }
                        else{
                            ussd=String.format("*%s*%s*%s*%s*%s*%s*%s%s",841,pass,6,2,frno.toString(),rechargeAmount,1,Uri.encode("#"));
                            message="You are about recharge Mobile Number = "+frno+"\nby\n"+rechargeAmount+" Birr \n To conform press Ok";

                        }
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
    public void topUpNew(){
        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.wallet_topup_new, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);
        final EditText pinn_number = (EditText) promptView.findViewById(R.id.pin_number);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                    String message;

                    public void onClick(DialogInterface dialog, int id) {
                        String pass=pinn_number.getText().toString();
                        String ussd=String.format("*%s*%s*%s%s",841,pass,6,Uri.encode("#"));
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
    //====================================MtoNonCustomer==============================//
    public void toNonCustomer(){
        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        View promptView = layoutInflater.inflate(R.layout.wallet_send_noncustomer_current, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);

        final EditText PIN = (EditText) promptView.findViewById(R.id.pinnumber);
        final EditText customerNumber = (EditText) promptView.findViewById(R.id.frendno);
//        final EditText customerName = (EditText) promptView.findViewById(R.id.non_customer_customerName);
//        final EditText customerAddress = (EditText) promptView.findViewById(R.id.cusaddr);
//        final EditText amount = (EditText) promptView.findViewById(R.id.amounttosend);


//        final boolean[] selfdeduction = {false};
//        final RadioButton self=promptView.findViewById(R.id.self);
//        final RadioButton friend=promptView.findViewById(R.id.friend);
//        friend.setSelected(true);
//
//        self.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selfdeduction[0] =true;
//            }
//        });
//        friend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selfdeduction[0] =false;
//            }
//        });

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                    String message;

                    public void onClick(DialogInterface dialog, int id) {
                        String pass=PIN.getText().toString();

                        String receivingCustomerMobile=customerNumber.getText().toString();
//                        int amountTosend=0;
//                        try {
//                            amountTosend = Integer.parseInt(amount.getText().toString());
//
//                        }
//                        catch (Exception e){
//                            amountTosend=0;
//
//                        }

                        //String receivingCustomerAddr=customerAddress.getText().toString();
                       // String receivingCustomerName=customerName.getText().toString();

                       // int deductionValue=2;

//                        if(selfdeduction[0]){
//                            deductionValue=1;
//                           // message=String.format("You  are about to send Birr %s\n to Customer name %s \n with Phone Number %s \n and receiver Address  %s transfer cost will be deducted from your account; \n To Confirm Transaction please Click ok",amountTosend,receivingCustomerName,receivingCustomerMobile,receivingCustomerAddr);
//                        }
//                        else{
//                            deductionValue=2;
//                           // message=String.format("You are about to send Birr %s\n to Customer name %s \n with Phone Number %s \n and receiver Address  %s transfer cost will be deducted from your account;\n To Confirm Transaction please Click ok",amountTosend,receivingCustomerName,receivingCustomerMobile,receivingCustomerAddr);
//                        }

                        String ussd=String.format("*%s*%s*%s*%s%s",841,pass,9,receivingCustomerMobile,Uri.encode("#"));
                        //confirmationMessage(ussd,message);
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
    //====================================confirmationMessage==============================//
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
                       mobileMoney.sendUSSD(USSd);
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
    public void watterBill() {
        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        final View promptView = layoutInflater.inflate(R.layout.water_payment, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);

        final EditText pinNumber =  promptView.findViewById(R.id.mypin);
        final RadioButton fullPayment=promptView.findViewById(R.id.water_pay_info);
        final RadioButton payment_info=promptView.findViewById(R.id.water_bill_info);
        fullPayment.setChecked(true);
        payment_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                water_bill_flag=1;
            }
        });
        fullPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                water_bill_flag=2;

            }
        });


        final Spinner agency=promptView.findViewById(R.id.agency);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(promptView.getContext(),
                R.array.agency, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        agency.setAdapter(adapter);

        alertDialogBuilder.setCancelable(false)

                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                      //  String pass=editText.getText().toString();
                      //  String ussd= String.format("*%s*%s*%s%s",841,pass,1,Uri.encode("#"));
                        String USSD_TEXT="";
                        int selected_id=agency.getSelectedItemPosition();
                        String pos_values=agency.getItemAtPosition(selected_id).toString();

                       if(pos_values.equals("Adama City")){

                           USSD_TEXT=String.format("*%s*%s*%s*%s*%s*%s*%s*%s%s",841,pinNumber.getText().toString(),4,2,1,2,1,2,Uri.encode("#"));

                           mobileMoney.sendUSSD(USSD_TEXT);

                          ///Toast.makeText(promptView.getContext(), "Selection"+USSD_TEXT, Toast.LENGTH_SHORT).show();

                       }
                       else if(pos_values.equals("Amboo City")){
                           USSD_TEXT=String.format("*%s*%s*%s*%s*%s*%s*%s%s",841,pinNumber.getText().toString(),4,2,6,2,water_bill_flag,Uri.encode("#"));

                           mobileMoney.sendUSSD(USSD_TEXT);

                           //Toast.makeText(promptView.getContext(), "Selection "+pos_values, Toast.LENGTH_SHORT).show();

                        }
                        else if(pos_values.equals("Walliso City")){
                           USSD_TEXT=String.format("*%s*%s*%s*%s*%s*%s*%s%s",841,pinNumber.getText().toString(),4,2,6,1,water_bill_flag,Uri.encode("#"));
                           mobileMoney.sendUSSD(USSD_TEXT);
                        }
                        else if(pos_values.equals("Negele Borena")){
                           USSD_TEXT=String.format("*%s*%s*%s*%s*%s*%s*%s%s",841,pinNumber.getText().toString(),4,2,6,3,water_bill_flag,Uri.encode("#"));
                           mobileMoney.sendUSSD(USSD_TEXT);
                        }else if(pos_values.equals("Adola Town")){
                           USSD_TEXT=String.format("*%s*%s*%s*%s*%s*%s*%s%s",841,pinNumber.getText().toString(),4,2,6,4,water_bill_flag,Uri.encode("#"));
                           mobileMoney.sendUSSD(USSD_TEXT);
                        }else if(pos_values.equals("Shakiso Town")){
                           USSD_TEXT=String.format("*%s*%s*%s*%s*%s*%s*%s%s",841,pinNumber.getText().toString(),4,2,6,5,water_bill_flag,Uri.encode("#"));
                           mobileMoney.sendUSSD(USSD_TEXT);
                        }
                        else{
                           Toast.makeText(promptView.getContext(), "Invalid Selection", Toast.LENGTH_SHORT).show();
                       }

                     // mobileMoney.sendUSSD(USSD_TEXT);
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

    public void trafficPenality() {


        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        final View promptView = layoutInflater.inflate(R.layout.trafic_penality_payment, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setView(promptView);

        final EditText pinNumber =  promptView.findViewById(R.id.traffic_pin_number);
        final RadioButton bill_info_query=promptView.findViewById(R.id.traffic_bill_info_request);
        final RadioButton full_payment=promptView.findViewById(R.id.traffic_full_pay);
        final RadioButton partial_payment=promptView.findViewById(R.id.traffic_partial_pay);

        final String[] request_type = {"bill_info"};

        bill_info_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request_type[0] ="bill_info";

            }
        });
        full_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request_type[0] ="full_pay";

            }
        });
        partial_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request_type[0] ="partial_pay";

            }
        });
        alertDialogBuilder.setCancelable(false)

                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String USSD_TEXT="";

                        if(request_type[0].equals("bill_info")){

                            USSD_TEXT=String.format("*%s*%s*%s*%s*%s*%s*%s%s",841,pinNumber.getText().toString(),4,2,1,7,1,Uri.encode("#"));
                            mobileMoney.sendUSSD(USSD_TEXT);

                        }
                        else if(request_type[0].equals("full_pay")){
                            USSD_TEXT=String.format("*%s*%s*%s*%s*%s*%s*%s%s",841,pinNumber.getText().toString(),4,2,1,7,2,Uri.encode("#"));
                            mobileMoney.sendUSSD(USSD_TEXT);



                        }
                        else {
                            USSD_TEXT=String.format("*%s*%s*%s*%s*%s*%s*%s%s",841,pinNumber.getText().toString(),4,2,1,7,3,Uri.encode("#"));
                            mobileMoney.sendUSSD(USSD_TEXT);
                        }



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
