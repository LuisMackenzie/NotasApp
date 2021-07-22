package com.mackenzie.notasapp.ui.notas;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mackenzie.notasapp.NotaRepository;
import com.mackenzie.notasapp.db.entity.NoteEntity;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private LiveData<List<NoteEntity>> allNotas;
    private NotaRepository notaRepository;

    public NoteViewModel(Application app) {
        super(app);
        notaRepository = new NotaRepository(app);
        allNotas = notaRepository.getAll();
    }

    // el Fragmento que necesita recibir la nueva lista de datos
    public LiveData<List<NoteEntity>> getAllNotas() {
        return allNotas;
    }

    // El fragmento que necesite borrar una nota,debera pasar el id de la nota
    public LiveData<List<NoteEntity>> borrarNota(int idNota) {
        notaRepository.deleteNote(idNota);
        return allNotas;
    }

    // El fragmento que necesite borrar todas las notas
    public void borrarTodasNotas() {
        notaRepository.deleteAllNotes();
    }

    // El fragmento que inserte una nueva nota, debera comunicarlo a esrw viewmodel
    public void insertarNota(NoteEntity newNoteEntity) {
        notaRepository.insert(newNoteEntity);
    }

}
