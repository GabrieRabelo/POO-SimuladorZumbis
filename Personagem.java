public abstract class Personagem {
    private String imagem; // Identificador da imagem
    // private int energia;
    private Celula celula;

    public Personagem(String imagemInicial,int linInicial,int colInicial){
        this.imagem = imagemInicial;
        Jogo.getInstance().getCelula(linInicial, colInicial).setPersonagem(this);
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

    // Verifica possiveis atualizações de estado a cada passo
    public abstract void verificaEstado();

    // Define como o personagem influencia os vizinhos
    // Toda vez que chega em uma célula analisa os vizinhos
    // e influencia os mesmos
    public abstract void influenciaVizinhos();
}
