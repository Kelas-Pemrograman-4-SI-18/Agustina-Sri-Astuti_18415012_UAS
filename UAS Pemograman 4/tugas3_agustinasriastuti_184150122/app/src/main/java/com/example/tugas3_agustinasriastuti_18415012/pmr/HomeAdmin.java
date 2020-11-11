package com.example.tugas3_agustinasriastuti_18415012.pmr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.tugas3_agustinasriastuti_18415012.R;
import com.example.tugas3_agustinasriastuti_18415012.session.PrefSetting;
import com.example.tugas3_agustinasriastuti_18415012.session.SessionManager;
import com.example.tugas3_agustinasriastuti_18415012.user.Login;

public class HomeAdmin extends AppCompatActivity {

    SessionManager session;
    SharedPreferences prefs ;
    PrefSetting prefSetting ;

    CardView dataDarah, inputDarah, profileUser, exitUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreferences();
        session = new SessionManager(HomeAdmin.this);
        prefSetting.isLogin(session, prefs);

        dataDarah = (CardView)findViewById(R.id.cardDarah);
        inputDarah = (CardView)findViewById(R.id.cardInput);
        profileUser = (CardView)findViewById(R.id.cardProfile);
        exitUser = (CardView)findViewById(R.id.cardExit);

        dataDarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdmin.this, DataDarah.class);
                startActivity(i);
                finish();
            }
        });

        inputDarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdmin.this, InsertData.class);
                startActivity(i);
                finish();
            }
        });

        profileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdmin.this, Profile.class);
                startActivity(i);
                finish();
            }
        });

        exitUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(HomeAdmin.this, Login.class);
                startActivity(i);
                finish();
            }
        });

    }
}
