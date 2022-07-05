#include <iostream>
#include <vector>
#include <fstream>
#include <string>

using namespace std;

class Base {
	int* ptr;
public:
	Base() { ptr = new int(100); }
	virtual ~Base() { delete ptr; }
};

class Derived : public Base {
	double * dptr;
public:
	Derived() { dptr = new double(5.5); }
	~Derived() { delete dptr; }
};

void func() {
	Base* bptr;
	bptr = new Derived();
	delete bptr;
}

/*void swap(int& a, int& b) {
	int temp;
	temp = a;
	a = b;
	b = temp;
}
void swap(double& a, double& b) {
	double temp;
	temp = a;
	a = b;
	b = temp;
}*/

template <class T>
void myswap(T& a, T&b) {
	T temp;
	temp = a;
	a = b;
	b = temp;
}

template <class T>
class Thing {
	T storeMe;
public:
	Thing(T newstore = T()) { storeMe = newstore; } // T() is the "default" T object
	T getMe() const { return storeMe; }
	void setMe(T newval);
};

template <class T>
void Thing<T>::setMe(T newval) 
{ 
	storeMe = newval; 
}

template <class T>
void selectionsort(vector<T>& v) { //Theta(n^2)
	int minIndex;
	for (int i = 0; i < v.size(); i++) { //n
		minIndex = i;
		for (int j = i + 1; j < v.size(); j++) //(n-1)+(n-2)+(n-3)+...+1= Theta(N)
			if (v[j] < v[minIndex])
				minIndex = j;
		myswap(v[i], v[minIndex]);
	}
}

template <class T>
void merge(vector<T>& v, int leftstart, int leftend, int rightend, vector<T>& temp) {
	int rightstart = leftend + 1;
	int originalstart = leftstart;
	int tempstart = leftstart;
	while (leftstart <= leftend && rightstart <= rightend) {
		if (v[leftstart] < v[rightstart])
			temp[tempstart++] = v[leftstart++];
		else
			temp[tempstart++] = v[rightstart++];
	}
	while (leftstart <= leftend) {
			temp[tempstart++] = v[leftstart++];
	}
	while (rightstart <= rightend) {
		temp[tempstart++] = v[rightstart++];
	}
	for (int i = originalstart; i <= rightend; i++)
		v[i] = temp[i];

}

template <class T>
void mergesort(vector<T>& v, int low, int high, vector<T>& temp) {
	if (low < high) {
		int mid = (low + high) / 2;
		mergesort(v, low, mid - 1, temp);
		mergesort(v, mid, high, temp);
		merge(v, low, mid, high, temp);
	}
}

template <class T>
void mergesort(vector<T>& v) { //driver
	vector<T> temp;
	temp.resize(v.size());
	mergesort(v, 0, v.size() - 1, temp);
}

template <class Comparable>
int binarySearch(const vector<Comparable>& v, Comparable toFind) { //Theta(log N)
	int start = 0;
	int end = v.size() - 1;
	while (start <= end) {
		int mid = (start + end) / 2;
		if (v[mid] == toFind)
			return mid;
		else if (v[mid] > toFind)
			end = mid - 1;
		else
			start = mid + 1;
	}
	return -1;
}

void printSomething(ostream& outs) {
	outs << "hello world from printSomething!" << endl;
}

template <class T>
class LList;

template <class T>
class LListNode {
	T data;
	LListNode<T>* next;
public:
	LListNode(T& newdata = T(), const LListNode<T>* newnext = nullptr) {
		data = newdata;
		next = newnext;
	}
	friend class LList<T>;
};

template <class T>
class LList {

};

int main() {
	ofstream outFile;
	outFile.open("output.txt");

	ofstream otherFile("other.txt");
	outFile << "Hello world!" << endl;

	printSomething(outFile);
	printSomething(cout);

	cout << "Enter a filename: ";
	string filename;
	cin >> filename;
	ifstream infile;
	infile.open(filename);
	while (!infile) {
		cout << "BAD FILENAME!" << endl;
		cout << "Enter a filename: ";
		cin >> filename;
		infile.clear(); // VERY IMPORTANT!
		infile.open(filename);
	}
	vector<int> v;
	int temp;
	char c;
	while (infile >> temp) {
		infile >> c;
		v.push_back(temp);
	}

	/*	Thing<int> one(1);
	Thing<double> two(5.5);

	int x, y;
	myswap(x, y);
	double w, z;
	myswap(w, z);

	vector<int> v;
	v.resize(1000000);
	int toFind = 100;
	//v is now sorted!

	for (int i = 0; i < v.size(); i++) //Theta (N)
		if (v[i] == toFind)
			cout << "FOUND IT!" << endl;

*/


/*	for (int i = 0; i < 10000000000; i++)
		func();*/
}