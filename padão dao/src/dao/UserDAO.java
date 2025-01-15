package dao;

import dao.*;
import Model.DatabaseConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO implements DataAccessObject<User> {

    @Override
    public void insert(User user) {
        String sqlInsert = "INSERT INTO users (nome, sexo, email) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {

            preparedStatement.setString(1, user.getNome());
            preparedStatement.setString(2, String.valueOf(user.getSexo())); // Convertendo o char para string
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados: " + e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        String sqlUpdate = "UPDATE users SET nome = ?, sexo = ?, email = ? WHERE id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
            preparedStatement.setString(1, user.getNome());
            preparedStatement.setString(2, String.valueOf(user.getSexo())); // Convertendo o char para string
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sqlDelete = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public User select(int id) {
        String sqlSelect = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    
                    User user = new User(
                        resultSet.getString("nome"),
                        resultSet.getString("sexo").charAt(0), 
                        resultSet.getString("email")
                    );
                    user.setId(resultSet.getInt("id"));
                    return user;
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;  
    }

    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
        String sqlSelectAll = "SELECT * FROM users";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectAll);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                
                User user = new User(
                        resultSet.getString("nome"),
                        resultSet.getString("sexo").charAt(0), // Converte para char
                        resultSet.getString("email")
                );
                user.setId(resultSet.getInt("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return users;
    }

    private void handleSQLException(SQLException e) {
        System.out.println("Erro no banco de dados: " + e.getMessage());
    }
}