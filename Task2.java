import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many subjects? ");
        int n = sc.nextInt();

        double total = 0;
        for (int i = 1; i <= n; i++) {
            System.out.print("Marks for subject " + i + ": ");
            double marks = sc.nextDouble();

            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks. Try again.");
                i--;
                continue;
            }
            total += marks;
        }

        double avg = total / n;

        String grade;
        if (avg >= 90)
            grade = "A";
        else if (avg >= 80)
            grade = "B";
        else if (avg >= 70)
            grade = "C";
        else if (avg >= 60)
            grade = "D";
        else
            grade = "F";

        System.out.println("\nTotal Marks: " + total);
        System.out.println("Average %: " + avg + "%");
        System.out.println("Grade: " + grade);

        sc.close();
    }
}
