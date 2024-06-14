package br.edu.up.Models;

public class Produto extends BaseEntity {
    
    private int id; 
    private String nome;
    private double preco;
    private int quantidade;
    // Removi a referência ao fornecedor, conforme solicitado

    public Produto(int id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public String toCSV(){
        return id + ";" + nome + ";" + preco + ";" + quantidade;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Getters e Setters removidos para fornecedor, conforme não é mais utilizado
}
