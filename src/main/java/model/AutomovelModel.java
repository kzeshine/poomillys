package model;

import config.SQLConnection;
import controller.MarcaController;
import controller.ModeloController;
import entity.Marca;
import entity.Automovel;
import entity.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AutomovelModel {

	Connection connection;
	ModeloController modeloController = new ModeloController();
	public AutomovelModel(){
		connection = SQLConnection.getConnection();
	}

	public boolean cadastrar(Automovel automovel) {

		String sql = "INSERT INTO garagem.automovel (cor, ano_fabricacao, ano_modelo, chassi, km, valor, placa, idModelo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		boolean automovelCadastrada = false;

		try {
			PreparedStatement ps  = connection.prepareStatement(sql);
			ps.setString(1, automovel.getCor());
			ps.setDate(2, automovel.getAno_fabricacao());
			ps.setDate(3, automovel.getAno_modelo());
			ps.setString(4, automovel.getChassi());
			ps.setFloat(5, automovel.getKm());
			ps.setFloat(6, automovel.getValor());
			ps.setString(7, automovel.getPlaca());
			ps.setInt(8, automovel.getModelo().getId());
			ps.execute();

			automovelCadastrada = true;

		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		return automovelCadastrada;

	}

	public List<Automovel> listar(){
		String sql = "select * from automovel, modelo where automovel.idModelo = modelo.id ;";
		PreparedStatement ps = null;
		ResultSet resultset = null;
		ArrayList<Automovel> listaAutomovels = new ArrayList<Automovel>();
		try {

			ps = connection.prepareStatement(sql);
			resultset = ps.executeQuery();

			while (resultset.next()){
				Automovel automovel = new Automovel();
				automovel.setCor(resultset.getString("cor"));
				automovel.setAno_fabricacao(resultset.getDate("ano_fabricacao"));
				automovel.setAno_modelo(resultset.getDate("ano_modelo"));
				automovel.setChassi(resultset.getString("chassi"));
				automovel.setKm(resultset.getFloat("km"));
				automovel.setValor(resultset.getFloat("valor"));
				automovel.setPlaca(resultset.getString("placa"));
				automovel.setId(resultset.getInt("id"));
					int id = resultset.getInt("idModelo");
				Modelo modelo = modeloController.buscar(id);
				automovel.setModelo(modelo);
				listaAutomovels.add(automovel);
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return listaAutomovels;
	}

	public boolean alterar(int idAutomovel, Automovel automovel) {

		String sql = "update automovel set cor = ?, ano_fabricacao = ?, ano_modelo = ?, chassi = ?, km = ?, valor = ?, placa = ? where id = ?";
		PreparedStatement ps = null;
		boolean automovelAtualizada = false;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, automovel.getCor());
			ps.setDate(2, automovel.getAno_fabricacao());
			ps.setDate(3, automovel.getAno_modelo());
			ps.setString(4, automovel.getChassi());
			ps.setFloat(5, automovel.getKm());
			ps.setFloat(6, automovel.getValor());
			ps.setString(7, automovel.getPlaca());
			ps.setInt(8, idAutomovel);
			ps.execute();
			automovelAtualizada = true;
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return automovelAtualizada;
	}

	public boolean remover(int id) {
		String sql = "delete from automovel where id = ?";
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

//	public List<Automovel> listar(){
//		return this.bdAutomovel;
//	}

	public Automovel buscar(int id){

		String sql = "select * from automovel where id = ?";
		PreparedStatement ps = null;
		ResultSet resultset = null;

		try {

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			resultset = ps.executeQuery();

			if (resultset.next()){

				Automovel automovel = new Automovel();
				automovel.setCor(resultset.getString("cor"));
				automovel.setAno_fabricacao(resultset.getDate("ano_fabricacao"));
				automovel.setAno_modelo(resultset.getDate("ano_modelo"));
				automovel.setChassi(resultset.getString("chassi"));
				automovel.setKm(resultset.getFloat("km"));
				automovel.setValor(resultset.getFloat("valor"));
				automovel.setPlaca(resultset.getString("placa"));
				automovel.setId(resultset.getInt("id"));
				int id1 = resultset.getInt("idModelo");
				Modelo modelo = modeloController.buscar(id1);
				automovel.setModelo(modelo);
				return automovel;

			}else {
				return null;
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return null;

	}


}