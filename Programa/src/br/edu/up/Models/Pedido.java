package br.edu.up.Models;

public class Pedido extends BaseEntity {

   private Produto produto;
   private Cliente cliente;
   private String metodoDePagamento;
   private int taxaDeEntrega;
   private String status;
   private String observacoes;

   public Pedido(int id, String metodoDePagamento, int taxaDeEntrega, String status, String observacoes) {
      super.Id = id;
      this.metodoDePagamento = metodoDePagamento;
      this.taxaDeEntrega = taxaDeEntrega;
      this.status = status;
      this.observacoes = observacoes;
   }

   public Pedido(int id, Produto produto, Cliente cliente, String metodoDePagamento, int taxaDeEntrega, String status,
         String observacoes) {
      super.Id = id;
      this.produto = produto;
      this.cliente = cliente;
      this.metodoDePagamento = metodoDePagamento;
      this.taxaDeEntrega = taxaDeEntrega;
      this.status = status;
      this.observacoes = observacoes;
   }

   // Método para converter para CSV
   public String toCSV(){
      return super.Id + ";" + (produto != null ? produto.getId() : "") + ";" + (cliente != null ? cliente.getId() : "") + ";" +
             metodoDePagamento + ";" + taxaDeEntrega + ";" + status + ";" + observacoes + ";";
   }

   // Getters e Setters
   public Produto getProduto() {
      return produto;
   }

   public void setProduto(Produto produto) {
      this.produto = produto;
   }

   public Cliente getCliente() {
      return cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public String getMetodoDePagamento() {
      return metodoDePagamento;
   }

   public void setMetodoDePagamento(String metodoDePagamento) {
      this.metodoDePagamento = metodoDePagamento;
   }

   public int getTaxaDeEntrega() {
      return taxaDeEntrega;
   }

   public void setTaxaDeEntrega(int taxaDeEntrega) {
      this.taxaDeEntrega = taxaDeEntrega;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getObservacoes() {
      return observacoes;
   }

   public void setObservacoes(String observacoes) {
      this.observacoes = observacoes;
   }
}
