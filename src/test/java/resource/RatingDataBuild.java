package resource;

import pojo.PostRating;

public class RatingDataBuild {
    public PostRating addRating(){
        PostRating postRating = new PostRating();
        postRating.setValue(7.5);
        return postRating;
    }
}
