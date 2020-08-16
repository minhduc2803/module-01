import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        LinearTable lpht = new LinearTable();

        int choice = 0;
        /**  Perform LinearProbingHashTable operations  **/
        do
        {
            System.out.println("\nHash Table Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. delete");
            System.out.println("3. search");
            System.out.println("4. number of elements");
            System.out.println("5. size");
            System.out.println("6. quit");

            choice = scan.nextInt();
            switch (choice)
            {
                case 1 :
                    System.out.println("Enter key and value");
                    lpht.insert(scan.next(), scan.next() );
                    break;
                case 2 :
                    System.out.println("Enter key");
                    lpht.delete( scan.next() );
                    break;
                case 3 :
                    System.out.println("Enter key");
                    System.out.println("Value = "+ lpht.search( scan.next() ));
                    break;
                case 4 :
                    System.out.println("Number of elements = "+ lpht.getNumberElements() );
                    break;
                case 5 :
                    System.out.println("Size = "+ lpht.getSize() );
                    break;
                case 6:
                    break;
                default :
                    System.out.println("Wrong Entry \n ");
                    break;
            }
        } while (choice != 6);

    }
}
