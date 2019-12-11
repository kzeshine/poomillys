package view;

import controller.VendaController;
import entity.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendaView {

    private VendaController vendaController;

    public VendaView() {
        this.vendaController = new VendaController();
    }

    public void menuVenda() {
        System.out.println("#Menu Venda");
        System.out.println("01- Listar");
        System.out.println("02- Cadastrar");
        System.out.println("03- Alterar");
        System.out.println("04- Buscar");
        System.out.println("05- Excluir");
        System.out.println("06- Relatório");
        System.out.println("00- Voltar");

        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        switch (op){
            case 1:{

                System.out.println("# Lista de Vendas");
                List<Venda> listaVendas = vendaController.listar();

                for (Venda v : listaVendas ){
                    System.out.println("Id:" + v.getId()
                            + "\n" +"Código de Venda:" + v.getCod_venda()
                            + "\n" +"Valor da Venda:" + v.getValor_venda()
                            + "\n" +"Funcionario:" + v.getFuncionario().getPessoa().getNome()
                            + "\n" +"Comissão de Venda:" + v.getComissao_venda()
                            + "\n" +"Cliente:" + v.getCliente().getPessoa().getNome()
                            + "\n" +"Automovel: " + v.getAutomovel().getModelo().getMarca().getNomeMarca());
                    System.out.println("************************************************");
                }

                menuVenda();
                break;
            }
            case 2: {
                System.out.println("# Cadastrar Venda");

                Venda venda = new Venda();

                System.out.print("> Informe o ID do Automovel: ");
                int id = sc.nextInt();

                Automovel automovel = new Automovel();

                automovel = vendaController.buscarAutomovel(id);
                if (automovel == null){
                    System.out.println("Essa Automovel não existe, tente novamente!");
                    menuVenda();
                    break;
                }else{
                    System.out.println("> ******************* Automovel Selecionado: ******************* " + "\n");
                    System.out.println("Marca: " + automovel.getModelo().getMarca().getNomeMarca()
                            + "\n" +"Modelo selecionado: " + automovel.getModelo().getNomeModelo()
                             +"\n" +"Tipo: " + automovel.getModelo().getTipo()
                            +"\n" +"Data de Fabricação: " + automovel.getAno_fabricacao()
                            +"\n" +"Data do modelo: " + automovel.getAno_modelo()
                            +"\n" +"Chassi: " + automovel.getChassi() +"\n" +"kilometragem: " + automovel.getKm()
                            +"\n" +"Placa do Automovel: " + automovel.getPlaca());

                }
                venda.setAutomovel(automovel);

                System.out.print("> Informe o ID do Cliente: ");
                Cliente cliente = new Cliente();
                cliente = this.vendaController.buscarCliente(sc.nextInt());

                if (cliente == null){
                    System.out.println("Esse Cliente não existe, tente novamente!");
                    menuVenda();
                    break;
                }else{
                    System.out.println("> ******************* Cliente Selecionado: ******************* " + "\n");
                    System.out.println("Nome: " + cliente.getPessoa().getNome() + "\n" +"Código: " + cliente.getCodigo());

                }
                venda.setCliente(cliente);

                System.out.print("> Informe o ID do Funcionario: ");
                Funcionario funcionario;
                funcionario = this.vendaController.buscarFuncionario(sc.nextInt());

                if (funcionario == null){
                    System.out.println("Esse Funcionario não existe, tente novamente!");
                    menuVenda();
                    break;
                }else{
                    System.out.println("> ******************* Funcionario Selecionado: ******************* " + "\n");
                    System.out.println("Nome: " + funcionario.getPessoa().getNome()
                            + "\n" +"Cargo: " + funcionario.getCargo());


                }
                venda.setFuncionario(funcionario);


                System.out.println("Informe o valor da Venda:");
                venda.setValor_venda(sc.nextFloat());
                venda.setComissao_venda(venda.getValor_venda()/10);

                System.out.println("Comissão da venda: " + venda.getComissao_venda());
               
                

                System.out.println("Informe a Data de Venda:");
                String dt = new String();
                dt = sc.next();

                DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    java.util.Date dataUtil = new java.util.Date(fmt.parse(dt).getTime());
                    java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
                    venda.setDt_venda(dataSql);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Informe o código da Venda:");
                venda.setCod_venda(sc.nextInt());


                if (vendaController.cadastrar(venda)) {
                    System.out.println("Venda cadastrada!");
                } else {
                    System.out.println("Erro ao cadastrar venda, tente novamente!");
                }

                menuVenda();

                break;
            }
            case 3: {

                System.out.println("# Alterar Venda");

                List<Venda> listaAutomoveis = vendaController.listar();

                for (Venda v : listaAutomoveis){
                    System.out.println("Id:" + v.getId()
                            + "\n" +"Código de Venda:" + v.getCod_venda()
                            + "\n" +"Valor da Venda:" + v.getValor_venda()
                            + "\n" +"Funcionario:" + v.getFuncionario().getPessoa().getNome()
                            + "\n" +"Comissão de Venda:" + v.getComissao_venda()
                            + "\n" +"Cliente:" + v.getCliente().getPessoa().getNome()
                            + "\n" +"Automovel: " + v.getAutomovel().getModelo().getMarca().getNomeMarca());
                    System.out.println("************************************************");
                }

                System.out.println("Informe o id da Venda");
                Venda venda = vendaController.buscar(sc.nextInt());
                if (venda == null){
                    System.out.println("Esse Venda não existe, tente novamente!");
                    menuVenda();
                    break;
                }
                Venda vendaAlterado = venda;

                System.out.println("> Informe o Valor alterado da Venda:");
                vendaAlterado.setValor_venda(sc.nextFloat());
                vendaAlterado.setComissao_venda(vendaAlterado.getValor_venda()/10);

                System.out.println("Informe a nova data da Venda: ");
                String dt = new String();
                dt = sc.next();

                DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    java.util.Date dataUtil = new java.util.Date(fmt.parse(dt).getTime());
                    java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
                    venda.setDt_venda(dataSql);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Informe o novo código da Venda: ");
                vendaAlterado.setCod_venda(sc.nextInt());

                if (vendaController.alterar(venda.getId(),vendaAlterado)){
                    System.out.println("Venda alterada!");
                }else {
                    System.out.println("Erro ao alterar venda, tente novamente!");
                }
                menuVenda();

                break;
            }
            case 4: {

                System.out.println("# Buscar Venda pelo Id");

                System.out.println("> Informe o  id do venda:");

                int id = sc.nextInt();
                Venda v = vendaController.buscar(id);
                if (v == null) {
                    System.out.println("Venda não encontrado!");
                } else {
                    System.out.println("************************************************");
                    System.out.println("Id:" + v.getId()
                            + "\n" +"CÃ³digo de Venda:" + v.getCod_venda()
                            + "\n" +"Valor da Venda:" + v.getValor_venda()
                            + "\n" +"Funcionario:" + v.getFuncionario().getPessoa().getNome()
                            + "\n" +"Comissão de Venda:" + v.getComissao_venda()
                            + "\n" +"Cliente:" + v.getCliente().getPessoa().getNome()
                            + "\n" +"Automovel: " + v.getAutomovel().getModelo().getMarca().getNomeMarca());
                    System.out.println("************************************************");
                }

                menuVenda();
                break;
            }
            case 5: {

                System.out.println("# Excluir Venda");

                System.out.println("> Informe o id do venda:");

                int id = sc.nextInt();

                if(vendaController.remover(id)){
                    System.out.println("Venda removida com sucesso!");
                }else {
                    System.out.println("Erro ao remover venda, tente novamente!");
                }

                menuVenda();
                break;
            }

            case 6:{
                System.out.println("# Relatório de Vendas");

                System.out.println("Informe a data de início do Relatório");
                String dt = new String();
                dt = sc.next();
                java.sql.Date inicio = null;
                java.sql.Date fim = null;

                DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    java.util.Date dataUtil = new java.util.Date(fmt.parse(dt).getTime());
                    java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
                    inicio = dataSql;

                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                System.out.println("Inform a data de fim do Relatório");
                dt = sc.next();

                try {
                    java.util.Date dataUtil = new java.util.Date(fmt.parse(dt).getTime());
                    java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
                    fim = dataSql;

                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                ArrayList<RelatorioItem> relatorio = vendaController.gerarRelatorio(inicio, fim);

                if(relatorio == null){
                    System.out.println("Não há dados para exibir!");
                    menuVenda();
                    break;
                }else{
                    System.out.println(">>>>>>>>>>>>> RELATÓRIO DE VENDAS <<<<<<<<<<<<<");
                    System.out.println("DATA INICIAL: "+inicio+"                       DATA FINAL: "+fim);
                    System.out.println("-----------------------------------------------");
                    for (RelatorioItem r : relatorio ){
                        System.out.println("VENDEDOR: " + r.getNome_vendedor()
                                + "\n" +"TOTAL DE VENDAS: R$ " + r.getTotal_vendas()
                                + "\n" +"TOTAL DE COMISSÕES: R$ " + r.getTotal_comissoes());
                        System.out.println("-----------------------------------------------");
                    }
                }

            }

            case 0:
                break;
            default:
                System.out.println("Opção invalida");
                break;
        }



    }

}
