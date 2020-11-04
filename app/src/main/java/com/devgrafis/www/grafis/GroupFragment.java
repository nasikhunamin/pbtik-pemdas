package com.devgrafis.www.grafis;


import android.app.ProgressDialog;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devgrafis.www.grafis.adapter.GroupAdapter;
import com.devgrafis.www.grafis.api.Api;
import com.devgrafis.www.grafis.model.Group;
import com.devgrafis.www.grafis.model.value;
import com.devgrafis.www.grafis.touchlistener.RecyclerTouchListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Group> group;
    private GroupAdapter groupAdapter;
    public static final String URL = "https://grafisdev.000webhostapp.com/api/";

    public GroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_group, container, false);
        recyclerView = v.findViewById(R.id.recyclerGroup);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        //recyclerView.addItemDecoration(new group.);
        groupAdapter = new GroupAdapter(getContext(), group);
        recyclerView.setAdapter(groupAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ProgressDialog progressDialog;
        group = new ArrayList<>();
        String[] nameGroup = {"Pemuda", "Kahfi"};
        for (int i= 0;i<nameGroup.length;i++){
            group.add(new Group(nameGroup[i]));
        }

       /* progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                //.client(new OkHttpClient())
                //.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<value> call = api.getGroup();
        call.enqueue(new Callback<value>() {
            @Override
            public void onResponse(Call<value> call, Response<value> response) {
                progressDialog.dismiss();
                String value = response.body().getValue();
                if (value.equals("1")){
                    group = response.body().getResultGroup();
                    Log.i("group", response.body().getResultGroup().toString());
                    groupAdapter = new GroupAdapter(getContext(), group);
                    recyclerView.setAdapter(groupAdapter);
                }
            }

            @Override
            public void onFailure(Call<value> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Network Error ! "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
*/
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
