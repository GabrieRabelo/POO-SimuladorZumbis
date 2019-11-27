public abstract class Item extends ObjetoCelula {
    private Celula celula;
    private String imagem;

    public Item(String imagemInicial, int linInicial, int colInicial) {
        super(imagemInicial, linInicial, colInicial);
    }

    @Override
    public void verificaEstado() {

    }

    @Override
    public void atualizaPosicao() {
        
    }
}