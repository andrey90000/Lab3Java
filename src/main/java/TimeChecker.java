import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class TimeChecker
 * It`s evaluating time for operations such us addition,deletion,getting
 *
 * @author Usov Andrey
 */
public class TimeChecker extends SimpleTable{

    /**
     * Field array - array list for test
     */
    private ArrayList<Integer> array;

    /**
     * Field list - linked list for test
     */
    private LinkedList<Integer> list;

    /**
     * Field begin - starting time for each testing
     */
    private long begin;

    /**
     * Field end - ending time for each testing
     */
    private long end;

    /**
     * Final AmountOfElems - amount of elements for testing
     */
    private final int AmountOfElems = 5000;

    /**
     * Field data - Data for table
     */
    public String[][] data;

    /**
     * Field iForColumn - Column counter
     */
    private int iForColumn;

    /**
     * Constructor TimeChecker
     * It initializes array and list
     */
    public TimeChecker(){
        iForColumn = 0;
        array = new ArrayList<>();
        list = new LinkedList<>();
        data = new String[10][4];
    }

    /**
     * Function AddTest
     * It adds missing elements into array and list
     */
    private void AddTest(){
        for (int i = array.size(); array.size() < AmountOfElems; i++) {
            array.add(i);
        }
        for (int i = list.size(); list.size() < AmountOfElems; i++) {
            list.add(i);
        }
    }

    /**
     * Function AddCheck
     * It adds elements into array and list also measures time for add operation
     */
    public void AddCheck(){
        begin = System.nanoTime();
        for (int i = 0; i < AmountOfElems; i++) {
            array.add(i);
        }
        end = System.nanoTime();
        long resultForLinked = end - begin;
        begin = System.nanoTime();
        for (int i = 0; i < AmountOfElems; i++) {
            list.add(i);
        }
        end = System.nanoTime();
        long resultForArray = end - begin;
        DataForStr(1, 1, resultForArray, iForColumn);

        DataForStr(1, 2, resultForLinked, iForColumn);
    }

    /**
     * Function DeleteCheck
     * It deletes elements in array and list also measures time for delete operation
     */
    public void DeleteCheck(){
        if (array.size() < AmountOfElems || list.size() < AmountOfElems) AddTest();
        begin = System.nanoTime();
        for (int i = AmountOfElems - 1; i >= 0; i--) {
            array.remove(i);
        }
        end = System.nanoTime();
        long resultForLinked = end - begin;
        begin = System.nanoTime();
        for (int i = AmountOfElems - 1; i >= 0; i--) {
            list.remove(i);
        }
        end = System.nanoTime();
        long resultForArray = end - begin;
        DataForStr(2, 1, resultForArray, iForColumn);
        DataForStr(2, 2, resultForLinked, iForColumn);
    }

    /**
     * Function GetCheck
     * It deletes elements in array and list also measures time for get operation
     */
    public void GetCheck(){
        if (array.size() < AmountOfElems || list.size() < AmountOfElems) AddTest();
        begin = System.nanoTime();
        for (int i = AmountOfElems - 1; i >= 0; i--) {
            array.get(i);
        }
        end = System.nanoTime();
        long resultForLinked = end - begin;
        begin = System.nanoTime();
        for (int i = AmountOfElems - 1; i >= 0; i--) {
             list.get(i);
        }
        end = System.nanoTime();
        long resultForArray = end - begin;
        DataForStr(3, 1, resultForArray, iForColumn);
        DataForStr(3, 2, resultForLinked, iForColumn);
    }

    /**
     * Method DataForStr
     * It adds record of one operation test
     *
     * @param op     - type of operation
     * @param type   - type of list
     * @param result - result of time test of operation
     * @param column - index of column in data array
     */
    private void DataForStr(int op, int type, long result, int column){
        if (op == 1) data[column][0] = "Add";
        if (op == 2) data[column][0] = "Delete";
        if (op == 3) data[column][0] = "Get";
        if (type == 1) data[column][1] = "Array List";
        if (type == 2) data[column][1] = "Linked List";
        data[column][2] = result + " ms";
        data[column][3] = Integer.toString(AmountOfElems);
        iForColumn++;
    }

    public static void main(String[] args){
        TimeChecker t = new TimeChecker();
        t.AddCheck();
        t.GetCheck();
        t.DeleteCheck();
        t.GetCheck();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                Create(t.data);
            }
        });
    }
}

