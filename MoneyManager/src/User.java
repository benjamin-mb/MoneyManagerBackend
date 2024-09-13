public class User{
    public int id;
    private String name;
    private String email;
    private String password;
    private String type;
    private String bank;
    private double balance;
    private static int userCounter;

    public User(String name, String email, String password){//CreateUser - Constructor
        this.id = ++userCounter;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Ingreso createIngreso(double value, String description, String category){
        Ingreso transaction = new Ingreso(this.id, value, description, category);
        return transaction;
    }

    public Gasto createGasto(double value, String description, String category){
        Gasto transaction = new Gasto(this.id, value, description, category);
        return transaction;
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