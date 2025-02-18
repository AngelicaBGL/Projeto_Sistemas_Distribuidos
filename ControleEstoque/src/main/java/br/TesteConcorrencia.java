package br;
/*
 * Projeto: Sistema para controle de estoque
 * Arquivo: TesteConcorrencia.java
 * Autor: Angélica B. G. Luciano
 * RA: 2047063
 * Última edição: 16/02/2024
 * Descrição:  simula múltiplas transações simultâneas de compra e venda para 
 *             verificar o comportamento do sistema em situações de concorrência.
 */
import java.util.Random;

public class TesteConcorrencia {


    private final EJBEstoque estoqueService;
    private final Random random = new Random();

    public TesteConcorrencia(EJBEstoque estoqueService) {
        this.estoqueService = estoqueService;
    }

    public void iniciarTeste(int numeroThreads) {
        for (int i = 0; i < numeroThreads; i++) {
            Thread thread = new Thread(new TransacaoAleatoria());
            thread.start();
        }
    }

    private class TransacaoAleatoria implements Runnable {
        @Override
        public void run() {
            try {
                int operacao = random.nextInt(2); // 0 = compra, 1 = venda
                int produtoId = random.nextInt(5) + 1; // ID do produto entre 1 e 5
                int quantidade = random.nextInt(50) + 1; // Quantidade entre 1 e 50

                if (operacao == 0) {
                    estoqueService.comprarProduto(produtoId, quantidade);
                } else {
                    estoqueService.venderProduto(produtoId, quantidade);
                }
            } catch (Exception e) {
                
            }
        }
    }
}
