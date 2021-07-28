package com.karaoke.mp3project.service;


import com.karaoke.mp3project.model.CommentPlayList;
import com.karaoke.mp3project.model.PlayList;

public interface ICommentplaylistService {
    Iterable<CommentPlayList> findAllByPlaylist(PlayList playlist);

    void saveCommentplaylist(CommentPlayList commentplaylist);
}
