package com.hira.coopay.customer.payments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hira.coopay.customer.MainActivity;
import com.hira.coopay.customer.MyAdapter;
import com.hira.coopay.customer.R;
import com.hira.coopay.customer.payments.fragment.BillPaymentMerchant;
import com.hira.coopay.customer.payments.fragment.BillPaymentPartiner;

public class BillPaymentTab extends DialogFragment {

    public BillPaymentTab() {

    }
    MainActivity act;

    public BillPaymentTab setValues(MainActivity act){
        this.act=act;

        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bill_payment_fragment,null);
        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) view.findViewById(R.id.result_tabs);
        tabs.setupWithViewPager(viewPager);
        return view;
    }
    private void setupViewPager(ViewPager viewPager) {


        MyAdapter adapter = new MyAdapter(getChildFragmentManager());
        adapter.addFragment(new BillPaymentMerchant().setAct(act), "Merchant");
        adapter.addFragment(new BillPaymentPartiner().setAct(act), "Partner");

        viewPager.setAdapter(adapter);



    }
}
