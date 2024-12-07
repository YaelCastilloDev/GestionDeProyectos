import javax.swing.SwingUtilities;

import DAO.Estudiante.Utils;
import GUI.LoginForm;
import models.Estudiante;
import jakarta.validation.ConstraintViolationException;

public class main {
    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        });    
    }
}
