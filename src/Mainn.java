import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Mainn extends JFrame {

    private JTextArea inputArea;
    private JTextArea outputArea;
    private JLabel erreursLabel; // Label pour les erreurs

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Mainn().setVisible(true));
    }

    public Mainn() {
        setTitle("Mini-Compilateur Try/Catch — Rovi");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buildUI();
    }

    private void buildUI() {

        // Zone de saisie du code
        inputArea = new JTextArea();
        inputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane inputScroll = new JScrollPane(inputArea);
        inputScroll.setBorder(BorderFactory.createTitledBorder("Code PHP à analyser"));

        // Zone affichage des résultats
        outputArea = new JTextArea();
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        outputArea.setEditable(false);
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(BorderFactory.createTitledBorder("Résultats (tokens + analyse syntaxique)"));

        // Label pour les erreurs
        erreursLabel = new JLabel(" "); // vide au départ
        erreursLabel.setForeground(Color.RED);
        erreursLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Boutons
        JButton btnLex = new JButton("Analyse lexicale");
        JButton btnSyn = new JButton("Analyse syntaxique");
        JButton btnClear = new JButton("Effacer");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(btnLex);
        buttonPanel.add(btnSyn);
        buttonPanel.add(btnClear);

        // Disposition générale
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputScroll, outputScroll);
        splitPane.setDividerLocation(300);

        add(buttonPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(erreursLabel, BorderLayout.SOUTH); // ajout du JLabel

        // Actions
        btnLex.addActionListener(e -> runLexical());
        btnSyn.addActionListener(e -> runSyntax());
        btnClear.addActionListener(e -> {
            inputArea.setText("");
            outputArea.setText("");
            erreursLabel.setText(" ");
        });
    }

    private void runLexical() {
        outputArea.setText("");
        erreursLabel.setText(" "); // Réinitialiser le label
        String code = inputArea.getText();

        if (code.isBlank()) {
            JOptionPane.showMessageDialog(this, "Veuillez coller du code PHP.", "Avertissement", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            AnalyseurLexical lex = new AnalyseurLexical(code);
            List<Token> tokens = lex.scan();

            StringBuilder sb = new StringBuilder();
            sb.append("=== TOKENS ===\n");
            for (Token t : tokens) {
                sb.append(t).append("\n");
            }

            outputArea.setText(sb.toString());
            erreursLabel.setText("Analyse lexicale OK.");

        } catch (Exception ex) {
            erreursLabel.setText("Erreur lexicale : " + ex.getMessage());
        }
    }

    private void runSyntax() {
        outputArea.setText("");
        erreursLabel.setText(" "); // Réinitialiser le label
        String code = inputArea.getText();

        if (code.isBlank()) {
            JOptionPane.showMessageDialog(this, "Veuillez coller du code PHP.", "Avertissement", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            AnalyseurLexical lex = new AnalyseurLexical(code);
            List<Token> tokens = lex.scan();

            StringBuilder sb = new StringBuilder();
            sb.append("=== TOKENS ===\n");
            for (Token t : tokens) sb.append(t).append("\n");

            // Analyse syntaxique
            AnalyseurSyntaxique.init(tokens);
            try {
                AnalyseurSyntaxique.Z();
            } catch (Exception ignored) {
                // les erreurs sont stockées dans AnalyseurSyntaxique.getErreurs()
            }

            List<String> erreurs = AnalyseurSyntaxique.getErreurs();
            sb.append("\n=== ANALYSE SYNTAXIQUE ===\n");
            if (erreurs.isEmpty()) {
                sb.append("Aucune erreur : Analyse syntaxique OK.");
                erreursLabel.setText("Analyse syntaxique OK.");
            } else {
                for (String e : erreurs) sb.append(e).append("\n");
                erreursLabel.setText(erreurs.size() + " erreur(s) détectée(s) !");
            }

            outputArea.setText(sb.toString());

        } catch (Exception ex) {
            erreursLabel.setText("Erreur lexicale : " + ex.getMessage());
        }
    }
}
