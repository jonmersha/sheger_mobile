package com.hira.coopay.customer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.hira.coopay.AppSetting;
import com.hira.coopay.ViewPagerAdapter;
import com.hira.coopay.customer.bank.CoopBankingFragment;
import com.hira.coopay.customer.mobile.MobileMoneyFragment;
import com.hira.coopay.customer.other_service.OtherService;

import java.util.Locale;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    View view;
    private String mLanguageCode = "am";
    private Locale locale;
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private Button mbalance, lang;
    private static final int MY_PERMISSIONS_REQUEST_PHONE_CALL = 0;
    ShareActionProvider myShareActionProvider;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
       adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });



//        adView.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                // Code to be executed when an ad finishes loading.
//                Toast.makeText(MainActivity.this, "Loaded", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                // Code to be executed when an ad request fails.
//                 Toast.makeText(MainActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onAdClicked() {
//                // Code to be executed when the user clicks on an ad.
//                // Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//                // Code to be executed when the user has left the app.
//
//            }
//
//            @Override
//            public void onAdClosed() {
//                // Code to be executed when the user is about to return
//                // to the app after tapping on an ad.
//            }
//
//        });
        //============
        tabLayout = findViewById(R.id.tablayout);
        appBarLayout = findViewById(R.id.appbar);
        viewPager = findViewById(R.id.vpager);
        //setting adapter
        String mobilemoney = getResources().getString(R.string.wallet_money);
        String coopbanking = getResources().getString(R.string.coopbank);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // MobileMoneyFragment myfragment=new
        adapter.addFragment(new MobileMoneyFragment().fragmentSet(this), mobilemoney);
        adapter.addFragment(new CoopBankingFragment(), coopbanking);
        adapter.addFragment(new OtherService(),"Other Services");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //==============
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {

            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_PHONE_CALL);
            }
            return;

        }

       // View flutterView=Flutter.createView();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.share_menu, menu);

        // MenuItem shareItem = menu.findItem(R.id.action_share);
        //  myShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

        // Return true to display menu

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.merchant_id) {
            Intent myShareIntent = new Intent(Intent.ACTION_SEND);
            myShareIntent.setType("image/*");
            myShareIntent.putExtra(Intent.EXTRA_STREAM, "myImageUri");
            myShareActionProvider.setShareIntent(myShareIntent);
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.pinchange) {
            new AppSetting(this).chagePINCode();
        } else if (id == R.id.bt) {
            new AppSetting(this).blockTransaction();
        } else if (id == R.id.ussd_lan_setting) {
            new AppSetting(this).changeUSSDLanguage();

        } else if (id == R.id.lostphone) {
            new AppSetting(this).lostPhoneReport();
        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);

            String shareLink="https://play.google.com/store/apps/details?id=com.hira.coopay.customer&hl=en";
            sendIntent.putExtra(Intent.EXTRA_TEXT, shareLink);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);


        } else if (id == R.id.nav_send) {

        }
        else if (id==R.id.action_share){

            Toast.makeText(this, "share menu clicked", Toast.LENGTH_SHORT).show();
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);

            String shareLink="https://play.google.com/store/apps/details?id=com.hira.coopay.customer&hl=en";
            sendIntent.putExtra(Intent.EXTRA_TEXT, shareLink);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);


        }
        else if (id == R.id.language) {
            new AppSetting(this).changeAppLanguage();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_PHONE_CALL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }


        }
    }
    public void changeLanguage(String language) {
        Resources resources = getResources();
        locale = new Locale(language);
        Configuration configuration = resources.getConfiguration();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        }
        getBaseContext().getResources().updateConfiguration(configuration,
                getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }
    public void sendUSSD(String ussd) {
        Intent callintent = new Intent(Intent.ACTION_CALL);
        callintent.setData(Uri.parse(Uri.parse("tel:" + ussd) + Uri.encode("#")));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callintent);
    }
    private void setShareIntent(Intent shareIntent) {
        if (myShareActionProvider != null) {
            myShareActionProvider.setShareIntent(shareIntent);
        }
    }
    public void callEbirrSupport(View v) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "6836"));
        //startActivity(intent);
    }


}
