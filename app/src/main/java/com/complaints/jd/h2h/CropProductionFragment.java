package com.complaints.jd.h2h;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CropProductionFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    List<Crop> cropList=new ArrayList<>();

    public CropProductionFragment() {
        // Required empty public constructor
    }
    public Context mycontext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mycontext = container.getContext();
        return inflater.inflate(R.layout.fragment_crop_production, container, false);

    }

    @Override
    public void onRefresh() {
        getData();
    }
    public void getData()
    {
        Crop a = new Crop("aaa","sasa","sasa","asdsa");
        cropList.add(a);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }


    CropAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView=(RecyclerView)getActivity().findViewById(R.id.recyclerview);
        swipeRefreshLayout=(SwipeRefreshLayout)getActivity().findViewById(R.id.swipe);
        adapter = new CropAdapter(mycontext, cropList);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mycontext, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        onRefresh();
    }
}
