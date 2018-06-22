package br.ufc.crt.bb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import br.ufc.crt.bb.model.Banco;
import br.ufc.crt.bb.model.Conta;
import br.ufc.crt.bb.model.enums.ContaTipo;

public class App {
	public static void main(String[] arguments){
		Banco bb = new Banco();

		Scanner in = new Scanner(System.in);
		int limite = 0, optTipo = 0, opcao = 0;
		float valorEnvio, saque;
		
		Conta contaDestino, contaOrigem, contaTemp;
		System.out.println(bb);
		do{
	
			System.out.println("Selecione uma opção para iniciar os trabalhos");
			System.out.println("1 => Criar Nova Conta");
			System.out.println("2 => Procurar contas existentes");
			System.out.println("3 => Saque");
			System.out.println("4 => Depósito");
			System.out.println("5 => Transferência");
			System.out.println("6 => Ver Saldo/Extrato");
			System.out.println("7 => Excluir Conta");
			System.out.println("8 => Listar Contas (ativas e inativas)");
			
			opcao = in.nextInt();
			
			switch(opcao){
			case 1:
				//nova conta
				System.out.println("Numero da Conta: ");
				int numConta = in.nextInt();
				
				if(bb.buscarConta(numConta) != null){
					System.out.printf("Já existe uma conta com esse número (%d), tente outro!", 
							numConta);
				}else{
				
					System.out.println("Digite o nome do titular: ");
					String nome = in.next();
					
					System.out.println("Qual o limite imposto à conta: ");
					limite = in.nextInt();
					
					System.out.println("Tipo da conta: 1 - COMUM, 2 - ESPECIAL");
					optTipo = in.nextInt();
					ContaTipo optTipoSelect = (optTipo == 1 ? ContaTipo.COMUM : ContaTipo.ESPECIAL); 
					
					bb.abrirConta(nome, numConta, 0.0, limite, optTipoSelect);
				}
				
				System.out.println("\nDigite 0 para retornar ao menu");
				break;
			case 2:
				//procurar contas
				System.out.println("Número de conta para pesquisar... ");
				contaTemp = bb.buscarConta(in.nextInt());
				
				if(contaTemp != null){
					System.out.println("-------------------------------------");
					System.out.println("++++++++ RESULTADOS DA BUSCA ++++++++");
					System.out.printf("\nConta encontrada! \n");
					System.out.println(contaTemp);
				}else{
					System.out.printf("\nConta não encontrada \n");
				}
				
				System.out.println("\nDigite 0 para retornar ao menu");
				break;
			case 3: 
				//saque
				System.out.println("Número da conta ao qual deseja efetuar saque?");
				contaTemp = bb.buscarConta(in.nextInt());
				
				if(contaTemp == null){
					System.out.println("Conta inexistente. Tente novamente.");
				}else{
					System.out.println("Qual o valor do saque?");
					saque = in.nextFloat();
					bb.saque(contaTemp, saque);
				}
					
				System.out.println("\nDigite 0 para retornar ao menu");
				
				break;
			case 4:
				// depósito
				System.out.println("Digite número da conta à receber o valor: ");
				contaDestino = bb.buscarConta(in.nextInt());
				
				System.out.println("Digite o valor do depósoto: ");
				valorEnvio = in.nextFloat();
				
				bb.depositar(contaDestino, valorEnvio);
				
				System.out.println("\nDigite 0 para retornar ao Menu Principal: ");
				break;
			case 5: 
				//transferência
				System.out.println("Digite número da conta de Origem: ");
				contaOrigem = bb.buscarConta(in.nextInt());
				
				if(contaOrigem == null){
					System.out.println("Conta de Origem inexistente. Tente novamente.");
				}else{
					System.out.println("Digite número da conta de Destino: ");
					contaDestino = bb.buscarConta(in.nextInt());
				
					if(contaDestino == null){
						System.out.println("Conta de Destino inexistente. Tente novamente.");
					}else{
						System.out.println("Digite o valor para envio: ");
						valorEnvio = in.nextFloat();
						
						bb.transfConta(contaOrigem, contaDestino, valorEnvio);
					}
				}
				System.out.println("\nDigite 0 para retornar ao Menu Principal: ");
				break;
			case 6:
				//saldo/extrato
				System.out.println("Digite número da conta para ver saldo/extrato: ");
				contaTemp = bb.buscarConta(in.nextInt());
				if(contaTemp == null){
					System.out.println("Conta inexistente. Tente novamente.");
				}else{
					bb.extrato(contaTemp);
				}
				
				System.out.println("\nDigite 0 para retornar ao Menu Principal: ");
				break;
			case 7:
				//excluir conta
				System.out.println("Digite número da conta para inativá-la: ");
				contaTemp = bb.buscarConta(in.nextInt());
								
				if(bb.inativarConta(contaTemp)){
					System.out.println("Conta " + contaTemp + " inativada!");
				}else{
					System.out.println("Conta " + contaTemp + " não encontrada ou não pode ser inativada!");
				}
				
				
				System.out.println("\nDigite 0 para retornar ao Menu Principal: ");
				break;
			case 8:
				//listar contas
				int x = 0;
				System.out.println("\nContas existentes: ");
				System.out.println("#\tConta\tTitular\t\tLimite\tSaldo\t\tStatus\tTipo");
				
				for(Conta listaContas : bb.getContas()){
					System.out.printf("%d\t%d\t%s\t\t%d\t%f\t%s\t%s\n", ++x, listaContas.getConta(),
							listaContas.getNomeTitular(), listaContas.getLimite(), 
							listaContas.getSaldo(), (listaContas.isStatus()) ? "Ativa" : "Inativa", 
							listaContas.getTipo());
				}
				
				System.out.println("\nDigite 0 para retornar ao Menu Principal: ");
				break;
			default:
				System.out.println("Opção inválida, tente novamente....");
			}
			
		}while(in.hasNext());
		
		System.out.println("\n-------------------------------------------------------------");
		in.close();
	}
}
