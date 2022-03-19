package aameur.ma.recyclev;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;

import aameur.ma.recyclev.adapter.StarAdapter;
import aameur.ma.recyclev.beans.Star;
import aameur.ma.recyclev.service.StarService;

public class ListActivity extends AppCompatActivity {

    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service =null;
    private static final String TAG = "StarAdapter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);

        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void init(){
        service.create(new Star("kate bosworth", "https://media.newstrack.in/uploads/entertainment-news/hollywood-news/Feb/24/big_thumb/Angelina-Jolie_5e53c82797384.jpg", 3.5f));
        service.create(new Star("george clooney", "https://static.onecms.io/wp-content/uploads/sites/20/2021/08/16/johnny-depp-1.jpg", 3));
        service.create(new Star("michelle rodriguez",
                "https://i.f1g.fr/media/madame/1900x1900_crop/sites/default/files/img/2017/10/les-nouveaux-enfants-stars-de-hollywood-photo-11.jpg", 5));
        service.create(new Star("george clooney", "https://imageresizer.static9.net.au/bbGijbGN7MweTdSeU7Sjiq-b_wI=/400x0/https%3A%2F%2Fprod.static9.net.au%2F_%2Fmedia%2FNetwork%2FImages%2F2018%2F11%2F24%2F10%2F42%2Fcelebrities_who_dont_have_star_Hollywood_Walk_of_fame_Brad_Pitt_gallery.jpg", 1));
        service.create(new Star("louise bouroin", "https://www.letelegramme.fr/images/2022/01/07/l-acteur-sidney-poitier-premiere-star-noire-d-hollywood-est_6108981_1000x526.jpg", 5));
        service.create(new Star("louise boroin", "https://cache.marieclaire.fr/data/photo/w700_c17/160/mila-kunis.jpg", 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new
                                                  SearchView.OnQueryTextListener() {
                                                      @Override
                                                      public boolean onQueryTextSubmit(String query) {
                                                          return true;
                                                      }
                                                      @Override
                                                      public boolean onQueryTextChange(String newText) {
                                                          if (starAdapter != null){
                                                              starAdapter.getFilter().filter(newText);
                                                          }
                                                          return true;
                                                      }
                                                  });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }


}