package schoolHelper;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ui implements ActionListener {
    String[] weekdays = { "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag" };
    String [] times = {"7:30","8:15","8:15","9:00","9:20","10:05","10:05","10:50","11:10","11:55","11:55","12:40","13:00","13:45","13:45","14:30"};
    JFrame frame = new JFrame("StundenPlan");
    JPanel mainPanel;
    JPanel leftPanel;
    JPanel rightPanel;
    JPanel topPanel;
    JPanel bottomPanel;
    JButton saveButton;
    JComboBox<?> weekdayBox = new JComboBox<Object>(weekdays);
    JTextField[][] timeTable;
    JTextField[] subjectTable;
    String[] carry;
    private Timetable timetable;

    public void showWindow() {
        mainPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();
        topPanel = new JPanel();
        saveButton = new JButton("Speichern");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.getContentPane().add(mainPanel);
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        leftPanel.setPreferredSize(new Dimension((frame.getWidth() / 2) - 10, 1000));
        rightPanel.setPreferredSize(new Dimension((frame.getWidth() / 2) - 10, 1000));

        topPanel.add(weekdayBox);
        rightPanel.setLayout(new GridLayout(10, 1));
        leftPanel.setLayout(new GridLayout(10, 3));
        rightPanel.add(new JLabel("Fach"));
        leftPanel.add(new JLabel("Start"));
        leftPanel.add(new JLabel("Zeiten"));
        leftPanel.add(new JLabel("Ende"));
        bottomPanel.add(saveButton, BorderLayout.NORTH);

        timeTable = new JTextField[8][3];
        int timeIndex = 0;
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 3; column++) {
                if (column == 1) {
                    timeTable[row][column] = new JTextField("bis");
                    timeTable[row][column].setEditable(false);
                } else {
                    timeTable[row][column] = new JTextField(times[timeIndex]);
         timeIndex ++;
                    
                }
                leftPanel.add(timeTable[row][column]);
            }
        }
        
        subjectTable = new JTextField[8];
        for (int i = 0; i < 8; i++) {
            subjectTable[i] = new JTextField();
            rightPanel.add(subjectTable[i]);
        }



        saveButton.addActionListener(this);
        weekdayBox.addActionListener(this);
        timetable = Timetable.readFromFile();
        carry = timetable.getSubjectsForDay(0);
        for (int i = 0; i < carry.length; i++) {
            subjectTable[i].setText(carry[i]);
        }
        frame.setVisible(true);


    }

    
    public void actionPerformed(ActionEvent e) {
        int weekday = weekdayBox.getSelectedIndex();
        String[] subjects = new String[8];
        if(e.getSource() == saveButton){
            for (int i = 0; i < 8; i++) {
                subjects[i] = subjectTable[i].getText();
            }
            timetable.changeSubjectsForDay(weekday,subjects);
            Timetable.writeToFile(timetable);
        }else if(e.getSource() == weekdayBox){
            carry = timetable.getSubjectsForDay(weekdayBox.getSelectedIndex());
            for (int i = 0; i < carry.length; i++) {
                subjectTable[i].setText(carry[i]);
            }
        }

          
    }
}
