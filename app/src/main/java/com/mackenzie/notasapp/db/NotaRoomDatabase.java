package com.mackenzie.notasapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mackenzie.notasapp.db.dao.NotaDao;
import com.mackenzie.notasapp.db.entity.NoteEntity;

// este decorador define esta clase como  base de datos ROOM
@Database(entities = {NoteEntity.class}, version = 1)
public abstract class NotaRoomDatabase extends RoomDatabase {

    // Declaramos la interfaz dao para notas
    public abstract NotaDao notaDao();

    // Variable que contendra la instancia
    private static volatile NotaRoomDatabase INSTANCE;

    // Aqui recuperamos la intancia de la DB
    public static NotaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotaRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotaRoomDatabase.class, "notas_database").build();
                }
            }
        }

        return INSTANCE;
    }

}
