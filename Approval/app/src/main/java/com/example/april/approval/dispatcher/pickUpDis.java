package com.example.april.approval.dispatcher;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.april.approval.model.ResponseKurir;
import com.example.april.approval.model.SemuakurirItem;
import com.example.april.approval.util.api.BaseApiService;
import com.example.april.approval.util.api.UtilsApi;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindFloat;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.april.approval.MainActivity;
import com.example.april.approval.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;

import static com.example.april.approval.R.id.add;
import static com.example.april.approval.R.id.spnKurir;


public class pickUpDis extends AppCompatActivity {


    @BindView(R.id.txtidPickUp)
    TextView tidPickUp;
    @BindView(R.id.txtNamaPengirim)
    EditText tNamaPengirim;
    @BindView(R.id.txtKontakPengirim)
    EditText tKontakPengirim;
    @BindView(R.id.txtNamaPenerima)
    EditText tNamaPenerima;
    @BindView(R.id.txtAlamat1)
    EditText tAlamat1;
    @BindView(R.id.txtKontakPenerima)
    EditText tKontakPenerima;
    @BindView(R.id.spnJenisBarang)
    Spinner sJenisBarang;
    @BindView(R.id.txtQty)
    EditText tQty;
    @BindView(R.id.txtWidth)
    EditText tWidth;
    @BindView(R.id.txtLength)
    EditText tLength;
    @BindView(R.id.txtHeight)
    EditText tHeight;
    @BindView(R.id.spnKendaraan)
    Spinner sKendaraan;
    @BindView(R.id.txtKet)
    EditText tKet;
    @BindView(R.id.txttanggal)
    TextView ttanggal;
    @BindView(R.id.spnKurir)
    Spinner sKurir;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    /**
     * Called when the activity is first created.
     */

    private String[] listJenisBarang;
    private String[] listKendaraan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up_dis);

        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder idNya = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            char c = chars[random.nextInt(chars.length)];
            idNya.append(c);
        }
        String output = idNya.toString();

        TextView idPickUp = (TextView) findViewById(R.id.txtidPickUp);
        idPickUp.setText(output.toString());


        ImageButton back = (ImageButton) findViewById(R.id.btnback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickup = new Intent(pickUpDis.this, MainActivity.class);
                startActivity(pickup);
            }
        });

        TextView date = (TextView) findViewById(R.id.txttanggal);

        String dt;
        Date cal = (Date) Calendar.getInstance().getTime();
        dt = cal.toLocaleString();
        date.setText(dt.toString());


        listJenisBarang = getResources().getStringArray(R.array.spinner_jenis_barang);
        final Spinner spnJenisBarang = (Spinner) findViewById(R.id.spnJenisBarang);

        ArrayAdapter<String> spinnerAdapterJB = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, listJenisBarang);
        spinnerAdapterJB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnJenisBarang.setAdapter(spinnerAdapterJB);

        spnJenisBarang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        listKendaraan = getResources().getStringArray(R.array.spinner_kendaraan);
        final Spinner spnKendaraan = (Spinner) findViewById(R.id.spnKendaraan);

        ArrayAdapter<String> spinnerAdapterK = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, listKendaraan);
        spinnerAdapterK.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnKendaraan.setAdapter(spinnerAdapterK);

//        spnKendaraan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                adapterView.getItemAtPosition(i).toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestSubmitPaket();
            }
        });

//        initSpnKurir();
//        sKurir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
//                String selectedItem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(mContext, "Kamu memilih kurir " + selectedItem, Toast.LENGTH_SHORT);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


    }

//    private void initSpnKurir() {
//        loading = ProgressDialog.show(mContext, null, "harap tunggu...", true, false);
//
//        mApiService.getSemuakurir().enqueue(new Callback<ResponseKurir>() {
//            @Override
//            public void onResponse(Call<ResponseKurir> call, Response<ResponseKurir> response) {
//                if (response.isSuccessful()) {
////                    loading.dismiss();
//                    List<SemuakurirItem> semuakurirItems = response.body().getSemuakurir();
//                    List<String> listSpn = new ArrayList<String>();
//                    for (int i = 0; i < semuakurirItems.size(); i++) {
//                        listSpn.add(semuakurirItems.get(i).getNama());
//                    }
//                    ArrayAdapter<String> adapternya = new ArrayAdapter<String>(mContext,
//                            android.R.layout.simple_spinner_item, listSpn);
//                    adapternya.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    sKurir.setAdapter(adapternya);
//
//                } else {
//                    loading.dismiss();
//                    Toast.makeText(mContext, "Gagal mengambil data kurir", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseKurir> call, Throwable t) {
//                loading.dismiss();
//                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }

    private void requestSubmitPaket() {
        loading = ProgressDialog.show(mContext, null, "Please Wait..", true, false);

        mApiService.submitPaketRequest(
                tidPickUp.getText().toString(),
                tNamaPengirim.getText().toString(),
                tKontakPengirim.getText().toString(),
                tNamaPenerima.getText().toString(),
                tAlamat1.getText().toString(),
                tKontakPenerima.getText().toString(),
                sJenisBarang.getSelectedItem().toString(),
                tQty.getText().toString(),
                tWidth.getText().toString(),
                tLength.getText().toString(),
                tHeight.getText().toString(),
                sKendaraan.getSelectedItem().toString(),
                tKet.getText().toString(),
                "Pending",
                ttanggal.getText().toString()
               )
                .enqueue(new Callback<ResponseBody>() {
// sKurir.getSelectedItem().toString()
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            Toast.makeText(mContext, "Berhasil menambahkan data!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mContext, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                        } else {
                            loading.dismiss();
                            Toast.makeText(mContext, "Gagal menambahkan data!", Toast.LENGTH_SHORT).show();
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










