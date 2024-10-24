package menu.panel.operation;

import classes.account.User;
import classes.Main;
import classes.operation.Transaction;

import java.util.ArrayList;

public class Filter {

    public static void filterTransaction(User user, ArrayList<Transaction> databaseTransactions){
        String option;
        do{
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("=========FILTRAR=========");
            user.getTransactionFilter().emptyDatafilter();
            for (int i = 0; i < user.getTransactionFilter().getDataFilter().length; i++) {
                System.out.print("[" + user.getTransactionFilter().getDataFilter()[i] + "]  " );
            }
            System.out.println("");
            System.out.println("Digite el número del tipo de filtro a consultar");
            System.out.println("Para iniciar con la consulta digite 0");
            System.out.println("Para limpiar los filtros digite -");
            System.out.println("1. Tipo");
            System.out.println("2. Valor");
            System.out.println("3. Categoria");
            System.out.println("4. Fecha");
            System.out.println("============");
            System.out.println("0. Consultar");
            option = Main.lector.next();

            switch (option){
                case "-":
                    user.getTransactionFilter().clearDataFilter();
                    break;

                case "0":
                    FilterTransaction.resultFilterTransaction(user, databaseTransactions);
                    break;

                case "1":
                    typeFilterTransaction(user);
                    break;

                case "2":
                    valueFilterTransaction(user);
                    break;

                case "3":
                    categoryFilterTransaction(user);
                    break;

                case "4":
                    dateFilterTransaction(user);

                default:
                    System.out.println("Opción invalida");
                    break;
            }
        }while(!option.equals("0"));
    }

    public static void typeFilterTransaction(User user){
        System.out.println("Digite el número de la opción a seleccionar");
        System.out.println("1. Ingreso");
        System.out.println("2. Gasto");
        int option = Main.lector.nextInt();

        if(option == 1){
            user.getTransactionFilter().getDataFilter()[0] = "Ingreso";
        } else if(option == 2){
            user.getTransactionFilter().getDataFilter()[0] = "Gasto";
        }
    }

    public static void valueFilterTransaction(User user) {
        String minor;
        String major;
        boolean status = false;
        System.out.print("Digite un valor para establecer un inicio al rango: ");
        minor = Main.lector.next();
        user.getTransactionFilter().getDataFilter()[1] = minor;

        do{
            System.out.println("Digite un valor mayor a " + user.getTransactionFilter().getDataFilter()[1] + " para establecer un limite al rango");
            System.out.println("Si no desea establecer un rango digite 0");
            major = Main.lector.next();

            if(Integer.parseInt(major) < Integer.parseInt(minor)){
                status = true;
            }else {
                status = false;
            }

            if(major.equals("0")){
                status = false;
            }
        }while(status);

        user.getTransactionFilter().getDataFilter()[2] = major;
    }

    public static void categoryFilterTransaction(User user){
        int option;
        String category = "";
        do{
            System.out.println("Digite el número de la opción a seleccionar");
            user.getCatogories().printCategoriesByUser();
            option = Main.lector.nextInt();
            if(option > 0 && option <= user.getCatogories().categoriesByUserSize()){
                category = user.getCatogories().selectCategory(option);
            }else if(option <= 0 && option > user.getCatogories().categoriesByUserSize()){
                System.out.println("Digite un número entre 1 y " + user.getCatogories().categoriesByUserSize());
            }
        }while(option < 1 || option > user.getCatogories().categoriesByUserSize());

        user.getTransactionFilter().getDataFilter()[3] = category;
    }

    private static void dateFilterTransaction(User user) {
        System.out.println("Digite la fecha a consultar de la siguiente forma: 'dd-mm-yyyy'");
        String option = Main.lector.next();

        user.getTransactionFilter().getDataFilter()[4] = option;
    }
}