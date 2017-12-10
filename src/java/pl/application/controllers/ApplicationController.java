package pl.application.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author Konrad
 */
@ManagedBean
@ApplicationScoped
public class ApplicationController {

    String appVariable = "Version 1.0.0";

    public ApplicationController() {
    }

    public String getAppVariable() {
        return appVariable;
    }

    public void setAppVariable(String appVariable) {
        this.appVariable = appVariable;
    }

    
}
