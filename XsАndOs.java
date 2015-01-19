package flairgc.xsandos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class XsАndOs {

    static String[][] m = new String[3][3];
    // заполняем массив пробелами
    static {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m[i][j] = " ";
            }
        }
    }

    static int[][] winComb = {{0,0,0}, {0,0,0}, {0,0,0}};


    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int x, y;

    static int isWin = 0;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args)  throws IOException{

        int isWin2;

        System.out.println("Крестики и нолики");
        System.out.println("Что бы походить - укажите сначала нужный ряд, следом клетку в этом ряду.");

        for (int i = 1; i < 10; i++) { // главный цикл игры - не может быть больше 9 раз

            print(); // выводит текущее положение дел на игровом поле

            if (i%2 == 1) { // ход Х
                turn("x");
            } else { // ход O
                turn("o");
            }

            isWin2 = win();
            if (isWin2 != 0){
                if (isWin2 == 1){
                    print();
                    System.out.println("Победили Крестики!");
                    break;
                }
                if (isWin2 == 2) {
                    print();
                    System.out.println("Победили Нолики!");
                    break;
                }
            }
        }
    }

    public static void print(){ // вывод на экран состояние поля после хода
        System.out.println("   1   2   3");
        System.out.println("1  "+m[0][0]+" | "+m[0][1]+" | "+m[0][2]);
        System.out.println("  -----------");
        System.out.println("2  "+m[1][0]+" | "+m[1][1]+" | "+m[1][2]);
        System.out.println("  -----------");
        System.out.println("3  "+m[2][0]+" | "+m[2][1]+" | "+m[2][2]);
    }

    public static void turn(String x_o)  throws IOException{ // ход каждого игрока

        String text;
        int turnXorO; // для массива winComb
        if (x_o.equals("x")) {text = "крестики"; turnXorO = 1;}
        else {text = "нолики"; turnXorO = 2;}

        System.out.println("ходят "+text+" ("+x_o+") :");

        String sx; // получаем первое число-координату от игрока
        String sy; // получаем второе число-координату от игрока
        while (true) {
            while (true) {
                sx = reader.readLine();
                if (!sx.equals("1") && !sx.equals("2") && !sx.equals("3")) { // проверяем на корретность вводимые числа
                    System.out.print("введите число от 1 до 3: ");
                    continue;
                }
                x = Integer.parseInt(sx);
                break;
            }
            while (true) {
                sy = reader.readLine();
                if (!sy.equals("1") && !sy.equals("2") && !sy.equals("3")) { // проверяем на корретность вводимые числа
                    System.out.print("введите число от 1 до 3: ");
                    continue;
                }
                y = Integer.parseInt(sy);
                break;
            }

            if (winComb[x - 1][y - 1] != 0) {
                System.out.print("Ход в эту клетку уже был сделан, виберите другую: ");
                continue;
            }

            break;
        }

        m[x - 1][y - 1] = x_o;
        winComb[x - 1][y - 1] = turnXorO; // записывает 1 или 2 в массив winComb - который отмечает где ходы играков
    }

    public static int win(){ // проверка на победитея, происходит обычным перебором всех возможных победныъ комбинаций на
    // соответствие текушего положения пользователя

        if ((winComb[0][0] == 1 && winComb[0][1] == 1 && winComb[0][2] == 1)||
                (winComb[1][0] == 1 && winComb[1][1] == 1 && winComb[1][2] == 1)||
                (winComb[2][0] == 1 && winComb[2][1] == 1 && winComb[2][2] == 1)||
                (winComb[0][0] == 1 && winComb[1][0] == 1 && winComb[2][0] == 1)||
                (winComb[0][1] == 1 && winComb[1][1] == 1 && winComb[2][1] == 1)||
                (winComb[0][2] == 1 && winComb[1][2] == 1 && winComb[2][2] == 1)||
                (winComb[0][0] == 1 && winComb[1][1] == 1 && winComb[2][2] == 1)||
                (winComb[2][0] == 1 && winComb[1][1] == 1 && winComb[0][2] == 1))
        {
            isWin = 1;
        }
        if ((winComb[0][0] == 2 && winComb[0][1] == 2 && winComb[0][2] == 2)||
                (winComb[1][0] == 2 && winComb[1][1] == 2 && winComb[1][2] == 2)||
                (winComb[2][0] == 2 && winComb[2][1] == 2 && winComb[2][2] == 2)||
                (winComb[0][0] == 2 && winComb[1][0] == 2 && winComb[2][0] == 2)||
                (winComb[0][1] == 2 && winComb[1][1] == 2 && winComb[2][1] == 2)||
                (winComb[0][2] == 2 && winComb[1][2] == 2 && winComb[2][2] == 2)||
                (winComb[0][0] == 2 && winComb[1][1] == 2 && winComb[2][2] == 2)||
                (winComb[2][0] == 2 && winComb[1][1] == 2 && winComb[0][2] == 2))
        {
            isWin = 2;
        }
        return isWin;// победитель - 1 крестики, 2 нолики, 0 игра не окончена;
    }

}
