package prdt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Authentification extends JFrame {
    // Déclaration des composants
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Connectage cn;

    public Authentification() {
        // Initialisation de la fenêtre d'authentification
        this.setTitle("Authentification");
        this.setSize(150, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(5,5));
        this.getContentPane().setBackground(new Color(250, 250, 250)); // Couleur de fond

        // Initialisation des composants
        JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
        JLabel passwordLabel = new JLabel("Mot de passe:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Se connecter");
        cn = new Connectage();

        // Ajout des composants à la fenêtre
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(new JLabel()); // Espace vide pour l'esthétique
        this.add(loginButton);

        // Couleurs des composants
        usernameLabel.setForeground(Color.DARK_GRAY);
        passwordLabel.setForeground(Color.DARK_GRAY);
        usernameField.setBackground(Color.WHITE);
        passwordField.setBackground(Color.WHITE);
        loginButton.setBackground(new Color(153, 153, 255));
        loginButton.setForeground(Color.WHITE);

        // Action du bouton de connexion
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Vérification des informations d'identification
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (checkCredentials(username, password)) {
                    // Authentification réussie, ouvrir la fenêtre principale
                    Produit_crud mainApp = new Produit_crud();
                    mainApp.setVisible(true);
                    dispose(); // Ferme la fenêtre d'authentification
                } else {
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect.", "Erreur d'authentification", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Méthode pour vérifier les informations d'identification
    private boolean checkCredentials(String username, String password) {
        try {
            Statement st = cn.laconnexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'");
            return rs.next(); // Si une ligne correspondante est trouvée, les informations d'identification sont valides
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // En cas d'erreur, considérer les informations d'identification comme invalides
        }
    }

    // Méthode principale pour démarrer l'application
    public static void main(String[] args) {
        Authentification auth = new Authentification();
        auth.setVisible(true);
    }
}
