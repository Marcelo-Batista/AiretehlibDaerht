package view;

import java.util.concurrent.Semaphore;

import controller.ControllerBilheteria;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int cod;
		int limite = 1;
		String idPessoa;
		Semaphore gargalo = new Semaphore(limite);
		for(cod=0; cod<300; cod++) {
			idPessoa= "Pessoa"+cod;
			ControllerBilheteria init = new ControllerBilheteria(idPessoa, gargalo);
			init.start();
		}
		
		
		
	}

}
