package entity;

import java.sql.Date;

public class Pessoa {
	private int id;
	private String nome;
	private String cpf;
	private String endereco;
	private String telefone;
	private java.sql.Date dt_nascimento;



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {return cpf;}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDt_nascimento() {return dt_nascimento; }
	public void setDt_nascimento(Date dt_nascimento) { this.dt_nascimento = dt_nascimento; }

	
	
	
	

}
