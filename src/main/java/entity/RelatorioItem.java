package entity;

public class RelatorioItem {
    private String nome_vendedor;
    private float total_vendas;
    private float total_comissoes;

    public String getNome_vendedor() {
        return nome_vendedor;
    }

    public void setNome_vendedor(String nome_vendedor) {
        this.nome_vendedor = nome_vendedor;
    }

    public float getTotal_vendas() {
        return total_vendas;
    }

    public void setTotal_vendas(float total_vendas) {
        this.total_vendas = total_vendas;
    }

    public float getTotal_comissoes() {
        return total_comissoes;
    }

    public void setTotal_comissoes(float total_comissoes) {
        this.total_comissoes = total_comissoes;
    }
}
