package ui;

import model.Note;
import service.NoteService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CreateNoteWindow extends JFrame {

    private JTextField titleField;
    private JTextArea contentArea;
    private MainWindow mainWindow;

    public CreateNoteWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        setTitle("Create New Note");
        setSize(400, 400);
        setLocationRelativeTo(null);

        titleField = new JTextField();
        contentArea = new JTextArea();
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

        JButton saveButton = new JButton("Save Note");
        saveButton.addActionListener(e -> saveNote());

        setLayout(new BorderLayout());
        add(titleField, BorderLayout.NORTH);
        add(new JScrollPane(contentArea), BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void saveNote() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        if (title.isEmpty() || content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title and Content cannot be empty!");
            return;
        }

        try {
            NoteService.saveNote(new Note(title, content));
            JOptionPane.showMessageDialog(this, "Note saved successfully!");
            mainWindow.loadNotes();
            dispose();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save note.");
        }
    }
}
