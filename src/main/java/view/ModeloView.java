package view;

import controller.ModeloController;
import entity.Marca;
import entity.Modelo;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ModeloView {

	private ModeloController modeloController;

	public ModeloView() {
		this.modeloController = new ModeloController();
	}

	public void menuModelo() {
		System.out.println("#Menu Modelo");
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

				System.out.println("# Lista de Modelos");

				List<Modelo> listaModelos = modeloController.listar();

				for (Modelo m : listaModelos){
					System.out.println("Id: "+m.getId()+"\n" + "Modelo: "+m.getNomeModelo() +
							"\n" +"Tipo: "+m.getTipo() +"\n" + "Marca: "+m.getMarca().getNomeMarca());
					System.out.println("************************************************");
				}


				menuModelo();
				break;
			}
			case 2: {
				System.out.println("# Cadastrar Modelo");

				Modelo modelo = new Modelo();

				System.out.println("> Informe o ID da Marca:");
				int id = sc.nextInt();

				Marca marca = new Marca();
				marca = modeloController.buscarMarca(id);
				if (marca == null){
					System.out.println("Essa Marca não existe, tente novamente!");
					menuModelo();
				}else{
					System.out.println("Marca selecionada: " + marca.getNomeMarca());

				}
				modelo.setMarca(marca);

				System.out.println("Informe o Modelo: ");
				modelo.setNomeModelo(sc.next());

				System.out.println("Informe o tipo do Modelo: ");
				modelo.setTipo(sc.next());

				if (modeloController.cadastrar(modelo)) {
					System.out.println("Modelo cadastrada!");
				} else {
					System.out.println("Erro ao cadastrar modelo, tente novamente!");
				}

				menuModelo();

				break;
			}
			case 3: {

				System.out.println("# Alterar Modelo");

				List<Modelo> listaModelos = modeloController.listar();

				for (Modelo m : listaModelos){
					System.out.println("Id: "+m.getId()+"\n" + "Modelo: "+m.getNomeModelo() +
							"\n" +"Tipo: "+m.getTipo() +"\n" + "Marca: "+m.getMarca().getNomeMarca());
					System.out.println("************************************************");
				}

				System.out.println("> Informe o id modelo:");
				int idModelo = sc.nextInt();

				Modelo modelo = modeloController.buscar(idModelo);
				if (modelo == null){
					System.out.println("Esse Modelo não existe, tente novamente!");
					menuModelo();
				}
				Modelo modeloAlterado = new Modelo();

				System.out.println("> Informe o novo nome do modelo:");
				modeloAlterado.setNomeModelo(sc.next());

				System.out.println("> Informe o novo tipo:");
				modeloAlterado.setTipo(sc.next());

				if (modeloController.alterar(idModelo,modeloAlterado)){
					System.out.println("Modelo alterada!");
				}else {
					System.out.println("Erro ao alterar modelo, tente novamente!");
				}
				menuModelo();

				break;
			}
			case 4: {

				System.out.println("# Buscar Modelo pelo Id");

				System.out.println("> Informe o  id do modelo:");

				int id = sc.nextInt();
				Modelo m = modeloController.buscar(id);
				if (m == null) {
					System.out.println("Modelo não encontrado!");
				} else {
					System.out.println("************************************************");
					System.out.println("Id: "+m.getId()+"\n" + "Modelo: "+m.getNomeModelo() +
							"\n" +"Tipo: "+m.getTipo() +"\n" + "Marca: "+m.getMarca().getNomeMarca());
					System.out.println("************************************************");
				}

				menuModelo();
				break;
			}
			case 5: {

				System.out.println("# Excluir Modelo");

				System.out.println("> Informe o id do modelo:");

				int id = sc.nextInt();

				if(modeloController.remover(id)){
					System.out.println("Modelo removida com sucesso!");
				}else {
					System.out.println("Erro ao remover modelo, tente novamente!");
				}

				menuModelo();
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
