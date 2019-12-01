public class ZumbiBebado extends Zumbi{
    public ZumbiBebado(int linInicial, int colInicial) {
        super("ZumbiBebado", linInicial, colInicial);
    }

    @Override
    public void atualizaPosicao(Celula celula) {
        int linTo = celula.getLinha();
        int colTo = celula.getColuna();

        int oldLin = this.getCelula().getLinha();
        int oldCol = this.getCelula().getColuna();

        int newLin = oldLin;
        int newCol = oldCol;

        int distX = Math.abs(colTo- oldCol);
        int distY = Math.abs(linTo - oldLin);

        if (distX > distY) {
            if (colTo > oldCol) newCol = oldCol + 1;       
            else if (colTo < oldCol) newCol = oldCol - 1;    
        } else {
            if (linTo > oldLin) newLin = oldLin + 1;
            else if (linTo < oldLin) newLin = oldLin - 1;
        }

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

