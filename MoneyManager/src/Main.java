public class Main {
    public static void main(String[] args) {
        User usuario = new User("Jose", "jfnr398@", "1234");
        User usuario2 = new User("Fernando", "fernando398@", "4321");

        System.out.println(usuario);
        System.out.println(usuario2);

        Ingreso ingreso = usuario2.createIngreso(10000, "Pago nómina", "Salario");
        System.out.println(ingreso);

        Gasto gasto = usuario2.createGasto(1000, "Factura hogar", "Servicios");
        System.out.println(gasto);
    }
}