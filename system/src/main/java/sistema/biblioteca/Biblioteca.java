package sistema.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro);
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void removerUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public void emprestaLivro(Livro livro, Usuario usuario) {
        if (livro.isDisponivel()) {
            livro.setDisponivel(false);
            Emprestimo emprestimo = new Emprestimo(livro, usuario);
            emprestimos.add(emprestimo);
        }
    }

    public void devolveLivro(Livro livro, Usuario usuario) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().equals(livro) && emprestimo.getUsuario().equals(usuario)) {
                livro.setDisponivel(true);  
                emprestimos.remove(emprestimo);  
                break;
            }
        }
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorCpf(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorNome(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        return null;
    }
    public Usuario buscarUsuarioPorLivro(Livro livro) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().equals(livro)) {
                return emprestimo.getUsuario();
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorEmailSenha(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }
}

