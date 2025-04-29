package service;

import model.Note;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NoteService {

    private static final String NOTES_DIR = "notes";

    static {
        File directory = new File(NOTES_DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public static void saveNote(Note note) throws IOException {
        String filename = NOTES_DIR + "/" + note.getTitle() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(note.getContent());
        }
    }

    public static List<String> getAllNoteTitles() {
        File directory = new File(NOTES_DIR);
        String[] files = directory.list((dir, name) -> name.endsWith(".txt"));
        List<String> titles = new ArrayList<>();

        if (files != null) {
            for (String file : files) {
                titles.add(file.replace(".txt", ""));
            }
        }
        return titles;
    }

    public static Note readNote(String title) throws IOException {
        String filename = NOTES_DIR + "/" + title + ".txt";
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return new Note(title, content.toString().trim());
    }

    public static void deleteNote(String title) {
        String filename = NOTES_DIR + "/" + title + ".txt";
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }
}
