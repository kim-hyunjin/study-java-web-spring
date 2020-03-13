package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;
import com.eomcs.util.Prompt;

public class PhotoBoardAddServlet implements Servlet {

  LessonService lessonService;
  PhotoBoardService photoBoardService;

  public PhotoBoardAddServlet(LessonService lessonService, PhotoBoardService photoBoardService) {
    this.lessonService = lessonService;
    this.photoBoardService = photoBoardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(Prompt.getString(in, out, "제목?"));

    int no = Prompt.getInt(in, out, "수업 번호?");

    Lesson lesson = lessonService.get(no);
    if (lesson == null) {
      out.println("수업 번호가 유효하지 않습니다.");
      return;
    }

    photoBoard.setLesson(lesson);
    // 사용자로부터 사진 게시글에 첨부할 파일을 입력받는다.
    List<PhotoFile> photoFiles = inputPhotoFiles(in, out);
    photoBoard.setFiles(photoFiles);

    photoBoardService.add(photoBoard);
    out.println("새 사진 게시물을 등록했습니다.");
  }

  private List<PhotoFile> inputPhotoFiles(Scanner in, PrintStream out) {
    // 첨부파일을 입력받는다.
    out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    out.println("파일명 입력 없이 엔터를 치면 파일 추가를 마칩니다.");
    ArrayList<PhotoFile> files = new ArrayList<>();
    while (true) {
      String filepath = Prompt.getString(in, out, "사진 파일?");
      if (filepath.length() == 0) {
        if (files.size() > 0) {
          break;
        } else {
          out.println("최소 한 개의 사진 파일을 등록해야합니다.");
          continue;
        }
      }
      files.add(new PhotoFile().setFilePath(filepath));
    }
    return files;
  }
}