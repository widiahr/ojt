package com.example.april.approval;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.april.approval.R;
import com.example.april.approval.adapter.PaketAdapter;
import com.example.april.approval.model.ResponsePaket;
import com.example.april.approval.model.SemuapaketItem;
import com.example.april.approval.util.RecyclerItemClickListener;
import com.example.april.approval.util.api.BaseApiService;
import com.example.april.approval.util.api.UtilsApi;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaketActivity extends AppCompatActivity {

    @BindView(R.id.rvPaket)
    RecyclerView rvPaket;
//    @BindView(R.id.btnProvepick)
//    Button btnProvePick;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    ProgressDialog loading;


    Context mContext;
    List<SemuapaketItem> semuapaketItemList = new ArrayList<>();
    PaketAdapter paketAdapter;
    BaseApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket);

//        getSupportActionBar().setTitle("Paket");


        ButterKnife.bind(this);
        mApiService = UtilsApi.getAPIService();
        mContext = this;

        paketAdapter = new PaketAdapter(this, semuapaketItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvPaket.setLayoutManager(mLayoutManager);
        rvPaket.setItemAnimator(new DefaultItemAnimator());

        getDataPaket();

//        btnProvePick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(PaketActivity.this, MainActivity.class));
//            }
//        });
    }

    private void getDataPaket() {
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu..", true, false);
        mApiService.getSemuapaket().enqueue(new Callback<ResponsePaket>() {
            @Override
            public void onResponse(Call<ResponsePaket> call, Response<ResponsePaket> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();

                    final List<SemuapaketItem> semuaPaketItems = response.body().getSemuapaket();

                    rvPaket.setAdapter(new PaketAdapter(mContext, semuaPaketItems));
                    paketAdapter.notifyDataSetChanged();
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data paket", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePaket> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void initDataIntent(final List<SemuapaketItem> paketList){
//        rvPaket.addOnItemTouchListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                String kode_pengiriman = paketList.get(position).getKodePengiriman();
//                String
//            }
//        });
//    }
}
