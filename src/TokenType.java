public enum TokenType {

    // Fin
    FIN_FICHIER,

    // Variables / Identifiants
    IDENTIFIANT,
    VARIABLE,
    NOM,
    PRENOM,

    // Nombres et chaînes
    NOMBRE,
    CHAINE,

    // Mots-clés PHP pour ton TP
    TRY,
    CATCH,
    EXCEPTION,
    IF,
    ELSE,
   FOR,
           WHILE,

    // Symboles
    PARENTHESE_OUV,
    PARENTHESE_FERM,
    ACCOLADE_OUV,
    ACCOLADE_FERM,
    POINT_VIRGULE,

    // Opérateurs arithmétiques
    PLUS,
    MOINS,
    MULT,
    DIV,
    MOD,
    INCR,      // ++
    DEC,       // --

    // Comparaison
    EGAL,
    EGAL_EGAL,
    DIFF,
    INF,
    SUP,
    INF_EGAL,
    SUP_EGAL,

    // Logique
    AND,       // &&
    OR,        // ||
    NON        // !
}




