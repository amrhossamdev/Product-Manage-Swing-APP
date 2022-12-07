import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Properties;

public class ProductDetails extends JPanel {

    private JPanel productPanel;
    private JTextField proName;
    private JTextField proPrice;
    private JTextField proDesc;
    private JButton addProductButton;
    private JFrame frame;

    public static void main(String[] args) {
        ProductDetails productDetails = new ProductDetails();
        productDetails.setVisible(true);
    }

    public ProductDetails() {
        frame = new JFrame();
        frame.setSize(new Dimension(350, 350));
        frame.add(productPanel);
        frame.setVisible(true);
        addProduct();
    }

    public void addProduct() {
        addProductButton.addActionListener(e -> {
            if (proName.getText().trim().isEmpty() || proDesc.getText().trim().isEmpty() || proPrice.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(productPanel, "Please fill all fields !");
                return;
            }
            insertProduct(proName.getText(), proDesc.getText(), Integer.valueOf(proPrice.getText().trim() + ""));
        });
    }

    private void insertProduct(String name, String description, Integer price) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gui?useTimezone=true&serverTimezone=UTC", "root", "");
            Statement st = connection.createStatement();

            String query = "INSERT INTO `product`(`name`, `description`, `price`) " +
                    "VALUES ('" + name + "','" + description + "','" + price + "')";
            // st.executeUpdate(query);
            int res = st.executeUpdate(query);
            if (res != 0) {
                JOptionPane.showMessageDialog(productPanel, "Product added successfully");
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
            connection.close();
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(productPanel, "There was error adding your product");
            sqlException.printStackTrace();
        }
    }

    private void createUIComponents() {

    }
}
