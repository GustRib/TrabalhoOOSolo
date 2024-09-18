package sistema.biblioteca;

import java.io.Serializable;
import java.util.regex.Pattern;

public class Usuario extends Pessoa implements Serializable {
    private String cpf;
    private String email;
    private String senha;

    public Usuario(String nome, String cpf, String email, String senha) {
        super(nome);

        if (!validarNome(nome) || !validarCPF(cpf) || email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Dados invalidos.");
        }

        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    private boolean validarNome(String nome) {
        return Pattern.matches("[a-zA-Z\\s]+", nome);
    }

    private boolean validarCPF(String cpf) {
        String regex = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$";
        if (!Pattern.matches(regex, cpf)) {
            return false;
        }
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        return true;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!validarCPF(cpf)) {
            throw new IllegalArgumentException("CPF invalido.");
        }
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email invalido.");
        }
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override // polimorfismo
    public String getIdentificacao() {
        return cpf;
    }
}
