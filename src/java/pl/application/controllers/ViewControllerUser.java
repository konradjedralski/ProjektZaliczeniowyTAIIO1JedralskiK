package pl.application.controllers;

import java.util.ArrayList;
import java.util.List;
import pl.application.dao.UserDao;
import pl.application.to.UserTo;

/**
 *
 * @author Konrad
 *
 * Komponent zarządzany w faces-config
 */
public class ViewControllerUser {

    List<UserTo> userToList = new ArrayList();

    public ViewControllerUser() {
        refleshData();
    }

    public List<UserTo> getUserToList() {
        return userToList;
    }

    public void setUserToList(List<UserTo> userToList) {
        this.userToList = userToList;
    }
    
    //Odświeżenie danych w tabeli
    public final void refleshData() {
        UserDao daneDao = new UserDao();
        List<UserTo> userToListLocal = daneDao.allData();
        if (userToListLocal != null) {
            userToList.clear();
            userToList = userToListLocal;
        }
    }

    //Przełączenie wiersza w tryb edycji i zapis do bazy
    public void editionRow(UserTo userTo) {
        int indexObject = userToList.indexOf(userTo);
        UserDao dataDao = new UserDao();
        if (dataDao.update(userTo) != -1) {
            userToList.set(indexObject, userTo);
        }
    }

     //Usunięcie aktualnego wiersza i zapis do bazy
    public void deleteRow(UserTo userTo) {
        int indexObject = userToList.indexOf(userTo);
        UserDao dataDao = new UserDao();
        if (dataDao.delete(userTo.getId()) != -1) {
            userToList.remove(indexObject);
        }
    }

    //Dodanie nowego pustego wiersza w trybie edycji i zapis do bazy
    public void addRow(UserTo userTo) {
        int indexObject = userToList.indexOf(userTo);
        int listSize = userToList.size();
        UserTo newRow = new UserTo(-1l, "", "", "", true);
        UserDao dataDao = new UserDao();
        Long id = dataDao.create(newRow);
        if (id != null) {
            newRow.setId(id);
            userToList.add(indexObject + 1, newRow);
        }
    }
}
