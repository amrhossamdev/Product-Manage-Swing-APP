import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JPanel {
    JButton searchButton;
    JButton addProductButton;
    JPanel Jpanel;

    public static void main(String[] args) {
        Home f = new Home();

        f.setVisible(true);

    }

    public Home() {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(350, 350));
        frame.add(Jpanel);
        frame.setVisible(true);
        addProductButton.addActionListener(e -> {
            JFrame product = new JFrame();
            product.setSize(new Dimension(350, 350));
            product.add(new ProductDetails());
            product.pack();
        });
        searchButton.addActionListener(e -> {
            JFrame product = new JFrame();
            product.setSize(new Dimension(350, 350));
            product.add(new ProductSearch());
            product.pack();
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
