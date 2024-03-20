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
import java.util.List;

public class CustomNamesAdapter extends RecyclerView.Adapter<CustomNamesAdapter.ViewHolder> {
    private List<NameItem> nameItems;
    private OnItemClickListener listener;
    private List<NameItem> items;

    public CustomNamesAdapter(List<NameItem> nameItems) {
        this.nameItems = nameItems;
    }

    public void setItems(List<NameItem> items) {
        this.items = items;
    }

    public List<NameItem> getItems() {
        return items;
    }

    public interface OnItemClickListener {
        void onItemClick(NameItem nameItem);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    private Context context;

    public CustomNamesAdapter(Context context, List<NameItem> nameItems) {
        this.context = context;
        this.nameItems = nameItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView meaningTextView;
        public ImageView shareButton;
        public ImageView copyButton;
        public ImageView removeButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_name);
            meaningTextView = itemView.findViewById(R.id.text_meaning);
            shareButton = itemView.findViewById(R.id.share);
            copyButton = itemView.findViewById(R.id.copy);
            removeButton = itemView.findViewById(R.id.remove_button);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NameItem nameItem = nameItems.get(position);

        // Bind data to views
        holder.nameTextView.setText("Name: " + nameItem.getName());
        holder.meaningTextView.setText("Meaning: " + nameItem.getMeaning());

        // Set onClickListeners for buttons (Share, Copy, Remove)
        holder.shareButton.setOnClickListener(view -> {
            String name = nameItem.getName();
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, name);
            context.startActivity(Intent.createChooser(shareIntent, "Share name via"));
        });

        holder.copyButton.setOnClickListener(view -> {
            // Handle copy button click
            copyToClipboard(nameItem.getName());

        });

        holder.removeButton.setOnClickListener(view -> {
            // Handle remove button click
            removeItem(position);
        });
    }

    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Name", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Name copied to clipboard", Toast.LENGTH_SHORT).show();
    }

    private void removeItem(int position) {
        nameItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, nameItems.size());
    }

    @Override
    public int getItemCount() {
        return nameItems.size();
    }
}
