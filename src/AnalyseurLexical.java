import java.util.ArrayList;
import java.util.List;

public class AnalyseurLexical {

    private String code;
    private int position = 0;
    private int ligne = 1;
    private List<Token> tokens;

    private static final char FIN_FICHIER = '\0';

    public AnalyseurLexical(String code) {
        this.code = code;
        this.tokens = new ArrayList<>();
    }

    private char lireCar() {
        if (position >= code.length()) return FIN_FICHIER;
        return code.charAt(position);
    }

    private char suivCar() {
        if (position + 1 >= code.length()) return FIN_FICHIER;
        return code.charAt(position + 1);
    }

    public List<Token> scan() {

        char c = lireCar();

        while (c != FIN_FICHIER) {

            // ESPACES ET RETOURS LIGNE
            if (c == ' ' || c == '\t' || c == '\r') {
                position++;
            } else if (c == '\n') {
                ligne++;
                position++;
            }

            // STRINGS "...":
            else if (c == '"') {
                lireString();
            }

            // VARIABLES $x
            else if (c == '$') {
                lireVariable();
            }

            // MOTS : try, catch, Exception, identifiants, mots-clés personnalisés
            else if (estLettre(c)) {
                lireMot();
            }

            // NOMBRES
            else if (estChiffre(c)) {
                lireNombre();
            }

            // SYMBOLES ET OPERATEURS
           else {
    if (c == '(') ajouter(TokenType.PARENTHESE_OUV, "(");
    else if (c == ')') ajouter(TokenType.PARENTHESE_FERM, ")");
    else if (c == '{') ajouter(TokenType.ACCOLADE_OUV, "{");
    else if (c == '}') ajouter(TokenType.ACCOLADE_FERM, "}");
    else if (c == ';') ajouter(TokenType.POINT_VIRGULE, ";");

    // Comparaison
    else if (c == '=' && suivCar() == '=') { ajouter(TokenType.EGAL_EGAL, "=="); position++; }
    else if (c == '!' && suivCar() == '=') { ajouter(TokenType.DIFF, "!="); position++; }
    else if (c == '<' && suivCar() == '=') { ajouter(TokenType.INF_EGAL, "<="); position++; }
    else if (c == '>' && suivCar() == '=') { ajouter(TokenType.SUP_EGAL, ">="); position++; }
    else if (c == '=') ajouter(TokenType.EGAL, "=");
    else if (c == '<') ajouter(TokenType.INF, "<");
    else if (c == '>') ajouter(TokenType.SUP, ">");

    // Arithmétique
    else if (c == '+') {
        if (suivCar() == '+') { ajouter(TokenType.INCR, "++"); position++; }
        else ajouter(TokenType.PLUS, "+");
    }
    else if (c == '-') {
        if (suivCar() == '-') { ajouter(TokenType.DEC, "--"); position++; }
        else ajouter(TokenType.MOINS, "-");
    }
    else if (c == '*') ajouter(TokenType.MULT, "*");
    else if (c == '/') ajouter(TokenType.DIV, "/");
    else if (c == '%') ajouter(TokenType.MOD, "%");

    // Logique
    else if (c == '&' && suivCar() == '&') { ajouter(TokenType.AND, "&&"); position++; }
    else if (c == '|' && suivCar() == '|') { ajouter(TokenType.OR, "||"); position++; }
    else if (c == '!') ajouter(TokenType.NON, "!");

    else {
        System.out.println("Caractère inconnu : " + c + " ligne " + ligne);
    }

    position++;
}

            

            c = lireCar();
        }

        tokens.add(new Token(TokenType.FIN_FICHIER, "", ligne));
        return tokens;
    }

    // -----------------------
    // LECTEURS SIMPLES
    // -----------------------

    private void lireString() {
        int debut = ++position;
        while (lireCar() != '"' && lireCar() != FIN_FICHIER) position++;
        String valeur = code.substring(debut, position);
        ajouter(TokenType.CHAINE, valeur);
        if (lireCar() == '"') position++;
    }

 private void lireVariable() {
    int debut = position;
    position++; // saute le $
    while (estLettre(lireCar()) || estChiffre(lireCar())) position++;
    ajouter(TokenType.VARIABLE, code.substring(debut, position)); // <-- VARIABLE au lieu d'IDENTIFIANT
}

   private void lireMot() {
    int debut = position;
    while (estLettre(lireCar()) || estChiffre(lireCar())) position++; // Lire jusqu'à ce que ce ne soit plus lettre/chiffre
    String mot = code.substring(debut, position);

    // Vérifier le mot-clé exact
    switch (mot) {
        case "try": ajouter(TokenType.TRY, mot); break;
        case "catch": ajouter(TokenType.CATCH, mot); break;
        case "Exception": ajouter(TokenType.EXCEPTION, mot); break;
        case "if": ajouter(TokenType.IF, mot); break;
        case "else": ajouter(TokenType.ELSE, mot); break;
        default:
            // Mots personnalisés
            if (mot.equalsIgnoreCase("Oumansour")) ajouter(TokenType.NOM, mot);
            else if (mot.equalsIgnoreCase("Roza")) ajouter(TokenType.PRENOM, mot);
            else ajouter(TokenType.IDENTIFIANT, mot);
    }
}


    private void lireNombre() {
        int debut = position;
        while (estChiffre(lireCar())) position++;
        ajouter(TokenType.NOMBRE, code.substring(debut, position));
    }

    // -----------------------
    // OUTILS
    // -----------------------

    private boolean estLettre(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')|| c == '_';
    }

    private boolean estChiffre(char c) {
        return (c >= '0' && c <= '9');
    }

    private void ajouter(TokenType type, String texte) {
        tokens.add(new Token(type, texte, ligne));
    }
}
