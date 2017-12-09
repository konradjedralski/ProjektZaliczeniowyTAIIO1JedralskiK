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

    private final String tableDaneViewAlias = "tableDaneViewAlias";
    private final String tableUzytkownikViewAlias = "tableUzytkownikViewAlias";
    private final String logoutWindowAlias = "logoutWindowAlias";

    public SessionController() {
    }

    public String getTableDaneViewAlias() {
        return tableDaneViewAlias;
    }
    
    public String getTableUzytkownikViewAlias() {
        return tableUzytkownikViewAlias;
    }
    
    public String getLogoutWindowAlias() {
        return logoutWindowAlias;
    }
}
