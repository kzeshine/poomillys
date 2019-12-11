package model;

import entity.Funcionario;
import entity.Gerente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GerenteModel {

    Connection connection;
    public boolean cadastrar(Gerente gerente, int id) {

        String sql = "INSERT INTO garagem.gerente (departamento, IdFuncionario) VALUES (?, ?)";
        boolean gerenteCadastrado = false;

        try {
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setString(1, gerente.getDepartamento());
            ps.setInt(2, id);

            ps.execute();

            gerenteCadastrado = true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return gerenteCadastrado;

    }

    public String buscarDepartamento(int id) {

        String sql = ("select departamento from gerente where idFuncionario = ?;");
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

        String sql = "delete from gerente where idFuncionario = id";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void alterar(Gerente gerente, int id) {
        String sql = "update gerente set departamento = ? where idFuncionario = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,gerente.getDepartamento());
            ps.setInt(2, id);
            ps.execute();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
