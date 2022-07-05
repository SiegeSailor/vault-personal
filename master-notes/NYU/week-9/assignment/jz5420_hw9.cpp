/**
 * @file jz5420_hw9.cpp
 * @author Jin Yu (jz5420@nyu.edu, siegesailor@gmail.com)
 * @brief
    The goal for this Project is to create a simple two-dimensional predator-prey simulation.
    In this simulation the prey are ants and the predators are doodlebugs.
    These critters live in a world composed of a 20 × 20 grid of cells. Only one critter may occupy a cell at a time.
    The grid is enclosed, so a critter is not allowed to move off the edges of the world.
    Time is simulated in time steps. Each critter performs some action every time step.

    The ants behave according to the following model:
    Move.
        Every time step, randomly try to move up, down, left, or right.
        If the neighboring cell in the selected direction is occupied or would move the ant off the grid,
        then the ant stays in the current cell.

    Breed.
        If an ant survives for three time steps,
        then at the end of the time step (that is; after moving) the ant will breed.
        This is simulated by creating a new ant in an adjacent (up, down, left, or right) cell that is empty.
        If there is no empty cell available, then no breeding occurs. Once an off-spring is produced,
        an ant cannot produce an offspring until three more time steps have elapsed.


    The doodlebugs behave according to the following model:
    Move.
        Every time step, if there is an adjacent ant (up, down, left, or right),
        then the doodlebug will move to that cell and eat the ant.
        Otherwise, the doodlebug moves according to the same rules as the ant.
        Note that a doodlebug cannot eat other doodlebugs.

    Breed.
        If a doodlebug survives for eight time steps,
        then at the end of the time step it will spawn off a new doodlebug in the same manner as the ant.

    Starve.
        If a doodlebug has not eaten an ant within the last three time steps,
        then at the end of the third time step it will starve and die.
        The doodlebug should then be removed from the grid of cells.

    During one turn, all the doodlebugs should move before the ants do.

    Write a program to implement this simulation and draw the world using ASCII characters of
    “o” for an ant and “X” for a doodlebug or "-" for an empty space.
    Create a class named Organism that encapsulates basic data common to both ants and doodlebugs.
    This class should have a virtual function named move that is defined in the derived classes of Ant and Doodlebug.
    You may need additional data structures to keep track of which critters have moved.

    Initialize the world with 5 doodlebugs and 100 ants.
    After each time step, prompt the user to press Enter to move to the next time step.
    You should see a cyclical pattern between the population of predators and prey,
    although random perturbations may lead to the elimination of one or both species.
 * @version 0.1
 * @date 2022-03-18
 *
 * @copyright Copyright (c) 2022
 */

#include <vector>
#include <string>
#include <cstdlib>
#include <ctime>
#include <iostream>
using namespace std;

const char SYMBOL_ANT = 'o', SYMBOL_DOODLEBUG = 'X', SYMBOL_EMPTY_SPACE = '-', SYMBOL_UNSIGNED = 'N';
const string NAME_ANT = "Ant", NAME_DOODLEBUG = "Doodlebug", NAME_UNSIGNED = "Unknown";
const int GRID_MIN = 1, GRID_MAX = 20;
const int LIFE_PREY_MAX = 3, LIFE_PREDATOR_MAX = 8, HEALTH_MAX = 3;

struct Coordinate
{
    int x = 0;
    int y = 0;
    char symbol = SYMBOL_UNSIGNED;
    bool operator==(const Coordinate &that)
    {
        return x == that.x && y == that.y;
    }
    bool operator!=(const Coordinate &that)
    {
        return x != that.x || y != that.y;
    }
    Coordinate operator=(const Coordinate &that)
    {
        x = that.x;
        y = that.y;
        return *this;
    }
};

class Organism
{
protected:
    string name = NAME_UNSIGNED;
    char body = SYMBOL_UNSIGNED;
    int x = 0;
    int y = 0;
    int life = 0;

public:
    Organism(char inputBody, string inputName) : body(inputBody), name(inputName) {}
    Organism(char inputBody, string inputName, int inputX, int inputY, string message = "")
        : body(inputBody), name(inputName), x(inputX), y(inputY)
    {
        call("spawned");
        if (message.length() > 0)
            cout << "(" << message << ")" << endl;
    }

    void call(string verb)
    {
        cout << "A " << name
             << " has been " << verb << " at {" << x << ", " << y << "}." << endl;
    }

    virtual void move(Coordinate inputCoordinate)
    {
        x = inputCoordinate.x;
        y = inputCoordinate.y;
    };

    virtual void survive()
    {
        life++;
    };

    int obtainLife() const { return life; }

    char obtainBody() const { return body; }

    Coordinate obtainCoordinate() const
    {
        Coordinate coordinate;
        coordinate.x = x;
        coordinate.y = y;
        coordinate.symbol = body;
        return coordinate;
    }

    void restore()
    {
        life = 0;
    }
};

class Predator : public Organism
{
private:
    int health = HEALTH_MAX;

public:
    Predator(char inputBody, string inputName) : Organism(inputBody, inputName){};
    Predator(char inputBody, string inputName, int inputX, int inputY)
        : Organism(inputBody, inputName, inputX, inputY){};

    void hunt()
    {
        health = HEALTH_MAX;
    };

    virtual void survive()
    {
        if (life < LIFE_PREDATOR_MAX)
            life++;
    };

    void starve()
    {
        if (health > 0)
            health--;
    }

    int obtainHealth() const
    {
        return health;
    }
};

class Prey : public Organism
{
public:
    Prey(char inputBody, string inputName) : Organism(inputBody, inputName){};
    Prey(char inputBody, string inputName, int inputX, int inputY)
        : Organism(inputBody, inputName, inputX, inputY) {}

    virtual void survive()
    {
        if (life < LIFE_PREY_MAX)
            life++;
    };
};

class World
{
private:
    int boundMaxX = GRID_MAX;
    int boundMaxY = GRID_MAX;
    int timeStep = 0;
    vector<Coordinate> listOfCoordinateOccupied;
    vector<Predator> listOfPredator;
    vector<Prey> listOfPrey;

public:
    World() {}
    World(int inputBoundMaxX, int inputBoundMaxY)
        : boundMaxX(inputBoundMaxX), boundMaxY(inputBoundMaxY) {}

    int obtainIndexOfOccupied(Coordinate coordinateCompare, vector<Coordinate> listOfCoordinateCompare) const
    {
        for (int i = 0; i < listOfCoordinateCompare.size(); i++)
        {
            if (listOfCoordinateCompare[i] == coordinateCompare)
                return i;
        };
        return -1;
    }

    Coordinate obtainCoordinateAdjacentRandom(Coordinate coordinateOriginal, vector<Coordinate> listOfCoordinateCompare) const
    {
        vector<Coordinate> listOfCoordinatePossible;
        Coordinate coordinateUpdated;
        coordinateUpdated.x = coordinateOriginal.x;
        coordinateUpdated.y = coordinateOriginal.y;
        coordinateUpdated.symbol = coordinateOriginal.symbol;

        if (coordinateOriginal.x + 1 <= boundMaxX)
        {
            coordinateUpdated.x = coordinateOriginal.x + 1;
            coordinateUpdated.y = coordinateOriginal.y;
            if (obtainIndexOfOccupied(coordinateUpdated, listOfCoordinateCompare) == -1)
                listOfCoordinatePossible.push_back(coordinateUpdated);
        };
        if (coordinateOriginal.x - 1 >= GRID_MIN)
        {
            coordinateUpdated.x = coordinateOriginal.x - 1;
            coordinateUpdated.y = coordinateOriginal.y;
            if (obtainIndexOfOccupied(coordinateUpdated, listOfCoordinateCompare) == -1)
                listOfCoordinatePossible.push_back(coordinateUpdated);
        };
        if (coordinateOriginal.y + 1 <= boundMaxY)
        {
            coordinateUpdated.x = coordinateOriginal.x;
            coordinateUpdated.y = coordinateOriginal.y + 1;
            if (obtainIndexOfOccupied(coordinateUpdated, listOfCoordinateCompare) == -1)
                listOfCoordinatePossible.push_back(coordinateUpdated);
        };
        if (coordinateOriginal.y - 1 >= GRID_MIN)
        {
            coordinateUpdated.x = coordinateOriginal.x;
            coordinateUpdated.y = coordinateOriginal.y - 1;
            if (obtainIndexOfOccupied(coordinateUpdated, listOfCoordinateCompare) == -1)
                listOfCoordinatePossible.push_back(coordinateUpdated);
        };

        int sizeOfListOfCoordinatePossible = listOfCoordinatePossible.size();
        if (sizeOfListOfCoordinatePossible < 1)
            return coordinateOriginal;

        return listOfCoordinatePossible[rand() % sizeOfListOfCoordinatePossible];
    };

    Coordinate obtainCoordinateUnoccupiedRandom() const
    {
        Coordinate coordinateRandom;
        while (true)
        {
            coordinateRandom.x = rand() % boundMaxX + 1;
            coordinateRandom.y = rand() % boundMaxY + 1;
            bool isOccupied = obtainIndexOfOccupied(coordinateRandom, listOfCoordinateOccupied) != -1;
            if (!isOccupied)
                break;
        }

        return coordinateRandom;
    };

    void eraseInAllList(Coordinate coordinateCompare)
    {
        for (int i = 0; i < listOfCoordinateOccupied.size(); i++)
        {
            if (coordinateCompare == listOfCoordinateOccupied[i])
                listOfCoordinateOccupied.erase(listOfCoordinateOccupied.begin() + i);
            if (i < listOfPredator.size() && coordinateCompare == listOfPredator[i].obtainCoordinate())
                listOfPredator.erase(listOfPredator.begin() + i);
            if (i < listOfPrey.size() && coordinateCompare == listOfPrey[i].obtainCoordinate())
                listOfPrey.erase(listOfPrey.begin() + i);
        }
    }

    void moveWithListOfOccupied(Organism &input, Coordinate coordinateNext)
    {
        for (int i = 0; i < listOfCoordinateOccupied.size(); i++)
        {
            if (input.obtainCoordinate() == listOfCoordinateOccupied[i])
                listOfCoordinateOccupied[i] = coordinateNext;
        }
    }

    void placePredator(Predator inputPredator)
    {
        Coordinate coordinateUnoccupied = obtainCoordinateUnoccupiedRandom();
        coordinateUnoccupied.symbol = inputPredator.obtainBody();
        inputPredator.move(coordinateUnoccupied);
        listOfCoordinateOccupied.push_back(coordinateUnoccupied);
        listOfPredator.push_back(inputPredator);
    };

    void placePrey(Prey inputPrey)
    {
        Coordinate coordinateUnoccupied = obtainCoordinateUnoccupiedRandom();
        coordinateUnoccupied.symbol = inputPrey.obtainBody();
        inputPrey.move(coordinateUnoccupied);
        listOfCoordinateOccupied.push_back(coordinateUnoccupied);
        listOfPrey.push_back(inputPrey);
    };

    bool indicateSimulationOver() const
    {
        if (listOfPredator.size() == 0 && listOfPrey.size() == 0)
        {
            cout << "All specie has been elminated" << endl;
            return true;
        }
        else if (listOfPredator.size() == 0)
        {
            cout << "All predator has been elminated." << endl;
            return true;
        }
        else if (listOfPrey.size() == 0)
        {
            cout << "All prey has been elminated." << endl;
            return true;
        };

        return false;
    };

    void restore()
    {
        timeStep = 0;
        listOfCoordinateOccupied.clear();
        listOfPredator.clear();
        listOfPrey.clear();
    };

    vector<Coordinate> obtainListOfCoordinatePredator() const
    {
        vector<Coordinate> listOfCoordinatePredator;
        for (int i = 0; i < listOfPredator.size(); i++)
            listOfCoordinatePredator.push_back(listOfPredator[i].obtainCoordinate());
        return listOfCoordinatePredator;
    }

    vector<Coordinate> obtainListOfCoordinatePrey() const
    {
        vector<Coordinate> listOfCoordinatePrey;
        for (int i = 0; i < listOfPrey.size(); i++)
            listOfCoordinatePrey.push_back(listOfPrey[i].obtainCoordinate());
        return listOfCoordinatePrey;
    }

    void interact()
    {
        string input;
        while (true)
        {
            timeStep++;
            getline(cin, input);
            cout << "Time Step: " << timeStep << endl;

            for (int i = 0; i < listOfPredator.size(); i++)
            {
                Coordinate coordinateAdjacentRandom = obtainCoordinateAdjacentRandom(listOfPredator[i].obtainCoordinate(), listOfCoordinateOccupied);
                if (coordinateAdjacentRandom != listOfPredator[i].obtainCoordinate())
                {
                    listOfPredator[i].move(coordinateAdjacentRandom);
                    moveWithListOfOccupied(listOfPredator[i], coordinateAdjacentRandom);
                }
                listOfPredator[i].survive();
                listOfPredator[i].starve();
            }

            vector<Coordinate> listOfCoordinatePredator = obtainListOfCoordinatePredator();
            vector<Coordinate> listOfCoordinatePrey = obtainListOfCoordinatePrey();
            for (int i = 0; i < listOfPrey.size(); i++)
            {
                Coordinate coordinateAdjacentRandom = obtainCoordinateAdjacentRandom(listOfPrey[i].obtainCoordinate(), listOfCoordinatePrey);
                int indexOfOccupied = obtainIndexOfOccupied(coordinateAdjacentRandom, listOfCoordinatePredator);
                if (coordinateAdjacentRandom == listOfPrey[i].obtainCoordinate())
                {
                    listOfPrey[i].survive();
                }
                else if (indexOfOccupied != -1)
                {
                    listOfPrey[i].call("eaten");
                    listOfPredator[indexOfOccupied].hunt();
                    eraseInAllList(listOfPrey[i].obtainCoordinate());
                }
                else
                {
                    listOfPrey[i].move(coordinateAdjacentRandom);
                    moveWithListOfOccupied(listOfPrey[i], coordinateAdjacentRandom);
                    listOfPrey[i].survive();
                }
            }

            for (int i = 0; i < listOfPredator.size(); i++)
            {
                if (listOfPredator[i].obtainHealth() <= 0)
                {
                    listOfPredator[i].call("starved and died");
                    eraseInAllList(listOfPredator[i].obtainCoordinate());
                }
                else if (listOfPredator[i].obtainLife() >= LIFE_PREDATOR_MAX)
                {
                    Coordinate coordinateAdjacentRandom = obtainCoordinateAdjacentRandom(listOfPredator[i].obtainCoordinate(), listOfCoordinateOccupied);
                    if (coordinateAdjacentRandom != listOfPredator[i].obtainCoordinate())
                    {
                        Predator predatorSpawend(SYMBOL_DOODLEBUG, NAME_DOODLEBUG, coordinateAdjacentRandom.x, coordinateAdjacentRandom.y);
                        listOfPredator.push_back(predatorSpawend);
                        listOfCoordinateOccupied.push_back(coordinateAdjacentRandom);
                        listOfPredator[i].restore();
                    }
                    else
                    {
                        listOfPredator[i].restore();
                    }
                }
            }

            for (int i = 0; i < listOfPrey.size(); i++)
            {
                if (listOfPrey[i].obtainLife() >= LIFE_PREY_MAX)
                {
                    Coordinate coordinateAdjacentRandom = obtainCoordinateAdjacentRandom(listOfPrey[i].obtainCoordinate(), listOfCoordinateOccupied);
                    if (coordinateAdjacentRandom != listOfPrey[i].obtainCoordinate())
                    {
                        Prey preySpawend(SYMBOL_ANT, NAME_ANT, coordinateAdjacentRandom.x, coordinateAdjacentRandom.y);
                        listOfPrey.push_back(preySpawend);
                        listOfCoordinateOccupied.push_back(coordinateAdjacentRandom);
                        listOfPrey[i].restore();
                    }
                    else
                    {
                        listOfPrey[i].restore();
                    }
                }
            }

            output();
            bool isSimulationOver = indicateSimulationOver();
            if (isSimulationOver)
                break;
        }
    }

    void output() const
    {
        cout << "Number of Predator: " << listOfPredator.size() << endl;
        cout << "Number of Prey: " << listOfPrey.size() << endl;
        for (int indexOfX = 1; indexOfX <= boundMaxX; indexOfX++)
        {
            for (int indexOfY = 1; indexOfY <= boundMaxY; indexOfY++)
            {
                bool isCalled = false;

                for (int i = 0; i < listOfCoordinateOccupied.size(); i++)
                {
                    Coordinate coordinateOccupied = listOfCoordinateOccupied[i];
                    Coordinate coordinateCurrent;
                    coordinateCurrent.x = indexOfX;
                    coordinateCurrent.y = indexOfY;

                    if (coordinateOccupied == coordinateCurrent)
                    {
                        cout << coordinateOccupied.symbol;
                        isCalled = true;
                        break;
                    }
                };

                if (!isCalled)
                    cout << SYMBOL_EMPTY_SPACE;

                cout << " ";
            };
            cout << endl;
        };
    }

    void create(int numberOfPredator, int numberOfPrey)
    {
        srand(time(0));
        restore();
        for (int i = 0; i < numberOfPredator; i++)
        {
            Predator inputPredtor(SYMBOL_DOODLEBUG, NAME_DOODLEBUG);
            placePredator(inputPredtor);
        };
        for (int i = 0; i < numberOfPrey; i++)
        {
            Prey inputPrey(SYMBOL_ANT, NAME_ANT);
            placePrey(inputPrey);
        };
        cout << "A simple two-dimensional predator-prey simulation has begun with grid "
             << boundMaxX << "x" << boundMaxY
             << ". All the stale data has been cleared." << endl;
        cout << "Time Step: 0 (Initial Simulation)" << endl;
        output();
        cout << endl
             << "Please press Enter to move to the next time step:" << endl;
        interact();
    }
};

int main()
{
    World world;
    world.create(5, 100);
    return 0;
}