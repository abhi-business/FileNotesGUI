package ui;

import model.Note;
import service.NoteService;
import service.PDFExporter;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ViewNoteWindow extends JFrame {

    private Note note;
    private JTextArea contentArea;
    private MainWindow mainWindow;

    public ViewNoteWindow(String title, MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        try {
            note = NoteService.readNote(title);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load note.");
            return;
        }

        setTitle("View/Edit Note - " + title);
        setSize(400, 400);
        setLocationRelativeTo(null);

        contentArea = new JTextArea(note.getContent());
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> saveChanges());

        JButton exportTextButton = new JButton("Export as Text");
        exportTextButton.addActionListener(e -> exportAsText());

        JButton exportPDFButton = new JButton("Export as PDF");
        exportPDFButton.addActionListener(e -> exportAsPDF());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(exportTextButton);
        buttonPanel.add(exportPDFButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(contentArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void saveChanges() {
        String updatedContent = contentArea.getText().trim();
        if (updatedContent.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Content cannot be empty!");
            return;
        }
        try {
            note.setContent(updatedContent);
            NoteService.saveNote(note);
            JOptionPane.showMessageDialog(this, "Note updated successfully!");
            mainWindow.loadNotes();
            dispose();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to update note.");
        }
    }

    private void exportAsText() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save as Text File");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(note.getContent());
                JOptionPane.showMessageDialog(this, "Note exported as text file successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to export as text.");
            }
        }
    }

    private void exportAsPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save as PDF File");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                PDFExporter.exportToPDF(note, fileToSave);
                JOptionPane.showMessageDialog(this, "Note exported as PDF successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to export as PDF.");
            }
        }
    }
}
