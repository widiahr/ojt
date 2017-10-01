package com.example.april.approval;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.april.approval.model.ResponsePaket;
import com.example.april.approval.model.SemuapaketItem;
import com.example.april.approval.util.api.BaseApiService;
import com.example.april.approval.util.api.UtilsApi;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaketDetailActivity extends AppCompatActivity {

    @BindView(R.id.txttanggalDet)
    TextView txttanggalDet;
    @BindView(R.id.txtidPickUpDet)
    TextView txtidPickUpDet;
    @BindView(R.id.txtNamaPenerimaDet)
    TextView txtNamaPenerimaDet;
    @BindView(R.id.txtKontakPenerimaDet)
    TextView txtKontakPenerimaDet;
    @BindView(R.id.txtAlamat1Det)
    TextView txtAlamat1Det;
    @BindView(R.id.txtJenisBarangDet)
    TextView txtJenisBarangDet;
    @BindView(R.id.txtQtyDet)
    TextView txtQtyDet;
    @BindView(R.id.txtWidthDet)
    TextView txtWidthDet;
    @BindView(R.id.txtLengthDet)
    TextView txtLengthDet;
    @BindView(R.id.txtHeightDet)
    TextView txtHeightDet;
    ProgressDialog loading;


    Context mContext;
    BaseApiService mApiService;

    private SemuapaketItem semuapaketItemList;
//    String mId;
//    String mTanggal;
//    String mNama;
//    String mKontak;
//    String mAlamat;
//    String mJenisBarang;
//    String mQty;
//    String mWidth;
//    String mLength;
//    String mHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket_detail);


        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();


        String extraPaket = getIntent().getStringExtra("paket");

        semuapaketItemList = new Gson().fromJson(extraPaket, SemuapaketItem.class);


        txtNamaPenerimaDet.setText(semuapaketItemList.getPenerima());
        txttanggalDet.setText(semuapaketItemList.getDate());
        txtAlamat1Det.setText(semuapaketItemList.getAlamatPenerima());
        txtKontakPenerimaDet.setText(semuapaketItemList.getTelpPenerima());
        txtHeightDet.setText(semuapaketItemList.getHeight());
        txtWidthDet.setText(semuapaketItemList.getWidth());
        txtJenisBarangDet.setText(semuapaketItemList.getJenisBarang());
        txtLengthDet.setText(semuapaketItemList.getLength());
        txtQtyDet.setText(semuapaketItemList.getQty());
        txtidPickUpDet.setText(semuapaketItemList.getKodePengiriman());

    }

    @OnClick(R.id.btnProvepick)
    public void doUpdate() {

        mApiService.updatePost(semuapaketItemList.getId_paket(), "Approved", semuapaketItemList.getStatus_pod()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                String res = response.message();
//                Log.d("Update", res);

                if (response.isSuccessful()) {
                    Toast.makeText(mContext, "Berhasil memperbaharui data!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                } else {
//                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal memperbaharui data!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                loading.dismiss();
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
