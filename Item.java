public abstract class Item extends ObjetoCelula {
    protected boolean ativo;
    protected Jogador jogador;
    int codigo;
    int start;

    public Item(String imagemInicial, int linInicial, int colInicial, Jogador jogador) {
        super(imagemInicial, linInicial, colInicial);

        this.ativo = false;
        this.jogador = jogador;
    }

    public Item(String imagemInicial, Jogador jogador) {
        super(imagemInicial);

        this.ativo = false;
        this.jogador = jogador;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int s) {
        this.start = s;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void ativa() {
        this.ativo = true;

        this.jogador.imuniza();
    }

    public void desativa() {
        this.ativo = false;

        this.jogador.desimuniza();
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