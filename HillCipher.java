
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class HillCipher
{
    //definir la matrice cle
    //et les tableau à utiliser pour le texte à encrypter
    int matriceCle[][];
    int matriceLigne[];
    int resultmatrix[];
 
    
 
    
    
    //créer une matrice avec la clé
    public void matrice_Cle(int a,int b, int c, int d, int len)
    {
        matriceCle = new int[len][len];
        
        
        matriceCle[0][0] = a;
        matriceCle[0][1] = b;
        matriceCle[1][0] = c; 
        matriceCle[1][1] = d;
                
            }
        
    
    //fonction qui récupère le texte entrée et on le convertit en nombre
    public void matrice_Ligne(String texte)
    {
        matriceLigne = new int[texte.length()];
        for (int i = 0; i < texte.length(); i++)
        {
            matriceLigne[i] = ((int) texte.charAt(i)) -((int)'a' + 1); //converti en nombre
        }
    }
    //fonction qui multiplie la chaque ligne par la clé, c'est à dire la matrice
    public void lignemultcle(int len)
    {
        resultmatrix = new int[len];
        
        for (int i = 0; i < len; i++)
        {
            for (int j = 0; j < len; j++)
            {
                resultmatrix[i] += (matriceCle[i][j] * matriceLigne[j])-32; //stock et addition avec la valeur present
                
            }
            
            resultmatrix[i] %= 95; // met le texte en entier dans une plage de valeurs de 0 -94
        }
    }
    //fonction qui affiche le resultat du chiffrement
    public void result(int len)
    {
        String result = "";
        for (int i = 0; i < len; i++)
        {
            result += (char) (resultmatrix[i] + 32);
        }
        System.out.print(result);
    }
    //Fonction qui vérifie si la clé donnée est correcte pour ce chiffrement
    //c'est-à-dire elle vérifie si le determinant de la matrice n'est pas zero
    public boolean check(String key, int len)
    {
        //matrice_Cle(key, len);
        int d = determinant(matriceCle, len);
        d = d % 26;
        if (d == 0)
        {
            System.out
                    .println("Clé Invalide, le determinant de la matrice est zero...");
            return false;
        }
        else if (d % 2 == 0 || d % 13 == 0)
        {
            System.out
                    .println("Clé invalide, le determinant est un facteur de 26...");
            return false;
        }
        else
        {
            return true;
        }
    }
    //fonction qui calcule le determinant d'une matrice 2 * 2
    public int determinant(int A[][], int N)
    {
        int res;
        if (N == 1)
            res = A[0][0];
        else if (N == 2)
        {
            res = A[0][0] * A[1][1] - A[1][0] * A[0][1];
        }
        else
        {
            res = 0;
            for (int j1 = 0; j1 < N; j1++)
            {
                int m[][] = new int[N - 1][N - 1];
                for (int i = 1; i < N; i++)
                {
                    int j2 = 0;
                    for (int j = 0; j < N; j++)
                    {
                        if (j == j1)
                            continue;
                        m[i - 1][j2] = A[i][j];
                        j2++;
                    }
                }
                res += Math.pow(-1.0, 1.0 + j1 + 1.0) * A[0][j1]
                        * determinant(m, N - 1);
            }
        }
        return res;
    }
 
    public void cofact(int num[][], int f)
    {
        int b[][], fac[][];
        b = new int[f][f];
        fac = new int[f][f];
        int p, q, m, n, i, j;
        for (q = 0; q < f; q++)
        {
            for (p = 0; p < f; p++)
            {
                m = 0;
                n = 0;
                for (i = 0; i < f; i++)
                {
                    for (j = 0; j < f; j++)
                    {
                        b[i][j] = 0;
                        if (i != q && j != p)
                        {
                            b[m][n] = num[i][j];
                            if (n < (f - 2))
                                n++;
                            else
                            {
                                n = 0;
                                m++;
                            }
                        }
                    }
                }
                fac[q][p] = (int) Math.pow(-1, q + p) * determinant(b, f - 1);
            }
        }
        trans(fac, f);
    }
    //fonction qui determine le transposition d'une matrice matrice
    // et ensuite affiche l'inverse de la matrice clé pour le dechiffrement
    void trans(int fac[][], int r)
    {
        int i, j;
        int b[][], inv[][];
        b = new int[r][r];
        inv = new int[r][r];
        int d = determinant(matriceCle, r);
        int mi = mi(d % 26);
        mi %= 26;
        if (mi < 0)
            mi += 26;
        for (i = 0; i < r; i++)
        {
            for (j = 0; j < r; j++)
            {
                b[i][j] = fac[j][i];
            }
        }
        for (i = 0; i < r; i++)
        {
            for (j = 0; j < r; j++)
            {
                inv[i][j] = b[i][j] % 26;
                if (inv[i][j] < 0)
                    inv[i][j] += 26;
                inv[i][j] *= mi;
                inv[i][j] %= 26;
            }
        }
        System.out.println("\nInverse de la matrice clé:");
        matrixtoinvkey(inv, r);
    }
 /*
    public int mi(int d)
    {
        int q, r1, r2, r, t1, t2, t;
        r1 = 26;
        r2 = d;
        t1 = 0;
        t2 = 1;
        while (r1 != 1 && r2 != 0)
        {
            q = r1 / r2;
            r = r1 % r2;
            t = t1 - (t2 * q);
            r1 = r2;
            r2 = r;
            t1 = t2;
            t2 = t;
        }
        return (t1 + t2);
    }*/
    //fonction qui calcule la matrice inverse
    //utiliser pour le dechiffrement
    public void matrixtoinvkey(int inv[][], int n)
    {
        String invkey = "";
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                invkey += (char) (inv[i][j] + 97);
            }
        }
        System.out.println(invkey);
    }
 
    public static void main(String args[]) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menu:\n1: Chiffrement\n2: Déchiffrement");

    
        String choice = scanner.nextLine();
        System.out.println(choice);





}