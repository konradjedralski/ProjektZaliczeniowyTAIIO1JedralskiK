package pl.application.controllers;

import java.util.ArrayList;
import java.util.List;
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
        userToList.add(new UserTo(1l, "", "", "", false));
        userToList.add(new UserTo(2l, "", "", "", false));
        userToList.add(new UserTo(3l, "", "", "", false));
    }

    public List<UserTo> getUserToList() {
        return userToList;
    }

    public void setUserToList(List<UserTo> userToList) {
        this.userToList = userToList;
    }

    //Przełączenie wiersza w tryb edycji
    public void editionRow(UserTo userTo) {
        int indexObject = userToList.indexOf(userTo);
        userToList.set(indexObject, userTo);
    }

    //Usunięcie aktualnego wiersza
    public void deleteRow(UserTo userTo) {
        int indexObject = userToList.indexOf(userTo);
        userToList.remove(indexObject);
    }

    //Dodanie nowego pustego wiersza
    public void addRow(UserTo userTo) {
        int indexObject = userToList.indexOf(userTo);
        int listSize = userToList.size();
        UserTo newRow = new UserTo(listSize + 1l, "", "", "", true);
        userToList.add(indexObject + 1, newRow);
    }
}
