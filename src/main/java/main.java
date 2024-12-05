import javax.swing.*;
import GUI.LoginForm;

public class main {
    public static void main(String[] args) {

       SwingUtilities.invokeLater(() -> {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        });    
    }
}
