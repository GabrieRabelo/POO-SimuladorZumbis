public class Glacial extends Item {
    public Glacial(Jogador jogador) {
        super("Glacial", jogador);
        this.codigo = 2;
    }

    @Override
    public void ativa() {
        this.ativo = true;
        this.jogador.imuniza();
    }

    @Override
    public void influenciaVizinhos() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void desativa() {
        this.ativo = false;
        this.jogador.desimuniza();
    }
}