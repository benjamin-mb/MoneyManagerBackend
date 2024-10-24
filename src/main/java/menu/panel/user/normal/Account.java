package menu.panel.user.normal;

import classes.Main;
import classes.account.User;
import menu.panel.Dashboard;
import menu.panel.user.suser.Admin;

import java.util.ArrayList;

public class Account {
    public static void selectUser(User user, ArrayList<User> databaseUsers){
        int option;
        System.out.println("");
        System.out.println("LISTA DE OPCIONES");
        System.out.println("=======================================================================================================");
        user.getUserManager().printDatabase(databaseUsers);
        System.out.println("=======================================================================================================");
        System.out.println("");

        do{
            System.out.println("Para cancelar digite cero (0)");
            System.out.print("Digite el número del usuario a seleccionar: ");
            option = Main.lector.nextInt();
            if(option == 0 && user.getId() == 1){
                Admin.dashboard(user);
            }
        }while(option < 0 || option > databaseUsers.size());

        user.getUserManager().selectUser(option, databaseUsers);
    }

    public static void updateUser(User user){
        int option;

        do {
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("====MODIFICAR USUARIO====");
            System.out.println("Para cancelar digite cero (0)");
            System.out.println("1. Cambiar nombre: " + user.getName());
            System.out.println("2. Cambiar email: " + user.getEmail());
            System.out.println("3. Cambiar contraseña: " + user.getPassword());
            System.out.println("4. Consultar cuenta");
            option = Main.lector.nextInt();

            switch (option){
                case 0:
                    break;

                case 1:
                    System.out.print("Digite un nuevo nombre: ");
                    String name = Main.lector.next();

                    System.out.println("Su nombre ha sido cambiado");
                    user.setName(name);
                    break;

                case 2:
                    System.out.print("Digite un nuevo email: ");
                    String email = Main.lector.next();

                    System.out.println("Su correo ha sido cambiado");
                    user.setEmail(email);
                    break;

                case 3:
                    String password;
                    String passwordConfirm;
                    do{
                        System.out.print("Digite una nueva contraseña: ");
                        password = Main.lector.next();
                        System.out.print("Confirme su nueva contraseña: ");
                        passwordConfirm = Main.lector.next();

                        if(password.equals(passwordConfirm)){
                            System.out.println("Su contraseña ha sido cambiada");
                            user.setPassword(password);
                        }else {
                            System.out.println("Las contraseñas no coinciden");
                        }
                    }while (!password.equals(passwordConfirm));
                    break;

                case 4:
                    Dashboard.selectUser(user);
                    break;

                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }while (option < 0 || option > 4);

        if(Admin.statusUpdateAdmin && user.getId() == 1){
            Admin.statusUpdateAdmin = false;
            if(option == 0){
                System.out.println("Operación cancelada");
            }
            Admin.dashboard(Admin.suser);
        }

        Dashboard.menu(user);
    }

    public static void deleteUser(User user){
        int confirm;

        do {
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("=====ELIMINAR USUARIO====");
            System.out.println("Estas seguro de eliminar a este usuario");
            System.out.println(user.getUserManager().getTargetUser());
            System.out.println("1. Si");
            System.out.println("2. No");
            confirm = Main.lector.nextInt();

            switch (confirm) {
                case 1:
                    System.out.println("Usuario eliminado");
                    user.getUserManager().deleteUser();
                    break;

                case 2:
                    Admin.dashboard(user);
                    break;

                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }while (confirm < 1 || confirm > 2);

        Admin.dashboard(user);
    }

    public static void printDatabase(ArrayList<User> databaseUser){
        System.out.println("");
        for (int i = 0; i < databaseUser.size(); i++) {
            System.out.println(i+1 + ". " + databaseUser.get(i));
        }
        System.out.println("");
    }
}