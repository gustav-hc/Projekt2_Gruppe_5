package com.example.projekt2_gruppe_5.service;

import com.example.projekt2_gruppe_5.repository.WishRepository;

public class WishService {
    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }
}
