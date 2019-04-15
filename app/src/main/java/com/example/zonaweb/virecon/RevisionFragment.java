package com.example.zonaweb.virecon;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class RevisionFragment extends Fragment {


    EditText e1,e2,r,e4,e5,e6,e7;
    TextView tv11,tv21,tv31;
    double a,c,fs1;


    @Override

    public void onPause(){
        super.onPause();
        SharedPreferences revi= PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor miEditor = revi.edit();

        miEditor.putFloat("a2", (float) a);
        miEditor.putFloat("c2", (float) c);
        miEditor.putFloat("fs2", (float) fs1);

        miEditor.putString("d2", e1.getText().toString());
        miEditor.putString("b2", e2.getText().toString());
        miEditor.putString("rec2", r.getText().toString());
        miEditor.putString("fc2", e4.getText().toString());
        miEditor.putString("fy2", e5.getText().toString());
        miEditor.putString("as2", e6.getText().toString());
        miEditor.putString("as12", e7.getText().toString());



        miEditor.apply();


    }

    @Override

    public void onResume(){
        super.onResume();
        SharedPreferences revi= PreferenceManager.getDefaultSharedPreferences(getActivity());

       a=revi.getFloat("a2",0);
        c=revi.getFloat("c2",0);
        fs1=revi.getFloat("fs2",0);
        tv11.setText("Altura del bloque equivalenta (a)="+a);

        tv21.setText("Eje neutro="+c);

        tv31.setText("F's= "+fs1);
        e1.setText(revi.getString("d2",""));
        e2.setText(revi.getString("b2",""));
        r.setText(revi.getString("rec2",""));
        e4.setText(revi.getString("fc2",""));
        e5.setText(revi.getString("fy2",""));
        e6.setText(revi.getString("as2",""));
        e7.setText(revi.getString("as12",""));




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_revision, container, false);
        e1= view.findViewById(R.id.et21);
        e2=  view.findViewById(R.id.et22);
        r=  view.findViewById(R.id.et23);
        e4= view.findViewById(R.id.et24);
        e5=  view.findViewById(R.id.et25);
        e6=  view.findViewById(R.id.et26);
        e7= view.findViewById(R.id.et27);

        final Button revi =  view.findViewById(R.id.revision);

        tv11=view.findViewById(R.id.a);
        tv21=view.findViewById(R.id.c);
        tv31= view.findViewById(R.id.fs);





        revi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float et1 = Float.valueOf(e1.getText().toString());
                float et2 = Float.valueOf(e2.getText().toString());
                float rec = Float.valueOf(r.getText().toString());
                float et3 = Float.valueOf(e4.getText().toString());
                float fy = Float.valueOf(e5.getText().toString());
                float as1 = Float.valueOf(e6.getText().toString());
                float as = Float.valueOf(e7.getText().toString());


                double b1;
                if (et3 <= 280) {
                    b1 = 0.85;
                } else {
                    b1 = 1.05 - et3 / 1400;
                }


                double asmin; //ACERO MINIMO
                    if(et3>315){
                        asmin=0.79*Math.sqrt(et3)*et2*et1/fy;
                    } else{
                        asmin=14/fy*et2*et1;
                    }
                    double a11=0.85*et3*b1*et2;
                    float b11= (float) (as1*0.003*2100000-as*fy);
                    double c11=-as1*0.003*2100000*rec;




                    double cp=(-b11+Math.sqrt(Math.pow(b11,2)-4*a11*c11))/(2*a11);
                    double cn=(-b11-Math.sqrt(Math.pow(b11,2)-4*a11*c11))/(2*a11);

                    //EJE NEUTRO
                    if (cp>=cn) {
                         c=cp;
                    }else {
                        c=cn;
                    }

                     a=b1*c;



                            double et=((et1-c)/c)*0.003;
                            double es=((c-rec)/c)*0.003;



                            if (2100000 * es > fy) {
                                fs1 = fy;
                            } else {
                                fs1 = 2100000 * es;
                            }


                tv11.setText("Altura del Bloque Equivalente (a)="+a);
                tv21.setText("Eje Neutro (c)="+c);
                tv31.setText("F'S ="+fs1);


                            }














        });

        return view;
}}