package com.example.projekt2_gruppe_5.model;

import java.math.BigDecimal;

public class Wish {
    private int id;
    private String name;
    private String description;
    private  BigDecimal price;
    private String link;
    private int wishlistId;
    private boolean reserved;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public int getWishlistId() { return wishlistId; }
    public void setWishlistId(int wishlistId) { this.wishlistId = wishlistId; }

    public boolean isReserved() { return reserved; }
    public void setReserved(boolean reserved) { this.reserved = reserved; }
}

