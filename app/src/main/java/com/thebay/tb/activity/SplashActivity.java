package com.thebay.tb.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.thebay.tb.R;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    private ConnectivityManager mManager;
    private NetworkInfo mMobile;
    private NetworkInfo mWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mMobile = mManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        mWifi = mManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mWifi.isConnected() || mMobile.isConnected()) {  //인터넷 연결 됐을때
            permissionCheck();

        } else {
            //인터넷 연결 안됐을때
            noNetwork();
        }
    }

    private void permissionCheck() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //마시멜로우 이상인지 체크

            int permissionCheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permissionCheck1 == PackageManager.PERMISSION_DENIED || permissionCheck2 == PackageManager.PERMISSION_DENIED) {
                permissionSetting();
            } else {
                //퍼미션값 다 있으면
                splashThread();
            }

        } else {
            //마시멜로우 미만
            //퍼미션 체크 x
            splashThread();
        }
    }

    private void noNetwork() {
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(SplashActivity.this);
        alert_confirm.setMessage("인터넷 연결 확인 후 다시 시도해주세요.").setCancelable(false).setPositiveButton("재접속",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mMobile = mManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                        mWifi = mManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                        if (mWifi.isConnected() || mMobile.isConnected()) {
                            splashThread();
                        } else {
                            noNetwork();
                        }
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alert = alert_confirm.create();
        alert.show();
    }

    public void permissionSetting() {

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                splashThread();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(SplashActivity.this, "권한을 거부하시면 해당 서비스를 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        };

        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("사용 권한을 거부하는 경우 이 서비스를 사용할 수 없습니다.\n\n사용 권한을 설정하십시오.[설정] > [사용 권한]")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    public void splashThread() {

        Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    Thread.sleep(2000);
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }


}
