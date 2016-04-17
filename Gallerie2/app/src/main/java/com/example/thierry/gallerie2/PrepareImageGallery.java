package com.example.thierry.gallerie2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.LruCache;

/**
 * Created by thierry on 13/04/16.
 */

/*

cette classe a pour role de determiner la taille des images
et de charger l'image aux dimensions prédéfinies dnas un premier temps
puis dans un format proche des possibilités offerte par la vue à terme(si j'ai le temps et le motivation)

source : https://developer.android.com/training/displaying-bitmaps/load-bitmap.html

 */
public class PrepareImageGallery {
    //gestion cache
    private static LruCache<String,Bitmap> memCache;

    //gestion du cache
    public static void initCache() {
        //gestion du cache
        final int maxMem = (int) (Runtime.getRuntime().maxMemory() / 1024);

        //google conseil 1/8 pour le cache
        final int tailleCache = maxMem / 8;

        memCache = new LruCache<String, Bitmap>(tailleCache) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    //gestion du cache
    public static void ajoutBitmapDansMemCache(String key, Bitmap bitmap) {
        if (getBitmapDepuisMemCache(key) == null ) {
            memCache.put(key, bitmap);
        }
    }

    //gestion du cache
    public static Bitmap getBitmapDepuisMemCache(String key) {
        return memCache.get(key);
    }

    //pour rappel cette methode ne capture que des infos sur l'image
    public static BitmapFactory.Options getTailleImage(Resources res, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        return options;
    }

    //je pars de l'exemple type afin de m'approprier la technique
    private static int calculVignette(BitmapFactory.Options options, int hauteurReq, int largeurReq) {
        final int hauteur = options.outHeight;
        final int largeur = options.outWidth;
        int facteurVignette = 1;
        if (hauteur > hauteurReq || largeur > largeurReq) {
            final int miHauteur = hauteur / 2;
            final int miLargeur = largeur / 2;

            while((miHauteur / facteurVignette) > hauteurReq &&
                    (miLargeur / facteurVignette) > largeurReq) {
                facteurVignette *= 2;
            }
        }
        return facteurVignette;
    }

    //a t-on vraiment besoin de commentaires? voir site web en cas de besoin
    public static Bitmap GetVignette(Resources res, int resId, int hauteurReq, int largeurReq) {
        BitmapFactory.Options options = getTailleImage(res, resId);
        int facteur = calculVignette(options, hauteurReq, largeurReq);
        options.inSampleSize = facteur;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
