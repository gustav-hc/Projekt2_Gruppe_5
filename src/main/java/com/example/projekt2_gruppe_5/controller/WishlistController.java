package com.example.projekt2_gruppe_5.controller;

import com.example.projekt2_gruppe_5.model.User;
import com.example.projekt2_gruppe_5.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WishlistController {
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/wishlists")
    public String showWishlists(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("wishlists", wishlistService.findByUserId(user.getId()));
        return "wishlists";
    }

    @PostMapping("/wishlists")
    public String createWishlist(@RequestParam String title, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        wishlistService.createWishlist(title, user.getId());
        return "redirect:/wishlists";
    }
}