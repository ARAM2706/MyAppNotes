package com.app.notapsavhll.appnotas;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.notapsavhll.appnotas.Datos.Nota;


public class ItemFragment extends Fragment {

    private static final String KEY_MODEL = "KEY_MODEL";

    private Nota[] dummyModels;
    private OnListFragmentInteractionListener interactionListener;

    public ItemFragment() {
    }

    /**
     * Receive the model list
     */
    public static ItemFragment newInstance(Nota[] dummyModels) {

        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putParcelableArray(KEY_MODEL, dummyModels);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() == null) {
            throw new RuntimeException("You must to send a dummyModels ");
        }

        dummyModels = (Nota[]) getArguments().getParcelableArray(KEY_MODEL);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new ItemRecyclerViewAdapter(dummyModels, interactionListener));

        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // activity must implement OnListFragmentInteractionListener
        if (context instanceof OnListFragmentInteractionListener) {
            interactionListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        interactionListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * <p/>
     */
    public interface OnListFragmentInteractionListener {

        void onListFragmentInteraction(Nota item);

    }

}
