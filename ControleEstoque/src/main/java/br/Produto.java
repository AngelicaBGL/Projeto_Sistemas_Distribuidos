package br;

/*
 * Projeto: Sistema para controle de estoque
 * Arquivo: Produto.java
 * Autor: Angélica B. G. Luciano
 * RA: 2047063
 * Última edição: 13/02/2024
 * Descrição: Representa um produto em um sistema de gerenciamento de estoque.
 *            Armazena informações básicas, como identificador único, nome, 
 *            quantidade em estoque e preço unitário.
 */

import java.io.Serializable;

public class Produto implements Serializable {
    private int id;          // Identificador único do produto
    private String nome;     // Nome do produto
    private int quantidade;  // Quantidade em estoque
    private double preco;    // Preço unitário do produto

    // Construtores 
    public Produto() {
    }

    public Produto(int id, String nome, int quantidade, double preco) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Getters e Setters
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Método toString para facilitar a exibição do produto
    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                '}';
    }
}