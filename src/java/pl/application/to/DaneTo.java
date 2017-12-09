package pl.application.to;

//Klasa reprezentująca pobieranie danych z tabeli t_dane
public class DaneTo 
{
    private Long id;
    private String nr;
    private String title;
    private String description;
    private float amount;
    private boolean edited;

    public DaneTo() {
    }

    public DaneTo(Long id, String nr, String title, String description, float amount, boolean edited) {
        this.id = id;
        this.nr = nr;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.edited = edited;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }
    
    
}
