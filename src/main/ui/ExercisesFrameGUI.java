package ui;

import model.*;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// A jframe for each exercise added
public class ExercisesFrameGUI extends JFrame implements WindowListener {
    private Day day;
    private WorkoutPlannerGUI workoutApp;
    private JLabel banner;
    private JPanel exercisePanel;
    JPanel scrollPanel;
    JButton addWorkoutButton;
    JButton backButton;


    // EFFECTS: creates a new frame that contains all that shows the current exercises, and takes in the day of the
    //          week, and the current workout planner app running.
    public ExercisesFrameGUI(Day day, WorkoutPlannerGUI workoutApp) {
        super("Workout Planner");
        this.day = day;
        this.workoutApp = workoutApp;
        this.banner = new JLabel("Exercises for " + day.getName());
        exercisePanel = new JPanel();
        scrollPanel = new JPanel();
        addWorkoutButton = new JButton("Add Workout");
        backButton = new JButton("Back");
        setSize(Constants.GUI_SIZE);
        banner.setBounds((Constants.GUI_SIZE.width - banner.getPreferredSize().width) / 2, 15,
                Constants.BANNER_SIZE.width, Constants.BANNER_SIZE.height);
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(this);
        createComponents();
    }

    // MODIFIES: this
    // EFFECTS: adds the components to the frame and processes the add workout button command
    public void createComponents() {
        exercisePanel.add(scrollPanel);
        for (Exercise exercise : day.getExercises()) {
            new ExercisePanelGUI(scrollPanel, exercise, day);
            for (Set set : exercise.getSets()) {
                new SetPanelGUI(scrollPanel, set);
            }
        }
        JScrollPane scrollPane = new JScrollPane(exercisePanel);
        scrollPane.setBounds(8, 70, Constants.EXERCISEPANEL_SIZE.width, Constants.EXERCISEPANEL_SIZE.height);
        scrollPane.setMaximumSize(Constants.EXERCISEPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        addWorkoutButton.addActionListener(e -> {
            setVisible(false);
            dispose();
            workoutApp.addWorkout(day);
        });
        addWorkoutButton.setBounds(-5, Constants.GUI_SIZE.height - 140, Constants.ADDEXERCISE.width,
                Constants.ADDEXERCISE.height);
        getContentPane().add(banner);
        getContentPane().add(scrollPane);
        getContentPane().add(addWorkoutButton);
        handleBack();
    }

    // MODIFIES: this
    // EFFECTS: handles the command for the back button
    public void handleBack() {
        backButton.setBounds(-5, Constants.GUI_SIZE.height - 80, Constants.ADDEXERCISE.width,
                Constants.ADDEXERCISE.height);
        backButton.addActionListener(e -> {
            setVisible(false);
            dispose();
            workoutApp.loadDays();
        });
        getContentPane().add(backButton);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
