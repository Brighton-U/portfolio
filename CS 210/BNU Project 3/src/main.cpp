/*
Brighton Ulery
brighton.ulery@ndus.edu
22 Feb 25
A program for converting an input file of purchased grocery items into
an easily parsable list.
*/
#include <iostream>
#include "produce_list.h"

using namespace std;

/*
[1] Search
[2] Print
[3] Print Histogram
[4] Exit
*/
void printMenu() {
    const char* menuItems[] = {"Search", "Print", "Print Histogram", "Exit"};
    
    for(size_t i = 0; i < 4; i++) {
        cout << "[" << (i + 1) << "] " << menuItems[i] << endl;
    }
}

void handleMenu(ProduceList& produceList) {
    int option;
    string searchTerm;
    
    while(true) {
        printMenu();
        
        if(!(cin >> option)) { // If the input is not of type int
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
            cerr << "ERROR: Please enter an integer value." << endl;
            continue;
        }
        
        if(option < 1 || option > 4) { // If the input is out of range
            cerr << "ERROR: Please enter a value between 1 and 4." << endl;
            continue;
        }
        
        switch(option) {
            case 1:
                cout << "Search term: ";
                cin >> searchTerm;
                
                if(ProduceNode* node = produceList.find(searchTerm)) {
                    node->print();
                } else {
                    cout << "Item not found." << endl;
                }
                
                break;
            case 2:
                produceList.printAll();
                break;
            case 3:
                produceList.printHistogram();
                break;
            case 4:
                return;
            default:
                cerr << "ERROR: Unexpected input." << endl;
        }
    }
}

int main() {
    const string INPUT_FILENAME = "CS210_Project_Three_Input_File.txt"; // Input file
    const string OUTPUT_FILENAME = "frequency.dat"; // Output file
    ProduceList produceList;
    produceList.loadFromFile(INPUT_FILENAME);
    handleMenu(produceList);
    produceList.saveToFile(OUTPUT_FILENAME);
    return 0;
}