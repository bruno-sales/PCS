
package pkg;

public class Funcao {

    String strFuncao;
    int pos = -1;
    int c = 0;

    public Funcao(String strFuncao) {
        this.strFuncao = strFuncao;
    }

    void eatChar() {
        if (++pos < strFuncao.length()) {
            c = strFuncao.charAt(pos);
        } else {
            c = -1;
        }
    }

    void eatSpace() {
        while (Character.isWhitespace(c)) {
            eatChar();
        }
    }

    double parse() {
        eatChar();
        double v = parseExpression();
        if (c != -1) {
            throw new RuntimeException("Caractere inválido. Apenas funções de uma variável são aceitas: " + (char) c);
        }
        return v;
    }

        // Grammar:
    // expression = term | expression `+` term | expression `-` term
    // term = factor | term `*` factor | term `/` factor | term brackets
    // factor = brackets | number | factor `^` factor
    // brackets = `(` expression `)`
    double parseExpression() {
        double v = parseTerm();

        while (true) {
            eatSpace();
            if (c == '+') { // addition
                eatChar();
                v += parseTerm();
            } else if (c == '-') { // subtraction
                eatChar();
                v -= parseTerm();
            } else {
                return v;
            }
        }
    }

    double parseTerm() {
        double v = parseFactor();
        while (true) {
            eatSpace();
            if (c == '/') { // division
                eatChar();
                v /= parseFactor();
            } else if (c == '*' || c == '(') { // multiplication
                if (c == '*') {
                    eatChar();
                }
                v *= parseFactor();
            } else {
                return v;
            }
        }
    }

    double parseFactor() {
        double v;
        boolean negate = false;
        eatSpace();

        if (c == '+' || c == '-') { // unary plus & minus
            negate = c == '-';
            eatChar();
            eatSpace();
        }
        if (c == '(') { // brackets

            eatChar();
            v = parseExpression();
            if (c == ')') {
                eatChar();
            }

        } else { // numbers
            StringBuilder sb = new StringBuilder();

            while ((c >= '0' && c <= '9') || c == '.') {
                sb.append((char) c);
                eatChar();
            }

            if (sb.length() == 0) {
                throw new RuntimeException("Caractere inválido. Apenas funções de uma variável são aceitas: " + (char) c);
            }

            v = Double.parseDouble(sb.toString());
        }
        eatSpace();

        if (negate) {
            v = -v; 
        }
        if (c == '^') { // exponentiation
            eatChar();
            v = Math.pow(v, parseFactor());
        }

        return v;
    }

}
