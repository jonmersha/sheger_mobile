package com.hira.coopay.customer.payments.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hira.coopay.customer.MainActivity;
import com.hira.coopay.customer.R;

public class BillPaymentPartiner extends Fragment implements AdapterView.OnItemSelectedListener {

    int paySourceOption=1;
    int fullPayOption=1;

    EditText merchantPIN;
  //  EditText merchantID;
    EditText paymentAmount;
    EditText billReferenceNumber;

    RadioButton fullPaymentOption;
    RadioButton partialPaymentOption;
    RadioButton payFromWalletOption;
    RadioButton payFromBankOption;
    String ussd;
    String hash= Uri.encode("#");

    Button payButton;
    Button billInfo;

    int partnerID=2;

    MainActivity mact;
    public BillPaymentPartiner() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View partnerView=inflater.inflate(R.layout.bill_payment_partnner,null);

        merchantPIN =partnerView.findViewById(R.id.bill_pay_pin);
       // merchantID=partnerView.findViewById(R.id.merchant_bill_id);
        paymentAmount=partnerView.findViewById(R.id.merchant_bil_payment_amount);
        billReferenceNumber =partnerView.findViewById(R.id.bill_reference_number);

        //Radio Option Button
        fullPaymentOption =partnerView.findViewById(R.id.full_pay);
        partialPaymentOption =partnerView.findViewById(R.id.partial_pay);

        payFromWalletOption =partnerView.findViewById(R.id.wallet_account);
        payFromBankOption =partnerView.findViewById(R.id.bank_account);



        billInfo=partnerView.findViewById(R.id.bill_info_request);

        billInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(partnerID==1){
                    //Telecom

                    ussd=String.format("*%s*%s*%s*%s*%s*%s*%s*%s*%s*%s%s",
                            841,merchantPIN.getText().toString(),4,2,1,
                            partnerID,fullPayOption,billReferenceNumber.getText().toString(),
                            paymentAmount.getText().toString(),paySourceOption,hash);
                    mact.sendUSSD(ussd);
                }
                else if(partnerID==2){
                    //derash uussd bulding
                    int patforOPtion=1;
                    ussd=String.format("*%s*%s*%s*%s*%s*%s*%s*%s*%s%s",
                            841,merchantPIN.getText().toString(),4,2,1,
                            partnerID,patforOPtion,1,billReferenceNumber.getText().toString(),hash);
                    mact.sendUSSD(ussd);
                }
                else{
                    Toast.makeText(mact, "Incorrect Partner Selection\n Only", Toast.LENGTH_LONG).show();
                }



            }
        });


        final Spinner spinner =  partnerView.findViewById(R.id.partner_list);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.partner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        fullPaymentOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullPayOption=1;

            }
        });
        partialPaymentOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullPayOption=2;

            }
        });

        payFromWalletOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paySourceOption=1;

            }
        });

        payFromBankOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paySourceOption=2;

            }
        });


        payButton=partnerView.findViewById(R.id.merchant_bill_payment);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(partnerID==1){
                    //Telecom

                    ussd=String.format("*%s*%s*%s*%s*%s*%s*%s*%s*%s*%s%s",
                            841,merchantPIN.getText().toString(),4,2,1,
                            partnerID,fullPayOption,billReferenceNumber.getText().toString(),
                            paymentAmount.getText().toString(),paySourceOption,hash);
                    mact.sendUSSD(ussd);
                }
               else if(partnerID==2){
                    //derash uussd bulding
                    int patforOPtion=1;
                    ussd=String.format("*%s*%s*%s*%s*%s*%s*%s*%s*%s*%s*%s%s",841,
                            merchantPIN.getText().toString(),4,2,1,
                            partnerID,patforOPtion,fullPayOption+1,
                            billReferenceNumber.getText().toString(),
                            paymentAmount.getText().toString(),paySourceOption,hash);
                    mact.sendUSSD(ussd);
                }
               else{
                    Toast.makeText(mact, "Incorrect Partner Selection\n Only", Toast.LENGTH_LONG).show();
                }


            }
        });
        return partnerView;
    }

    public BillPaymentPartiner setAct(MainActivity act) {

        this.mact=act;
        return this;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        partnerID=i;

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
