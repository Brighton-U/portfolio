#ifndef PRODUCE_LIST_H
#define PRODUCE_LIST_H

#include <fstream>
#include "produce_node.h"

/*
@class ProduceList
@brief Represents a linked list of nodes, specifically ProduceNode
*/
class ProduceList {
    private:
        ProduceNode* headPtr; // Pointer to the head node
        /*
        @brief Helper function that converts a string to all lowercase
        @param str The string to convert
        @return An all lowercase string of str
        */
        std::string toLower(const std::string& str) const;
    public:
        /*
        @brief Constructor for the ProduceList
        */
        explicit ProduceList();
        /*
        @brief Deconstructor for the ProduceList
        */
        ~ProduceList();
        /*
        @brief Loads data from the file into the ProduceList
        @param filename The name of the input file
        */
        void loadFromFile(const std::string& filename);
        /*
        @brief Saves data from the ProduceList into a file
        @param filename The name of the output file
        */
        void saveToFile(const std::string& filename) const;
        /*
        @brief Searches the ProduceList for a specific node
        @return A pointer to the ProduceNode if found, else nullptr
        */
        ProduceNode* find(const std::string& name) const;
        /*
        @brief Prints each node in the ProduceList
        */
        void printAll() const;
        /*
        @brief Prints each node in the ProduceList as a histogram
        */
        void printHistogram() const;
};

#endif // PRODUCE_LIST_H