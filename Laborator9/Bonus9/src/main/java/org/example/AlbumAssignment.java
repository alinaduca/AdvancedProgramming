package org.example;

import org.example.models.Album;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class AlbumAssignment {
    private Album album;
    @PlanningVariable(valueRangeProviderRefs = "letterRange")
    private char assignedLetter;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public AlbumAssignment(Album album, char assignedLetter) {
        this.album = album;
        this.assignedLetter = assignedLetter;
    }

    public char getAssignedLetter() {
        return assignedLetter;
    }

    public void setAssignedLetter(char assignedLetter) {
        this.assignedLetter = assignedLetter;
    }
}
