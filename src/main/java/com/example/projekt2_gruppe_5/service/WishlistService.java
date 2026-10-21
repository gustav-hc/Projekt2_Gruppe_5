package com.example.projekt2_gruppe_5.service;

import com.example.projekt2_gruppe_5.model.Wishlist;
import com.example.projekt2_gruppe_5.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wishlist> findByUserId(int userId) {
        return wishlistRepository.findByUserId(userId);
    }
}