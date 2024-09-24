package com.hira.coopay;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.core.app.ActivityCompat;

public  class USSDsender extends Activity{
    public  void sendUSSD(String ussd){
        Intent callintent=new Intent(Intent.ACTION_CALL);
        callintent.setData(Uri.parse(Uri.parse("tel:" + ussd)+Uri.encode("#")));
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            return;

        }
       startActivity(callintent);

    }

}
