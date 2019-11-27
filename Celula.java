import javafx.scene.image.ImageView;

public class Celula extends javafx.scene.control.Button {
    private ObjetoCelula objetoCelula;
    private int linha;
    private int coluna;

    public Celula(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
        objetoCelula = null;
        setImageFromObjetoCelula();

    }

    public void setImageFromObjetoCelula(){
        if (objetoCelula != null){
            String imagem = this.getObjetoCelula().getImage();
            ImageView iVaux = new ImageView(Jogo.getInstance().getImage(imagem));
            iVaux.setFitWidth(Jogo.CELL_WIDTH);
            iVaux.setFitHeight(Jogo.CELL_HEIGHT);
            setGraphic(iVaux);        
        }else{
            ImageView iVaux = new ImageView(Jogo.getInstance().getImage("Vazio"));
            iVaux.setFitWidth(Jogo.CELL_WIDTH);
            iVaux.setFitHeight(Jogo.CELL_HEIGHT);
            setGraphic(iVaux);        
        }
    }

    public ObjetoCelula getObjetoCelula() {
        return objetoCelula;
    }

    public void setObjetoCelula(ObjetoCelula objetoCelula) {
        this.objetoCelula = objetoCelula;
        if (objetoCelula != null){
            objetoCelula.setCelula(this);
        }
        setImageFromObjetoCelula();
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
}