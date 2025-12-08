🚀 MiniCompilateur PHP Try/Catch
<div align="center">
https://img.shields.io/badge/Java-17+-orange
https://img.shields.io/badge/Interface-Swing-blue
https://img.shields.io/badge/Projet-Acad%C3%A9mique-green

Un compilateur minimaliste pour l'analyse de code PHP avec gestion d'exceptions

📥 Télécharger • 🖥️ Utilisation • 🏗️ Structure • 👥 Auteurs

</div>
📋 Présentation
MiniCompilateur PHP Try/Catch est un projet académique développé en Java qui implémente les bases de la compilation : analyse lexicale et syntaxique d'un sous-ensemble du langage PHP, avec une attention particulière aux structures try-catch.

🎯 Objectifs pédagogiques
Comprendre le fonctionnement d'un analyseur lexical

Implémenter un analyseur syntaxique descendant récursif

Créer une interface graphique utilisateur

Gérer les erreurs de compilation

✨ Fonctionnalités
🔤 Analyseur Lexical (AnalyseurLexical.java)
Découpe le code source en tokens (unités lexicales)

Reconnaît les éléments PHP : variables ($x), chaînes, nombres

Identifie les mots-clés : try, catch, Exception

Gère les opérateurs : ++, --, ==, !=, etc.

📐 Analyseur Syntaxique (AnalyseurSyntaxique.java)
Vérifie la structure grammaticale du code

Implémente une grammaire formelle définie

Détecte les erreurs syntaxiques avec messages clairs

Utilise l'analyse descendante récursive

🎨 Interface Graphique (Mainn.java)
Mainn est la classe principale de l'interface utilisateur :

Interface développée avec Java Swing

Deux zones de texte : saisie du code et affichage des résultats

Trois boutons d'action :

Analyse lexicale : Affiche la liste des tokens

Analyse syntaxique : Vérifie la grammaire

Effacer : Réinitialise les zones

Messages d'erreur en temps réel

Design simple et intuitif

🏗️ Structure du Projet
text
MiniCompilateur/
├── 📄 README.md                    # Ce fichier
├── 📄 LICENSE                      # Licence MIT
├── 📄 rapport.md                   # Rapport technique complet
├── ⚙️ .gitignore                   # Configuration Git
├── 📁 src/                         # Code source Java
│   ├── 🎨 Mainn.java               # INTERFACE GRAPHIQUE PRINCIPALE
│   ├── 🔤 AnalyseurLexical.java    # Analyseur lexical
│   ├── 📐 AnalyseurSyntaxique.java # Analyseur syntaxique
│   ├── 🏷️ Token.java              # Classe représentant un token
│   └── 📋 TokenType.java           # Types de tokens supportés
├── 📁 tests/                       # Cas de test
└── 📁 docs/                        # Documentation
📊 Classes principales
Classe	Rôle	Description
Mainn	Interface graphique	Point d'entrée de l'application, gestion de l'UI
AnalyseurLexical	Analyse lexicale	Transforme le code en tokens
AnalyseurSyntaxique	Analyse syntaxique	Vérifie la grammaire
Token	Données	Représente un token avec type, texte, ligne
TokenType	Énumération	Liste tous les types de tokens possibles
📥 Téléchargement
Option 1 : JAR exécutable (Recommandé)
bash
# Téléchargez le fichier JAR
java -jar MiniCompilateur.jar
Option 2 : Compilation manuelle
bash
# 1. Cloner le dépôt
git clone https://github.com/Rovi-za/MiniCompilateurphptrycatch.git

# 2. Compiler
cd MiniCompilateurphptrycatch
javac src/*.java

# 3. Exécuter
java -cp src Mainn
🖥️ Utilisation
Lancement de l'interface
L'interface Mainn se lance automatiquement :

bash
java -cp src Mainn
Étapes d'utilisation
Saisie du code : Écrivez ou collez du code PHP dans la zone supérieure

Analyse lexicale : Cliquez pour voir les tokens détectés

Analyse syntaxique : Cliquez pour vérifier la grammaire

Correction : Utilisez les messages d'erreur pour corriger le code

Exemple de code testable
php
// Test simple
$x = 10;

// Test avec bloc try-catch
try {
    $result = 100 / 0;
} catch (Exception $e) {
    echo "Division par zéro";
}

// Signature personnalisée
Oumansour Roza;
🧪 Tests
Le projet inclut plusieurs cas de test :

✅ Code valide
php
// Variables et affectations
$a = 5;
$b = $a + 3;

// Structures complexes
try {
    $x = (10 + 5) * 2;
} catch (Exception ex) {
    // Gestion d'erreur
}
❌ Code avec erreurs
php
// Erreur : point-virgule manquant
$x = 10

// Erreur : structure try-catch incorrecte
try {
    $y = 5
catch Exception  // '}' manquant
📚 Grammaire implémentée
Le compilateur vérifie cette grammaire :

text
Programme → (Instruction)* FIN_FICHIER
Instruction → Affectation | TryCatch | Signature
Affectation → VARIABLE ('=' Expression | '++' | '--') ';'
TryCatch → 'try' '{' Instructions '}' 'catch' '(' IdentException ')' '{' Instructions '}'
Signature → NOM PRENOM ';'
Expression → Terme (Opérateur Terme)*
👥 Auteurs
Rovi - Développement principal, interface graphique (Mainn)

Oumansour Roza - Mots-clés personnalisés, tests

Projet académique - Université [Nom de votre université]

Rôles spécifiques
Interface Mainn : Rovi - Développement complet de l'UI Swing

Analyseurs : Rovi & Oumansour Roza - Implémentation des algorithmes

Tests et validation : Oumansour Roza

Documentation : Équipe complète

🎓 Contexte académique
Ce projet a été réalisé dans le cadre du cours de Compilation et démontre :

Compétences acquises
✅ Conception d'analyseur lexical

✅ Implémentation d'analyseur syntaxique

✅ Développement d'interface graphique Java

✅ Gestion d'erreurs et tests

✅ Utilisation de Git/GitHub

Évaluation
Code source : Structuré et commenté

Interface Mainn : Fonctionnelle et intuitive

Analyseurs : Conformes à la grammaire définie

Documentation : Complète et professionnelle

🔧 Développement technique
Technologies utilisées
Langage : Java 17+

Interface : Java Swing (package javax.swing)

Structure : Programmation orientée objet

Gestion de version : Git/GitHub

Architecture
text
Code PHP → Interface Mainn → AnalyseurLexical → Tokens → AnalyseurSyntaxique → Résultats
                     ↑                                      ↑
                Saisie utilisateur                   Affichage résultats
📞 Support
Pour toute question ou problème :

Consultez le rapport technique

Vérifiez les issues GitHub

Contactez les auteurs

📄 Licence
Ce projet est sous licence MIT. Voir le fichier LICENSE pour plus de détails.

<div align="center">
🌟 L'interface Mainn vous permet de tester facilement votre code PHP !
Développé avec passion pour l'apprentissage de la compilation

</div>
****
