package com.trycatch_tanmay.naamkaran;

import java.util.ArrayList;
import java.util.List;

public class WishlistManager {
    private static WishlistManager instance;
    private List<NameItem> wishlist;

    private WishlistManager() {
        wishlist = new ArrayList<>();
    }

    public static WishlistManager getInstance() {
        if (instance == null) {
            instance = new WishlistManager();
        }
        return instance;
    }

    public List<NameItem> getWishlist() {
        return wishlist;
    }

    public void addToWishlist(NameItem nameItem) {
        wishlist.add(nameItem);
    }

    public void removeFromWishlist(NameItem nameItem) {
        wishlist.remove(nameItem);
    }
}
