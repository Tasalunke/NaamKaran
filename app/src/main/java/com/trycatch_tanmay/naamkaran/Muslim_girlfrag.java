package com.trycatch_tanmay.naamkaran;

import android.annotation.SuppressLint;
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

public class Muslim_girlfrag extends Fragment {
    private RecyclerView muslimGirlRecyclerView;
    private MuslimGirlNamesAdapter muslimGirlAdapter;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_muslim_girlfrag, container, false);
        muslimGirlRecyclerView = view.findViewById(R.id.muslimGirlRecyclerView);

        // Assuming you have categoryId and genderId, replace them with actual values.
        int categoryId = 8; // Muslim category ID
        int genderId = 2;   // Girls gender ID

        // Make API call to get Muslim girl names
        makeApiCall(categoryId, genderId);

        return view;
    }

    private void makeApiCall(int categoryId, int genderId) {
        Call<ArrayList<MuslimGirlApi_POJO>> call = Api_client.getTryCatchInterface().getMuslimgirldata(categoryId, genderId);
        call.enqueue(new Callback<ArrayList<MuslimGirlApi_POJO>>() {
            @Override
            public void onResponse(Call<ArrayList<MuslimGirlApi_POJO>> call, Response<ArrayList<MuslimGirlApi_POJO>> response) {
                if (isAdded() && getActivity() != null) {
                    if (response.isSuccessful() && response.body() != null) {
                        muslimGirlAdapter = new MuslimGirlNamesAdapter(requireContext(), response.body());
                        muslimGirlRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                        muslimGirlRecyclerView.setAdapter(muslimGirlAdapter);
                    } else {
                        Toast.makeText(requireContext(), "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MuslimGirlApi_POJO>> call, Throwable t) {
                if (isAdded() && getActivity() != null) {
                    Toast.makeText(requireContext(), "Something went wrong: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
