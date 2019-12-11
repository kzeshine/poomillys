package controller;

import entity.Pessoa;
import model.PessoaModel;

import java.util.ArrayList;
import java.util.List;

public class PessoaController {

    private PessoaModel pessoaModel;

    public PessoaController() {
        this.pessoaModel = new PessoaModel();
    }

    public boolean cadastrar(Pessoa pessoa) {
        return pessoaModel.cadastrar(pessoa);
    }

    public List<Pessoa> listar(){
        return pessoaModel.listar();
    }

    public boolean alterar(int idPessoa, Pessoa pessoaAlterada) {
        return this.pessoaModel.alterar(idPessoa,pessoaAlterada);
    }

    public boolean remover(int id) {
        return  pessoaModel.remover(id);
    }


    public Pessoa buscar(int id){
        return this.pessoaModel.buscar(id);
    }


}
