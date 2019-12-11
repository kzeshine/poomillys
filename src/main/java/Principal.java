import java.util.Scanner;

import view.AutomovelView;
import view.*;
//import view.VendaView;


public class Principal {

    PessoaView pessoaView = new PessoaView();
    AutomovelView automovelView = new AutomovelView();
    MarcaView marcaView = new MarcaView();
    ModeloView modeloView = new ModeloView();
    FuncionarioView funcionarioView = new FuncionarioView();
    ClienteView clienteView = new ClienteView();
    VendaView vendaView = new VendaView();

    public static void main(String[] args){

        Principal principal = new Principal();
        principal.menuPrincipal();




    }


    public void menuPrincipal(){

        //ArrayList<Marca> bdMarca = new ArrayList<Marca>();


        System.out.println("#Menu Principal");
        System.out.println("01- Pessoa");
        System.out.println("02- Funcionario");
        System.out.println("03- Cliente");
        System.out.println("04- Marcas");
        System.out.println("05- Modelo");
        System.out.println("06- Automovel");
        System.out.println("07- Vendas");

        System.out.println("00- Sair");

        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        switch (op){
            case 1:
                pessoaView.menuPessoa();
                menuPrincipal();
                break;
            case 2:
                funcionarioView.menuFuncionario();
                menuPrincipal();
                break;
            case 3:
                clienteView.menuCliente();
                menuPrincipal();
                break;
            case 4:
                marcaView.menuMarca();
                menuPrincipal();
                break;
            case 5:
                modeloView.menuModelo();
                menuPrincipal();
                break;
            case 6:
                automovelView.menuAutomovel();

                menuPrincipal();

                break;
            case 7:
                vendaView.menuVenda();
                menuPrincipal();
            case 0: default:
                System.exit(0);
                break;
        }

    }





}