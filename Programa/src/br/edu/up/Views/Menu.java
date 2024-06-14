package br.edu.up.Views;

import br.edu.up.Controllers.*;
import br.edu.up.Models.*;
import java.util.Scanner;

public class Menu {
    Scanner leitor = new Scanner(System.in);
    ClienteController clienteController = new ClienteController();
    ProdutoController produtoController = new ProdutoController();
    EnderecoController enderecoController = new EnderecoController();
    PedidoController pedidoController = new PedidoController();

    public void mostrar() {
        System.out.println("---------------------------");
        System.out.println("    MENU PRINCIPAL  ");
        System.out.println("---------------------------");
        System.out.println("1. Endereço");
        System.out.println("2. Cliente");
        System.out.println("3. Produto");
        System.out.println("4. Pedido");
        System.out.println("---------------------------");
        System.out.println("Opção: ");
        int opcao = leitor.nextInt();

        switch (opcao) {
            case 1:
                menuEndereco();
                break;
            case 2:
                menuCliente();
                break;
            case 3:
                menuProduto();
                break;
            case 4:
                menuPedido();
                break;
            default:
                System.out.println("Opção Inválida!");
                break;
        }
    }

    private void menuPedido() {
        System.out.println("---------------------------");
        System.out.println("    MENU PEDIDO  ");
        System.out.println("---------------------------");
        System.out.println("1. Adicionar Pedido");
        System.out.println("2. Remover Pedido");
        System.out.println("3. Listar Pedido");
        System.out.println("4. Voltar");
        System.out.println("---------------------------");
        System.out.println("Opção: ");
        int opcao = leitor.nextInt();

        switch (opcao) {
            case 1:
                Pedido novoPedido = pedirDadosPedido();
                pedidoController.adicionarPedido(novoPedido);
                menuPedido();
                break;
            case 2:
                System.out.println("Informe o ID do pedido que deseja remover:");
                int idInformado = leitor.nextInt();
                pedidoController.removerPedido(idInformado);
                menuPedido();
                break;
            case 3:
                pedidoController.listarPedidos();
                menuPedido();
                break;
            case 4:
                mostrar();
                break;
            default:
                System.out.println("Opção Inválida!");
                break;
        }
    }

    private void menuCliente() {
        System.out.println("---------------------------");
        System.out.println("    MENU CLIENTE  ");
        System.out.println("---------------------------");
        System.out.println("1. Adicionar Cliente");
        System.out.println("2. Remover Cliente");
        System.out.println("3. Listar Clientes");
        System.out.println("4. Voltar");
        System.out.println("---------------------------");
        System.out.println("Opção: ");
        int opcao = leitor.nextInt();

        switch (opcao) {
            case 1:
                Cliente novoCliente = pedirDadosCliente();
                clienteController.adicionarCliente(novoCliente);
                menuCliente();
                break;
            case 2:
                System.out.println("Informe o ID do cliente que deseja remover:");
                int idInformado = leitor.nextInt();
                clienteController.removerCliente(idInformado);
                menuCliente();
                break;
            case 3:
                clienteController.listarClientes();
                menuCliente();
                break;
            case 4:
                mostrar();
                break;
            default:
                System.out.println("Opção Inválida!");
                break;
        }
    }

    private void menuProduto() {
        System.out.println("---------------------------");
        System.out.println("    MENU PRODUTO  ");
        System.out.println("---------------------------");
        System.out.println("1. Adicionar Produto");
        System.out.println("2. Remover Produto");
        System.out.println("3. Listar Produtos");
        System.out.println("4. Voltar");
        System.out.println("---------------------------");
        System.out.println("Opção: ");
        int opcao = leitor.nextInt();

        switch (opcao) {
            case 1:
                Produto novoProduto = pedirDadosProduto();
                produtoController.adicionarProduto(novoProduto);
                menuProduto();
                break;
            case 2:
                System.out.println("Informe o ID do produto que deseja remover:");
                int idInformado = leitor.nextInt();
                produtoController.removerProduto(idInformado);
                menuProduto();
                break;
            case 3:
                produtoController.listarProdutos();
                menuProduto();
                break;
            case 4:
                mostrar();
                break;
            default:
                System.out.println("Opção Inválida!");
                break;
        }
    }

    private Cliente pedirDadosCliente() {
        leitor.nextLine(); // Limpar o buffer do scanner
        System.out.println("Informe o CPF:");
        String cpf = leitor.nextLine();
        System.out.println("Informe o nome:");
        String nome = leitor.nextLine();
        System.out.println("Informe a idade:");
        int idade = leitor.nextInt();

        // Criar e retornar o objeto cliente com os dados fornecidos
        Cliente novoCliente = new Cliente(clienteController.retornarID() + 1, nome, cpf, idade);
        return novoCliente;
    }

    private Produto pedirDadosProduto() {
        leitor.nextLine(); // Limpar o buffer do scanner
        System.out.println("Informe o nome:");
        String nome = leitor.nextLine();
        System.out.println("Informe o preço:");
        double preco = leitor.nextDouble();
        System.out.println("Informe a quantidade:");
        int quantidade = leitor.nextInt();

        // Criar e retornar o objeto produto com os dados fornecidos
        Produto novoProduto = new Produto(produtoController.retornarID() + 1, nome, preco, quantidade);
        return novoProduto;
    }

    private Pedido pedirDadosPedido() {
        leitor.nextLine(); // Limpar o buffer do scanner

        System.out.println("Informe o método de pagamento:");
        String metodoPagamento = leitor.nextLine();

        System.out.println("Informe a taxa de entrega:");
        int taxaEntrega = leitor.nextInt();
        leitor.nextLine(); // Limpar o buffer do scanner

        System.out.println("Informe o status do pedido:");
        String status = leitor.nextLine();

        System.out.println("Informe observações sobre o pedido:");
        String observacoes = leitor.nextLine();

        // Obter dados do cliente
        Cliente cliente = pedirDadosCliente();

        // Obter dados do produto
        Produto produto = pedirDadosProduto();

        // Criar e retornar o objeto pedido com os dados fornecidos
        Pedido novoPedido = new Pedido(pedidoController.retornarID() + 1, produto, cliente, metodoPagamento, taxaEntrega, status, observacoes);
        return novoPedido;
    }

    private void menuEndereco() {
        System.out.println("---------------------------");
        System.out.println("    MENU Endereço  ");
        System.out.println("---------------------------");
        System.out.println("1. Adicionar Endereço");
        System.out.println("2. Remover Endereço");
        System.out.println("3. Listar Endereços");
        System.out.println("4. Voltar");
        System.out.println("---------------------------");
        System.out.println("Opção: ");
        int opcao = leitor.nextInt();

        switch (opcao) {
            case 1:
                Endereco novoEndereco = pedirDadosEndereco();
                enderecoController.adicionarEndereco(novoEndereco);
                menuEndereco();
                break;
            case 2:
                System.out.println("Informe o ID do endereço que deseja remover:");
                int idInformado = leitor.nextInt();
                enderecoController.removerEndereco(idInformado);
                menuEndereco();
                break;
            case 3:
                enderecoController.listarEnderecos();
                menuEndereco();
                break;
            case 4:
                mostrar();
                break;
            default:
                System.out.println("Opção Inválida!");
                break;
        }
    }

    private Endereco pedirDadosEndereco() {
        leitor.nextLine(); // Limpar o buffer do scanner
        System.out.println("Informe a rua:");
        String rua = leitor.nextLine();
        System.out.println("Informe o número:");
        String numero = leitor.nextLine();
        System.out.println("Informe o complemento:");
        String complemento = leitor.nextLine();
        System.out.println("Informe a cidade:");
        String cidade = leitor.nextLine();
        System.out.println("Informe o estado:");
        String estado = leitor.nextLine();
        System.out.println("Informe o CEP:");
        String cep = leitor.nextLine();

        // Criar e retornar o objeto endereço com os dados fornecidos
        Endereco novoEndereco = new Endereco(enderecoController.retornarID() + 1, rua, numero, complemento, cidade, estado, cep);
        return novoEndereco;
    }
}
