package com.karaoke.mp3project.service.impl;


import com.karaoke.mp3project.model.CommentPlayList;
import com.karaoke.mp3project.model.PlayList;
import com.karaoke.mp3project.repo.CommentPlayListRepo;
import com.karaoke.mp3project.service.ICommentPlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentPlayListService implements ICommentPlayListService {
    @Autowired
    private CommentPlayListRepo commentPlayListRepo;

    @Override
    public Iterable<CommentPlayList> findAllByPlaylist(PlayList playlist) {
        return commentPlayListRepo.findAllByPlaylist(playlist);
    }

    @Override
    public void saveCommentplaylist(CommentPlayList commentplaylist) {
        commentPlayListRepo.save(commentplaylist);
    }
}
