package com.example.projekt2_gruppe_5.service;

import com.example.projekt2_gruppe_5.model.Wishlist;
import com.example.projekt2_gruppe_5.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wishlist> findByUserId(int userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public void createWishlist(String title, int userId) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Skriv venligst en titel!");
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setTitle(title);
        wishlist.setUserId(userId);
        wishlistRepository.save(wishlist);
    }

    public Wishlist findById(int id) {
        return wishlistRepository.findById(id);
    }

    public String createShareToken(int wishlistId) {
        String token = UUID.randomUUID().toString();
        wishlistRepository.saveShareToken(wishlistId, token);
        return token;
    }
}