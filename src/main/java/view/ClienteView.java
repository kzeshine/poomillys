package view;

import controller.ClienteController;
import entity.Pessoa;
import entity.Cliente;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ClienteView {

    private ClienteController clienteController;

    public ClienteView() {
        this.clienteController = new ClienteController();
    }

    public void menuCliente() {
        System.out.println("#Menu Cliente");
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

                System.out.println("# Lista de Clientes");

                List<Cliente> listaClientes = clienteController.listar();

                for (Cliente m : listaClientes){
                    System.out.println("Id: "+m.getId()+"\n" + "Nome: "+m.getPessoa().getNome() +
                            "\n" +"CPF: "+m.getPessoa().getCpf() +"\n" + "Endereço: "+m.getPessoa().getEndereco()
                            +"\n" + "Telefone: "+m.getPessoa().getTelefone()
                            +"\n" + "Data de Nascimento: "+m.getPessoa().getDt_nascimento() +
                            "\n" + "Código do Cliente: "+m.getCodigo());
                    System.out.println("************************************************");
                }


                menuCliente();
                break;
            }
            case 2: {
                System.out.println("# Cadastrar Cliente");

                Cliente cliente = new Cliente();

                System.out.println("> Informe o ID da Pessoa:");
                int id = sc.nextInt();

                Pessoa pessoa = new Pessoa();
                pessoa = clienteController.buscarPessoa(id);
                if (pessoa == null){
                    System.out.println("Essa Pessoa não existe, tente novamente!");
                    menuCliente();
                }else{
                    System.out.println("Pessoa selecionada: " + pessoa.getNome());

                }
                cliente.setPessoa(pessoa);

                System.out.println("Informe o Codigo do Cliente: ");
                cliente.setCodigo(sc.nextInt());

                if (clienteController.cadastrar(cliente)) {
                    System.out.println("Cliente cadastrada!");
                } else {
                    System.out.println("Erro ao cadastrar cliente, tente novamente!");
                }

                menuCliente();

                break;
            }
            case 3: {

                System.out.println("# Alterar Cliente");

                List<Cliente> listaClientes = clienteController.listar();

                for (Cliente m : listaClientes){
                    System.out.println("Id: "+m.getId()+"\n" + "Nome: "+m.getPessoa().getNome() +
                                    "\n" +"CPF: "+m.getPessoa().getCpf() +"\n" + "Endereço: "+m.getPessoa().getEndereco()
                                    +"\n" + "Telefone: "+m.getPessoa().getTelefone()
                                    +"\n" + "Data de Nascimento: "+m.getPessoa().getDt_nascimento() +
                            "\n" + "Código do Cliente: "+m.getCodigo());
                    System.out.println("************************************************");
                }

                System.out.println("> Informe o id cliente:");
                int idCliente = sc.nextInt();

                Cliente cliente = clienteController.buscar(idCliente);
                if (cliente == null){
                    System.out.println("Esse Cliente não existe, tente novamente!");
                    menuCliente();
                }
                Cliente clienteAlterado = new Cliente();

                System.out.println("Informe o Codigo do Cliente: ");
                cliente.setCodigo(sc.nextInt());

                if (clienteController.alterar(idCliente,clienteAlterado)){
                    System.out.println("Cliente alterada!");
                }else {
                    System.out.println("Erro ao alterar cliente, tente novamente!");
                }
                menuCliente();

                break;
            }
            case 4: {

                System.out.println("# Buscar Cliente pelo Id");

                System.out.println("> Informe o  id do cliente:");

                int id = sc.nextInt();
                Cliente m = clienteController.buscar(id);
                if (m == null) {
                    System.out.println("Cliente não encontrado!");
                } else {
                    System.out.println("************************************************");
                    System.out.println("Id: "+m.getId()+"\n" + "Nome: "+m.getPessoa().getNome() +
                            "\n" +"CPF: "+m.getPessoa().getCpf() +"\n" + "Endereço: "+m.getPessoa().getEndereco()
                            +"\n" + "Telefone: "+m.getPessoa().getTelefone()
                            +"\n" + "Data de Nascimento: "+m.getPessoa().getDt_nascimento() +
                            "\n" + "Código do Cliente: "+m.getCodigo());
                    System.out.println("************************************************");
                }

                menuCliente();
                break;
            }
            case 5: {

                System.out.println("# Excluir Cliente");

                System.out.println("> Informe o id do cliente:");

                int id = sc.nextInt();

                if(clienteController.remover(id)){
                    System.out.println("Cliente removida com sucesso!");
                }else {
                    System.out.println("Erro ao remover cliente, tente novamente!");
                }

                menuCliente();
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
