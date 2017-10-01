package com.example.april.approval.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.april.approval.R;
import com.example.april.approval.adapter.PaketAdapter;
import com.example.april.approval.model.ResponsePaket;
import com.example.april.approval.model.SemuapaketItem;
import com.example.april.approval.util.api.BaseApiService;
import com.example.april.approval.util.api.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PackageApp extends Fragment {

    @BindView(R.id.rvPakett)
    RecyclerView rvPakett;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    ProgressDialog loading;


    Context mContext;
    List<SemuapaketItem> semuapaketItemList = new ArrayList<>();
    PaketAdapter paketAdapter;
    BaseApiService mApiService;

    public PackageApp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_package_app, container, false);

        ButterKnife.bind(this, view);
        mApiService = UtilsApi.getAPIService();


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvPakett.setLayoutManager(mLayoutManager);
        rvPakett.setItemAnimator(new DefaultItemAnimator());

        getDataPaket();

        return view;
    }

    private void getDataPaket() {
        loading = ProgressDialog.show(getActivity(), null, "Harap Tunggu..", true, false);
        mApiService.getSemuapaket().enqueue(new Callback<ResponsePaket>() {
            @Override
            public void onResponse(Call<ResponsePaket> call, Response<ResponsePaket> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();

                    final List<SemuapaketItem> semuaPaketItems = response.body().getSemuapaket();

                    paketAdapter = new PaketAdapter(getActivity(), semuaPaketItems);
                    rvPakett.setAdapter(paketAdapter);
                    paketAdapter.notifyDataSetChanged();
                } else {
                    loading.dismiss();
                    Toast.makeText(getActivity(), "Gagal mengambil data paket", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePaket> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getActivity(), "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
