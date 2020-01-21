package com.example.studentsproject4threats.ui.manel;

import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentsproject4threats.R;

public class ManelFragment extends Fragment {

    private ManelViewModel mViewModel;

    EditText editText;
    TextView textView, resultField;
    Button button;
    MyAsyncTask myAsyncTask;

    public static ManelFragment newInstance() {
        return new ManelFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_manel, container, false);
        editText = root.findViewById(R.id.editText);
        textView = root.findViewById(R.id.textView);
        button = root.findViewById(R.id.button);
        resultField = root.findViewById(R.id.resultField);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Long n = Long.parseLong(editText.getText().toString());
                int n = Integer.parseInt(editText.getText().toString());
                myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute(n);
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ManelViewModel.class);
        // TODO: Use the ViewModel
    }

    private class MyAsyncTask extends AsyncTask<Integer, Integer, Boolean > {
        Integer m;

        /*@Override
        public MyAsyncTask(Integer x){
            this.m = x;
        }*/
        protected Boolean doInBackground (Integer... m) {
            boolean isPrime = true;
            if (m[0] % 2 == 0) isPrime = false;
            else {
                long factor = 3;
                double limit = Math.sqrt(m[0] ) + 0.0001;
                while (factor < limit) {
                    if (m[0]  % factor == 0) {
                        isPrime = false;
                        break;
                    }
                    factor += 2;
                }
            }
            return isPrime;
        }

        protected void onPostExecute(Boolean result ) {
            // Accessing to the GUI from the Thread
            if(result) {
                resultField.setText("YES");
            } else{
                resultField.setText("NO");
            }
        }
    }
}