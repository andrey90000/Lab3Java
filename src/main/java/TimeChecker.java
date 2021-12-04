import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class TimeChecker
 * It`s evaluating time for operations such us addition,deletion,getting
 *
 * @author Usov Andrey
 */
public class TimeChecker extends SimpleTable {

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
    private final int AmountOfElems = 10000;

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
    public TimeChecker() {
        iForColumn = 0;
        data = new String[20][4];
    }

    /**
     * Function AddTest
     * It adds missing elements into array and list
     *
     * @param list   - list to test
     * @param amount - amount of elements for operation
     */
    private void AddTest(List<Integer> list, int amount) {
        for (int i = list.size(); list.size() < amount; i++) {
            list.add(i);
        }
    }

    /**
     * Function AddCheck
     * It adds elements into array and list also measures time for add operation
     *
     * @param list   - list to test
     * @param amount - amount of elements for operation
     * @param type   - type of list for output
     */
    public void AddCheck(List<Integer> list, int amount, int type) {
        begin = System.nanoTime();
        for (int i = 0; i < amount; i++) {
            list.add(i);
        }
        end = System.nanoTime();
        long result = end - begin;
        DataForStr(1, type, result, amount, iForColumn);
    }

    /**
     * Function DeleteCheck
     * It deletes elements in array and list also measures time for delete operation
     *
     * @param list   - list to test
     * @param amount - amount of elements for operation
     * @param type   - type of list for output
     */
    public void DeleteCheck(@NotNull List<Integer> list, int amount, int type) {

        if (list.size() < amount) AddTest(list, amount);
        begin = System.nanoTime();
        for (int i = amount - 1; i >= 0; i--) {
            list.remove(i);
        }
        end = System.nanoTime();
        long result = end - begin;

        DataForStr(2, type, result, amount, iForColumn);

    }

    /**
     * Function GetCheck
     * It deletes elements in array and list also measures time for get operation
     *
     * @param list   - list to test
     * @param amount - amount of elements for operation
     * @param type   - type of list for output
     */
    public void GetCheck(@NotNull List<Integer> list, int amount, int type) {
        if (list.size() < amount) AddTest(list, amount);

        begin = System.nanoTime();
        for (int i = amount - 1; i >= 0; i--) {
            list.get(i);
        }
        end = System.nanoTime();
        long result = end - begin;
        DataForStr(3, type, result, amount, iForColumn);
    }

    /**
     * Method DataForStr
     * It adds record of one operation test
     *
     * @param op     - type of operation
     * @param type   - type of list
     * @param result - result of time test of operation
     * @param amount - amount of elements in list
     * @param column - index of column in data array
     */
    private void DataForStr(int op, int type, long result, int amount, int column) {
        if (op == 1) data[column][0] = "Add";
        if (op == 2) data[column][0] = "Delete";
        if (op == 3) data[column][0] = "Get";
        if (type == 1) data[column][1] = "Array List";
        if (type == 2) data[column][1] = "Linked List";
        data[column][2] = result + " ns";
        data[column][3] = Integer.toString(amount);
        iForColumn++;
    }

    /**
     * Method Testing
     * Evaluates time for add, get, delete for ArrayList and Linked-list
     */
    public void Testing() {
        ArrayList<Integer> array = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= 3; i++) {
            AddCheck(array, i * AmountOfElems, 1);
            AddCheck(list, i * AmountOfElems, 2);
            GetCheck(array, i * AmountOfElems, 1);
            GetCheck(list, i * AmountOfElems, 2);
            DeleteCheck(array, i * AmountOfElems, 1);
            DeleteCheck(list, i * AmountOfElems, 2);
        }


    }

    public static void main(String[] args) {

        TimeChecker t = new TimeChecker();
        t.Testing();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                Create(t.data);
            }
        });
    }
}

