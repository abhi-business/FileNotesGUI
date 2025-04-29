package ui;

import service.NoteService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {

    private JList<String> notesList;
    private DefaultListModel<String> listModel;

    public MainWindow() {
        setTitle("FileNotes - Home");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        notesList = new JList<>(listModel);
        loadNotes();

        JScrollPane scrollPane = new JScrollPane(notesList);

        JButton createButton = new JButton("Create Note");
        createButton.addActionListener(e -> new CreateNoteWindow(this));

        JButton viewButton = new JButton("View/Edit Note");
        viewButton.addActionListener(e -> {
            String selectedTitle = notesList.getSelectedValue();
            if (selectedTitle != null) {
                new ViewNoteWindow(selectedTitle, this);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a note first.");
            }
        });

        JButton deleteButton = new JButton("Delete Note");
        deleteButton.addActionListener(e -> {
            String selectedTitle = notesList.getSelectedValue();
            if (selectedTitle != null) {
                NoteService.deleteNote(selectedTitle);
                JOptionPane.showMessageDialog(this, "Note deleted successfully!");
                loadNotes();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a note first.");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void loadNotes() {
        listModel.clear();
        List<String> titles = NoteService.getAllNoteTitles();
        for (String title : titles) {
            listModel.addElement(title);
        }
    }
}
