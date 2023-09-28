package controller;

import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread {

	private int idConta;
	private int contaSaldo;
	private int contaVal;
	private Semaphore semaforo;

	public ThreadBanco(int idConta, int contaSaldo, int contaVal, Semaphore semaforo) {
		this.idConta = idConta;
		this.contaSaldo = contaSaldo;
		this.contaVal = contaVal;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		if (idConta % 2 == 0) {
			try {
				semaforo.acquire();
				SaqueBanco();
			} catch (InterruptedException e) {
				System.err.println(e);
			} finally {
				semaforo.release();
			}
		} else {
			try {
				semaforo.acquire();
				DepositoBanco();
			} catch (InterruptedException e) {
				System.err.println(e);
			} finally {
				semaforo.release();
			}
		}
	}

	private void DepositoBanco() {
		System.out.println("O valor atual na conta é de R$"+contaSaldo+".");
		System.out.println("A operação escolhida foi depósito.");
		contaSaldo += contaVal;
		System.out.println("Houve o depósito de R$"+contaVal+", atualizando o valor em conta para R$"+contaSaldo+".");
		System.out.println(" ");
	}

	private void SaqueBanco() {
	System.out.println("O valor atual na conta é de R$" + contaSaldo+".");
		System.out.println("A operação escolhida foi saque.");
		if (contaSaldo >= contaVal) {
			contaSaldo -= contaVal;
			System.out.println("Houve o saque de R$"+contaVal+", atualizando o valor em conta para R$"+contaSaldo+".");
			System.out.println(" ");
		} else {
			System.err.println("Valor excede o disponível para o saque.");
			System.err.println("Não foi possível realizar o saque.");
			System.out.println(" ");
		}
	}

}
