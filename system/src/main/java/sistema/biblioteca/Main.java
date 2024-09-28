package sistema.biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static Biblioteca biblioteca = new Biblioteca();
    private static Usuario usuarioLogado;

    public static void main(String[] args) {
        telaLoginCadastro();
    }
        public static void telaLoginCadastro() {
            JFrame frame = new JFrame("Login ou Cadastro");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new FlowLayout());

            JButton btnLogin = new JButton("Login");
            JButton btnCadastro = new JButton("Cadastro");

            frame.add(btnLogin);
            frame.add(btnCadastro);

            btnCadastro.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String nome = JOptionPane.showInputDialog("Nome:");
                    String cpf = JOptionPane.showInputDialog("CPF:");
                    String email = JOptionPane.showInputDialog("Email:");
                    if (email == null || !Usuario.validarEmail(email)) {
                        JOptionPane.showMessageDialog(null, "Email invalido. Insira um email no formato correto (exemplo@dominio.com).", "Erro de Validacao", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String senha = JOptionPane.showInputDialog("Senha:");
                    String[] opcoes = {"Administrador", "Estudante"};
                    String tipo = (String) JOptionPane.showInputDialog(null, "Tipo de Conta", "Cadastro",
                            JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[1]);

                    Usuario novoUsuario;
                    if (tipo.equals("Administrador")) {
                        novoUsuario = new Administrador(nome, cpf, email, senha, "Administrador", true, true);
                    } else {
                        novoUsuario = new Estudante(nome, cpf, email, senha, "Estudante");
                    }

                    biblioteca.adicionarUsuario(novoUsuario);
                    JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                }
            });
    
            btnLogin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String email = JOptionPane.showInputDialog("Email:");
                    String senha = JOptionPane.showInputDialog("Senha:");
    
                    Usuario usuario = biblioteca.buscarUsuarioPorEmailSenha(email, senha);
                    if (usuario == null) {
                        JOptionPane.showMessageDialog(null, "Credenciais invalidas.", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        usuarioLogado = usuario;
                        JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
    
                        frame.dispose();
                        telaPrincipal();
                    }
                }
            });
    
            frame.setVisible(true);
        }
        public static void telaPrincipal() {
        JFrame frame = new JFrame("Sistema de Biblioteca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new FlowLayout());

        JButton btnAdicionarLivro = new JButton("Adicionar Livro");
        JButton btnListarLivros = new JButton("Listar Livros");
        JButton btnAdicionarUsuario = new JButton("Adicionar Usuario");
        JButton btnListarUsuarios = new JButton("Listar Usuarios");
        JButton btnEmprestarLivro = new JButton("Emprestar Livro");
        JButton btnDevolverLivro = new JButton("Devolver Livro");
        JButton btnRemoverUsuario = new JButton("Remover Usuario");
        JButton btnLogoff = new JButton("Logoff");


        JButton[] buttons = {btnAdicionarLivro, btnListarLivros, btnAdicionarUsuario, btnListarUsuarios, btnEmprestarLivro, btnDevolverLivro, btnRemoverUsuario, btnLogoff};
        for (JButton button : buttons) {
            button.setPreferredSize(new Dimension(200, 50));
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setForeground(Color.BLACK);
            frame.add(button);
        }

        if (usuarioLogado instanceof Estudante) {
            btnAdicionarUsuario.setEnabled(false);
            btnAdicionarLivro.setEnabled(false);
            btnRemoverUsuario.setEnabled(false);
            btnListarUsuarios.setEnabled(false);
        }

        frame.add(btnAdicionarLivro);
        frame.add(btnListarLivros);
        frame.add(btnAdicionarUsuario);
        frame.add(btnListarUsuarios);
        frame.add(btnEmprestarLivro);
        frame.add(btnDevolverLivro);
        frame.add(btnRemoverUsuario);
        frame.add(btnLogoff);


        
        btnLogoff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Deseja realmente sair?", "Confirmar Logoff", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    usuarioLogado = null;
                    frame.dispose();
                    telaLoginCadastro();
                }
            }
        });

        btnRemoverUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeUsuario = JOptionPane.showInputDialog(frame, "Nome do usuario a ser removido:", "Remover Usuario", JOptionPane.PLAIN_MESSAGE);
                
                if (nomeUsuario == null || nomeUsuario.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Operacao cancelada.", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }
        
                Usuario usuario = biblioteca.buscarUsuarioPorNome(nomeUsuario);
        
                if (usuario == null) {
                    JOptionPane.showMessageDialog(frame, "Usuario nao encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    int confirm = JOptionPane.showConfirmDialog(frame, 
                        "Tem certeza que deseja remover o usuario '" + usuario.getNome() + "' permanentemente?", 
                        "Confirmar Exclusao", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        biblioteca.removerUsuario(usuario);
                        JOptionPane.showMessageDialog(frame, "Usuario removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Operacao cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        
        btnAdicionarLivro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog(frame, "Titulo do Livro:", "Adicionar Livro", JOptionPane.PLAIN_MESSAGE);
                if (titulo == null || titulo.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Operacao cancelada.", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String autor = JOptionPane.showInputDialog(frame, "Autor do Livro:", "Adicionar Livro", JOptionPane.PLAIN_MESSAGE);
                if (autor == null || autor.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Operacao cancelada.", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String isbn = JOptionPane.showInputDialog(frame, "ISBN do Livro:", "Adicionar Livro", JOptionPane.PLAIN_MESSAGE);
                if (isbn == null || isbn.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Operacao cancelada.", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Livro livro = new Livro(titulo, autor, isbn);
                biblioteca.adicionarLivro(livro);
                JOptionPane.showMessageDialog(frame, "Livro adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnListarLivros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder livrosListados = new StringBuilder("Livros disponiveis:\n");
                for (Livro livro : biblioteca.listarLivros()) {
                    livrosListados.append(livro.getTitulo()).append(" - ").append(livro.getAutor());
                    if (!livro.isDisponivel()) {
                        Usuario usuarioEmprestado = biblioteca.buscarUsuarioPorLivro(livro);
                        livrosListados.append(" (Emprestado para: ").append(usuarioEmprestado.getNome()).append(")");
                    } else {
                        livrosListados.append(" (Disponivel)");
                    }
                    livrosListados.append("\n");
                }
                JOptionPane.showMessageDialog(frame, livrosListados.toString(), "Listar Livros", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnAdicionarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = JOptionPane.showInputDialog(frame, "Nome do Usuario:", "Adicionar Usuario", JOptionPane.PLAIN_MESSAGE);
                if (nome == null || nome.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Operacao cancelada.", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String cpf = JOptionPane.showInputDialog(frame, "CPF do Usuario (11 dígitos):", "Adicionar Usuario", JOptionPane.PLAIN_MESSAGE);
                if (cpf == null || !Usuario.validarCPF(cpf)) {
                    JOptionPane.showMessageDialog(frame, "CPF invalido. Insira um CPF com 11 digitos numericos.", "Erro de Validacao", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String email = JOptionPane.showInputDialog(frame, "Email do Usuario:", "Adicionar Usuario", JOptionPane.PLAIN_MESSAGE);
                if (email == null || !Usuario.validarEmail(email)) {
                    JOptionPane.showMessageDialog(frame, "Email invalido. Insira um email no formato correto (exemplo@dominio.com).", "Erro de Validacao", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Usuario usuario = new Usuario(nome, cpf, email, "1234");
                biblioteca.adicionarUsuario(usuario);
                JOptionPane.showMessageDialog(frame, "Usuario adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        btnListarUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder usuariosListados = new StringBuilder("Usuarios cadastrados:\n");
                for (Usuario usuario : biblioteca.listarUsuarios()) {
                    usuariosListados.append(usuario.getNome()).append(" - ").append(usuario.getCpf()).append("\n");
                    if (usuario instanceof Administrador) {
                        usuariosListados.append(" (Admin)");
                    }
                    usuariosListados.append("\n");
                }

                JOptionPane.showMessageDialog(frame, usuariosListados.toString(), "Listar Usuarios", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnEmprestarLivro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tituloLivro = JOptionPane.showInputDialog(frame, "Titulo do livro a ser emprestado:", "Emprestar Livro", JOptionPane.PLAIN_MESSAGE);
                if (tituloLivro == null || tituloLivro.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Operacao cancelada.", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String nomeUsuario = JOptionPane.showInputDialog(frame, "Nome do usuario que esta emprestando:", "Emprestar Livro", JOptionPane.PLAIN_MESSAGE);
                if (nomeUsuario == null || nomeUsuario.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Operacao cancelada.", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Livro livro = biblioteca.buscarLivroPorTitulo(tituloLivro);
                Usuario usuario = biblioteca.buscarUsuarioPorNome(nomeUsuario);
                if (usuario == null) {
                    JOptionPane.showMessageDialog(frame, "Usuario nao encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else if (livro == null) {
                    JOptionPane.showMessageDialog(frame, "Livro nao encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else if (!livro.isDisponivel()) {
                    JOptionPane.showMessageDialog(frame, "Livro ja esta emprestado.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    biblioteca.emprestaLivro(livro, usuario);
                    JOptionPane.showMessageDialog(frame, "Livro emprestado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnDevolverLivro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tituloLivro = JOptionPane.showInputDialog(frame, "Titulo do livro a ser devolvido:", "Devolver Livro", JOptionPane.PLAIN_MESSAGE);
                if (tituloLivro == null || tituloLivro.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Operacao cancelada.", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String nomeUsuario = JOptionPane.showInputDialog(frame, "Nome do usuario que esta devolvendo:", "Devolver Livro", JOptionPane.PLAIN_MESSAGE);
                if (nomeUsuario == null || nomeUsuario.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Operacao cancelada.", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Livro livro = biblioteca.buscarLivroPorTitulo(tituloLivro);
                Usuario usuario = biblioteca.buscarUsuarioPorNome(nomeUsuario);
                if (usuario == null) {
                    JOptionPane.showMessageDialog(frame, "Usuario nao encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else if (livro == null) {
                    JOptionPane.showMessageDialog(frame, "Livro nao encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else if (livro.isDisponivel()) {
                    JOptionPane.showMessageDialog(frame, "Livro nao esta emprestado.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    biblioteca.devolveLivro(livro, usuario);
                    JOptionPane.showMessageDialog(frame, "Livro devolvido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        frame.setVisible(true);
    }
}
