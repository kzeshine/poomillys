package model;

import entity.Funcionario;
import entity.Badeco;
import entity.Gerente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BadecoModel {

    Connection connection;
    public boolean cadastrar(Badeco badeco, int id) {

        String sql = "INSERT INTO garagem.badeco (funcao, IdFuncionario) VALUES (?, ?)";
        boolean badecoCadastrado = false;

        try {
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setString(1, badeco.getFuncao());
            ps.setInt(2, id);

            ps.execute();

            badecoCadastrado = true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return badecoCadastrado;

    }

    public String buscarFuncao(int id) {

        String sql = ("select funcao from badeco where idFuncionario = ?;");
        ResultSet resultset;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            resultset = ps.executeQuery();
            return resultset.getString(1);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deletar(int id) {

        String sql = "delete from badeco where idFuncionario = id";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void alterar(Badeco badeco, int id) {
        String sql = "update gerente set departamento = ? where idFuncionario = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,badeco.getFuncao());
            ps.setInt(2, id);
            ps.execute();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
