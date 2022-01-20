#include <iostream>
#include <cmath>
using namespace std;

const int THREE = 3;
int main()
{
    int number_of_pods, peas_per_pod, total_peas;
    string tax = "10%";
    cout << "Press return after entering a number.\n";
    cout << "Enter the number of pods:\n";
    cin >> number_of_pods;
    cout << "Enter the number of peas in a pod:\n";
    cin >> peas_per_pod;
    total_peas = number_of_pods * peas_per_pod;
    cout << "If you have ";
    cout << number_of_pods;
    cout << " pea pods\n";
    cout << "and ";
    cout << peas_per_pod;
    cout << " peas in each pod, then\n";
    cout << "you have ";
    cout << total_peas;
    cout << " peas in all the pods.\n";
    cout << total_peas << " / " << number_of_pods
         << " is " << total_peas / number_of_pods << endl;
    cout << number_of_pods << " % " << total_peas
         << " is " << number_of_pods % total_peas << endl;
    cout << total_peas << " / " << 3 << " is " << total_peas / THREE << endl;
    cout << total_peas << " / " << M_PI << " is " << total_peas / M_PI << endl;

    cout << "BTW, tax is ";
    cout << tax;
    cout << " . It is pretty high!\n";

    string response_cache, response, response_copy;
    cout << "Write your response:\n";
    cin >> response_cache;
    response = response_copy = response_cache;
    cout << "Your response is ";
    cout << response << endl;
    cout << "Your response copy is " << response_copy << endl;
    return 0;
}