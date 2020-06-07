# ReadWriteDisplayTriangles
This program parses text File data to create Panes full of triangles.

----On Windows:
Run compileAndRun.bat


----On any other machine:
--From the root directory
-Compile with javac ./src/edu/mid/readWriteDisplayTriangles/*.java -d bin
-Run JVM 1.8 with java -cp bin edu.mid.readWriteDisplayTriangles.MainEntry


----There are optional debug modes which may only be activated from the command line
---They are passed as (String args[]) 

example: java -cp bin edu.mid.readWriteDisplayTriangles.MainEntry DEBUG4
  will cause console to output information about loading Triangles from the display pane/stage to the record table.
  DEBUG1 - Checks basic trigonometry
  DEBUG2 - Files
  DEBUG3 - Colors
  DEBUG4 - File Generator UI
  DEBUG5 - Loading Triangles from Pane to RecordTable
  DEBUG6 - Controls for moving Triangles around
  

Sadly, the repository was accidentally overwritten in a way that destroyed the changelog in the most recent commit.
  
Features:
  - Generate hundreds or thousands of triangles (but not hundreds of thousands) randomly based on seed value and number
  - Colorize them equally into 3 starting colors
  - Move triangles around and bring them forward and backwards with +/-
  - Have a name for the art you create different from the name of your file
  - Change individual triangles colors
  - Use alt+keyboard button or shift+keyboard direction for quicker or more precise movement
  - save and load data about triangle positions and colors into .txt files
  
Originally created by Karl Miller for Prof Haley's Java Class in March 2020, Mid-Term project.
