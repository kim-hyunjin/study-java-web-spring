package com.eomcs.lms.domain;

import java.sql.Date;

public class Board {
  private int no;
  private String title;
  private Date date;
  private int viewCount;

  // CSV 포맷:
  // 번호,제목,등록일,조회수
  public static Board valueOf(String csv) {
    String[] data = csv.split(",");

    Board b = new Board();
    b.setNo(Integer.parseInt(data[0]));
    b.setTitle(data[1]);
    b.setDate(Date.valueOf(data[2]));
    b.setViewCount(Integer.parseInt(data[3]));

    return b;
  }

  public String toCsvString() {
    return String.format("%d,%s,%s,%d", this.getNo(), this.getTitle(), this.getDate(),
        this.getViewCount());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + no;
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + viewCount;
    result = prime * result + ((writer == null) ? 0 : writer.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Board other = (Board) obj;
    if (date == null) {
      if (other.date != null)
        return false;
    } else if (!date.equals(other.date))
      return false;
    if (no != other.no)
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (viewCount != other.viewCount)
      return false;
    if (writer == null) {
      if (other.writer != null)
        return false;
    } else if (!writer.equals(other.writer))
      return false;
    return true;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public String writer;
}
