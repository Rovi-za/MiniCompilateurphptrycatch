public class Token {
    public TokenType type;
    public String texte;
    public int ligne;

    public Token(TokenType type, String texte, int ligne) {
        this.type = type;
        this.texte = texte;
        this.ligne = ligne;
    }

    public String toString() {
        return type + " : \"" + texte + "\" (ligne " + ligne + ")";
    }
}


