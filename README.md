Project Description
FileNotesGUI is a lightweight Java Swing Desktop Application that allows users to:

Create new notes.

View and edit existing notes.

Delete notes.

Export notes as Text (.txt) or PDF (.pdf) files anywhere on their local machine.

All notes are stored safely inside a local folder named notes/.

 Features
Create new notes with title and content

View/Edit notes

Delete notes

Export notes to custom location as Text File

Export notes to custom location as PDF File (using Apache PDFBox)

Clean User Interface (built with Java Swing)

Project Structure
FileNotesGUI/
├── src/
│   ├── model/
│   │    └── Note.java
│   ├── service/
│   │    ├── NoteService.java
│   │    └── PDFExporter.java
│   └── ui/
│        ├── MainWindow.java
│        ├── CreateNoteWindow.java
│        └── ViewNoteWindow.java
├── notes/   <-- (Auto created when first note is saved)
├── lib/     <-- (Add pdfbox-app-*.jar here)
├── README.md
└── App.java


Requirements
Java JDK 8 or higher

Apache PDFBox Library

A basic understanding of Java and Swing

Learnings
File Handling in Java (read/write/delete)

Java Swing for GUI Applications

Exporting to PDF using Apache PDFBox

Organizing Java Projects professionally

Author
Designed and Developed with Abhishek Chourasia.