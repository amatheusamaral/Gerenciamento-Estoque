package br.edu.up.Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.up.Models.Cliente;
import br.edu.up.Models.Endereco;

public class ClienteController {

    String relativePath = "Programa\\Clientes.csv";
    File ClientesBD = new File(relativePath);

    // Não há necessidade de EnderecoController, pois não estamos mais lidando com endereços de fornecedores

    public List<Cliente> listaClientes = new ArrayList<>();

    // Método para carregar os clientes do arquivo CSV para a listaClientes
    public void carregarClientesDoArquivo() {
        try {
            Scanner scanner = new Scanner(ClientesBD);
            scanner.nextLine(); // Ignora o cabeçalho

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                String cpf = partes[1];
                String nome = partes[2];
                int idade = Integer.parseInt(partes[3]);
                Endereco endereco = null; // Como não temos mais fornecedores, não carregamos o endereço
                Cliente cliente = new Cliente(id, nome, cpf, idade, endereco);
                listaClientes.add(cliente);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }

    // Método para obter o próximo ID disponível para cliente
    public int retornarID() {
        int ultimoID = 0;

        try {
            Scanner leitor = new Scanner(ClientesBD);
            leitor.nextLine(); // Ignora o cabeçalho

            while (leitor.hasNextLine()) {
                String ultimaLinha = leitor.nextLine();
                String[] partes = ultimaLinha.split(";");
                ultimoID = Integer.parseInt(partes[0]);
            }
            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter ID para inteiro: " + e.getMessage());
        }

        return ultimoID + 1; // Incrementa para obter o próximo ID disponível
    }

    // Método para adicionar um novo cliente
    public void adicionarCliente(Cliente cliente) {
        listaClientes.add(cliente);
        salvarCliente();
    }

    // Método para listar todos os clientes
    public void listarClientes() {
        carregarClientesDoArquivo();

        for (Cliente cliente : listaClientes) {
            // Como não temos mais fornecedores, removemos a carga de endereços relacionados
            System.out.println(cliente.toString());
        }

        listaClientes.clear(); // Limpa a lista após listar
    }

    // Método para limpar a lista de clientes em memória
    public void limparClientes() {
        listaClientes.clear();
    }

    // Método para salvar os clientes no arquivo CSV
    public void salvarCliente() {
        try {
            FileWriter ClienteBDgravar = new FileWriter(ClientesBD, false);
            PrintWriter gravador = new PrintWriter(ClienteBDgravar);

            gravador.println("ID;cpf;nome;idade");

            for (Cliente c : listaClientes) {
                String linhaCSV = c.toCSV(); // Implemente o método toCSV na classe Cliente se necessário
                gravador.println(linhaCSV);
            }
            gravador.close();
        } catch (IOException e) {
            System.out.println("Erro de IO: " + e.getMessage());
        }
    }

    // Método para remover um cliente pelo ID
    public void removerCliente(int idCliente) {
        boolean encontrado = false;

        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == idCliente) {
                listaClientes.remove(cliente);
                salvarCliente();
                encontrado = true;
                System.out.println("Cliente removido com sucesso");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Cliente não encontrado");
        }
    }

}