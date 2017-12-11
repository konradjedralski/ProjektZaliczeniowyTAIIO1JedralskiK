package pl.application.controllers;

import java.util.ArrayList;
import java.util.List;
import pl.application.dao.DataDao;
import pl.application.to.DataTo;

/**
 *
 * @author Konrad
 *
 * Komponent zarządzany w faces-config
 */
public class ViewControllerData {

    List<DataTo> dataToList = new ArrayList();

    public ViewControllerData() {
        refleshData();
    }

    public List<DataTo> getDataToList() {
        return dataToList;
    }

    public void setDataToList(List<DataTo> dataToList) {
        this.dataToList = dataToList;
    }

    //Odświeżenie danych w tabeli
    public final void refleshData() {
        DataDao daneDao = new DataDao();
        List<DataTo> userToListLocal = daneDao.allData();
        if (userToListLocal != null) {
            dataToList.clear();
            dataToList = userToListLocal;
        }
    }

    //Zapisanie danych do bazy
    public void saveData(DataTo dataTo) {
        int indexObject = dataToList.indexOf(dataTo);
        dataToList.get(indexObject).setEdited(false);
        DataDao dataDao = new DataDao();
        if (dataDao.update(dataTo) != -1) {
            dataToList.set(indexObject, dataTo);
        }
    }

    //Przełączenie wiersza w tryb edycji
    public void editionRow(DataTo dataTo) {
        int indexObject = dataToList.indexOf(dataTo);
        dataToList.get(indexObject).setEdited(true);

    }

    //Usunięcie aktualnego wiersza i zapis do bazy
    public void deleteRow(DataTo dataTo) {
        int indexObject = dataToList.indexOf(dataTo);
        DataDao dataDao = new DataDao();
        if (dataDao.delete(dataTo.getId()) != -1) {
            dataToList.remove(indexObject);
        }
    }

    //Dodanie nowego pustego wiersza w trybie edycji i zapis do bazy
    public void addRow(DataTo dataTo) {
        int indexObject = dataToList.indexOf(dataTo);
        DataTo newRow = new DataTo(-1l, "", "", "", 0f, true);
        DataDao dataDao = new DataDao();
        Long id = dataDao.create(newRow);
        if (id != null) {
            newRow.setId(id);
            dataToList.add(indexObject + 1, newRow);
        }
    }
}
