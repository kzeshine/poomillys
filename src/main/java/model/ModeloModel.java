package model;

import config.SQLConnection;
import controller.MarcaController;
import entity.Marca;
import entity.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModeloModel {

	Connection connection;
	MarcaController marcaController = new MarcaController();
	public ModeloModel(){
		connection = SQLConnection.getConnection();
	}

	public boolean cadastrar(Modelo modelo) {

		String sql = "INSERT INTO garagem.modelo (nomeModelo, tipo, idMarca) VALUES (?, ?, ?)";
		boolean modeloCadastrada = false;

		try {
			PreparedStatement ps  = connection.prepareStatement(sql);
			ps.setString(1, modelo.getNomeModelo());
			ps.setString(2, modelo.getTipo());
			ps.setInt(3, modelo.getMarca().getId());

			ps.execute();

			modeloCadastrada = true;

		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		return modeloCadastrada;

	}

	public List<Modelo> listar(){
		String sql = "select * from modelo, marca where modelo.idMarca = marca.id ;";
		PreparedStatement ps = null;
		ResultSet resultset = null;
		ArrayList<Modelo> listaModelos = new ArrayList<Modelo>();
		try {

			ps = connection.prepareStatement(sql);
			resultset = ps.executeQuery();

			while (resultset.next()){
				Modelo modelo = new Modelo();
				modelo.setNomeModelo(resultset.getString("nomeModelo"));
				modelo.setTipo(resultset.getString("tipo"));
				modelo.setId(resultset.getInt("id"));
				int id = resultset.getInt("idMarca");
				Marca marca = marcaController.buscar(id);
				modelo.setMarca(marca);
				listaModelos.add(modelo);
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return listaModelos;
	}

	public boolean alterar(int idModelo, Modelo modelo) {

		String sql = "update modelo set nomeModelo = ?, tipo = ? where id = ?";
		PreparedStatement ps = null;
		boolean modeloAtualizada = false;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,modelo.getNomeModelo());
			ps.setString(2,modelo.getTipo());
			ps.setInt(3,idModelo);
			ps.execute();
			modeloAtualizada = true;
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return modeloAtualizada;
	}

	public boolean remover(int id) {
		String sql = "delete from modelo where id = ?";
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

//	public List<Modelo> listar(){
//		return this.bdModelo;
//	}

	public Modelo buscar(int id){

		String sql = "select * from modelo where id = ?";
		PreparedStatement ps = null;
		ResultSet resultset = null;

		try {

			ps = connection.prepareStatement(sql);
			ps.setInt(1,id);
			resultset = ps.executeQuery();

			if (resultset.next()){

				Modelo modelo = new Modelo();
				modelo.setNomeModelo(resultset.getString("nomeModelo"));
				modelo.setTipo(resultset.getString("tipo"));
				modelo.setId(resultset.getInt("id"));
				int id1 = resultset.getInt("idMarca");
				Marca marca = marcaController.buscar(id1);
				modelo.setMarca(marca);
				return modelo;

			}else {
				return null;
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return null;

	}


}