package sistema.biblioteca;

public class Administrador extends Usuario{
    private String nivelAcesso;
    private boolean gerenciarUsuario;
    private boolean gerenciarSistema;

    public Administrador(String nome, String cpf, String email, String senha, String nivelAcesso, boolean gerenciarUsuario, boolean gerenciarSistema) {
        super(nome, cpf, email, senha);
        this.nivelAcesso = nivelAcesso;
        this.gerenciarUsuario = gerenciarUsuario;
        this.gerenciarSistema = gerenciarSistema;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public boolean isGerenciarUsuario() {
        return gerenciarUsuario;
    }

    public void setGerenciarUsuario(boolean gerenciarUsuario) {
        this.gerenciarUsuario = gerenciarUsuario;
    }

    public boolean isGerenciarSistema() {
        return gerenciarSistema;
    }

    public void setGerenciarSistema(boolean gerenciarSistema) {
        this.gerenciarSistema = gerenciarSistema;
    }
}
