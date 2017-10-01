package com.example.april.approval;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.RatingCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.april.approval.model.KantorRItem;
import com.example.april.approval.model.ResponseA;
import com.example.april.approval.model.ResponseKurir;
import com.example.april.approval.model.SemuakurirItem;
import com.example.april.approval.util.api.BaseApiService;
import com.example.april.approval.util.api.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kotlin.reflect.KAnnotatedElement;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisActivity extends AppCompatActivity {

    @BindView(R.id.txtNameU)
    EditText txtNameU;
    @BindView(R.id.txtPhoneU)
    EditText txtPhoneU;
    @BindView(R.id.txtEmailU)
    EditText txtEmailU;
    @BindView(R.id.txtPasswordU)
    EditText txtPasswordU;
    @BindView((R.id.txtAlamatU))
    EditText txtAlamatU;

    @BindView(R.id.btnRegister)
    Button btnRegister;


    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                requestSimpanKurir();
            }
        });

    }



    private void requestSimpanKurir() {
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

        mApiService.submitKurirRequest(txtEmailU.getText().toString(),
                txtPasswordU.getText().toString(),
                txtNameU.getText().toString(),
                "kurir",
                txtAlamatU.getText().toString(),
                txtPhoneU.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            Toast.makeText(mContext, "Berhasil melakukan registrasi!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mContext, Loginnya.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                        } else {
                            loading.dismiss();
                            Toast.makeText(mContext, "Gagal melakukan registrasi!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(mContext, "Koneksi internet bermasalah!", Toast.LENGTH_SHORT).show();

                    }
                });


    }
}
