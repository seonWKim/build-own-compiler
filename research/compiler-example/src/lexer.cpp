#include <string>
#include <vector>

enum Token {
    tok_eof = -1,

    tok_def = -2,
    tok_extern = -3,

    tok_identifier = -4,
    tok_number = -5
};

class ExprAST {
public:
    virtual ~ExprAST() = default;
};

// Expression class for numeric literals like 1.0
class NumberExprAST : public ExprAST {
    double Val;

public:
    NumberExprAST(double Val) : Val(Val) {}
};

// Expression class for referencing a variable
class VariableExprAST : public ExprAST {
    std::string Name;

public:
    VariableExprAST(const std::string &Name)
            : Name(Name) {}
};

// Expression class for a binary operator
class BinaryExprAST : public ExprAST {
    char Op;
    std::unique_ptr<ExprAST> LHS, RHS;;

public:
    BinaryExprAST(char Op, std::unique_ptr<ExprAST> LHS, std::unique_ptr<ExprAST> RHS)
            : Op(Op), LHS(std::move(LHS)),
              RHS(std::move(RHS)) {}
};

// Expression class for function calls
class CallExprAST : public ExprAST {
    std::string Callee;
    std::vector<std::unique_ptr<ExprAST>> Args;

public:
    CallExprAST(const std::string &Callee,
                std::vector<std::unique_ptr<ExprAST>> Args)
            : Callee(Callee), Args(std::move(Args)) {}
};

// Represents the "prototype" for a function
// Captures its name, its argument names (thus implicitly the number of arguments)
class PrototypeAST {
    std::string Name;
    std::vector<std::string> Args;

public:
    PrototypeAST(const std::string &Name, std::vector<std::string> Args)
            : Name(Name), Args(std::move(Args)) {}

    const std::string &getName() const { return Name; }
};

class FunctionAST {
    std::unique_ptr<PrototypeAST> Proto;
    std::unique_ptr<ExprAST> Body;

public:
    FunctionAST(std::unique_ptr<PrototypeAST> Proto, std::unique_ptr<ExprAST> Body)
            : Proto(std::move(Proto)), Body(std::move(Body)) {}
};

static std::string IdentifierStr;
static double NumVal;

static int gettok() {
    static int LastChar = ' ';

    while (isspace(LastChar)) {
        LastChar = getchar();

        if (isalpha(LastChar)) {
            IdentifierStr = LastChar;
            while (isalnum((LastChar = getchar()))) {
                IdentifierStr += LastChar;
            }

            if (IdentifierStr == "def") {
                return tok_def;
            }

            if (IdentifierStr == "extern") {
                return tok_extern;
            }

            return tok_identifier; // return tok_identifier if it's not "def" or "extern"
        }

        if (isdigit(LastChar) || LastChar == '.') {
            std::string NumStr;
            do {
                NumStr += LastChar;
                LastChar = getchar();
            } while (isdigit(LastChar) || LastChar == '.');

            NumVal = strtod(NumStr.c_str(), 0);
            return tok_number;
        }

        if (LastChar == '#') {
            do {
                LastChar = getchar();
            } while (LastChar != EOF && LastChar != '\n' && LastChar != '\r');

            if (LastChar != EOF) {
                return gettok();
            }
        }

        if (LastChar == EOF) {
            return tok_eof;
        }

        int ThisChar = LastChar;
        LastChar = getchar();
        return ThisChar;
    }
}