import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyPanel extends JPanel {
    private boolean projectsIdAreIntegerOnly = false;

    public MyPanel() {
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(400, 400));
        setLayout(new BorderLayout());

        // добавяне на лого
        ImageIcon imageIcon = new ImageIcon("src/files/logo-sirma-117-450x0.png");
        Image image = imageIcon.getImage();
        int width = 400; // desired width
        int height = 200; // desired height
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        add(imageLabel, BorderLayout.NORTH);

        //Добавяне на текст
        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to Sirma's Academy Pair Extractor.").append(System.lineSeparator()).append(System.lineSeparator())
                .append("Import file - press to import .cvs file").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Show Result - press to get \"Pair worked most together\"").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Exit - press to exit the app");
        resultTextArea.setText(sb.toString());
        resultTextArea.setFont(new Font("Thoma", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane, BorderLayout.CENTER);


        // Добавяне на checkbox

        JCheckBox showAllPairsCheckBox = new JCheckBox("Mark if Projects IDs are only numbers");
        setCheckBox(showAllPairsCheckBox);
        JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        checkboxPanel.add(showAllPairsCheckBox);
        add(checkboxPanel, BorderLayout.SOUTH);

        // добвяне на бутони
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        JButton importButton = new JButton("Import File");
        JButton showResultButton = new JButton("Show Result");
        JButton exitButton = new JButton("Exit");

        setExitButton(exitButton);
        setGetResultButton(resultTextArea, showResultButton);
        setImportButton(importButton);

        buttonPanel.add(importButton);
        buttonPanel.add(showResultButton);
        buttonPanel.add(exitButton);
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        add(buttonPanel, BorderLayout.WEST);
    }

    private void setCheckBox(JCheckBox showAllPairsCheckBox) {
        showAllPairsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectsIdAreIntegerOnly = showAllPairsCheckBox.isSelected();
                System.out.println(projectsIdAreIntegerOnly);
            }
        });
    }

    private void setImportButton(JButton importButton) {
        importButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    CSVReader.filePath = fileChooser.getSelectedFile().getPath();

                }
            }
        });
    }

    private void setGetResultButton(JTextArea resultTextArea, JButton showResultButton) {
        showResultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                List<Employee> uniqueEmployeeList = new ArrayList<>();
                CSVReader.getUniqueEmployeesFromCSV(uniqueEmployeeList);
                EmployeePairsFinder finder = new EmployeePairsFinder(uniqueEmployeeList);
                Team teamToPrint = finder.findTeamWithMostDaysWorkedTogether(projectsIdAreIntegerOnly);


                resultTextArea.setText(teamToPrint.toString());
            }
        });
    }

    private void setExitButton(JButton exitButton) {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}