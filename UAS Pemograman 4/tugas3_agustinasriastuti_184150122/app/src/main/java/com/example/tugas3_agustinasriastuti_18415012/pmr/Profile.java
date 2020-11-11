package com.example.tugas3_agustinasriastuti_18415012.pmr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tugas3_agustinasriastuti_18415012.R;
import com.example.tugas3_agustinasriastuti_18415012.session.PrefSetting;

public class Profile extends AppCompatActivity {

    TextView username, fullname, textNik, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = (TextView)findViewById(R.id.uname);
        fullname = (TextView)findViewById(R.id.fullname);
        textNik = (TextView)findViewById(R.id.textnik);
        phone = (TextView)findViewById(R.id.telp);

        username.setText(PrefSetting.username);
        fullname.setText(PrefSetting.fullname);
        textNik.setText(PrefSetting.nik);
        phone.setText(PrefSetting.phone);

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Profile.this, HomeAdmin.class);
        startActivity(i);
        finish();
    }
}
