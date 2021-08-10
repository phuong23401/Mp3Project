package com.karaoke.mp3project.model;

import lombok.Data;

@Data
public class AddSongToPlaylistReq {

    private String idSong;
    private String idPlaylist;


}
