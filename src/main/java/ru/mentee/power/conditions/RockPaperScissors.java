package ru.mentee.power.conditions;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static final String ROCK = "Камень";
    public static final String PAPER = "Бумага";
    public static final String SCISSORS = "Ножницы";

    public static final String PLAYER_WINS = "Победа игрока";
    public static final String COMPUTER_WINS = "Победа компьютера";
    public static final String DRAW = "Ничья";
    public static final String ERROR = "Ошибка";

    private static final List<String> VALID_MOVES = Arrays.asList(ROCK, PAPER, SCISSORS);

    private Random random = new Random();

    /**
     * Определяет исход игры на основе ходов игрока и компьютера
     */
    public String determineWinner(String playerMove, String computerMove) {
        if (!validateMove(playerMove) || !validateMove(computerMove)) {
            return ERROR;
        }
        if (playerMove.equals(computerMove)) {
            return DRAW;
        }
        switch (playerMove) {
            case ROCK:
                return computerMove.equals(SCISSORS) ? PLAYER_WINS : COMPUTER_WINS;
            case SCISSORS:
                return computerMove.equals(PAPER) ? PLAYER_WINS : COMPUTER_WINS;
            case PAPER:
                return computerMove.equals(ROCK) ? PLAYER_WINS : COMPUTER_WINS;
            default:
                return ERROR;
        }
    }

    /**
     * Проверяет, является ли ход допустимым
     */
    private boolean validateMove(String move) {
        return VALID_MOVES.contains(move);
    }

    /**
     * Генерирует случайный ход компьютера
     */
    public String generateComputerMove() {
        int idx = random.nextInt(VALID_MOVES.size());
        return VALID_MOVES.get(idx);
    }

    /**
     * Запускает одну игровую сессию, возвращает результат как строку
     * (для теста нужен вариант, который не использует Scanner)
     */
    public String playGame(String playerChoice) {
        if (!validateMove(playerChoice)) {
            return "Ошибка";
        }
        String computerChoice = generateComputerMove();
        String result = determineWinner(playerChoice, computerChoice);
        return String.format("Компьютер выбрал: %s. Результат: %s", computerChoice, result);
    }

    /**
     * Запускает одну игровую сессию через консоль
     */
    public void playOneGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите ход (Камень, Ножницы, Бумага):");
        String playerChoice = scanner.nextLine();
        String computerChoice = generateComputerMove();
        String result = determineWinner(playerChoice, computerChoice);
        System.out.printf("Компьютер выбрал: %s. Результат: %s%n", computerChoice, result);
    }

    public void startGameLoop() {
        Scanner scanner = new Scanner(System.in);
        int games = 0, playerWins = 0, computerWins = 0, draws = 0;
        System.out.println("Добро пожаловать в игру Камень-Ножницы-Бумага!");
        System.out.println("Выберите ход: Камень, Ножницы или Бумага.");
        while (true) {
            games++;
            System.out.print("Ваш ход: ");
            String playerChoice = scanner.nextLine();
            if (!validateMove(playerChoice)) {
                System.out.println("Ошибка: недопустимый ход!");
                games--;
                continue;
            }
            String computerChoice = generateComputerMove();
            String result = determineWinner(playerChoice, computerChoice);
            System.out.printf("Компьютер выбрал: %s. Результат: %s%n", computerChoice, result);
            switch (result) {
                case PLAYER_WINS: playerWins++; break;
                case COMPUTER_WINS: computerWins++; break;
                case DRAW: draws++; break;
            }
            System.out.print("Сыграть еще? (да/нет): ");
            String answer = scanner.nextLine();
            if (answer.trim().equalsIgnoreCase("нет")) break;
        }
        System.out.printf("Статистика: игр %d, побед игрока %d, побед компьютера %d, ничьих %d%n",
                games, playerWins, computerWins, draws);
        System.out.println("Спасибо за игру!");
    }

    public static void main(String[] args) {
        RockPaperScissors game = new RockPaperScissors();
        game.startGameLoop();
    }
}