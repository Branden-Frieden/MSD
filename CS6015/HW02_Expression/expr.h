//
// Created by Branden Frieden on 1/18/23.
//

#ifndef HW02_EXPRESSION_EXPR_H
#define HW02_EXPRESSION_EXPR_H

#include <iostream>
#include <string>


class Expr {
public:
    virtual bool equals(Expr *e) = 0;
};

class NumExpr : public Expr {
public:
    int val;
    NumExpr(int val) {
        this->val = val;
    }
    bool equals(Expr *e){
        NumExpr *n = dynamic_cast<NumExpr*>(e);
        if (n == nullptr)
            return false;
        else
            return (this->val == n->val);
    }
};

class AddExpr : public Expr {
public:
    Expr *lhs;
    Expr *rhs;
    AddExpr(Expr *lhs, Expr *rhs) {
        this->lhs = lhs;
        this->rhs = rhs;
    }
    bool equals(Expr *e) {
        AddExpr *a = dynamic_cast<AddExpr *>(e);
        if (a == nullptr)
            return false;
        else
            return (this->lhs->equals(a->lhs) &&
                    this->rhs->equals(a->rhs));
    }
};

class MultExpr : public Expr {
public:
    Expr *lhs;
    Expr *rhs;
    MultExpr(Expr *lhs, Expr *rhs) {
        this->lhs = lhs;
        this->rhs = rhs;
    }
    bool equals(Expr *e) {
        MultExpr *m = dynamic_cast<MultExpr *>(e);
        if (m == nullptr)
            return false;
        else
            return (this->lhs->equals(m->lhs) &&
                    this->rhs->equals(m->rhs));
    }
};

class VarExpr : public Expr {
public:
    std::string var;
    VarExpr(std::string variable) {
        this->var = variable;
    }
    bool equals(Expr *e){
        VarExpr *n = dynamic_cast<VarExpr*>(e);
        if (n == nullptr)
            return false;
        else
            return (this->var == n->var);
    }
};

#endif //HW02_EXPRESSION_EXPR_H
