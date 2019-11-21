public class Item {
    private int id;
    private String nome;
    private Celula celula;

    public Item(int id) {
        this.id = id;
        Jogo.getInstance().getCelula(linInicial, colInicial).setPersonagem(this);
    }
}