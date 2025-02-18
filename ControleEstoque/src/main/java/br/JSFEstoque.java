package br;

/*
 * Projeto: Sistema para controle de estoque
 * Arquivo: JSFEstoque.java
 * Autor: Angélica B. G. Luciano
 * RA: 2047063
 * Última edição: 16/02/2024
 * Descrição: permite a interação da interface do usuário com os serviços de 
 *            gerenciamento de estoque.
 */

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;    
import jakarta.inject.Named;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "jsfEstoque")
@RequestScoped
public class JSFEstoque {

    @EJB(lookup = "java:global/ControleEstoque/EJBEstoque!br.RemoteInterface") // JNDI Name
    private RemoteInterface estoqueService; // Usando a interface remota
    
    private List<Produto> produtos;
    private List<Transacao> transacoes;

    private Produto novoProduto = new Produto();
    private int produtoId;
    private int quantidade;

    @PostConstruct
    public void init(){
        try {
            produtos = estoqueService.listarProdutos();
        } catch (RemoteException ex) {
            Logger.getLogger(JSFEstoque.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            transacoes = estoqueService.listarTransacoes();
        } catch (RemoteException ex) {
            Logger.getLogger(JSFEstoque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void adicionarProduto() throws RemoteException {
        estoqueService.adicionarProduto(novoProduto);
        produtos = estoqueService.listarProdutos();
        novoProduto = new Produto(); // Limpa o formulário após adicionar
    }

    public void comprarProduto() throws RemoteException {
        estoqueService.comprarProduto(produtoId, quantidade);
        produtos = estoqueService.listarProdutos();
    }

    public void venderProduto() throws RemoteException {
        estoqueService.venderProduto(produtoId, quantidade);
        produtos = estoqueService.listarProdutos();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public Produto getNovoProduto() {
        return novoProduto;
    }

    public void setNovoProduto(Produto novoProduto) {
        this.novoProduto = novoProduto;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}