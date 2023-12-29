package com.almaralengineering.pokedex;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements PokemonListFragment.OnFragmentInteractionListener,StatsFragment.OnFragmentInteractionListener,
        DetailFragment.OnFragmentInteractionListener {

    private final static int OPTION_DETAIL = 0;
    private final static int OPTION_STATS = 1;

    private TextView imageTextView;
    private TextView statsTextView;
    private Pokemon selectedPokemon;
    private int selectedOption;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageTextView = (TextView) findViewById(R.id.activity_main_image_option_textView);
        statsTextView = (TextView) findViewById(R.id.activity_main_stats_option_textView);

        imageTextView.setBackgroundColor(ContextCompat.getColor(this,R.color.display_options_selected));
        statsTextView.setBackgroundColor(ContextCompat.getColor(this,R.color.display_options_not_selected));

        imageTextView.setOnClickListener(this::onClick);
        statsTextView.setOnClickListener(this::onClick);
    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPokemonSelected(Pokemon pokemon) {
        selectedPokemon = pokemon;
       // Toast.makeText(this, pokemon.getName(), Toast.LENGTH_SHORT).show();
      //  DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
       /* detailFragment.setPokemonImage(pokemon.getImageUrl());
        detailFragment.playPokemonSound(pokemon.getSoundId());*/
        setFragmentContent();
    }

    @Override
    public void onClick(View view){
        int viewId = view.getId();
        switch (viewId){
            case R.id.activity_main_image_option_textView:
                imageTextView.setBackgroundColor(ContextCompat.getColor(this,R.color.display_options_selected));
                statsTextView.setBackgroundColor(ContextCompat.getColor(this,R.color.display_options_not_selected));
                selectedOption = OPTION_DETAIL;
                break;
            case R.id.activity_main_stats_option_textView:
                imageTextView.setBackgroundColor(ContextCompat.getColor(this,R.color.display_options_not_selected));
                statsTextView.setBackgroundColor(ContextCompat.getColor(this,R.color.display_options_selected));
                selectedOption = OPTION_STATS;
                break;
        }
        setFragmentContent();
    }
    private void setFragmentContent(){
        if(selectedPokemon!=null){
            if(selectedOption == OPTION_DETAIL){
                currentFragment = DetailFragment.newInstance(selectedPokemon.getImageUrl(),selectedPokemon.getSoundId());
            }else if(selectedOption == OPTION_STATS){
                currentFragment = StatsFragment.newInstance(selectedPokemon.getStats());
            }
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.detail_fragment_container,currentFragment);
            fragmentTransaction.commit();
        }else{
            Toast.makeText(this, "Debes seleccionar un pokemon primero", Toast.LENGTH_SHORT).show();
        }
    }
}
