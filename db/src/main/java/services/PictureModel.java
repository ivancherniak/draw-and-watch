package services;

import model.Comment;
import model.Picture;

import java.util.List;

/**
 * This class contains information about picture, its likes and comments
 */
public class PictureModel {
    /**
     * Picture itself
     */
    private Picture picture;
    /**
     * Amount of likes
     */
    private int likes;
    /**
     * Comments under picture
     */
    private List<Comment> comments;

    /**
     * Getter for picture
     *
     * @return instance of picture
     */
    public Picture getPicture() {
        return picture;
    }

    /**
     * Setter for picture
     *
     * @param picture instance of picture
     */
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    /**
     * Getter for likes
     *
     * @return amount of likes under picture
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Setter for likes
     *
     * @param likes amount of likes under picture
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * Getter for comments
     *
     * @return list of comments under picture
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Setter for comments
     *
     * @param comments list of comments under picture
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * This constructor creates instance of PictureModel
     *
     * @param picture  picture itself
     * @param likes    amount of likes under picture
     * @param comments list of comments under picture
     */
    public PictureModel(Picture picture, int likes, List<Comment> comments) {
        this.picture = picture;
        this.likes = likes;
        this.comments = comments;
    }
}
