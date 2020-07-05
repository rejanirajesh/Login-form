import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;

//Login frame
class LoginDemo implements ActionListener {
    JFrame frame1;
    JLabel AdmID = new JLabel("ADMN. NO.");
    JLabel Pass = new JLabel("PASSWORD");
    JTextField Adm = new JTextField();
    JPasswordField Password = new JPasswordField();
    JButton Submit = new JButton("SUBMIT");
    JButton SignUp = new JButton("SIGN UP");

    LoginDemo() {
        createWindow();
        setLocationAndSize();
        addComponentsToFrame();
        actionEvent();
    }

    public void createWindow() {
        frame1 = new JFrame();
        frame1.setTitle("Login Page");
        frame1.setBounds(20, 80, 350, 300);
        frame1.getContentPane().setBackground(Color.lightGray);
        frame1.getContentPane().setLayout(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setResizable(false);
    }

    public void setLocationAndSize() {
        AdmID.setBounds(40, 40, 80, 70);
        Pass.setBounds(40, 80, 80, 70);
        Adm.setBounds(120, 60, 165, 23);
        Password.setBounds(120, 100, 165, 23);
        Submit.setBounds(70, 150, 80, 23);
        SignUp.setBounds(190, 150, 80, 23);
    }

    public void addComponentsToFrame() {
        frame1.add(AdmID);
        frame1.add(Pass);
        frame1.add(Adm);
        frame1.add(Password);
        frame1.add(Submit);
        frame1.add(SignUp);
    }

    public void actionEvent() {
        Submit.addActionListener(this);
        SignUp.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Submit) {
            try {
                String user = Adm.getText().trim();
                String pass = Password.getText().trim();
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Student", "Rejani", "password");
                Statement myStatement = conn.createStatement();
                String sql = "Select * from Student Where AdmID='" + user + "' and password='"+pass+ "'";
                ResultSet myRS = myStatement.executeQuery(sql);
                if( myRS.next()){
                    JOptionPane.showMessageDialog(null, "Successfully logged in!");
                } else {
                    JOptionPane.showMessageDialog(null, "User not found");
                }
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        else if (ae.getSource() == SignUp) {
            RegForm reg=new RegForm();
        }
    }
}
public class Login {
    public static void main(String arg[]) {
        new LoginDemo();
    }
}
//Registration form
class RegForm extends JFrame implements ActionListener {
    JFrame frame;
    String[] Sem = {"S1","S2","S3","S4","S5","S6","S7","S8"};
    String[] Branches = {"CS","EC","EEE","Mechanical","Civil"};
    JLabel AdmLabel=new JLabel("ADMN. NO.");
    JLabel FnameLabel=new JLabel("FIRST NAME");
    JLabel LnameLabel=new JLabel("LAST NAME");
    JLabel genderLabel=new JLabel("GENDER");
    JLabel SemLabel=new JLabel("SEMESTER");
    JLabel BranchLabel=new JLabel("BRANCH");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JLabel confirmPasswordLabel=new JLabel("CONFIRM PASSWORD");
    JLabel emailLabel=new JLabel("EMAIL");
    JTextField AdmTextField=new JTextField();
    JTextField FnameTextField=new JTextField();
    JTextField LnameTextField=new JTextField();
    String gender;
    JRadioButton Male=new JRadioButton("Male");
    JRadioButton Female=new JRadioButton("Female");
    JComboBox Semester =new JComboBox(Sem);
    JComboBox Branch = new JComboBox(Branches);
    JPasswordField passwordField=new JPasswordField();
    JPasswordField confirmPasswordField=new JPasswordField();
    JTextField emailTextField=new JTextField();
    JButton registerButton=new JButton("REGISTER");
    JButton resetButton=new JButton("RESET");


    RegForm()
    {
        createWindow();
        setLocationAndSize();
        addComponentsToFrame();
        actionEvent();
    }
    public void createWindow()
    {
        frame=new JFrame();
        frame.setTitle("Registration Form");
        frame.setBounds(20,80,450,550);
        frame.getContentPane().setBackground(Color.lightGray);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    public void setLocationAndSize()
    {
        AdmLabel.setBounds(40,40,80,70);
        FnameLabel.setBounds(40,80,80,70);
        LnameLabel.setBounds(40,120,80,70);
        genderLabel.setBounds(40,160,80,70);
        SemLabel.setBounds(40,200,80,70);
        BranchLabel.setBounds(40,240,80,70);
        passwordLabel.setBounds(40,280,100,70);
        confirmPasswordLabel.setBounds(40,320,140,70);
        emailLabel.setBounds(40,360,100,70);
        AdmTextField.setBounds(180,60,165,23);
        FnameTextField.setBounds(180,100,165,23);
        LnameTextField.setBounds(180,140,165,23);
        Male.setBounds(180,180,60,23);
        Female.setBounds(260,180,70,23);
        Semester.setBounds(180,220,165,23);
        Branch.setBounds(180,260,165,23);
        passwordField.setBounds(180,300,165,23);
        confirmPasswordField.setBounds(180,340,165,23);
        emailTextField.setBounds(180,380,165,23);
        registerButton.setBounds(70,420,100,35);
        resetButton.setBounds(220,420,100,35);
    }
    public void addComponentsToFrame()
    {
        frame.add(AdmLabel);
        frame.add(FnameLabel);
        frame.add(LnameLabel);
        frame.add(genderLabel);
        frame.add(SemLabel);
        frame.add(BranchLabel);
        frame.add(passwordLabel);
        frame.add(confirmPasswordLabel);
        frame.add(emailLabel);
        frame.add(AdmTextField);
        frame.add(FnameTextField);
        frame.add(LnameTextField);
        frame.add(Male);
        frame.add(Female);
        frame.add(Semester);
        frame.add(Branch);
        frame.add(passwordField);
        frame.add(confirmPasswordField);
        frame.add(emailTextField);
        frame.add(registerButton);
        frame.add(resetButton);
    }
    public void actionEvent()
    {
        registerButton.addActionListener(this);
        resetButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        try {
            if (passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText())){
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Student", "Rejani", "password");
                PreparedStatement str = connection.prepareStatement("insert into student values(?,?,?,?,?,?,?,?)");
                str.setString(1, AdmTextField.getText());
                str.setString(2, FnameTextField.getText());
                str.setString(3, LnameTextField.getText());
                if(Male.isSelected()){
                    gender="M";
                }
                else if(Female.isSelected()) {
                    gender = "F";
                }
                str.setString(4, gender);
                str.setString(5, Semester.getSelectedItem().toString());
                str.setString(6, Branch.getSelectedItem().toString());
                str.setString(7, passwordField.getText());
                str.setString(8, emailTextField.getText());
                //Executing query
                str.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Registered Successfully");
            }
            else {
                JOptionPane.showMessageDialog(null, "password did not match");
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        if(e.getSource()==resetButton)
        {
            //Clearing Fields
            AdmTextField.setText("");
            FnameTextField.setText("");
            LnameTextField.setText("");
            Male.isSelected();
            Semester.setSelectedItem("S1");
            Branch.setSelectedItem("CS");
            passwordField.setText("");
            confirmPasswordField.setText("");
            emailTextField.setText("");
        }

    }
}
