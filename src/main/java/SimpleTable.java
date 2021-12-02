import javax.swing.*;
import java.awt.*;

/**
 * Class SimpleTable
 * It's creating a table for class TimeChecker
 */
public class SimpleTable extends JFrame{
    /**
     * Field columnsHeader - initialize header for table
     */
    private static Object[] columnsHeader = new String[]{"Тип операции", "Тип массива",
            "Время выполнения", "Кол-во элементов"};

    /**
     * Method Create
     * It`s initializing table settings and data
     *
     * @param data - inputting data in table
     */
    public static void Create(String[][] data){
        JFrame frame = new JFrame("Подсчет времени операций");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTable table = new JTable(data, columnsHeader);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane);
        frame.setPreferredSize(new Dimension(450, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
