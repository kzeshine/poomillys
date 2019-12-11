package model;

import config.SQLConnection;
import controller.PessoaController;
import entity.Automovel;
import entity.Pessoa;
import entity.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteModel {

	Connection connection;
	PessoaModel pessoaModel = new PessoaModel();
	public ClienteModel(){
		connection = SQLConnection.getConnection();
	}

	public boolean cadastrar(Cliente cliente) {

		String sql = "INSERT INTO garagem.cliente (codigo, idPessoa) VALUES (?, ?)";
		boolean clienteCadastrada = false;

		try {
			PreparedStatement ps  = connection.prepareStatement(sql);
			ps.setInt(1, cliente.getCodigo());
			ps.setInt(2, cliente.getPessoa().getId());

			ps.execute();

			clienteCadastrada = true;

		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		return clienteCadastrada;

	}

	public List<Cliente> listar(){
		String sql = "select * from cliente, pessoa where cliente.idPessoa = pessoa.id ;";
		PreparedStatement ps = null;
		ResultSet resultset = null;
		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
		try {

			ps = connection.prepareStatement(sql);
			resultset = ps.executeQuery();

			while (resultset.next()){
				Cliente cliente = new Cliente();
				cliente.setCodigo(resultset.getInt("codigo"));
				cliente.setId(resultset.getInt("id"));
				int id = resultset.getInt("idPessoa");
				Pessoa pessoa = pessoaModel.buscar(id);
				cliente.setPessoa(pessoa);
				listaClientes.add(cliente);
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return listaClientes;
	}

	public boolean alterar(int idCliente, Cliente cliente) {

		String sql = "update cliente set codigo = ? where id = ?";
		PreparedStatement ps = null;
		boolean clienteAtualizada = false;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1,cliente.getCodigo());
			ps.setInt(2,idCliente);
			ps.execute();
			clienteAtualizada = true;
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return clienteAtualizada;
	}

	public boolean remover(int id) {
		String sql = "delete from cliente where id = ?";
		PreparedStatement ps = null;
		boolean deletar = false;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			deletar = true;
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return deletar;
	}

//	public List<Cliente> listar(){
//		return this.bdCliente;
//	}

	public Cliente buscar(int id){

		String sql = "select * from cliente where id = ?";
		PreparedStatement ps = null;
		ResultSet resultset = null;

		try {

			ps = connection.prepareStatement(sql);
			ps.setInt(1,id);
			resultset = ps.executeQuery();

			if (resultset.next()){

				Cliente cliente = new Cliente();
				cliente.setId(resultset.getInt("id"));
				cliente.setCodigo(resultset.getInt("codigo"));
				int id1 = resultset.getInt("idPessoa");
				Pessoa pessoa = pessoaModel.buscar(id1);
				cliente.setPessoa(pessoa);
				return cliente;

			}else {
				return null;
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return null;

	}


}