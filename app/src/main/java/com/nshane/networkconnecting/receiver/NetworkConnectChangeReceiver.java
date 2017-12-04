package com.nshane.networkconnecting.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

import com.nshane.networkconnecting.NetworkApplication;
import com.nshane.networkconnecting.R;

/**
 * Created by Administrator on 2017/11/18 0018.
 */

public class NetworkConnectChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        Log.d("cg", action);

        NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        if (info.getState().equals(NetworkInfo.State.DISCONNECTED)) {
            Log.d("cg", "wifi断开");
        } else if (info.getState().equals(NetworkInfo.State.CONNECTED)) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (TextUtils.equals(wifiInfo.getSSID(), "<unknown ssid>")) {
                //网络状态更改时会执行多次回调,建立or断开连接过程中会出现<unknown ssid>,连接真正建立后会再次获取 WIFI名称
                Log.d("cg", "nulllllll 网络" + wifiInfo.getSSID());
            } else {
                //此处为与wifi连接上
                Log.d("cg", "wifi连接至网络:::::" + wifiInfo.getSSID());
                String ssid = wifiInfo.getSSID();
                String content = context.getString(R.string.tip_connected);
                NetworkApplication.sendNotification(content+ssid);
            }
        }

    }


}
