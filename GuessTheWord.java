import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class GuessTheWord extends Applet implements ActionListener {
    private final String[] words = {"computer", "java", "program", "language", "developer"};
    private String word;
    private int guesses;
    private String displayWord;
    private TextField inputField;
    private Label resultLabel;
    private Button guessButton;

    public void init() {
        word = words[(int) (Math.random() * words.length)];
        guesses = 6;
        displayWord = new String(new char[word.length()]).replace('\0', '_');
        Label promptLabel = new Label("Guess a letter: ");
        inputField = new TextField(1);
        inputField.addActionListener(this);
        guessButton = new Button("Guess");
        guessButton.addActionListener(this);
        resultLabel = new Label("");
        add(promptLabel);
        add(inputField);
        add(guessButton);
        add(resultLabel);
    }

    public void actionPerformed(ActionEvent e) {
        String letter = inputField.getText();
        if (letter.length() != 1) {
            resultLabel.setText("Please enter a single letter.");
        } else {
            boolean correct = false;
            String newDisplayWord = "";
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter.charAt(0)) {
                    newDisplayWord += letter.charAt(0);
                    correct = true;
                } else {
                    newDisplayWord += displayWord.charAt(i);
                }
            }
            if (!correct) {
                guesses--;
                resultLabel.setText("Incorrect. " + guesses + " guesses left.");
            } else {
                displayWord = newDisplayWord;
                if (displayWord.equals(word)) {
                    resultLabel.setText("You win!");
                    guessButton.setEnabled(false);
                } else {
                    resultLabel.setText("Correct. " + guesses + " guesses left.");
                }
            }
            inputField.setText("");
            repaint();
        }
    }

    public void paint(Graphics g) {
        g.drawString(displayWord, 10, 100);
    }
}