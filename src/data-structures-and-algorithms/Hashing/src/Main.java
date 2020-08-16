import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        HashTable hashtable = new ChainingTable();

        int choice = 0;

        do
        {
            System.out.print(hashtable.getName());
            System.out.println(" Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. delete");
            System.out.println("3. update");
            System.out.println("4. search");
            System.out.println("5. number of elements");
            System.out.println("6. size");
            System.out.println("7. quit");

            choice = scan.nextInt();
            switch (choice)
            {
                case 1 :
                    System.out.println("Enter key and value");
                    hashtable.insert(scan.next(), scan.next() );
                    break;
                case 2 :
                    System.out.println("Enter key");
                    hashtable.delete( scan.next() );
                    break;
                case 3 :
                    System.out.println("Enter key and value");
                    hashtable.update(scan.next(), scan.next() );
                    break;
                case 4 :
                    System.out.println("Enter key");
                    System.out.println("Value = "+ hashtable.search( scan.next() ));
                    break;
                case 5 :
                    System.out.println("Number of elements = "+ hashtable.getNumberElements() );
                    break;
                case 6 :
                    System.out.println("Size = "+ hashtable.getSize() );
                    break;
                case 7:
                    break;
                default :
                    System.out.println("Wrong Entry \n ");
                    break;
            }
        } while (choice != 7);

    }
}
