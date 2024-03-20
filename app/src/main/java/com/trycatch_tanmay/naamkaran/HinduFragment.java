package com.trycatch_tanmay.naamkaran;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HinduFragment extends Fragment {
    private RecyclerView hinduRecyclerView;
    private Api_Adapter_boy apiAdapterBoy;
    private Context context;
    GifImageView loader;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hindu, container, false);
        hinduRecyclerView = view.findViewById(R.id.hinduRecyclerView);
        loader = view.findViewById(R.id.loader);

        // Assuming you have categoryId and genderId, replace them with actual values.
        int categoryId = 3; // Hindu category ID
        int genderId = 1;   // Boys gender ID

        // Make API call to get Hindu names
        makeApiCall(categoryId, genderId);

        return view;
    }

    private void makeApiCall(int categoryId, int genderId) {
        Call<ArrayList<Api_POJO>> call = Api_client.getTryCatchInterface().getHinduData(categoryId, genderId);
        call.enqueue(new Callback<ArrayList<Api_POJO>>() {
            @Override
            public void onResponse(Call<ArrayList<Api_POJO>> call, Response<ArrayList<Api_POJO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.isSuccessful() && response.body() != null) {
                        apiAdapterBoy = new Api_Adapter_boy(requireContext(), response.body());
                        hinduRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                        loader.setVisibility(View.GONE);
                        hinduRecyclerView.setVisibility(View.VISIBLE);
                        hinduRecyclerView.setAdapter(apiAdapterBoy);
                    } else {
                        Toast.makeText(requireContext(), "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Response not successful", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ArrayList<Api_POJO>> call, Throwable t) {
                Toast.makeText(requireContext(), "Something went wrong: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}



