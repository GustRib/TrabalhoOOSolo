package sistema.biblioteca;
import java.util.ArrayList;
import java.util.List;

public class Repositorio<Generic> implements Gerenciavel<Generic> {
    private List<Generic> itens = new ArrayList<>();

    @Override
    public void adicionar(Generic Generic) {
        itens.add(Generic);
    }

    @Override
    public void remover(Generic Generic) {
        itens.remove(Generic);
    }

    @Override
    public List<Generic> listarTodos() {
        return itens;
    }
}
