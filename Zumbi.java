public abstract class Zumbi extends ObjetoCelula {
    private static boolean congelado;

    public Zumbi(String imagemInicial, int linInicial,int colInicial){
        super(imagemInicial,linInicial,colInicial);
    }

    @Override
    public void atualizaPosicao() {}

    @Override
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

    @Override
    public void verificaEstado() {
        // Como não sofre influencia de ninguém, o estado nunca muda
    }

    public static boolean congelado() {
        return congelado;
    }

    public static void congela(){
        Zumbi.congelado = true;
    }
    
    public static void descongela(){
        Zumbi.congelado = false;
    }

}