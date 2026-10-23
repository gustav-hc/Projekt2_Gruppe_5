package com.example.projekt2_gruppe_5.controller;

import com.example.projekt2_gruppe_5.model.User;
import com.example.projekt2_gruppe_5.model.Wish;
import com.example.projekt2_gruppe_5.model.Wishlist;
import com.example.projekt2_gruppe_5.repository.WishlistRepository;
import com.example.projekt2_gruppe_5.service.WishService;
import com.example.projekt2_gruppe_5.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class WishController {
    private final WishService wishService;
    private final WishlistService wishlistService;

    public WishController(WishService wishService, WishlistService wishlistService) {
        this.wishService = wishService;
        this.wishlistService = wishlistService;
    }

    @GetMapping("/wishlists/{wishlistId}/wishes/new")
    public String showAddForm(@PathVariable int wishlistId,
                              HttpSession session,
                              Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("wishlistId", wishlistId);
        return "wish-form";
    }

    @PostMapping("/wishes")
    public String addWish(@RequestParam String name,
                          @RequestParam(required = false) String description,
                          @RequestParam(required = false) BigDecimal price,
                          @RequestParam(required = false) String link,
                          @RequestParam int wishlistId,
                          HttpSession session, Model model) {
        Wish wish = new Wish();
        wish.setName(name);
        wish.setDescription(description);
        wish.setPrice(price);
        wish.setLink(link);
        wish.setWishlistId(wishlistId);

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        if (wishService.addWish(wish)) {
            return "redirect:/wishlists";
        }

            model.addAttribute("error", "mangler navn");
            model.addAttribute("wishlistId", wishlistId);
            return "wish-form";
    }

    @PostMapping("/shared/{token}/wishes/{wishId}/reserve")
    public String reserveWish(@PathVariable String token, @PathVariable int wishId) {
        Wishlist wishlist = wishlistService.findByShareToken(token);
        if (wishlist == null) {
            return "redirect:/login";
        }
        wishService.reserveWish(wishlist.getId(), wishId);
        return "redirect:/shared/" + token;
    }
    }

