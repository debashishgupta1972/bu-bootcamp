import java.io.*;
import java.util.ArrayList;

public class GradeAnalyzer {
    public static void main(String[] args){

        Double average_score;
        String filename = "";
        String outputfilename = "";

        if(args.length > 2){
            throw new IllegalArgumentException("Too many arguments! Expected at most 2.");
        }
        if(args.length == 0){
            filename = "scores.txt";
            outputfilename = "grade_analysis_report.txt";
        // }else if (args[0] != null && args[1] != null) {
        }else if (args.length == 2) {
            filename = args[0];
            outputfilename = args[1];
        // }else if (args[0] != null && args[1] == null){
        }else if (args.length == 1){
            filename = args[0];
            outputfilename = "grade_analysis_report.txt";
        }else{
            filename = "scores.txt";
            outputfilename = "grade_analysis_report.txt";
        }

        ArrayList<Integer> scores = readScores(filename);

        int Highest = Integer.MIN_VALUE;
        int Lowest = Integer.MAX_VALUE;

        if(scores.size() != 0){
            for(int get_score : scores){
                if (get_score >= 0) {
                    if ((get_score > Highest)){
                        Highest = get_score;
                    } 
                    if ((get_score < Lowest)) {
                        Lowest = get_score;
                    }
                }
                if (get_score == -1 && (Highest == Integer.MIN_VALUE) && (Lowest == Integer.MAX_VALUE)){
                    Highest = 0;
                    Lowest = 0;
                }

            }
        }else{
            Highest = 0;
            Lowest = 0;
        }

        if (!scores.isEmpty()){
            average_score = calculateAverage(scores);
        } else {
            average_score = 0.0;
        }

        writeReport(scores,average_score,Highest, Lowest, outputfilename);
    }

    // Returns a list of valid scores read from a buffer
    public static ArrayList<Integer> readScores(String filename){
        String line;
        ArrayList<Integer> list = new ArrayList<Integer>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            if(!reader.ready()){
                throw new IOException("WARNING: File is empty. Output will contain incorrect values.");
            }
            while ((line = reader.readLine()) != null){
                line = line.trim();
                if (line.isEmpty()){
                    list.add(-1);
                    System.out.println("NOTE: blank line is skipped in list");
                    continue;
                }
                try{
                    int score = Integer.parseInt(line);
                    list.add(score);
                }catch (NumberFormatException e){
                    list.add(-1);
                    System.out.println("NOTE: Non-Number value \'" + line + "\' is skipped in list");
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("CAUTION: File not found: " + e.getMessage());
        }catch(IOException e){
           System.out.println(e);
        }
        return list;

    }

    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores){
        if (scores.isEmpty()){
            return 0.0;
        }
        Double total_scores = 0.0;
        int validCount = 0;

        for(int score : scores){
            if (score >= 0){
                total_scores += score;
                validCount++;
            }
        }

        if(validCount == 0){
            return 0.0;
        }

        // return total_scores / scores.size();
        return total_scores / validCount;
    }

    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                    double avg, int high, int low,
                                    String outputFile){
        int InvalidValues = 0, countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))){
            for(int score : scores){
                if (score > Integer.MIN_VALUE && score < 0){
                    InvalidValues += 1;
                }
                if(score > 0){
                    if (score >= 90){
                        countA += 1;
                    }else if (score >= 80 && score <= 89){
                        countB += 1;
                    }else if (score >= 70 && score <= 79){
                        countC += 1;
                    }else if (score >= 60 && score <= 69){
                        countD += 1;
                    }else{
                        countF += 1;
                    }
                }
            }

            writer.write("=".repeat(3) + " Grade Analysis Report " + "=".repeat(3));
            System.out.println("=".repeat(3) + " Grade Analysis Report " + "=".repeat(3));
            
            if (InvalidValues > 0){
                writer.write("\nTotal scores processed:\t" + scores.size());
                System.out.println("\nTotal scores processed:\t" + scores.size());
                writer.write("\nInvalid lines skipped:\t" + InvalidValues);
                System.out.println("Invalid lines skipped:\t" + InvalidValues);
            }else {
                writer.write("Total scores processed:" + scores.size());
                System.out.println("Total scores processed:" + scores.size());
            }
            writer.newLine();
            writer.write("\n" + String.format("Average score:\t %.2f%n", avg));
            writer.write(String.format("Highest score:\t %d%n", high));
            writer.write(String.format("Lowest score:\t %d%n", low));
            writer.write("\nGrade distribution:");
            writer.newLine();
            writer.write("\tA (90-100):" + "\t".repeat(2) + countA);
            writer.newLine();
            writer.write("\tB (80-89):" + "\t".repeat(2) + countB);
            writer.newLine();
            writer.write("\tC (70-79):" + "\t".repeat(2) + countC);
            writer.newLine();
            writer.write("\tD (60-69):" + "\t".repeat(2) + countD);
            writer.newLine();
            writer.write("\tF (Below 60):" + "\t".repeat(1) + countF);

            System.out.println("\n" + String.format("Average score:\t %.2f", avg));
            System.out.println(String.format("Highest score:\t %d", high));
            System.out.println(String.format("Lowest score:\t %d", low));
            System.out.println("\nGrade distribution:");
            System.out.println("\tA (90-100):" + "\t".repeat(1) + countA);
            System.out.println("\tB (80-89):" + "\t".repeat(1) + countB);
            System.out.println("\tC (70-79):" + "\t".repeat(1) + countC);
            System.out.println("\tD (60-69):" + "\t".repeat(1) + countD);
            System.out.println("\tF (Below 60):" + "\t".repeat(1) + countF);

        }catch(IOException e){
            System.out.println("File cannot be written: " + e.getMessage());
        }
    }
}
