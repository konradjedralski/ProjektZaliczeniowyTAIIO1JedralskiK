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
import pl.application.to.DataTo;

public class DataDao {

    protected static String SQL_SELECT_ALL = "select id,nr,tytul,opis,kwota from t_dane order by id";
    protected static String SQL_SELECT_MAX = "select max(id) from t_dane";
    protected static String SQL_UPDATE = "update t_dane set id=?,nr=?,tytul=?,opis=?,kwota=? where id=?";
    protected static String SQL_INSERT = "insert into t_dane (id,nr,tytul,opis,kwota) values (?,?,?,?,?)";
    protected static String SQL_DELETE = "delete from t_dane where id=?";

    Connection connection;

    public DataDao() {
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

    public Long create(DataTo dataTo) {
        Long dataToId;
        try {
            dataToId = getNextId();
            PreparedStatement question = connection.prepareStatement(SQL_INSERT);
            question.setLong(1, dataToId);
            question.setString(2, dataTo.getNr());
            question.setString(3, dataTo.getTitle());
            question.setString(4, dataTo.getDescription());
            question.setFloat(5, dataTo.getAmount());
            int changesNumber = question.executeUpdate();
            return dataToId;
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

    public Long update(DataTo dataTo) {
        try {
            PreparedStatement question = connection.prepareStatement(SQL_UPDATE);
            question.setLong(1, dataTo.getId());
            question.setString(2, dataTo.getNr());
            question.setString(3, dataTo.getTitle());
            question.setString(4, dataTo.getDescription());
            question.setFloat(5, dataTo.getAmount());
            question.setLong(6, dataTo.getId());
            int changesNumber = question.executeUpdate();
            return dataTo.getId();
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

    public ArrayList<DataTo> allData() {
        try {
            ArrayList<DataTo> userToList = new ArrayList();
            ResultSet question = connection.prepareStatement(SQL_SELECT_ALL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery();
            boolean lineTrue = question.first();
            while (lineTrue) {
                userToList.add(new DataTo(question.getLong("id"), question.getString("nr"), question.getString("tytul"), question.getString("opis"), question.getFloat("kwota"), false));
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
