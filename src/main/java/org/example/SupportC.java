package org.example;
import java.util.Scanner;

public class SupportC {




    public static double isSumm() throws NumberFormatException {
        String summ = inLine();
        while (true)
        {
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

    public static boolean isDouble(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

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

    public static String inLine(){

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            return sc.nextLine();
        }
        return "111111111111111111111111111111111";
    }






}
