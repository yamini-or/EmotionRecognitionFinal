package main.java.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.com.dao.VideoDAO;
import main.java.com.model.Video;


/**
 * An servcie implementation of the VideoDAO interface.
 *
 */

@Service
@Transactional(readOnly = true)
public class VideoServiceImpl implements VideoService {
/*
   @Autowired
   private VideoDAO videoDao;

   @Transactional
   @Override
   public long save(Video video) {
      return videoDao.save(video);
   }

   @Override
   public Video get(long id) {
      return videoDao.get(id);
   }

   @Override
   public List<Video> list() {
      return videoDao.list();
   }

   @Transactional
   @Override
   public void update(long id, Video video) {
      videoDao.update(id, video);
   }

   @Transactional
   @Override
   public void delete(long id) {
      videoDao.delete(id);
   }
*/
}
