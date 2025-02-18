package br;

/*
 * Projeto: Sistema para controle de estoque
 * Arquivo: LockManager.java
 * Autor: Angélica B. G. Luciano
 * RA: 2047063
 * Última edição: 16/02/2024
 * Descrição: é responsável por gerenciar os bloqueios de leitura e escrita no 
 *            sistema de controle de estoque.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LockManager {

    private final Map<Integer, Set<Long>> readLocks = new HashMap<>();
    private final Map<Integer, Long> writeLocks = new HashMap<>();

    public synchronized void acquireReadLock(int produtoId, long transactionId) throws InterruptedException {
        while (writeLocks.containsKey(produtoId)) {
            wait();
        }
        readLocks.computeIfAbsent(produtoId, k -> new HashSet<>()).add(transactionId);
       
    }

    public synchronized void acquireWriteLock(int produtoId, long transactionId) throws InterruptedException {
        while (writeLocks.containsKey(produtoId) || readLocks.containsKey(produtoId)) {
            wait();
        }
        writeLocks.put(produtoId, transactionId);
        
    }

    public synchronized void releaseLocks(long transactionId) {
        for (Set<Long> locks : readLocks.values()) {
            locks.remove(transactionId);
            if (locks.isEmpty()) {
                readLocks.remove(transactionId);
            }
        }

        writeLocks.entrySet().removeIf(entry -> entry.getValue() == transactionId);
        
        notifyAll();
    }
}
