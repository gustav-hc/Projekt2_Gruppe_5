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
        if (wish.getDescription() == null || wish.getDescription().isEmpty()) {
            wish.setDescription(null);
        }

        if (wish.getLink().isEmpty()) {
            wish.setLink(null);
        }

        wishRepository.save(wish);
        return true;
    }

    public List<Wish> findByWishlistId(int wishlistId) {
        return wishRepository.findByWishlistId(wishlistId);
    }
    public boolean reserveWish(int wishlistId, int wishId) {
        boolean belongsToList = wishRepository.findByWishlistId(wishlistId)
                .stream()
                .anyMatch(w -> w.getId() == wishId);
        if (!belongsToList) {
            return false;
        }
        return wishRepository.reserve(wishId);
    }

    public Wish findById(int id) {
       return wishRepository.findById(id);
    }
}
