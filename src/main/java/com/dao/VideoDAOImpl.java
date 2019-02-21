package main.java.com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.java.com.model.Video;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * An implementation of the VideoDAO interface.
 *
 */

public class VideoDAOImpl implements VideoDAO{

   private JdbcTemplate jdbcTemplate;
   
   public VideoDAOImpl(DataSource dataSource) {
      jdbcTemplate = new JdbcTemplate(dataSource);
   }

   @Override
   public List<Video> list() {
      String sql = "SELECT * FROM VIDEO";
      System.out.println("****** " + sql);
      List<Video> listVideo = jdbcTemplate.query(sql, new RowMapper<Video>() {

         @Override
         public Video mapRow(ResultSet rs, int rowNum) throws SQLException {
            Video aVideo = new Video();
   
            aVideo.setId(rs.getInt("id"));
            aVideo.setTitle(rs.getString("title"));
            aVideo.setLink(rs.getString("link"));
            aVideo.setRecruiter(rs.getString("recruiter"));
            aVideo.setCandidate(rs.getString("candidate"));
            aVideo.setFaceCount(rs.getInt("faceCount"));
            aVideo.setNeutral(rs.getInt("neutral"));
            aVideo.setAnger(rs.getInt("anger"));
            aVideo.setDisgust(rs.getInt("disgust"));
            aVideo.setHappy(rs.getInt("happy"));
            aVideo.setSurprise(rs.getInt("surprise"));
            aVideo.setComments(rs.getString("comments"));

            return aVideo;
         }
         
      });
      
      return listVideo;
   }

   @Override
   public void save (Video video) {
      String sql = "INSERT INTO VIDEO (Title, Link, Recruiter, Candidate, FaceCount, Neutral, Anger, Disgust, Happy, Surprise, Comments)"
                  + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
         jdbcTemplate.update(sql, video.getTitle(), video.getLink(), video.getRecruiter(), video.getCandidate(), video.getFaceCount(), 
            video.getNeutral(), video.getAnger(), video.getDisgust(), video.getHappy(), video.getSurprise(), video.getComments());

   }

   @Override
   public void update (String video, int[] emotionList) {
      if (video != null && !video.isEmpty()) {
         int sum = 0;
        for(int i=0;i<emotionList.length;i++)
            sum += emotionList[i];
         String sql = "UPDATE video SET FaceCount=?, Neutral=?, Anger=?, Disgust=?, Happy=?, Surprise=? "
                  + "WHERE link=?";
         jdbcTemplate.update(sql, sum, emotionList[0], emotionList[1], emotionList[2], emotionList[3], emotionList[4], video);
      } 
   }

   @Override
   public Video get(String link) {
      String sql = "SELECT * FROM video WHERE link = ?";
      return jdbcTemplate.query(sql, new Object[] { link }, new ResultSetExtractor<Video>() {

         @Override
         public Video extractData(ResultSet rs) throws SQLException,
               DataAccessException {
            if (rs.next()) {
               Video aVideo = new Video();
               aVideo.setId(rs.getInt("id"));
               aVideo.setTitle(rs.getString("title"));
               aVideo.setLink(rs.getString("link"));
               aVideo.setRecruiter(rs.getString("recruiter"));
               aVideo.setCandidate(rs.getString("candidate"));
               aVideo.setFaceCount(rs.getInt("faceCount"));
               aVideo.setNeutral(rs.getInt("neutral"));
               aVideo.setAnger(rs.getInt("anger"));
               aVideo.setDisgust(rs.getInt("disgust"));
               aVideo.setHappy(rs.getInt("happy"));
               aVideo.setSurprise(rs.getInt("surprise"));
               aVideo.setComments(rs.getString("comments"));

               return aVideo;
            }
            
            return null;
         }
         
      });
   }

   @Override
   public void updateComments (String video, String comments) {
         if (video != null && !video.isEmpty()) {
         String sql = "UPDATE video SET Comments=? "
                  + " WHERE link=?";
         jdbcTemplate.update(sql, comments, video);
      } 
   }

   @Override
   public void delete(String video) {
      if (video != null && !video.isEmpty()) {
         String sql = "DELETE FROM video WHERE link=?";
         jdbcTemplate.update(sql, video);
      }
   }
}

