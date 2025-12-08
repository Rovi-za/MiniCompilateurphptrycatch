
# Mini-Compilateur PHP Try/Catch

Un projet de compilateur simple qui effectue l'analyse lexicale et syntaxique du code PHP, spécifiquement axé sur les blocs try/catch et les constructions PHP de base.

## Description

Ce projet est un mini-compilateur développé en Java qui analyse le code PHP en se concentrant sur les structures de gestion des exceptions try/catch. Il fournit une interface graphique utilisateur (GUI) pour saisir du code PHP et affiche les jetons lexicaux ainsi que les résultats de l'analyse syntaxique.


## Fonctionnalités
- **Analyse Lexicale** : Tokenise le code PHP en unités significatives incluant :
  - Mots-clés (try, catch, if, else, for, while)
  - Variables (commençant par $)
  - Identifiants et jetons spéciaux (nom, prenom, Exception)
  - Opérateurs (arithmétiques, de comparaison, logiques)
  - Symboles (parenthèses, accolades, points-virgules)
  - Chaînes de caractères et nombres

- **Analyse Syntaxique** : Valide la structure grammaticale du code PHP
  - Validation des blocs try/catch
  - Structures de contrôle de flux (if, else, for, while)
  - Évaluation des expressions
  - Rapport d'erreurs avec numéros de ligne


- **Interface Graphique** : Interface conviviale basée sur Swing
  - Zone de saisie de code
  - Zone d'affichage des résultats
  - Boutons pour l'analyse lexicale et syntaxique
  - Mise en évidence des erreurs


## Structure du Projet

```
.
├── src/
│   ├── Mainn.java                  # Main application with GUI
│   ├── AnalyseurLexical.java       # Lexical analyzer
│   ├── AnalyseurSyntaxique.java    # Syntactic analyzer
│   ├── Token.java                  # Token class
│   ├── TokenType.java              # Token type enumeration
│   └── Rapport de Projet.pdf       # Project report
├── mini-compilateur-trycatch-php.jar  # Compiled executable
├── Rapport de Projet.pdf           # Project report (copy at root)
└── README.md                       #           # Ce fichier
```


## Comment

### Utilisation du fichier JAR (recommandé)
```bash
java -jar mini-compilateur-trycatch-php.jar
```

### Compilation depuis les sources
```bash
cd src
javac *.java
java Mainn
```

## Utilisation

1. Lancez l'application
2. Entrez du code PHP dans la zone de texte d'entrée
3. Cliquez sur "Analyse lexicale" pour effectuer l'analyse lexicale et voir les jetons
4. Cliquez sur "Analyse syntaxique" pour effectuer l'analyse syntaxique et valider la structure du code
5. Les erreurs éventuelles seront affichées en rouge en bas de la fenêtre
6. Utilisez le bouton "Effacer" pour vider les zones d'entrée et de sortie
## Exemple de Code PHP

```php
try {
@@ -80,15 +80,15 @@ try {
}
```

## Prérequis

- Java Runtime Environment (JRE) 8 or higher
- For compilation: Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE) 8 ou supérieur
- Pour la compilation : Java Development Kit (JDK) 8 ou supérieur

## Licence

See the `Licence` file for details.
Consultez le fichier `Licence` pour plus de détails.

## Auteur


Développé par Rovi 
