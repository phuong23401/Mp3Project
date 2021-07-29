package com.karaoke.mp3project.service.impl;


import com.karaoke.mp3project.model.CommentSong;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.repo.CommentSongRepo;
import com.karaoke.mp3project.service.ICommentSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentSongService implements ICommentSongService {
    @Autowired
    private CommentSongRepo commentSongRepo;

    @Override
    public Iterable<CommentSong> findAllBySong(Song song) {
        return commentSongRepo.findAllBySong(song);
    }

    @Override
    public void saveComment(CommentSong commentsong) {
        commentSongRepo.save(commentsong);
    }
}
