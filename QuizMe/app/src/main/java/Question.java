import java.util.Map;

public class Question {
    private String texte_question;
    private Map<String, String> options;
    private String reponse_correcte;

    public Question() {
        // Constructeur par défaut nécessaire pour Firebase
    }

    public String getTexteQuestion() {
        return texte_question;
    }

    public void setTexteQuestion(String texte_question) {
        this.texte_question = texte_question;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public String getReponseCorrecte() {
        return reponse_correcte;
    }

    public void setReponseCorrecte(String reponse_correcte) {
        this.reponse_correcte = reponse_correcte;
    }
}
