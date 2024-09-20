package cinema;

import java.util.Scanner;

public class CinemaRoomManager {
    // Class-level static variable
    private static String[][] boughtSeatsArray;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int rows;
        int seats;

        // Get user input for rows and seats.
        System.out.print("Enter the number of rows: ");
        rows = input.nextInt();
        System.out.print("Enter the number of seats in each row: ");
        seats = input.nextInt();

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
                    System.out.print("Enter a row number: ");
                    int rowNumber = input.nextInt();
                    System.out.print("Enter a seat number in that row: ");
                    int seatNumber = input.nextInt();
                    buyTicket(rowNumber, seatNumber);
                    determineTicketPrice(rowNumber);
                    System.out.println();
                    break;
                case 3:
                    // Statistics code goes here
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
    private static void buyTicket(int rowNumber, int seatNumber) {
        boughtSeatsArray[rowNumber - 1][seatNumber - 1] = "B";
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
    public static void determineTicketPrice(int rowNumber) {
        if (rowNumber <= 4) {
            System.out.println("Ticket price: $10");
        } else {
            System.out.println("Ticket price: $8");
        }
    }
}