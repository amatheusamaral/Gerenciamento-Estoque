package br.edu.up.Controllers;

import br.edu.up.Models.Endereco;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnderecoController {

    static String relativePath = "Programa\\Enderecos.csv";
    static File enderecoDB = new File(relativePath);

    public List<Endereco> listaEnderecos = new ArrayList<>();

    // Método para carregar os endereços do arquivo CSV para a listaEnderecos
    public void carregarEnderecosDoArquivo() {
        try {
            Scanner scanner = new Scanner(enderecoDB);
            scanner.nextLine(); // Ignora o cabeçalho

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                String rua = partes[1];
                String numero = partes[2];
                String complemento = partes[3];
                String cidade = partes[4];
                String estado = partes[5];
                String cep = partes[6];
                int clienteId = Integer.parseInt(partes[7]);
                Endereco endereco = new Endereco(id, rua, numero, complemento, cidade, estado, cep);
                listaEnderecos.add(endereco);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }

    // Método para obter o próximo ID disponível para endereço
    public static int retornarID() {
        int ultimoID = 0;

        try {
            Scanner leitor = new Scanner(enderecoDB);
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

    // Método para adicionar um novo endereço
    public void adicionarEndereco(Endereco end) {
        listaEnderecos.add(end);
        salvarEndereco();
    }

    // Método para buscar um endereço pelo ID do cliente
    public Endereco buscarPorClienteId(int clienteId) {
        Endereco enderecoEncontrado = null;
        for (Endereco endereco : listaEnderecos) {
            if (endereco.getClienteId() == clienteId) {
                enderecoEncontrado = endereco;
                break; // Se encontrado, podemos interromper o loop
            }
        }
        return enderecoEncontrado;
    }

    // Método para salvar os endereços no arquivo CSV
    public void salvarEndereco() {
        try {
            FileWriter enderecoBDgravar = new FileWriter(enderecoDB, true); // O segundo parâmetro true indica modo de append
            PrintWriter gravador = new PrintWriter(enderecoBDgravar);

            for (Endereco e : listaEnderecos) {
                String linhaCSV = e.toCSV(); // Implemente o método toCSV na classe Endereco se necessário
                gravador.println(linhaCSV);
            }
            gravador.close();
        } catch (IOException e) {
            System.out.println("Erro de IO: " + e.getMessage());
        }
    }

    public static void listarEnderecos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removerEndereco(int idInformado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}