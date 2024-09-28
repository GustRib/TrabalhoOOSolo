package sistema.biblioteca;

public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponivel;
    private static int totalLivros;

    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponivel = true;
        totalLivros++;
}
        public String getTitulo(){
            return titulo;
        }

        public String getAutor() {
            return autor;
        }

        public String getIsbn() {
            return isbn;
        }
        
        public boolean isDisponivel() {
            return disponivel;
        }

        public void setDisponivel(boolean disponivel) {
            this.disponivel = disponivel;
        }

        public static int getTotalLivros() {
            return totalLivros;
        }
}
