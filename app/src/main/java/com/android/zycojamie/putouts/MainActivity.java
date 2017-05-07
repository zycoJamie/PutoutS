package com.android.zycojamie.putouts;

import android.annotation.TargetApi;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {

    private DevicePolicyManager mDevicePolicyManager;
    private ComponentName componentName;
    @TargetApi(24)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDevicePolicyManager=(DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName=new ComponentName(this,yy.class);
        if(!mDevicePolicyManager.isAdminActive(componentName)){
            Intent intent=new Intent();
            intent.setAction(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,componentName);
            startActivityForResult(intent,1);

        }else{
            mDevicePolicyManager.lockNow();
            finish();
        }
               /*try{
                   ActivityInfo ai=getPackageManager().getActivityInfo(new ComponentName(MainActivity.this,MainActivity.class), PackageManager.GET_META_DATA);
                   int name=ai.metaData.getInt("testmetaData");
                   Toast.makeText(MainActivity.this,R.string.app_name+"",Toast.LENGTH_SHORT).show();
               }catch(Exception e){
                   e.printStackTrace();
               }*/


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK) {
            mDevicePolicyManager.lockNow();
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
