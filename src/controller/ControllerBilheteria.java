package controller;

import java.util.concurrent.Semaphore;

public class ControllerBilheteria extends Thread{
	private static int totalIngresso = 100;
	private String idPessoa;
	private int pedido;
	private int tempo;
	private Semaphore gargalo;
	
	public ControllerBilheteria(String idPessoa, Semaphore gargalo) {
		this.idPessoa = idPessoa;
		this.gargalo = gargalo;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		loginSistema();
	}
	
	public void loginSistema() {
		tempo = (int)(Math.random()*1951+50);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tempo>1000) {
			System.out.println(idPessoa+": TIMEOUT!");
		}else {
			processoCompra();
		}
	}
	
	public void processoCompra() {
		tempo = (int)(Math.random()*2001+1000);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tempo>2500) {
			System.out.println(idPessoa+": Sessão expirada!");
		}else {
			pedido = (int)(Math.random()*4+1);
			validacao(pedido);
		}
	}
	
	public void validacao(int pedido) {
		try {
			gargalo.acquire();
			if (pedido <= totalIngresso){
				totalIngresso -= pedido;
				System.out.println(idPessoa+": Compra de "+pedido+" ingresso(s) efetuada com sucesso!");
			}else{
				System.out.println(idPessoa+": Ingressos esgotados!");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			gargalo.release();
		}
		
	}
	
	
}
