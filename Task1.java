import java.util.*;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int totalScore = 0;
        int wins = 0;
        int totalRounds = 0;

        System.out.println("--- Number Guessing Game ---");

        while (true) {
            totalRounds++;
            int target = rand.nextInt(100) + 1;
            int tries = 10;
            boolean found = false;

            System.out.println("\nRound " + totalRounds + ": Guess a number (1-100)");
            System.out.println("Attempts allowed: " + tries);

            while (tries > 0) {
                System.out.print("Your guess: ");

                String input = sc.next();
                int guess;
                try {
                    guess = Integer.parseInt(input);
                } catch (Exception e) {
                    System.out.println("Invalid input. Enter a number!");
                    continue;
                }

                tries--;

                if (guess == target) {
                    System.out.println("Correct! The number was " + target);
                    wins++;
                    int roundPoints = (tries + 1) * 10;
                    totalScore += roundPoints;

                    showProgress(tries, roundPoints);
                    found = true;
                    break;
                } else if (guess < target) {
                    System.out.println("Too low. (" + tries + " left)");
                } else {
                    System.out.println("Too high. (" + tries + " left)");
                }
            }

            if (!found) {
                System.out.println("Out of tries! The number was " + target);
            }

            System.out.println("\nCurrent Stats - Wins: " + wins + "/" + totalRounds + " | Total Score: " + totalScore);

            System.out.print("Keep playing? (y/n): ");
            String prompt = sc.next().toLowerCase();
            if (!prompt.startsWith("y"))
                break;
        }

        System.out.println("\n--- FINAL RESULTS ---");
        System.out.println("Rounds Played: " + totalRounds);
        System.out.println("Rounds Won:    " + wins);
        System.out.println("Final Score:   " + totalScore);
        System.out.println("Thanks for playing!");

        sc.close();
    }

    private static void showProgress(int left, int pts) {
        System.out.println("You earned " + pts + " points this round.");
        if (left > 7)
            System.out.println("Impressive speed!");
    }
}
