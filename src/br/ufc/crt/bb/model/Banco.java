package br.ufc.crt.bb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ufc.crt.bb.model.enums.ContaTipo;
import br.ufc.crt.bb.model.enums.MovimentacoesTipo;

public class Banco {
	
	private List<Conta> contas = new ArrayList<>();
	private Conta conta;
	Scanner in = new Scanner(System.in);
	
	
	public Conta abrirConta(String nomeTitular, int idConta, double saldo, int limite, 
			ContaTipo tipo){
		conta = new Conta(nomeTitular, idConta,saldo,limite,tipo);
		contas.add(conta);
		
		System.out.println("Conta criada com id " + conta.getConta() 
			+ ", totalizando " + contas.size());
		return conta;
	}
	
	public List<Conta> getContas(){
		return this.contas;
	}
	
	public boolean inativarConta(Conta conta){
		if(conta != null){
			if(!conta.isStatus()){
				System.out.println("Voc� j� inativou esta conta");
			}else{
				conta.setStatus(false);
				return true;
			}
		}
		return false;
	}
	
	public double saldo(Conta conta){
		if(conta != null){
			return conta.getSaldo();
		}		
		return 0.0;
	}
	
	public void depositar(Conta conta, double dep){
		if(conta != null){			
			if(!conta.isStatus()){
				System.out.println("Voc� n�o pode efetuar transa��es com uma conta inativa");
			}else{
				conta.setSaldo(dep);
				Movimentacoes mov = new Movimentacoes("dep�sito", MovimentacoesTipo.CREDITO, dep);
				conta.movimentacaoAdd(mov);
				System.out.println("Dep�sito efetuado!");
			}
		}else{
			System.out.println("Transa��o n�o efetuada. Conta inexistente");
		}
	}
	
	public boolean debitar(Conta conta, double deb){
		if(conta != null){
			if(!conta.isStatus()){
				System.out.println("Voc� n�o pode efetuar transa��es com uma conta inativa");
				return false;
			}else if(conta.getSaldo() > 0) {
				conta.setSaldo(-deb);
				Movimentacoes mov = new Movimentacoes("d�bito",MovimentacoesTipo.DEBITO, deb);
				conta.movimentacaoAdd(mov);
				return true;
			}
		}else{
			System.out.println("Transa��o n�o efetuada. Conta inexistente");
		}
		return false;
	}
	
	public void transfConta(Conta contaOrigem, Conta contaDestino, double valor){
		if((contaOrigem != null) && (contaDestino != null)){
			if(!(saldo(contaOrigem) >= valor)){
				System.out.println("Voc� n�o tem saldo suficiente para enviar " + valor);
			}else if(!contaOrigem.isStatus() || !contaDestino.isStatus()){
				System.out.println("Voc� n�o pode efetuar transa��es com uma conta inativa");
			}else{

				System.out.println("Conta � enviar recursos:");
				System.out.println(contaOrigem);
	
				System.out.println("Conta � receber recursos:");
				System.out.println(contaDestino);
				
				System.out.println("Voc� confirma envio de " + valor + "? 1=sim / 2=n�o");
				int confirmacao = in.nextInt();
			
			if(confirmacao == 1){
				contaOrigem.setSaldo(-valor);
				Movimentacoes mov = new Movimentacoes("Envio de recursos", 
						MovimentacoesTipo.TRANSFERENCIA, valor);
				contaOrigem.movimentacaoAdd(mov);
				
				contaDestino.setSaldo(valor);
				Movimentacoes mov2 = new Movimentacoes("Recebimento de recursos", 
						MovimentacoesTipo.TRANSFERENCIA, valor);
				contaDestino.movimentacaoAdd(mov2);
				System.out.println("Transfer�ncia conclu�da.");
			}else{
				System.out.println("Transfer�ncia cancelada.");
			}
		}
	  }else{
		  System.out.println("N�o � poss�vel efetuar transa��es com contas inexistentes");
	  }
	}
	
	public Conta buscarConta(int numConta){
		for(Conta co : contas){
			if(co.getConta() == numConta){
				return co;
			}
		}
		return null;
	}
	
	public void extrato(Conta conta){
		int c = 0;
		List<Movimentacoes> movimentacoes = conta.getMovimentacoes();
		
		if(conta != null){
				System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
				System.out.printf("++++++++++ Extrato da conta n� %d +++++++++++++", conta.getConta());
				System.out.println(conta);
				System.out.println("---------------------------------------------");
				System.out.println("+++++++++++++ Movimenta��es +++++++++++++++++\n");
				
				if(movimentacoes.isEmpty()){
					System.out.println("\nNenhuma movimenta��o encontrada para esta conta at� aqui...");
				}else{
					for(Movimentacoes mo : movimentacoes){
						System.out.printf("Mov n� #%d ==> %s (%s) de %f \n", 
								++c, mo.getTipoMovimentacao(), mo.getDescricao(), 
								mo.getValor());
					}
				}
			}
	}

	public void saque(Conta contaTemp, float saque) {

			if(!contaTemp.isStatus()){
				System.out.println("N�o � possivel efetuar transa��es em contas inativas");
			}else if((contaTemp.getSaldo() >= saque) & (contaTemp.getSaldo() > 0)){
				contaTemp.setSaldo(-saque);
				Movimentacoes mov = new Movimentacoes("Saque efetuado", MovimentacoesTipo.DEBITO, 
						saque);
				contaTemp.movimentacaoAdd(mov);
				System.out.println("Saque efetuado");
			}else{
				System.out.println("Sem saldo suficiente para efetuar saque!");
			}
		}
	
	public String toString(){
		System.out.println("#####  Bem-vindo ao Sistema Banc�rio BANCO DO BRASIL  #####\n");
		System.out.println("-------------------------------------------------------------");
		System.out.println("Total de contas => " + contas.size());
		return "";
	}
}
