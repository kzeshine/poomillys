package entity;

public class Gerente extends Funcionario {

	@Override
	public float calculaSalario(float salario) {
		return (float) (salario*1.5);
	}

	private String departamento;

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	

}
