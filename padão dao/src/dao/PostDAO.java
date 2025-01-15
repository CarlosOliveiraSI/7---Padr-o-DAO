package dao;

import dao.*;
import Model.DatabaseConnect;
import Model.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlo
 */
public class PostDAO implements DataAccessObject<Post> {

    @Override
    public void insert(Post post) {
        String sqlInsert = "INSERT INTO posts (content, post_date, user_id) VALUES (?, CURDATE(), ?)";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, post.getContent());
            preparedStatement.setInt(2, post.getUserId());
            preparedStatement.executeUpdate();

            
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                post.setId(generatedKeys.getInt(1)); 
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public void update(Post post) {
        String sqlUpdate = "UPDATE posts SET content = ? WHERE id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {

            preparedStatement.setString(1, post.getContent());
            preparedStatement.setInt(2, post.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sqlDelete = "DELETE FROM posts WHERE id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public Post select(int id) {
        String sqlSelect = "SELECT * FROM posts WHERE id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Post(
                    resultSet.getInt("id"),
                    resultSet.getString("content"),
                    resultSet.getDate("post_date").toLocalDate(),
                    resultSet.getInt("user_id")
                );
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;
    }

    public List<Post> selectAll() {
        String sqlSelectAll = "SELECT * FROM posts";
        List<Post> posts = new ArrayList<>();
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectAll);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                posts.add(new Post(
                    resultSet.getInt("id"),
                    resultSet.getString("content"),
                    resultSet.getDate("post_date").toLocalDate(),
                    resultSet.getInt("user_id")
                ));
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
        return posts;
    }

    
    private void handleSQLException(SQLException e) {
        System.err.println("SQL error: " + e.getMessage());
        e.printStackTrace();
    }
}