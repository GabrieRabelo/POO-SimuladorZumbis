
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Jogo extends Application {
    public static final int CELL_WIDTH = 30;
    public static final int CELL_HEIGHT = 30;
    public static final int NLIN = 15;
    public static final int NCOL = 15;

    public static final int SPAWN_RATIO = 7;

    public static Jogo jogo = null;
    public Jogador jogador;
    private javafx.scene.control.Button closeButton;
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

    public int aleatorio(int limite){
        return random.nextInt(limite);
    }

    public Celula getCelula(int nLin,int nCol){
        int pos = (nLin*NCOL)+nCol;
        return celulas.get(pos);
    }

    private void loadImagens() {
        imagens = new HashMap<>();

        // Armazena as imagens dos ObjetoCelulas
        Image aux = new Image("file:Imagens\\jogador.jpeg");
        imagens.put("Jogador", aux);
        aux = new Image("file:Imagens\\zumbi-bebado.gif");
        imagens.put("ZumbiBebado", aux);
        aux = new Image("file:Imagens\\zumbi-da-paz.gif");
        imagens.put("ZumbiDaPaz", aux);
        aux = new Image("file:Imagens\\corote.png");
        imagens.put("Corote", aux);
        aux = new Image("file:Imagens\\glacial.png");
        imagens.put("Glacial", aux);
        aux = new Image("file:Imagens\\telefone.jpeg");
        imagens.put("Telefone", aux);

        // Armazena a imagem da celula nula
        imagens.put("Null", null);
    }

    public Image getImage(String id){
        return imagens.get(id);
    }

    @Override
    public void start(Stage primaryStage) {
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
            int lin = random.nextInt(3);
            int col = random.nextInt(NCOL);
            if (this.getCelula(lin, col).getObjetoCelula() == null){
                jogador = new Jogador(lin, col);
                objetoCelulas.add(jogador);
                posOk = true;
            }
        }

        //cria e posiciona aleatoriamente o zumbi da paz
        
        for(int i = 0; i < 3; i ++) {
            posOk = false;
            while(!posOk){
                int lin = random.nextInt(3) + 12;
                int col = random.nextInt(NCOL);
                if (this.getCelula(lin, col).getObjetoCelula() == null){
                    ZumbiDaPaz zumbi = new ZumbiDaPaz(lin, col);
                    objetoCelulas.add(zumbi);
                    posOk = true;
                }
            }
        }

        // Cria 1 Zumbi Bebado aleatorio
        spawnZumbiBebado();
        ZumbiBebado.setTarget(jogador.getCelula());

        // Define os botoes que avançam a simulação
        Button up = new Button("/\\");
        up.setOnAction(e -> {
            Celula c = jogador.getCelula();
            int lin = c.getLinha() - 1;
            int col = c.getColuna();

            jogador.atualizaPosicao(new Celula(lin, col));
            avancaSimulacao();
        });
        Button down = new Button("\\/");
        down.setOnAction(e -> {
            Celula c = jogador.getCelula();
            int lin = c.getLinha() + 1;
            int col = c.getColuna();
            
            jogador.atualizaPosicao(new Celula(lin, col));
            avancaSimulacao();   
        });
        Button left = new Button("<<");
        left.setOnAction(e -> {
            Celula c = jogador.getCelula();
            int lin = c.getLinha();
            int col = c.getColuna() - 1;
            
            jogador.atualizaPosicao(new Celula(lin, col));
            avancaSimulacao();
        });
        Button right = new Button(">>");
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
        glacial.setOnAction(e -> {
            this.usaItem(2);
        });

        Button sair = new Button("Sair");
        sair.setOnAction(e -> System.exit(0s));

        Button reinicia = new Button("Reiniciar");
        sair.setOnAction(e -> {

        });

        // Monta a cena e exibe

        GridPane tab2 = new GridPane();
        tab2.setAlignment(Pos.CENTER);
        tab2.setHgap(1);
        tab2.setVgap(5);
        tab2.setPadding(new Insets(25, 25, 25, 25));
        tab2.add(up, 2, 0);
        tab2.add(down,2,1);
        tab2.add(left,1,1);
        tab2.add(right, 3,1);
        tab2.add(corote, 1,4);
        tab2.add(glacial,2,4);
        tab2.add(sair,1,7);

        HBox hb = new HBox(10);
        hb.setAlignment(Pos.CENTER);
        hb.setPadding(new Insets(25, 0, 25, 25));
        hb.getChildren().addAll(tab, tab2);
        
        Scene scene = new Scene(hb);
        primaryStage.setScene(scene);
        primaryStage.show();

        Alert msgBox = new Alert(AlertType.WARNING);
        msgBox.setHeaderText("Bem-vindo");
        msgBox.setContentText("A cerveja da cidade acabou, você é o único que possui uma heineken bem gelada " +
                "e precisa achar o celular e chamar o uber para ir pra casa antes que roubem de você. Boa Sorte!\n" +
                "Os zumbies da paz não irão lhe seguir, mas é melhor fugir dos Zumbis bêbados.\n" +
                "Você tem um corote e uma glacial para distrair os zumbies.");
        msgBox.show();
    }

    public void avancaSimulacao(){
        if (roundCounter == 25) {
            this.spawnTelefone();
        }
        // Avança um passo em todos os objetoCelulas
        Celula celula = jogador.getCelula();
        for(ObjetoCelula o : objetoCelulas) {
            if (o instanceof Corote) {
                Corote c = (Corote)o;
                if (roundCounter - c.getStart() >= 5) {
                    c.desativa();
                    c.getCelula().setObjetoCelula(null);
                }
            } else if (o instanceof Zumbi) {
                if (!Zumbi.congelado()) {
                    if (o instanceof ZumbiBebado) {
                        if(roundCounter%2==0) o.atualizaPosicao(celula);
                    } else if (o instanceof ZumbiDaPaz){
                        if(roundCounter%2==0) o.atualizaPosicao();
                    }    
                }

                o.influenciaVizinhos();
            } else if(o instanceof Glacial) {
                Glacial g = (Glacial)o;
                if (roundCounter - g.getStart() >= 5) {
                    g.desativa();
                    g.getCelula().setObjetoCelula(null);
                }
            } else if (!(o instanceof Jogador)) {
                o.atualizaPosicao();
            };
            
            o.verificaEstado();
        }

        if (!this.jogador.estaVivo()){
            Alert msgBox = new Alert(AlertType.INFORMATION);
            msgBox.setHeaderText("Fim de Jogo");
            msgBox.setContentText("Você perdeu a sua cerveja gelada");
            msgBox.show();
        } else if(this.jogador.ganhou()) {
            Alert msgBox = new Alert(AlertType.INFORMATION);
            msgBox.setHeaderText("Fim de Jogo");
            msgBox.setContentText("Você chamou o Uber pra ir pra casa");
            msgBox.show();
        }

        roundCounter ++;

        if (roundCounter % SPAWN_RATIO == 0) {
            spawnZumbiBebado();
        }
    }

    public void spawnZumbiBebado() {
        boolean posOk = false;

        posOk = false;
        while(!posOk){
            int lin = random.nextInt(3) + 12;
            int col = random.nextInt(NCOL);
            if (this.getCelula(lin, col).getObjetoCelula() == null){
                objetoCelulas.add(new ZumbiBebado(lin,col));
                posOk = true;
            }
        }
    }

    private void usaItem(int cod) {
        Item i = this.jogador.usaItem(cod);

        if(i != null) {
            if(cod == 1) {
                boolean posOk = false;
                while(!posOk){
                    int lin = random.nextInt(NLIN);
                    int col = random.nextInt(NCOL);
                    if (this.getCelula(lin, col).getObjetoCelula() == null){
                        i.setCelula(this.getCelula(lin,col));
                        this.getCelula(lin,col).setObjetoCelula(i);
                        objetoCelulas.add(i);

                        i.setStart(roundCounter);
                        posOk = true;
                    }
                }
            } else if (cod == 2) {
                boolean posOk = false;
                while(!posOk){
                    int lin = random.nextInt(NLIN);
                    int col = random.nextInt(NCOL);
                    if (this.getCelula(lin, col).getObjetoCelula() == null){
                        i.setCelula(this.getCelula(lin,col));
                        this.getCelula(lin,col).setObjetoCelula(i);
                        i.ativa();

                        objetoCelulas.add(i);

                        i.setStart(roundCounter);
                        posOk = true;
                    }
                }
            }
        } 
    }

    public void spawnTelefone() {
        Telefone t = null;
        boolean posOk = false;
        while(!posOk){
            int lin = random.nextInt(NLIN);
            int col = random.nextInt(NCOL);
            if (this.getCelula(lin, col).getObjetoCelula() == null){
                t = new Telefone(lin, col, jogador);
                objetoCelulas.add(t);
                posOk = true;
            }
        }
    }

    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
