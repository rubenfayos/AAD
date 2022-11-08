/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication38;

/**
 *
 * @author denos
 */
// Java Program to Illustrate Creation of Simple POJO Class
 
// Importing required classes
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "song")
 
// POJO class
public class Song {
 
    @Id @Column(name = "songId") private int id;
 
    @Column(name = "songName") private String songName;
 
    @Column(name = "singer") private String artist;
 
    public int getId() { return id; }
 
    public void setId(int id) { this.id = id; }
 
    public String getSongName() { return songName; }
 
    public void setSongName(String songName)
    {
        this.songName = songName;
    }
 
    public String getArtist() { return artist; }
 
    public void setArtist(String artist)
    {
        this.artist = artist;
    }
}
