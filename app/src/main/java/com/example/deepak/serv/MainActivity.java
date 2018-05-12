package com.example.deepak.serv;

import android.Manifest;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button start, stop,change;


    @Override
    public void onClick(View v) {
        if (v == start) {
            startService(new Intent(this, MyService.class));
        } else if (v == stop) {
            stopService(new Intent(this, MyService.class));
        } else if(v == change){
            Intent i = new Intent(getApplicationContext(),clicked.class);
            startActivity(i);
        }
    }

    public void reqP(){
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                99);
    }

    public boolean checkP(){
        if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else return false;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.startB);
        stop = findViewById(R.id.stopB);
        change = findViewById(R.id.changeB);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        change.setOnClickListener(this);

        if(!checkP()){
            reqP();
        }

        ProcessLifecycleOwner.get().getLifecycle().addObserver(new AppLifecycleListener());
    }


    public class AppLifecycleListener implements LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onMoveToForeground() {
            // app moved to foreground
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void onMoveToBackground() {
            Toast.makeText(MainActivity.this, "LifeCycleEvent", Toast.LENGTH_SHORT).show();
            startService(new Intent(MainActivity.this, MyService.class));
        }
    }


}


