public abstract class ObjetoCelula {
    private String imagem; // Identificador da imagem
    private Celula celula;

    public ObjetoCelula(String imagemInicial,int linInicial,int colInicial){
        this.imagem = imagemInicial;
        Jogo.getInstance().getCelula(linInicial, colInicial).setObjetoCelula(this);
    }

    public ObjetoCelula(String imagemInicial){
        this.imagem = imagemInicial;
    }

    public String getImage(){
        return imagem;
    }

    public void setImage(String imagem){
        this.imagem = imagem;
    }

    public Celula getCelula(){
        return celula;
    }

    public void setCelula(Celula celula){
        this.celula = celula;
    }

    // Define próximo movimento
    public abstract void atualizaPosicao();
    
    public abstract void atualizaPosicao(Celula celula);

    // Verifica possiveis atualizações de estado a cada passo
    public abstract void verificaEstado();

    // Define como o personagem influencia os vizinhos
    // Toda vez que chega em uma célula analisa os vizinhos
    // e influencia os mesmos
    // public abstract void influenciaVizinhos();
}
