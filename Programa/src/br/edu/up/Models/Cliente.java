package br.edu.up.Models;

public class Cliente extends BaseEntity {
    private String nome;
    private String cpf;
    private int idade;
    private Endereco endereco;

    // Construtor com campos básicos
    public Cliente(int id, String nome, String cpf, int idade) {
        super.Id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    // Construtor completo incluindo o endereço
    public Cliente(int id, String nome, String cpf, int idade, Endereco endereco) {
        super.Id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + super.Id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", idade=" + idade +
                ", endereco=" + (endereco != null ? endereco.toString() : "null") +
                '}';
    }

    // Método para converter para formato CSV
    public String toCSV() {
        return super.Id + ";" + cpf + ";" + nome + ";" + idade;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}