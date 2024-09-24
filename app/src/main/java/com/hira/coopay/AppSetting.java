package com.hira.coopay;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.hira.coopay.customer.MainActivity;
import com.hira.coopay.customer.R;


public class AppSetting{
    MainActivity act;
    public AppSetting(MainActivity act) {
        this.act=act;
    }
    public void changeAppLanguage() {
        final Dialog opendialog=new Dialog(this.act);
        opendialog.setContentView(R.layout.lang_setting);
        //===============================================
        opendialog.setTitle(R.string.language_setting);
        RadioButton am=opendialog.findViewById(R.id.am_lan);
        RadioButton oro=opendialog.findViewById(R.id.oro_lan);
        RadioButton en=opendialog.findViewById(R.id.en_lan);
        RadioButton som=opendialog.findViewById(R.id.som_lan);
        Button save=opendialog.findViewById(R.id.savelang);
        save.setText(R.string.cancel);
        //  final String selected_language[] = {"am"};


        am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.changeLanguage("am");
                opendialog.dismiss();

            }
        });
        oro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.changeLanguage("om");
                opendialog.dismiss();

            }
        });
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.changeLanguage("en");
                opendialog.dismiss();

            }
        });
        som.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.changeLanguage("so");
                opendialog.dismiss();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog.dismiss();


            }
        });
        opendialog.show();


    }

    public void chagePINCode(){
        final Dialog opendialog=new Dialog(this.act);
        opendialog.setContentView(R.layout.change_pin);
        //====================================================
        opendialog.setTitle(R.string.change_pin);
        final EditText old=opendialog.findViewById(R.id.oldpin1);
        final EditText pin1=opendialog.findViewById(R.id.pin1);
        final EditText pin2=opendialog.findViewById(R.id.pin2);
        Button save=opendialog.findViewById(R.id.chpin);
        save.setText(R.string.ok);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(pin1.getText().toString().equals())
                if(pin1.getText().toString().equals(pin2.getText().toString())){
                    //perform the actual task and dismiss the dialog box
                    //cpmpuse requared string
                    String ast="*";
                    String USSDpin=ast+841+ast+old.getText().toString()+ast+7+ast+1+ast+pin1.getText().toString()+ast+pin2.getText().toString();
                    act.sendUSSD(USSDpin);
                    opendialog.dismiss();
                }
                else{

                    pin2.setText(R.string.pin_miss_match);
                }

            }
        });
        opendialog.show();

    }
    public void lostPhoneReport(){
        final Dialog opendialog=new Dialog(this.act);
        opendialog.setContentView(R.layout.lost_phone);
        //===============================================================================
        opendialog.setTitle(R.string.lostphonereporting);
        final EditText currentpin=opendialog.findViewById(R.id.lost_pin);
        final EditText lostphone=opendialog.findViewById(R.id.lost_phone);
        final EditText lost_phone_pin=opendialog.findViewById(R.id.lost_phone_pin);
        Button save=opendialog.findViewById(R.id.lost_chpin);

        save.setText(R.string.ok_change_pin);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ast="*";
                String USSDlostPhone=ast+841+ast+currentpin.getText().toString()+ast+7+ast+3+ast+lostphone.getText().toString()+ast+lost_phone_pin.getText().toString()+ast+1;
                act.sendUSSD(USSDlostPhone);
                opendialog.dismiss();

            }
        });
        opendialog.show();

    }
    public void ChangeAppLanguge(){
        final Dialog opendialog=new Dialog(this.act);
        opendialog.setContentView(R.layout.lang_setting);
        //===============================================================================
        opendialog.setTitle(R.string.language_setting);
        RadioButton am=opendialog.findViewById(R.id.am_lan);
        RadioButton oro=opendialog.findViewById(R.id.oro_lan);
        RadioButton en=opendialog.findViewById(R.id.en_lan);
        RadioButton som=opendialog.findViewById(R.id.som_lan);
        Button save=opendialog.findViewById(R.id.savelang);
        save.setText(R.string.cancel);
        //  final String selected_language[] = {"am"};

        am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.changeLanguage("am");
                opendialog.dismiss();

            }
        });
        oro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.changeLanguage("om");
                opendialog.dismiss();

            }
        });
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.changeLanguage("en");
                opendialog.dismiss();

            }
        });
        som.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.changeLanguage("so");
                opendialog.dismiss();

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog.dismiss();


            }
        });
        opendialog.show();
    }

    public void changeUSSDLanguage(){
        final Dialog opendialog=new Dialog(this.act);
        opendialog.setContentView(R.layout.ussd_lang_setting);
        //===============================================================================
        opendialog.setTitle(R.string.ussd_language_setting);
       final EditText pin=opendialog.findViewById(R.id.ussd_pin);

        RadioButton am=opendialog.findViewById(R.id.am_lan);
        RadioButton oro=opendialog.findViewById(R.id.oro_lan);
        RadioButton en=opendialog.findViewById(R.id.en_lan);
        RadioButton som=opendialog.findViewById(R.id.som_lan);
        Button save=opendialog.findViewById(R.id.savelang);
        save.setText(R.string.change_language);
        //  final String selected_language[] = {"am"};
        //final String pincode=pin.getText().toString();


        am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pin.getText().toString().length()!=0){
                changeLanguage(pin.getText().toString(),4);
                opendialog.dismiss();}
                else
                    Toast.makeText(act, "Please Insert Pin Number", Toast.LENGTH_LONG).show();

            }
        });
        oro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pin.getText().toString().length()!=0){
                    changeLanguage(pin.getText().toString(),3);
                    opendialog.dismiss();}
                else
                    Toast.makeText(act, "Please Insert Pin Number", Toast.LENGTH_LONG).show();

            }
        });
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pin.getText().toString().length()!=0){
                    changeLanguage(pin.getText().toString(),1);
                    opendialog.dismiss();}
                else
                    Toast.makeText(act, "Please Insert Pin Number", Toast.LENGTH_LONG).show();

            }
        });
        som.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pin.getText().toString().length()!=0){
                    changeLanguage(pin.getText().toString(),2);
                    opendialog.dismiss();}
                else
                    Toast.makeText(act, "Please Insert Pin Number", Toast.LENGTH_LONG).show();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog.dismiss();


            }
        });
        opendialog.show();


    }
    public void blockTransaction() {
        final Dialog opendialog=new Dialog(this.act);
        opendialog.setContentView(R.layout.block_transaction);
        //===============================================================================
        opendialog.setTitle(R.string.ussd_language_setting);
        final EditText pin=opendialog.findViewById(R.id.block_pin);
        final EditText mistnumber=opendialog.findViewById(R.id.wrong_number);
        final EditText intendednumber=opendialog.findViewById(R.id.intended_no);
        final EditText coment=opendialog.findViewById(R.id.block_comment);

        Button save=opendialog.findViewById(R.id.block_transaction);
        save.setText(R.string.block_transation);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ast="*";
                String ussdblocktransaction=ast+841+ast+pin.getText().toString()+ast+7+ast+4+ast+mistnumber.getText().toString()+ast+intendednumber.getText().toString()+ast+coment.getText().toString()+ast+1;
                act.sendUSSD(ussdblocktransaction);
                opendialog.dismiss();
            }
        });
        opendialog.show();



    }
    public void changeLanguage(final String pin,int opt){
        String ast="*";
        String USSDlang_chage=ast+841+ast+pin+ast+7+ast+2+ast+opt;
        act.sendUSSD(USSDlang_chage);

    }
}

