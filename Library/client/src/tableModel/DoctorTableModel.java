package tableModel;

import model.Book;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DoctorTableModel implements TableModel{
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private List<Book> books;

    public DoctorTableModel(List<Book> books){
        this.books = books;
    }


    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ID";
            case 1:
                return "Фамилия";
            case 2:
                return "Имя";
            case 3:
                return "Отчество";
            case 4:
                return "Логин";
            case 5:
                return "Личный телефон";
            case 6:
                return "Рабочий телефон";
            case 7:
                return "Должность";
            case 8:
                return "Кабинет";
            case 9:
                return "Участок";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Integer.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return String.class;
            case 5: return String.class;
            case 6: return String.class;
            case 7: return String.class;
            case 8: return String.class;
            case 9: return String.class;
            default: return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        switch (columnIndex){
            case 0:
                return book.getUserId();
            case 1:
                return book.getSurname();
            case 2:
                return book.getName();
            case 3:
                return book.getLastname();
            case 4:
                return book.getLogin();
            case 5:
                return book.getPhone();
            case 6:
                return book.getWork_phone();
            case 7:
                return book.getPost();
            case 8:
                return book.getRoom();
            case 9:
                return book.getDistrict();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    public void addRow(Book admin){
        books.add(admin);
    }
}
