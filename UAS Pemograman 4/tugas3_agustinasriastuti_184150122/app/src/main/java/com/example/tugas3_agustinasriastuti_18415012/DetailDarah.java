package com.example.tugas3_agustinasriastuti_18415012;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.tugas3_agustinasriastuti_18415012.pendonor.HomePendonor;

public class DetailDarah extends AppCompatActivity {

    EditText golongan, qty, harga;
    String strGolongan, strQTY, strHarga, _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_darah);

        golongan = (EditText)findViewById(R.id.edtGolongan);
        qty = (EditText)findViewById(R.id.edtQty);
        harga = (EditText)findViewById(R.id.edtharga);

        Intent i = getIntent();
        strGolongan = i.getStringExtra("name");
        strQTY = i.getStringExtra("qty");
        strHarga = i.getStringExtra("harga");
        _id = i.getStringExtra("_id");

        golongan.setText(strGolongan);
        qty.setText(strQTY);
        harga.setText(strHarga);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(DetailDarah.this, HomePendonor.class);
        startActivity(i);
        finish();
    }
}
