package teste.jogodamemoria20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Ranking extends AppCompatActivity {
    private ListView ranking;
    private Button zerar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        ranking = (ListView)findViewById(R.id.ranking);
        zerar = (Button)findViewById(R.id.zerarButton);

        list();

    }

    public void list() {
        Database db = new Database(this);

        List<Usuario> usuarios = db.all();

        ranking = (ListView)findViewById(R.id.ranking);

        List<String> itens = new ArrayList<String>();

        for (int i = 0; i < usuarios.size(); i++) {
            itens.add(usuarios.get(i).getNome() + " - " + usuarios.get(i).getTentativas());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens);

        ranking.setAdapter(adapter);
    }

    public void zerar(View view) {
        Database db = new Database(this);
        db.celar();

        list();


    }
}
