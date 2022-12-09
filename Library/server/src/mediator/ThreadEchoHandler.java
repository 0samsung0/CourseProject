package mediator;

import db.DAO;
import model.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadEchoHandler implements Runnable{
    Socket clientSocket = null;
    ObjectInputStream input;
    ObjectOutputStream output;

    public ThreadEchoHandler(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            input = new ObjectInputStream(clientSocket.getInputStream());
            output = new ObjectOutputStream(clientSocket.getOutputStream());
        }
        catch(Exception e) {

        }
    }

    @Override
    public void run() {
        try {
            DAO dao = new DAO();
            String command = "";
            while(!command.equals("exit")){
                command = (String) input.readObject();
                switch (command){
                    case "authorization":
                        User authorizationUser = (User) input.readObject();
                        output.writeObject(dao.authorization(authorizationUser));
                        break;
                    case "getAllAdmins":
                        output.writeObject(dao.getAllAdmins());
                        break;
                    case "getAllUsers":
                        output.writeObject(dao.getAllUsers());
                        break;
                    case "getAllDoctors":
                        output.writeObject(dao.getAllBooks());
                        break;
                    case "getAllClients":
                        output.writeObject(dao.getAllClients());
                        break;
                    case "getRecordsSchedule":
                        Book book = (Book) input.readObject();
                        output.writeObject(dao.getRecordsSchedule(book));
                        break;
                    case "getAllVisits":
                        output.writeObject(dao.getAllVisits());
                        break;
                    case "getAllVisitsDoctor":
                        Book workdoctor = (Book) input.readObject();
                        output.writeObject(dao.getAllVisitsBooks(workdoctor));
                        break;
                    case "insertAdmin":
                        Admin newAdmin = (Admin) input.readObject();
                        output.writeObject(dao.addAdmin(newAdmin));
                        break;
                    case "insertUser":
                        User newUser = (User) input.readObject();
                        output.writeObject(dao.addUser(newUser));
                        break;
                    case "insertDoctor":
                        Book newBook = (Book) input.readObject();
                        output.writeObject(dao.addBook(newBook));
                        break;
                    case "insertClient":
                        Client newClient = (Client) input.readObject();
                        output.writeObject(dao.addClient(newClient));
                        break;
                    case "insertVisit":
                        Visits addVisit = (Visits) input.readObject();
                        output.writeObject(dao.addVisit(addVisit));
                        break;
                    case "updateMyUserData":
                        User updateMyUserData = (User) input.readObject();
                        output.writeObject(dao.updateMyUserData(updateMyUserData));
                        break;
                    case "updatePassword":
                        User updatePassword = (User) input.readObject();
                        output.writeObject(dao.updatePassword(updatePassword));
                        break;
                    case "updatePerson":
                        User updatePerson = (User) input.readObject();
                        output.writeObject(dao.updatePerson(updatePerson));
                        break;
                    case "updateAdmin":
                        Admin updateAdmin = (Admin) input.readObject();
                        output.writeObject(dao.updateAdmin(updateAdmin));
                        break;
                    case "updateUser":
                        User updateUser = (User) input.readObject();
                        output.writeObject(dao.updateUser(updateUser));
                        break;
                    case "updateBook":
                        Book updateBook = (Book) input.readObject();
                        output.writeObject(dao.updateBook(updateBook));
                        break;
                    case "updateClient":
                        Client updateClient = (Client) input.readObject();
                        output.writeObject(dao.updateClient(updateClient));
                        break;
                    case "deleteAdmin":
                        Admin deleteAdmin = (Admin) input.readObject();
                        output.writeObject(dao.deleteAdmin(deleteAdmin));
                        break;
                    case "deleteUser":
                        User deleteUser = (User) input.readObject();
                        output.writeObject(dao.deleteUser(deleteUser));
                        break;
                    case "deleteClient":
                        Client deletClient = (Client) input.readObject();
                        output.writeObject(dao.deleteClient(deletClient));
                        break;
                    case "deleteBook":
                        Book deleteBook = (Book) input.readObject();
                        output.writeObject(dao.deleteBook(deleteBook));
                        break;
                    case "getCheck":
                        Visits currentVisit = (Visits) input.readObject();
                        output.writeObject(dao.getCheck(currentVisit));
                        break;
                    }
            }
        }
        catch (Exception e) {
            System.out.println("Закрыто подключение...\nКоличество активных подключений: " + --Mediator.connectionsCounter + "\n");
        }
    }


}
