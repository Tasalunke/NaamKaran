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

public class christian_girlfrag extends Fragment {
    private RecyclerView christianGirlRecyclerView;
    private ChristianGirlNamesAdapter apiAdapterGirl;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_christian_girlfrag, container, false);
        christianGirlRecyclerView = view.findViewById(R.id.christianGirlRecyclerView);

        // Assuming you have categoryId and genderId, replace them with actual values.
        int categoryId = 10; // Christian category ID
        int genderId = 2;   // Girls gender ID

        // Make API call to get Christian girl names
        makeApiCall(categoryId, genderId);

        return view;
    }

    private void makeApiCall(int categoryId, int genderId) {
        Call<ArrayList<ChristianGirlApi_POJO>> call = Api_client.getTryCatchInterface().getChristiangirldata(categoryId, genderId);
        call.enqueue(new Callback<ArrayList<ChristianGirlApi_POJO>>() {
            @Override
            public void onResponse(Call<ArrayList<ChristianGirlApi_POJO>> call, Response<ArrayList<ChristianGirlApi_POJO>> response) {
                if (isAdded() && getActivity() != null) {
                    if (response.isSuccessful() && response.body() != null) {
                        apiAdapterGirl = new ChristianGirlNamesAdapter(requireContext(), response.body());
                        christianGirlRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                        christianGirlRecyclerView.setAdapter(apiAdapterGirl);
                    } else {
                        Toast.makeText(requireContext(), "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ChristianGirlApi_POJO>> call, Throwable t) {
                if (isAdded() && getActivity() != null) {
                    Toast.makeText(requireContext(), "Something went wrong: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
