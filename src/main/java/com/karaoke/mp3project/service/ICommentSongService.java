package com.karaoke.mp3project.service;


import com.karaoke.mp3project.model.CommentSong;
import com.karaoke.mp3project.model.Song;

public interface ICommentSongService {
    Iterable<CommentSong> findAllBySong(Song song);

    void saveComment(CommentSong commentsong);
}
