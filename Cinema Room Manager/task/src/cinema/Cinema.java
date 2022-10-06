package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void initCinemaHall(char[][] cinemaHall) {
        for (char[] chars : cinemaHall) {
            Arrays.fill(chars, 'S');
        }
    }

    private static void printCinemaHall(char[][] cinemaHall) {
        int totalRows = cinemaHall.length;
        int totalCols = cinemaHall[0].length;

        System.out.println("Cinema:");

        System.out.print("  ");
        for (int i = 1; i <= totalCols; i++) {
            System.out.print(i + (i == totalCols ? "\n" : " "));
        }

        for (int i = 0; i < totalRows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < totalCols; j++) {
                System.out.print(cinemaHall[i][j] +
                        (j == totalCols - 1 ? "\n" : " "));
            }
        }

    }

    public static void assignSeatNumber(char[][] cinemaHall) {

        int totalRows = cinemaHall.length;

        int totalSeatsPerRow = cinemaHall[0].length;

        int totalSeats = totalRows * totalSeatsPerRow;

        int currentPrice = 0;

        int rowNum, colNum;

        do {
            System.out.println("Enter a row number: ");
            rowNum = scanner.nextInt();

            System.out.println("Enter a seat number in that row: ");
            colNum = scanner.nextInt();

            if (rowNum < 1 || rowNum > totalRows || colNum < 1 || colNum > totalSeatsPerRow) {
                System.out.println("Wrong Input!");
            } else if (cinemaHall[rowNum - 1][colNum - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            }
        } while (rowNum < 1 || rowNum > totalRows || colNum < 1 ||
                colNum > totalSeatsPerRow ||
                cinemaHall[rowNum - 1][colNum - 1] == 'B');

        cinemaHall[rowNum - 1][colNum - 1] = 'B';
        ticketsPurchased += 1;

        if (totalSeats <= 60) {
            currentPrice = 10;
        } else {
            if (rowNum <= totalRows / 2) {
                currentPrice = 10;
            } else {
                currentPrice = 8;
            }
        }

        System.out.println("Ticket price: $" + currentPrice);
        totalTicketCost += currentPrice;

    }

    public static int totalIncome(int totalRows, int totalSeatsPerRow) {
        if (totalRows * totalSeatsPerRow <= 60) {
            return totalSeatsPerRow * totalSeatsPerRow * 10;
        } else {
            int firstHalfRows = totalRows / 2;
            int remainingRows = totalRows - firstHalfRows;
            return firstHalfRows * totalSeatsPerRow * 10 + remainingRows * totalSeatsPerRow * 8;
        }
    }

    public static void printStatistics(int totalRows, int totalSeatsPerRow) {
        System.out.println("Number of purchased tickets: " + ticketsPurchased);

        int totalSeats = totalRows * totalSeatsPerRow;

        double seatsOccupiedPercentage = ticketsPurchased * 100.0 / totalSeats;

        System.out.printf("Percentage: %.2f%%\n", seatsOccupiedPercentage);
        System.out.println("Current income: $" + totalTicketCost);
        System.out.println("Total income: $" + totalIncome(totalRows, totalSeatsPerRow));
    }

    private static final Scanner scanner = new Scanner(System.in);

    private static int ticketsPurchased = 0;
    private static int totalTicketCost = 0;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows: ");
        int totalRows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row: ");
        int totalSeatsPerRow = scanner.nextInt();

        char[][] cinemaHall = new char[totalRows][totalSeatsPerRow];

        initCinemaHall(cinemaHall);

        int userChoice;

        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    printCinemaHall(cinemaHall);
                    break;
                case 2:
                    assignSeatNumber(cinemaHall);
                    break;
                case 3:
                    printStatistics(totalRows, totalSeatsPerRow);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wrong choice.");
                    break;
            }

        } while (userChoice != 0);


    }
}