public class ZumbiBebado extends Zumbi{
    public static Celula target;
    public ZumbiBebado(int linInicial, int colInicial) {
        super("ZumbiBebado", linInicial, colInicial);
    }

    public static Celula getTarget() {
        return target;
    }

    public static void setTarget(Celula c) {
        target = c;
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

    public void atualizaPosicao() {}

    public void influenciaVizinhos() {
        int lin = this.getCelula().getLinha();
        int col = this.getCelula().getColuna();
        for(int l=lin-1;l<=lin+1;l++){
            for(int c=col-1;c<=col+1;c++){
                // Se a posição é dentro do tabuleiro
                if (l>=0 && l<Jogo.NLIN && c>=0 && c<Jogo.NCOL){
                    // Se não é a propria celula
                    if (!( lin == l && col == c)){
                        // Recupera o personagem da célula vizinha
                        ObjetoCelula p = Jogo.getInstance().getCelula(l,c).getObjetoCelula();
                        // Se não for nulo, infecta
                        if (p != null && p instanceof Jogador) {
                            Jogador p2 = (Jogador)p;
                            if(!p2.estaImune()) {
                                p2.morre();
                            }
                        }
                    }
                }
            }
        }
        
    }
}

