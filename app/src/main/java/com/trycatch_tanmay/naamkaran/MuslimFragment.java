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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MuslimFragment extends Fragment {
    private RecyclerView muslimRecyclerView;
    private Api_Adapter_Muslim apiAdapterMuslim;
    private Context context;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_muslim, container, false);
        muslimRecyclerView = view.findViewById(R.id.muslimRecyclerView);

        // Assuming you have categoryId and genderId, replace them with actual values.
        int categoryId = 8; // Muslim category ID
        int genderId = 1;   // Boys gender ID

        // Make API call to get Muslim names
        makeApiCall(categoryId, genderId);

        return view;
    }

    private void makeApiCall(int categoryId, int genderId) {
        Call<ArrayList<Api_POJO_Muslim>> call = Api_client.getTryCatchInterface().getMuslimData(categoryId, genderId);
        call.enqueue(new Callback<ArrayList<Api_POJO_Muslim>>() {
            @Override
            public void onResponse(Call<ArrayList<Api_POJO_Muslim>> call, Response<ArrayList<Api_POJO_Muslim>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.isSuccessful() && response.body() != null) {
                        apiAdapterMuslim = new Api_Adapter_Muslim(requireContext(), response.body());
                        muslimRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                        muslimRecyclerView.setVisibility(View.VISIBLE);
                        muslimRecyclerView.setAdapter(apiAdapterMuslim);
                    } else {
                        Toast.makeText(requireContext(), "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Api_POJO_Muslim>> call, Throwable t) {
                Toast.makeText(requireContext(), "Something went wrong: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}