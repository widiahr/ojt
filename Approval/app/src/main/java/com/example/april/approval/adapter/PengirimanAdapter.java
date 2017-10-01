package com.example.april.approval.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.april.approval.R;

import com.example.april.approval.fragment.ApprovaldetailsDet;

import com.example.april.approval.fragment.PackageappDet;
import com.example.april.approval.model.ItemPengiriman;
import com.example.april.approval.model.SemuapaketItem;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hp on 9/22/2017.
 */


public class PengirimanAdapter extends RecyclerView.Adapter<PengirimanAdapter.PengirimanHolder> {
    Context mContext;
    List<ItemPengiriman> ItemPengirimanList;

//    //tambahan
//    List<SemuapaketItem> semuapaketItemList;
//    //tambahan
    public PengirimanAdapter(Context context, List<ItemPengiriman> pengirimanList) {
        this.mContext = context;
        ItemPengirimanList = pengirimanList;

//        //tambahan
//        semuapaketItemList = paketList;
//        //tambahan
    }

    @Override
    public PengirimanAdapter.PengirimanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.flist_approvaldetails, parent, false);
        return new PengirimanHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PengirimanAdapter.PengirimanHolder holder, int position) {

        final ItemPengiriman itemPengiriman = ItemPengirimanList.get(position);

        holder.txtidPickUpAD.setText(itemPengiriman.getKodePengiriman());
        holder.txtPenerimaAD.setText(itemPengiriman.getPenerima());
        holder.txtJenisBarangAD.setText(itemPengiriman.getJenisBarang());


//        //tambahan
//        final SemuapaketItem semuapaketItem = semuapaketItemList.get(position);
//        semuapaketItem.getId_paket();
//        semuapaketItem.getStatus_pengiriman();
//        semuapaketItem.getStatus_pod();
//        //tambahan
    }

    @Override
    public int getItemCount() {
        return ItemPengirimanList.size();
    }


    public class PengirimanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txtidPickUpAD)
        TextView txtidPickUpAD;
        @BindView(R.id.txtPenerimaAD)
        TextView txtPenerimaAD;
        @BindView(R.id.txtJenisBarangAD)
        TextView txtJenisBarangAD;

        public PengirimanHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ItemPengiriman itemPengiriman = ItemPengirimanList.get(position);
            String extraPengiriman = new Gson().toJson(itemPengiriman, ItemPengiriman.class);

            Bundle bundle = new Bundle();

            bundle.putString("pengiriman", extraPengiriman);

            ApprovaldetailsDet approvaldetailsDet = new ApprovaldetailsDet();
            approvaldetailsDet.setArguments(bundle);

            FragmentManager fm = ((Activity) mContext).getFragmentManager();
            fm.beginTransaction().replace(R.id.contennya, approvaldetailsDet).addToBackStack("").commit();
//
//            //tambahan
//            SemuapaketItem semuapaketItem = semuapaketItemList.get(position);
//            String extraPaket = new Gson().toJson(semuapaketItem, SemuapaketItem.class);
//            Bundle bundlePaket = new Bundle();
//            bundlePaket.putString("paket", extraPaket);
//            PackageappDet packageappDet = new PackageappDet();
//            packageappDet.setArguments(bundlePaket);
//            //tambahan
        }
    }
}
