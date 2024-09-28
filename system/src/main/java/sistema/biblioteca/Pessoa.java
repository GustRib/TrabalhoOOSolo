package sistema.biblioteca;

public abstract class Pessoa {
    protected String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract void setNome(String nome);
    public abstract String getIdentificacao();
}
