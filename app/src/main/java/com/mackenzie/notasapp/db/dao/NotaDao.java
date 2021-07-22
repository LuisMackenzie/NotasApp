package com.mackenzie.notasapp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mackenzie.notasapp.db.entity.NoteEntity;

import java.util.List;

@Dao
public interface NotaDao {
    @Insert
    public abstract void insert(NoteEntity nota);

    @Update
    public abstract void update(NoteEntity nota);

    @Query("DELETE FROM Notas")
    public abstract void deleteAll();

    @Query("DELETE FROM Notas WHERE id = :idNota")
    public abstract void deleteById(int idNota);

    @Query("SELECT * FROM Notas ORDER BY fecha ASC")
    public abstract LiveData<List<NoteEntity>> getAllNotes();

    @Query("SELECT * FROM Notas WHERE Favoritos LIKE 'true'")
    public abstract LiveData<List<NoteEntity>> getAllFavoritas();

}
