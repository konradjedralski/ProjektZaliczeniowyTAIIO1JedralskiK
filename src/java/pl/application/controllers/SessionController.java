package pl.application.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Konrad
 */
@ManagedBean
@SessionScoped
public class SessionController {

    private final String tableDataViewAlias = "tableDataViewAlias";
    private final String tableUserViewAlias = "tableUserViewAlias";
    private final String logoutWindowAlias = "logoutWindowAlias";
    private final String mainWindowAlias = "mainWindowAlias";

    //Wstrzyknięcie ApplicationController do SessionController
    @ManagedProperty(value = "#{applicationController}")
    ApplicationController applicationControllerReference;

    public SessionController() {
    }

    public String getTableDataViewAlias() {
        return tableDataViewAlias;
    }

    public String getTableUserViewAlias() {
        return tableUserViewAlias;
    }

    public String getLogoutWindowAlias() {
        return logoutWindowAlias;
    }

    public String getMainWindowAlias() {
        return mainWindowAlias;
    }

    public void setApplicationControllerReference(ApplicationController applicationControllerReference) {
        this.applicationControllerReference = applicationControllerReference;
    }

    //Metoda odczytująca dane z wstrzyknietego komponentu modyfikująca wartość zmiennej
    public String getDataFromReference() {
        String appVariableModified = applicationControllerReference.appVariable;
        return "Konrad Jędralski: " + appVariableModified;
    }
}
