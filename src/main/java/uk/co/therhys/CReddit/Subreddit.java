/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package uk.co.therhys.CReddit;

public class Subreddit {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected Subreddit(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Subreddit obj) {
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
        libredditJNI.delete_Subreddit(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setName(String value) {
    libredditJNI.Subreddit_name_set(swigCPtr, this, value);
  }

  public String getName() {
    return libredditJNI.Subreddit_name_get(swigCPtr, this);
  }

  public void setSubs(long value) {
    libredditJNI.Subreddit_subs_set(swigCPtr, this, value);
  }

  public long getSubs() {
    return libredditJNI.Subreddit_subs_get(swigCPtr, this);
  }

  public void setReddit(Reddit value) {
    libredditJNI.Subreddit_reddit_set(swigCPtr, this, Reddit.getCPtr(value), value);
  }

  public Reddit getReddit() {
    long cPtr = libredditJNI.Subreddit_reddit_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Reddit(cPtr, false);
  }

  public Subreddit(Reddit reddit, String name, String url) {
    this(libredditJNI.new_Subreddit__SWIG_0(Reddit.getCPtr(reddit), reddit, name, url), true);
  }

  public Subreddit(Reddit reddit, String name) {
    this(libredditJNI.new_Subreddit__SWIG_1(Reddit.getCPtr(reddit), reddit, name), true);
  }

  public int get_posts(String type, long limit, String before, SWIGTYPE_p_f_p_Post_p_void__void callback, SWIGTYPE_p_void ptr) {
    return libredditJNI.Subreddit_get_posts(swigCPtr, this, type, limit, before, SWIGTYPE_p_f_p_Post_p_void__void.getCPtr(callback), SWIGTYPE_p_void.getCPtr(ptr));
  }

  public PostVector get_posts_list(String type, long limit, String after) {
    return new PostVector(libredditJNI.Subreddit_get_posts_list(swigCPtr, this, type, limit, after), true);
  }

  public int get_posts_t(String type, long limit, String after, SWIGTYPE_p_f_p_Post_p_void__void callback, SWIGTYPE_p_void ptr) {
    return libredditJNI.Subreddit_get_posts_t(swigCPtr, this, type, limit, after, SWIGTYPE_p_f_p_Post_p_void__void.getCPtr(callback), SWIGTYPE_p_void.getCPtr(ptr));
  }

}
