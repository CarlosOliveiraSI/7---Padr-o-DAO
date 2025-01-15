package model;

public class User {
    private int id;
    private String nome;
    private char sexo;
    private String email;

    
    public User(String nome, char sexo, String email) {
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
