/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package uk.co.therhys.CReddit;

public class Reddit {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected Reddit(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Reddit obj) {
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
        libredditJNI.delete_Reddit(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setAuthenticated(boolean value) {
    libredditJNI.Reddit_authenticated_set(swigCPtr, this, value);
  }

  public boolean getAuthenticated() {
    return libredditJNI.Reddit_authenticated_get(swigCPtr, this);
  }

  public Reddit(String username, String password, String client_id, String secret) {
    this(libredditJNI.new_Reddit__SWIG_0(username, password, client_id, secret), true);
  }

  public Reddit(String username, String token) {
    this(libredditJNI.new_Reddit__SWIG_1(username, token), true);
  }

  public SWIGTYPE_p_Headers get_headers() {
    long cPtr = libredditJNI.Reddit_get_headers(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_Headers(cPtr, false);
  }

  public int get_posts_hot(long limit, String before, SWIGTYPE_p_f_p_Post_p_void__void callback, SWIGTYPE_p_void ptr) {
    return libredditJNI.Reddit_get_posts_hot(swigCPtr, this, limit, before, SWIGTYPE_p_f_p_Post_p_void__void.getCPtr(callback), SWIGTYPE_p_void.getCPtr(ptr));
  }

  public SubredditVector get_subbed_list() {
    return new SubredditVector(libredditJNI.Reddit_get_subbed_list(swigCPtr, this), true);
  }

  public PostVector get_posts_hot_list(long limit, String after) {
    return new PostVector(libredditJNI.Reddit_get_posts_hot_list(swigCPtr, this, limit, after), true);
  }

  public final static int AUTHENTICATED = libredditJNI.Reddit_AUTHENTICATED_get();
  public final static int UNAUTHENTICATED = libredditJNI.Reddit_UNAUTHENTICATED_get();

}