package com.example.appausa.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.appausa.R;
import com.example.appausa.model.AesUtil;
import com.example.appausa.model.Transferencia;


import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransferenciaAdapter extends ArrayAdapter<Transferencia> {

    AesUtil crip = new AesUtil();

    public TransferenciaAdapter(Context context, List<Transferencia> objects) {
        super(context, 0, objects);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_item_reporte,
                    parent,
                    false);
        }

        // Referencias UI.
        ImageView avatar = (ImageView) convertView.findViewById(R.id.estado);
        TextView id_emp = (TextView) convertView.findViewById(R.id.codt);
        TextView fecha = (TextView) convertView.findViewById(R.id.fecha);
        TextView tipo = (TextView) convertView.findViewById(R.id.cuentao);
        TextView id_r = (TextView) convertView.findViewById(R.id.cuentad);
        TextView valor = (TextView) convertView.findViewById(R.id.valor);

        // Lead actual.
        Transferencia lead = getItem(position);

        avatar.setImageResource(R.drawable.check_r);
        id_emp.setText(crip.decrypt(lead.getIdTrans()));
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
        fecha.setText(lead.getFecha().format(dt));
        tipo.setText(crip.decrypt(lead.getCuentaOrigen().getNum()));
        id_r.setText(crip.decrypt(lead.getCuentaDestino().getNum()));
        valor.setText(String.valueOf(lead.getValor()));
        return convertView;
    }
}