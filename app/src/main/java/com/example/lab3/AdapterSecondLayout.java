package com.example.lab3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import static com.example.lab3.ModelMark.sumMarks;

public class AdapterSecondLayout extends RecyclerView.Adapter<AdapterSecondLayout.MarksViewHolder> {
    //List<ModelMark> oraz LayoutInflater
    private List<ModelMark> modelMarkList;
    private LayoutInflater layoutInflaterl;

    public AdapterSecondLayout(List<ModelMark> modelMarkList, Activity context) {
        this.modelMarkList = modelMarkList;
        layoutInflaterl = context.getLayoutInflater();
    }
    //tworzenie ViewHoldera
    @NonNull
    @Override
    public MarksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View subjectView = inflater.inflate(R.layout.row_subjects, parent, false);
        // zwraca nowy holder
        MarksViewHolder viewHolder = new MarksViewHolder(subjectView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MarksViewHolder holder, int position) {
        final ModelMark modelMark = modelMarkList.get(position);

        TextView textView = holder.textViewNameSubject;
        textView.setText(modelMark.getNameSubject());

        RadioGroup rg = holder.rg;
        RadioButton rb2 = holder.rb2;
        RadioButton rb3 = holder.rb3;
        RadioButton rb4 = holder.rb4;
        RadioButton rb5 = holder.rb5;

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioMark2) {
                    modelMark.setMark(2);
                    //holder.rb2.setTextColor(Color.RED);
                }
                if (checkedId == R.id.radioMark3) {
                    modelMark.setMark(3);
                    //holder.rb3.setTextColor(Color.GREEN);
                }
                if (checkedId == R.id.radioMark4) {
                    modelMark.setMark(4);
                    //holder.rb4.setTextColor(Color.BLUE);
                }
                if (checkedId == R.id.radioMark5) {
                    modelMark.setMark(5);
                    //holder.rb5.setTextColor(Color.YELLOW);
                }
                System.out.println(sumMarks);
            }
        });
    }
    //rozmiar listy elementow
    @Override
    public int getItemCount() {
        return modelMarkList.size();
    }

    public class MarksViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNameSubject;
        private RadioGroup rg;
        private RadioButton rb2, rb3, rb4, rb5;

        public MarksViewHolder(@NonNull View itemView) {
            super(itemView);
            //referencja
            textViewNameSubject = (TextView) itemView.findViewById(R.id.textToNameSubject);
            rg = (RadioGroup) itemView.findViewById(R.id.radioGroupToSubject);
            rb2 = (RadioButton) itemView.findViewById(R.id.radioMark2);
            rb3 = (RadioButton) itemView.findViewById(R.id.radioMark3);
            rb4 = (RadioButton) itemView.findViewById(R.id.radioMark4);
            rb5 = (RadioButton) itemView.findViewById(R.id.radioMark5);
        }
    }
}
