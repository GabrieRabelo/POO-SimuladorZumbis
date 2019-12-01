public class Corote extends Item {
    public Corote(Jogador jogador) {
        super("Corote", jogador);
        this.codigo = 1;
    }

    public Corote(int lin, int col, Jogador jogador) {
        super("Corote", lin, col, jogador);
    }

    @Override
    public void influenciaVizinhos() {

    }

}