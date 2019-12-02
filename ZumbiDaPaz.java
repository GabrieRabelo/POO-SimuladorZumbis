/**
 * ZumbiDaPaz
 */
public class ZumbiDaPaz extends Zumbi {

    public ZumbiDaPaz(int lin, int col) {
        super("ZumbiDaPaz", lin, col);
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
    public void atualizaPosicao(Celula celula) {
        
    }
}