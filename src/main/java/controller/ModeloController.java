package controller;

import entity.Marca;
import entity.Modelo;
import model.MarcaModel;
import model.ModeloModel;


import java.util.ArrayList;
import java.util.List;

public class ModeloController {

	private ModeloModel modeloModel;
	private MarcaModel marcaModel;
	public ModeloController() {
		this.modeloModel = new ModeloModel();
		this.marcaModel = new MarcaModel();
	}

	public boolean cadastrar(Modelo modelo) {
		return modeloModel.cadastrar(modelo);
	}

	public List<Modelo> listar(){
		return modeloModel.listar();
	}

	public boolean alterar(int idModelo, Modelo modelo) {
		return this.modeloModel.alterar(idModelo,modelo);
	}

	public boolean remover(int id) {
		return  modeloModel.remover(id);
	}


	public Modelo buscar(int id){
		return this.modeloModel.buscar(id);
	}


	public Marca buscarMarca(int id){

		return this.marcaModel.buscar(id);

	}

}
