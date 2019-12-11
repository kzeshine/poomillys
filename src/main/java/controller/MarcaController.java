package controller;

import entity.Marca;
import model.MarcaModel;

import java.util.ArrayList;
import java.util.List;

public class MarcaController {
	
	private MarcaModel marcaModel;
	
	public MarcaController() {
		this.marcaModel = new MarcaModel();
	}
	
	public boolean cadastrar(Marca marca) {
		return marcaModel.cadastrar(marca);
	}

	public List<Marca> listar(){
		return marcaModel.listar();
	}
	
	public boolean alterar(int idMarca, String nomeMarcaAlterada) {
		return this.marcaModel.alterar(idMarca,nomeMarcaAlterada);
	}
	
	public boolean remover(int id) {
		return  marcaModel.remover(id);
	}

	
	public Marca buscar(int id){
		return this.marcaModel.buscar(id);
	}
	 

}
