package br;

/*
 * Projeto: Sistema para controle de estoque
 * Arquivo: RemoteInterface.java
 * Autor: Angélica B. G. Luciano
 * RA: 2047063
 * Última edição: 13/02/2024
 * Descrição: Define um conjunto de métodos remotos para um sistema de gerenciamento
 *            de estoque, utilizando RMI (Remote Method Invocation).
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface RemoteInterface extends Remote {
    // Adiciona um novo produto ao estoque
    void adicionarProduto(Produto produto) throws RemoteException;

    // Realiza uma compra (incrementa a quantidade de um produto no estoque)
    void comprarProduto(int produtoId, int quantidade) throws RemoteException;

    // Realiza uma venda (decrementa a quantidade de um produto no estoque)
    void venderProduto(int produtoId, int quantidade) throws RemoteException;

    // Retorna a lista de todos os produtos em estoque
    List<Produto> listarProdutos() throws RemoteException;

    // Retorna a lista de todas as transações realizadas
    List<Transacao> listarTransacoes() throws RemoteException;
}