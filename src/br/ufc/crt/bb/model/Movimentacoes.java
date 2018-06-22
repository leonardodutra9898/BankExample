package br.ufc.crt.bb.model;

import br.ufc.crt.bb.model.enums.MovimentacoesTipo;

public class Movimentacoes {

	private String descricao;
	private MovimentacoesTipo tipoMovimentacao;
	private double valor;
	
	public Movimentacoes(String desc, MovimentacoesTipo tipo, double val){
		descricao = desc;
		tipoMovimentacao = tipo;
		valor = val;
	}

	public String getDescricao() {
		return descricao;
	}

	public MovimentacoesTipo getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public double getValor() {
		return valor;
	}
	
	public String toString(){
		System.out.println("Descrição do Registro de Movimentação Bancária");
		System.out.println("Descrição => " + this.descricao);
		System.out.println("Tipo => " + this.tipoMovimentacao);
		System.out.println("Valor => " + this.valor);
		return "";
	}
	
}
