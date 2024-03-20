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

public class ChristianFragment extends Fragment {
    private RecyclerView christianRecyclerView;
    private Api_Adapter_Christian apiAdapterChristian;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chirstan, container, false);
        christianRecyclerView = view.findViewById(R.id.christianRecyclerView);

        // Assuming you have categoryId and genderId, replace them with actual values.
        int categoryId = 10; // Christian category ID
        int genderId = 1; // Boys gender ID

        // Make API call to get Christian names
        makeApiCall(categoryId, genderId);

        return view;
    }

    private void makeApiCall(int categoryId, int genderId) {
        Call<ArrayList<Api_POJO_Christian>> call = Api_client.getTryCatchInterface().getChristianData(categoryId, genderId);
        call.enqueue(new Callback<ArrayList<Api_POJO_Christian>>() {
            @Override
            public void onResponse(Call<ArrayList<Api_POJO_Christian>> call, Response<ArrayList<Api_POJO_Christian>> response) {
                if (isAdded() && getActivity() != null) {
                    if (response.isSuccessful() && response.body() != null) {
                        apiAdapterChristian = new Api_Adapter_Christian(requireContext(), response.body());
                        christianRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                        christianRecyclerView.setVisibility(View.VISIBLE);
                        christianRecyclerView.setAdapter(apiAdapterChristian);
                    } else {
                        Toast.makeText(requireContext(), "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Api_POJO_Christian>> call, Throwable t) {
                Toast.makeText(requireContext(), "Something went wrong: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    }


