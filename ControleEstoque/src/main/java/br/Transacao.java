package br;

/*
 * Projeto: Sistema para controle de estoque
 * Arquivo: Transacao.java
 * Autor: Angélica B. G. Luciano
 * RA: 2047063
 * Última edição: 16/02/2024
 * Descrição: representa uma transação no sistema de gerenciamento de estoque.
 */

import java.io.Serializable;
import java.util.Date;

public class Transacao implements Serializable {
    private int id;          // Identificador único da transação
    private int produtoId;   // ID do produto relacionado à transação
    private String tipo;     // Tipo da transação: "compra" ou "venda"
    private int quantidade;  // Quantidade envolvida na transação
    private Date data;       // Data da transação

    // Construtor padrão (necessário para frameworks como JSF)
    public Transacao() {
    }

    // Construtor com parâmetros
    public Transacao(int id, int produtoId, String tipo, int quantidade, Date data) {
        this.id = id;
        this.produtoId = produtoId;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    // Método toString para facilitar a exibição da transação
    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", produtoId=" + produtoId +
                ", tipo='" + tipo + '\'' +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }
}