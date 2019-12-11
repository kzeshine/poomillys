package view;

import controller.FuncionarioController;
import entity.Badeco;
import entity.Gerente;
import entity.Pessoa;
import entity.Funcionario;

import javax.swing.plaf.FontUIResource;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class FuncionarioView {

    private FuncionarioController funcionarioController;

    public FuncionarioView() {
        this.funcionarioController = new FuncionarioController();
    }

    public void menuFuncionario() {
        System.out.println("#Menu Funcionario");
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

                System.out.println("# Lista de Funcionarios");

                List<Funcionario> listaFuncionarios = funcionarioController.listar();

                for (Funcionario m : listaFuncionarios){
                    System.out.println("Id: "+m.getId()+"\n" + "Nome: "+m.getPessoa().getNome() +
                            "\n" +"CPF: "+m.getPessoa().getCpf()
                            +"\n" + "Telefone: "+m.getPessoa().getTelefone()
                            +"\n" + "Usuario: "+m.getUsuario()+"\n" + "Salário: "+m.getSalario()
                            +"\n" + "Cargo: "+m.getCargo());
                    if (m.getCargo()== "gerente"){
                        System.out.println("Departamento: "+((Gerente) m).getDepartamento());
                    }

                    if (m.getCargo()== "badeco"){
                        System.out.println("Função: "+((Badeco)m).getFuncao());
                    }
                    System.out.println("************************************************");
                }


                menuFuncionario();
                break;
            }
            case 2: {
                System.out.println("# Cadastrar Funcionario");

                Funcionario funcionario = null;

                System.out.println("> Informe o Tipo de Funcionário:");
                System.out.println("> 1 - Funcionário Comum");
                System.out.println("> 2 - Badeco");
                System.out.println("> 3 - Gerente");
                int f = sc.nextInt();

                switch(f){
                    case 1:{
                        funcionario = new Funcionario();
                        funcionario.setCargo("funcionario");
                        System.out.println("> Informe o Salário do Funcionário:");
                        float salario = sc.nextFloat();
                        salario = funcionario.calculaSalario(salario);
                        System.out.println("Salário calculado: "+ salario);
                        if(funcionario.getCargo()== "funcionario") {
                            if (funcionarioController.verificaSalarioFuncionario(salario) == true) {
                                funcionario.setSalario(salario);
                            } else {
                                System.out.println("Um Funcionario não pode ganhar mais que um Gerente!");
                                menuFuncionario();
                            }
                        }
                        break;
                    }
                    case 2:{
                        funcionario = new Badeco();
                        funcionario.setCargo("badeco");
                        System.out.println("> Informe o Salário do Badeco:");
                        float salario = sc.nextFloat();
                        salario = funcionario.calculaSalario(salario);
                        System.out.println("Salário calculado: "+ salario);
                        if(funcionario.getCargo()== "badeco") {
                            if (funcionarioController.verificaSalarioBadeco(salario) == true) {
                                funcionario.setSalario(salario);
                            } else {
                                System.out.println("Um Badeco não pode ganhar mais que um Funcionário!");
                                menuFuncionario();
                            }
                        }
                        System.out.println("> Informe a Função: ");
                        ((Badeco) funcionario).setFuncao(sc.next());
                        break;
                    }
                    case 3:{
                        funcionario = new Gerente();
                        funcionario.setCargo("gerente");
                        System.out.println("> Informe o Salário do Gerente:");
                        float salario = sc.nextFloat();
                        salario = funcionario.calculaSalario(salario);
                        System.out.println("Salário calculado: "+ salario);
                        funcionario.setSalario(salario);

                        System.out.println("> Informe o Departamento: ");
                        ((Gerente) funcionario).setDepartamento(sc.next());
                        break;
                    }
                }

                System.out.println("> Informe o ID da Pessoa:");
                int id = sc.nextInt();

                Pessoa pessoa = new Pessoa();
                pessoa = funcionarioController.buscarPessoa(id);
                if (pessoa == null){
                    System.out.println("Essa Pessoa não existe, tente novamente!");
                    menuFuncionario();
                }else{
                    System.out.println("Pessoa selecionada: " + pessoa.getNome());

                }
                funcionario.setPessoa(pessoa);

                System.out.println("Informe o Usuário: ");
                funcionario.setUsuario(sc.next());

                System.out.println("Informe a Senha: ");
                funcionario.setSenha(sc.next());


                if (funcionarioController.cadastrar(funcionario)) {
                    System.out.println("Funcionario cadastrada!");
                } else {
                    System.out.println("Erro ao cadastrar funcionario, tente novamente!");
                }

                menuFuncionario();

                break;
            }
            case 3: {

                System.out.println("# Alterar Funcionario");

                List<Funcionario> listaFuncionarios = funcionarioController.listar();

                for (Funcionario m : listaFuncionarios){
                    System.out.println("Id: "+m.getId()+"\n" + "Nome: "+m.getPessoa().getNome() +
                            "\n" +"CPF: "+m.getPessoa().getCpf()
                            +"\n" + "Telefone: "+m.getPessoa().getTelefone()
                            +"\n" + "Usuario: "+m.getUsuario()+"\n" + "Salário: "+m.getSalario()
                            +"\n" + "Cargo: "+m.getUsuario());
                    if (m.getCargo()== "gerente"){
                        System.out.println("Departamento: "+((Gerente) m).getDepartamento());
                    }

                    if (m.getCargo()== "badeco"){
                        System.out.println("Função: "+((Badeco)m).getFuncao());
                    }
                    System.out.println("************************************************");
                }

                System.out.println("> Informe o id funcionario:");
                int idFuncionario = sc.nextInt();

                Funcionario funcionario = funcionarioController.buscar(idFuncionario);
                if (funcionario == null){
                    System.out.println("Esse Funcionario não existe, tente novamente!");
                    menuFuncionario();
                }
                Funcionario funcionarioAlterado = funcionario;

                System.out.println("Informe o novo Usuário: ");
                funcionarioAlterado.setUsuario(sc.next());

                System.out.println("Informe o nova Senha: ");
                funcionarioAlterado.setSenha(sc.next());

                System.out.println("Informe o novo Salário: ");
                float salario = sc.nextFloat();
                salario = funcionarioAlterado.calculaSalario(salario);

                if (funcionarioAlterado.getCargo()== "badeco"){
                    if (funcionarioController.verificaSalarioBadeco(salario) == true){
                        funcionarioAlterado.setSalario(salario);
                    }else{
                        System.out.println("Um Badeco não pode ganhar mais que um Funcionário!");
                    }
                }else if(funcionarioAlterado.getCargo()== "funcionario"){
                    if (funcionarioController.verificaSalarioFuncionario(salario) == true){
                        funcionarioAlterado.setSalario(salario);
                    }else{
                        System.out.println("Um Funcionario não pode ganhar mais que um Gerente!");
                    }
                }else{
                    funcionarioAlterado.setSalario(salario);
                }


                if (funcionarioController.alterar(idFuncionario,funcionarioAlterado)){
                    System.out.println("Funcionario alterada!");
                }else {
                    System.out.println("Erro ao alterar funcionario, tente novamente!");
                }
                menuFuncionario();

                break;
            }
            case 4: {

                System.out.println("# Buscar Funcionario pelo Id");

                System.out.println("> Informe o  id do Funcionario:");

                int id = sc.nextInt();
                Funcionario m = funcionarioController.buscar(id);
                if (m == null) {
                    System.out.println("Funcionario não encontrado!");
                } else {
                    System.out.println("************************************************");
                    System.out.println("Id: "+m.getId()+"\n" + "Nome: "+m.getPessoa().getNome() +
                            "\n" +"CPF: "+m.getPessoa().getCpf()
                            +"\n" + "Telefone: "+m.getPessoa().getTelefone()
                            +"\n" + "Usuario: "+m.getUsuario()+"\n" + "Salário: "+m.getSalario()
                            +"\n" + "Cargo: "+m.getUsuario());
                    if (m.getCargo()== "gerente"){
                        System.out.println("Departamento: "+((Gerente) m).getDepartamento());
                    }

                    if (m.getCargo()== "badeco"){
                        System.out.println("Função: "+((Badeco)m).getFuncao());
                    }
                    System.out.println("************************************************");
                }

                menuFuncionario();
                break;
            }
            case 5: {

                System.out.println("# Excluir Funcionario");

                System.out.println("> Informe o id do funcionario:");

                int id = sc.nextInt();

                Funcionario f = funcionarioController.buscar(id);

                if(funcionarioController.remover(f)){
                    System.out.println("Funcionario removida com sucesso!");
                }else {
                    System.out.println("Erro ao remover funcionario, tente novamente!");
                }

                menuFuncionario();
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
