package model;

import config.SQLConnection;
import controller.ModeloController;
import controller.PessoaController;
import entity.Badeco;
import entity.Gerente;
import entity.Pessoa;
import entity.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioModel {

	Connection connection;
	GerenteModel gerenteModel = new GerenteModel();
	BadecoModel badecoModel = new BadecoModel();
	PessoaModel pessoaModel = new PessoaModel();

	public FuncionarioModel(){
		connection = SQLConnection.getConnection();
	}

	public boolean cadastrar(Funcionario funcionario) {

		String sql = "INSERT INTO garagem.funcionario (usuario, senha, salario, cargo, idPessoa) VALUES (?, ?, ?, ?, ?)";
		boolean funcionarioCadastrada = false;

		try {
			PreparedStatement ps  = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, funcionario.getUsuario());
			ps.setString(2, funcionario.getSenha());
			ps.setFloat(3, funcionario.getSalario());
			ps.setString(4, funcionario.getCargo());
			ps.setInt(5, funcionario.getPessoa().getId());

			ps.execute();
			ResultSet resultset = ps.getGeneratedKeys();
			if(resultset.next()) {
				if (funcionario.getCargo() == "gerente") {
					gerenteModel.cadastrar((Gerente) funcionario, resultset.getInt(1));
				}
				if (funcionario.getCargo() == "badeco") {
					badecoModel.cadastrar((Badeco) funcionario, resultset.getInt(1));
				}

				funcionarioCadastrada = true;
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		return funcionarioCadastrada;

	}

	public List<Funcionario> listar(){
		String sql = "select * from funcionario;";
		PreparedStatement ps = null;
		ResultSet resultset = null;
		ArrayList<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
		try {

			ps = connection.prepareStatement(sql);
			resultset = ps.executeQuery();

			while (resultset.next()){
				Funcionario funcionario;
				if (resultset.getString("cargo") == "gerente"){
					funcionario = new Gerente();
					((Gerente) funcionario).setDepartamento(gerenteModel.buscarDepartamento(resultset.getInt("id")));
				}
				else if (resultset.getString("cargo") == "badeco"){
					funcionario = new Badeco();
					((Badeco) funcionario).setFuncao(badecoModel.buscarFuncao(resultset.getInt("id")));
				}
				else{
					funcionario = new Funcionario();
				}

				funcionario.setId(resultset.getInt("id"));
				funcionario.setUsuario(resultset.getString("usuario"));
				funcionario.setSenha(resultset.getString("senha"));
				funcionario.setSalario(resultset.getFloat("salario"));
				funcionario.setCargo(resultset.getString("cargo"));
				int id = resultset.getInt("idPessoa");
				Pessoa pessoa = pessoaModel.buscar(id);
				funcionario.setPessoa(pessoa);
				listaFuncionarios.add(funcionario);
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return listaFuncionarios;
	}

	public boolean alterar(int idFuncionario, Funcionario funcionario) {

		String sql = "update funcionario set usuario = ?, senha = ?, salario = ? where id = ?";
		PreparedStatement ps = null;
		boolean funcionarioAtualizada = false;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,funcionario.getUsuario());
			ps.setString(2,funcionario.getSenha());
			ps.setFloat(3,funcionario.getSalario());
			ps.execute();
			if(funcionario.getCargo() == "gerente"){
				gerenteModel.alterar((Gerente) funcionario, idFuncionario);
			}
			if(funcionario.getCargo() == "badeco"){
				badecoModel.alterar((Badeco) funcionario, idFuncionario);
			}
			funcionarioAtualizada = true;
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return funcionarioAtualizada;
	}

	public boolean remover(Funcionario funcionario) {
		String sql = "delete from funcionario where id = ?";
		PreparedStatement ps = null;
		boolean deletar = false;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, funcionario.getId());
			ps.execute();
			deletar = true;
			if (funcionario.getCargo()== "gerente"){
				gerenteModel.deletar(funcionario.getId());
			}
			if (funcionario.getCargo()== "badeco"){
				badecoModel.deletar(funcionario.getId());
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return deletar;
	}

//	public List<Funcionario> listar(){
//		return this.bdFuncionario;
//	}

	public Funcionario buscar(int id){

		String sql = "select * from funcionario where id = ?";
		PreparedStatement ps = null;
		ResultSet resultset = null;
		try {

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			resultset = ps.executeQuery();

			if (resultset.next()){
				Funcionario funcionario;
				if (resultset.getString("cargo") == "gerente"){
					funcionario = new Gerente();
					((Gerente) funcionario).setDepartamento(gerenteModel.buscarDepartamento(resultset.getInt("id")));
				}
				if (resultset.getString("cargo") == "badeco"){
					funcionario = new Badeco();
					((Badeco) funcionario).setFuncao(badecoModel.buscarFuncao(resultset.getInt("id")));
				}
				else{
					funcionario = new Funcionario();
				}

				funcionario.setId(resultset.getInt("id"));
				funcionario.setUsuario(resultset.getString("usuario"));
				funcionario.setSenha(resultset.getString("senha"));
				funcionario.setSalario(resultset.getFloat("salario"));
				funcionario.setCargo(resultset.getString("cargo"));
				int id1 = resultset.getInt("idPessoa");
				Pessoa pessoa = pessoaModel.buscar(id1);
				funcionario.setPessoa(pessoa);
				return funcionario;
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return null;

	}


	public boolean verificarSalarioBadeco(float salario) {
		String sql = "select MIN(salario) as salario, count(*) as quantidade  from funcionario where cargo = ?";
		ResultSet resultset;

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,"funcionario");
			ps.execute();
			resultset = ps.executeQuery();


			if(resultset.next()){
				if(resultset.getFloat("quantidade") == 0 || salario < resultset.getFloat("salario")){
					return true;
				}
				else{
					return false;
				}

			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean verificaSalarioFuncionario(float salario) {
		String sql = "select min(salario) as salario, count(*) as quantidade from funcionario where cargo = ?";
		ResultSet resultset;

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,"gerente");
			ps.execute();
			resultset = ps.executeQuery();


			if(resultset.next()){
				if(resultset.getFloat("quantidade") == 0 || salario < resultset.getFloat("salario")){
					return true;
				}
				else{
					return false;
				}

			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return false;
	}
}