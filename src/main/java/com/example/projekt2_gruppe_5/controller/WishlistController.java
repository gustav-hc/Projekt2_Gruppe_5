package com.example.projekt2_gruppe_5.controller;

import com.example.projekt2_gruppe_5.model.User;
import com.example.projekt2_gruppe_5.model.Wish;
import com.example.projekt2_gruppe_5.model.Wishlist;
import com.example.projekt2_gruppe_5.service.WishService;
import com.example.projekt2_gruppe_5.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WishlistController {
    private final WishlistService wishlistService;
    private final WishService wishService;

    public WishlistController(WishlistService wishlistService, WishService wishService) {
        this.wishlistService = wishlistService;
        this.wishService = wishService;
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
    @GetMapping("/wishlists/{id}")
    public String showWishlist(@PathVariable int id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Wishlist wishlist = wishlistService.findById(id);
        if (wishlist == null || wishlist.getUserId() != user.getId()) {
            return "redirect:/wishlists";
        }

        model.addAttribute("wishlist", wishlist);
        model.addAttribute("wishes", wishService.findByWishlistId(id));
        return "wishlist";
    }

    @GetMapping("/wishlists/{id}/edit")
    public String showEditForm(@PathVariable int id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Wishlist wishlist = wishlistService.findById(id);
        if (wishlist == null || wishlist.getUserId() != user.getId()) {
            return "redirect:/wishlists";
        }

        model.addAttribute("wishlist", wishlist);
        return "wishlist-edit";
    }

    @PostMapping("/wishlists/{id}/edit")
    public String updateWishlist(@PathVariable int id,
                                 @RequestParam String title,
                                 HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Wishlist wishlist = wishlistService.findById(id);
        if (wishlist == null || wishlist.getUserId() != user.getId()) {
            return "redirect:/wishlists";
        }

        wishlistService.updateTitle(id, title);
        return "redirect:/wishlists/" + id;
    }

    @PostMapping("/wishlists/{id}/delete")
    public String deleteWishlist(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Wishlist wishlist = wishlistService.findById(id);
        if (wishlist == null || wishlist.getUserId() != user.getId()) {
            return "redirect:/wishlists";
        }

        wishlistService.deleteWishlist(id);
        return "redirect:/wishlists";
    }

    @PostMapping("/wishlists/{id}/share")
    public String shareWishList(@PathVariable int id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        String token = wishlistService.createShareToken(id);
        String link = "http://localhost:8080/shared/" + token;

        model.addAttribute("shareLink", link);
        model.addAttribute("wishlists", wishlistService.findByUserId(user.getId()));
        return "wishlists";
    }

    @GetMapping("/shared/{token}")
    public String showSharedWishList(@PathVariable String token,
                                     Model model) {
       Wishlist wishlist = wishlistService.findByShareToken(token);
       if (wishlist == null) {
           return "redirect:/login";
       }

       int wishId = wishlist.getId();
       model.addAttribute("wishes", wishService.findByWishlistId(wishId));
       model.addAttribute("wishlist", wishlist);
       model.addAttribute("token", token);
       return "shared-wishlist";
    }

    @GetMapping("/shared/{token}/wishes/{id}")
    public String showSharedWish(@PathVariable String token,
                                 @PathVariable int id, Model model) {
        Wishlist wishlist = wishlistService.findByShareToken(token);
        if (wishlist == null) {
            return "redirect:/login";
        }

        Wish wish =  wishService.findById(id);
        if (wish == null) {
            return "redirect:/shared/" + token;
        }

        if (wish.getWishlistId() == wishlist.getId()) {
            model.addAttribute("wish", wish);
            model.addAttribute("wishlist", wishlist);
            model.addAttribute("token", token);

            return "shared-wish";
        }
        return "redirect:/shared/" + token;
    }
}