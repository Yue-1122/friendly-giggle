#include <iostream>
#include <cstdlib>
#include <vector>
#include <iomanip>
#include <limits>
using namespace std;
class Decimal;
class Integer
{
public:
	virtual void print() const = 0;
	virtual ~Integer() {}
};
class Fraction :public virtual Integer
{
public:
	int numerator;
	int denominator;
	void simplify();

	Fraction(int num, int deno);

	Fraction operator+(const Fraction&) const;
	Fraction operator-(const Fraction&) const;
	Fraction operator*(const Fraction&) const;
	Fraction operator/(const Fraction&) const;

	Fraction operator+(int n);
	Fraction operator-(int n);
	Fraction operator*(int n);
	Fraction operator/(int n);

	friend Fraction operator-(int n, Fraction& c);
	friend Fraction operator/(int n, Fraction& c);
	
	friend istream& operator>>(istream& in, Fraction&);
	friend ostream& operator<<(ostream& out, Fraction&);
	
	bool operator>(const Fraction&)const;
	bool operator<(const Fraction&)const;
	bool operator==(const Fraction&)const;
	bool operator!=(const Fraction&)const;
		
	bool operator>(int n) const;
	bool operator<(int n) const;
	bool operator==(int n) const;
	bool operator!=(int n) const;
		
	friend bool operator>(int n, const Fraction &f);
	friend bool operator<(int n, const Fraction &f);
	friend bool operator==(int n, const Fraction &f);
	friend bool operator!=(int n, const Fraction &f);
		
	friend Fraction decimalToFraction(const Decimal& d);

	Fraction operator+(const Decimal& d);
	Fraction operator-(const Decimal& d);
	Fraction operator*(const Decimal& d);
	Fraction operator/(const Decimal& d);

	void print() const;

	~Fraction() {}
};
void Fraction::simplify()
{
	int gcd = 1;
	for (int i = 1; i <= abs(numerator) && i <= abs(denominator); i++)
	{
		if (numerator % i == 0 && denominator % i == 0)
		{
			gcd = i;
		}
	}
	numerator = numerator / gcd;
	denominator = denominator / gcd;
}
Fraction::Fraction(int num = 0, int deno = 1)
{
	this->numerator = num;
	this->denominator = deno;
	simplify();
}
Fraction Fraction::operator+(const Fraction& other) const
{
	Fraction temp;
	if (denominator == other.denominator){
		temp.numerator = numerator + other.numerator;
		temp.denominator = denominator;
	}else{
		temp.numerator = numerator * other.denominator + denominator * other.numerator;
		temp.denominator = denominator * other.denominator;
	}
	temp.simplify();
	return temp;
}
Fraction Fraction::operator-(const Fraction& other) const
{
	Fraction temp;
	if (denominator == other.denominator){
		temp.numerator = numerator - other.numerator;
		temp.denominator = denominator;
	}else{
		temp.numerator = numerator * other.denominator - denominator * other.numerator;
		temp.denominator = denominator * other.denominator;
	}
	temp.simplify();
	return temp;
}
Fraction Fraction::operator*(const Fraction& other) const
{
	Fraction temp;
	temp.numerator = numerator * other.numerator;
	temp.denominator = denominator * other.denominator;
	temp.simplify();
	return temp;
}
Fraction Fraction::operator/(const Fraction& other) const
{
	Fraction temp;
	if (other.numerator == 0)
		cout << "denominator cannot be 0" << endl;
	else{
		temp.numerator = numerator * other.denominator;
		temp.denominator = denominator * other.numerator;
	}
	temp.simplify();
	return temp;
}
Fraction Fraction::operator+(int n)
{
	Fraction temp;
	temp.numerator = numerator + n * denominator;
	temp.denominator = denominator;
	temp.simplify();
	return temp;
}
Fraction Fraction::operator-(int n)
{
	Fraction temp;
	temp.numerator = numerator - n * denominator;
	temp.denominator = denominator;
	temp.simplify();
	return temp;
}
Fraction Fraction::operator*(int n)
{
	Fraction temp;
	temp.numerator = numerator * n;
	temp.denominator = denominator;
	temp.simplify();
	return temp;
}
Fraction Fraction::operator/(int n)
{
	Fraction temp;
	temp.numerator = numerator;
	temp.denominator = denominator * n;
	temp.simplify();
	return temp;
}
Fraction operator-(int n, Fraction& c)
{
	Fraction temp;
	temp.numerator = c.denominator * n - c.numerator;
	temp.denominator = c.denominator;
	temp.simplify();
	return temp;
}
Fraction operator/(int n, Fraction& c)
{
	Fraction temp;
	temp.numerator = n * c.denominator;
	temp.denominator = c.denominator;
	temp.simplify();
	return temp;
}
bool Fraction::operator>(const Fraction &other) const { 
	return (numerator * other.denominator) > (other.numerator * denominator); 
}
bool Fraction::operator<(const Fraction& other)const{
    return (numerator * other.denominator) < (other.numerator * denominator);
}
bool Fraction::operator==(const Fraction& other)const{
   return (numerator * other.denominator) == (other.numerator * denominator);
} 
bool Fraction::operator!=(const Fraction& other)const{
   return (numerator * other.denominator) != (other.numerator * denominator);
}
bool Fraction::operator>(int n) const { 
	return (numerator > n * denominator); 
}
bool Fraction::operator<(int n) const { 
	return (numerator < n * denominator); 
}
bool Fraction::operator==(int n) const { 
	return (numerator == n * denominator); 
}
bool Fraction::operator!=(int n) const { 
	return (numerator != n * denominator); 
}
bool operator>(int n, const Fraction &f){
    return (n * f.denominator > f.numerator);
}
bool operator<(int n, const Fraction &f){
    return (n * f.denominator < f.numerator);
}
bool operator==(int n, const Fraction &f){
    return (n * f.denominator == f.numerator);
}
bool operator!=(int n, const Fraction &f){
    return (n * f.denominator != f.numerator);
}
ostream& operator<<(ostream& out, const Fraction& f)
{
	if (f.numerator == 0){
		out << "0";
	}
	else if (f.denominator == 1){
		out << f.numerator;
	}
	else{
		out << f.numerator << "/" << f.denominator;
	}
	return out;
}
istream& operator>>(istream& in, Fraction& f)
{
	char ch;
	in >> f.numerator >> ch >> f.denominator;
	return in;
}
void Fraction::print() const
{
	if (numerator == 0)
		cout << "0";
	else if (denominator == 1)
		cout << numerator;
	else
		cout << numerator << '/' << denominator;
}
class Decimal :public Fraction,public virtual Integer
{
public:
	double value;

	Decimal(){}
	Decimal(double val);

	Decimal operator+(const Fraction& f) const;
	Decimal operator-(const Fraction& f) const;
	Decimal operator*(const Fraction& f) const;
	Decimal operator/(const Fraction& f) const;

	Decimal operator+(int n);
	Decimal operator-(int n);
	Decimal operator*(int n);
	Decimal operator/(int n);

	friend Decimal operator+(int n, const Decimal& d);
	friend Decimal operator-(int n, const Decimal& d);
	friend Decimal operator*(int n, const Decimal& d);
	friend Decimal operator/(int n, const Decimal& d);

    bool operator>(const Decimal &d) const;
    bool operator<(const Decimal &d) const;
    bool operator==(const Decimal &d) const;
    bool operator!=(const Decimal &d) const;
    
    bool operator>(int n) const;
    bool operator<(int n) const;
    bool operator==(int n) const;
    bool operator!=(int n) const;
    
    friend bool operator>(Fraction &f, const Decimal &d);
    friend bool operator<(Fraction &f, const Decimal &d);
    friend bool operator==(Fraction &f, const Decimal &d);
    friend bool operator!=(Fraction &f, const Decimal &d);
    
    friend bool operator>(int n, const Decimal &d);
    friend bool operator<(int n, const Decimal &d);
    friend bool operator==(int n, const Decimal &d);
    friend bool operator!=(int n, const Decimal &d);
    
	friend istream& operator>>(istream& in, Decimal& d);
	friend ostream& operator<<(ostream& out, Decimal& d);

	friend Fraction decimalToFraction(const Decimal& d);

	void print() const;

	~Decimal() {}
};
Fraction decimalToFraction(const Decimal& d)
{
	int precision = 1000000;
	int num = static_cast<int>(d.value * precision);
	int den = precision;
	int a = num, b = den, temp;
	while (b != 0) {
		temp = a % b;
		a = b;
		b = temp;
	}
	num /= a;
	den /= a;
	return Fraction(num, den);
}
Fraction Fraction::operator+(const Decimal& d)
{
	Fraction decimalAsFraction = decimalToFraction(d);
	int resultNum = numerator * decimalAsFraction.denominator + denominator * decimalAsFraction.numerator;
	int resultDenom = denominator * decimalAsFraction.denominator;
	return Fraction(resultNum, resultDenom);
}
Fraction Fraction:: operator-(const Decimal& d)
{
	Fraction decimalAsFraction = decimalToFraction(d);
	int resultNum = numerator * decimalAsFraction.denominator - denominator * decimalAsFraction.numerator;
	int resultDenom = denominator * decimalAsFraction.denominator;
	return Fraction(resultNum, resultDenom);
}
Fraction Fraction::operator*(const Decimal& d)
{
	Fraction decimalAsFraction = decimalToFraction(d);
	int resultNum = numerator * decimalAsFraction.numerator;
	int resultDenom = denominator * decimalAsFraction.denominator;
	return Fraction(resultNum, resultDenom);
}
Fraction Fraction::operator/(const Decimal& d)
{
	Fraction decimalAsFraction = decimalToFraction(d);
	int resultNum = numerator * decimalAsFraction.denominator;
	int resultDenom = denominator * decimalAsFraction.numerator;
	return Fraction(resultNum, resultDenom);
}
Decimal::Decimal(double val) : value(val) {}
Decimal Decimal:: operator+(const Fraction& f) const{
	return Decimal(value + (static_cast<double>(f.numerator) / f.denominator));
}
Decimal Decimal:: operator-(const Fraction& f) const{
	return Decimal(value - (static_cast<double>(f.numerator) / f.denominator));
}
Decimal Decimal:: operator*(const Fraction& f) const{
	return Decimal(value * (static_cast<double>(f.numerator) / f.denominator));
}
Decimal Decimal:: operator/(const Fraction& f) const{
	return Decimal(value / (static_cast<double>(f.numerator) / f.denominator));
}
Decimal Decimal:: operator+(int n){
	return Decimal(value + n);
}
Decimal Decimal:: operator-(int n){
	return Decimal(value - n);
}
Decimal Decimal:: operator*(int n){
	return Decimal(value * n);
}
Decimal Decimal:: operator/(int n){
	return Decimal(value / n);
}
Decimal operator+(int n, const Decimal& d){
	return Decimal(n + d.value);
}
Decimal operator-(int n, const Decimal& d){
	return Decimal(n - d.value);
}
Decimal operator*(int n, const Decimal& d){
	return Decimal(n * d.value);
}
Decimal operator/(int n, const Decimal& d){
	return Decimal(n / d.value);
}
bool Decimal:: operator>(const Decimal &d) const {
    return value > d.value;
} 
bool Decimal:: operator<(const Decimal &d) const {
    return value < d.value;
}
bool Decimal:: operator==(const Decimal &d) const {
    return value == d.value;
}
bool Decimal:: operator!=(const Decimal &d) const {
    return value != d.value;
}
bool Decimal:: operator>(int n) const {
    return value > n;
} 
bool Decimal:: operator<(int n) const {
    return value < n;
}
bool Decimal:: operator==(int n) const {
    return value == n;
}
bool Decimal:: operator!=(int n) const {
    return value != n;
}
bool operator>(const Fraction &f, const Decimal &d) {
    double fractionValue = static_cast<double>(f.numerator) / f.denominator;
    return fractionValue > d.value;
}
bool operator<(const Fraction &f, const Decimal &d) {
    double fractionValue = static_cast<double>(f.numerator) / f.denominator;
    return fractionValue < d.value;
}
bool operator==(const Fraction &f, const Decimal &d) {
    double fractionValue = static_cast<double>(f.numerator) / f.denominator;
    return fractionValue == d.value;
}
bool operator!=(const Fraction &f, const Decimal &d) {
    double fractionValue = static_cast<double>(f.numerator) / f.denominator;
    return fractionValue != d.value;
}
bool operator>(int n, const Decimal &d) {
    return n > d.value;
}
bool operator<(int n, const Decimal &d) {
    return n < d.value;
}
bool operator==(int n, const Decimal &d) {
    return n == d.value;
}
bool operator!=(int n, const Decimal &d) {
    return n != d.value;
}
istream& operator>>(istream& in, Decimal& d)
{
	in >> d.value;
	return in;
}
ostream& operator<<(ostream& out, Decimal& d)
{
	out << d.value;
	return out;
}
void Decimal::print() const{
	cout << value;
}
template <typename T, typename T1>
T add(T a, T1 b) {  return a + b; }
template <typename T, typename T1>
T subtract(T a, T1 b) {	return a - b; }
template <typename T, typename T1>
T multiply(T a, T1 b) {  return a * b; }
template <typename T, typename T1>
T divide(T a, T1 b) {
    if (b != 0) {
        return a / b;
    } else {
        return 0;
    }
}
void calculateResult1(const vector<Fraction>& fractions, const vector<char>& operators) 
{
    vector<Fraction> newFractions = fractions;
    vector<char> newOperators = operators;
    for (size_t i = 0; i < newOperators.size(); ++i)
    {
        if (newOperators[i] == '*' || newOperators[i] == '/')
        {
            if (newOperators[i] == '*')
            {
                newFractions[i] = newFractions[i] * newFractions[i + 1];
            }
            else
            {
                newFractions[i] = newFractions[i] / newFractions[i + 1];
            }
            newFractions.erase(newFractions.begin() + i + 1);
            newOperators.erase(newOperators.begin() + i);
            --i;
        }
    }
    if (newOperators.size() == 0 || newFractions.size() == 0) {
        cout << "没有输入操作符或分数" << endl;
        return;
    }
    Fraction result = newFractions[0];
    for (size_t i = 0; i < newOperators.size(); ++i)
    {
        if (newOperators[i] == '+')
        {
            result = result + newFractions[i + 1];
        }
        else
        {
            result = result - newFractions[i + 1];
        }
    }
    cout << "计算结果：";
    result.print();
	cout << endl;
}
void processInputAndCalculate1() {
    vector<Fraction> fractions;
    vector<char> operators;
    cout << "请输入多个分数和操作符，以回车结束输入：" << endl;
    while (true) {
        int n, d;
        char op,ch;
        while (cin >> n>> ch >> d) {
        fractions.push_back(Fraction(n,d));
        if (!(cin >> op) || op == '=') {
            break;
        }
        operators.push_back(op);
    	}
        if (cin.peek() == '\n') {
            break;
        }
    }
    if (fractions.size() != operators.size() + 1) {
        cout << "输入的分数和操作符数量不匹配" << endl;
        return;
    }
    calculateResult1(fractions, operators);
}
void calculateResult2(const vector<int>& integers, const vector<char>& operators) 
{
    vector<int> newIntegers = integers;
    vector<char> newOperators = operators;
    for (size_t i = 0; i < newOperators.size(); ++i) 
	{
        if (newOperators[i] == '*' || newOperators[i] == '/') 
		{
            if (newOperators[i] == '*') 
			{
                newIntegers[i] = newIntegers[i] * newIntegers[i + 1];
            } else 
			{
                newIntegers[i] = newIntegers[i] / newIntegers[i + 1];
            }
            newIntegers.erase(newIntegers.begin() + i + 1);
            newOperators.erase(newOperators.begin() + i);
            --i;
        }
    }
    int result = newIntegers[0];
    for (size_t i = 0; i < newOperators.size(); ++i) 
	{
        if (newOperators[i] == '+') 
		{
            result += newIntegers[i + 1];
        } else 
		{
            result -= newIntegers[i + 1];
        }
    }
    cout << "计算结果：" << result << endl;
}
void processInputAndCalculate2() {
    vector<int> integers;
    vector<char> operators;
    cout << "请输入多个整数和操作符，输入等号后以回车结束输入：" << endl;
    int n;
    char op;
    while (cin >> n) {
        integers.push_back(n);
        if (!(cin >> op)||op=='=') {
            break;
        }
        operators.push_back(op);
    }
    if (integers.size() != operators.size() + 1) {
        cout << "输入的整数和操作符数量不匹配" << endl;
        return;
    }
    calculateResult2(integers, operators);
}
void calculateResult3(const vector<double>& decimals, const vector<char>& operators) 
{
    vector<double> newDecimals = decimals;
    vector<char> newOperators = operators;
    for (size_t i = 0; i < newOperators.size(); ++i) {
        if (newOperators[i] == '*' || newOperators[i] == '/') {
            if (newOperators[i] == '*') {
                newDecimals[i] = newDecimals[i] * newDecimals[i + 1];
            } else {
                newDecimals[i] = newDecimals[i] / newDecimals[i + 1];
            }
            newDecimals.erase(newDecimals.begin() + i + 1);
            newOperators.erase(newOperators.begin() + i);
            --i;
        }
    }
    double result = newDecimals[0];
    for (size_t i = 0; i < newOperators.size(); ++i) {
        if (newOperators[i] == '+') {
            result += newDecimals[i + 1];
        } else {
            result -= newDecimals[i + 1];
        }
    }
    cout << "计算结果：" << result << endl;
}
void processInputAndCalculate3() {
    vector<double> decimals;
    vector<char> operators;
    cout << "请输入多个小数和操作符，输入等号后以回车结束输入：" << endl;
    while (true) {
        double n;
        char op;
        while(cin>>n)
        {
        	decimals.push_back(n);
            if (!(cin >> op) || op == '=') 
			{
            	break;
        	}
        	operators.push_back(op);
		}
        if (cin.peek() == '\n') {
            break;
        }
    }
    if (decimals.size() != operators.size() + 1) {
        cout << "输入的小数和操作符数量不匹配" << endl;
        return;
    }
    calculateResult3(decimals, operators);
}
int main()
{
	int select, pick, fz, fm, c;
	Fraction a, b, x;
	Decimal d,s;
	double f;
	cout << setw(20) << "分 数 计 算 器" << endl;
	while (1)
	{
		cout << setw(20) << "==========================" << endl;
		cout << setw(20) << "1.分数与分数的四则运算" << endl;
		cout << setw(20) << "2.整数与整数的四则运算" << endl;
		cout << setw(20) << "3.小数与小数的四则运算" << endl;
		cout << setw(20) << "4.分数与整数的四则运算" << endl;
		cout << setw(20) << "5.分数与小数的四则运算" << endl;
		cout << setw(20) << "6.整数与分数的四则运算" << endl;
		cout << setw(20) << "7.小数与分数的四则运算" << endl;
		cout << setw(20) << "8.小数与整数的四则运算" << endl;
		cout << setw(20) << "9.重新输入" << endl;
		cout << setw(20) << "0.退出" << endl;
		cout << setw(20) << "==========================" << endl;
		cout << setw(20) << "请输入菜单项" << endl;
		cin >> select;
		if (select == 9)
			break;
		else if (select == 0)
			return 0;
		else if (select == 1){
			processInputAndCalculate1();
		}
		else if (select == 2){
			processInputAndCalculate2();
		}
		else if (select == 3){
			processInputAndCalculate3();
		}
		else
			while (1)
			{
				cout << setw(20) << "==========================" << endl;
				cout << setw(20) << "1.加法运算" << endl;
				cout << setw(20) << "2.减法运算" << endl;
				cout << setw(20) << "3.乘法运算" << endl;
				cout << setw(20) << "4.除法运算" << endl;
				cout << setw(20) << "5.重新输入" << endl;
				cout << setw(20) << "0.退出" << endl;
				cout << setw(20) << "==========================" << endl;
				cout << setw(20) << "请输入菜单项" << endl;
				cin >> pick;
				cout << endl;
				if (pick == 5)
					break;
				if (pick == 0)
					return 0;
				switch (select)
				{
				case 4:
					cout  << "请输入分数：" ;
					while (1)
					{
						cin >> fz;
						getchar();
						cin >> fm;
						try{
							if (fm == 0)
								throw - 1; //抛出int类型异常
							else{
								break;
							}
						}catch (int){
							cout << setw(20) << "分母不能为0,请重新输入:";
						}
					}
					a = Fraction(fz, fm);
					cout << endl;
					cout << "请输入整数：";
					cin >> c;
					switch (pick)
					{
					case 1:
						x = a+c;
						cout << endl;
						cout << "相加结果为： ";
						x.print();
						cout << endl;
						break;
					case 2:
						x = a-c;
						cout << endl;
						cout << "相减结果为： ";
						x.print();
						cout << endl;
						break;
					case 3:
						x = a*c;
						cout << endl;
						cout << "相乘结果为： ";
						x.print();
						cout << endl;
						break;
					case 4:
						while (1)
						{
							try{
								if (c == 0)
									throw - 1; //抛出int类型异常
								else{
									break;
								}
							}catch (int){
								cout << setw(20) << "分母不能为0,请重新输入:";
							}
						}
						x = a/c;
						cout << endl;
						cout << "相除结果为： ";
						x.print();
						cout << endl;
						break;
					case 0:
						return 0;
					case 5:
						break;
					default:
						break;
					}
					break;
				case 5:
					cout  << "请输入分数：";
					while (1)
					{
						cin >> fz;
						getchar();
						cin >> fm;
						try{
							if (fm == 0)
								throw - 1; //抛出int类型异常
							else{
								break;
							}
						}catch (int){
							cout << setw(20) << "分母不能为0,请重新输入:";
						}
					}
					a = Fraction(fz, fm);
					cout << endl;
					cout << "请输入小数：";
					cin >> d;
					switch (pick)
					{
					case 1:
						x = a+d;
						cout << endl;
						cout << "相加结果为： ";
						x.print();
						cout << endl;
						break;
					case 2:
						x =a-d;
						cout << endl;
						cout << "相减结果为： ";
						x.print();
						cout << endl;
						break;
					case 3:
						x = a*d;
						cout << endl;
						cout << "相乘结果为： ";
						x.print();
						cout << endl;
						break;
					case 4:
						while (1)
						{
							try{
								if (d == 0)
									throw - 1; //抛出int类型异常
								else{
									break;
								}
							}catch (int){
								cout << setw(20) << "分母不能为0,请重新输入:";
							}
						}
						x = a/d;
						cout << endl;
						cout << "相除结果为： ";
						x.print();
						cout << endl;
						break;
					case 0:
						return 0;
					case 5:
						break;
					default:
						break;
					}
					break;
				case 6:
					cout  << "请输入整数：" ;
					cin >> c;
					cout  << "请输入分数:" ;
					while (1)
					{
						cin >> fz;
						getchar();
						cin >> fm;
						try{
							if (fm == 0)
								throw - 1; //抛出int类型异常
							else{
								break;
							}
						}catch (int){
							cout << setw(20) << "分母不能为0,请重新输入:";
						}
					}
					b = Fraction(fz, fm);
					cout << endl;
					switch (select)
					{
					case 1:
						x = b+c;
						cout << endl;
						cout << "相加结果为： ";
						x.print();
						cout << endl;
						break;
					case 2:
						x = c-b;
						cout << endl;
						cout << "相减结果为： ";
						x.print();
						cout << endl;
						break;
					case 3:
						x = b*c;
						cout << endl;
						cout << "相乘结果为： ";
						x.print();
						cout << endl;
						break;
					case 4:
						x = c/b;
						cout << endl;
						cout << "相除结果为： ";
						x.print();
						cout << endl;
						break;
					case 0:
						return 0;
					case 5:
						break;
					default:
						break;
					}
					break;
				case 7:
					cout << "请输入小数：";
					cin >> d;
					cout  << "请输入分数：";
					while (1)
					{
						cin >> fz;
						getchar();
						cin >> fm;
						try{
							if (fm == 0)
								throw - 1; //抛出int类型异常
							else{
								break;
							}
						}catch (int){
							cout << setw(20) << "分母不能为0,请重新输入:";
						}
					}
					a = Fraction(fz, fm);
					cout << endl;
					switch (pick)
					{
					case 1:
						x = a+d;
						cout << endl;
						cout << "相加结果为： ";
						x.print();
						cout << endl;
						break;
					case 2:
						s = d-a;
						cout << endl;
						cout << "相减结果为： ";
						s.print();
						cout << endl;
						break;
					case 3:
						x = a*d;
						cout << endl;
						cout << "相乘结果为： ";
						x.print();
						cout << endl;
						break;
					case 4:
						while (1)
						{
							try{
								if (a == 0)
									throw - 1; //抛出int类型异常
								else{
									break;
								}
							}
							catch (int){
								cout << setw(20) << "分母不能为0,请重新输入:";
							}
						}
						s = d/a;
						cout << endl;
						cout << "相除结果为： ";
						s.print();
						cout << endl;
						break;
					case 0:
						return 0;
					case 5:
						break;
					default:
						break;
					}
					break;
				case 8:
					 cout<<"请输入小数：";
					 cin>>f;
					 cout<<"请输入整数：";
					 cin>>c;
					 switch(pick)
					 {
					 	case 1:
					 		cout<<"相加结果为："<<add(f,c)<<endl;
					 	case 2:
					 		cout<<"相减结果为："<<subtract(f,c)<<endl;
					 	case 3:
					 		cout<<"相乘结果为："<<multiply(f,c)<<endl;
					 	case 4:
					 		cout<<"相除结果为："<<divide(f,c)<<endl;
					 	case 0:
					 		return 0;
					 	case 5:
					 		break;
					 	default:
					 		break;
					 }
				default:
					break;
				}
			}
		}
	return 0;
}
