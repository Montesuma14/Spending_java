package org.example;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SupportC {



    // Проверка строки на число, удовлетворяющего значению суммы.
    public static double isSumm() throws NumberFormatException {
        String summ;
        while (true)
        {
            summ = inLine();
            if (isDigit(summ) || isDouble(summ))
            {
                if (isDigit(summ))
                    return Integer.parseInt(summ);
                else
                    return Double.parseDouble(summ);
            }
            else {
                System.out.println("введенное значение не явлется числом. Пожалуйста, введите повторно.");
            }
        }

    }

    //Проверка, является ли строка числом дабл
    public static boolean isDouble(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDate_ddmm(String s) throws NumberFormatException {
        if (s.length() != 5)
            return false;
        try {
            LocalDate.parse(s + " " + LocalDate.now().getYear(), DateTimeFormatter.ofPattern("dd MM uuuu"));

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isDate_ddmmuuuu(String s) throws NumberFormatException {
        if (s.length() !=10)
            return false;
        try {
            LocalDate.parse(s , DateTimeFormatter.ofPattern("dd MM uuuu"));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    //Проверка, является ли строка числом интовым
    public static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    //приём команд от пользователя
    public static byte  take_command_and_check(int commands){
        byte command;
        String input_command_string = inLine();
        while (true){
            if (isDigit(input_command_string)){
                command = Byte.parseByte(input_command_string);
                if (command >0 && command <= commands){
                    return command;
                }
                else {
                    System.out.println("Число не является командой. Допустимы номера команд от 1 до " + commands);
                    input_command_string = inLine();
                }
            }
            else {
                System.out.println("Принимаются только числовые значения команд от 1 до " + commands);
                input_command_string = inLine();
            }

        }
    }

    //Проверка, доступно ли следующая строка в потоке(?)
    public static String inLine(){

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            return sc.nextLine();
        }
        return "111111111111111111111111111111111";
    }






}
