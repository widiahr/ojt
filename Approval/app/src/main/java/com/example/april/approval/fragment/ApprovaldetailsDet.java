package com.example.april.approval.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.april.approval.fragment.PackageappDet;
import com.example.april.approval.MainActivity;
import com.example.april.approval.R;
import com.example.april.approval.model.ItemPengiriman;


import com.example.april.approval.model.SemuapaketItem;
import com.example.april.approval.util.api.BaseApiService;

import java.util.Calendar;
import java.util.Date;

import com.example.april.approval.util.api.UtilsApi;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApprovaldetailsDet extends Fragment {

    @BindView(R.id.txtidPickUpADet)
    TextView txtidPickUpADet;
    @BindView(R.id.txtPenerimaTerteraADet)
    TextView txtPenerimaTereteraADet;
    @BindView(R.id.txtJenisBarangADet)
    TextView txtJenisBarangADet;
    @BindView(R.id.txtNamaPenerimaADet)
    EditText txtNamaPenerimaADet;
    @BindView(R.id.spnPenerima)
    Spinner spnPenerima;


    BaseApiService mApiService;

    private ItemPengiriman semuapengirimanList;

    String ListPengiriman;

    private String[] listPenerima;

    //tambahan
    private SemuapaketItem semuapaketItemList;
    String ListPaket;
    //tambahan


    public ApprovaldetailsDet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_approvaldetails_det, container, false);
        ButterKnife.bind(this, view);

        mApiService = UtilsApi.getAPIService();

        initComponents();

//        //tambahan
//        bindDataPaket();
//        //tambahan


        semuapengirimanList.getId_paket();
        txtidPickUpADet.setText(semuapengirimanList.getKodePengiriman());
        txtPenerimaTereteraADet.setText(semuapengirimanList.getPenerima());
        txtJenisBarangADet.setText(semuapengirimanList.getJenisBarang());
//        semuapaketItemList.getStatus_pengiriman();
//        semuapaketItemList.getStatus_pod();

        Toast.makeText(getActivity(), semuapaketItemList.getStatus_pengiriman(), Toast.LENGTH_LONG).show();
        return view;

        //PIYE jzlsjdljds itu dia tadi masi belum ke detail nya, kalo yang sebelumnya udah bisa ke detailnya cuman pas di klik buttonnya gamau lgls keluar gt
        // itu null gak dapet. terus bilair adapeyt gemana lia.t  udah bingunghg fhgmaufhhgf nambahin apalagi
        //kebiasaan. liat adaptermnta
    }

    private String getDateTime() {
        String dt;
        Date cal = Calendar.getInstance().getTime();
        dt = cal.toLocaleString();
        return dt;
    }

    private void getSpinnerPenerima() {
        listPenerima = getResources().getStringArray(R.array.spn_hubungan_penerima);

        ArrayAdapter<String> spinnerAdapterJB = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, listPenerima);
        spinnerAdapterJB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPenerima.setAdapter(spinnerAdapterJB);

        spnPenerima.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void bindData() {
        if (getArguments() != null) {
            ListPengiriman = getArguments().getString("pengiriman");
        }
        if (getArguments() != null) {
            ListPaket = getArguments().getString("paket");
        }

        semuapengirimanList = new Gson().fromJson(ListPengiriman, ItemPengiriman.class);
        semuapaketItemList = new Gson().fromJson(ListPaket, SemuapaketItem.class);
    }


    private void initComponents() {
        getDateTime();
        getSpinnerPenerima();
        bindData();
    }



    @OnClick(R.id.btnSubmitADet)
    public void doUpdate() {
        mApiService.updatePengiriman(semuapengirimanList.getId_paket(),
                txtidPickUpADet.getText().toString(),
                txtNamaPenerimaADet.getText().toString(),
                spnPenerima.getSelectedItem().toString(),
                "Delivered",
                getDateTime()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Delivered", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getActivity(), MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                } else {
//                    loading.dismiss();
                    Toast.makeText(getActivity(), "Failed to Deliver", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Internet Connection Failed", Toast.LENGTH_SHORT).show();
            }
        });


        //tambahan
        mApiService.updatePost(semuapengirimanList.getId_paket(),
                semuapaketItemList.getStatus_pengiriman(),
                "Delivered")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//
                    }
                });
        //tambahan
    }
}
