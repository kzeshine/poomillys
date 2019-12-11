package view;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import controller.AutomovelController;
import entity.Modelo;
import entity.Automovel;
import entity.Modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class AutomovelView {

    private AutomovelController automovelController;

    public AutomovelView() {
        this.automovelController = new AutomovelController();
    }

    public void menuAutomovel() {
        System.out.println("#Menu Automovel");
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

                System.out.println("# Lista de Automoveis");

                List<Automovel> listaAutomoveis = automovelController.listar();

                for (Automovel m : listaAutomoveis){
                    System.out.println("Id: "+m.getId()+"\n" +  "Marca do Automovel: "+m.getModelo().getMarca().getNomeMarca() +
                            "\n" + "Modelo: "+m.getModelo().getNomeModelo() + "\n" +"Tipo de Modelo: "+m.getModelo().getTipo() + "\n" +"Cor: "+m.getCor() +
                            "\n" +"Data de Fabricação: "+m.getAno_fabricacao() +"\n" + "Data do modelo: "+m.getAno_modelo()
                            +"\n" +  "Chassi: "+m.getChassi() +"\n" +  "Kilometragem: "+m.getKm() +"\n" +  "Valor do Automovel: "+m.getValor()
                     +"\n" +  "Placa do Automovel: "+m.getPlaca());
                    System.out.println("************************************************");
                }


                menuAutomovel();
                break;
            }
            case 2: {
                System.out.println("# Cadastrar Automovel");

                Automovel automovel = new Automovel();

                System.out.println("> Informe o ID do Modelo:");
                int id = sc.nextInt();

                Modelo modelo = new Modelo();

                modelo = automovelController.buscarModelo(id);
                if (modelo == null){
                    System.out.println("Essa Modelo não existe, tente novamente!");
                    menuAutomovel();
                }else{
                    System.out.println("Marca selecionada: " + modelo.getMarca().getNomeMarca() + "\n" +"Modelo selecionado: " + modelo.getNomeModelo());

                }
                automovel.setModelo(modelo);

                System.out.println("Informe a cor do Automovel: ");
                automovel.setCor(sc.next());
                System.out.println("Informe a Data de fabricação do Automóvel:");
                String dt = new String();
                dt = sc.next();

                DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    java.util.Date dataUtil = new java.util.Date(fmt.parse(dt).getTime());
                    java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
                    automovel.setAno_fabricacao(dataSql);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                System.out.println("Informe a Data do modelo do Automóvel:");
                dt = sc.next();
                try {
                    java.util.Date dataUtil = new java.sql.Date(fmt.parse(dt).getTime());
                    java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
                    automovel.setAno_modelo(dataSql);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Informe o Chassi do Automovel: ");
                automovel.setChassi(sc.next());

                System.out.println("Informe a Kilometragem do Automovel: ");
                automovel.setKm(sc.nextFloat());

                System.out.println("Informe o Valor do Automovel: ");
                automovel.setValor(sc.nextFloat());

                System.out.println("Informe a Placa do Automovel: ");
                automovel.setPlaca(sc.next());


                if (automovelController.cadastrar(automovel)) {
                    System.out.println("Automovel cadastrada!");
                } else {
                    System.out.println("Erro ao cadastrar automovel, tente novamente!");
                }

                menuAutomovel();

                break;
            }
            case 3: {

                System.out.println("# Alterar Automovel");

                List<Automovel> listaAutomoveis = automovelController.listar();

                for (Automovel m : listaAutomoveis){
                    System.out.println("Id: "+m.getId()+"\n" +  "Marca do Automovel: "+m.getModelo().getMarca().getNomeMarca() +
                            "\n" + "Modelo: "+m.getModelo().getNomeModelo() + "\n" +"Tipo de Modelo: "+m.getModelo().getTipo() + "cor: "+m.getCor() +
                            "\n" +"Data de Fabricação: "+m.getAno_fabricacao() +"\n" + "Data do modelo: "+m.getAno_modelo()
                            +"\n" +  "Chassi: "+m.getChassi() +"\n" +  "Kilometragem: "+m.getKm() +"\n" +  "Valor do Automovel: "+m.getValor()
                            +"\n" +  "Placa do Automovel: "+m.getPlaca());
                    System.out.println("************************************************");
                }

                System.out.println("> Informe o id  do Automovel:");
                int idAutomovel = sc.nextInt();

                Automovel automovel = automovelController.buscar(idAutomovel);
                if (automovel == null){
                    System.out.println("Esse Automovel não existe, tente novamente!");
                    menuAutomovel();
                }
                Automovel automovelAlterado = new Automovel();

                System.out.println("Informe a nova cor do Automovel: ");
                automovelAlterado.setCor(sc.next());
                System.out.println("Informe a nova Data de fabricação do Automóvel:");
                String dt = new String();
                dt = sc.next();

                DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    java.util.Date dataUtil = new java.util.Date(fmt.parse(dt).getTime());
                    java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
                    automovelAlterado.setAno_fabricacao(dataSql);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                System.out.println("Informe a nova Data do modelo do Automóvel:");
                dt = sc.next();
                try {
                    java.util.Date dataUtil = new java.sql.Date(fmt.parse(dt).getTime());
                    java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
                    automovelAlterado.setAno_fabricacao(dataSql);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Informe o novo Chassi do Automovel: ");
                automovelAlterado.setChassi(sc.next());

                System.out.println("Informe a nova Kilometragem do Automovel: ");
                automovelAlterado.setKm(sc.nextFloat());

                System.out.println("Informe o novo Valor do Automovel: ");
                automovelAlterado.setValor(sc.nextFloat());

                System.out.println("Informe a nova Placa do Automovel: ");
                automovelAlterado.setPlaca(sc.next());

                if (automovelController.alterar(idAutomovel,automovelAlterado)){
                    System.out.println("Automovel alterada!");
                }else {
                    System.out.println("Erro ao alterar automovel, tente novamente!");
                }
                menuAutomovel();

                break;
            }
            case 4: {

                System.out.println("# Buscar Automovel pelo Id");

                System.out.println("> Informe o  id do automovel:");

                int id = sc.nextInt();
                Automovel m = automovelController.buscar(id);
                if (m == null) {
                    System.out.println("Automovel não encontrado!");
                } else {
                    System.out.println("************************************************");
                    System.out.println("Id: "+m.getId()+"\n" +  "Marca do Automovel: "+m.getModelo().getMarca().getNomeMarca() +
                            "\n" + "Modelo: "+m.getModelo().getNomeModelo() + "\n" +"Tipo de Modelo: "+m.getModelo().getTipo() + "\n" +"Cor: "+m.getCor() +
                            "\n" +"Data de Fabricação: "+m.getAno_fabricacao() +"\n" + "Data do modelo: "+m.getAno_modelo()
                            +"\n" +  "Chassi: "+m.getChassi() +"\n" +  "Kilometragem: "+m.getKm() +"\n" +  "Valor do Automovel: "+m.getValor()
                            +"\n" +  "Placa do Automovel: "+m.getPlaca());
                    System.out.println("************************************************");
                }

                menuAutomovel();
                break;
            }
            case 5: {

                System.out.println("# Excluir Automovel");

                System.out.println("> Informe o id do automovel:");

                int id = sc.nextInt();

                if(automovelController.remover(id)){
                    System.out.println("Automovel removida com sucesso!");
                }else {
                    System.out.println("Erro ao remover automovel, tente novamente!");
                }

                menuAutomovel();
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
