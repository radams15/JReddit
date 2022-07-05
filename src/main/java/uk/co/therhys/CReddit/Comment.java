/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package uk.co.therhys.CReddit;

public class Comment {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected Comment(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Comment obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  @SuppressWarnings("deprecation")
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libredditJNI.delete_Comment(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setPost(Post value) {
    libredditJNI.Comment_post_set(swigCPtr, this, Post.getCPtr(value), value);
  }

  public Post getPost() {
    long cPtr = libredditJNI.Comment_post_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Post(cPtr, false);
  }

  public void setTitle(String value) {
    libredditJNI.Comment_title_set(swigCPtr, this, value);
  }

  public String getTitle() {
    return libredditJNI.Comment_title_get(swigCPtr, this);
  }

  public void setAuthor(String value) {
    libredditJNI.Comment_author_set(swigCPtr, this, value);
  }

  public String getAuthor() {
    return libredditJNI.Comment_author_get(swigCPtr, this);
  }

  public void setBody(String value) {
    libredditJNI.Comment_body_set(swigCPtr, this, value);
  }

  public String getBody() {
    return libredditJNI.Comment_body_get(swigCPtr, this);
  }

  public void setScore(long value) {
    libredditJNI.Comment_score_set(swigCPtr, this, value);
  }

  public long getScore() {
    return libredditJNI.Comment_score_get(swigCPtr, this);
  }

  public void setId(String value) {
    libredditJNI.Comment_id_set(swigCPtr, this, value);
  }

  public String getId() {
    return libredditJNI.Comment_id_get(swigCPtr, this);
  }

  public void setThumbnail(String value) {
    libredditJNI.Comment_thumbnail_set(swigCPtr, this, value);
  }

  public String getThumbnail() {
    return libredditJNI.Comment_thumbnail_get(swigCPtr, this);
  }

  public void setUrl(String value) {
    libredditJNI.Comment_url_set(swigCPtr, this, value);
  }

  public String getUrl() {
    return libredditJNI.Comment_url_get(swigCPtr, this);
  }

  public void setChildren(SWIGTYPE_p_p_void value) {
    libredditJNI.Comment_children_set(swigCPtr, this, SWIGTYPE_p_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_p_void getChildren() {
    long cPtr = libredditJNI.Comment_children_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_p_void(cPtr, false);
  }

  public void setNo_children(long value) {
    libredditJNI.Comment_no_children_set(swigCPtr, this, value);
  }

  public long getNo_children() {
    return libredditJNI.Comment_no_children_get(swigCPtr, this);
  }

  public Comment() {
    this(libredditJNI.new_Comment(), true);
  }

}
