//public class Transaction{
//    private int id;
//    private int user;
//    private String type;
//    private double value;
//    private String description;
//    private String category;
//    private String date;
//    private static int counterTransaction;
//
//    public Transaction(String type, int user, double value, String description, String category){
//        this.id = ++counterTransaction;
//        this.user = user;
//        this.type = type;
//        this.value = value;
//        this.description = description;
//        this.category = category;
//    }
//
//    @Override
//    public String toString() {
//        return "Transaction{" +
//                "id=" + id +
//                ", user=" + user +
//                ", type='" + type + '\'' +
//                ", value=" + value +
//                ", description='" + description + '\'' +
//                ", category='" + category + '\'' +
//                ", date='" + date + '\'' +
//                '}';
//    }
//
//
//}
public class Transaction{
    private int id;
    private int user;
    private String type;
    private double value;
    private String description;
    private String category;
    private String date;
    private static int counterTransaction;

    public Transaction(int user, String type, double value, String description, String category){
        this.id = ++counterTransaction;
        this.user = user;
        this.type = type;
        this.value = value;
        this.description = description;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", user=" + user +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
