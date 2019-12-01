public class Telefone extends ObjetoCelula {
    private Jogador jogador;
    public Telefone(int lin, int col, Jogador jogador) {
        super("Telefone", lin, col);

        this.jogador = jogador;
    }

    public Jogador getJogador() {
        return jogador;
    }

    @Override
    public void atualizaPosicao() {
        // TODO Auto-generated method stub

    }

    @Override
    public void atualizaPosicao(Celula celula) {
        // TODO Auto-generated method stub

    }

    @Override
    public void verificaEstado() {
        Celula tel = this.getCelula();
        Celula jog = this.jogador.getCelula();

        int difX = Math.abs(tel.getColuna() - jog.getColuna()); 
        int difY = Math.abs(tel.getLinha() - jog.getLinha());

        double dist = Math.sqrt(Math.pow(difX, 2) + Math.pow(difY, 2));

        if (dist < 2) {
            this.jogador.ganha();
        }
    }

    @Override
    public void influenciaVizinhos() {
        // TODO Auto-generated method stub

    }

    
}