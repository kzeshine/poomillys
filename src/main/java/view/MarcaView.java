package view;

import controller.MarcaController;
import entity.Marca;

import java.util.List;
import java.util.Scanner;

public class MarcaView {

	private MarcaController marcaController;
	
	public MarcaView() {
		this.marcaController = new MarcaController();
	}
	
	public void menuMarca() {
	        System.out.println("#Menu Marca");
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
	            	
	            	System.out.println("# Lista de Marcas");

					List<Marca> listaMarcas = marcaController.listar();

					for (Marca m : listaMarcas){
						System.out.println("Id: "+m.getId()+"\n" + "Nome: "+m.getNomeMarca());
						System.out.println("************************************************");
					}


					menuMarca();
	                break;
	            }
	            case 2: {
					System.out.println("# Cadastrar Marca");


					System.out.println("> Informe a marca:");
					sc.nextLine();
					String nome = sc.nextLine();

					Marca marca = new Marca();
					marca.setNomeMarca(nome);

					if (marcaController.cadastrar(marca)) {
						System.out.println("Marca cadastrada!");
					} else {
						System.out.println("Erro ao cadastrar marca, tente novamente!");
					}

					menuMarca();

					break;
				}
	            case 3: {

	            	System.out.println("# Alterar Marca");

					List<Marca> listaMarcas = marcaController.listar();

					for (Marca m : listaMarcas){
						System.out.println("Id: "+m.getId()+"\n" + "Nome: "+m.getNomeMarca());
						System.out.println("************************************************");
					}
	            	
	            	System.out.println("> Informe o id marca:");
	            	int idMarca = sc.nextInt();

	            	Marca marca = marcaController.buscar(idMarca);
					if (marca == null){
						System.out.println("Essa Marca não existe, tente novamente!");
						menuMarca();
					}

					System.out.println("> Informe o novo nome da marca:");
					sc.nextLine();
					String nomeMarcaAlterada = sc.nextLine();

	            	if (marcaController.alterar(idMarca,nomeMarcaAlterada)){
						System.out.println("Marca alterada!");
					}else {
						System.out.println("Erro ao alterar marca, tente novamente!");
					}


					break;
				}
	            case 4: {

					System.out.println("# Buscar Marca pelo Id");

					System.out.println("> Informe o  id da marca:");

					int id = sc.nextInt();
					Marca m = marcaController.buscar(id);
					if (m == null) {
						System.out.println("Marca não encontrada!");
					} else {
						System.out.println("************************************************");
						System.out.println("Id: " + m.getId() + "\n" + "Nome: " + m.getNomeMarca());
						System.out.println("************************************************");
					}

					menuMarca();
					break;
				}
	            case 5: {

					System.out.println("# Excluir Marca");

					System.out.println("> Informe o id da marca:");

					int id = sc.nextInt();

					if(marcaController.remover(id)){
						System.out.println("Marca removida com sucesso!");
					}else {
						System.out.println("Erro ao remover marca, tente novamente!");
					}

					menuMarca();
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
