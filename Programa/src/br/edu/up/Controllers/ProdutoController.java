package br.edu.up.Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.up.Models.Produto;

public class ProdutoController {

    String relativePath = "Programa\\Produtos.csv";
    File ProdutosBD = new File(relativePath);
    
    public List<Produto> listaProdutos = new ArrayList<>();

    // Método para carregar os produtos do arquivo CSV para a listaProdutos
    public void carregarProdutosDoArquivo() {
        try {
            Scanner scanner = new Scanner(ProdutosBD);
            scanner.nextLine(); // Ignora o cabeçalho

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                double preco = Double.parseDouble(partes[2]);
                int quantidade = Integer.parseInt(partes[3]);
                // Removido o fornecedor, pois não estamos mais lidando com fornecedores
                Produto produto = new Produto(id, nome, preco, quantidade);
                listaProdutos.add(produto);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter valor: " + e.getMessage());
        }
    }

    // Método para obter o próximo ID disponível para produto
    public int retornarID(){
        int ultimoID = 0;

        try {
            Scanner leitor = new Scanner(ProdutosBD);
            leitor.nextLine(); // Ignora o cabeçalho

            String ultimaLinha = null;

            while (leitor.hasNextLine()) {
                ultimaLinha = leitor.nextLine();
            }
    
            if (ultimaLinha != null) {
                String[] partes = ultimaLinha.split(";");
                ultimoID = Integer.parseInt(partes[0]);
            }
            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter ID para inteiro: " + e.getMessage());
        }
        return ultimoID;
    }

    // Método para adicionar um novo produto
    public void adicionarProduto(Produto produto) {
        listaProdutos.add(produto);
        salvarProduto();
    }

    // Método para listar os produtos
    public void listarProdutos() {
        try {
            Scanner leitor = new Scanner(ProdutosBD);
            leitor.nextLine(); // Ignora o cabeçalho

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                System.out.println(linha);
            }

            leitor.close();
        } catch (FileNotFoundException x) {
            System.out.println("O arquivo " + ProdutosBD + " não foi encontrado: " + x.getCause());
        }
    }

    // Método para salvar os produtos no arquivo CSV
    public void salvarProduto() {
        try {
            FileWriter ProdutoBDgravar = new FileWriter(ProdutosBD, false);
            PrintWriter gravador = new PrintWriter(ProdutoBDgravar);

            gravador.println("ID;nome;preço;quantidade");

            for (Produto p : listaProdutos) {
                String linhaCSV = p.toCSV(); // Implemente o método toCSV na classe Produto se necessário
                gravador.println(linhaCSV);
            }
            gravador.close();
        } catch (IOException e) {
            System.out.println("Erro de IO: " + e.getMessage());
        }
    }

    // Método para remover um produto pelo ID
    public void removerProduto(int idProduto) {
        boolean encontrado = false;

        for (Produto produto : listaProdutos) {
            if (produto.getId() == idProduto) {
                listaProdutos.remove(produto);
                salvarProduto();
                encontrado = true;
                System.out.println("Produto removido com sucesso");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Produto não encontrado");
        }
    }
}
