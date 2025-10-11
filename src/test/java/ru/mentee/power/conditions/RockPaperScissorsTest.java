package ru.mentee.power.conditions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RockPaperScissorsTest {

    private RockPaperScissors game;

    private static final String ROCK = "Камень";
    private static final String PAPER = "Бумага";
    private static final String SCISSORS = "Ножницы";
    private static final String PLAYER_WINS = "Победа игрока";
    private static final String COMPUTER_WINS = "Победа компьютера";
    private static final String DRAW = "Ничья";
    private static final String ERROR = "Ошибка";

    @BeforeEach
    void setUp() {
        game = new RockPaperScissors();
    }

    @Test
    @DisplayName("Камень побеждает ножницы")
    void rockBeatsScissors() {
        String playerChoice = ROCK;
        String computerChoice = SCISSORS;
        String result = game.determineWinner(playerChoice, computerChoice);
        assertThat(result).isEqualTo(PLAYER_WINS);
    }

    @Test
    @DisplayName("Ножницы побеждают бумагу")
    void scissorsBeatsPaper() {
        String playerChoice = SCISSORS;
        String computerChoice = PAPER;
        String result = game.determineWinner(playerChoice, computerChoice);
        assertThat(result).isEqualTo(PLAYER_WINS);
    }

    @Test
    @DisplayName("Бумага побеждает камень")
    void paperBeatsRock() {
        String playerChoice = PAPER;
        String computerChoice = ROCK;
        String result = game.determineWinner(playerChoice, computerChoice);
        assertThat(result).isEqualTo(PLAYER_WINS);
    }

    @Test
    @DisplayName("Ничья при одинаковом выборе (Камень)")
    void drawWhenSameChoiceRock() {
        String playerChoice = ROCK;
        String computerChoice = ROCK;
        String result = game.determineWinner(playerChoice, computerChoice);
        assertThat(result).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("Ничья при одинаковом выборе (Бумага)")
    void drawWhenSameChoicePaper() {
        String playerChoice = PAPER;
        String computerChoice = PAPER;
        String result = game.determineWinner(playerChoice, computerChoice);
        assertThat(result).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("Ничья при одинаковом выборе (Ножницы)")
    void drawWhenSameChoiceScissors() {
        String playerChoice = SCISSORS;
        String computerChoice = SCISSORS;
        String result = game.determineWinner(playerChoice, computerChoice);
        assertThat(result).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("Обработка некорректного выбора игрока")
    void handleInvalidPlayerChoice() {
        String playerChoice = "Колодец";
        String computerChoice = ROCK;
        String result = game.determineWinner(playerChoice, computerChoice);
        assertThat(result).isEqualTo(ERROR);
    }

    @Test
    @DisplayName("Обработка некорректного выбора компьютера")
    void handleInvalidComputerChoice() {
        String playerChoice = ROCK;
        String computerChoice = "Огонь";
        String result = game.determineWinner(playerChoice, computerChoice);
        assertThat(result).isEqualTo(ERROR);
    }

    @Test
    @DisplayName("Обработка некорректного выбора у обоих")
    void handleInvalidBothChoices() {
        String playerChoice = "Вода";
        String computerChoice = "Воздух";
        String result = game.determineWinner(playerChoice, computerChoice);
        assertThat(result).isEqualTo(ERROR);
    }

    @RepeatedTest(10)
    @DisplayName("Генерация случайного выбора компьютера")
    void generateComputerChoiceReturnsValidOption() {
        String choice = game.generateComputerMove();
        assertThat(choice).isIn(ROCK, PAPER, SCISSORS);
    }

    @ParameterizedTest
    @CsvSource({
            "Камень, Ножницы, Победа игрока",
            "Ножницы, Камень, Победа компьютера",
            "Бумага, Бумага, Ничья",
            "Ножницы, Бумага, Победа игрока",
            "Камень, Бумага, Победа компьютера",
            "Бумага, Ножницы, Победа компьютера"
    })
    @DisplayName("Различные комбинации выборов для determineWinner")
    void testVariousChoiceCombinationsDetermineWinner(String playerChoice, String computerChoice, String expectedResult) {
        String result = game.determineWinner(playerChoice, computerChoice);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Тестирование метода playGame (валидный выбор)")
    void testPlayGame_ValidChoice() {
        String playerChoice = ROCK;
        String result = game.playGame(playerChoice);
        assertThat(result).contains("Компьютер выбрал:");
        assertThat(result).containsAnyOf(PLAYER_WINS, COMPUTER_WINS, DRAW);
    }

    @Test
    @DisplayName("Тестирование метода playGame с неверным выбором")
    void testPlayGame_InvalidChoice() {
        String playerChoice = "Ящерица";
        String result = game.playGame(playerChoice);
        assertThat(result).isEqualTo(ERROR);
    }
}