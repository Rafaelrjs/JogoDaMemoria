package teste.jogodamemoria20;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// Nova Linha
public class MainActivity extends AppCompatActivity {
    private Button botao1;
    private Button botao2;
    private Button botao3;
    private Button botao4;
    private Button botao5;
    private Button botao6;

    private TextView texto;

    private Drawable corDefault;

    private RelativeLayout janela;

    private ArrayList<Integer> nuArrayList = new ArrayList<>();

    private ProgressBar progressBar;

    private int index = 0;

    private int tentativas = 0;

    private TextView texto1, texto2;

    //private ImageView ranking;

    private Button resultado;

    private int[] numeros = {1, 2, 3, 4, 5, 6};

    private Button ok;
    private EditText campo;
    private TextView nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botao1 = (Button)findViewById(R.id.button1);
        botao2 = (Button)findViewById(R.id.button2);
        botao3 = (Button)findViewById(R.id.button3);
        botao4 = (Button)findViewById(R.id.button4);
        botao5 = (Button)findViewById(R.id.button5);
        botao6 = (Button)findViewById(R.id.button6);
        texto1 = (TextView)findViewById(R.id.textView);
        texto2 = (TextView)findViewById(R.id.textView2);
        janela = (RelativeLayout)findViewById(R.id.janela);
        ok = (Button)findViewById(R.id.ok);
        campo = (EditText)findViewById(R.id.editText);
        nome = (TextView)findViewById(R.id.textView3);
       // ranking = (ImageView)findViewById(R.id.imagemRanking);

        corDefault = janela.getBackground();

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(0);

        for (int i = 1; i <= 6; i++)
            nuArrayList.add(i);

        nuArrayList = embaralhar(nuArrayList);

        Log.d("Senha: ", nuArrayList.toString());

    }

    private ArrayList embaralhar(ArrayList arrayList) {
        Collections.shuffle(nuArrayList, new Random(System.nanoTime()));
        return arrayList;
    }

    public void clickButton(View view) {
        switch (view.getId()) {

            case R.id.button1 :
                Log.d("Botao: ", "01");
                checarSenha(1, view);
                break;

            case R.id.button2 :
                Log.d("Botao: ", "02");
                checarSenha(2, view);
                break;

            case R.id.button3 :
                Log.d("Botao: ", "03");
                checarSenha(3, view);
                break;

            case R.id.button4 :
                Log.d("Botao: ", "04");
                checarSenha(4, view);
                break;

            case R.id.button5 :
                Log.d("Botao: ", "05");
                checarSenha(5, view);
                break;

            case R.id.button6 :
                Log.d("Botao: ", "06");
                checarSenha(6, view);
                break;
        }
    }

    public void checarSenha(int posicao, View view) {
        if (posicao == nuArrayList.get(index)) {
            view.setVisibility(View.INVISIBLE);
            progressBar.setProgress(index + 1);
            janela.setBackground(view.getBackground());
            index++;

            //Vibrar(10);

            if (index == 6) {
                texto1.setVisibility(View.VISIBLE);
                texto2.setVisibility(View.VISIBLE);

                ok.setVisibility(View.VISIBLE);
                campo.setVisibility(View.VISIBLE);
                nome.setVisibility(View.VISIBLE);
            }
        }
        else {
            botao1.setVisibility(View.VISIBLE);
            botao2.setVisibility(View.VISIBLE);
            botao3.setVisibility(View.VISIBLE);
            botao4.setVisibility(View.VISIBLE);
            botao5.setVisibility(View.VISIBLE);
            botao6.setVisibility(View.VISIBLE);
            index = 0;
            janela.setBackground(corDefault);
            progressBar.setProgress(0);


            tentativas++;

         //   Vibrar(80);


        }
    }

    public void reiniciar(View view) {
        botao1.setVisibility(View.VISIBLE);
        botao2.setVisibility(View.VISIBLE);
        botao3.setVisibility(View.VISIBLE);
        botao4.setVisibility(View.VISIBLE);
        botao5.setVisibility(View.VISIBLE);
        botao6.setVisibility(View.VISIBLE);
        index = 0;
        tentativas = 0;
        embaralhar(nuArrayList);
        janela.setBackground(corDefault);
        progressBar.setProgress(0);
        Log.d("Senha: ", nuArrayList.toString());

        texto1.setVisibility(View.INVISIBLE);
        texto2.setVisibility(View.INVISIBLE);

        ok.setVisibility(View.INVISIBLE);
        campo.setVisibility(View.INVISIBLE);
        nome.setVisibility(View.INVISIBLE);

       // Vibrar(10);
    }

    private void Vibrar(long milliseconds) {
        Vibrator rr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        rr.vibrate(milliseconds);
    }

    public void ok(View view) {
        String nome = campo.getText().toString();

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setTentativas(tentativas);

        try {
            Database db = new Database(this);
            List<Usuario>lis = db.all();
            db.insert(usuario);
            db.close();
            Toast.makeText(this, lis.get(0).getNome(), Toast.LENGTH_SHORT).show();
            // db = new Database(this);


        }catch (Exception e){
            Log.d("Menssagem", e.getMessage());
        }

        nova_tela();
    }

    public void nova_tela() {
        Intent intent = new Intent(this, Ranking.class);
        startActivity(intent);
    }

    public void ranking(View view) {
        nova_tela();
    }
}