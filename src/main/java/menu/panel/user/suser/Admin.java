package menu.panel.user.suser;

import classes.Main;
import classes.account.User;
import classes.operation.Transaction;
import menu.panel.Dashboard;
import menu.panel.operation.Filter;
import menu.panel.operation.PanelTransaction;
import menu.panel.user.normal.Account;

import java.util.ArrayList;

import static classes.Main.lector;

public class Admin {
    public static User suser = null;
    public static boolean statusUpdateAdmin = false;
    public static void dashboard(User user){
        int option;
        do{
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("=====DASHBOARD ADMIN=====");
            System.out.println("===========MENU==========");
            System.out.println("1. Gestionar usuarios");
            System.out.println("2. Gestionar transacciones");
            System.out.println("3. Dashboard principal");
            option = lector.nextInt();

            switch (option){
                case 1:
                    usersManager(user);
                    break;

                case 2:
                    transactionsManager(user);
                    break;

                case 3:
                    Dashboard.menu(user);
                    break;
            }
        }while (option < 1 || option > 3);
    }

    public static void usersManager(User user){
        int option;
        do{
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("========GESTIONAR========");
            System.out.println("Elija una opción: ");
            System.out.println("1. Filtrar usuarios");
            System.out.println("2. Modificar usuario");
            System.out.println("3. Eliminar usuario");
            option = lector.nextInt();

            switch (option){
                case 1:
                    filterUserData(user);
                    break;

                case 2:
                    updateUserData(user, User.getUserData());
                    break;

                case 3:
                    deleteUserData(user, User.getUserData());
                    break;

                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }while(option < 1 || option > 3);
    }

    public static void filterUserData(User user){
        user.printData();
        System.out.println("Total de usuarios creados: " + User.totalUsers());

        PanelFilter.filterUser(user);
    }

    public static void updateUserData(User user, ArrayList<User> databaseUsers){
        suser = user;
        statusUpdateAdmin = true;
        Account.selectUser(user, databaseUsers);
        Account.updateUser(user.getUserManager().getTargetUser());
    }

    public static void deleteUserData(User user, ArrayList<User> databaseUsers){
        Account.selectUser(user, databaseUsers);
        Account.deleteUser(user);
    }

    public static void transactionsManager(User user){
        int option;
        do{
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("========GESTIONAR========");
            System.out.println("Elija una opción: ");
            System.out.println("1. Filtrar transacciones");
            System.out.println("2. Modificar transacción");
            System.out.println("3. Eliminar transacción");
            option = Main.lector.nextInt();

            switch (option){
                case 1:
                    if(user.getId() == 1){
                        filterTransactionsData(user);
                    }

                    break;

                case 2:
                    if(user.getId() == 1){
                        updateTransactionsData(user);
                    }
                    break;

                case 3:
                    if(user.getId() == 1){
                        deleteTransactionsData(user);
                    }
                    break;

                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }while(option > 1);
    }

    public static void filterTransactionsData(User user){
        suser = user;
        statusUpdateAdmin = true;
        Transaction.printData();
        System.out.println("Total de transacciones creadas: " + Transaction.totalTransactions());

        Filter.filterTransaction(user, Transaction.getTransactionsData());
    }

    public static void updateTransactionsData(User user){
        suser = user;
        statusUpdateAdmin = true;
        PanelTransaction.selectTransaction(user, Transaction.getTransactionsData());
        PanelTransaction.updateTransaction(user);
    }

    public static void deleteTransactionsData(User user){
        suser = user;
        statusUpdateAdmin = true;
        PanelTransaction.selectTransaction(user, Transaction.getTransactionsData());
        PanelTransaction.deleteTransaction(user);
    }
}