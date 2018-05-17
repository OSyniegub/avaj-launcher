# avaj-launcher
Implementation of a minimal aircraft simulation program based on a given UML class diagram. Program take one and only one argument from the command line. This argument represents the name of a text file that will contain the scenario that needs to be simulated. Executing the program will generate a file simulation.txt that describes the outcome of the simulation.
 Scenario file.
The first line of the file contains a positive integer number. This number represents the number of times the simulation is run. In our case, this will be the number of times a weather change is triggered.
Each following line describes an aircraft that will be part of the simulation, with this
format: TYPE NAME LONGITUDE LATITUDE HEIGHT.
