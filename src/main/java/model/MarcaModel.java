package model;

import config.SQLConnection;
import entity.Marca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MarcaModel {

        Connection connection;
	public MarcaModel(){
		 connection = SQLConnection.getConnection();
	}
	
	public boolean cadastrar(Marca marca) {

		String sql = "INSERT INTO garagem.marca (nomeMarca) VALUES (?)";
		boolean marcaCadastrada = false;

		try {
			PreparedStatement ps  = connection.prepareStatement(sql);
			ps.setString(1, marca.getNomeMarca());

			ps.execute();

			marcaCadastrada = true;

		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		return marcaCadastrada;

	}

	public List<Marca> listar(){
		String sql = "select * from marca";
		PreparedStatement ps = null;
		ResultSet resultset = null;
		ArrayList<Marca> listaMarcas = new ArrayList<Marca>();
		try {

			ps = connection.prepareStatement(sql);
			resultset = ps.executeQuery();

			while (resultset.next()){
				Marca marca = new Marca();
				marca.setNomeMarca(resultset.getString("nomeMarca"));
				marca.setId(resultset.getInt("id"));
				listaMarcas.add(marca);
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return listaMarcas;
	}
	
	public boolean alterar(int idMarca, String nomeMarcaAlterada) {

		String sql = "update marca set nomeMarca = ? where id = ?";
		PreparedStatement ps = null;
		boolean marcaAtualizada = false;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,nomeMarcaAlterada);
			ps.setInt(2,idMarca);
			ps.execute();
			marcaAtualizada = true;
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return marcaAtualizada;
	}
	
	public boolean remover(int id) {
		String sql = "delete from marca where id = ?";
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
	
//	public List<Marca> listar(){
//		return this.bdMarca;
//	}
	
	public Marca buscar(int id){

		String sql = "select * from marca where id = ?";
		PreparedStatement ps = null;
		ResultSet resultset = null;
		Marca marca = new Marca();
		try {


			ps = connection.prepareStatement(sql);
			ps.setInt(1,id);
			resultset = ps.executeQuery();

			if (resultset.next()){

				marca.setNomeMarca(resultset.getString("nomeMarca"));
				marca.setId(resultset.getInt("id"));
				return marca;

			}else{
				return null;
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return marca;

	}
	
	
}