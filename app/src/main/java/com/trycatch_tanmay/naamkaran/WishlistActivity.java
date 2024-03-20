package com.trycatch_tanmay.naamkaran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WishlistActivity extends AppCompatActivity implements CustomNamesAdapter.OnItemClickListener {
    Button about_us;
    private List<NameItem> wishlistItems;
    
   private CustomNamesAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        about_us=findViewById(R.id.about_us);
        getWindow().setStatusBarColor(Integer.parseInt(String.valueOf(getColor(R.color.Blue))));

        // Assuming you have a RecyclerView in your activity_wishlist.xml layout file with id recyclerViewWishlist
        RecyclerView recyclerViewWishlist = findViewById(R.id.recyclerViewWishlist);

        // Initialize the wishlistItems with the data from the WishlistManager
        wishlistItems = WishlistManager.getInstance().getWishlist();

        // Initialize and set up the CustomNamesAdapter for the RecyclerView
        adapter = new CustomNamesAdapter(this,wishlistItems);
        adapter.setOnItemClickListener(this);

        // Set the layout manager and adapter for the RecyclerView
        recyclerViewWishlist.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewWishlist.setAdapter(adapter);
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WishlistActivity.this,AboutUsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(NameItem nameItem) {
        // Handle the item click, add the nameItem to your wishlist
        WishlistManager.getInstance().addToWishlist(nameItem);

        // Update the RecyclerView with the updated wishlist
        wishlistItems = WishlistManager.getInstance().getWishlist();

        // Update the data inside the adapter and notify the adapter of the changes
        adapter.setItems(wishlistItems);
        adapter.notifyDataSetChanged();
    }
}
