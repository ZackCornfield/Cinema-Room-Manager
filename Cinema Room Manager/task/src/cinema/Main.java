package cinema;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the number of rows:\n> ");
        int rows = scan.nextInt();

        System.out.print("Enter the number of seats in each row:\n> ");
        int seats = scan.nextInt();

        Cinema cinema = new Cinema(rows, seats);
        cinema.startMenu(scan);
    }
}