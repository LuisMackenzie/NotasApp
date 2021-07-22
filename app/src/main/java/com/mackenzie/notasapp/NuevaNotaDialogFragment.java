package com.mackenzie.notasapp;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.mackenzie.notasapp.db.entity.NoteEntity;
import com.mackenzie.notasapp.ui.notas.NoteViewModel;

public class NuevaNotaDialogFragment extends DialogFragment {

    private View view;
    private EditText etTitulo, etContent;
    private RadioGroup rgColor;
    private Switch swIsFavorite;

    public static NuevaNotaDialogFragment newInstance() {
        return new NuevaNotaDialogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nueva_nota_dialog_fragment, container, false);
    }

/*

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NuevaNotaDialogViewModel.class);
        // TODO: Use the ViewModel
    }
*/


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Nueva Nota");
        builder.setMessage("Introduzca los datos de la nueva nota")
                .setPositiveButton("Guardar Nota", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        String titulo = etTitulo.getText().toString();
                        String content = etContent.getText().toString();
                        String color = "azul";
                        switch (rgColor.getCheckedRadioButtonId()) {
                            case R.id.rb_rojo:
                                color = "rojo";
                                break;
                            case R.id.rb_verde:
                                color = "verde";
                                break;
                        }
                        boolean isFavorite = swIsFavorite.isChecked();

                        // comunicar al viewmodel el nuevo dato
                        NoteViewModel mViewModel = new ViewModelProvider(getActivity()).get(NoteViewModel.class);
                        // mViewModel = new ViewModelProvider(getActivity()).get(NuevaNotaDialogViewModel.class);

                        mViewModel.insertarNota(new NoteEntity(titulo, content, isFavorite, color));
                        dialog.dismiss();

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.nueva_nota_dialog_fragment, null);

        etTitulo = view.findViewById(R.id.et_title);
        etContent = view.findViewById(R.id.et_content);
        rgColor = view.findViewById(R.id.rg_color);
        swIsFavorite = view.findViewById(R.id.s_color);

        builder.setView(view);

        Constantes.ERASED_CONTENT = false;
        // Create the AlertDialog object and return it
        return builder.create();
    }

}