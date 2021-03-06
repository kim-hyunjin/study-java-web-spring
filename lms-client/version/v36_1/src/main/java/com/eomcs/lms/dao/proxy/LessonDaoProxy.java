package com.eomcs.lms.dao.proxy;

import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;


// 프록시 객체는 작업 객체와 동일한 인터페이스를 구현해야 한다.

public class LessonDaoProxy implements LessonDao{
  DaoProxyHelper daoProxyHelper;

  public LessonDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Lesson lesson) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/lesson/add");
      out.writeObject(lesson);
      out.flush();

      String response = in.readUTF();
      if(response.equals("FAIL")) {
        return 0;
      }
      return 1;
    });
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Lesson> findAll() throws Exception {
    return (List<Lesson>) daoProxyHelper.request((in, out) -> {out.writeUTF("/lesson/list");
    out.flush();
    String response = in.readUTF();
    if (response.equals("FAIL")) {
      return null;
    }
    return (List<Lesson>) in.readObject();
    });
  }

  @Override
  public Lesson findByNo(int no) throws Exception {
    return (Lesson) daoProxyHelper.request((in, out) -> {out.writeUTF("/lesson/detail");
    out.writeInt(no);
    out.flush();
    String response = in.readUTF();
    if (response.equals("FAIL")) {
      return null;
    }
    return (Lesson) in.readObject();});
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    return (int)daoProxyHelper.request((in,out) -> {out.writeUTF("/lesson/update");
    out.writeObject(lesson);
    out.flush();
    String response = in.readUTF();
    if (response.equals("FAIL")) {
      return 0;
    }
    return 1;});
  }

  @Override
  public int delete(int no) throws Exception {
    return (int)daoProxyHelper.request((in, out) -> {out.writeUTF("/lesson/delete");
    out.writeInt(no);
    out.flush();
    String response = in.readUTF();
    if (response.equals("FAIL")) {
      return 0;
    }
    return 1;});
  }

} // LessonDaoProxy {}
