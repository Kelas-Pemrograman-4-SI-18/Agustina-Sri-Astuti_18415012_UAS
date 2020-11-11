package com.example.tugas3_agustinasriastuti_18415012.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tugas3_agustinasriastuti_18415012.server.BaseURL;
import com.example.tugas3_agustinasriastuti_18415012.R;
import com.ornach.nobobutton.NoboButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Registrasi extends AppCompatActivity {

    Button kembalilogin;
    NoboButton btnregistrasi;
    EditText namalengkap,username,password,nik,nomortelepon,darah,addres,umur;
    ProgressDialog pDialog ;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);

        namalengkap = (EditText) findViewById(R.id.namalengkap);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        nik = (EditText) findViewById(R.id.nik);
        nomortelepon = (EditText) findViewById(R.id.nomortelepon);
        darah = (EditText) findViewById(R.id.darah);
        addres = (EditText) findViewById(R.id.addres);
        umur = (EditText) findViewById(R.id.umur);

        kembalilogin = (Button) findViewById(R.id.kembali_login);
        btnregistrasi = (NoboButton) findViewById(R.id.btnregistrasi);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        kembalilogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(Registrasi.this, Login.class);
                startActivity(kembali);
                finish();
            }
        });

        btnregistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strnamalengkap = namalengkap.getText().toString();
                String strusername = username.getText().toString();
                String strpassword = password.getText().toString();
                String strnik = nik.getText().toString();
                String strnomortelepon = nomortelepon.getText().toString();
                String strdarah = darah.getText().toString();
                String stralamat = addres.getText().toString();
                String strumur = umur.getText().toString();

                if (strnamalengkap.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"nama lengkap tidak boleh kosong",Toast.LENGTH_LONG).show();
                }else if (strusername.isEmpty()){
                    Toast.makeText(getApplicationContext(),"username tidak boleh kosong",Toast.LENGTH_LONG).show();
                }else if (strpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"password tidak boleh kosong",Toast.LENGTH_LONG).show();
                } else if (strnik.isEmpty()){
                    Toast.makeText(getApplicationContext(),"nik tidak boleh kosong",Toast.LENGTH_LONG).show();
                } else if (strnomortelepon.isEmpty()){
                    Toast.makeText(getApplicationContext(),"nomor telepon tidak boleh kosong",Toast.LENGTH_LONG).show();
                }else if (strdarah.isEmpty()){
                    Toast.makeText(getApplicationContext(),"golongan darah tidak boleh kosong",Toast.LENGTH_LONG).show();
                }else if (stralamat.isEmpty()){
                    Toast.makeText(getApplicationContext(),"alamat tidak boleh kosong",Toast.LENGTH_LONG).show();
                }else if (strumur.isEmpty()){
                    Toast.makeText(getApplicationContext(),"umur tidak boleh kosong",Toast.LENGTH_LONG).show();
                } else {
                    registrasi(strnamalengkap, strusername, strpassword, strnik, strnomortelepon, strdarah, stralamat, strumur);
                }
            }
        });

    }

    public void registrasi(String fullname, String username, String password, String nik, String phone, String blood, String address, String age ){
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("fullname", fullname);
        params.put("username", username);
        params.put("password", password);
        params.put("nik", nik);
        params.put("phone", phone);
        params.put("blood", blood);
        params.put("address", address);
        params.put("age", age);
        params.put("role", "2");

        pDialog.setMessage("mohon tunggu.....");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(BaseURL.register, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            String strMsg = response.getString("msg");
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Registrasi.this, Login.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });
        mRequestQueue.add(req);
    }

    @Override
    public  void onBackPressed(){
        Intent i = new Intent (Registrasi.this, Login.class);
        startActivity(i);
        finish();
    }

    private void showDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private  void hideDialog(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }

}
