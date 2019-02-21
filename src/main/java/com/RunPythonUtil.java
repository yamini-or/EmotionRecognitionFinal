package main.java.com;

import java.io.*;


public class RunPythonUtil {

    static int[] toIntArray(String[] arr) {
        int[] ints = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ints[i] = Integer.parseInt(arr[i]);
        }
        return ints;
    }

    static int[] parseLineToIntArray(String line) {
        //System.out.println("****in parsing");
        return toIntArray(line.split(","));
    }

	public int[] runPython(String video) {
	    String line = "";
        String lastLine = "";
        try{
            String filePath = "//Users//yaminiaggarwal//Desktop//Emotion//temp//emotion_recognition1.py";
            String pythonPath = "//Users//yaminiaggarwal//anaconda3//bin//python";
            String videoPath = "//Users//yaminiaggarwal//Desktop//Emotion//emotionModel//src//main//webapp//resources//images//" + video;
            System.out.println("***** " + videoPath);

            ProcessBuilder pb = new ProcessBuilder(pythonPath, filePath, videoPath);
            Process p = pb.start();

            BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            System.out.println("Running Python starts: " + line);
            line = bfr.readLine();
            System.out.println("First Line: " + line);
            while ((line = bfr.readLine()) != null){
                System.out.println("Python Output: " + line);
                lastLine = line;
            }

        } catch(Exception e) {
            System.out.println(e);
        }

        //System.out.println(lastLine);
        lastLine = lastLine.replaceAll("^\\[|\\]$", "");
        lastLine = lastLine.replaceAll("\\s","");
        //System.out.println(lastLine);
        return parseLineToIntArray(lastLine);
    }


}