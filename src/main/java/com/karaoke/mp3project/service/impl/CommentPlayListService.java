package com.karaoke.mp3project.service.impl;


import com.karaoke.mp3project.model.CommentPlayList;
import com.karaoke.mp3project.model.PlayList;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.repo.CommentPlayListRepo;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.ICommentPlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class CommentPlayListService implements ICommentPlayListService {
    @Autowired
    private CommentPlayListRepo commentPlayListRepo;

    @Autowired
    private UserDtService userDtService;

    @Override
    public Iterable<CommentPlayList> findAllByPlaylist(PlayList playlist) {
        return commentPlayListRepo.findAllByPlaylist(playlist);
    }

    @Override
    public void saveCommentplaylist(CommentPlayList commentplaylist) {
        commentPlayListRepo.save(commentplaylist);
    }


    public void cmtPlaylist(CommentPlayList commentPlayList){
        User user = userDtService.getCurrentUser();
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        commentPlayList.setCreatedTime(createdTime);
        commentPlayList.setUser(user);
        commentPlayListRepo.save(commentPlayList);
    }
}
