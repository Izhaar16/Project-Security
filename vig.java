import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;


public class vig {

    
   //Fonction pour generer la clé

    static String genereCle(String chaine, String key)
{
    int x = chaine.length();
    //boucle pour copier la clé plusieurs fois afin que ca soit même longueure que le texte à crypter
    for (int i = 0; ; i++)
    {
        if (x == i)
            i = 0;
        if (key.length() == chaine.length()) 
            break;
        key+=(key.charAt(i));  
    }
    return key;
}
  
// Cette fonction retourne le message crypté
// en utilisant la clé
static String encryptMessage(String chaine, String key)
{
    String encryptText="";
  
    for (int i = 0; i < chaine.length(); i++)
    {
        // on converti dans une plage de valeurs de 0-94
        int x = ((chaine.charAt(i)-32) + (key.charAt(i)-32)) % 95;
  
        // en converti en ASCII
        x += 32;
        
  
        encryptText+=(char)(x);
    }
    return encryptText;
}


  
// cette fonction decrypt le message encrypté
// et retourn le texte clair à l'aide de la clé
static String decryptMessage(String encryptText, String key)
{
    String decryptText="";
  
    for (int i = 0 ; i < encryptText.length() && 
                            i < key.length(); i++)
    {
        // on convertit dans la plage de valeurs de 0-94
        
        int x = ((encryptText.charAt(i)-32) - (key.charAt(i)-32) + 95 ) % 95 ;
    
        
  
        // on convertit en ASCII
        x += 32;
        decryptText+=(char)(x);
    }
    return decryptText;
}
  
// Methode main pour lancer le program dans le terminal
public static void main(String[] args) throws IOException
{
    /*
    //scanner récupère les valeurs entrée sur la ligne de commande
    Scanner scanner = new Scanner(System.in);
    System.out.println("Menu:\n1: Chiffrement\n2: Déchiffrement");

    
    String choice = scanner.nextLine();
    System.out.println(choice);

    
        //Si le choix 1, c'est à dire message à encrypté, ce code est exécuté
        if(choice.equals("1")){
            System.out.println("Entrez le texte à crypté: ");
            String texte = scanner.nextLine();

            System.out.println("Entrez la clé : ");
            String cle = scanner.nextLine();
            String key = genereCle(texte, cle);
            String res1 = encryptMessage(texte, key);
            
            
            System.out.println("Le message encrypté est :"+res1);
            
        } //Sinon si le choix est de decrypter un message, ce code est exécuté
        else if(choice.equals("2")){

            System.out.println("Entrez le texte à decrypté: ");
            String texte = scanner.nextLine();

            System.out.println("Entrez la clé : ");
            String cle = scanner.nextLine();
         
            
            String key = genereCle(texte, cle);
            String res1 = encryptMessage(texte, key);
            String res2 = decryptMessage(res1, key);
            
            
            System.out.println("Le message decrypté est :"+res2);

        }
        scanner.close();*/

    
    String texte_clair = "the german is coming";
    String cle = "vigenere";

    String key = genereCle(texte_clair, cle);
    String chiffrer= encryptMessage(texte_clair, key);

    System.out.println("Le message chiffré est : "
        + chiffrer+ "\n");
  
    System.out.println("Le message clair est: "
        + decryptMessage(chiffrer, key));
    

}


}


