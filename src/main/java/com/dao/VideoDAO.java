package main.java.com.dao;

import java.util.List;
import main.java.com.model.Video;
/**
 * Defines DAO operations for the video upload model.
 *
 */
public interface VideoDAO {
	
    public List<Video> list();
    public void save (Video video);
    public void update (String video, int[] emotionList);
    public Video get(String videoLink);
    public void updateComments (String video, String comments);
    public void delete(String video);

}