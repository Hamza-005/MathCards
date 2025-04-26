import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Enumeration;

// MainView class extends JFrame to create a GUI window
public class MainView extends JFrame {

    // Declare all the components and variables used in the class
    private JPanel contentPane, centerPanel, bottomPanel, inputsPanel, typePanel, factorPanel, timerPanel;
    private JTextField answerTf, pointsTf;
    private JLabel questionLabel;
    private JButton practiceBtn;
    private JButton exitBtn;
    private ButtonGroup factorBg, timerBg;
    private JCheckBox addCb, subtractCb, multiplyCb, divideCb;
    private Question currentQuestion;
    private int correctAnswers, totalQuestions;
    private CustomSpinnerModel model; //spinner model for timer
    private Timer timer; //timer that will set the spinner value
    private JSpinner spinner; //spinner that will show the time
    int currentMinutes, currentSeconds; //current time in minutes and seconds

    // Constructor of the MainView class
    public MainView() {
        // Set the default close operation to DISPOSE_ON_CLOSE
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set the title and background color of the JFrame
        setTitle("Math Cards");
        setBackground(Color.decode("#1e90ff"));
        setResizable(false);
        setBackground(SystemColor.info);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 956, 494);
        contentPane = new JPanel();
        contentPane.setBackground(Color.decode("#ffffe1"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Set the content pane and layout
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Call methods to create the top, bottom, and center panes
        createTopPane();
        createBottomPane();
        createCenterPane();

        // Call method to set the actions for the components
        actions();
    }

    // Method to set the question text
    public void setQuestion(String question) {
        questionLabel.setText("<html><p style='padding:30px;'>" + question + "</p></html>");
    }

    // Method to create the top pane
    public void createTopPane() {
        // Initialize the top panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#ffffe1"));
        contentPane.add(topPanel, BorderLayout.NORTH);

        // Create the labels and text fields
        JLabel lblAnswer = createCustomLabel("Answer", 80, 40);
        JLabel lblPoints = createCustomLabel("Points", 80, 40);
        answerTf = createTextField(250, 40, 30);
        pointsTf = createTextField(80, 40, 10);

        // Set the initial text of the points text field
        pointsTf.setText("0");
        pointsTf.setEditable(false);

        // Add the components to the top panel
        topPanel.add(lblAnswer);
        topPanel.add(answerTf);
        topPanel.add(lblPoints);
        topPanel.add(pointsTf);
    }

    // Method to create the bottom pane
    public void createBottomPane() {
        // Initialize the bottom panel
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.decode("#ffffe1"));
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        // Create the buttons
        practiceBtn = createButton("Start Practice", "#ffffff", "#1e90ff", "#0078d7");
        exitBtn = createButton("Exit", "#ffffff", "#a0a0a0", "#0078d7");

        // Add the buttons to the bottom panel
        bottomPanel.add(practiceBtn);
        bottomPanel.add(Box.createHorizontalStrut(80));
        bottomPanel.add(exitBtn);
    }

    // Method to create the center pane
    public void createCenterPane() {
        // Initialize the center panel
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.decode("#ffffe1"));
        contentPane.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Create the question label
        questionLabel = new JLabel();
        setQuestion("Math Cards");
        questionLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setBackground(Color.white);
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setOpaque(true);
        questionLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        questionLabel.setBorder(new LineBorder(Color.decode("#0078d7")));

        // Initialize the inputs panel
        inputsPanel = new JPanel();
        inputsPanel.setBackground(Color.decode("#ffffe1"));
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.X_AXIS));

        // Initialize the type, factor, and timer panels
        typePanel = createInputPanel();
        factorPanel = createInputPanel();
        timerPanel = createInputPanel();

        // Call methods to initialize the type, factor, and timer panels
        initTypePanel();
        initFactorPanel();
        initTimerPanel();

        // Add the components to the center panel
        centerPanel.add(questionLabel);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(inputsPanel);
        inputsPanel.add(Box.createHorizontalStrut(5));
        inputsPanel.add(typePanel);
        inputsPanel.add(Box.createHorizontalStrut(60));
        inputsPanel.add(factorPanel);
        inputsPanel.add(Box.createHorizontalStrut(60));
        inputsPanel.add(timerPanel);
        inputsPanel.add(Box.createHorizontalStrut(5));
    }

    // Method to create an input panel
    public JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#99b4d1"));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMaximumSize(new Dimension(250, 200));
        panel.setBorder(new LineBorder(Color.decode("#0078d7")));

        return panel;
    }

    // Method to initialize the type panel
    public void initTypePanel() {
        // Create the type label and checkboxes
        JLabel typeLabel = createCustomLabel("Type:", 80, 40);
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        addCb = new JCheckBox("Addition");
        subtractCb = new JCheckBox("Subtraction");
        multiplyCb = new JCheckBox("Multiplication");
        divideCb = new JCheckBox("Division");

        addCb.setBackground(Color.decode("#99b4d1"));
        subtractCb.setBackground(Color.decode("#99b4d1"));
        multiplyCb.setBackground(Color.decode("#99b4d1"));
        divideCb.setBackground(Color.decode("#99b4d1"));
        addCb.setFont(new Font("Tahoma", Font.PLAIN, 16));
        subtractCb.setFont(new Font("Tahoma", Font.PLAIN, 16));
        multiplyCb.setFont(new Font("Tahoma", Font.PLAIN, 16));
        divideCb.setFont(new Font("Tahoma", Font.PLAIN, 16));

        typePanel.add(typeLabel);
        typePanel.add(addCb);
        typePanel.add(subtractCb);
        typePanel.add(multiplyCb);
        typePanel.add(divideCb);
    }

    // Method to initialize the factor panel
    public void initFactorPanel() {
        // Create the factor label and radio buttons
        JLabel factorLabel = createCustomLabel("Factor:", 80, 40);
        factorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel gridPanel = new JPanel();
        gridPanel.setBackground(Color.decode("#99b4d1"));
        gridPanel.setLayout(new BoxLayout(gridPanel, BoxLayout.Y_AXIS));
        gridPanel.setMaximumSize(new Dimension(250, 200));
        gridPanel.setBorder(new LineBorder(Color.decode("#0078d7")));

        JPanel row1 = new JPanel();
        row1.setBackground(Color.decode("#99b4d1"));
        row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));
        row1.setMaximumSize(new Dimension(250, 40));

        JPanel row2 = new JPanel();
        row2.setBackground(Color.decode("#99b4d1"));
        row2.setLayout(new BoxLayout(row2, BoxLayout.X_AXIS));
        row2.setMaximumSize(new Dimension(250, 40));

        JPanel row3 = new JPanel();
        row3.setBackground(Color.decode("#99b4d1"));
        row3.setLayout(new BoxLayout(row3, BoxLayout.X_AXIS));
        row3.setMaximumSize(new Dimension(250, 40));

        JPanel row4 = new JPanel();
        row4.setBackground(Color.decode("#99b4d1"));
        row4.setLayout(new BoxLayout(row4, BoxLayout.X_AXIS));
        row4.setMaximumSize(new Dimension(250, 40));

        JRadioButton randomRb = new JRadioButton("Random");
        JRadioButton rb0 = new JRadioButton("0");
        JRadioButton rb1 = new JRadioButton("1");
        JRadioButton rb2 = new JRadioButton("2");
        JRadioButton rb3 = new JRadioButton("3");
        JRadioButton rb4 = new JRadioButton("4");
        JRadioButton rb5 = new JRadioButton("5");
        JRadioButton rb6 = new JRadioButton("6");
        JRadioButton rb7 = new JRadioButton("7");
        JRadioButton rb8 = new JRadioButton("8");
        JRadioButton rb9 = new JRadioButton("9");

        randomRb.setBackground(Color.decode("#99b4d1"));
        rb0.setBackground(Color.decode("#99b4d1"));
        rb1.setBackground(Color.decode("#99b4d1"));
        rb2.setBackground(Color.decode("#99b4d1"));
        rb3.setBackground(Color.decode("#99b4d1"));
        rb4.setBackground(Color.decode("#99b4d1"));
        rb5.setBackground(Color.decode("#99b4d1"));
        rb6.setBackground(Color.decode("#99b4d1"));
        rb7.setBackground(Color.decode("#99b4d1"));
        rb8.setBackground(Color.decode("#99b4d1"));
        rb9.setBackground(Color.decode("#99b4d1"));

        randomRb.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rb0.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rb1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rb2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rb3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rb4.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rb5.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rb6.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rb7.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rb8.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rb9.setFont(new Font("Tahoma", Font.PLAIN, 16));

        factorBg = new ButtonGroup();
        factorBg.add(randomRb);
        factorBg.add(rb0);
        factorBg.add(rb1);
        factorBg.add(rb2);
        factorBg.add(rb3);
        factorBg.add(rb4);
        factorBg.add(rb5);
        factorBg.add(rb6);
        factorBg.add(rb7);
        factorBg.add(rb8);
        factorBg.add(rb9);

        row1.add(randomRb);
        row1.add(rb0);
        row2.add(rb1);
        row2.add(rb2);
        row2.add(rb3);
        row3.add(rb4);
        row3.add(rb5);
        row3.add(rb6);
        row4.add(rb7);
        row4.add(rb8);
        row4.add(rb9);

        gridPanel.add(row1);
        gridPanel.add(row2);
        gridPanel.add(row3);
        gridPanel.add(row4);

        factorPanel.add(Box.createHorizontalStrut(20));
        factorPanel.add(factorLabel);
        factorPanel.add(gridPanel);
        factorPanel.add(Box.createHorizontalStrut(20));
    }

    // Method to initialize the timer panel
    public void initTimerPanel() {
        // Create the timer label, radio buttons, and spinner
        JLabel timerLabel = createCustomLabel("Timer:", 80, 40);
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        model = new CustomSpinnerModel();
        JRadioButton offRb = new JRadioButton("Off");
        JRadioButton countUpRb = new JRadioButton("Count Up");
        JRadioButton countDownRb = new JRadioButton("Count Down");

        offRb.setBackground(Color.decode("#99b4d1"));
        countUpRb.setBackground(Color.decode("#99b4d1"));
        countDownRb.setBackground(Color.decode("#99b4d1"));

        timerBg = new ButtonGroup();
        timerBg.add(offRb);
        timerBg.add(countUpRb);
        timerBg.add(countDownRb);

        offRb.setFont(new Font("Tahoma", Font.PLAIN, 16));
        countUpRb.setFont(new Font("Tahoma", Font.PLAIN, 16));
        countDownRb.setFont(new Font("Tahoma", Font.PLAIN, 16));

        spinner = new JSpinner(model);
        timerPanel.add(Box.createHorizontalStrut(20));
        timerPanel.add(timerLabel);
        timerPanel.add(offRb);
        timerPanel.add(countUpRb);
        timerPanel.add(countDownRb);
        timerPanel.add(spinner);
        timerPanel.add(Box.createHorizontalStrut(20));

        //change listener on off radio button
        offRb.addChangeListener(e -> {
            //if off radio button is selected
            if (offRb.isSelected()) {
                //stop the timer if it is running
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }

                //set off state of the spinner model
                spinner.setEnabled(false);
                model.setOffState();
            }
        });

        //change listener on count up radio button
        countUpRb.addChangeListener(e -> {
            //if count up radio button is selected
            if (countUpRb.isSelected()) {
                //set maximum time of the spinner model
                spinner.setEnabled(false);
                model.setMaxTime();
            }
        });

        //change listener on count down radio button
        countDownRb.addChangeListener(e -> {
            //if count down radio button is selected
            if (countDownRb.isSelected()) {
                spinner.setEnabled(true);
            }
        });
    }

    public void actions() {
        //set enter button action
        answerTf.addActionListener(e -> {
            //if practice button is "Stop Practice"
            if (practiceBtn.getText().equals("Stop Practice")) {
                //increase total questions
                totalQuestions++;

                //if answer is empty show error message
                if (answerTf.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter answer!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //if answer correct set points with correct answers
                if (answerTf.getText().equals(String.valueOf(currentQuestion.getAnswer()))) {
                    pointsTf.setText(String.valueOf(++correctAnswers));
                }

                //clear answer text field
                answerTf.setText("");

                //get type and factor
                String[] arr = typeAndFactor();
                String types = arr[0];
                char factor = arr[1].charAt(0);

                //generate new question
                currentQuestion = Question.generateQuestion(factor, types.toCharArray());

                //set updated question
                setQuestion(currentQuestion.toString());
            }
        });

        //set practice button action
        practiceBtn.addActionListener(e -> {
            //if practice button is "Start Practice"
            if (practiceBtn.getText().equals("Start Practice")) {
                //get type and factor
                String[] arr = typeAndFactor();
                String types = arr[0];
                char factor = arr[1].charAt(0);

                //if any of the type is not selected or factor is empty show error message
                if (types.isEmpty() || factor == ' ') {
                    JOptionPane.showMessageDialog(null, "Please select factor and type(s)!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //if model is not off then start timer (because if user select off radio button then timer will not start)
                if (!model.isOff()) {
                    timerAction();
                }

                //disable functionalities
                setFunctionalities(false);

                //practice button will be "Stop Practice"
                practiceBtn.setText("Stop Practice");

                //set new question by generating new question
                currentQuestion = Question.generateQuestion(factor, types.toCharArray());
                setQuestion(currentQuestion.toString());
            } else {
                //if practice button is "Stop Practice"

                //consider initial time as 0 : 0, first index is minutes and second index is seconds
                int[] difference = new int[]{0, 0};

                //if model is not off then stop timer and get the difference
                if (!model.isOff()) {
                    //get current time
                    String[] currentTime = spinner.getValue().toString().split(":");
                    currentMinutes = Integer.parseInt(currentTime[0]);
                    currentSeconds = Integer.parseInt(currentTime[1]);

                    //get difference
                    difference = model.getTimeDifference(currentMinutes, currentSeconds);

                    //stop timer
                    timer.stop();
                }

                //set off state of the spinner model
                model.setOffState();

                //enable functionalities
                setFunctionalities(true);

                //show result view
                new ResultView(totalQuestions, correctAnswers,
                        difference).setVisible(true);

                //set practice button to "Start Practice"
                practiceBtn.setText("Start Practice");
            }
        });
    }

    //timer action that will set the spinner value and stop the timer when spinner value is 00:00
    public void timerAction() {
        //get current time
        String[] time = model.getValue().toString().split(":");

        //set max time of the spinner model
        model.setMaxTime(Integer.parseInt(time[0]), Integer.parseInt(time[1]));

        //update spinner value each second
        timer = new Timer(1000, e -> {
            //get current time
            spinner.setValue(spinner.getNextValue());

            //if spinner value is 00:00 then stop the timer
            if ("00:00".equals(spinner.getValue().toString())) {
                timer.stop();
            }
        });

        //start the timer
        timer.start();
    }


    //create custom text field
    public JTextField createTextField(int width, int height, int col) {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tf.setPreferredSize(new Dimension(width, height));
        tf.setColumns(col);
        tf.setBorder(new LineBorder(Color.decode("#0078d7")));

        return tf;
    }

    // Method to create a custom label
    public JLabel createCustomLabel(String text, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(width, height));

        return label;
    }

    // Method to create a custom button
    public JButton createButton(String text, String foreground, String background, String border) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn.setForeground(Color.decode(foreground));
        btn.setBackground(Color.decode(background));
        btn.setBorder(new LineBorder(Color.decode(border)));
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(150, 40));

        return btn;
    }

    // Method to get the type and factor for the question
    public String[] typeAndFactor() {
        char factor = ' ';

        //go to each factor button group element and get the selected factor
        for (Enumeration<AbstractButton> buttons = factorBg.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            //if button is selected then set factor to the button text
            if (button.isSelected()) {
                factor = button.getText().charAt(0);
            }
        }

        //get the selected types
        String types = "";
        if (addCb.isSelected())
            types += "+";
        if (subtractCb.isSelected())
            types += "-";
        if (multiplyCb.isSelected())
            types += "*";
        if (divideCb.isSelected())
            types += "/";

        //return the types and factor
        return new String[]{types, String.valueOf(factor)};
    }

    // Method to enable or disable functionalities
    public void setFunctionalities(boolean enable) {
        addCb.setEnabled(enable);
        subtractCb.setEnabled(enable);
        multiplyCb.setEnabled(enable);
        divideCb.setEnabled(enable);

        for (Enumeration<AbstractButton> buttons = factorBg.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            button.setEnabled(enable);
        }

        for (Enumeration<AbstractButton> buttons = timerBg.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            button.setEnabled(enable);
        }
    }
}
