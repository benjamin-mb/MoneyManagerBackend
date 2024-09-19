import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String type;
    private String bank;
    private double balance;

    private static int userCounter;
    public static ArrayList<User> datos = new ArrayList<>();
    //Atributos static: Se comparten entre todas las instancias. Solo existe una copia de la variable para toda la clase, por lo que las listas es ideal que solo exista una copia ya que aquí se guardarán a todos los usuarios
    //Métodos static: Pueden ser llamados sin necesidad de crear una instancia de la clase.

    public ArrayList<Transaction> ingresoUser = new ArrayList<Transaction>();
    public ArrayList<Transaction> gastoUser = new ArrayList<Transaction>();

    public User(String name, String email, String password) {//CreateUser - Constructor
        this.id = ++userCounter;
        this.name = name;
        this.email = email;
        this.password = password;
        datos.add(this);
    }

    public String getName() {
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public int getTotalIngreso(){
        return ingresoUser.size();
    }

    public int getTotalGasto(){
        return gastoUser.size();
    }

    public static Boolean validateUser(String email, String password){
        for (int i = 0; i < datos.size(); i++) {
            if(email.equals(datos.get(i).email) && password.equals(datos.get(i).password)){
                System.out.println("Acceso autorizado.");
                System.out.println("Bienvenido " + datos.get(i).name);
                Main.dashboard(datos.get(i));
                return true;
            }
        }
        return false;
    }

    public void printIngreso(){
        Transaction.userIngreso(this.id, this.ingresoUser);
        this.ingresoUser.forEach(System.out::println);
    }

    public void printGasto(){
        Transaction.userGasto(this.id, this.gastoUser);
        this.gastoUser.forEach(System.out::println);
    }

    public void logout(){
        Main.menu();
    }

    public void createTransaction(String type, double value, String description, String category){
        if(type.equals("Ingreso")){
            Ingreso transaction = new Ingreso(this.id, value, description, category);
        } else if(type.equals("Gasto")){
            Gasto transaction = new Gasto(this.id, value, description, category);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}