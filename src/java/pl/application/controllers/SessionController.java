package pl.application.controllers;

import javax.faces.bean.ManagedBean;
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
}
