import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rayssa on 23/04/17.
 */
public class JogoMatematica {

    //final sao constantes ou seja tem valores inalteraveis ,fixos
    private static final String[] LEVEL_ONE_OPERATIONS = {"-","+"};
    private static final String[] LEVEL_TWO_OPERATIONS = {"/"};
    private static final String[] LEVEL_THREE_OPERATIONS = {"*"};
    private static final String[] LEVEL_FOUR_OPERATIONS = {"+", "/", "*", "-"};

    private int lives = 3;

    private int points = 0;

    private int currentLevel = 1;

    private int currentRound = 1;

    public Scanner scanner = new Scanner(System.in);

    public static void main (String[]args){
        while(true) {
            JogoMatematica jogo = new JogoMatematica();
            System.out.println("      Jogo de Matemática");
            System.out.println("************************************");
            System.out.println("* 1 - Novo Jogo                    *");
            System.out.println("* 2 - Sair                         *");
            System.out.println("************************************");

            int option = jogo.scanner.nextInt();
            while (option != 1 && option != 2) {
                System.out.println("Opção invalida");
                option = jogo.scanner.nextInt();
            }
            if (option == 2)
                break;


            while (jogo.getLives() > 0 && jogo.getCurrentLevel() <= 4) {
                System.out.println("Tentativas Restantes " + jogo.getLives() + " Pontuação " + jogo.getPoints());
                System.out.println("Nivel " + jogo.getCurrentLevel() + " Rodada " + jogo.getCurrentRound());
                if (jogo.generateEquation(jogo.getCurrentLevel())) {
                    jogo.addPoints();
                    jogo.addRound();
                } else {
                    jogo.loseLife();
                    if (jogo.getLives() == 0) {
                        System.out.println("Game Over!!");
                        break;
                    }
                }
            }

            if (jogo.getCurrentLevel() > 4)
                System.out.println("Parabéns você ganhou o jogo!!");
        }
    }

    public boolean generateEquation(int nivel) {
        int first = ThreadLocalRandom.current().nextInt(0, 9 );
        int second = ThreadLocalRandom.current().nextInt(0,9);
        String[] operations = {};

        switch (nivel){
            case 1:
                operations = LEVEL_ONE_OPERATIONS;
                break;
            case 2:
                operations = LEVEL_TWO_OPERATIONS;
                break;
            case 3:
                operations = LEVEL_THREE_OPERATIONS;
                break;
            case 4:
                operations = LEVEL_FOUR_OPERATIONS;
                break;

        }                   // classe estatica java que gera numeros aleatorios
        int index = ThreadLocalRandom.current().nextInt(0, operations.length);

        int result = 0;
        switch (operations[index]) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "/":
                result = first / second;
                break;
            case "*":
                result = first * second;
                break;
        }
        System.out.println(first + " " +  operations[index] + " " + second + " = ?" );
        System.out.println("Sua resposta:");
        int resultado = scanner.nextInt();

        /*Poderia ser assim
        return resultado == result;
        */
        if (resultado ==  result) return true;
        else return false;

    }

    public int getLives(){
        return lives;
    }

    public void loseLife() {
        lives--;
    }

    public void addPoints()
    {
        points += 10;
    }

    public void nextLevel()
    {
        currentLevel++;
        currentRound = 1;
    }

    public void addRound()
    {
        currentRound++;
        if (currentRound == 2)
            nextLevel();
    }

    public int getCurrentLevel()
    {
        return currentLevel;
    }

    public int getPoints()
    {
        return this.points;
    }

    public int getCurrentRound()
    {
        return this.currentRound;
    }
}
