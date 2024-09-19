package sistema.biblioteca;
import java.util.List;

public interface Gerenciavel<Generic> {
    void adicionar(Generic obj);
    void remover(Generic obj);
    List<Generic> listarTodos();
}
