package com.example.projekt2_gruppe_5.service;

import com.example.projekt2_gruppe_5.model.Wish;
import com.example.projekt2_gruppe_5.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {
    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public boolean addWish(Wish wish) {
        if (wish.getName() == null || wish.getName().isBlank()) {
            return false;
        }
        wishRepository.save(wish);
        return true;
    }

    public List<Wish> findByWishlistId(int wishlistId) {
        return wishRepository.findByWishlistId(wishlistId);
    }

    public Wish findById(int id) {
       return wishRepository.findById(id);
    }
}
