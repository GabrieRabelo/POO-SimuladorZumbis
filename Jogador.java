import java.util.ArrayList;
import java.util.List;
public class Jogador extends ObjetoCelula {
    private boolean imune;
    private boolean vivo;
    // private List<Item> inventario;

    public Jogador(int linInicial, int colInicial) {
        super("Jogador", linInicial, colInicial);
        this.vivo = true;
        this.imune = false;

        // this.inventario = new ArrayList<>();
    }

    public boolean estaImune(){
        return imune;
    }

    public void imuniza(){
        imune = true;
    }

    public void desimuniza(){
        imune = false;
    }

    public boolean estaVivo() {
        return vivo;
    }

    public void morre() {
        if (!this.imune) {
            vivo = false;
        }
    }

    @Override
    public void verificaEstado() {

    }

    @Override
    public void atualizaPosicao() {

    }

    @Override
    public void atualizaPosicao(Celula celula) {

    }
}