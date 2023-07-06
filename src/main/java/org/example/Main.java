package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.*;
import java.time.format.*;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) {
        //Scanner  in = new Scanner(System.in);
        //список пользователей
        Users users = new Users();
        //Объявление пользователя, который будет работать
        User user = null;
        boolean program = true;
        byte flag = 0;
        while (program){
            switch (flag) {

                //Первый вход. отображается только при первичном заходе в систему.
                case 0: {
                    System.out.println("Привет! Перед тобой программа учёта расходов.");
                    flag = 1;
                }
                break;

                //стартовое меню входа или регистрации в программе.
                case 1: {
                    System.out.println("Выбери одну из команд ниже(используй цифры команд):");
                    System.out.println("1. Регистрация.");
                    System.out.println("2. Войти");
                    System.out.println("3. Выйти из программы.");
                    byte comand = SupportC.take_command_and_check(3);
                    switch (comand) {

                        //Регистрация пользователя и вход
                        case 1: {
                            user = users.create_User();
                            System.out.println("Хотите войти в зарегистрированный ЛК?");
                            System.out.println("1. Да");
                            System.out.println("2. Нет");
                            comand = SupportC.take_command_and_check(2);
                            if (comand == 1)

                                flag = 2;
                            else
                                flag = 1;
                        }
                        break;

                        //Проверка логина и пароля пользователя
                        case 2: {
                            user = users.check_entering();
                            flag = 2;
                        }
                        break;
                        case 3: flag = 3;

                        break;
                        default:
                            break;
                    }
                }
                break;
                //Польватель зашел в аккаунт, варианты действий.
                case 2:{
                    flag = userMenu(user);
                }
                break;

                case 3:{
                    System.out.println("Пока");
                    program = false;
                }
                break;


                default:
                    break;
            }
        }
    }

    public static byte userMenu(User user){
        System.out.println(user.getName() + " Выберите действие:");
        System.out.println("1.Внести расходы.");
        System.out.println("2.Изменить/Удалить расход(ы).");
        System.out.println("3. Получить статистику расходов за период.");
        System.out.println("4. Выход из ЛК.");
        byte command = SupportC.take_command_and_check(4);

        switch (command){
            case 1:{
                System.out.println("Добавить трату за сегодня?");
                System.out.println("1.Да");
                System.out.println("2.Ввести дату вручную этого месяца.");
                System.out.println("3.Ввести любую дату вручную.");
                command = SupportC.take_command_and_check(3);
                if (command == 1) {
                    addExpenseToday(user.expenses);
                }
                else if (command == 2)
                    addExpenseAnyDay(1, user.expenses);
                else {
                    System.out.println("Какую дату хотите ввести? \n(Формат даты dd mm если дата текущего года, если нет, то dd mm yyyy )");
                    System.out.println("Например, подходящие варианты 11 01 (11 января) 11 05 2022 и тд.");
                    addExpenseAnyDay(2, user.expenses);
                    System.out.println();
                }

                return 2;
                }

            //.Изменить/Удалить расход(ы).
            case 2: {
                System.out.println("Какой расход вы хотите изменить/удалить? (Введите номер расхода)");
                printAllExpenses(user.expenses);
                chooseDeleteExpense(SupportC.take_command_and_check(user.expenses.getExpenses().size()) - 1, user);
                System.out.println();
                return 2;
            }
            // Получить статистику расходов за период (пока только все).
            case 3: {
                printAllExpenses(user.expenses);
                return 2;
            }
            // 4. Выход из ЛК.
            case 4:{
                return 1;
            }

        }
        return 2;

    }

    //Метод изменения или удаления записи расходов
    public static void chooseDeleteExpense(int nomberOfPozition, User user  ){
        System.out.print("Хотите удалить или изменить расход? ");
        System.out.println(printExpense(nomberOfPozition, user.expenses));
        System.out.println("1.Удалить.");
        System.out.println("2.Изменить.");
        int command = SupportC.take_command_and_check(2);
        if (command == 1) {
            user.expenses.getExpenses().remove(nomberOfPozition);

        }
        else{

            System.out.println("Что вы хотите отредактировать?");
            System.out.println("1. Сумму.");
            System.out.println("2. Категорию.");
            System.out.println("3. Дату.");
            command = SupportC.take_command_and_check(3);
            switch (command){
                //Изменение суммы у выбранной позиции
                case 1:{
                    System.out.println("Какую сумму вы хотите установить?");
                    double cost = SupportC.isSumm();
                    user.expenses.getExpenses().get(nomberOfPozition).setCoast(cost);
                    System.out.println("Сумма успешно изменена!");

                }

                //Изменение категории трат у выбранной позиции.
                case 2: {
                    System.out.println("Какую категорию вы хотите установить?");
                    SearchTypeExp(user.expenses);
                    command = SupportC.take_command_and_check(user.expenses.getTypesOfExpens().size());
                    user.expenses.getExpenses().get(nomberOfPozition).setType(user.expenses.getTypesOfExpens().get(command - 1));

                }

                //Изменение даты у выбранной позиции
                case 3: {
                    System.out.println("На какаю дату вы хотите изменить текущую дату?");
                    System.out.println("Формат даты dd mm если дата текущего года, если нет, то dd mm yyyy ");
                    System.out.println("Например, подходящие варианты 11 01 (11 января) 11 05 2022 и тд.");
                    System.out.println();
                    String dateS = SupportC.inLine();
                    LocalDate date = dateReturnAnyDate(dateS);
                    user.expenses.getExpenses().get(nomberOfPozition).setDate(date);

                }


            }

        }
    }

    //Добавить трату за сегодня
    public static   void addExpenseToday(Expenses expenses){
        LocalDate date;
        date = LocalDate.now();
        System.out.println("Трату какой категории хотите добавить?");
        SearchTypeExp(expenses);
        byte command = SupportC.take_command_and_check(expenses.getTypesOfExpens().size());
        String type = expenses.getTypesOfExpens().get(command-1);
        System.out.println("Сколько вы потратили?");
        double cost = SupportC.isSumm();
        expenses.setNewExpens(expenses.new Expense(date, type, cost ));
        System.out.println("Отлично! Ваш расход добавлен!");
        System.out.println(date + " " + type + " " + cost);
    }


    //Вывести определенный расход
    public static String printExpense(int i, Expenses expenses){
        Expenses.Expense expense = expenses.getExpenses().get(i);
        return i + "___" + expense.getDate() + "___" + expense.getType() + "___" + expense.getCoast();
    }

    //Отобразить текущие категории трат
    public static void SearchTypeExp(Expenses expenses){
        ArrayList<String> types = expenses.getTypesOfExpens();
        for (int i = 0; i < types.size(); i++){
            System.out.println((i +1) + ". " + types.get(i));
        }
    }


    //Добавить трату за любой день этого месяца или любого другого дня
    public static void addExpenseAnyDay(int flag ,Expenses expenses){
        LocalDate date;
        if (flag == 1) {
            date = LocalDate.now();
            System.out.println("За какой день этого месяца вы хотите ввести расходы?");
            date = dateReturn(SupportC.inLine());
        }
        else {
            String dateS = SupportC.inLine();
            date = dateReturnAnyDate(dateS);
        }
        System.out.println("Трату какой категории хотите добавить?");
        SearchTypeExp(expenses);
        byte command = SupportC.take_command_and_check(expenses.getTypesOfExpens().size());
        String type = expenses.getTypesOfExpens().get(command-1);
        System.out.println("Сколько вы потратили?");
        double cost = SupportC.isSumm();
        expenses.setNewExpens(expenses.new Expense(date, type, cost ));
        System.out.println("Отлично! Ваш расход добавлен!");
        System.out.println(date + " " + type + " " + cost);
    }

    //Ввод дня текущего месяца с клавиатуры с проверками
    public static LocalDate dateReturn(String dateS){
        LocalDate date = LocalDate.now();
        while (true) {
            if (!SupportC.isDigit(dateS)) {
                System.out.println("Значение не является числом. Введите подходящее значение.");
                dateS = SupportC.inLine();
            }
            else {
                int day = Integer.parseInt(dateS);
                //System.out.println(date.getDayOfMonth() + "   " + date.lengthOfMonth());
                if (day <= 0 || date.getDayOfMonth() < day ){
                    System.out.println("Значение выходит за диапазон текущего месяца");
                    dateS = SupportC.inLine();
                }
                else return date.withDayOfMonth(day);
            }

        }
    }
    //Ввод даты в корректном формате
    public static LocalDate dateReturnAnyDate(String dateS){
        LocalDate date;
        while(!SupportC.isDate_ddmm(dateS) && !SupportC.isDate_ddmmuuuu(dateS)){
            System.out.println("Вы ввели невалидный вариант. попробуйте снова");
            System.out.println("Например, подходящие варианты 11 01 (11 января) 11 05 2022 и тд.");
            dateS = SupportC.inLine();
        }
        if (SupportC.isDate_ddmm(dateS))
            date = LocalDate.parse(dateS + " " + LocalDate.now().getYear(), DateTimeFormatter.ofPattern("dd MM uuuu"));
        else
            date = LocalDate.parse(dateS , DateTimeFormatter.ofPattern("dd MM uuuu"));
        return  date;
    }


    //Вывести все расходы пользователя
    public static void printAllExpenses(Expenses expenses){
        for (int i = 0; i < expenses.getExpenses().size(); i++){
            Expenses.Expense expense = expenses.getExpenses().get(i);
            System.out.println((i+1) +". "+ expense.getDate() +"___"+ expense.getType() +"___" + expense.getCoast());
        }
    }

}