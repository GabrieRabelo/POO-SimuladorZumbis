import java.util.ArrayList;
import java.util.List;

public class Jogador extends ObjetoCelula {
    private boolean imune;
    private boolean vivo;
    private boolean ganhou;
    private List<Item> inventario;

    public Jogador(int linInicial, int colInicial) {
        super("Jogador", linInicial, colInicial);
        this.vivo = true;
        this.imune = false;

        this.inventario = new ArrayList<>();

        this.inventario.add(new Corote(this));
        this.inventario.add(new Glacial(this));

        this.ganhou = false;
    }

    public boolean pegaItem(Item i) {
        if (this.inventario.size() >= 4) return false;

        this.inventario.add(i);
        return true;
    }

    public Item usaItem(int cod) {
        if(this.inventario.isEmpty()) return null;
        
        Item removido = null;
        for(int i = 0; i < this.inventario.size(); i ++) {
            if (cod == this.inventario.get(i).getCodigo()) {
                removido = this.inventario.remove(i);
                removido.ativa();
                break;
            }
        }

        return removido;
    }

    public List<Item> getInventario() {
        return this.inventario;
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

    public void ganha() {
        this.ganhou = true;
    }

    public boolean ganhou() {
        return this.ganhou;
    }

    @Override
    public void verificaEstado() {}

    @Override
    public void atualizaPosicao() {
        //talvez dependa da implementação das setas
    }

    @Override
    public void influenciaVizinhos() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void atualizaPosicao(Celula celula) {
        int oldLin = this.getCelula().getLinha();
        int oldCol = this.getCelula().getColuna();

        int newLin = celula.getLinha();
        int newCol = celula.getColuna();

        if (newLin < 0) newLin = 0;
        if (newLin >= Jogo.NLIN) newLin = Jogo.NLIN-1;
        if (newCol < 0) newCol = 0;
        if (newCol >= Jogo.NCOL) newCol = Jogo.NCOL-1;

        if (Jogo.getInstance().getCelula(newLin, newCol).getObjetoCelula() != null){
            return;
        }else{
            // Limpa celula atual
            Jogo.getInstance().getCelula(oldLin, oldCol).setObjetoCelula(null);
            // Coloca objetoCelula na nova posição
            Jogo.getInstance().getCelula(newLin, newCol).setObjetoCelula(this);
        }

    }
}