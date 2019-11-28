public class Glacial extends Item {
    Jogador jogador;

    public Glacial(Jogador jogador) {
        super("Glacial");

        this.jogador = jogador;
    }

    @Override
    public void ativa() {
        this.ativo = true;
        this.jogador.imuniza();
    }

    @Override
    public void desativa() {
        this.ativo = false;
        this.jogador.desimuniza();
    }
}