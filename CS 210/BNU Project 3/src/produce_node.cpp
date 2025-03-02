#include <iomanip>
#include "produce_node.h"

using namespace std;

ProduceNode::ProduceNode(const string& name) : name(name), count(1), next(nullptr) {}

ProduceNode::~ProduceNode() {
    delete next;
}

ProduceNode* ProduceNode::getNext() const {
    return next;
}

const string& ProduceNode::getName() const {
    return name;
}

void ProduceNode::setNext(ProduceNode* node) {
    next = node;
}

const int ProduceNode::getCount() const {
    return count;
}

void ProduceNode::increaseCount() {
    count++;
}

void ProduceNode::print() const {
    cout << left << setw(15) << name << count << endl;
}

void ProduceNode::printHistogram() const {
    cout << left << setw(15) << name << generateHistogram(count, '*') << endl;
}

string ProduceNode::generateHistogram(int count, char c) const {
    return string(count, c);
}