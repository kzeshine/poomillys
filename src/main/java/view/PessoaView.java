package view;

import controller.PessoaController;
import entity.Pessoa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class PessoaView {

    private PessoaController PessoaController;

    public PessoaView() {
        this.PessoaController = new PessoaController();
    }

    public void menuPessoa() {
        System.out.println("#Menu Pessoa");
        System.out.println("01- Listar");
        System.out.println("02- Cadastrar");
        System.out.println("03- Alterar");
        System.out.println("04- Buscar");
        System.out.println("05- Excluir");
        System.out.println("00- Voltar");

        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        switch (op){
            case 1:{

                System.out.println("# Lista de Pessoas");

                List<Pessoa> listaPessoas = PessoaController.listar();

                for (Pessoa m : listaPessoas){
                    System.out.println("Id: "+m.getId()+"\n" + "Nome: "+m.getNome()+"\n" + "Endereço: "+m.getEndereco()
                            +"\n" + "Telefone: "+m.getTelefone()+"\n" + "Data de Nascimento: "+m.getDt_nascimento()
                            +"\n" + "CPF: "+m.getCpf());
                    System.out.println("************************************************");
                }


                menuPessoa();
                break;
            }
            case 2: {
                System.out.println("# Cadastrar Pessoa");

                Pessoa pessoa = new Pessoa();
                System.out.println("> Informe seu Nome: ");
                pessoa.setNome(sc.next());

                System.out.println("> Informe seu Endereço: ");
                pessoa.setEndereco(sc.next());

                System.out.println("> Informe seu Telefone: ");
                pessoa.setTelefone(sc.next());

                System.out.println("Informe a sua Data de Nascimento:");
                String dt = new String();
                dt = sc.next();

                DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    java.util.Date dataUtil = new java.util.Date(fmt.parse(dt).getTime());
                    java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
                    pessoa.setDt_nascimento(dataSql);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("> Informe seu CPF: ");
                pessoa.setCpf(sc.next());

                if (PessoaController.cadastrar(pessoa)) {
                    System.out.println("Pessoa cadastrada!");
                } else {
                    System.out.println("Erro ao cadastrar Pessoa, tente novamente!");
                }

                menuPessoa();

                break;
            }
            case 3: {

                System.out.println("# Alterar Pessoa");
                Pessoa pessoaAlterada = new Pessoa();
                List<Pessoa> listaPessoas = PessoaController.listar();

                for (Pessoa m : listaPessoas){
                    System.out.println("Id: "+m.getId()+"\n" + "Nome: "+m.getNome()+"\n" + "Endereço: "+m.getEndereco()
                            +"\n" + "Telefone: "+m.getTelefone()+"\n" + "Data de Nascimento: "+m.getDt_nascimento()
                            +"\n" + "CPF: "+m.getCpf());
                    System.out.println("************************************************");
                }

                System.out.println("> Informe o id Pessoa:");
                int idPessoa = sc.nextInt();

                Pessoa Pessoa = PessoaController.buscar(idPessoa);
                if (Pessoa == null){
                    System.out.println("Essa Pessoa não existe, tente novamente!");
                    menuPessoa();
                }

                System.out.println("> Informe o novo Nome: ");
                pessoaAlterada.setNome(sc.next());

                System.out.println("> Informe o novo Endereço: ");
                pessoaAlterada.setEndereco(sc.next());

                System.out.println("> Informe o novo Telefone: ");
                pessoaAlterada.setTelefone(sc.next());

                System.out.println("Informe a nova Data de Nascimento:");
                String dt = new String();
                dt = sc.next();

                DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    java.util.Date dataUtil = new java.util.Date(fmt.parse(dt).getTime());
                    java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
                    pessoaAlterada.setDt_nascimento(dataSql);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("> Informe o novo CPF: ");
                pessoaAlterada.setCpf(sc.next());

                if (PessoaController.alterar(idPessoa,pessoaAlterada)){
                    System.out.println("Pessoa alterada!");
                }else {
                    System.out.println("Erro ao alterar Pessoa, tente novamente!");
                }


                break;
            }
            case 4: {

                System.out.println("# Buscar Pessoa pelo Id");

                System.out.println("> Informe o  id da Pessoa:");

                int id = sc.nextInt();
                Pessoa m = PessoaController.buscar(id);
                if (m == null) {
                    System.out.println("Pessoa não encontrada!");
                } else {
                    System.out.println("************************************************");
                    System.out.println("Id: " + m.getId() + "\n" + "Nome: " + m.getNome());
                    System.out.println("************************************************");
                }

                menuPessoa();
                break;
            }
            case 5: {

                System.out.println("# Excluir Pessoa");

                System.out.println("> Informe o id da Pessoa:");

                int id = sc.nextInt();

                if(PessoaController.remover(id)){
                    System.out.println("Pessoa removida com sucesso!");
                }else {
                    System.out.println("Erro ao remover Pessoa, tente novamente!");
                }

                menuPessoa();
                break;
            }
            case 0:
                break;
            default:
                System.out.println("Opção invalida");
                break;
        }



    }

}
