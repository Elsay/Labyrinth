package pro.arta;

import java.io.*;

public class Application {

    public static void main (String arg[]) {
        File fileFrom = new File(arg[0]);
        File fileTo = new File(arg[1]);
        String[][] lab = getLabyrinthFromFile(fileFrom, new String[getCountRow(fileFrom)][]);

        System.out.println("\n****** Labyrinth ******");
        printLabyrinth(lab);

        LabyrinthField field = new LabyrinthField(translateOnField(lab));

        if (field.getShortWay(lab)) {
            System.out.println("\n****** The shortest way in labyrinth ******");
            printLabyrinth(lab);

        } else {
            System.out.println("\nAttention! In the labyrinth no exit");
        }

        writeToFile(fileTo, lab);
    }

    private static String[][] getLabyrinthFromFile (File file, String[][] lab) {
        int index = 0;
        String line = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] tmp = line.split(" ");
                lab[index] = tmp;
                index++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lab;
    }

    private static int getCountRow (File file) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))){

            while (br.readLine() != null) {
                count++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    private static void printLabyrinth(String[][] lab) {
        for (String[] row : lab) {
            for (String item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    private static int[][] translateOnField(String[][] lab) {
        int[][] field = new int[lab.length][lab[0].length];

        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[i].length; j++) {
                if (lab[i][j] != null) {
                    switch (lab[i][j]) {
                        case "*": field[i][j] = -1;
                            break;
                        case "0": field[i][j] = 0;
                            break;
                        case "x": field[i][j] = 1;
                            break;
                    }
                }
            }
        }

        return field;
    }

    private static void writeToFile(File file, String[][] matrix) {
        try (FileWriter fw = new FileWriter(file)) {
            for (String[] row : matrix) {
                StringBuilder str = new StringBuilder();
                for (String item : row) {
                    str.append(item + " ");
                }
                fw.write(str.toString() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
