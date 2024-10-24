package menu.panel;

import classes.Main;
import classes.account.User;
import menu.panel.operation.Filter;
import menu.panel.operation.PanelTransaction;
import menu.panel.user.normal.Account;
import menu.panel.user.suser.Admin;
import menu.panel.user.normal.Categories;

public class Dashboard {
    public static boolean statusUpdate;
    public static void menu(User user) {
        int option;
        do{
            user.getTransactionManager().updateListUser(user.getId());
            user.getTransactionManager().updateListType();
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("========DASHBOARD========");
            System.out.println("=========BALANCE=========");
            System.out.println("TOTAL: " + user.getUserBalance().sumaTotal());
            System.out.println("En ingresos: " + user.getUserBalance().sumaIngresos());
            System.out.println("En gastos: " + user.getUserBalance().sumaGastos());
            System.out.println("======TRANSACCIONES======");
            System.out.println("De ingresos: ");
            user.getTransactionManager().printListIngresos();
            System.out.println("");
            System.out.println("De gastos: ");
            user.getTransactionManager().printListGastos();
            System.out.println("========OPCIONES=========");
            System.out.println("1. Registrar transacción");
            System.out.println("2. Gestionar transacciones");
            System.out.println("3. Consultar cuenta");
            if(Admin.statusUpdateAdmin && user.getId() == 1){
                System.out.println("0. Dashboard Admin");
            }
            System.out.println("=========================");
            option = Main.lector.nextInt();

            if(option == 1){
                menuTransaction(user);
            }else if(option == 2) {
                manageTransaction(user);
            }else if(option == 3){
                selectUser(user);
            } else if (option == 0 && user.getId() == 1) {
                Admin.statusUpdateAdmin = false;
                Admin.dashboard(Admin.suser);
            }
        }while(option < 0 || option > 3);
    }

    public static void menuTransaction(User user){
        int option;
        int type;

        System.out.println("");
        System.out.println("======MONEY MANAGER======");
        System.out.println("=====REG TRANSACCION=====");
        System.out.println("Para cancelar digite cero (0)");

        do {
            System.out.println("Digite el tipo de transacción: ");
            System.out.println("1. Ingreso");
            System.out.println("2. Gasto");
            type = Main.lector.nextInt();

            if(type == 0 && Admin.statusUpdateAdmin && user.getId() == 1){
                Admin.statusUpdateAdmin = false;
                System.out.println("Operación cancelada");
                Admin.dashboard(Admin.suser);
            } else if(type == 0){
                menu(user);
            }
        }while(type < 0 || type > 2);

        System.out.print("Digite un valor: ");
        int value = Main.lector.nextInt();
        System.out.print("Digite una descripción: ");
        String description = Main.lector.next();
        Main.lector.nextLine();

        String category = "";

        do{
            System.out.println("Escoja una categoria: ");
            user.getCatogories().printCategoriesByUser();
            option = Main.lector.nextInt();
            if(option > 0 && option <= user.getCatogories().categoriesByUserSize()){
                category = user.getCatogories().selectCategory(option);
            }else if(option < 0 && option > user.getCatogories().categoriesByUserSize()){
                System.out.println("Digite un número entre 1 y " + user.getCatogories().categoriesByUserSize());
            }

            if(option == 0 && Admin.statusUpdateAdmin && user.getId() == 1){
                Admin.statusUpdateAdmin = false;
                System.out.println("Operación cancelada");
                Admin.dashboard(Admin.suser);
            } else if (option == 0){
                menu(user);
            }
        }while(option < 0 || option > user.getCatogories().categoriesByUserSize());

        if(statusUpdate == false){
            switch (type){
                case 1:
                    user.getTransactionManager().createTransaction(user.getId(), "Ingreso", value, description, category);
                    break;
                case 2:
                    user.getTransactionManager().createTransaction(user.getId(), "Gasto", value, description, category);
                    break;
            }
        }else if(statusUpdate == true){
            if(type == 1){
                user.getTransactionManager().updateTransaction("Ingreso", value, description, category);
            } else if (type == 2){
                user.getTransactionManager().updateTransaction("Gasto", value, description, category);
            }
            statusUpdate = false;
        }

        if(Admin.statusUpdateAdmin && user.getId() == 1){
            Admin.statusUpdateAdmin = false;
            Admin.dashboard(Admin.suser);
        }else {
            menu(user);
        }
    }

    public static void manageTransaction(User user){
        int option;
        do {
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("========GESTIONAR========");

            System.out.println("Elija una opción: ");
            System.out.println("1. Modificar");
            System.out.println("2. Eliminar");
            System.out.println("3. Filtrar");
            option = Main.lector.nextInt();

            switch (option){
                case 1:
                    PanelTransaction.selectTransaction(user, user.getTransactions().getListUser());
                    PanelTransaction.updateTransaction(user);
                    break;

                case 2:
                    PanelTransaction.selectTransaction(user, user.getTransactions().getListUser());
                    PanelTransaction.deleteTransaction(user);
                    break;

                case 3:
                    Filter.filterTransaction(user, user.getTransactions().getListUser());
                    break;

                default:
                    System.out.println("Opción invalida");
                    break;
            }
        }while (option < 1 || option > 2);
    }

    public static void selectUser(User user){
        boolean statusAdmin = false;
        int option;
        do{
            System.out.println("======MONEY MANAGER======");
            System.out.println("======CUENTA USUARIO=====");
            System.out.println("Nombre: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Ingresos registrados: " + user.getTransactionManager().getTotalIngreso());
            System.out.println("Gastos registrados: " + user.getTransactionManager().getTotalGasto());
            System.out.println("1. Modificar datos");
            System.out.println("2. Lista de categorias");
            System.out.println("3. Dashboard principal");
            if(user.getId() == 1){
                statusAdmin = true;
                System.out.println("4. Dashboard Admin");
            }
            System.out.println("0. Cerrar sesión");
            System.out.println("=========================");
            option = Main.lector.nextInt();

            switch (option){
                case 0:
                    user.logout(user);
                    break;

                case 1:
                    Account.updateUser(user);
                    break;

                case 2:
                    Categories.categoriesUser(user);
                    break;

                case 3:
                    menu(user);
                    break;

                case 4:
                    Admin.dashboard(user);
                    break;

                default:
                    System.out.println("Digite una opcion correcta");
                    break;
            }
        }while((option < 0 || option > 3) && statusAdmin == false);
    }
}
