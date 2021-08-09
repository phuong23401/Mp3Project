package com.karaoke.mp3project.service;


import com.karaoke.mp3project.model.CommentSong;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.model.User;

public interface ICommentSongService {
    Iterable<CommentSong> findAllBySong(Song song);

    void saveComment(CommentSong commentsong);

    void cmtSong(CommentSong commentSong, User user);
}
