package com.example.projekt2_gruppe_5.repository;


import com.example.projekt2_gruppe_5.model.Wish;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishRepository {
    private final DataSource dataSource;

    public WishRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Wish wish) {
        String sql = "INSERT INTO wish (name, description, price, link, wishlist_id) VALUES (?,?,?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, wish.getName());
            preparedStatement.setString(2, wish.getDescription());
            preparedStatement.setBigDecimal(3, wish.getPrice());
            preparedStatement.setString(4, wish.getLink());
            preparedStatement.setInt(5, wish.getWishlistId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke gemme ønske", e);

        }

    }

    public void deleteById(int id) {
        String sql = "DELETE FROM wish WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("kunne ikke slette ønske", e);
        }


    }

    public List<Wish> findByWishlistId(int wishlistId) {
        List<Wish> wishes = new ArrayList<>();
        String sql = "SELECT * FROM wish WHERE wishlist_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, wishlistId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Wish wish = new Wish();
                    wish.setId(resultSet.getInt("id"));
                    wish.setName(resultSet.getString("name"));
                    wish.setDescription(resultSet.getString("description"));
                    wish.setPrice(resultSet.getBigDecimal("price"));
                    wish.setLink(resultSet.getString("link"));
                    wish.setWishlistId(resultSet.getInt("wishlist_id"));
                    wish.setReserved(resultSet.getBoolean("reserved"));
                    wishes.add(wish);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke hente ønsker", e);
        }
        return wishes;
    }

    public int findWishlistIdByUserId(int userId) {
        String sql = "SELECT id FROM wishlist WHERE user_id =? ";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke hente ønskeliste", e);
        }

    }

    public boolean reserve(int wishId) {
        String sql = "UPDATE wish SET reserved = TRUE WHERE id = ? AND reserved = FALSE";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, wishId);
            int changedRows = preparedStatement.executeUpdate();
            return changedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke reservere ønske", e);
        }


    }
}

