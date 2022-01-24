package com.company;


import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    static void drawField(int battleField[][], boolean playingGame) {
        for (int i = 0; i < battleField.length; i++) {
            System.out.print("\t" + i);
            if (i == battleField.length - 1) {
                System.out.print("\n");
            }
        }
        for (int i = 0; i < battleField.length; i++) {
            for (int j = 0; j < battleField.length; j++) {
                if (battleField[i][j] == 0 && !playingGame) {
                    if (j == 0) {
                        System.out.print(i + "\t-\t");
                    } else {
                        System.out.print("-\t");
                    }
                } else if (battleField[i][j] == 1 && !playingGame) {
                    if (j == 0) {
                        System.out.print(i + "\t\uD83D\uDEA2\t");
                    } else {
                        System.out.print("\uD83D\uDEA2\t");
                    }
                } else if (battleField[i][j] == 0 || battleField[i][j] == 1 && playingGame) {
                    if (j == 0) {
                        System.out.print(i + "\t-\t");
                    } else {
                        System.out.print("-\t");
                    }
                } else if (battleField[i][j] == 2 && playingGame) {
                    if (j == 0) {
                        System.out.print(i + "\t\uD83D\uDC80\t");
                    } else {
                        System.out.print("\uD83D\uDC80\t");
                    }
                } else if (battleField[i][j] == 3 && playingGame) {
                    if (j == 0) {
                        System.out.print(i + "\t✘\t");
                    } else {
                        System.out.print("✘\t");
                    }
                }
                if (j == battleField.length - 1) {
                    System.out.print("\n");
                }
            }
        }
    }

    static void clearConsole() {
        for (int i = 0; i <= 40; i++) {
            System.out.println();
        }
    }

    static boolean insertDataToArray(int battleField[][], int directionShip, int OXDot, int OYDot, int numberOfDecks) {
        if (OXDot > 9 || OXDot < 0 || OYDot > 9 || OYDot < 0) {
            return false;
        } else {
            switch (directionShip) {
                case 1:
                    if (OYDot + 1 - numberOfDecks < 0) {
                        return false;
                    }
                    break;
                case 2:
                    if (OYDot - 1 + numberOfDecks > 9) {
                        return false;
                    }
                    break;
                case 3:
                    if (OXDot + 1 - numberOfDecks < 0) {
                        return false;
                    }
                    break;
                case 4:
                    if (OXDot - 1 + numberOfDecks > 9) {
                        return false;
                    }
                    break;
            }
        }
        boolean checkCell = true;
        switch (directionShip) {
            case 1:
                for (int k = 0; k < 2; k++) {
                    for (int i = 0; i < numberOfDecks; i++) {
                        if (checkCell) {
                            if (battleField[OYDot - i][OXDot] != 0) {
                                return false;
                            }
                        } else {
                            battleField[OYDot - i][OXDot] = 1;
                        }
                    }
                    checkCell = false;
                }
                break;
            case 2:
                for (int k = 0; k < 2; k++) {
                    for (int i = 0; i < numberOfDecks; i++) {
                        if (checkCell) {
                            if (battleField[OYDot + i][OXDot] != 0) {
                                return false;
                            }
                        } else {
                            battleField[OYDot + i][OXDot] = 1;
                        }
                    }
                    checkCell = false;
                }
                break;
            case 3:
                for (int k = 0; k < 2; k++) {
                    for (int i = 0; i < numberOfDecks; i++) {
                        if (checkCell) {
                            if (battleField[OYDot][OXDot - i] != 0) {
                                return false;
                            }
                        } else {
                            battleField[OYDot][OXDot - i] = 1;
                        }
                    }
                    checkCell = false;
                }
                break;
            case 4:
                for (int k = 0; k < 2; k++) {
                    for (int i = 0; i < numberOfDecks; i++) {
                        if (checkCell) {
                            if (battleField[OYDot][OXDot + i] != 0) {
                                return false;
                            }
                        } else {
                            battleField[OYDot][OXDot + i] = 1;
                        }
                    }
                    checkCell = false;
                }
                break;
        }
        return true;
    }

    static void placementShips(String playerName, int battleField[][]) {
        int counter = 0;
        int xCoordinate = 0;
        int yCoordinate = 0;
        int shipDirection = 0;
        System.out.println();
        System.out.println(playerName + ", place your ships\n");
        while (counter < 4) {
            drawField(battleField, false);
            System.out.print("\nPlace - " + (4 - counter) + " DECKS SHIP -, input OX coordinate: ");
            xCoordinate = scan.nextInt();
            System.out.print("Input OY coordinate: ");
            yCoordinate = scan.nextInt();
            System.out.println("1 -  ↑  |  2 - ↓  |  3 - ←  |  4 - →");
            System.out.print("Input direction: ");
            shipDirection = scan.nextInt();
            if (insertDataToArray(battleField, shipDirection, xCoordinate, yCoordinate, 4 - counter)) {
                counter++;
            } else {
                System.out.println("\nError, incorrect data, try again!\n");
            }
        }
        clearConsole();
    }

    static int coinToss(String firstPlayerName, String secondPlayerName) {
        System.out.println("Coin will decide who will walk first!!!");
        System.out.println("1 - heads , 2 - tail");
        System.out.print(firstPlayerName + ", choose the side of the coin: ");
        int sideOfTheCoin = scan.nextInt();
        boolean firstPlayer, secondPlayer;
        if (sideOfTheCoin == 1) {
            System.out.println(firstPlayerName + " - heads");
            System.out.println(secondPlayerName + " - tail");
        } else {
            System.out.println(firstPlayerName + " - tail");
            System.out.println(secondPlayerName + " - heads");
        }
        int valueCoin = (int) Math.round(Math.random());
        if (valueCoin == 1) {
            System.out.println("And...coins shows heads");
            if (sideOfTheCoin == 1) {
                System.out.println(firstPlayerName+", will walk first");
                firstPlayer = true;
                secondPlayer = false;
                return 1;
            } else {
                System.out.println(secondPlayerName+", will walk first");
                firstPlayer = false;
                secondPlayer = true;
                return 2;
            }
        } else {
            System.out.print("And...coins shows tail");
            if (sideOfTheCoin == 1) {
                System.out.println(secondPlayerName+", will walk first");
                firstPlayer = false;
                secondPlayer = true;
                return 2;
            } else {
                System.out.println(firstPlayerName+", will walk first");
                firstPlayer = true;
                secondPlayer = false;
                return 1;
            }
        }
    }

    static boolean checkTheAttack(int battleField[][], int i, int j) {
        if (i < 0 || i > 9 || j < 0 || j > 9) {
            return false;
        }
        if (battleField[j][i] == 1) {
            battleField[j][i] = 2;
            return true;
        } else {
            battleField[j][i] = 3;
            return false;
        }
    }

    static void logicOfTheGame(int playerOneField[][], int playerTwoField[][], String playerOneName, String playerTwoName,
                               boolean playerOneMove, boolean playerTwoMove) {
        boolean isGameOver = false;
        int OX = 0;
        int OY = 0;
        Integer destroyedShipsFirst = 0;
        Integer destroyedShipsSecond = 0;
        clearConsole();
        while (isGameOver == false) {
            if (playerOneMove) {
                drawField(playerTwoField, true);
                System.out.println();
                System.out.println(playerTwoName + ", you walk, choose a cage to attack  ");
                System.out.print("OX coordinate: ");
                OX = scan.nextInt();
                System.out.print("OY coordinate: ");
                OY = scan.nextInt();
                if (checkTheAttack(playerTwoField, OX, OY)) {
                    drawField(playerTwoField, true);
                    destroyedShipsFirst++;
                    if (destroyedShipsFirst == 10) {
                        System.out.println("Congratulations," + playerOneName + ", you won, all enemies ships are destroyed!!!");
                        isGameOver = true;
                    }
                    clearConsole();
                    System.out.println("Great, you got it!\n");
                } else {
                    clearConsole();
                    System.out.println("Oops, " + playerOneName + ", you missed\n");
                    playerOneMove = false;
                    playerTwoMove = true;
                }
            } else {
                drawField(playerOneField, true);
                System.out.println();
                System.out.println(playerTwoName + ", you walk, choose a cage to attack  ");
                System.out.print("OX coordinate: ");
                OX = scan.nextInt();
                System.out.print("OY coordinate: ");
                OY = scan.nextInt();
                if (checkTheAttack(playerOneField, OX, OY)) {
                    destroyedShipsSecond++;
                    drawField(playerOneField, true);
                    if (destroyedShipsSecond == 10) {
                        System.out.println("Congratulations," + playerTwoName + ", you won, all enemies ships are destroyed!!!");
                        isGameOver = true;
                    }
                    clearConsole();
                    System.out.println("Great, you got it!\n");
                } else {
                    clearConsole();
                    System.out.println("Oops, " + playerTwoName + ", you missed\n");
                    playerOneMove = true;
                    playerTwoMove = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        int firstPlayerField[][] = new int[10][10];
        int secondPlayerField[][] = new int[10][10];
        boolean firstPlayerMove = false;
        boolean secondPlayerMove = false;
        String firstPlayer = "";
        String secondPlayer = "";
        System.out.print("First player enter your name: ");
        firstPlayer = scan.next();
        System.out.print("Second player enter your name: ");
        secondPlayer = scan.next();
        placementShips(firstPlayer, firstPlayerField);
        placementShips(secondPlayer, secondPlayerField);
        if (coinToss(firstPlayer, secondPlayer) == 1) {
            firstPlayerMove = true;
        } else {
            secondPlayerMove = true;
        }
        System.out.print("Press any button to start game ");
        String startGame = scan.next();
        logicOfTheGame(firstPlayerField, secondPlayerField, firstPlayer, secondPlayer, firstPlayerMove, secondPlayerMove);
    }
}
