 
public class TranspositionRect
{
    public static String cle_choisi;
    public static char   cle_trie[];
    public static int    pos_trieCle[];
 
    // constructeur pour definir la cle entrée
    public TranspositionRect()
    {
        cle_choisi = "oiseau";
        pos_trieCle = new int[cle_choisi.length()];
        cle_trie = cle_choisi.toCharArray();
    }
 

    public TranspositionRect(String myKey)
    {
        cle_choisi = myKey;
        pos_trieCle = new int[cle_choisi.length()];
        cle_trie = cle_choisi.toCharArray();
    }
 
    // fonction pour ordonner la cle
    public static void ordonne_cle()
    {
        
        //trouver la position de chaque charactère de la cle et 
        // on le range en ordre alphabetique
       
        int min, i, j;
        char cleOriginal[] = cle_choisi.toCharArray();
        char res;
        // First Sort the array of selected key
        for (i = 0; i < cle_choisi.length(); i++)
        {
            min = i;
            for (j = i; j < cle_choisi.length(); j++)
            {
                if (cle_trie[min] > cle_trie[j])
                {
                    min = j;
                }
            }
            if (min != i)
            {
                res = cle_trie[i];
                cle_trie[i] = cle_trie[min];
                cle_trie[min] = res;
            }
        }
      
        //on remplie le tableau en ordre alphabetique
        for (i = 0; i < cle_choisi.length(); i++)
        {
            for (j = 0; j < cle_choisi.length(); j++)
            {
                if (cleOriginal[i] == cle_trie[j])
                    pos_trieCle[i] = j;
            }
        }
    }
 
    // pour chiffrer le texte choisi
    public static String chiffrement(String texte_clair)
    {
        int  i, j;
        
        ordonne_cle();
        
        //genere le message crypté par transposition
        
        int ligne = texte_clair.length() / cle_choisi.length();
        int extra = texte_clair.length() % cle_choisi.length();
        int ligne_sup = (extra == 0) ? 0 : 1;
       
        int coltemp = -1;
        int length_total = (ligne + ligne_sup) * cle_choisi.length();
        char pmat[][] = new char[(ligne + ligne_sup)][(cle_choisi.length())];
        char encry[] = new char[length_total];
    
        ligne = 0;
        for (i = 0; i < length_total; i++)
        {
            coltemp++;
            if (i < texte_clair.length())
            {
                if (coltemp == (cle_choisi.length()))
                {
                    ligne++;
                    coltemp = 0;
                }
                pmat[ligne][coltemp] = texte_clair.charAt(i);
            }
            else
            { 
                pmat[ligne][coltemp] = '*';
            }
        }
        int len = -1, k;
        for (i = 0; i < cle_choisi.length(); i++)
        {
            for (k = 0; k < cle_choisi.length(); k++)
            {
                if (i == pos_trieCle[k])
                {
                    break;
                }
            }
            for (j = 0; j <= ligne; j++)
            {
                len++;
                encry[len] = pmat[j][k];
            }
        }
        String p1 = new String(encry);
        return (new String(p1));
    }
 
    
    //pour déchiffrer le message codé
    public static String doDecryption(String s)
    {
        int  i, j, k;
        
        char encry[] = s.toCharArray();
    
        ordonne_cle();
        // on genere le message clair
        int row = s.length() / cle_choisi.length();
        char pmat[][] = new char[row][(cle_choisi.length())];
        int tempcnt = -1;
        for (i = 0; i < cle_choisi.length(); i++)
        {
            for (k = 0; k < cle_choisi.length(); k++)
            {
                if (i == pos_trieCle[k])
                {
                    break;
                }
            }
            for (j = 0; j < row; j++)
            {
                tempcnt++;
                pmat[j][k] = encry[tempcnt];
            }
        }
       
        //on sauvarde les charactères du matrice dans une chaine de charactères
        char p1[] = new char[row * cle_choisi.length()];
        k = 0;
        for (i = 0; i < row; i++)
        {
            for (j = 0; j < cle_choisi.length(); j++)
            {
                if (pmat[i][j] != '*')
                {
                    p1[k++] = pmat[i][j];
                }
            }
        }
        p1[k++] = '\0';
        return (new String(p1));
    }
 
    @SuppressWarnings("static-access")
    public static void main(String[] args)
    {
        TranspositionRect tc = new TranspositionRect();
        System.out.println("Message à chiffré: "
                + tc.chiffrement("Les allemends aiment chanter"));
        System.out.println("Message déchiffré est : "
                + tc.doDecryption(tc.chiffrement("Les allemends aiment chanter")));
    }
}