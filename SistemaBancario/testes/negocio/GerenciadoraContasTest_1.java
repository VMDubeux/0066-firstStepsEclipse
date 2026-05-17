package negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GerenciadoraContasTest_1 {

	private GerenciadoraContas gerContas;
	
	/**
	 * Teste de transferência de valor da conta de um cliente para outro,
	 * com ambos clientes ativos e havendo saldo suficiente para a transferência
	 * ser efetuada com sucesso.
	 * 
	 * @author Victor Dubeux / Aula de Gustavo Farias
	 * @throws Exception 
	 * @date 15/05/2026
	 */
	@Test
	public void testTransfereValor_SaldoSuficiente() throws Exception {
		System.out.println("Teste saldo suficiente:");
		/// 1. Montagem do cenário (Given) ///
		
		int idConta01 = 1;
		int idConta02 = 2;
		double saldoInicialConta01 = 200;
		double saldoInicialConta02 = 0;
		double valorTransferido = 1;
		
		// Criar as contas com os saldos iniciais:
		ContaCorrente conta01 = new ContaCorrente(idConta01, saldoInicialConta01, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, saldoInicialConta02, true);
		
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		
		gerContas = new GerenciadoraContas(contasDoBanco);

		// Variáveis de controle dinâmico para o Loop (acumuladores)
		double saldoEsperadoConta01 = saldoInicialConta01; // Começa em 200
		double saldoEsperadoConta02 = saldoInicialConta02; // Começa em 0

		/// 2. Execução da ação e laço de repetição (When) ///
		
		// O loop roda enquanto o saldo esperado for maior que 100
		while(saldoEsperadoConta01 > (saldoInicialConta01/2)) {
			
			// Executa a transferência no sistema
			gerContas.transfereValor(idConta01, valorTransferido, idConta02);			
			
			// CORREÇÃO AQUI: Atualiza as variáveis dinamicamente baseadas no valor anterior delas
			saldoEsperadoConta01 -= valorTransferido; // Subtrai 1 do saldo que já havia sido modificado
			saldoEsperadoConta02 += valorTransferido; // Soma 1 ao saldo que já havia sido modificado
			
			/// 3. Verificações (Then) ///
			
			// Agora o assert vai comparar o saldo atualizado do objeto com o acumulador correto
			assertThat(conta01.getSaldo(), is(saldoEsperadoConta01));
			assertThat(conta02.getSaldo(), is(saldoEsperadoConta02));
			
			// Print no console para acompanhar o decréscimo até 100
			String mensagem = String.format("Saldo conta 1: %.2f | Valor transferido: %.2f | Saldo conta 2: %.2f", 
					conta01.getSaldo(), valorTransferido, conta02.getSaldo());
			System.out.println(mensagem);
		}		
	}

	
	/**
	 * Teste de transferência de valor da conta de um cliente para outro,
	 * com ambos clientes ativos e havendo saldo insuficiente para a transferência
	 * não ser efetuada.
	 * 
	 * @author Victor Dubeux / Aula de Gustavo Farias
	 * @date 15/05/2026
	 */
	@Test
	public void testTransfereValor_SaldoInsuficiente() {
		System.out.println("Teste saldo insuficiente:");

		/// 1. Montagem do cenário (Given) ///
		
		// Variáveis de configuração inicial:
		int idConta01 = 1;
		int idConta02 = 2;
		double saldoInicialConta01 = 0;
		double saldoInicialConta02 = 200;
		double valorTransferido = 1;
		
		// Criar as contas com os saldos iniciais:
		ContaCorrente conta01 = new ContaCorrente(idConta01, saldoInicialConta01, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, saldoInicialConta02, true);
		
		// Inserir as contas na lista do banco:
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		
		// Inicializar a gerenciadora com a lista criada
		gerContas = new GerenciadoraContas(contasDoBanco);

		/// 2. Execução da ação (When) ///
		
		// Executa a transferência uma única vez
		try{
			gerContas.transfereValor(idConta01, valorTransferido, idConta02);			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		// Calcula matematicamente o valor esperado após UMA transferência:
		double saldoEsperadoConta01 = saldoInicialConta01 - valorTransferido; // 0 - 1 = -1
		double saldoEsperadoConta02 = saldoInicialConta02 + valorTransferido; // 200 + 1 = 201

		/// 3. Verificações (Then) ///
		
		// Verifica se o saldo real dos objetos mudou conforme o esperado
		assertThat(conta01.getSaldo(), is(saldoInicialConta01));
		assertThat(conta02.getSaldo(), is(saldoInicialConta02));
		
		// Print no console para conferência manual
		String mensagem = String.format("Saldo conta 1: %.2f "
				+ "| Valor transferido: %.2f |"
				+ " Saldo conta 2: %.2f", 
                conta01.getSaldo(), valorTransferido, conta02.getSaldo());
		System.out.println(mensagem);
	}
	
	/**
	 * Teste de tentativa de transferência de um valor da conta de um cliente para outro
	 * quando o saldo do cliente origem é negativo e do cliente destino também é negativo.
	 * 
	 * @author Victor Dubeux / Aula de Gustavo Farias
	 * @date 15/05/2026
	 */
	@Test
	public void testTransfereValor_SaldoNegativoParaNegativo() {
		System.out.println("Teste saldo negativo:");
		/// 1. Montagem do cenário (Given) ///
		
				// Variáveis de configuração inicial:
				int idConta01 = 1;
				int idConta02 = 2;
				double saldoInicialConta01 = -100;
				double saldoInicialConta02 = -200;
				double valorTransferido = 1;
				
				// Criar as contas com os saldos iniciais:
				ContaCorrente conta01 = new ContaCorrente(idConta01, saldoInicialConta01, true);
				ContaCorrente conta02 = new ContaCorrente(idConta02, saldoInicialConta02, true);
				
				// Inserir as contas na lista do banco:
				List<ContaCorrente> contasDoBanco = new ArrayList<>();
				contasDoBanco.add(conta01);
				contasDoBanco.add(conta02);
				
				// Inicializar a gerenciadora com a lista criada
				gerContas = new GerenciadoraContas(contasDoBanco);

				/// 2. Execução da ação (When) ///
				
				// Executa a transferência uma única vez
				try{
					gerContas.transfereValor(idConta01, valorTransferido, idConta02);			
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
				// Calcula matematicamente o valor esperado após UMA transferência:
				double saldoEsperadoConta01 = saldoInicialConta01 - valorTransferido; // -100 - 1 = -101 (possibilidade não autorizada pela regra de negócio)
				double saldoEsperadoConta02 = saldoInicialConta02 + valorTransferido; // -200 + 1 = -199 (possibilidade não autorizada pela regra de negócio)

				/// 3. Verificações (Then) ///
				
				// Verifica se o saldo real dos objetos mudou conforme o esperado
				assertThat(conta01.getSaldo(), is(saldoInicialConta01));
				assertThat(conta02.getSaldo(), is(saldoInicialConta02));
				
				// Print no console para conferência manual
				String mensagem = String.format("Saldo conta 1: %.2f "
						+ "| Valor transferido: %.2f |"
						+ " Saldo conta 2: %.2f", 
		                conta01.getSaldo(), valorTransferido, conta02.getSaldo());
				System.out.println(mensagem);
			}
}