package controller;

import entity.Pessoa;
import entity.Funcionario;
import model.PessoaModel;
import model.FuncionarioModel;


import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {

	private FuncionarioModel funcionarioModel;
	private PessoaModel pessoaModel;
	public FuncionarioController() {
		this.funcionarioModel = new FuncionarioModel();
		this.pessoaModel = new PessoaModel();
	}

	public boolean cadastrar(Funcionario funcionario) {
		return funcionarioModel.cadastrar(funcionario);
	}

	public List<Funcionario> listar(){
		return funcionarioModel.listar();
	}

	public boolean alterar(int idFuncionario, Funcionario funcionario) {
		return this.funcionarioModel.alterar(idFuncionario,funcionario);
	}

	public boolean remover(Funcionario f) {
		return  funcionarioModel.remover(f);
	}


	public Funcionario buscar(int id){
		return this.funcionarioModel.buscar(id);
	}


	public Pessoa buscarPessoa(int id){

		return this.pessoaModel.buscar(id);

	}

	public boolean verificaSalarioBadeco(float salario) {
		return  this.funcionarioModel.verificarSalarioBadeco(salario);
	}

	public boolean verificaSalarioFuncionario(float salario) {
		return  this.funcionarioModel.verificaSalarioFuncionario(salario);
	}
}
