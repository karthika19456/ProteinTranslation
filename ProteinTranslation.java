public class ProteinTranslation 
{
    public static void main(String[] args)
    {
        String[] aminoAcid = {"Arg", "Asn", "Asp", "Cys", "Gln", "Glu", 
                              "His", "Ile", "Lys", "Met", "Phe", 
                              "Trp", "Tyr"};
        String[][] codons = {{"AGA", "AGG"}, {"AAU", "AAC"}, {"GAU", "GAC"}, {"UGU", "UGC"}, {"CAA", "CAG"}, {"GAA", "GAG"},
                             {"CAU", "CAC"}, {"AUU", "AUC", "AUA"}, {"AAA", "AAG"}, {"AUG"}, {"UUU", "UUC"}, {"UGG"}, 
                             {"UAU", "UAC"}};
        String dna = args[0];
        String rna = "", rna2 = "", rna3 = "", protein = "";
        int j = 0;  

        // converting dna to rna - transcription
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'A')
                rna = rna + 'U';
            else if (dna.charAt(i) == 'C')
                rna = rna + 'G';
            else if (dna.charAt(i) == 'G')
                rna = rna + 'C';
            else
                rna = rna + 'A';
        }
        // converting rna to protein - translation
        // identifying start codon
        for (int i = 0; i < rna.length() - 2; i++) {
            if (rna.substring(i, i + 3).equals("AUG")) {
                rna2 = rna2 + rna.substring(i);
                break;
            }
        }
        // identifying end codon
        for (int i = 0; i <= rna2.length() - 2; i+=3) {
            if (rna2.substring(i, i + 3).equals("UAA")) {
                rna3 = rna3 + rna2.substring(0, i);
                break;
            }
            if (rna2.substring(i, i + 3).equals("UGA")) {
                rna3 = rna3 + rna2.substring(0, i);
                break;
            }
            if (rna2.substring(i, i + 3).equals("UAG")) {
                rna3 = rna3 + rna2.substring(0, i);
                break;
            }
        }
        // protein translation
        for (int i = 0; i <= rna3.length() - 2; i+=3) {
            if (rna3.substring(i, i + 2).equals("GC")) {
                protein = protein + "Ala "; 
                continue; }
            else if (rna3.substring(i, i + 2).equals("CG") || rna3.substring(i, i + 3).equals("AGA") 
                    || rna3.substring(i, i + 3).equals("AGG")) {
                protein = protein + "Arg "; 
                continue; }
            else if (rna3.substring(i, i + 2).equals("GG")) {
                protein = protein + "Gly "; 
                continue; }
            else if (rna3.substring(i, i + 2).equals("CU") || rna3.substring(i, i + 3).equals("UUA") 
            || rna3.substring(i, i + 3).equals("UUG")) {
                protein = protein + "Leu "; 
                continue; }
            else if (rna3.substring(i, i + 2).equals("CC")) {
                protein = protein + "Pro "; 
                continue; }
            else if (rna3.substring(i, i + 2).equals("UC") || rna3.substring(i, i + 3).equals("AGU") 
            || rna3.substring(i, i + 3).equals("AGC")) {
                protein = protein + "Ser "; 
                continue; }
            else if (rna3.substring(i, i + 2).equals("AC")) {
                protein = protein + "Thr "; 
                continue; }
            else if (rna3.substring(i, i + 2).equals("GU")) {
                protein = protein + "Val "; 
                continue; }
            for (; j < aminoAcid.length; j++) { // length is 13 
                for (int k = 0; k < codons[j].length; k++) { // length varies depending on acid
                    if (rna3.substring(i, i + 3).equals(codons[j][k])) {
                        protein = protein + aminoAcid[j] + " ";
                    }
                }
            }
            j = 0;
        }
        System.out.println(protein);
    }
}