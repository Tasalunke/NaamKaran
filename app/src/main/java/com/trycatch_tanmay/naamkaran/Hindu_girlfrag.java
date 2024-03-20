package com.trycatch_tanmay.naamkaran;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Hindu_girlfrag extends Fragment {

    private RecyclerView hinduGirlRecyclerView;
    private Hindu_girlAdapter apiAdapterGirl;
    private Context context;
    GifImageView loader;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hindu_girlfrag, container, false);
        hinduGirlRecyclerView = view.findViewById(R.id.hinduGirlRecyclerView);
        loader = view.findViewById(R.id.loader);

        // Assuming you have categoryId and genderId, replace them with actual values.
        int categoryId = 3; // Hindu category ID
        int genderId = 2;   // Girls gender ID

        // Make API call to get Hindu girl names
        makeApiCall(categoryId, genderId);

        return view;
    }

    private void makeApiCall(int categoryId, int genderId) {
        Call<ArrayList<HinduGirlApiPOJO>> call = Api_client.getTryCatchInterface().getHindgirludata(categoryId, genderId);
        call.enqueue(new Callback<ArrayList<HinduGirlApiPOJO>>() {
            @Override
            public void onResponse(Call<ArrayList<HinduGirlApiPOJO>> call, Response<ArrayList<HinduGirlApiPOJO>> response) {
                if (isAdded() && getActivity() != null) {
                    if (response.isSuccessful() && response.body() != null) {
                        apiAdapterGirl = new Hindu_girlAdapter(requireContext(), response.body());
                        hinduGirlRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                        loader.setVisibility(View.GONE);
                        hinduGirlRecyclerView.setVisibility(View.VISIBLE);
                        hinduGirlRecyclerView.setAdapter(apiAdapterGirl);
                    } else {
                        Toast.makeText(requireContext(), "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<HinduGirlApiPOJO>> call, Throwable t) {
                if (isAdded() && getActivity() != null) {
                    Toast.makeText(requireContext(), "Something went wrong: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}