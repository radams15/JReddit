/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package uk.co.therhys.CReddit;

public class libredditJNI {
  public final static native long new_PostVector__SWIG_0();
  public final static native long new_PostVector__SWIG_1(long jarg1, PostVector jarg1_);
  public final static native long PostVector_capacity(long jarg1, PostVector jarg1_);
  public final static native void PostVector_reserve(long jarg1, PostVector jarg1_, long jarg2);
  public final static native boolean PostVector_isEmpty(long jarg1, PostVector jarg1_);
  public final static native void PostVector_clear(long jarg1, PostVector jarg1_);
  public final static native long new_PostVector__SWIG_2(int jarg1, long jarg2, Post jarg2_);
  public final static native int PostVector_doSize(long jarg1, PostVector jarg1_);
  public final static native void PostVector_doAdd__SWIG_0(long jarg1, PostVector jarg1_, long jarg2, Post jarg2_);
  public final static native void PostVector_doAdd__SWIG_1(long jarg1, PostVector jarg1_, int jarg2, long jarg3, Post jarg3_);
  public final static native long PostVector_doRemove(long jarg1, PostVector jarg1_, int jarg2);
  public final static native long PostVector_doGet(long jarg1, PostVector jarg1_, int jarg2);
  public final static native long PostVector_doSet(long jarg1, PostVector jarg1_, int jarg2, long jarg3, Post jarg3_);
  public final static native void PostVector_doRemoveRange(long jarg1, PostVector jarg1_, int jarg2, int jarg3);
  public final static native void delete_PostVector(long jarg1);
  public final static native long new_SubredditVector__SWIG_0();
  public final static native long new_SubredditVector__SWIG_1(long jarg1, SubredditVector jarg1_);
  public final static native long SubredditVector_capacity(long jarg1, SubredditVector jarg1_);
  public final static native void SubredditVector_reserve(long jarg1, SubredditVector jarg1_, long jarg2);
  public final static native boolean SubredditVector_isEmpty(long jarg1, SubredditVector jarg1_);
  public final static native void SubredditVector_clear(long jarg1, SubredditVector jarg1_);
  public final static native long new_SubredditVector__SWIG_2(int jarg1, long jarg2, Subreddit jarg2_);
  public final static native int SubredditVector_doSize(long jarg1, SubredditVector jarg1_);
  public final static native void SubredditVector_doAdd__SWIG_0(long jarg1, SubredditVector jarg1_, long jarg2, Subreddit jarg2_);
  public final static native void SubredditVector_doAdd__SWIG_1(long jarg1, SubredditVector jarg1_, int jarg2, long jarg3, Subreddit jarg3_);
  public final static native long SubredditVector_doRemove(long jarg1, SubredditVector jarg1_, int jarg2);
  public final static native long SubredditVector_doGet(long jarg1, SubredditVector jarg1_, int jarg2);
  public final static native long SubredditVector_doSet(long jarg1, SubredditVector jarg1_, int jarg2, long jarg3, Subreddit jarg3_);
  public final static native void SubredditVector_doRemoveRange(long jarg1, SubredditVector jarg1_, int jarg2, int jarg3);
  public final static native void delete_SubredditVector(long jarg1);
  public final static native void Reddit_authenticated_set(long jarg1, Reddit jarg1_, boolean jarg2);
  public final static native boolean Reddit_authenticated_get(long jarg1, Reddit jarg1_);
  public final static native long new_Reddit__SWIG_0(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native long new_Reddit__SWIG_1(String jarg1, String jarg2);
  public final static native long Reddit_get_headers(long jarg1, Reddit jarg1_);
  public final static native int Reddit_get_posts_hot(long jarg1, Reddit jarg1_, long jarg2, String jarg3, long jarg4, long jarg5);
  public final static native long Reddit_get_subbed_list(long jarg1, Reddit jarg1_);
  public final static native long Reddit_get_posts_hot_list(long jarg1, Reddit jarg1_, long jarg2, String jarg3);
  public final static native int Reddit_AUTHENTICATED_get();
  public final static native int Reddit_UNAUTHENTICATED_get();
  public final static native void delete_Reddit(long jarg1);
  public final static native long process_post(long jarg1, Reddit jarg1_, long jarg2);
  public final static native void Post_title_set(long jarg1, Post jarg1_, String jarg2);
  public final static native String Post_title_get(long jarg1, Post jarg1_);
  public final static native void Post_author_set(long jarg1, Post jarg1_, String jarg2);
  public final static native String Post_author_get(long jarg1, Post jarg1_);
  public final static native void Post_subreddit_set(long jarg1, Post jarg1_, long jarg2, Subreddit jarg2_);
  public final static native long Post_subreddit_get(long jarg1, Post jarg1_);
  public final static native void Post_text_set(long jarg1, Post jarg1_, String jarg2);
  public final static native String Post_text_get(long jarg1, Post jarg1_);
  public final static native void Post_thumbnail_set(long jarg1, Post jarg1_, String jarg2);
  public final static native String Post_thumbnail_get(long jarg1, Post jarg1_);
  public final static native void Post_url_set(long jarg1, Post jarg1_, String jarg2);
  public final static native String Post_url_get(long jarg1, Post jarg1_);
  public final static native void Post_upvotes_set(long jarg1, Post jarg1_, long jarg2);
  public final static native long Post_upvotes_get(long jarg1, Post jarg1_);
  public final static native void Post_downvotes_set(long jarg1, Post jarg1_, long jarg2);
  public final static native long Post_downvotes_get(long jarg1, Post jarg1_);
  public final static native void Post_score_set(long jarg1, Post jarg1_, long jarg2);
  public final static native long Post_score_get(long jarg1, Post jarg1_);
  public final static native void Post_id_set(long jarg1, Post jarg1_, String jarg2);
  public final static native String Post_id_get(long jarg1, Post jarg1_);
  public final static native void Post_type_set(long jarg1, Post jarg1_, String jarg2);
  public final static native String Post_type_get(long jarg1, Post jarg1_);
  public final static native int Post_get_comments(long jarg1, Post jarg1_, long jarg2, String jarg3, long jarg4, long jarg5);
  public final static native int Post_get_comments_t(long jarg1, Post jarg1_, long jarg2, String jarg3, long jarg4, long jarg5);
  public final static native String Post_fullname(long jarg1, Post jarg1_);
  public final static native String Post_full_url(long jarg1, Post jarg1_);
  public final static native boolean Post_is_img(long jarg1, Post jarg1_);
  public final static native long new_Post();
  public final static native void delete_Post(long jarg1);
  public final static native void Subreddit_name_set(long jarg1, Subreddit jarg1_, String jarg2);
  public final static native String Subreddit_name_get(long jarg1, Subreddit jarg1_);
  public final static native void Subreddit_subs_set(long jarg1, Subreddit jarg1_, long jarg2);
  public final static native long Subreddit_subs_get(long jarg1, Subreddit jarg1_);
  public final static native void Subreddit_reddit_set(long jarg1, Subreddit jarg1_, long jarg2, Reddit jarg2_);
  public final static native long Subreddit_reddit_get(long jarg1, Subreddit jarg1_);
  public final static native long new_Subreddit__SWIG_0(long jarg1, Reddit jarg1_, String jarg2, String jarg3);
  public final static native long new_Subreddit__SWIG_1(long jarg1, Reddit jarg1_, String jarg2);
  public final static native int Subreddit_get_posts(long jarg1, Subreddit jarg1_, String jarg2, long jarg3, String jarg4, long jarg5, long jarg6);
  public final static native long Subreddit_get_posts_list(long jarg1, Subreddit jarg1_, String jarg2, long jarg3, String jarg4);
  public final static native int Subreddit_get_posts_t(long jarg1, Subreddit jarg1_, String jarg2, long jarg3, String jarg4, long jarg5, long jarg6);
  public final static native void delete_Subreddit(long jarg1);
  public final static native void Comment_post_set(long jarg1, Comment jarg1_, long jarg2, Post jarg2_);
  public final static native long Comment_post_get(long jarg1, Comment jarg1_);
  public final static native void Comment_title_set(long jarg1, Comment jarg1_, String jarg2);
  public final static native String Comment_title_get(long jarg1, Comment jarg1_);
  public final static native void Comment_author_set(long jarg1, Comment jarg1_, String jarg2);
  public final static native String Comment_author_get(long jarg1, Comment jarg1_);
  public final static native void Comment_body_set(long jarg1, Comment jarg1_, String jarg2);
  public final static native String Comment_body_get(long jarg1, Comment jarg1_);
  public final static native void Comment_score_set(long jarg1, Comment jarg1_, long jarg2);
  public final static native long Comment_score_get(long jarg1, Comment jarg1_);
  public final static native void Comment_id_set(long jarg1, Comment jarg1_, String jarg2);
  public final static native String Comment_id_get(long jarg1, Comment jarg1_);
  public final static native void Comment_thumbnail_set(long jarg1, Comment jarg1_, String jarg2);
  public final static native String Comment_thumbnail_get(long jarg1, Comment jarg1_);
  public final static native void Comment_url_set(long jarg1, Comment jarg1_, String jarg2);
  public final static native String Comment_url_get(long jarg1, Comment jarg1_);
  public final static native void Comment_children_set(long jarg1, Comment jarg1_, long jarg2);
  public final static native long Comment_children_get(long jarg1, Comment jarg1_);
  public final static native void Comment_no_children_set(long jarg1, Comment jarg1_, long jarg2);
  public final static native long Comment_no_children_get(long jarg1, Comment jarg1_);
  public final static native long new_Comment();
  public final static native void delete_Comment(long jarg1);
}
