package br.com.cfarias.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cfarias.dao.ClienteDAO;
import br.com.cfarias.dao.ClienteDaoMock;
import br.com.cfarias.dao.IclientDAO;
import br.com.cfarias.domain.Cliente;
import br.com.cfarias.exceptions.TipoChaveNaoEncontradaException;

public class ClientDaoTest {
	
	
	private IclientDAO clienteDao;
	private Cliente cliente;
	
	
	
	
	
	public ClientDaoTest() {
		
		clienteDao = new ClienteDaoMock();
					
	}
	
	@Before
	public void initDao() {
		cliente = new Cliente();
		
		cliente.setCidade("Rio de Janeiro");
		cliente.setCpf(64684654l);
		cliente.setEndereco("Rua da Amendoeira");
		cliente.setEstado("RJ");
		cliente.setNome("Caio");
		cliente.setNumero(2);
		cliente.setTelefone(24950866l);
		cliente.setIdade(28);
		
		//clienteDao.salvar(cliente);
		
	}

	
	@Test
	public void salvarDao() throws TipoChaveNaoEncontradaException {
		Boolean retorno = clienteDao.cadastrar(cliente);
		Assert.assertTrue(retorno);
		
	}
	
	@Test
	public void pesquisarClienteDao() {
		
		Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
		
	}
	
	@Test
	public void excluirClienteDao() {
		clienteDao.excluir(cliente.getCpf());
		
	}
	
	@Test
	public void alterarClienteDao() throws TipoChaveNaoEncontradaException {
		cliente.setNome("João Carvalho");
		clienteDao.alterar(cliente);
		Assert.assertEquals("João Carvalho", cliente.getNome());
		
	}
	
	
	

}
