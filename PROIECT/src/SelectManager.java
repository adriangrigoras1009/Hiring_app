import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Iterator;

public class SelectManager extends JFrame {
    Application my_app;
    ButtonGroup group;
    JTextField text;
    SelectManager(Application my_app, String titlu) {
        super(titlu);
        this.my_app = my_app;
        setSize(1400,800);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(),3));
        text = new JTextField("Alege compania",5);
        text.setPreferredSize(new Dimension(1400,400));
        text.setMaximumSize(new Dimension(1400,400));
        text.setMinimumSize(new Dimension(1400,400));
        text.setBackground(Color.RED);
        text.setFont(new Font("Serif",Font.BOLD,100));
        text.setSelectedTextColor(Color.DARK_GRAY);
        add(text);
        group = new ButtonGroup();
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        for(int i = 0; i < my_app.companies.size(); i++) {
            JRadioButton comp = new JRadioButton(my_app.companies.get(i).nume_companie);
            comp.setFont(new Font("Serif",Font.BOLD,50));
            group.add(comp);
            panel.add(comp);
            int index = i;
        }
        JButton click = new JButton("Manager log in");
        click.setMinimumSize(new Dimension(100,100));
        panel.add(click);
        add(panel);
        click.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Iterator butoane = group.getElements().asIterator();
                while(butoane.hasNext()) {
                    AbstractButton buton = (AbstractButton) butoane.next();
                    if (buton.isSelected()) {
                        String text = buton.getText();
                        for (int i = 0; i < my_app.companies.size(); i++) {
                            if (text.equals(my_app.companies.get(i).nume_companie)) {
                                ManagerPage manager = new ManagerPage(my_app, "manager", my_app.companies.get(i).manager);
                                manager.setVisible(true);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        });

    }
}
