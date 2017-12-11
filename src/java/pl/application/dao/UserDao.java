package pl.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import pl.application.to.UserTo;

public class UserDao {

    protected static String SQL_SELECT_ALL = "select id,imie,nazwisko,opis from t_uzytkownik order by id";
    protected static String SQL_SELECT_MAX = "select max(id) from t_uzytkownik";
    protected static String SQL_UPDATE = "update t_uzytkownik set id=?,imie=?,nazwisko=?,opis=? where id=?";
    protected static String SQL_INSERT = "insert into t_uzytkownik (id,imie,nazwisko,opis) values (?,?,?,?)";
    protected static String SQL_DELETE = "delete from t_uzytkownik where id=?";

    Connection connection;

    public UserDao() {
        try {
            Context ctx = new InitialContext();
            DataSource datasource = (DataSource) ctx.lookup("java:comp/env/jdbc/bazaTestowaMSSQL");
            String name = datasource.toString();
            connection = datasource.getConnection();
        } catch (Exception ex) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            servletContext.log(servletContext.getContextPath() + ":" + ex.toString());
            facesContext.addMessage(null, new FacesMessage(ex.toString()));
        }
    }

    //Ustalanie wartości kolumny klucza głównego id, dla nowego wstawianego wiersza
    private Long getNextId() {
        try {
            ResultSet question = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(SQL_SELECT_MAX);
            question.first();
            long lMaxId = question.getLong(1) + 1;
            return lMaxId;
        } catch (Exception ex) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(ex.toString()));
            return null;
        }
    }

    public Long create(UserTo userTo) {
        Long userToId;
        try {
            userToId = getNextId();
            PreparedStatement question = connection.prepareStatement(SQL_INSERT);
            question.setLong(1, userToId);
            question.setString(2, userTo.getName());
            question.setString(3, userTo.getSurname());
            question.setString(4, userTo.getDescription());
            int changesNumber = question.executeUpdate();
            return userToId;
        } catch (Exception ex) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(ex.toString()));
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (Exception ex) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    facesContext.addMessage(null, new FacesMessage(ex.toString()));
                }
            }
        }
    }

    public Long delete(Long id) {
        try {
            PreparedStatement question = connection.prepareStatement(SQL_DELETE);
            question.setLong(1, id);
            int changesNumber = question.executeUpdate();
            return id;
        } catch (Exception ex) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(ex.toString()));
            return -1l;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (Exception ex) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    facesContext.addMessage(null, new FacesMessage(ex.toString()));
                }
            }
        }
    }

    public Long update(UserTo userTo) {
        try {
            PreparedStatement question = connection.prepareStatement(SQL_UPDATE);
            question.setLong(1, userTo.getId());
            question.setString(2, userTo.getName());
            question.setString(3, userTo.getSurname());
            question.setString(4, userTo.getDescription());
            question.setLong(5, userTo.getId());
            int changesNumber = question.executeUpdate();
            return userTo.getId();
        } catch (Exception ex) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(ex.toString()));
            return -1l;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (Exception ex) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    facesContext.addMessage(null, new FacesMessage(ex.toString()));
                }
            }
        }
    }

    public ArrayList<UserTo> allData() {
        try {
            ArrayList<UserTo> userToList = new ArrayList();
            ResultSet question = connection.prepareStatement(SQL_SELECT_ALL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery();
            boolean lineTrue = question.first();
            while (lineTrue) {
                userToList.add(new UserTo(question.getLong("id"), question.getString("imie"), question.getString("nazwisko"), question.getString("opis"), false));
                lineTrue = question.next();
            }
            return userToList;
        } catch (Exception ex) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(ex.toString()));
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (Exception ex) {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    facesContext.addMessage(null, new FacesMessage(ex.toString()));
                }
            }
        }
    }
}
