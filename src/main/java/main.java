import javax.swing.*;
import DBConeccion.SQLConeccion;

import GUI.LoginForm;
import Security.PasswordHasher;
import jakarta.validation.ConstraintViolationException;

public class main {
    public static void main(String[] args) {


 //    PasswordHasher passwordHasher = new PasswordHasher();
    // String s =passwordHasher.encodePassword("1234567");
  //   System.out.println(s);
        SwingUtilities.invokeLater(() -> {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        });
/////////////////////////////////////
/////////////////////////////////////
        ////////////////////////////SI VAN A HACER ALGUN CONTROLLER HAGANLO BOOLEANO PARA QUE SEAN MAS FACILES LOS TESTS
        /////////////////////////////////////
        /////////////////////////////////////
    }
}
