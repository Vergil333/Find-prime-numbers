# Find-prime-numbers
Entry task to CNI company

# Task
### Test assignment:
Create a program written in Java that will run from a command line. The only input parameter is the data file described below.
The program should write all Prime Numbers found in the data file to the standard output (one line of output = one found Prime Number).  
Any publicly available libraries can be used.  
The output is an executable jar file including the source code or an Eclipse project.
 
### Data file description:
The data file is in Excel 2007-2013 (XLSX) format.  
Data is located in the first sheet in the B column (data is entered as a text field).  
Only positive whole numbers are valid data. Invalid data should be ignored by the program.  
A small SAMPLE of data is attached. The final data may be different!

# Solution
### Description
Because the task is primarily logic oriented, I decided to write it in Kotlin instead of Java syntax.  
It's because Java is heavy object oriented, even in situation where it does not have to be. This task does not require us to create any class and we can achieve the result just by using static methods.  
  
I've seperated the code into 2 files.  
[FileLoader](https://github.com/Vergil333/Find-prime-numbers/blob/master/src/main/kotlin/FileLoader.kt) contains methods to load the xlsx file.
[FindPrimeNumbers](https://github.com/Vergil333/Find-prime-numbers/blob/master/src/main/kotlin/FindPrimeNumbers.kt) is the main file responsible for parsing and printing xlsx file.  
  
### Executable jar
Download [primes.jar](https://github.com/Vergil333/Find-prime-numbers/releases/download/v1.0/primes.jar) and run it in CLI  
```console
java -jar path/to/primes.jar path/to/data.xlsx
```
If the path to data.xlsx file is missing or incorrect, the app will ask you to choose a new path in stdin.
  
P.S. I've made it a little more interactive and therefore more complex then it needed to be. I'm aware of that. I wanted the application to work so I covered the corner cases. It has not been cleared to me if the path to file has to be put in stdin or as command line argument (I assume it was meant to be the argument), so I did both options but in team I would firstly ask those details before writing the code.
