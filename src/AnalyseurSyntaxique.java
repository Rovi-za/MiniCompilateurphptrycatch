import java.util.ArrayList;
import java.util.List;

public class AnalyseurSyntaxique {

    private static List<Token> tokens;
    private static int i = 0;
    private static boolean err = false;
    private static String errMsg = "";
    private static List<String> erreurs = new ArrayList<>();

    public static void init(List<Token> tokensALire) {
        tokens = tokensALire;
        i = 0;
        err = false;
        errMsg = "";
        erreurs.clear();
    }

    public static List<String> getErreurs() {
        return erreurs;
    }

    public static boolean verifier(TokenType typeAttendu) {
        if (err) return false;
        if (i >= tokens.size()) return false;
        Token courant = tokens.get(i);
        if (courant.type == typeAttendu) {
            i++;
            return true;
        } else {
            return false;
        }
    }

    public static void erreur(String message) throws Exception {
        if (!err) {
            err = true;
            errMsg = message;
            erreurs.add(construireMessageErreur());
            throw new Exception(construireMessageErreur());
        }
    }

    public static String construireMessageErreur() {
        int index = (i < tokens.size()) ? i : tokens.size() - 1;
        if (index < 0) return "Erreur sur un token inexistant. Le code est peut-être vide.";
        Token t = tokens.get(index);
        String texte = (t.texte == null) ? "" : t.texte;
        return "ERREUR SYNTAXIQUE Ligne " + t.ligne + "\n"
                + "    Token trouvé : \"" + texte + "\"\n"
                + "    Attendu      : " + errMsg;
    }

    // ---------------------------
    // Z → S FIN_FICHIER
    // ---------------------------
    public static void Z() throws Exception {
        S();
        if (!verifier(TokenType.FIN_FICHIER)) {
            if (!err) recuperation("Fin de fichier (FIN_FICHIER) attendue");
        }
    }

    public static void S() throws Exception {
        if (err) return;
        while (i < tokens.size() && tokens.get(i).type != TokenType.FIN_FICHIER
                && tokens.get(i).type != TokenType.ACCOLADE_FERM) {
            Instruction();
            if (err) return;
        }
    }

    public static void Instruction() throws Exception {
        if (err) return;
        if (i >= tokens.size()) return;

        TokenType t = tokens.get(i).type;

        switch (t) {
            case VARIABLE:
                AffectationOuDeclaration();
                break;
            case TRY:
                TryCatch();
                break;
            case NOM:
            case PRENOM:
                Signature();
                break;
            case IF:
            case ELSE:
            case FOR:
            case WHILE:
                InstructionIgnoree();
                break;
            default:
                recuperation("Instruction inconnue");
        }
    }

    private static void AffectationOuDeclaration() throws Exception {
        if (err) return;
        i++; // consomme VARIABLE
        if (i >= tokens.size()) {
            recuperation("Fin de fichier inattendue après variable");
            return;
        }

        TokenType suivant = tokens.get(i).type;
        if (suivant == TokenType.EGAL) {
            i++;
            Expression();
            if (!verifier(TokenType.POINT_VIRGULE)) {
                recuperation("Il manque ';' après l'affectation");
            }
        } else if (suivant == TokenType.INCR || suivant == TokenType.DEC) {
            i++;
            if (!verifier(TokenType.POINT_VIRGULE)) {
                recuperation("Il manque ';' après l'opérateur");
            }
        } else {
            recuperation("Attendu '=', '++' ou '--' après la variable");
        }
    }

    public static void TryCatch() throws Exception {
        if (err) return;

        if (!verifier(TokenType.TRY)) recuperation("Mot-clé 'try' attendu");

        if (verifier(TokenType.ACCOLADE_OUV)) S();
        if (!verifier(TokenType.ACCOLADE_FERM)) recuperation("Il manque '}' fermante après try");

        if (!verifier(TokenType.CATCH)) recuperation("Mot-clé 'catch' attendu");
        if (!verifier(TokenType.PARENTHESE_OUV)) recuperation("Il manque '(' après catch");

        if (!verifier(TokenType.IDENTIFIANT) && !verifier(TokenType.VARIABLE)
                && !verifier(TokenType.EXCEPTION)) {
            recuperation("Identifiant d'exception attendu");
        }

        if (!verifier(TokenType.PARENTHESE_FERM)) recuperation("Il manque ')' après identifiant");
        if (verifier(TokenType.ACCOLADE_OUV)) S();
        if (!verifier(TokenType.ACCOLADE_FERM)) recuperation("Il manque '}' fermante après catch");
    }

    public static void Signature() throws Exception {
        if (err) return;
        if (!verifier(TokenType.NOM)) recuperation("Nom attendu pour la signature");
        if (!verifier(TokenType.PRENOM)) recuperation("Prénom attendu pour la signature");
        if (!verifier(TokenType.POINT_VIRGULE)) recuperation("Il manque ';' après la signature");
    }

    private static void InstructionIgnoree() {
        i++;
        while (i < tokens.size() && tokens.get(i).type != TokenType.POINT_VIRGULE
                && tokens.get(i).type != TokenType.ACCOLADE_FERM
                && tokens.get(i).type != TokenType.FIN_FICHIER) {
            i++;
        }
        if (i < tokens.size() && (tokens.get(i).type == TokenType.POINT_VIRGULE
                || tokens.get(i).type == TokenType.ACCOLADE_FERM)) i++;
    }

    public static void Expression() throws Exception {
        if (err) return;
        Terme();
        while (i < tokens.size()) {
            TokenType t = tokens.get(i).type;
            if (t == TokenType.PLUS || t == TokenType.MOINS || t == TokenType.MULT
                    || t == TokenType.DIV || t == TokenType.MOD) {
                i++;
                Terme();
            } else break;
        }
    }

    public static void Terme() throws Exception {
        if (err) return;
        if (i >= tokens.size()) {
            recuperation("Expression incomplète");
            return;
        }

        TokenType t = tokens.get(i).type;
        if (t == TokenType.NOMBRE || t == TokenType.CHAINE
                || t == TokenType.VARIABLE || t == TokenType.IDENTIFIANT) {
            i++;
        } else if (t == TokenType.PARENTHESE_OUV) {
            i++;
            Expression();
            if (!verifier(TokenType.PARENTHESE_FERM)) recuperation("Il manque ')' dans l'expression");
        } else {
            recuperation("Facteur attendu (NOMBRE, CHAINE, VARIABLE ou '(' )");
        }
    }

    private static void recuperation(String message) {
        int ligneCourante = (i < tokens.size()) ? tokens.get(i).ligne : -1;
        String texte = (i < tokens.size()) ? tokens.get(i).texte : "EOF";
        String msgErreur = "ERREUR SYNTAXIQUE Ligne " + ligneCourante
                + " : " + message + " (token=" + texte + ")";
        erreurs.add(msgErreur);
        err = true;

        // resynchronisation
        while (i < tokens.size()) {
            TokenType t = tokens.get(i).type;
            if (t == TokenType.POINT_VIRGULE || t == TokenType.ACCOLADE_FERM
                    || t == TokenType.FIN_FICHIER) {
                if (t == TokenType.POINT_VIRGULE || t == TokenType.ACCOLADE_FERM) i++;
                break;
            }
            i++;
        }
    }
}

