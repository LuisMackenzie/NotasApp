package com.mackenzie.notasapp.ui.notas;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mackenzie.notasapp.NuevaNotaDialogFragment;
import com.mackenzie.notasapp.NuevaNotaDialogViewModel;
import com.mackenzie.notasapp.R;
import com.mackenzie.notasapp.db.entity.NoteEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NoteFragment2 extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private List<NoteEntity> notaList;
    private MyNoteRecyclerViewAdapter adapterNotes;
    private NuevaNotaDialogViewModel mViewModel;
    private NoteViewModel noteViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteFragment2() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NoteFragment2 newInstance(int columnCount) {
        NoteFragment2 fragment = new NoteFragment2();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (view.getId() == R.id.listPortrait) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                float dpWidth = metrics.widthPixels / metrics.density;
                int numCol = (int) (dpWidth / 180);

                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numCol, StaggeredGridLayoutManager.VERTICAL));
            }

            notaList = new ArrayList<>();
            /*
            notaList.add(new Note("Nota de Prueba", "Probando el contenido de una nota aleatoruiamente", true, R.color.white));
            notaList.add(new Note("Lista de compra", "Probando el contenido de una nota aleatoruiamente", false, android.R.color.holo_red_light));
            notaList.add(new Note("Cosas de casa", "Probando el contenido de una nota aleatoruiamente, Probando el contenido de una nota aleatoruiamente, Probando el contenido de una nota aleatoruiamente, Probando el contenido de una nota aleatoruiamente", true, R.color.white));
            notaList.add(new Note("Lista de Tareas", "Probando el contenido de una nota aleatoruiamente", false, android.R.color.holo_blue_light));
            notaList.add(new Note("Trabajos pendientes", "Probando el contenido de una nota aleatoruiamente", false, android.R.color.holo_orange_light));
            notaList.add(new Note("Estudios Pendientes", "Probando el contenido de una nota aleatoruiamente , Probando el contenido de una nota aleatoruiamente, Probando el contenido de una nota aleatoruiamente", false, android.R.color.holo_green_light));
            notaList.add(new Note("Lista de Tareas", "Probando el contenido de una nota aleatoruiamente", false, android.R.color.holo_blue_light));
            */
            adapterNotes = new MyNoteRecyclerViewAdapter(notaList, getActivity());
            recyclerView.setAdapter(adapterNotes);

            lanzarViewModel();

        }
        return view;
    }

    private void lanzarViewModel() {
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        // mViewModel = ViewModelProviders.of(getActivity()).get(NuevaNotaDialogViewModel.class);
        // mViewModel = new ViewModelProvider(this).get(NuevaNotaDialogViewModel.class);

        noteViewModel.getAllNotas().observe(getViewLifecycleOwner(), new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(List<NoteEntity> noteEntities) {

                adapterNotes.setNuevasNotas(noteEntities);
                adapterNotes.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_fragment, menu);
        // super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_note:
                mostrarDialogoNuevaNota();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void mostrarDialogoNuevaNota() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        NuevaNotaDialogFragment dialogNuevaNota = new NuevaNotaDialogFragment();
        dialogNuevaNota.show(fm, "NuevaNotaDialogFragment");
    }


}