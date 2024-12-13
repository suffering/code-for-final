package unit13.hangman;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import unit13.backtracker.Backtracker;
import unit13.backtracker.Configuration;
import unit13.hangman.Hangman.Status;

/**
 * A brute force solution to guessing a hangman puzzle.
 */
public class HangmanConfiguration implements Configuration<HangmanConfiguration> {

    private final Hangman hangman;

    public HangmanConfiguration(Hangman hangman) {
        this.hangman = hangman;
    }

    @Override
    public Collection<HangmanConfiguration> getSuccessors() {
        List<HangmanConfiguration> successors = new ArrayList<>();
        for(char c='a'; c<='z'; c++) {
            Hangman copy = new Hangman(hangman);
            if(copy.guess(c)) {
                HangmanConfiguration successor = new HangmanConfiguration(copy);
                successors.add(successor);
            }
        }
        return successors;
    }

    @Override
    public boolean isValid() {
        return hangman.getStatus() != Status.LOST;
    }

    @Override
    public boolean isGoal() {
        return hangman.getStatus() == Status.WON;
    }

    @Override
    public String toString() {
        return hangman.getGuesses() + ", " + hangman.revealed();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a secret phrase: ");
        String secret = scanner.nextLine();
        scanner.close();

        Backtracker<HangmanConfiguration> backtracker = new Backtracker<HangmanConfiguration>(false);
        Hangman hangman = new Hangman(secret);
        HangmanConfiguration config = new HangmanConfiguration(hangman);

        HangmanConfiguration sol = backtracker.solve(config);
        if(sol != null) {
            System.out.println(sol);
        } else {
            System.out.println("No solution.");
        }
    }
}
