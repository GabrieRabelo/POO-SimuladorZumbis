public abstract class Zumbi extends ObjetoCelula {
    public Zumbi(String imagemInicial, int linInicial,int colInicial){
        super(imagemInicial,linInicial,colInicial);
    }

    @Override
    public void atualizaPosicao() {
        int dirLin = Jogo.getInstance().aleatorio(3)-1;
        int dirCol = Jogo.getInstance().aleatorio(3)-1;
        int oldLin = this.getCelula().getLinha();
        int oldCol = this.getCelula().getColuna();
        int lin = oldLin + dirLin;
        int col = oldCol + dirCol;
        if (lin < 0) lin = 0;
        if (lin >= Jogo.NLIN) lin = Jogo.NLIN-1;
        if (col < 0) col = 0;
        if (col >= Jogo.NCOL) col = Jogo.NCOL-1;
        if (Jogo.getInstance().getCelula(lin, col).getObjetoCelula() != null){
            return;
        }else{
            // Limpa celula atual
            Jogo.getInstance().getCelula(oldLin, oldCol).setObjetoCelula(null);
            // Coloca Objeto na nova posição
            Jogo.getInstance().getCelula(lin, col).setObjetoCelula(this);
        }
    }

    @Override
    public void verificaEstado() {
        // Como não sofre influencia de ninguém, o estado nunca muda
    }
}