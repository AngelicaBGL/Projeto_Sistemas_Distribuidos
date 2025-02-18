package br;

/*
 * Projeto: Sistema para controle de estoque
 * Arquivo: EJBEstoque.java
 * Autor: Angélica B. G. Luciano
 * RA: 2047063
 * Última edição: 16/02/2024
 * Descrição: contém a lógica de negócios do sistema de gerenciamento de estoque, 
 *          permitindo adicionar produtos, realizar compras, vendas e listar 
 *          produtos e transações.
 */

import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Stateless(name = "EJBEstoque")
public class EJBEstoque implements RemoteInterface {

    private final List<Produto> produtos = new ArrayList<>();
    private final List<Transacao> transacoes = new ArrayList<>();
    private final LockManager lockManager = new LockManager();

   
    @Override
    public void adicionarProduto(Produto produto) {
        long transactionId = Thread.currentThread().getId();
        try {
            lockManager.acquireWriteLock(produto.getId(), transactionId);
            produto.setId(produtos.size() + 1); // Atribui um ID sequencial
            produtos.add(produto);
        } catch (InterruptedException e) {
            throw new RuntimeException("Erro ao adicionar produto: " + e.getMessage());
        } finally {
            lockManager.releaseLocks(transactionId);
        }
    }

    @Override
    public void comprarProduto(int produtoId, int quantidade) {
        long transactionId = Thread.currentThread().getId();
        try {
            lockManager.acquireWriteLock(produtoId, transactionId);
            for (Produto p : produtos) {
                if (p.getId() == produtoId) {
                    p.setQuantidade(p.getQuantidade() + quantidade); // Aumenta a quantidade
                    transacoes.add(new Transacao(transacoes.size() + 1, produtoId, "compra", quantidade, new Date()));
                    break;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Erro ao comprar produto: " + e.getMessage());
        } finally {
            lockManager.releaseLocks(transactionId);
        }
    }

    @Override
    public void venderProduto(int produtoId, int quantidade) {
        long transactionId = Thread.currentThread().getId();
        try {
            lockManager.acquireWriteLock(produtoId, transactionId);
            for (Produto p : produtos) {
                if (p.getId() == produtoId) {
                    if (p.getQuantidade() >= quantidade) {
                        p.setQuantidade(p.getQuantidade() - quantidade); // Reduz a quantidade
                        transacoes.add(new Transacao(transacoes.size() + 1, produtoId, "venda", quantidade, new Date()));
                    } else {
                        throw new RuntimeException("Quantidade insuficiente em estoque.");
                    }
                    break;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Erro ao vender produto: " + e.getMessage());
        } finally {
            lockManager.releaseLocks(transactionId);
        }
    }
    @Override
    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }

    @Override
    public List<Transacao> listarTransacoes() {
        return new ArrayList<>(transacoes);
    }

    // Método para adicionar transações recebidas do consumidor de mensagens
    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }
}