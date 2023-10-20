package br.ulbra.apostila7_sistemadecadastro;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelaListagemUsuarios {
    MainActivity act;
    TelaPrincipal tela_principal;
    Button btnAnterior, btnProximo, btnFechar;
    TextView txtNome, txtTelefone, txtEndereco, txtStatus;
    int index;
    public TelaListagemUsuarios(
            MainActivity act,TelaPrincipal tela_principal) {
        this.act = act;
        this.tela_principal = tela_principal;
        index = 0;
    }
    public void CarregarTela() {
//Antes de carregar a tela, verifica se existe registros
//inseridos
        if(act.getRegistros().size() == 0) {
            (new AlertDialog.Builder(act)) .setTitle("Aviso")
.setMessage("Não existe nenhum registro cadastrado.")
.setNeutralButton("OK", null).show();
            return;
        }
        act.setContentView(R.layout.listar);
        btnAnterior = (Button) act.findViewById(R.id.btnAnterior);
        btnProximo = (Button) act.findViewById(R.id.btnProximo);
        btnFechar = (Button) act.findViewById(R.id.btnFechar);
        txtNome = (TextView) act.findViewById(R.id.txtNome);
        txtEndereco = (TextView) act.findViewById(R.id.txtEndereco);
        txtTelefone = (TextView) act.findViewById(R.id.txtTelefone);
        txtStatus = (TextView) act.findViewById(R.id.txtStatus);
        PreencheCampos(index);
        AtualizaStatus(index);
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index > 0) {
                    index--;
                    PreencheCampos(index);
                    AtualizaStatus(index);
                } } });
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(index < act.getRegistros().size() - 1){
                    index++;
                    PreencheCampos(index);
                    AtualizaStatus(index);
                }
            } });
        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela_principal.CarregarTela();
            } }); }
    private void PreencheCampos(int idx) {
        txtNome.setText(act.getRegistros().get(idx).getNome());
        txtTelefone.setText(act.getRegistros().get(idx).getTelefone());
        txtEndereco.setText(act.getRegistros().get(idx).getEndereco());
    }
    private void AtualizaStatus(int idx) {
        int total = act.getRegistros().size();
        txtStatus.setText("Registros : " + (idx+1) + "/" + total);
    }
}
