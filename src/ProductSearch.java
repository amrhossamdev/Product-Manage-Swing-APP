import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Vector;

public class ProductSearch extends JPanel {
    private JPanel panel;
    private JPanel productPanel;
    private JTextField proName;
    private JTextField proPrice;
    private JTextField proDesc;
    private JButton searchButton;
    private JTextField proId;
    private JButton deleteBtn;
    private JFrame frame;

    public static void main(String[] args) {
        ProductSearch productSearch = new ProductSearch();
        productSearch.setVisible(true);

    }

    public ProductSearch() {
        frame = new JFrame();
        frame.setSize(new Dimension(350, 350));
        frame.add(panel);
        frame.setVisible(true);

        proName.setFocusable(false);
        proDesc.setFocusable(false);
        proPrice.setFocusable(false);
        searchButton.addActionListener(e -> searchProduct(Integer.valueOf(proId.getText())));
    }

    void deleteProduct(Integer id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gui?useTimezone=true&serverTimezone=UTC", "root", "");
            Statement st = connection.createStatement();

            String query = "DELETE  FROM `product` WHERE id = " + id + "";
            // st.executeUpdate(query);
            int res = st.executeUpdate(query);
            if (res != 0) {
                JOptionPane.showMessageDialog(productPanel, "Product deleted successfully");
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
            connection.close();
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(productPanel, "No product found");
            sqlException.printStackTrace();
        }
    }

    void searchProduct(Integer id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gui?useTimezone=true&serverTimezone=UTC", "root", "");
            Statement st = connection.createStatement();

            String query = "SELECT * FROM `product` WHERE id = " + id + "";

            ResultSet res = st.executeQuery(query);
//            System.out.println(res.next());
            if (!res.next()) {
                JOptionPane.showMessageDialog(productPanel, "No product found");
                return;
            }
            res.previous();
            while (res.next()) {
                int proId = res.getInt("id");
                String proNameRes = res.getString("name");
                String proDescRes = res.getString("description");
                Integer proPriceRes = Integer.valueOf(res.getString("price"));
                proName.setText(proNameRes);
                proDesc.setText(proDescRes);
                proPrice.setText(String.valueOf(proPriceRes));
                deleteBtn.addActionListener(e -> deleteProduct(proId));
            }
            connection.close();
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(productPanel, "No product found");
            sqlException.printStackTrace();
        }
    }

    private void createUIComponents() {

    }


}
