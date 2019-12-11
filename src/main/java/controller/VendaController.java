package controller;

import entity.*;
import model.*;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class VendaController {

    private VendaModel vendaModel;
    private ModeloModel modeloModel;
    private AutomovelModel automovelModel;
    private ClienteModel clienteModel;
    private FuncionarioModel funcionarioModel;
    public VendaController() {
        this.vendaModel = new VendaModel();
        this.modeloModel = new ModeloModel();
        this.automovelModel = new AutomovelModel();
        this.clienteModel = new ClienteModel();
        this.funcionarioModel = new FuncionarioModel();
    }

    public boolean cadastrar(Venda venda) {
        return vendaModel.cadastrar(venda);
    }

    public List<Venda> listar(){
        return vendaModel.listar();
    }

    public boolean alterar(int idVenda, Venda venda) {
        return this.vendaModel.alterar(idVenda,venda);
    }

    public boolean remover(int id) {
        return  vendaModel.remover(id);
    }


    public Venda buscar(int id){
        return this.vendaModel.buscar(id);
    }


    public Automovel buscarAutomovel(int id){

        return this.automovelModel.buscar(id);

    }

    public Cliente buscarCliente(int id){

        return this.clienteModel.buscar(id);

    }

    public Funcionario buscarFuncionario(int id){

        return this.funcionarioModel.buscar(id);

    }

    public ArrayList<RelatorioItem> gerarRelatorio(Date inicio, Date fim) {
        return vendaModel.relatorio(inicio,fim);
    }
}
