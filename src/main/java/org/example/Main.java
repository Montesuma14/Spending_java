package org.example;

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
                System.out.println("2.Нет (ввести дату врчуную этого месяца)");
                command = SupportC.take_command_and_check(2);
                if (command == 1) {
                    user.expenses.addExpenseToday();

                }
                else
                    user.expenses.addExpenseAnyDay();

                }
                return 2;

            case 2: {
                System.out.println("Какой расход вы хотите изменить/далить?");
                user.expenses.printAllExpenses();
                ChouseDeleteExpense(SupportC.take_command_and_check(user.expenses.getExpenses().size()), user);
                System.out.println();
                return 2;
            }
            case 3: {
                System.out.println();
                System.out.println();
                user.expenses.printAllExpenses();
                System.out.println();
                System.out.println();
                return 2;
            }
            case 4:{
                return 1;
            }

        }
        return 2;

    }

    public static void ChouseDeleteExpense(int i, User user  ){
        System.out.println("Хотите удалить или изменить расход?");
        System.out.println("1.Удалить.");
        System.out.println("2.Изменить.");
        int command = SupportC.take_command_and_check(2);
        if (command == 1) {
            user.expenses.getExpenses().remove(i - 1);

        }
        else{
            System.out.println("Пока без реализации.");

        }
    }









}