public class Glacial extends Item {
    public Glacial(Jogador jogador) {
        super("Glacial", jogador);
        this.codigo = 2;
    }

    @Override
    public void ativa() {
        this.ativo = true;
        Zumbi.congela();
    }

    public void desativa() {
        this.ativo = false;
        Zumbi.descongela();
    }


    @Override
    public void influenciaVizinhos() {
        
    }
}