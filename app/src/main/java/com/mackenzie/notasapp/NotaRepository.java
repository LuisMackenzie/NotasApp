package com.mackenzie.notasapp;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mackenzie.notasapp.db.NotaRoomDatabase;
import com.mackenzie.notasapp.db.dao.NotaDao;
import com.mackenzie.notasapp.db.entity.NoteEntity;

import java.util.ArrayList;
import java.util.List;

public class NotaRepository {

    private NotaDao notaDao;
    private NoteEntity entity;
    private LiveData<List<NoteEntity>> allNotas;
    private LiveData<List<NoteEntity>> allFavNotas;
    private LiveData<List<NoteEntity>> allNotasNotDeleted;

    public NotaRepository(Application app) {
        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(app);
        notaDao = db.notaDao();
        // entity = new NoteEntity();
        allNotas =  notaDao.getAllNotes();
        allFavNotas = notaDao.getAllFavoritas();
        // allNotasNotDeleted = notaDao.deleteById(entity.getId());
    }

    public LiveData<List<NoteEntity>> getAll() {
        if (allNotasNotDeleted != null) {
            return allNotasNotDeleted;
        } else {
            return allNotas;
        }
    }

    public LiveData<List<NoteEntity>> getAllFavs() {
        return allFavNotas;
    }

    public LiveData<List<NoteEntity>> deleteNote(int idNota) {
        // new DeleteAsyncTask(notaDao);
        List<NoteEntity> clonedNotes = new ArrayList<>();

        for(int i = 0;i < allNotas.getValue().size(); i++) {
            if (allNotas.getValue().get(i).getId() != idNota) {
                clonedNotes.add(allNotas.getValue().get(i));
            }
        }

        // allNotasNotDeleted = new ArrayList<>();
        // allNotasNotDeleted = clonedNotes;
        // allNotas.setValue(clonedNotes);


        return allNotasNotDeleted;
    }

    public void deleteFavNote(int idNota) {

    }

    public void deleteAllNotes() {
         // notaDao.deleteAll();
        new DeleteAllAsyncTask(notaDao);
    }

    public void insert (NoteEntity nota) {

        new insertAsyncTask(notaDao).execute(nota);
    }

    private static class DeleteAsyncTask extends AsyncTask<NoteEntity, Void, Void> {

        private NotaDao notaDaoAsyncTask;

        DeleteAsyncTask(NotaDao dao) {
            notaDaoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            notaDaoAsyncTask.deleteById(noteEntities[0].getId());
            return null;
        }

    }

    private static class DeleteAllAsyncTask extends AsyncTask<NoteEntity, Void, Void> {

        private NotaDao notaDaoAsyncTask;
        private LiveData<List<NoteEntity>> clonedNotes;

        DeleteAllAsyncTask(NotaDao dao) {
            notaDaoAsyncTask = dao;
            // notaDaoAsyncTask.deleteAll();
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            notaDaoAsyncTask.deleteAll();

            return null;
        }

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
