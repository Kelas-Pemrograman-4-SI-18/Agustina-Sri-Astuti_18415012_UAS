package com.example.tugas3_agustinasriastuti_18415012.session;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.tugas3_agustinasriastuti_18415012.pendonor.HomePendonor;
import com.example.tugas3_agustinasriastuti_18415012.pmr.HomeAdmin;

public class PrefSetting {

    public static String _id;
    public static String fullname;
    public static String username;
    public static String password;
    public static String nik;
    public static String phone;
    public static String blood;
    public static String address;
    public static String age;
    public static String role;

    Activity activity;

    public PrefSetting (Activity activity){
        this.activity = activity;
    }

    public SharedPreferences getSharePreferences(){
        SharedPreferences preferences = activity.getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        return preferences;
    }

    public void isLogin (SessionManager session, SharedPreferences pref){
        session = new SessionManager(activity);
        if (session.isLoggedIn()){
            pref = getSharePreferences();
            _id = pref.getString("_id", "");
            fullname = pref.getString("fullname", "");
            username = pref.getString("username", "");
            password = pref.getString("password", "");
            nik = pref.getString("nik", "");
            phone = pref.getString("phone", "");
            blood = pref.getString("blood", "");
            address = pref.getString("address", "");
            age = pref.getString("age", "");
            role = pref.getString("role","");
        }else {
            session.setLogin(false);
            session.setSessid(0);
            Intent i = new Intent(activity, activity.getClass());
            activity.startActivity(i);
            activity.finish();
        }
    }

    public void checkLogin (SessionManager session, SharedPreferences pref){
        session = new SessionManager(activity);
        _id = pref.getString("_id", "");
        fullname = pref.getString("fullname", "");
        username = pref.getString("username", "");
        password = pref.getString("password", "");
        nik = pref.getString("nik", "");
        phone = pref.getString("phone", "");
        blood = pref.getString("blood", "");
        address = pref.getString("address", "");
        age = pref.getString("age", "");
        role = pref.getString("role","");
        if (session.isLoggedIn()){
            if (role.equals("1")){
                Intent i = new Intent(activity, HomeAdmin.class);
                activity.startActivity(i);
                activity.finish();
            }else {
                Intent i = new Intent(activity, HomePendonor.class);
                activity.startActivity(i);
                activity.finish();
            }
        }
    }

    public void  storeRegIdSharedPreferences(Context context, String _id, String fullname, String username, String password, String nik, String phone, String blood, String address, String age, String role, SharedPreferences prefs){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("_id", _id);
        editor.putString("fullname", fullname);
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putString("nik", nik);
        editor.putString("phone", phone);
        editor.putString("blood", blood);
        editor.putString("adderess", address);
        editor.putString("age", age);
        editor.putString("role", role);
        editor.commit();
    }

}
