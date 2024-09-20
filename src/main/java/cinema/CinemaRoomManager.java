package cinema;

import java.util.Scanner;

public class CinemaRoomManager {
    // Class-level static variable
    private static String[][] boughtSeatsArray;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int rows;
        int seats;
        int ticketsBought = 0;
        int previousBought = 0;
        int currentIncome = 0;
        int totalIncome = 0;
        int totalSeats = 0;

        // Get user input for rows and seats.
        System.out.print("Enter the number of rows: ");
        rows = input.nextInt();
        System.out.print("Enter the number of seats in each row: ");
        seats = input.nextInt();
        totalSeats = rows * seats;

        // Initialize 2D array size from user input.
        boughtSeatsArray = new String[rows][seats];

        // Call method to set all seats to "S".
        initializeSeats(rows, seats);

        // Main loop begins displaying the Cinema menu.
        int item;
        do {
            System.out.println("\nCinema Manager Menu:");
            System.out.println();
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            item = input.nextInt();

            switch (item) {
                case 1:
                    printSeats(rows, seats);
                    System.out.println();
                    break;
                case 2:
                    while (ticketsBought == previousBought) {
                        System.out.print("Enter a row number: ");
                        int rowNumber = input.nextInt();
                        System.out.print("Enter a seat number in that row: ");
                        int seatNumber = input.nextInt();
                        ticketsBought = buyTicket(rowNumber, seatNumber, ticketsBought, currentIncome);
                        currentIncome = determineTicketPrice(rowNumber, currentIncome, ticketsBought, previousBought);
                    }
                    previousBought = ticketsBought;
                    System.out.println();
                    break;
                case 3:
                    statistics(ticketsBought, currentIncome, totalIncome, totalSeats, seats, rows);
                case 0:
                    break;
            }
        } while (item != 0);

        input.close();
    }

    // A method that sets all seats (elements) to "S" in the boughtSeatsArray at the start.
    private static void initializeSeats(int rows, int seats) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                boughtSeatsArray[i][j] = "S";
            }
        }
    }

    // A method that sets all bought seats (elements) in the boughtSeatsArray to "B".
    private static int buyTicket(int rowNumber, int seatNumber, int ticketsBought, int currentIncome) {
        if (rowNumber < 1 || rowNumber > boughtSeatsArray.length ||
            seatNumber < 1 || seatNumber > boughtSeatsArray[0].length) {
            System.out.println("Wrong input!");
            return ticketsBought;
        }

        // Adjust to 0-based indexing
        int actualRow = rowNumber - 1;
        int actualSeat = seatNumber - 1;

        if (boughtSeatsArray[actualRow][actualSeat].equals("B")) {
            System.out.println("That ticket has already been purchased!");
            return ticketsBought;
        } else {
            boughtSeatsArray[actualRow][actualSeat] = "B";
            ticketsBought += 1;
            return ticketsBought;
        }
    }

    // A method that uses the boughtSeatsArray to display the current state of the cinema.
    public static void printSeats(int rows, int seats) {
        System.out.println("\nCinema:");
        System.out.print("  ");
        // Prints a top row identifying seat numbers.
        for (int i = 1; i <= seats; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(boughtSeatsArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    // A method that determines the price of tickets.
    public static int determineTicketPrice(int rowNumber, int currentIncome, int ticketsBought, int previousBought) {
        if (ticketsBought == previousBought) {
            return currentIncome;
        } else {
            if (rowNumber <= 4) {
                System.out.println("Ticket price: $10");
                currentIncome += 10;
            } else {
                System.out.println("Ticket price: $8");
                currentIncome += 8;
            }
            return currentIncome;
        }
    }

    // A method that provides statistics.
    public static void statistics(int ticketsBought, int currentIncome, int totalIncome, int totalSeats, int seats, int rows) {

        System.out.println("Number of purchased tickets: " + ticketsBought);
        float percentage = (float) ticketsBought / totalSeats * 100;
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        // seats * 4 - totalSeats = leftover seats

        if (rows <= 4) {
            totalIncome = totalSeats * 10;
            System.out.println("Total income: $" + totalIncome);
        } else {
            int backRow = (totalSeats) - (seats * 4) ;
            backRow = backRow * 8;
            int frontRow = seats * 4;
            frontRow = frontRow * 10;
            totalIncome = backRow + frontRow;
            System.out.println("Total income: $" + totalIncome);
        }
    }
}