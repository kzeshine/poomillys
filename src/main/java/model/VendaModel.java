package model;

import config.SQLConnection;
import controller.*;
import entity.*;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VendaModel {

	Connection connection;
	AutomovelController automovelController = new AutomovelController();
	FuncionarioController funcionarioController = new FuncionarioController();
	//VendaController vendaController =new VendaController();

	PessoaModel pessoaModel = new PessoaModel();
	AutomovelModel automovelModel = new AutomovelModel();
	ClienteModel clienteModel = new ClienteModel();
	FuncionarioModel funcionarioModel = new FuncionarioModel();
	//VendaModel vendaModel = new VendaModel();

	public VendaModel(){
		connection = SQLConnection.getConnection();
	}

	public boolean cadastrar(Venda venda) {

		String sql = "INSERT INTO garagem.venda (valor_venda, dt_venda, cod_venda, idAutomovel, comissao_venda, idCliente, idFuncionario) VALUES (?, ?, ?, ?, ?, ?, ?)";
		boolean vendaCadastrada = false;

		try {
			PreparedStatement ps  = connection.prepareStatement(sql);
			ps.setFloat(1, venda.getValor_venda());
			ps.setDate(2, venda.getDt_venda());
			ps.setInt(3, venda.getCod_venda());
			ps.setInt(4, venda.getAutomovel().getId());
			ps.setFloat(5, venda.getComissao_venda());
			ps.setInt(6, venda.getCliente().getId());
			ps.setInt(7, venda.getFuncionario().getId());
			ps.execute();

			vendaCadastrada = true;

		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		return vendaCadastrada;

	}

	public List<Venda> listar(){
		String sql = "select * from venda";
		PreparedStatement ps = null;
		ResultSet resultset = null;
		ArrayList<Venda> listaVendas = new ArrayList<Venda>();
		try {

			ps = connection.prepareStatement(sql);
			resultset = ps.executeQuery();

			while (resultset.next()){
				Venda venda = new Venda();
				venda.setCod_venda(resultset.getInt("cod_venda"));
				venda.setValor_venda(resultset.getFloat("valor_venda"));
				venda.setComissao_venda(resultset.getFloat("comissao_venda"));
				int id = resultset.getInt("idCliente");
				int id1 = resultset.getInt("idAutomovel");
				int id2 = resultset.getInt("idFuncionario");
				venda.setId(resultset.getInt("id"));
				Cliente cliente = clienteModel.buscar(id);
				Automovel automovel = automovelModel.buscar(id1);
				Funcionario funcionario = funcionarioModel.buscar(id2);
				venda.setAutomovel(automovel);
				venda.setCliente(cliente);
				venda.setFuncionario(funcionario);
				listaVendas.add(venda);
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return listaVendas;
	}

	public boolean alterar(int idVenda, Venda venda) {

		String sql = "update venda set valor_venda = ?, comissao_venda = ?, cod_venda = ?, dt_venda = ? where id = ?";
		PreparedStatement ps = null;
		boolean vendaAtualizada = false;
		try {
			ps = connection.prepareStatement(sql);
			ps.setFloat(1, venda.getValor_venda());
			ps.setFloat(2, venda.getComissao_venda());
			ps.setInt(3, venda.getCod_venda());
			ps.setDate(4, venda.getDt_venda());
			ps.setInt(5, idVenda);
			ps.execute();
			vendaAtualizada = true;
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return vendaAtualizada;
	}

	public boolean remover(int id) {
		String sql = "delete from venda where id = ?";
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

	public Venda buscar(int id){

		String sql = "select * from venda where id = ?";
		PreparedStatement ps = null;
		ResultSet resultset = null;

		try {

			ps = connection.prepareStatement(sql);
			ps.setInt(1,id);
			resultset = ps.executeQuery();

			if (resultset.next()){

				Venda venda = new Venda();
				venda.setCod_venda(resultset.getInt("cod_venda"));
				venda.setValor_venda(resultset.getFloat("valor_venda"));
				venda.setComissao_venda(resultset.getFloat("comissao_venda"));
				int idd = resultset.getInt("idCliente");
				int id1 = resultset.getInt("idAutomovel");
				int id2 = resultset.getInt("idFuncionario");
				venda.setId(resultset.getInt("id"));
				Cliente cliente = clienteModel.buscar(idd);
				Automovel automovel = automovelModel.buscar(id1);
				Funcionario funcionario = funcionarioModel.buscar(id2);
				venda.setAutomovel(automovel);
				venda.setCliente(cliente);
				venda.setFuncionario(funcionario);
				return venda;

			}else {
				return null;
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return null;

	}


	public ArrayList<RelatorioItem> relatorio (java.sql.Date dtinicio, java.sql.Date dtfim){

		ArrayList<RelatorioItem> relatorio = new ArrayList<RelatorioItem>();

		DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String inicio = dt.format(dtinicio);
		String fim = dt.format(dtfim);

		String sql = "select p.nomePessoa as nome_vendedor, sum(v.valor_venda) as total_vendas, sum(v.comissao_venda) as total_comissoes from venda as v join funcionario as f on v.idFuncionario = f.id join pessoa as p on f.idPessoa = p.id where v.dt_venda between '" + inicio +  "' and '" + fim + "' group by p.nomePessoa order by p.nomePessoa";

		PreparedStatement ps = null;
		ResultSet resultset = null;

		try {

			ps = connection.prepareStatement(sql);
			resultset = ps.executeQuery();

			while (resultset.next()) {
				RelatorioItem r = new RelatorioItem();
				r.setNome_vendedor(resultset.getString("nome_vendedor"));
				r.setTotal_vendas(resultset.getFloat("total_vendas"));
				r.setTotal_comissoes(resultset.getFloat("total_comissoes"));
				relatorio.add(r);
			}
			return relatorio;
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return null;

	}

}