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
import java.util.Set;
import java.util.HashSet;
import java.util.Set;

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
    public String reserveWish(@PathVariable String token,
                              @PathVariable int wishId,
                              HttpSession session) {
        Wishlist wishlist = wishlistService.findByShareToken(token);
        if (wishlist == null) {
            return "redirect:/login";
        }
        if (wishService.reserveWish(wishlist.getId(), wishId)) {
            getMyReservations(session).add(wishId);
        }
        return "redirect:/shared/" + token;
    }
    @PostMapping ("/shared/{token}/wishes/{wishId}/unreserve")
    public String unreserveWish(@PathVariable String token,
                                @PathVariable int wishId,
                                HttpSession session) {
        Wishlist wishlist = wishlistService.findByShareToken(token);
        if (wishlist == null) {
            return "redirect:/login";
        }
        Set<Integer> mine = getMyReservations(session);
        if (mine.contains(wishId) && wishService.unreserveWish(wishlist.getId(), wishId)) {
            mine.remove(wishId);
        }
        return "redirect:/shared/" + token;
    }

    @SuppressWarnings("unchecked")
    private Set<Integer> getMyReservations(HttpSession session) {
        Set<Integer> ids = (Set<Integer>) session.getAttribute("myReservations");
        if (ids == null) {
            ids = new HashSet<>();
            session.setAttribute("myReservations", ids);
        }
        return ids;
    }
    }


