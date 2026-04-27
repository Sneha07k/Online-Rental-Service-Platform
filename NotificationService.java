package findmyroomie;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


 
public class NotificationService {


    private static final ExecutorService executor = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r, "NotificationThread");
        t.setDaemon(true); 
        return t;
    });

    
    public static void notify(String message) {

        executor.submit(() -> {

            SwingUtilities.invokeLater(() -> showToast(message));
        });
    }

    
    public static void notifyWithDelay(String message, int delayMs) {
        executor.submit(() -> {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException ignored) {}
            SwingUtilities.invokeLater(() -> showToast(message));
        });
    }

    
    private static void showToast(String message) {

        JWindow toast = new JWindow();
        toast.setAlwaysOnTop(true);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(94, 234, 141), 2),
                BorderFactory.createEmptyBorder(12, 16, 12, 16)
        ));

        JLabel icon = new JLabel("🔔");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));

        JLabel text = new JLabel("<html><body style='width:200px'>" + message + "</body></html>");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        panel.add(icon, BorderLayout.WEST);
        panel.add(text, BorderLayout.CENTER);

        toast.getContentPane().add(panel);
        toast.pack();


        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screen.width - toast.getWidth() - 20;
        int y = screen.height - toast.getHeight() - 60;
        toast.setLocation(x, y);
        toast.setVisible(true);


        executor.submit(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ignored) {}
            SwingUtilities.invokeLater(toast::dispose);
        });
    }

    
    public static void shutdown() {
        executor.shutdown();
    }
}


