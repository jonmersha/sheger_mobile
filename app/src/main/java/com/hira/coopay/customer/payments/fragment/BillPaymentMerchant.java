package com.hira.coopay.customer.payments.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hira.coopay.customer.MainActivity;
import com.hira.coopay.customer.R;

public class BillPaymentMerchant extends Fragment {

    int paySourceOption=1;
    int fullPayOption=2;

    EditText merchantPIN;
    EditText merchantID;
    EditText paymentAmount;
    EditText billReferenceNumber;

    RadioButton fullPaymentOption;
    RadioButton partialPaymentOption;
    RadioButton payFromWalletOption;
    RadioButton payFromBankOption;

    Button payButton;
    Button billInfo;

    MainActivity mact;
    String hash= Uri.encode("#");







    public BillPaymentMerchant() {


    }

    public BillPaymentMerchant setAct(MainActivity mct){
        this.mact=mct;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View merchantView=inflater.inflate(R.layout.bill_payment_merchant,null);

                merchantPIN =merchantView.findViewById(R.id.bill_pay_pin);
                merchantID=merchantView.findViewById(R.id.merchant_bill_id);
                paymentAmount=merchantView.findViewById(R.id.merchant_bil_payment_amount);
                billReferenceNumber =merchantView.findViewById(R.id.bill_reference_number);

                //Radio Option Button
                fullPaymentOption =merchantView.findViewById(R.id.full_pay);
                partialPaymentOption =merchantView.findViewById(R.id.partial_pay);

                payFromWalletOption =merchantView.findViewById(R.id.wallet_account);
                payFromBankOption =merchantView.findViewById(R.id.bank_account);

                payButton=merchantView.findViewById(R.id.merchant_bill_payment);
                billInfo=merchantView.findViewById(R.id.bill_info_request);

                billInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String ussd=String.format("*%s*%s*%s*%s*%s*%s*%s*%s%s",841,merchantPIN.getText().toString(),4,2,2,merchantID.getText().toString(),1,billReferenceNumber.getText().toString(),hash);
                        mact.sendUSSD(ussd);



                    }
                });


                fullPaymentOption.setSelected(true);
                payFromWalletOption.setSelected(true);

                fullPaymentOption.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fullPayOption=2;

                    }
                });


        fullPaymentOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullPayOption=2;

            }
        });
        partialPaymentOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullPayOption=3;


            }
        });
        payFromBankOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paySourceOption=2;

            }
        });
        payFromWalletOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paySourceOption=1;

            }
        });



        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                   String ussdMessage=String.format("*%s*%s*%s*%s*%s*%s*%s*%s*%s*%s%s",
                            841,merchantPIN.getText().toString(),4,2,2,merchantID.getText().toString(),fullPayOption,
                            billReferenceNumber.getText().toString(),paymentAmount.getText().toString(),paySourceOption, hash);
                   mact.sendUSSD(ussdMessage);

                    //Toast.makeText(mact, ussdMessage, Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(mact, e.getMessage(), Toast.LENGTH_LONG).show();

                }



            }
        });


        return merchantView;
    }
}
