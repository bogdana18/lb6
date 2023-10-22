# lb6 додаток для кінотеатру

# Cinema Class

## Опис
Клас `Cinema` представляє систему бронювання місць у кінотеатрі. Він містить методи для ініціалізації місць, бронювання та скасування бронювання, перевірки наявності вільних місць, друку схеми розміщення місць, пошуку найкращих доступних послідовних місць, перевірки, чи всі місця в залі заброньовані, і виводить список всіх заброньованих місць.
Нижче наведена реалізація деяких методів:

## Ініціалізація масиву
```java
public Cinema() {
    // Ініціалізація масиву місць
    seats = new int[SCREEN_COUNT][ROW_COUNT][SEAT_COUNT];
    for (int screen = 0; screen < SCREEN_COUNT; screen++) {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int seat = 0; seat < SEAT_COUNT; seat++) {
                seats[screen][row][seat] = 0;
            }
        }
    }
}
```

## Бронювання місць

```java
public void bookSeats(int screen, int row, int[] seats) {
    // Перевірка наявності місць
    for (int seat : seats) {
        if (this.seats[screen][row][seat] == 1) {
            System.out.println("Ці місця вже заброньовані.");
            return;
        }
    }
    // Бронювання місць
    for (int seat : seats) {
        this.seats[screen][row][seat] = 1;
    }
}
```

## Скасування бронювання

```java
public void cancelBooking(int screen, int row, int[] seats) {
    for (int seat : seats) {
        this.seats[screen][row][seat] = 0;
    }
}
```

## Перевірка наявності

```java
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
```

## Друк схеми розміщення місць
```java
public void printSeatingArrangement(int screen) {
        System.out.println("Розташування місць в залі " + screen + ":");
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int seat = 0; seat < SEAT_COUNT; seat++) {
                System.out.print(seats[screen][row][seat] + " ");
            }
            System.out.println();
        }
    }
```

## Отримання кількості вільних місць в залі

```java
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
```
