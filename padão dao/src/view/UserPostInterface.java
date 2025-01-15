package view;

import Model.Post;
import dao.UserDAO;
import dao.PostDAO;
import model.User;


import java.util.List;
import java.util.Scanner;

public class UserPostInterface {
    
    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final Scanner scanner;

    public UserPostInterface() {
        postDAO = new PostDAO();
        userDAO = new UserDAO();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Criar Post");
            System.out.println("2. Listar Posts");
            System.out.println("3. Alterar Post");
            System.out.println("4. Excluir Post");
            System.out.println("5. Criar Usuário");
            System.out.println("6. Listar Usuarios");
            System.out.println("7. Alterar Usuario");
            System.out.println("8. Excluir Usuario");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  
            
            switch (choice) {
                case 1:
                    createPost();
                    break;
                case 2:
                    listPosts();
                    break;
                case 3:
                    updatePost();
                    break;
                case 4:
                    deletePost();
                    break;
                case 5:
                    createUser();
                    break;
                case 6:
                    listUsers();
                    break;
                case 7:
                    updateUser();
                    break;
                case 8:
                    deleteUser();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção invalida. Tente novamente.");
            }
        }
    }

    
    private void createPost() {
        System.out.println("=== Criar Post ===");
        System.out.print("Digite o conteudo do post: ");
        String content = scanner.nextLine();

        System.out.println("Escolha um usuario:");
        List<User> users = userDAO.selectAll();  
        for (User user : users) {
            System.out.println("ID: " + user.getId() + " | Nome: " + user.getNome());
        }
        System.out.print("Digite o ID do usuário: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); 

        Post post = new Post(content, java.time.LocalDate.now(), userId);
        postDAO.insert(post);
        System.out.println("Post criado com sucesso!");
    }

    private void listPosts() {
        System.out.println("=== Listar Posts ===");
        List<Post> posts = postDAO.selectAll();
        for (Post post : posts) {
            User user = userDAO.select(post.getUserId());
            System.out.printf("ID: %d | Conteudo: %s | Data: %s | Usuario: %s%n",
                    post.getId(), post.getContent(), post.getPostDate(), user.getNome());
        }
    }

    private void updatePost() {
        System.out.println("=== Atualizar Post ===");
        System.out.print("Digite o ID do post a ser atualizado: ");
        int postId = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Digite o novo conteúdo do post: ");
        String newContent = scanner.nextLine();

        Post post = postDAO.select(postId);
        if (post != null) {
            post.setContent(newContent);
            postDAO.update(post);
            System.out.println("Post atualizado com sucesso!");
        } else {
            System.out.println("Post não encontrado.");
        }
    }

    private void deletePost() {
        System.out.println("=== Excluir Post ===");
        System.out.print("Digite o ID do post a ser excluido: ");
        int postId = scanner.nextInt();
        scanner.nextLine(); 
        postDAO.delete(postId);
        System.out.println("Post excluido com sucesso!");
    }

 
    public void createUser() {
    System.out.println("=== Criar Usuario ===");
    System.out.print("Digite o nome do usuário: ");
    String nome = scanner.nextLine();
    System.out.print("Digite o sexo do usuario (M/F): ");
    char sexo = scanner.next().charAt(0);
    scanner.nextLine();  
    System.out.print("Digite o email do usuario: ");
    String email = scanner.nextLine();

    
    if (nome.isEmpty() || email.isEmpty()) {
        System.out.println("Nome e e-mail são obrigatorios!");
        return;
    }

    User user = new User(nome, sexo, email);
    userDAO.insert(user); 
    System.out.println("Usuario criado com sucesso!");
}


    private void listUsers() {
        System.out.println("=== Listar Usuarios ===");
        List<User> users = userDAO.selectAll();
        for (User user : users) {
            System.out.printf("ID: %d | Nome: %s | Email: %s%n", user.getId(), user.getNome(), user.getEmail());
        }
    }

    private void updateUser() {
        System.out.println("=== Atualizar Usuario ===");
        System.out.print("Digite o ID do usuario a ser atualizado: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Digite o novo nome do usuario: ");
        String newName = scanner.nextLine();
        System.out.print("Digite o novo email do usuario: ");
        String newEmail = scanner.nextLine();

        User user = userDAO.select(userId);
        if (user != null) {
            user.setNome(newName);
            user.setEmail(newEmail);
            userDAO.update(user);
            System.out.println("Usuario atualizado com sucesso!");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private void deleteUser() {
        System.out.println("=== Excluir Usuario ===");
        System.out.print("Digite o ID do usuário a ser excluido: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); 
        userDAO.delete(userId);
        System.out.println("Usuario excluido com sucesso!");
    }

    public static void main(String[] args) {
        UserPostInterface userPostInterface = new UserPostInterface();
        userPostInterface.showMenu();
    }
}
