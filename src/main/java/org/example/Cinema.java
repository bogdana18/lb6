package org.example;

public class Cinema {

    private static final int SCREEN_COUNT = 5;
    private static final int ROW_COUNT = 10;
    private static final int SEAT_COUNT = 20;

    private int[][][] seats;

    public Cinema() {
        seats = new int[SCREEN_COUNT][ROW_COUNT][SEAT_COUNT];
        for (int screen = 0; screen < SCREEN_COUNT; screen++) {
            for (int row = 0; row < ROW_COUNT; row++) {
                for (int seat = 0; seat < SEAT_COUNT; seat++) {
                    seats[screen][row][seat] = 0;
                }
            }
        }
    }

    // Ініціалізація масиву

    public void initializeSeats() {
        for (int screen = 0; screen < SCREEN_COUNT; screen++) {
            for (int row = 0; row < ROW_COUNT; row++) {
                for (int seat = 0; seat < SEAT_COUNT; seat++) {
                    seats[screen][row][seat] = 0;
                }
            }
        }
    }

    // Бронювання місць

    public void bookSeats(int screen, int row, int[] seats) {
        for (int seat : seats) {
            if (this.seats[screen][row][seat] == 1) {
                System.out.println("Ці місця вже заброньовані.");
                return;
            }
        }

        for (int seat : seats) {
            this.seats[screen][row][seat] = 1;
        }
    }

    // Скасування бронювання

    public void cancelBooking(int screen, int row, int[] seats) {
        for (int seat : seats) {
            this.seats[screen][row][seat] = 0;
        }
    }

    // Перевірка наявності

    public boolean checkAvailability(int screen, int numSeats) {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int start = 0; start < SEAT_COUNT - numSeats + 1; start++) {
                boolean available = true;
                for (int seat = 0; seat < numSeats; seat++) {
                    if (this.seats[screen][row][start + seat] == 1) {
                        available = false;
                        break;
                    }
                }

                if (available) {
                    return true;
                }
            }
        }

        return false;
    }

// Друк схеми розміщення місць

    public void printSeatingArrangement(int screen) {
        System.out.println("Розташування місць в залі " + screen + ":");
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int seat = 0; seat < SEAT_COUNT; seat++) {
                System.out.print(seats[screen][row][seat] + " ");
            }
            System.out.println();
        }
    }

// Знаходить та повертає найкращі доступні послідовні місця для даної кількості місць в зазначеному залі

    public int[] findBestAvailable(int screen, int numSeats) {
        int[] bestSeats = new int[numSeats];
        int bestDistance = Integer.MAX_VALUE;

        for (int row = 0; row < ROW_COUNT; row++) {
            for (int start = 0; start < SEAT_COUNT - numSeats + 1; start++) {
                int[] seats = new int[numSeats];
                int distance = 0;
                for (int seat = 0; seat < numSeats; seat++) {
                    seats[seat] = start + seat;
                    distance = Math.max(distance, seats[seat] - seats[0]);
                }

                if (distance < bestDistance) {
                    bestSeats = seats;
                    bestDistance = distance;
                }
            }
        }

        return bestSeats;
    }

// Додаткові методи

// Перевіряє, чи всі місця в залі заброньовані

    public boolean isAllSeatsBooked(int screen) {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int seat = 0; seat < SEAT_COUNT; seat++) {
                if (seats[screen][row][seat] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

// Виводить список всіх заброньованих місць

    public void printBookedSeats(int screen) {
        System.out.println("Заброньовані місця в залі " + screen + ":");
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int seat = 0; seat < SEAT_COUNT; seat++) {
                if (seats[screen][row][seat] == 1) {
                    System.out.print("(" + row + ", " + seat + ") ");
                }
            }
        }

        System.out.println();
    }

// Отримати кількість вільних місць в залі

    public int getFreeSeatsCount(int screen) {
        int count = 0;
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int seat = 0; seat < SEAT_COUNT; seat++) {
                if (seats[screen][row][seat] == 0) {
                    count++;
                }
            }
        }

        return count;
    }
}
