import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConsumersPage extends JFrame {
    Application my_app;
    public ConsumersPage(Application my_app, String titlu) {
        super(titlu);
        this.my_app = my_app;
        setSize(1400,800);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(),3));
        JTextField text = new JTextField("Alege tipul de utilizator");
        text.setMinimumSize(new Dimension(1400,400));
        text.setMaximumSize(new Dimension(1400,400));
        text.setBackground(Color.RED);
        text.setFont(new Font("Serif",Font.BOLD,50));
        text.setSelectedTextColor(Color.DARK_GRAY);
        getContentPane().add(text);
        ButtonGroup group = new ButtonGroup();
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(1400,400));
        panel.setMinimumSize(new Dimension(1400,400));
        panel.setBackground(Color.BLACK);
        JButton User = new JButton("User");
        JButton Recruiter = new JButton("Recruiter");
        JButton Employee = new JButton("Employee");
        panel.setLayout(new GridLayout(1,2));
        group.add(User);
        group.add(Recruiter);
        group.add(Employee);
        panel.add(User);
        panel.add(Recruiter);
        panel.add(Employee);
        getContentPane().add(panel);
        User.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ArrayList<User> users = my_app.users;
                ArrayList<String> users_mail = new ArrayList<String>();
                for(int i = 0; i < users.size(); i++)
                    users_mail.add(users.get(i).cv.getPersonal_datas().getEmail());
                Login_Consumer<User> log = new Login_Consumer(my_app,users,users_mail,"user");
                log.setVisible(true);
            }
        });
        Employee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ArrayList<Employee> employees = new ArrayList<Employee>();
                for(int i = 0; i < my_app.companies.size(); i++)
                    employees.addAll(my_app.companies.get(i).getAllEmployees());
                ArrayList<String> emp_mail = new ArrayList<String>();
                for(int i = 0; i < employees.size(); i++)
                    emp_mail.add(employees.get(i).cv.getPersonal_datas().getEmail());
                Login_Consumer<Employee> log = new Login_Consumer(my_app,employees,emp_mail,"employee");
                log.setVisible(true);
            }
        });
        Recruiter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ArrayList<Recruiter> recruiters = new ArrayList<Recruiter>();
                for(int i = 0; i < my_app.companies.size(); i++)
                    recruiters.addAll(my_app.companies.get(i).recruiters);
                ArrayList<String> rec_mail = new ArrayList<String>();
                for(int i = 0; i < recruiters.size(); i++)
                    rec_mail.add(recruiters.get(i).cv.getPersonal_datas().getEmail());
                Login_Consumer<Recruiter> log = new Login_Consumer(my_app,recruiters,rec_mail,"recruiter");
                log.setVisible(true);
            }
        });
    }
}
