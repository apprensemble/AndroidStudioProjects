package com.example.thierry.gallerie2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

    //pour rappel cette methode ne capture que des infos sur l'image
    private static BitmapFactory.Options getTailleImage(Resources res, int resId) {
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
