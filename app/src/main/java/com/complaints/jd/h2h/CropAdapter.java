package com.complaints.jd.h2h;

/**
 * Created by Nitin Tonger on 25-03-2017.
 */
import android.app.Dialog;
import android.content.Context;
import android.service.voice.VoiceInteractionSession;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CropAdapter extends RecyclerView.Adapter<CropAdapter.MyViewHolder> {

    private Context mContext;
    private List<Crop> albumList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count,item;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.croptype);
            count = (TextView) view.findViewById(R.id.quantity);
            thumbnail = (ImageView) view.findViewById(R.id.cropimage);
            item=(TextView)view.findViewById(R.id.center1);
        }
    }
    String user_email;

    public CropAdapter(Context mContext, List<Crop> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crop_card, parent, false);

        return new MyViewHolder(itemView);
    }
    Crop album;
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        album = albumList.get(position);

        holder.title.setText(album.getproduct());
        holder.count.setText(album.getPrice());
        Glide.with(mContext).load(album.getImgurl()).into(holder.thumbnail);
        holder.item.setText(album.getBarcode());
        //holder.item.setVisibility(View.INVISIBLE);

    }

    String dataid;


    @Override
    public int getItemCount() {
        return albumList.size();
    }
}