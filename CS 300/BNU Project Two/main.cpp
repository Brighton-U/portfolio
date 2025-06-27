/*
 * Brighton Ulery
 * brighton.ulery@snhu.edu
 * 18-Jun-25
 * A console-based program for loading, printing, and searching course data
 * using a binary search tree
*/

#include <algorithm>
#include <fstream>
#include <iostream>
#include <limits>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

struct Course {
    string courseNumber;
    string courseTitle;
    vector<string> prerequisites;
};

struct Node {
    Course course;
    Node* left_node;
    Node* right_node;

    Node(Course aCourse) {
        course = aCourse;
        left_node = nullptr;
        right_node = nullptr;
    }
};

class BST {
    private:
        Node* root;
    public:
        BST();
        virtual ~BST();
        Node* getRoot() const;
        void insert(Course course);
        void clear();
        void inOrder();
        Course search(string courseNumber);
    private:
        void destroy(Node* node);
        void addNode(Node* node, Course course);
        void inOrder(Node* node);
};

// Forward declarations
void printCourse(Course course);
bool verifyCourses(BST &bst);

/*
 * Default constructor
*/
BST::BST() {
    root = nullptr;
}

/*
 * Destructor
*/
BST::~BST() {
    destroy(root);
}

/*
 * Recursively destroys each node in the tree
 *
 * @param node the current node to destroy
*/
void BST::destroy(Node* node) {
    if(node == nullptr) return; // Guard clause

    destroy(node->left_node);
    destroy(node->right_node);
    delete node;
}

/*
 * Adds a new node to the tree
 *
 * @param node the current node for comparisons and insertion
 * @param course the course that is being added to the tree
*/
void BST::addNode(Node* node, Course course) {
    if(course.courseNumber < node->course.courseNumber) {
        if(node->left_node == nullptr) {
            node->left_node = new Node(course);
        } else {
            addNode(node->left_node, course);
        }
    } else {
        if(node->right_node == nullptr) {
            node->right_node = new Node(course);
        } else {
            addNode(node->right_node, course);
        }
    }
}

/*
 * Prints the tree in alphanumeric order
 *
 * @param node the current node for printing
*/
void BST::inOrder(Node* node) {
    if(node == nullptr) return; // Guard clause

    inOrder(node->left_node);
    printCourse(node->course);
    inOrder(node->right_node);
}

/*
 * Returns the root node
*/
Node* BST::getRoot() const {
    return root;
}

/*
 * Publicly accessible method for adding nodes to the tree
 *
 * @param course the course to add to the tree
*/
void BST::insert(Course course) {
    if(root == nullptr) { 
        // If the root does not exist, set the root to a new node
        root = new Node(course);
    } else {
        addNode(root, course);
    }
}

/*
 * Resets the tree
*/
void BST::clear() {
    destroy(root);
    root = nullptr;
}

/*
 * Publicly accessible method for printing the tree alphanumerically
*/
void BST::inOrder() {
    inOrder(root);
}

/*
 * Method for searching a specific course in the tree
 *
 * @param courseNumber the courseNumber of the course to search for
 * @returns Course either the found course or an empty Course object
*/
Course BST::search(string courseNumber) {
    Node* curr_node = root;

    while(curr_node != nullptr) {
        if(courseNumber == curr_node->course.courseNumber) {
            return curr_node->course;
        }

        if(courseNumber < curr_node->course.courseNumber) {
            curr_node = curr_node->left_node;
        } else {
            curr_node = curr_node->right_node;
        }
    }

    return Course();
}

/*
 * Loads course data from a file into a data structure
 *
 * @param bst the binary search tree data structure
 * @param filename the data file to open
 * @returns bool whether the file and data was successfully loaded
*/
bool loadCourses(BST &bst, string filename) {
    ifstream file(filename);

    if(!file.is_open()) {
        cout << "[ERROR] File path " << filename << " could not be loaded." << endl;
        return false;
    }

    string line;
    int line_number = 1;

    while(getline(file, line)) {
        stringstream ss(line);
        string token;
        vector<string> tokens;

        while(getline(ss, token, ',')) {
            if(!token.empty()) {
                tokens.push_back(token);
            }
        }

        if(tokens.size() < 2) {
            cout << "[ERROR] Line " << line_number << " (" << line << ") improperly formatted." << endl;
            ++line_number;
            continue;
        }

        if(tokens.size() >= 2) {
            Course course;
            course.courseNumber = tokens[0];
            course.courseTitle = tokens[1];

            for(size_t i = 2; i < tokens.size(); ++i) {
                course.prerequisites.push_back(tokens[i]);
            }

            bst.insert(course);
        }

        ++line_number;
    }

    file.close();
    return verifyCourses(bst); // Return whether the courses were all valid
}

/*
 * Verifies each course prerequisite is within the tree
 *
 * @param node the current node to verify
 * @param bst the data structure
 * @param verified a variable passed recursively to track if all courses have been verified
*/
void verifyCourses(Node* node, BST &bst, bool &verified) {
    if(node == nullptr) return; // Guard clause

    verifyCourses(node->left_node, bst, verified);

    for(string prerequisite : node->course.prerequisites) {
        Course course = bst.search(prerequisite);

        if(course.courseNumber.empty()) { // If the Course is an empty object
            verified = false;
            cout << "[ERROR] " << prerequisite << " not found for " << node->course.courseNumber << "." << endl;
        }
    }

    verifyCourses(node->right_node, bst, verified);
}

/*
 * Function for recursively verifying the courses in the bst
 *
 * @param bst the data structure
 * @returns bool whether all the courses are valid or not
*/
bool verifyCourses(BST &bst) {
    bool verified = true;
    verifyCourses(bst.getRoot(), bst, verified);
    return verified;
}

/*
 * Prints the course information, including prerequisites
 * 
 * @param course the course to print
*/
void printCourse(Course course) {
    cout << course.courseNumber << ", " << course.courseTitle << endl;

    if(!course.prerequisites.empty()) {
        cout << "\tPrerequisites: ";

        for(size_t i = 0; i < course.prerequisites.size(); ++i) {
            cout << course.prerequisites[i];

            if(i < course.prerequisites.size() - 1) {
                cout << ", ";
            }
        }

        cout << endl;
    }
}

int main(int argc, char *argv[]) {
    int user_input;
    bool isLoaded = false; // Flag for checking if data has been loaded into the data structure
    BST bst;
    string file_path = "./CS_300_ABCU_Advising_Program_Input.csv"; // default file path
    cout << "Welcome to the course planner." << endl << endl;

    while(true) {
        cout << "1. Load Data Structure." << endl;
        cout << "2. Print Course List." << endl;
        cout << "3. Print Course." << endl;
        cout << "9. Exit." << endl << endl;
        cout << "What would you like to do? ";
        cin >> user_input;

        if(cin.fail()) {
            /*
            * If the input is not an int, clear the input and any excess characters,
            * then inform the user
            */
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
            cout << "[ERROR] Invalid input, please enter a number." << endl << endl;
            continue;
        }
        
        switch(user_input) {
            case 1: {
                string user_path;
                cout << "Enter a file path (leave blank to use default): ";
                cin.ignore(numeric_limits<streamsize>::max(), '\n');
                getline(cin, user_path);
                
                if(!user_path.empty()) {
                    file_path = user_path;
                }

                bst.clear(); // Clears the BST every time a new file is loaded

                if(loadCourses(bst, file_path)) {
                    isLoaded = true;
                    cout << "[INFO] Successfully loaded." << endl << endl;
                } else {
                    cout << "[ERROR] Failed to load the data structure." << endl << endl;
                }

                break;
            }
            case 2:
                if(isLoaded) {
                    cout << endl;
                    bst.inOrder();
                    cout << endl;
                } else {
                    cout << "[INFO] Please load the data structure." << endl << endl;
                }

                break;
            case 3:
                if(isLoaded) {
                    string user_course;
                    cout << "What course do you want to know about? ";
                    cin >> user_course;
                    transform(user_course.begin(), user_course.end(), user_course.begin(), ::toupper); // Convert the user input to uppercase
                    Course course = bst.search(user_course);

                    if(!course.courseNumber.empty()) { // If the Course object is not empty
                        cout << endl;
                        printCourse(course);
                        cout << endl;
                    } else {
                        cout << "[INFO] Course " << user_course << " was not found." << endl << endl;
                    }
                } else {
                    cout << "[INFO] Please load the data structure." << endl << endl;
                }

                break;
            case 9:
                return 0;
            default:
                // Catches all unexpected (numerical) input.
                cout << "[ERROR] " << user_input << " is not a valid option." << endl << endl;
                break;
        }
    }
}