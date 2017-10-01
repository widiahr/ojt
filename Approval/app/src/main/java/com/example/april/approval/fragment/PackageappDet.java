package com.example.april.approval.fragment;


import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.april.approval.MainActivity;
import com.example.april.approval.PaketActivity;
import com.example.april.approval.R;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class PackageappDet extends Fragment {


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

    BaseApiService mApiService;

    private SemuapaketItem semuapaketItemList;

    String ListPaket;

    public PackageappDet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_packageapp_det, container, false);

        ButterKnife.bind(this, view);
        mApiService = UtilsApi.getAPIService();

        bindData();

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

        return view;
    }

//    String extraPaket = getFragmentManager().getStringExtra("paket");
//        semuapaketItemList = new Gson().fromJson(extraPaket, SemuapaketItem.class);

    private void bindData() {
        if (getArguments() != null) {
            //ini yang buat get datanya yang dari adapter tadi
            ListPaket = getArguments().getString("paket");
        }

        semuapaketItemList = new Gson().fromJson(ListPaket, SemuapaketItem.class);
    }

    @OnClick(R.id.btnProvepick)
    public void doUpdate() {

        mApiService.updatePost(semuapaketItemList.getId_paket(), "Approved", semuapaketItemList.getStatus_pod())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Approved", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getActivity(), MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                    finish();
//                    FragmentManager fragmentManager = getFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.fragment_appdetails_det, new PackageApp()).commit();
                        } else {
//                    loading.dismiss();
                            Toast.makeText(getActivity(), "Failed to Approve", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                loading.dismiss();
                        Toast.makeText(getActivity(), "Internet Connection Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
