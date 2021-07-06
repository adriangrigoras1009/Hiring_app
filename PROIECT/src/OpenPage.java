import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenPage extends JFrame {
    Application my_app;
    JTextField intrebare;
    ButtonGroup grup;
    public OpenPage(Application my_app, String titlu) {
        super(titlu);
        this.my_app = my_app;
        setSize(1400,800);
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        intrebare = new JTextField("Alegeti o pagina");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(1400, 800);
        panel.setLayout(new BoxLayout(panel,3));
        intrebare.setFont(new Font("Serif",Font.BOLD,70));
        intrebare.setSelectedTextColor(Color.darkGray);
        intrebare.setBackground(Color.BLUE);
        panel.add(intrebare);
        JRadioButton admin = new JRadioButton("Admin");;
        JRadioButton manager = new JRadioButton("Manager");
        JRadioButton profile = new JRadioButton("Profile");
        admin.setFont(new Font("Serif",Font.ITALIC,70));
        manager.setFont(new Font("Serif",Font.ITALIC,70));
        profile.setFont(new Font("Serif",Font.ITALIC,70));
        grup = new ButtonGroup();
        grup.add(admin);
        grup.add(manager);
        grup.add(profile);
        panel.add(admin);
        panel.add(manager);
        panel.add(profile);

        JButton click = new JButton("Login");
        click.setFont(new Font("Serif",Font.BOLD,40));
        click.setBackground(Color.BLUE);
        panel.add(click);
        add(panel);
        click.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(admin.isSelected()) {
                    dispose();
                    AdminPage admin = new AdminPage(my_app,"admin");
                    admin.setVisible(true);
                }
                else if(manager.isSelected()) {
                    dispose();
                    SelectManager manager = new SelectManager(my_app,"select");
                    manager.setVisible(true);
                }
                else if(profile.isSelected()) {
                    dispose();
                    ConsumersPage profil = new ConsumersPage(my_app,"profil");
                    profil.setVisible(true);
                }
            }
        });
    }

}
