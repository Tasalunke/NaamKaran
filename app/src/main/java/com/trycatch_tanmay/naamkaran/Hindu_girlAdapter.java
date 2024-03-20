package com.trycatch_tanmay.naamkaran;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Hindu_girlAdapter extends RecyclerView.Adapter<Hindu_girlAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HinduGirlApiPOJO> girlApiList;

    public Hindu_girlAdapter(Context context, ArrayList<HinduGirlApiPOJO> girlApiList) {
        this.context = context;
        this.girlApiList = girlApiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.girl_itemview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HinduGirlApiPOJO girlApiPOJO = girlApiList.get(position);
        holder.girlName.setText("Name: " + girlApiPOJO.getName());
        holder.girlNameMeaning.setText("Meaning: " + girlApiPOJO.getMeaning());

        holder.copyButton.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Name", girlApiPOJO.getName());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context, "Name copied to clipboard", Toast.LENGTH_SHORT).show();
        });
        holder.heart_button.setOnClickListener(new View.OnClickListener() {
            private boolean isFavorite = false;

            @Override
            public void onClick(View view) {
                isFavorite = !isFavorite;
                if (isFavorite) {
                    NameItem nameItem = new NameItem(girlApiPOJO.getName(), girlApiPOJO.getMeaning());
                    WishlistManager.getInstance().addToWishlist(nameItem);
                    Toast.makeText(context, "Added to Wishlist", Toast.LENGTH_SHORT).show();
                }

                // Update heart button state (You can set different heart icons for selected and unselected states)
                holder.heart_button.setImageResource(isFavorite ? R.drawable.heart_logo : R.drawable.heart_selected_logo);
            }
        });




        holder.shareButton.setOnClickListener(v -> {
            String name = girlApiPOJO.getName();
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, name);
            context.startActivity(Intent.createChooser(shareIntent, "Share name via"));
        });
    }

    @Override
    public int getItemCount() {
        return girlApiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView girlName;
        TextView girlNameMeaning;
        ImageView copyButton;
        ImageView heart_button;
        ImageView shareButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            girlName = itemView.findViewById(R.id.text_name_boy); // Assuming this is the correct ID for girl names
            girlNameMeaning = itemView.findViewById(R.id.text_meaning_boy); // Assuming this is the correct ID for name meanings
            copyButton = itemView.findViewById(R.id.copy);
            heart_button=itemView.findViewById(R.id.heart_button);
            shareButton = itemView.findViewById(R.id.share);
        }
    }
}
