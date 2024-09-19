package sistema.biblioteca;
import java.util.ArrayList;
import java.util.List;

public class Repositorio<Generic> implements Gerenciavel<Generic> {
    private List<Generic> itens = new ArrayList<>();

    @Override // polimorfismo
    public void adicionar(Generic Generic) {
        itens.add(Generic);
    }

    @Override // polimorfismo
    public void remover(Generic Generic) {
        itens.remove(Generic);
    }

    @Override // polimorfismo
    public List<Generic> listarTodos() {
        return itens;
    }
}
