package com.example.projekt2_gruppe_5.repository;

import com.example.projekt2_gruppe_5.model.Wish;
import com.example.projekt2_gruppe_5.model.Wishlist;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishlistRepository {
    private final DataSource dataSource;

    public WishlistRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Wishlist> findByUserId(int userId) {
        List<Wishlist> wishlists = new ArrayList<>();
        String sql = "SELECT id, title, user_id, share_token FROM wishlist WHERE user_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    wishlists.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke hente ønskesedler for bruger " + userId, e);
        }
        return wishlists;
    }


    public void save(Wishlist wishlist){
        String sql ="INSERT INTO wishlist (title, user_id) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, wishlist.getTitle());
            ps.setInt(2, wishlist.getUserId());
            ps.executeUpdate();
        }  catch (SQLException e) {
            throw new RuntimeException("Kunne ikke gemme ønskeseddel", e);
        }
    }

    public Wishlist findById(int id) {
        String sql = "SELECT id, title, user_id, share_token FROM wishlist WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke hente ønskeseddel " + id, e);
        }
    }

    private Wishlist mapRow(ResultSet rs) throws SQLException {
        Wishlist wishlist = new Wishlist();
        wishlist.setId(rs.getInt("id"));
        wishlist.setTitle(rs.getString("title"));
        wishlist.setUserId(rs.getInt("user_id"));
        wishlist.setShareToken(rs.getString("share_token"));
        return wishlist;
    }

    public Wishlist findByShareToken(String token) {
        String sql = "SELECT id, title, user_id, share_token FROM wishlist WHERE share_token = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, token);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke hente ønskeseddel med link", e);
        }
    }

    public void saveShareToken(int wishlistId, String token) {
        String sql = "UPDATE wishlist SET share_token = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, token);
            ps.setInt(2, wishlistId);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke oprette link", e);
        }
    }

}