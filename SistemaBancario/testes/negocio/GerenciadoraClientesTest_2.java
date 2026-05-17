package negocio;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GerenciadoraClientesTest_2 {
	@Test
	public void testRemoveCliente() {
		Cliente cliente01 = new Cliente(1, "Gustavo Farias", 31, "gugufarias@gmail.com", 1, true);
		
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
	
		GerenciadoraClientes gerClientes = new GerenciadoraClientes (clientesDoBanco);
		
		boolean clienteRemovido = gerClientes.removeCliente(1);
		
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(0));
		assertNull(gerClientes.pesquisaCliente(1));
	}
}
