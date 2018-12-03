package com.example.ariel.testederepositorio.fragmentos;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ariel.testederepositorio.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SobreFragment extends Fragment {

    private Button botao_versao_dialog;

    public SobreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sobre, container, false);

        botao_versao_dialog = view.findViewById(R.id.versao_app);

        botao_versao_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyCustomDialog dialog = new MyCustomDialog();
                dialog.setTargetFragment(SobreFragment.this, 1);
                dialog.show(getFragmentManager(), "MyCustomDialog");

            }
        });







        return view;
    }

}
