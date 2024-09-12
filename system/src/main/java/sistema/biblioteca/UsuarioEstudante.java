package sistema.biblioteca;

public class UsuarioEstudante extends Usuario{
    private int limiteEmprestimos = 3;
    private String nivelAcesso;

    public UsuarioEstudante(String nome, String cpf, String email, String senha, String nivelAcesso) {
        super(nome, cpf, email, senha);
        this.nivelAcesso = nivelAcesso;
    }

    public int getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
}
