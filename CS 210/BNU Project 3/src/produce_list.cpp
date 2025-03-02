#include "produce_list.h"

using namespace std;

ProduceList::ProduceList() : headPtr(nullptr) {}

ProduceList::~ProduceList() {
    delete headPtr; // Deleting the head node recursively deletes all following nodes
}

string ProduceList::toLower(const string& str) const {
    string lowerStr = str;
    transform(lowerStr.begin(), lowerStr.end(), lowerStr.begin(), ::tolower);
    return lowerStr;
}

void ProduceList::loadFromFile(const string& filename) {
    ifstream file(filename);
    
    if(!file) { // If the file could not be opened
        cerr << "ERROR: File not found." << endl;
        return;
    }
    
    string line;
    ProduceNode* lastPtr = nullptr;
    
    while(getline(file, line)) {
        if(!line.empty() && line.back() == '\r') { // Fix for Windows carriage return
            line.pop_back();
        }
        
        ProduceNode* existingPtr = find(line);
        
        if(existingPtr) {
            existingPtr->increaseCount();
        } else {
            if(!headPtr) { // If the headPtr is nullptr
                headPtr = lastPtr = new ProduceNode(line);
            } else {
                lastPtr->setNext(new ProduceNode(line));
                lastPtr = lastPtr->getNext();
            }
        }
    }
    
    file.close();
}

void ProduceList::saveToFile(const string& filename) const {
    ofstream file(filename);
    
    if(!file) { // If the file could not be opened
        cerr << "ERROR: Could not create backup file." << endl;
        return;
    }
    
    ProduceNode* currPtr = headPtr;
    
    while(currPtr) {
        file << currPtr->getName() << ' ' << currPtr->getCount() << endl;
        currPtr = currPtr->getNext();
    }
    
    file.close();
    cout << "Backup created successfully." << endl;
}

ProduceNode* ProduceList::find(const string& name) const {
    string searchName = toLower(name); // Convert the search term to lowercase
    ProduceNode* currPtr = headPtr;
    
    while(currPtr) {
        if(toLower(currPtr->getName()) == searchName) {
            return currPtr;
        }
        
        currPtr = currPtr->getNext();
    }
    
    return nullptr;
}

void ProduceList::printAll() const {
    if(!headPtr) { // If the headPtr is nullptr
        cerr << "ERROR: ProduceList is empty." << endl;
        return;
    }
    
    ProduceNode* currPtr = headPtr;
    
    while(currPtr) {
        currPtr->print();
        currPtr = currPtr->getNext();
    }
}

void ProduceList::printHistogram() const {
    if(!headPtr) { // If the headPtr is nullptr
        cerr << "ERROR: ProduceList is empty." << endl;
        return;
    }
    
    ProduceNode* currPtr = headPtr;
    
    while(currPtr) {
        currPtr->printHistogram();
        currPtr = currPtr->getNext();
    }
}