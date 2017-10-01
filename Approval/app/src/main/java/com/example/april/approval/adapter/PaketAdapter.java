package com.example.april.approval.adapter;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.april.approval.R;

import com.example.april.approval.fragment.ApprovaldetailsDet;
import com.example.april.approval.fragment.PackageappDet;
import com.example.april.approval.model.SemuapaketItem;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Aprilia on 08/09/2017.
 */

public class PaketAdapter extends RecyclerView.Adapter<PaketAdapter.PaketHolder> {
    Context mContext;
    List<SemuapaketItem> semuapaketItemList;




    public PaketAdapter(Context context, List<SemuapaketItem> paketList) {
        this.mContext = context;
        semuapaketItemList = paketList;
    }

    @Override
    public PaketAdapter.PaketHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.flist_packageapp, parent, false);
        return new PaketHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PaketAdapter.PaketHolder holder, int position) {
        final SemuapaketItem semuapaketItem = semuapaketItemList.get(position);
        holder.txttanggalDet.setText(semuapaketItem.getDate());
        holder.txtidPickUpDet.setText(semuapaketItem.getKodePengiriman());
        holder.txtNamaPenerimaDet.setText(semuapaketItem.getPenerima());


    }

    @Override
    public int getItemCount() {
        return semuapaketItemList.size();
    }

    public class PaketHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txttanggalDet)
        TextView txttanggalDet;
        @BindView(R.id.txtidPickUpDet)
        TextView txtidPickUpDet;
        @BindView(R.id.txtNamaPenerimaDet)
        TextView txtNamaPenerimaDet;


        public PaketHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            SemuapaketItem semuapaketItem = semuapaketItemList.get(position);
            String extraPaket = new Gson().toJson(semuapaketItem, SemuapaketItem.class);

            //untuk ngirim data ke fragment lain, kalo di activity pakenya putExtra, kalo fragment namanya argument

            Bundle bundle = new Bundle();

            // yang "paket" itu nama opsional, tapi harus sama sama yang buat nge get nya (di PackageappDet)
            bundle.putString("paket", extraPaket);

            PackageappDet packageappDet = new PackageappDet();
            packageappDet.setArguments(bundle);


            //tambahan
            ApprovaldetailsDet approvaldetailsDet = new ApprovaldetailsDet();
            approvaldetailsDet.setArguments(bundle);
            //tambahan


            FragmentManager fm = ((Activity) mContext).getFragmentManager();
            fm.beginTransaction().replace(R.id.contennya, packageappDet).addToBackStack("").commit();

        }
    }

}


