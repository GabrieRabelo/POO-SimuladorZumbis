
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Jogo extends Application {
    public static final int CELL_WIDTH = 20;
    public static final int CELL_HEIGHT = 20;
    public static final int NLIN = 10;
    public static final int NCOL = 10;

    public static final int SPAWN_RATIO = 5;

    public static Jogo jogo = null;
    public Jogador jogador;

    public static int roundCounter;

    private Random random;
    private Map<String, Image> imagens;
    private List<Celula> celulas;
    private List<ObjetoCelula> objetoCelulas;

    public static Jogo getInstance(){
        return jogo;
    }

    public Jogo(){
        jogo = this;
        random = new Random();
        roundCounter = 0;
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Retorna um número aleatorio a partir do gerador unico
    public int aleatorio(int limite){
        return random.nextInt(limite);
    }

    // Retorna a celula de uma certa linha,coluna
    public Celula getCelula(int nLin,int nCol){
        int pos = (nLin*NCOL)+nCol;
        return celulas.get(pos);
    }

    private void loadImagens() {
        imagens = new HashMap<>();

        // Armazena as imagens dos ObjetoCelulas
        Image aux = new Image("file:Imagens\\img1.jpg");
        imagens.put("Jogador", aux);
        aux = new Image("file:Imagens\\zumbi-bebado.gif");
        imagens.put("ZumbiBebado", aux);
        aux = new Image("file:Imagens\\zumbi-da-paz.gif");
        imagens.put("ZumbiDaPaz", aux);
        aux = new Image("file:Imagens\\corote.png");
        imagens.put("Corote", aux);
        aux = new Image("file:Imagens\\glacial.png");
        imagens.put("Glacial", aux);

        // Armazena a imagem da celula nula
        imagens.put("Null", null);
    }

    public Image getImage(String id){
        return imagens.get(id);
    }

    public void spawnZumbiBebado() {
        boolean posOk = false;

        posOk = false;
        while(!posOk){
            int lin = random.nextInt(3) + 7;
            int col = random.nextInt(NCOL);
            if (this.getCelula(lin, col).getObjetoCelula() == null){
                objetoCelulas.add(new ZumbiBebado(lin,col));
                posOk = true;
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // Carrega imagens
        loadImagens();

        // Configura a interface com o usuario
        primaryStage.setTitle("Simulador de Zumbis");
        GridPane tab = new GridPane();
        tab.setAlignment(Pos.CENTER);
        tab.setHgap(10);
        tab.setVgap(10);
        tab.setPadding(new Insets(25, 25, 25, 25));

        // Monta o "tabuleiro"
        celulas = new ArrayList<>(NLIN*NCOL);
        for (int lin = 0; lin < NLIN; lin++) {
            for (int col = 0; col < NCOL; col++) {
                Celula cel = new Celula(lin,col);
                celulas.add(cel);
                tab.add(cel, col, lin);
            }
        }
        
        objetoCelulas = new ArrayList<>(NLIN*NCOL);
        
        //cria e posiciona o jogador protagonista.
        boolean posOk = false;
        while(!posOk){
            int lin = random.nextInt(NLIN);
            int col = random.nextInt(NCOL);
            if (this.getCelula(lin, col).getObjetoCelula() == null){
                jogador = new Jogador(lin, col);
                objetoCelulas.add(jogador);
                posOk = true;
            }
        }

        // Cria 1 Zumbi Bebado aleatorio
        spawnZumbiBebado();

        // Define os botoes que avançam a simulação
        Button up = new Button("CIMA");
        up.setOnAction(e -> {
            Celula c = jogador.getCelula();
            int lin = c.getLinha() - 1;
            int col = c.getColuna();

            jogador.atualizaPosicao(new Celula(lin, col));
            avancaSimulacao();
        });
        Button down = new Button("BAIXO");
        down.setOnAction(e -> {
            Celula c = jogador.getCelula();
            int lin = c.getLinha() + 1;
            int col = c.getColuna();
            
            jogador.atualizaPosicao(new Celula(lin, col));
            avancaSimulacao();   
        });
        Button left = new Button("ESQUERDA");
        left.setOnAction(e -> {
            Celula c = jogador.getCelula();
            int lin = c.getLinha();
            int col = c.getColuna() - 1;
            
            jogador.atualizaPosicao(new Celula(lin, col));
            avancaSimulacao();
        });
        Button right = new Button("DIREITA");
        right.setOnAction(e -> {
            Celula c = jogador.getCelula();
            int lin = c.getLinha();
            int col = c.getColuna() + 1;
            
            jogador.atualizaPosicao(new Celula(lin, col));
            avancaSimulacao();
        });

        Button corote = new Button("Corote");
        corote.setOnAction(e -> {
            this.usaItem(1);
        });
        
        Button glacial = new Button("Glacial");
        

        // Monta a cena e exibe
        HBox hb = new HBox(10);
        hb.setAlignment(Pos.CENTER);
        hb.setPadding(new Insets(25, 25, 25, 25));
        hb.getChildren().add(tab);
        hb.getChildren().add(up);      
        hb.getChildren().add(down);      
        hb.getChildren().add(left);      
        hb.getChildren().add(right);

        hb.getChildren().add(corote);  
        hb.getChildren().add(glacial);    
        
        Scene scene = new Scene(hb);
        primaryStage.setScene(scene);

        // Scene scene2 = new Scene(hb2);
        // primaryStage.setScene(scene2);
        primaryStage.show();
    }

    public void avancaSimulacao(){
        Celula cel;

        // Avança um passo em todos os objetoCelulas
        objetoCelulas.forEach(p->{
            if (p instanceof ZumbiBebado) p.atualizaPosicao(jogador.getCelula());
            else if (! (p instanceof Jogador)) p.atualizaPosicao();
            p.verificaEstado();
        });
        // Verifica se o jogo acabou
        long vivos = objetoCelulas
                    .stream()
                    .filter(p->!(p instanceof Zumbi))
                    .count();
        if (vivos == 0){
            Alert msgBox = new Alert(AlertType.INFORMATION);
            msgBox.setHeaderText("Fim de Jogo");
            msgBox.setContentText("Todos os boboes morreram!");
            msgBox.show();
            System.exit(0);
        }

        roundCounter ++;

        if (roundCounter % SPAWN_RATIO == 0) {
            spawnZumbiBebado();
        }
    }

    private void usaItem(int cod) {
        Item i = this.jogador.usaItem(cod);

        if(i != null) {
            if(cod == 1) {
                boolean posOk = false;
    
                posOk = false;
                while(!posOk){
                    int lin = random.nextInt(NLIN);
                    int col = random.nextInt(NCOL);
                    if (this.getCelula(lin, col).getObjetoCelula() == null){
                        objetoCelulas.add(new Corote(lin,col));
                        posOk = true;
                    }
                }
            }
        }
        
    }

    public void anda() {
    }
}
