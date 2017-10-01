package com.example.april.approval.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.april.approval.R;

import com.example.april.approval.adapter.PaketAdapter;
import com.example.april.approval.adapter.PengirimanAdapter;
import com.example.april.approval.model.ItemPengiriman;
import com.example.april.approval.model.ResponseA;
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

import com.example.april.approval.fragment.PackageApp;



import static com.example.april.approval.R.id.rvPakett;
import static com.example.april.approval.R.layout.fragment_packageapp_det;

public class ApprovalDetails extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    @BindView(R.id.rvPengiriman)
    RecyclerView rvPengiriman;
//    @BindView(R.id.rvPakett)
//    RecyclerView rvPakett;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    ProgressDialog loading;

    List<ItemPengiriman> itemPengirimanList = new ArrayList<>();

    //tambahan
    List<SemuapaketItem> semuapaketItemList = new ArrayList<>();
    PaketAdapter paketAdapter;
    //tambahan

    PengirimanAdapter pengirimanAdapter;
    BaseApiService mApiService;


    public ApprovalDetails() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_approval_details, container, false);

        View view = inflater.inflate(R.layout.fragment_approval_details, container, false);

        ButterKnife.bind(this, view);
        mApiService = UtilsApi.getAPIService();

        // kalo parameter adapter minta context, kasih aja this itu kalo di activity. kalo fragment itu getactivity

        pengirimanAdapter = new PengirimanAdapter(getActivity(), itemPengirimanList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvPengiriman.setLayoutManager(mLayoutManager);
        rvPengiriman.setItemAnimator(new DefaultItemAnimator());

        //tambahan
        paketAdapter = new PaketAdapter(getActivity(), semuapaketItemList);
//        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity());
//        rvPakett.setLayoutManager(mLayoutManager2);
//        rvPakett.setItemAnimator(new DefaultItemAnimator());
        //tambahan

        getDataPengiriman();

        return view;
    }


    public void getDataPengiriman() {

        loading = ProgressDialog.show(getActivity(), null, "Harap Tunggu..", true, false);
        mApiService.getSemuapengiriman().enqueue(new Callback<ResponseA>() {

            @Override
            public void onResponse(Call<ResponseA> call, Response<ResponseA> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    final List<ItemPengiriman> pengirimanItems = response.body().getSemuapengiriman();
                    rvPengiriman.setAdapter(new PengirimanAdapter(getActivity(), pengirimanItems));
                    pengirimanAdapter.notifyDataSetChanged();
                } else {
                    loading.dismiss();
                    Toast.makeText(getActivity(), "Gagal mengambil data pengiriman", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseA> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getActivity(), "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });

        mApiService.getSemuapaket().enqueue(new Callback<ResponsePaket>() {

            @Override
            public void onResponse(Call<ResponsePaket> call, Response<ResponsePaket> response) {
                if (response.isSuccessful()) {

//                    final List<SemuapaketItem> paketItems = response.body().getSemuapaket();
//                    rvPakett.setAdapter(new PaketAdapter(getActivity(), paketItems));
                    paketAdapter.notifyDataSetChanged();
                } else {

                    Toast.makeText(getActivity(), "Gagal mengambil data paket", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePaket> call, Throwable t) {

                Toast.makeText(getActivity(), "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
