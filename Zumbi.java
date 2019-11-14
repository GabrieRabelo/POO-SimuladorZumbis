public abstract class Zumbi extends Personagem {
    public Zumbi(int energiaInicial, String imagemInicial, int linInicial, int colInicial){
        super(energiaInicial,imagemInicial,linInicial,colInicial);
    }

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
                        Personagem p = Jogo.getInstance().getCelula(l,c).getPersonagem();
                        // Se não for nulo, infecta
                        if (p != null){
                            p.infecta();
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
}