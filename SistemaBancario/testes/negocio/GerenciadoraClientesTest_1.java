package negocio;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
 

public class GerenciadoraClientesTest_1 {
	
	@Test
	public void procurarClientesTest() {
		Cliente cliente01 = new Cliente(1, "Gustavo Farias", 31, "gugufarias@gmail.com", 1, true);
		
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		
		GerenciadoraClientes gerClientes = new GerenciadoraClientes(clientesDoBanco);
		
		Cliente cliente = gerClientes.pesquisaCliente(1);
		
		assertThat(cliente.getId(), is(1));
		assertThat(cliente.getEmail(), is("gugufarias@gmail.com"));
	}
}
