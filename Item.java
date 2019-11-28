public abstract class Item extends ObjetoCelula {
    private Celula celula;
    private String imagem;
    protected boolean ativo;

    public Item(String imagemInicial, int linInicial, int colInicial) {
        super(imagemInicial, linInicial, colInicial);

        this.ativo = false;
    }

    public Item(String imagemInicial) {
        super(imagemInicial);

        this.ativo = false;
    }

    public void ativa() {
        this.ativo = true;
    }

    public void desativa() {
        this.ativo = false;
    }

    public boolean estaAtivo() {
        return this.ativo;
    }

    @Override
    public void verificaEstado() {

    }

    @Override
    public void atualizaPosicao() {
        
    }

    @Override
    public void atualizaPosicao(Celula celula) {
        
    }
}