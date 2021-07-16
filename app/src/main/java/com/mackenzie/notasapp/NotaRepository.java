package com.mackenzie.notasapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mackenzie.notasapp.db.NotaRoomDatabase;
import com.mackenzie.notasapp.db.dao.NotaDao;
import com.mackenzie.notasapp.db.entity.NoteEntity;

import java.util.List;

public class NotaRepository {

    private NotaDao notaDao;
    private LiveData<List<NoteEntity>> allNotas;
    private LiveData<List<NoteEntity>> allFavNotas;

    public NotaRepository(Application app) {
        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(app);
        notaDao = db.notaDao();
        allNotas =  notaDao.getAllNotes();
        allFavNotas = notaDao.getAllFavoritas();
    }

    public LiveData<List<NoteEntity>> getAll() {
       return allNotas;
    }

    public LiveData<List<NoteEntity>> getAllFavs() {
        return allFavNotas;
    }

    public void insert (NoteEntity nota) {

        new insertAsyncTask(notaDao).execute(nota);
    }

    private static class insertAsyncTask extends AsyncTask<NoteEntity, Void, Void> {
        private NotaDao notaDaoAsyncTask;

        insertAsyncTask(NotaDao dao) {
            notaDaoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            notaDaoAsyncTask.insert(noteEntities[0]);
            return null;
        }

    }

}
