#ifndef PRODUCE_NODE_H
#define PRODUCE_NODE_H

#include <iostream>
#include <string>

/*
@class ProduceNode
@brief Represents a node in a linked list of a produce item and their count
*/
class ProduceNode {
    private:
        std::string name; // Name of the item
        int count; // Amount of the item
        ProduceNode* next; // Pointer to the next node in the list
    public:
        /*
        @brief Constructor for the ProduceNode
        @param name The name of the produce item
        */
        explicit ProduceNode(const std::string& name);
        /*
        @brief Deconstructor for the node (recursive)
        */
        ~ProduceNode();
        /*
        @brief Returns the next node in the linked list, if it exists
        @return Pointer to the next node
        */
        ProduceNode* getNext() const;
        /*
        @brief Gets the name of the produce item
        @return The name as a constant
        */
        const std::string& getName() const;
        /*
        @brief Sets the next node in the linked list
        @param node The pointer to the next node
        */
        void setNext(ProduceNode* node);
        /*
        @brief Gets the amount of the produce item
        @return The amount as a constant
        */
        const int getCount() const;
        /*
        @brief Increases the amount of the produce item
        */
        void increaseCount();
        /*
        @brief Prints the name of the produce item and the amount represented as an int
        */
        void print() const;
        /*
        @brief Prints the name of the produce item and the amount represented as a histogram
        */
        void printHistogram() const;
    private:
        /*
        @brief Creates a histogram representation of the amount of the produce item
        @param count The amount of the produce item
        @param c The char used to represent the amount in the histogram
        @return The histogram represented as a string
        */
        std::string generateHistogram(int count, char c) const;
};

#endif // PRODUCE_NODE_H