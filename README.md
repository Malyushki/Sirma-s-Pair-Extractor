<p align="center">
<a href="#"><img src="src/files/logo-sirma-117-450x0.png"  width="600" height="300" /></a>
</p>

# TASK:

Pair of employees who have worked together

Create an application that identifies the pair of employees who have worked
together on common projects for the longest period of time and the time for
each of those projects.

# Input data:
A CSV file with data in the following format:\
EmpID, ProjectID, DateFrom, DateTo\
Sample input (partial data):\
143, 12, 2013-11-01, 2014-01-05\
218, 10, 2012-05-16, NULL\
143, 10, 2009-01-01, 2011-04-27\
...

# Sample output:
143, 218, 8\
10, 5\
12, 3

# Specific requirements
1) DateTo can be NULL, equivalent to today
2) We are interested in the number of days they worked together
3) The input data must be loaded to the program from a CSV file
4) More than one date format to be supported, extra points will be given if all date
formats are supported
5) In a README.md file summarize your understanding for the task and your algorithm
6) Do not use external libraries for the CSV parsing
7) Follow clean code conventions

# Solution 

<hr align="center"/>
<h2 align="center" >TASK:
Pair of employees who have worked together</h2 >
<hr align="center"/>
<br>
<br>
<p><strong>SUMMARIZE:</strong></p>
<p><i>We need to read a CSV file (without using external libraries for the parsing). After that we need to go through every
employee ID and check if two employees have worked together on a project comparing the project's IDs. If we find employees
that have worked together on a project we must check if the two employees have worked on the project
on the same time. We do this by checking the dates that both employees have worked on the same project.
If we get a match of dates we need to store them and continue checking the other
employees. In the end we must return as result the pair that have worked more together.
On the first line we must return the pair IDs and how many days have they worked together.
On the next n lines we must print every project ID that they have worked on and for how many days.
</i></p>

<p><strong>MAIN POINTS:</strong></p>
<p><i>1. Read CSV file and store data</i></p>
<p><i>2. Form the stored data extract unique employees with their projects</i></p>
<p><i>3. Put all Pairs that have worked together in a collection </i></p>
<p><i>4. Stream the collection and return only the pair that have worked most together </i></p>

<p><strong>USED ALGORITHM:</strong></p>
<p><strong>Base logic classes:</strong></p>
<p><i>
We have CSVReader class that have two static methods (we don't need to Initialize it) one for reading the CSV file and  stores it in a 
list of String arrays every line a different array. Second one is for creating a list of unique "Employees".
It calls the first method to receive data form CVS file and for every line in the file it splits the information in
four Strings "employeeId" , "projectId", "startDate" and "endDate". After that we create two temporary objects
"employeeTemp" and "projectTemp". We check if the employee already exists in our map. If it does exist we get the 
employee and overwrite it to our "employeeTemp". After that we add the "projectTemp" to our "employeeTemp" and put it
in our map. After that we make an entry set for each value in the map we put it in a list of Employees
and return the list of unique Employees.</i></p>

<p><i>In EmployeePairsFinder class we crate an object of it which receives the unique list of Employees. Then we call the
findTeamWithMostDaysWorkedTogether method that uses two for loops to go through every employee and compares their Projects.
Depending on that if ProjectsIDs are only numbers, or they could be texts we call different methods for comparing. If ProjectsIds are
numbers we use only one while loop which is faster than using two for loops for IDs that could be Strings and Numbers.
/We could use only one while loop because when we created the Employees we have stored their project in ascending order/
After going through the employee list we store every pair in a Map {String, Team}. In the end we stream the map and return only the
Team that have worked most together.</i></p>

<p><strong>Secondary classes:</strong></p>

<p>Project class -> basic class for storing Projects contains Project ID, Start Date, End Date. Has two constructors
one when receives StartDate and EndDates as String (goes through DatesParser for parsing to LocalDate) and the second one 
if StartDate and EndDates are already LocalDates. Have getters for fields, method to get days between StartDate and EndDate
and a toString method.
</p>

<p>Employee class -> basic class for storing Employees contains Employee ID, List of Projects.
Has a constructor, getters for fields, addProjects method for adding new Projects to the list 
and a toString method. </p>

<p>Team class -> basic class for storing a Pair of Employees contains Employee1 ID, Employee2 ID and List of Projects.
Has a constructor, getters for fields, addProjects method for adding new Projects to the list,
daysWorkedTogether method tha sums all the days that they have worked together
and a toString method. </p>

<p>DatesParser class -> basic class for parsing from String format Date to LocalDate. Supporting different formats </p>

<p>MyPanel class -> custom JPanel class. It has a constructor that sets the background color, preferred size, and layout. </p>

<p>Engine class ->sets functions for MyPanel buttons </p>




<p><strong>CHECK PROJECT</strong></p>
<p>Pair Extractor -> https://replit.com/@Malyushki/Sirma-s-Pair-Extractor?v=1 </p>
<p><strong>OTHER PROJECTS FROM SIRMA ACADEMY:</strong></p>
<p>Virtual Library -> https://replit.com/@Malyushki/OnlineLibrary?v=1 </p>
<p>CardGame project -> https://replit.com/@Malyushki/Royal-Capture?v=1</p>
