package controller;

import entity.Pessoa;
import entity.Cliente;
import model.PessoaModel;
import model.ClienteModel;


import java.util.ArrayList;
import java.util.List;

public class ClienteController {

	private ClienteModel clienteModel;
	private PessoaModel pessoaModel;
	public ClienteController() {
		this.clienteModel = new ClienteModel();
		this.pessoaModel = new PessoaModel();
	}

	public boolean cadastrar(Cliente cliente) {
		return clienteModel.cadastrar(cliente);
	}

	public List<Cliente> listar(){
		return clienteModel.listar();
	}

	public boolean alterar(int idCliente, Cliente cliente) {
		return this.clienteModel.alterar(idCliente,cliente);
	}

	public boolean remover(int id) {
		return  clienteModel.remover(id);
	}


	public Cliente buscar(int id){
		return this.clienteModel.buscar(id);
	}


	public Pessoa buscarPessoa(int id){

		return this.pessoaModel.buscar(id);

	}

}
