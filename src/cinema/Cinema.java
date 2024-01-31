package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private final char[][] cinema;
    private final int numberOfRows;
    private final int numberOfSeats;

    private int income;
    private int count;
    private double percentage;

    public Cinema(int numberOfRows, int numberOfSeats) {
        this.numberOfRows = numberOfRows;
        this.numberOfSeats = numberOfSeats;
        cinema = new char[numberOfRows][numberOfSeats];
        this.percentage = income = count = 0;
        fillTheSeats();
    }

    public void startMenu(Scanner scan) {
        if (isValid()) {
            while (true) {
                printMenu();
                int option = scan.nextInt();

                switch (option) {
                    case 1:
                        showSeats();
                        break;
                    case 2:
                        buyTicket(scan);
                        break;
                    case 3:
                        printStatistic();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        }
    }

    private void printMenu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.print("0. Exit\n> ");
    }

    private void showSeats() {
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 1; i <= numberOfSeats; i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 0; i < cinema.length; i++) {
            System.out.print((i + 1) + "");
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(" " + cinema[i][j]);
            }
            System.out.println();
        }

        System.out.println();
    }

    private void buyTicket(Scanner scan) {
        while (true) {
            System.out.print("\nEnter a row number:\n> ");
            int rowNumber = scan.nextInt();

            System.out.print("Enter a seat number in that row:\n> ");
            int seatNumber = scan.nextInt();

            if (isValidSeat(rowNumber, seatNumber)) {
                if (cinema[rowNumber - 1][seatNumber - 1] == 'B') {
                    System.out.println("\nThat ticket has already been purchased! Please choose another seat.");
                } else {
                    int ticketPrice = calculateTicketPrice(rowNumber);
                    System.out.println("\nTicket price: $" + ticketPrice);

                    cinema[rowNumber - 1][seatNumber - 1] = 'B';
                    updateStatistics(ticketPrice);
                    break;  // Break out of the loop if the ticket is successfully purchased
                }
            } else {
                System.out.println("\nWrong input! Please enter valid seat coordinates.");
            }
        }
    }

    private void updateStatistics(int ticketPrice) {
        income += ticketPrice;
        count++;
        percentage = (count * 1.0 / (numberOfRows * numberOfSeats)) * 100;
    }

    private int calculateTicketPrice(int rowNumber) {
        int calculatedTicket = rowNumber <= (numberOfRows /2 ) ? 10 : 8;
        return numberOfRows * numberOfSeats < 60 ? 10 : calculatedTicket;
    }

    private void fillTheSeats() {
        for (char[] row : cinema) {
            Arrays.fill(row, 'S');
        }
    }

    private void printStatistic() {
        int maxValue = Math.max(numberOfRows, numberOfSeats);
        int totalNumberOfSeats = numberOfRows * numberOfSeats;
        int totalIncome = (totalNumberOfSeats > 60 ? maxValue * 8 : totalNumberOfSeats) * 10;

        System.out.println("\nNumber of purchased tickets: " + count);
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + totalIncome + "\n");
    }

    private boolean isValid() {
        return numberOfRows > 0 && numberOfRows <= 9 && numberOfSeats > 0 && numberOfSeats <= 9;
    }

    private boolean isValidSeat(int rowNumber, int seatNumber) {
        return rowNumber > 0 && rowNumber <= numberOfRows && seatNumber > 0 && seatNumber <= numberOfSeats;
    }
}
