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
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public class FlexionFragment extends Fragment {
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv14;

    EditText e1,e2,e3,e4,e5,r;

    double as,p,pmax,a,c,et,t,cc,cs,mr,fs1,as11,es,asmax;
@Override

    public void onPause(){
       super.onPause();
        SharedPreferences datos=PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor miEditor = datos.edit();

         miEditor.putFloat("as11", (float) as11);
         miEditor.putFloat("as", (float) as);
         miEditor.putFloat("p", (float) p);
         miEditor.putFloat("pmax", (float) pmax);
         miEditor.putFloat("a", (float) a);
         miEditor.putFloat("c", (float) c);
         miEditor.putFloat("et", (float) et);
         miEditor.putFloat("t", (float) t);
         miEditor.putFloat("cs", (float) cs);
         miEditor.putFloat("cc", (float) cc);
         miEditor.putFloat("es", (float) es);
        miEditor.putFloat("mr", (float) mr);
       miEditor.putString("d", e1.getText().toString());
    miEditor.putString("b", e2.getText().toString());
    miEditor.putString("fc", e3.getText().toString());
  miEditor.putString("fy", e4.getText().toString());
   miEditor.putString("mu", e5.getText().toString());
  miEditor.putString("rec", r.getText().toString());






         miEditor.apply();


    }


  @Override

    public void onResume(){
       super.onResume();
       SharedPreferences datos= PreferenceManager.getDefaultSharedPreferences(getActivity());

       as11=datos.getFloat("as11",0);
        as=datos.getFloat("as",0);
       p=datos.getFloat("p",0);
        pmax=datos.getFloat("pmax",0);
       a=datos.getFloat("a",0);
        c=datos.getFloat("c",0);
        et=datos.getFloat("et",0);
        t=datos.getFloat("t", 0);
        cs=datos.getFloat("cs",  0);
        cc=datos.getFloat("cc",  0);
        es=datos.getFloat("es", 0);
        mr=datos.getFloat("mr", 0);


        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');
        DecimalFormat formato2 = new DecimalFormat("#.##", separadoresPersonalizados);
      DecimalFormat formato1 = new DecimalFormat("#.#####", separadoresPersonalizados);





        tv1.setText("Acero A Compresion (A´s)="+formato2.format(as11));
        tv2.setText("Acero A Flexión (As) ="+formato2.format(as));
        tv3.setText("Cuantia Geometrica (ρmáx) ="+formato2.format(pmax));
        tv4.setText("Cuantia Geometrica (ρ)="+formato1.format(p));
        tv5.setText("Altura Del Bloque Equivente (a) ="+formato2.format(a));
        tv6.setText("Eje Neutro (c) ="+formato2.format(c));
        tv7.setText("Esfuerzo (εt)"+formato1.format(et));
        tv8.setText("T=" + formato2.format(t));
        tv9.setText("Cs=" + formato2.format(cs));
        tv10.setText("Cc=" + formato2.format(cc));
        tv11.setText("ε´s=" + formato1.format(es));
        tv12.setText("Momento de dsieño=" + formato2.format(mr));
       e1.setText(datos.getString("d",""));
       e2.setText(datos.getString("b",""));
       e3.setText(datos.getString("fc",""));
       e4.setText(datos.getString("fy",""));
       e5.setText(datos.getString("mu",""));
       r.setText(datos.getString("rec",""));


   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_flexion, container, false);

        e1= view.findViewById(R.id.et1);
        e2=  view.findViewById(R.id.et2);
        e3=  view.findViewById(R.id.et3);
        e4= view.findViewById(R.id.et4);
        e5=  view.findViewById(R.id.et5);
         r=  view.findViewById(R.id.rec);



        final Button dise =  view.findViewById(R.id.diseñar);
        final Button mas =  view.findViewById(R.id.mas);


        tv1=view.findViewById(R.id.textView14);
        tv2=view.findViewById(R.id.tv2);
        tv3= view.findViewById(R.id.tv3);
        tv4= view.findViewById(R.id.tv4);
        tv5= view.findViewById(R.id.tv5);
        tv6= view.findViewById(R.id.tv6);
        tv7=view.findViewById(R.id.tv7);
        tv8= view.findViewById(R.id.tv8);
        tv14= view.findViewById(R.id.tv14);
        tv9=  view.findViewById(R.id.tv9);
        tv10=  view.findViewById(R.id.tv10);
        tv11= view.findViewById(R.id.tv11);
        tv12= view.findViewById(R.id.tv12);



        dise.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        float et1 = Float.valueOf(e1.getText().toString());
                                        float et2 = Float.valueOf(e2.getText().toString());
                                        float et3 = Float.valueOf(e3.getText().toString());
                                        float et4 = Float.valueOf(e4.getText().toString());
                                        float et5 = Float.valueOf(e5.getText().toString());
                                        float rec = Float.valueOf(r.getText().toString());


                                        double b1;
                                        if (et3 <= 280) {
                                            b1 = 0.85;
                                        } else {
                                            b1 = 1.05 - et3 / 1400;
                                        }


                                        double rn=2*(et5)/(0.9*et2*et1*et1*0.85*et3);
                                        if(rn<1){



                                            double asmin; //ACERO MINIMO
                                            if(et3>315){
                                                asmin=0.79*Math.sqrt(et3)*et2*et1/et4;
                                            } else{
                                                asmin=14/et4*et2*et1;
                                            }

                                            float pb=(float) (b1*0.85*et3*6300/(et4*(6300+et4)));// CUANTIA BALANCESDA
                                            pmax=0.5*pb; //CUANTIA BALANCEADA MAXIMA

                                            asmax = pmax * et1 * et2;//ACERO MAXIMO

                                            as= (((0.85*et3*et2*et1)/et4)*(1-Math.sqrt(1-rn))); //ACERO REQUERIDO


                                            if (as<asmin){
                                                //VIGA SIMPLE CON ACERO MINIMO
                                                 a=asmin*et4/(0.85*et3*et2);// ALTURA DEL BLOQUE EQUIVALENTE
                                                 c=a/b1;// EJE NEUTRO
                                                 p=as/(et1*et2);//CAUNTIA GEOMETRICA
                                                 et=((et1-c)/c)*0.003;
                                                 as11=0;
                                                tv8.setVisibility(View.GONE);
                                                tv9.setVisibility(View.GONE);
                                                tv10.setVisibility(View.GONE);
                                                tv11.setVisibility(View.GONE);
                                                tv12.setVisibility(View.GONE);

                                            }else{
                                                if(as<=asmax){
                                                    //VIGA SIMPLE CON ACERO REQUERIDO
                                                    a=as*et4/(0.85*et3*et2);// ALTURA DEL BLOQUE EQUIVALENTE
                                                    c=a/b1;// EJE NEUTRO
                                                    p=as/(et1*et2);//CAUNTIA GEOMETRICA
                                                    et=((et1-c)/c)*0.003;
                                                    as11=0;
                                                    tv8.setVisibility(View.GONE);
                                                    tv9.setVisibility(View.GONE);
                                                    tv10.setVisibility(View.GONE);
                                                    tv11.setVisibility(View.GONE);
                                                    tv12.setVisibility(View.GONE);

                                                }else {
                                                    //VIGA DOBLE

                                                    double as1 = pmax * et1 * et2;
                                                    a =((as1 * et4) / (0.85 * et3 * et2));

                                                    double m1 = (0.90 * as1 * et4) * (et1 - a / 2);
                                                    double m2 = et5 - m1;
                                                    double as2 = m2 / (0.9 * et4 * (et1 - rec));
                                                    as = as1 + as2;
                                                    c = a / b1;
                                                    es =  ((c - rec) / (c) * 0.003);
                                                    p=as/(et1*et2);//CAUNTIA GEOMETRICA
                                                    et=((et1-c)/c)*0.003;



                                                    if (2100000 * es > et5) {
                                                        fs1 = et4;
                                                    } else {
                                                        fs1 = 2100000 * es;
                                                    }
                                                    as11 =m2 / (0.9 * fs1 * (et1 - rec));
                                                    cs = as11 * fs1;
                                                    t=as*et4;
                                                    cc = t - cs;
                                                    mr =0.90*(cc * (et1 - a / 2) + cs * (et1 - rec));

                                                    if(tv3.getVisibility()==View.VISIBLE){
                                                        tv8.setVisibility(View.VISIBLE);
                                                        tv9.setVisibility(View.VISIBLE);
                                                        tv10.setVisibility(View.VISIBLE);
                                                        tv11.setVisibility(View.VISIBLE);
                                                        tv12.setVisibility(View.VISIBLE);

                                                    }



                                                }

                                            }
                                            DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
                                            separadoresPersonalizados.setDecimalSeparator('.');
                                            DecimalFormat formato2 = new DecimalFormat("#.##", separadoresPersonalizados);
                                            DecimalFormat formato1 = new DecimalFormat("#.#####", separadoresPersonalizados);




                                            tv1.setText("Acero A Compresion (A´s)="+formato2.format(as11));
                                            tv2.setText("Acero A Flexión (As) ="+formato2.format(as));
                                            tv3.setText("Cuantia Geometrica (ρmáx) ="+formato1.format(pmax));
                                            tv4.setText("Cuantia Geometrica (ρ)="+formato1.format(p));
                                            tv5.setText("Altura Del Bloque Equivente (a) ="+formato2.format(a));
                                            tv6.setText("Eje Neutro (c) ="+formato2.format(c));
                                            tv7.setText("Esfuerzo (εt)"+formato1.format(et));
                                            tv11.setText("ε´s=" + formato1.format(es));
                                            tv8.setText("T=" + formato2.format(t));
                                            tv9.setText("Cs=" + formato2.format(cs));
                                            tv10.setText("Cc=" + formato2.format(cc));
                                            tv11.setText("fs=" + formato2.format(fs1));
                                            tv12.setText("Momento de dsieño=" + formato2.format(mr));


                                        }else{

                                            Toast toast= Toast.makeText(getActivity(),"Cambie La Dimención De la Sección", Toast.LENGTH_LONG);

                                            toast.show();





                                        }
                                    }
                                }



        );

        mas.setOnClickListener(new View.OnClickListener(){

                                   @Override
                                   public void onClick(View view) {
                                       if(as>asmax){
                                           if(tv3.getVisibility()==View.GONE){
                                               tv3.setVisibility(View.VISIBLE);
                                       tv4.setVisibility(View.VISIBLE);
                                       tv5.setVisibility(View.VISIBLE);
                                       tv6.setVisibility(View.VISIBLE);
                                       tv7.setVisibility(View.VISIBLE);
                                       tv8.setVisibility(View.VISIBLE);
                                       tv9.setVisibility(View.VISIBLE);
                                       tv10.setVisibility(View.VISIBLE);
                                       tv11.setVisibility(View.VISIBLE);
                                       tv12.setVisibility(View.VISIBLE);}
                                       else { tv3.setVisibility(View.GONE);
                                               tv4.setVisibility(View.GONE);
                                               tv5.setVisibility(View.GONE);
                                               tv6.setVisibility(View.GONE);
                                               tv7.setVisibility(View.GONE);
                                               tv8.setVisibility(View.GONE);
                                               tv9.setVisibility(View.GONE);
                                               tv10.setVisibility(View.GONE);
                                               tv11.setVisibility(View.GONE);
                                               tv12.setVisibility(View.GONE);}

                                           }

                                   else {if (tv3.getVisibility()==View.GONE) {
                                           tv3.setVisibility(View.VISIBLE);
                                           tv4.setVisibility(View.VISIBLE);
                                           tv5.setVisibility(View.VISIBLE);
                                           tv6.setVisibility(View.VISIBLE);
                                           tv7.setVisibility(View.VISIBLE);
                                           tv8.setVisibility(View.GONE);
                                           tv9.setVisibility(View.GONE);
                                           tv10.setVisibility(View.GONE);
                                           tv11.setVisibility(View.GONE);
                                       }else {
                                           tv3.setVisibility(View.GONE);
                                           tv4.setVisibility(View.GONE);
                                           tv5.setVisibility(View.GONE);
                                           tv6.setVisibility(View.GONE);
                                           tv7.setVisibility(View.GONE);
                                           tv8.setVisibility(View.GONE);
                                           tv9.setVisibility(View.GONE);
                                           tv10.setVisibility(View.GONE);
                                           tv11.setVisibility(View.GONE);

                                       }

                                       }
                               }}
        );


        return view;
    }

}





