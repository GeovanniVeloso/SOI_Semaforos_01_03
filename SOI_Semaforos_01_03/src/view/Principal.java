package view;

import java.util.concurrent.Semaphore;

import controller.ThreadBanco;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		int conta;
		int saldo;
		int op;
		for(int i = 0; i<20; i++) {
			conta = (int)(Math.random()*99)+1;
			saldo = (int)(Math.random()*9000)+1000;
			op = (int)(Math.random()*9000)+1000;
			Thread tBanco = new ThreadBanco(conta, saldo, op, semaforo);
			tBanco.start();
		}

	}

}
