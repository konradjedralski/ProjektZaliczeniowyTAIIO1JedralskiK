package pl.application.to;

//Klasa reprezentująca pobieranie danych z tabeli t_uzytkownik
public class UzytkownikTo {

    private Long id;
    private String name;
    private String surname;
    private String description;
    private boolean edited;

    public UzytkownikTo() {
    }

    public UzytkownikTo(Long id, String name, String surname, String description, boolean edited) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.edited = edited;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }
    
   
}
