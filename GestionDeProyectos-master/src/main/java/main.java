import javax.swing.SwingUtilities;

import GUI.LoginForm;

/**
 * Clase principal de la aplicación. Este es el punto de entrada al programa,
 * donde se inicializa la interfaz de usuario del formulario de inicio de sesión.
 */
public class main {
    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        });    
    }
}
