package com.devgrafis.www.grafis;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.devgrafis.www.grafis.adapter.MaterialAdapter;
import com.devgrafis.www.grafis.api.Api;
import com.devgrafis.www.grafis.model.Material;
import com.devgrafis.www.grafis.model.value;
import com.devgrafis.www.grafis.sharedpreferences.SaveSharedPreferences;
import com.devgrafis.www.grafis.touchlistener.RecyclerTouchListener;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class MaterialFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ArrayList<Material> mMaterialData;
    public MaterialAdapter mAdapter;
    private ProgressDialog progressDialog;
    public static final String URL = "https://grafisdev.000webhostapp.com/api/";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_material, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMaterialData = new ArrayList<>();
        int gridColumn = 1;
        mRecyclerView = view.findViewById(R.id.recyclerMaterial);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), gridColumn));
        mAdapter = new MaterialAdapter(getContext(), mMaterialData);
        mRecyclerView.setAdapter(mAdapter);
        /*mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent detailMaterialActivity = new Intent(getContext(), DetailMaterialActivity.class);
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                }
                startActivity(detailMaterialActivity);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/
        initializeData();
    }

    private void initializeData() {
        TypedArray img = getResources().obtainTypedArray(R.array.MaterialImage);
        String title = "Materi";
        String subtitle[] = getResources().getStringArray(R.array.MaterialSubtitle);
        String content[] = getResources().getStringArray(R.array.MaterialConten);
        mMaterialData.clear();
        for(int i=0;i<subtitle.length;i++){
            mMaterialData.add(new Material(title +" "+ (i+1), subtitle[i], content[i], img.getResourceId(0,0)));
        }
        mAdapter.notifyDataSetChanged();
        /*progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();*/


        //Gson gson = new GsonBuilder().setLenient().create();
        //*OkHttpClient client = new OkHttpClient();*//*

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                //.client(new OkHttpClient())
                //.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<value> call = api.getMaterial();

        call.enqueue(new Callback<value>() {
            @Override
            public void onResponse(Call<value> call, Response<value> response) {
                try {
                    if (response.isSuccessful()) {
                        //Log.i("title", response.body().getMaterialTitle());
                        progressDialog.dismiss();
                        mMaterialData = new ArrayList<>();

                       *//* String s = response.body().toString();
                        JSONObject obj = new JSONObject(s.substring(s.indexOf("{"), s.lastIndexOf("{") + 1));
                        JSONArray jsonArray = obj.getJSONArray("titleMaterial");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject dataObj = jsonArray.getJSONObject(i);
                            Log.i("title", dataObj.getString("title"));
                            Log.i("content", dataObj.getString("content"));
                            mMaterialData.add(new Material((dataObj.getString("title")), "content", img.getResourceId(0, 0)));
                        }*//*
                    }
                        mAdapter = new MaterialAdapter(getContext(), mMaterialData);
                        mRecyclerView.setAdapter(mAdapter);
                    //}
                }catch (Exception ex){
                    ex.printStackTrace();
                    progressDialog.dismiss();
//                    Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<value> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Network Error ! "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    /*public void resetData(View view) {
        initializeData();
    }*/
}
