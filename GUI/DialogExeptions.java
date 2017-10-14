package Snake.GUI;

import javax.swing.*;
import java.awt.*;

public class DialogExeptions {
    public static void Massage(String msg,String title){
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.WARNING_MESSAGE);
    }
    public static void CloseWindowMsg(String msg,String title)
    {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
        System.exit(1);
    }
}
