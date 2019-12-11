package model;

    import config.SQLConnection;
    import entity.Pessoa;

    import java.sql.Connection;
        import java.sql.PreparedStatement;
            import java.sql.ResultSet;
            import java.util.ArrayList;
            import java.util.List;

            public class PessoaModel {

                Connection connection;
    public PessoaModel(){
        connection = SQLConnection.getConnection();
    }

    public boolean cadastrar(Pessoa pessoa) {

        String sql = "INSERT INTO garagem.pessoa (nomePessoa, endereco, telefone, dt_nascimento, cpf) VALUES (?, ?, ?, ?, ?)";
        boolean pessoaCadastrada = false;

        try {
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getEndereco());
            ps.setString(3, pessoa.getTelefone());
            ps.setDate(4, pessoa.getDt_nascimento());
            ps.setString(5, pessoa.getCpf());


            ps.execute();

            pessoaCadastrada = true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return pessoaCadastrada;

    }

    public List<Pessoa> listar(){
        String sql = "select * from pessoa";
        PreparedStatement ps = null;
        ResultSet resultset = null;
        ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
        try {

            ps = connection.prepareStatement(sql);
            resultset = ps.executeQuery();

            while (resultset.next()){
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(resultset.getString("nomePessoa"));
                pessoa.setEndereco(resultset.getString("endereco"));
                pessoa.setTelefone(resultset.getString("telefone"));
                pessoa.setDt_nascimento(resultset.getDate("dt_nascimento"));
                pessoa.setCpf(resultset.getString("cpf"));
                pessoa.setId(resultset.getInt("id"));
                listaPessoas.add(pessoa);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return listaPessoas;
    }

    public boolean alterar(int idPessoa, Pessoa pessoaAlterada) {

        String sql = "update pessoa set nomePessoa = ?, endereco = ?, telefone = ?, dt_nascimento = ?, cpf = ? where id = ?";
        PreparedStatement ps = null;
        boolean pessoaAtualizada = false;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,pessoaAlterada.getNome());
            ps.setString(2,pessoaAlterada.getEndereco());
            ps.setString(3,pessoaAlterada.getTelefone());
            ps.setDate(4,pessoaAlterada.getDt_nascimento());
            ps.setString(5  ,pessoaAlterada.getCpf());
            ps.setInt(6,idPessoa);
            ps.execute();
            pessoaAtualizada = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return pessoaAtualizada;
    }

    public boolean remover(int id) {
        String sql = "delete from pessoa where id = ?";
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

//	public List<Pessoa> listar(){
//		return this.bdPessoa;
//	}

    public Pessoa buscar(int id){

        String sql = "select * from pessoa where id = ?";
        PreparedStatement ps = null;
        ResultSet resultset = null;
        Pessoa pessoa = new Pessoa();
        try {


            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            resultset = ps.executeQuery();

            if (resultset.next()){

                pessoa.setNome(resultset.getString("nomePessoa"));
                pessoa.setEndereco(resultset.getString("endereco"));
                pessoa.setTelefone(resultset.getString("telefone"));
                pessoa.setDt_nascimento(resultset.getDate("dt_nascimento"));
                pessoa.setCpf(resultset.getString("cpf"));
                pessoa.setId(resultset.getInt("id"));
                return pessoa;

            }else{
                return null;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return pessoa;

    }


}