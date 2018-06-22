package br.ufc.crt.bb.model;

import java.util.ArrayList;
import java.util.List;

import br.ufc.crt.bb.model.enums.ContaTipo;

public class Conta {

	private int conta;
	private String nomeTitular;
	private double saldo = 0;
	private boolean status;
	private ContaTipo tipo;
	private int limite;
	private List<Movimentacoes> movimentacoes; 

	
	public Conta(String nome, int conta, double saldo, int limite, ContaTipo tipo){
		this.nomeTitular = nome;
		this.conta = conta;
		this.saldo = saldo;
		this.status = true;
		this.limite = limite;
		this.tipo = tipo;
		movimentacoes = new ArrayList<Movimentacoes>();
	}
	
	public void movimentacaoAdd(Movimentacoes mov){
		movimentacoes.add(mov);
	}

	public int getConta(){
		return this.conta;
	}
	
	public void setStatus(boolean status){
		this.status = status;
	}
	
	public double getSaldo(){
		return this.saldo;
	}
	
	public void setSaldo(double saldo){
		this.saldo += saldo;
	}
	
	public boolean isStatus() {
		return status;
	}

	public int getLimite(){
		return this.limite;
	}
	
	public ContaTipo getTipo(){
		return this.tipo;
	}
	
	public String getNomeTitular(){
		return this.nomeTitular;
	}
	
	public String toString(){
		System.out.println("\n################################");
		System.out.println("++++ DADOS DA CONTA++++++");
		System.out.println("Nome do Titular: " + nomeTitular);
		System.out.println("Num Conta: " + conta);
		System.out.println("Saldo: " + saldo);
		System.out.println("Situação: " + (status ? "Ativa" : "Inativa"));
		System.out.println("Tipo de conta: " + tipo);
		System.out.println("Limite da conta: " + limite);
		System.out.println("################################");
		return "";
	}
	
	public List<Movimentacoes> getMovimentacoes(){
		return this.movimentacoes;
	}
	
}
