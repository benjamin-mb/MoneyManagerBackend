import java.util.Scanner;

public class Main {
    static Scanner lector = new Scanner(System.in);


    public static void main(String[] args) {
        //Instancias de prueba
        User admin = new User("José", "jfnr398", "1234");
        User user = new User("Fernando" ,"fercho398", "4321");
        menu();
    }

    public static void menu() {
        int option;
        do {
            System.out.println("======MONEY MANAGER======");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrate");
            System.out.println("3. Salir");
            option = lector.nextInt();

            switch (option) {
                case 1:
                    menuSesion();
                    break;

                case 2:
                    menuRegistro();
                    break;

                case 3:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Digite una opcion correcta");
                    break;
            }
        } while (option > 3);
    }

    public static void menuSesion(){
        System.out.println("======MONEY MANAGER======");
        System.out.println("======INICIAR SESION=====");
        System.out.print("Digite su correo: ");
        String email = lector.next();
        System.out.print("Digite su contraseña: ");
        String password = lector.next();

        Boolean status = User.validateUser(email, password);

        if(status == false){
            System.out.println("Correo o contraseña incorrectos");
            menu();
        }
    }

    public static void menuRegistro(){
        System.out.println("======MONEY MANAGER======");
        System.out.println("=========REGISTRO========");
        System.out.print("Digite su nombre: ");
        String name = lector.next();
        System.out.print("Digite su correo: ");
        String email = lector.next();
        System.out.print("Digite su contraseña: ");
        String password = lector.next();

        User user = new User(name, email, password);
        System.out.println("Usuario creado");
        menu();
    }

    public static void dashboard(User user) {
        int option;
        do{
            System.out.println("======MONEY MANAGER======");
            System.out.println("========DASHBOARD========");
            System.out.println("Ingresos: ");
            user.printIngreso();
            System.out.println("Gastos: ");
            user.printGasto();
            System.out.println("1. Registrar ingreso");
            System.out.println("2. Registrar gasto");
            System.out.println("3. Consultar cuenta");
            option = lector.nextInt();

            if(option == 1 || option == 2){
                menuTransaccion(option, user);
            } else if(option == 3){
                consultarCuenta(user);
            }
        }while(option > 3);
    }

    private static void menuTransaccion(int type, User user){
        int option = 0;
        System.out.println("======MONEY MANAGER======");
        System.out.println("=====REG TRANSACCION=====");
        System.out.print("Digite un valor: ");
        int value = lector.nextInt();
        System.out.print("Digite una descripción: ");
        String description = lector.next();
        lector.nextLine();

        String category = "";

        do{
            System.out.println("Escoja una categoria: ");
            System.out.println("1. Salario");
            System.out.println("2. Arriendo");
            System.out.println("3. Comisiones");
            System.out.println("4. Servicios");
            option = lector.nextInt();

            switch (option){
                case 1:
                    category = "Salario";
                    break;

                case 2:
                    category = "Arriendo";
                    break;

                case 3:
                    category = "Comisiones";
                    break;

                case 4:
                    category = "Servicios";
                    break;

                default:
                    System.out.println("Digite una opcion correcta");
                    break;
            }
        }while(option > 4);

        switch (type){
            case 1:
                user.createTransaction("Ingreso", value, description, category);
                break;

            case 2:
                user.createTransaction("Gasto", value, description, category);
                break;
        }

        dashboard(user);
    }

    public static void consultarCuenta(User user){
        int option;
        do{
            System.out.println("======MONEY MANEGER======");
            System.out.println("======CUENTA USUARIO======");
            System.out.println("Nombre: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Ingresos registrados: " + user.getTotalIngreso());
            System.out.println("Gastos registrados: " + user.getTotalGasto());
            System.out.println("1. Dashboard");
            System.out.println("2. Cerrar sesión");
            option = lector.nextInt();

            switch (option){
                case 1:
                    dashboard(user);
                    break;

                case 2:
                    user.logout();

                default:
                    System.out.println("Digite una opcion correcta");
                    break;
            }
        }while(option > 2);
    }
}