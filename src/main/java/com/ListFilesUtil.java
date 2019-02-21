package main.java.com;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.activation.MimetypesFileTypeMap;


public class ListFilesUtil {



   private static String getFileExtension(File file) {
        String extension = "";
 
        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }
 
        return extension;
 
    }

    private static boolean isVideoCheck(File file) {
        String extension = getFileExtension(file);
        boolean isVideo =  extension.equals(".mp4") || extension.equals(".flv") || extension.equals(".avi") || 
        extension.equals(".mov") || extension.equals(".mpg") || extension.equals(".wmv") || extension.equals(".3gp") || 
        extension.equals(".asf") || extension.equals(".rm") || extension.equals(".swf");
        return isVideo;    
    }

    public static boolean isImageCheck(File file) {
        String mimeType = new MimetypesFileTypeMap().getContentType(file);
        // mimeType should now be something like "image/png"

        if(mimeType.substring(0,5).equalsIgnoreCase("image"))
            return true;
        return false;
    }

	public String listFiles(String directoryName){
		//System.out.println("Inside list files******");

        File directory = new File(directoryName);
        //get all the files from a directory
        String videoList = "";
        File[] fList = directory.listFiles();
        int countOfVideos = 0;
        for (File file : fList){
            if (file.isFile() && (isVideoCheck(file) || isImageCheck(file) )){
                countOfVideos++;
                System.out.println(file.getName()); 
                if (countOfVideos == 1)      
                    videoList = file.getName();        
                else    
                    videoList = videoList + "," + file.getName();
            }
        }
        return videoList;
    }

}