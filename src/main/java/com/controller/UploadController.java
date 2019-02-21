package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;

import javax.servlet.ServletException;

import main.java.com.ListFilesUtil;
import main.java.com.RunPythonUtil;
import main.java.com.dao.VideoDAO;
import main.java.com.model.Video;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller

public class UploadController {

    @Autowired
    private VideoDAO videoDAO;
    private static String UPLOADED_FOLDER = "//Users//yaminiaggarwal//Desktop//Emotion//emotionModel//src//main//webapp//resources//images//";

    @RequestMapping(value="/")
    public ModelAndView listVideo(ModelAndView model) throws IOException{
        List<Video> listVideo = videoDAO.list();
        model.addObject("listVideo", listVideo);
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value="/homePage", params="act1", method = RequestMethod.POST)
    public String home1(ModelMap model) {
        ListFilesUtil listFilesUtil = new ListFilesUtil();
        String videoList = listFilesUtil.listFiles(UPLOADED_FOLDER);
        model.addAttribute("files", videoList);
        return "viewFiles";
    }

    

    @RequestMapping(value="/homePage", params="act2", method = RequestMethod.POST)
    public String home2(Model model) {
        model.addAttribute("video", new Video()); 
        return "video";
    }

    @PostMapping("/viewFiles") //new annotation since 4.3
    public String selectVideo(@RequestParam("video") String video,
                                   RedirectAttributes redirectAttributes) {
        if (StringUtils.isEmpty(video)) {
                redirectAttributes.addFlashAttribute("message", "Please select a video for analysis");
                redirectAttributes.addFlashAttribute("video", "");
            } else {
                redirectAttributes.addFlashAttribute("message", "You selected '" + video + "'");
                redirectAttributes.addFlashAttribute("video", video);
            }
        return "redirect:/uploadStatus";
    }

    @PostMapping("/video")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   @ModelAttribute("video") Video video1, RedirectAttributes redirectAttributes) 
                                    throws ServletException, IOException {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            redirectAttributes.addFlashAttribute("video", "");
            return "redirect:uploadStatus";
        }

        try {

            videoDAO.save(video1);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("video", file.getOriginalFilename());
        } catch (Throwable e) {
            throw new NestedServletException("Please upload file with different name", e);

        } 

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus(@ModelAttribute("video") String video,
                               RedirectAttributes redirectAttributes) {
        
        redirectAttributes.addFlashAttribute("video", video);
        return "uploadStatus";
    }


    //@PostMapping("/startAnalysis")
    @RequestMapping(value = "/startAnalysis", method = {RequestMethod.POST, RequestMethod.GET})
    public String startAnalysis(@RequestParam("video") String video,
                                Model model) {

        int[] emotionList = new int[5];
        Video availableVideo = videoDAO.get(video);
        
        if (availableVideo != null && availableVideo.getFaceCount() != 0) {
            emotionList[0] = availableVideo.getNeutral();
            emotionList[1] = availableVideo.getAnger();
            emotionList[2] = availableVideo.getDisgust();
            emotionList[3] = availableVideo.getHappy();
            emotionList[4] = availableVideo.getSurprise();
        } else {
            RunPythonUtil runPythonUtil = new RunPythonUtil();
            emotionList = runPythonUtil.runPython(video);
            videoDAO.update(video, emotionList);
        }

        model.addAttribute("video",video);
        model.addAttribute("emotionList", emotionList);

        return "analysisFinal";

    }
    @PostMapping("/saveComments")
    public String saveComments(@RequestParam("video") String video, @ModelAttribute("comments") String comments,
                               Model model) {
        videoDAO.updateComments(video, comments);
        model.addAttribute("message", "Comments saved successfully.");
        return "final";
    }

    @GetMapping("/deleteVideo")
    public String deleteVideo(@RequestParam("video") String video, Model model) {
        videoDAO.delete(video);
        model.addAttribute("message", "File deleted successfully.");
        return "final";
    }    
}