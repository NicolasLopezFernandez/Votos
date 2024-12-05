package dominio;

import java.util.ArrayList;

public class Papeleta {
    private ArrayList<Candidato> preferencia;

    public Papeleta() {
        preferencia = new ArrayList<>();
    }

    public Candidato getPrimeraPreferencia() {
        return preferencia.get(0);
    }

    public ArrayList<Candidato> getPreferencias() {
        return preferencia;
    }

    public void addPapeleta(Candidato c) {
        preferencia.add(c);
    }

    public void eliminarCandidato(Candidato c) {
        preferencia.remove(c);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < preferencia.size(); i++) {
            sb.append(preferencia.get(i).toString());
        }
        return sb.toString();
    }
}