package controller;

import entity.Marca;
import entity.Automovel;
import entity.Modelo;
import model.MarcaModel;
import model.AutomovelModel;
import model.ModeloModel;


import java.util.ArrayList;
import java.util.List;

public class AutomovelController {

    private AutomovelModel automovelModel;
    private ModeloModel modeloModel;
    public AutomovelController() {
        this.automovelModel = new AutomovelModel();
        this.modeloModel = new ModeloModel();
    }

    public boolean cadastrar(Automovel automovel) {
        return automovelModel.cadastrar(automovel);
    }

    public List<Automovel> listar(){
        return automovelModel.listar();
    }

    public boolean alterar(int idAutomovel, Automovel automovel) {
        return this.automovelModel.alterar(idAutomovel,automovel);
    }

    public boolean remover(int id) {
        return  automovelModel.remover(id);
    }


    public Automovel buscar(int id){
        return this.automovelModel.buscar(id);
    }


    public Modelo buscarModelo(int id){

        return this.modeloModel.buscar(id);

    }

}
