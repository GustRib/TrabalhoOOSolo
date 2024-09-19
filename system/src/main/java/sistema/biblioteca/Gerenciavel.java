package sistema.biblioteca;
import java.util.List;

public interface Gerenciavel<Generic> {
    // Interface com Crud para tratar Objetos Genericos do meu sistema utilizando o Polimorfismo de Sobreposicao
    void adicionar(Generic obj);
    void remover(Generic obj);
    List<Generic> listarTodos();
}
