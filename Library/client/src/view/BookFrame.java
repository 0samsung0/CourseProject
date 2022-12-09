package view;

import model.*;
import org.jfree.data.category.DefaultCategoryDataset;
import tableModel.VisitTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BookFrame extends JFrame{
    private JPanel mainPanel;
    private JButton closeFrameButton;
    private JTextField mySurnameField;
    private JTextField myNameField;
    private JTextField myLastnameField;
    private JTextField myPhoneField;
    private JButton editMyPersonalDataButton;
    private JTextField myWorkPhoneField;
    private JTextField myLoginField;
    private JPasswordField myPasswordField1;
    private JPasswordField myPasswordField2;
    private JButton editMyAuthorizationDataButton;
    private JTextField myPostField;
    private JTextField myRoomField;
    private JTextField myDistrictField;
    private JLabel moIn;
    private JLabel moOut;
    private JLabel tuIn;
    private JLabel tuOut;
    private JLabel weIn;
    private JLabel weOut;
    private JLabel thIn;
    private JLabel thOut;
    private JLabel frIn;
    private JLabel frOut;
    private JLabel saIn;
    private JLabel saOut;
    private JLabel suIn;
    private JLabel suOut;
    private JTable visitsTable;
    private JButton statsButton;
    private JTabbedPane tabbedPane1;
    private ArrayList<Admin> admins = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();
    private ObjectOutputStream output = MainFrame.output;
    private ObjectInputStream input = MainFrame.input;
    private ArrayList<Visits> visits = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();
    private int USER_ID;

    //-------------------------------ИНИЦИАЛИЗАЦИЯ ФРЕЙМА-------------------------------


    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Врач");
        setContentPane(mainPanel);
        setResizable(false);
        readData();
        TableModel clientsModel = new VisitTableModel(visits, clients);
        visitsTable.setModel(clientsModel);
        pack();
        setLocationRelativeTo(null);
    }


    //-------------------------------КОНСТРУКТОР ФРЕЙМА-------------------------------


    public BookFrame(int user_id) {
        this.USER_ID = user_id;
        initComponents();
        editMyPersonalDataButton.addActionListener(e -> editMyPersonalDataActionPerformed());
        editMyAuthorizationDataButton.addActionListener(e -> editMyAuthorizationDataActionPerformed());
        closeFrameButton.addActionListener(e -> closeFrameActionPerformed());
        statsButton.addActionListener(e -> statsButtonActionPerformed());
    }

    //-------------------------------ВСПОМОГАТЕЛЬНЫЕ ФУНКЦИИ-------------------------------


    public void readData(){
        try{
            output.writeObject("getAllDoctors");
            this.books = (ArrayList<Book>) input.readObject();
            for(int i = 0; i < books.size(); i++){
                if(USER_ID == books.get(i).getUserId()){
                    Book book = books.get(i);
                    mySurnameField.setText(book.getSurname());
                    myNameField.setText(book.getName());
                    myLastnameField.setText(book.getLastname());
                    myPhoneField.setText(book.getPhone());
                    myWorkPhoneField.setText(book.getWork_phone());
                    myLoginField.setText(book.getLogin());
                    myPasswordField1.setText(book.getPassword());
                    myPasswordField2.setText(book.getPassword());
                    myPostField.setText(book.getPost());
                    myRoomField.setText(book.getRoom());
                    myDistrictField.setText(book.getDistrict());
                    moIn.setText(book.getSchedule()[0]);
                    moOut.setText(book.getSchedule()[1]);
                    tuIn.setText(book.getSchedule()[2]);
                    tuOut.setText(book.getSchedule()[3]);
                    weIn.setText(book.getSchedule()[4]);
                    weOut.setText(book.getSchedule()[5]);
                    thIn.setText(book.getSchedule()[6]);
                    thOut.setText(book.getSchedule()[7]);
                    frIn.setText(book.getSchedule()[8]);
                    frOut.setText(book.getSchedule()[9]);
                    saIn.setText(book.getSchedule()[10]);
                    saOut.setText(book.getSchedule()[11]);
                    suIn.setText(book.getSchedule()[12]);
                    suOut.setText(book.getSchedule()[13]);
                }
            }
            output.writeObject("getAllUsers");
            this.users = (ArrayList<User>) input.readObject();
            output.writeObject("getAllAdmins");
            this.admins = (ArrayList<Admin>) input.readObject();
            output.writeObject("getAllVisits");
            this.visits = (ArrayList<Visits>) input.readObject();
            output.writeObject("getAllClients");
            this.clients = (ArrayList<Client>) input.readObject();
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }


    //-------------------------------ФУНКЦИИ-СЛУШАТЕЛИ-------------------------------


    private Boolean checkLogin(String login) {
        if (login.equals("")) {
            JOptionPane.showMessageDialog(null, "Вы не ввели логин!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (login.length() <= 4 || login.length() >= 15) {
            JOptionPane.showMessageDialog(null, "Логин должен быть больше 4 и меньше 15 символов!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            for (int i = 0; i < admins.size(); i++) {
                if (login.equals(admins.get(i).getLogin())) {
                    JOptionPane.showMessageDialog(null, "Данный логин уже есть в системе!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            for (int i = 0; i < users.size(); i++) {
                if (login.equals(users.get(i).getLogin())) {
                    JOptionPane.showMessageDialog(null, "Данный логин уже есть в системе!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            for (int i = 0; i < books.size(); i++) {
                if (login.equals(books.get(i).getLogin())) {
                    JOptionPane.showMessageDialog(null, "Данный логин уже есть в системе!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            return true;
        }
    }

    private Boolean checkPassword(String password, String provePassword) {
        if(password.equals("") || provePassword.equals("")) {
            JOptionPane.showMessageDialog(null, "Вы не ввели пароль!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(password.length() <= 4 || password.length() >= 15) {
            JOptionPane.showMessageDialog(null, "Пароль должен быть больше 4 и меньше 15 символов!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(!password.equals(provePassword)){
            JOptionPane.showMessageDialog(null, "Пароль и его подтверждение не совпадают!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else return true;
    }

    private void editMyPersonalDataActionPerformed(){
        if(mySurnameField.isEditable()) {
            try {
                User user = new User();
                user.setId(USER_ID);
                user.setSurname(mySurnameField.getText());
                user.setName(myNameField.getText());
                user.setLastname(myLastnameField.getText());
                user.setPhone(myPhoneField.getText());
                output.writeObject("updatePerson");
                output.writeObject(user);
                String result = (String) input.readObject();
                JOptionPane.showMessageDialog(null, result, "Результат", JOptionPane.INFORMATION_MESSAGE);
                if (result.equals("Успешно сохранено!")) {
                    for (int i = 0; i < books.size(); i++) {
                        if (USER_ID == books.get(i).getUserId()) {
                            Book book = books.get(i);
                            book.setSurname(user.getSurname());
                            book.setName(user.getName());
                            book.setLastname(user.getLastname());
                            book.setPhone(user.getPhone());
                            books.set(i, book);
                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            mySurnameField.setEditable(false);
            myNameField.setEditable(false);
            myLastnameField.setEditable(false);
            myPhoneField.setEditable(false);
            editMyPersonalDataButton.setText("Редактировать личные данные");
        }
        else{
            mySurnameField.setEditable(true);
            myNameField.setEditable(true);
            myLastnameField.setEditable(true);
            myPhoneField.setEditable(true);
            editMyPersonalDataButton.setText("Сохранить");
        }
    }

    public void refreshData(){
        admins.clear();
        users.clear();
        books.clear();
        readData();
        //заблица
    }

    private void editMyAuthorizationDataActionPerformed(){
        if(myLoginField.isEditable()){
            if(!checkLogin(myLoginField.getText())) return;
            if(!checkPassword(myPasswordField1.getText(), myPasswordField2.getText())) return;
            try{
                ObjectOutputStream output = MainFrame.output;
                ObjectInputStream input = MainFrame.input;
                User user = new User();
                user.setId(USER_ID);
                user.setLogin(myLoginField.getText());
                user.setPassword(myPasswordField1.getText());
                user.setWork_phone(myWorkPhoneField.getText());
                output.writeObject("updateMyUserData");
                output.writeObject(user);
                String result = (String) input.readObject();
                JOptionPane.showMessageDialog(null, result, "Результат", JOptionPane.INFORMATION_MESSAGE);
                if (result.equals("Успешно сохранено!")) {
                    for (int i = 0; i < books.size(); i++) {
                        if (USER_ID == books.get(i).getUserId()) {
                            Book book = books.get(i);
                            book.setLogin(user.getLogin());
                            book.setWork_phone(user.getWork_phone());
                            books.set(i, book);
                        }
                    }
                }
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            myLoginField.setEditable(false);
            myPasswordField1.setEditable(false);
            myPasswordField2.setEditable(false);
            myWorkPhoneField.setEditable(false);
            editMyAuthorizationDataButton.setText("Редактировать данные авторизации");
        }
        else{
            myLoginField.setEditable(true);
            myPasswordField1.setEditable(true);
            myPasswordField2.setEditable(true);
            myWorkPhoneField.setEditable(true);
            editMyAuthorizationDataButton.setText("Сохранить");
        }
    }

    private void closeFrameActionPerformed(){
        new MainFrame().setVisible(true);
        dispose();
    }

    private void statsButtonActionPerformed(){
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        dataSet.setValue(clients.size(), "", "Количество клиентов");
        dataSet.setValue(visits.size(), "", "Количество записей на будущее время");
        MainFrame.createGraph(dataSet, "Статистика активности клиентов у текущего врача");
    }
}
