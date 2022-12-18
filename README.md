# courses-management-system
A Java console application to help manage an educational place, CS213 Project for Helwan University.

## Team
- Ahmed Abutahoun
- Hazem Salah
- Mazin Sayed

## Functionality
- A user could log into the system as an admin, instructor or student.
- An admin could:
  - Manage parent courses *(Add, Update, Delete)*
  - Manage instructors *(Add, Update, Delete)*
  - Manage students *(Add, Update, Delete)*
  - Create a course *(And assign it to specific students)*
  - Create a report for courses near to start or end
- An instructor could:
  - Publish grades for a course they teach
  - View the full details for a course they teach
  - View surveys for a course they teach
- A student could:
  - View their own grades
  - View the courses they have registered
  - View all courses in the system
  - Create a survey *(1 line comment)* on a course they have registered
  - Update their own information

## Notes
- Since this is a console application, the project runs by typing `java -jar courses-management-system.jar`
- The project has no data, only the administrator's credentials *(Username: `admin`, Password: `admin`)* in `Login/admins.txt`