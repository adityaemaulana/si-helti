package com.example.sihelti.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sihelti.Fragment.AkunFragment;
import com.example.sihelti.Fragment.BerandaFragment;
import com.example.sihelti.Fragment.DiagnosaFragment;
import com.example.sihelti.Fragment.RiwayatFragment;
import com.example.sihelti.Fragment.NewsFragment;
import com.example.sihelti.R;
import com.example.sihelti.Utils.BottomNavigationBehaviour;

public class MainActivity extends AppCompatActivity {
    private Intent intentAsal;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(mNavigationListener);
        bottomNavigationView.setItemIconTintList(null);

        intentAsal = getIntent();
        bukaFragmentDariIntent();

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehaviour());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadFragment(new BerandaFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mNavigationListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fm_container);
                    switch(item.getItemId()){
                        case R.id.nav_beranda:
                            if(!(fragment instanceof BerandaFragment)){
                                fragment = new BerandaFragment();
                                loadFragment(fragment);
                            }
                            return true;
                        case R.id.nav_news:
                            if(!(fragment instanceof NewsFragment)) {
                                fragment = new NewsFragment();
                                loadFragment(fragment);
                            }
                            return true;
                        case R.id.nav_diagnosa:
                            if(!(fragment instanceof DiagnosaFragment)) {
                                fragment = new DiagnosaFragment();
                                loadFragment(fragment);
                            }
                            return true;
                        case R.id.nav_hasil:
                            if(!(fragment instanceof RiwayatFragment)) {
                                fragment = new RiwayatFragment();
                                loadFragment(fragment);
                            }
                            return true;
                        case R.id.nav_akun:
                            if(!(fragment instanceof AkunFragment)) {
                                fragment = new AkunFragment();
                                loadFragment(fragment);
                            }
                            return true;
                    }
                    return false;
                }
            };

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fm_container, fragment);
        transaction.commit();
    }

    private void bukaFragmentDariIntent(){
        Menu bottomMenu = bottomNavigationView.getMenu();
        MenuItem item = bottomMenu.getItem(0);
        loadFragment(new BerandaFragment());
        item.setChecked(true);
    }
}
