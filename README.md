# Mini-Compilateur PHP Try/Catch

A simple compiler project that performs lexical and syntactic analysis of PHP code, specifically focused on try/catch blocks and basic PHP constructs.

## Description

This project is a mini-compiler built in Java that analyzes PHP code with a focus on try/catch exception handling structures. It provides a graphical user interface (GUI) to input PHP code and displays both lexical tokens and syntactic analysis results.

## Features

- **Lexical Analysis**: Tokenizes PHP code into meaningful units including:
  - Keywords (try, catch, if, else, for, while)
  - Variables (starting with $)
  - Identifiers and special tokens (nom, prenom, Exception)
  - Operators (arithmetic, comparison, logical)
  - Symbols (parentheses, braces, semicolons)
  - Strings and numbers

- **Syntactic Analysis**: Validates the grammatical structure of PHP code
  - Try/catch block validation
  - Control flow structures (if, else, for, while)
  - Expression evaluation
  - Error reporting with line numbers

- **GUI Interface**: User-friendly Swing-based interface
  - Code input area
  - Results display area
  - Buttons for lexical and syntactic analysis
  - Error highlighting

## Project Structure

```
.
â”œâ”€â”€ src/
â”‚   â”œâ”€â”€ Mainn.java                  # Main application with GUI
â”‚   â”œâ”€â”€ AnalyseurLexical.java       # Lexical analyzer
â”‚   â”œâ”€â”€ AnalyseurSyntaxique.java    # Syntactic analyzer
â”‚   â”œâ”€â”€ Token.java                  # Token class
â”‚   â”œâ”€â”€ TokenType.java              # Token type enumeration
â”‚   â””â”€â”€ Rapport de Projet.pdf       # Project report
â”œâ”€â”€ mini-compilateur-trycatch-php.jar  # Compiled executable
â”œâ”€â”€ Rapport de Projet.pdf           # Project report (copy at root)
â””â”€â”€ README.md                       # This file
```

## How to Run

### Using the JAR file (recommended)
```bash
java -jar mini-compilateur-trycatch-php.jar
```

### Compiling from source
```bash
cd src
javac *.java
java Mainn
```

## Usage

1. Launch the application
2. Enter PHP code in the input text area
3. Click "Analyse lexicale" to perform lexical analysis and see the tokens
4. Click "Analyse syntaxique" to perform syntactic analysis and validate the code structure
5. Any errors will be displayed in red at the bottom of the window
6. Use "Effacer" button to clear the input and output areas

## Example PHP Code

```php
try {
    $x = 10;
    if ($x > 5) {
        $nom = "Test";
    }
} catch (Exception $e) {
    $prenom = "Error";
}
```

## Requirements

- Java Runtime Environment (JRE) 8 or higher
- For compilation: Java Development Kit (JDK) 8 or higher

## License

See the `Licence` file for details.

## Author

Developed by Rovi
