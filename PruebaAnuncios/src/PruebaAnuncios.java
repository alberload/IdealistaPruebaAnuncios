import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONException;

/**
 * @author Alberto Gil
 */
public class PruebaAnuncios {

	public static void main(String[] args) throws JSONException, IOException {

		ArrayList<Anuncio> listaAnuncios = CargaAnuncios.cargarAnuncios("anuncios.json");
		imprimirAnuncios(listaAnuncios, "usuario");
		imprimirAnuncios(listaAnuncios, "calidad");

	}

	public static void imprimirAnuncios(ArrayList<Anuncio> lista, String mode) {
		switch (mode) {
		case "calidad":
			System.out.println("----------------------------------------");
			System.out.println("----------ENCARGADO DE CALIDAD----------");
			for (int i = 0; i < lista.size(); i++) {
				if (!lista.get(i).isRelevante()) {
					System.out.println("----------------------------------------");
					System.out.println("ID: " + lista.get(i).getId());
					System.out.println("Puntuación: " + lista.get(i).getPuntuacion());
					System.out.println("Fecha desde irrelevante: " + lista.get(i).getFechaIrrelevante());
					System.out.println("Descripción: " + lista.get(i).getDescription());
					System.out.println("Fotos: " + lista.get(i).getPictures().toString());
				}
			}
			System.out.println();
			break;
		case "usuario":
			// Ordeno la lista por su puntuacion
			Collections.sort(lista, new Comparator<Anuncio>() {
				public int compare(Anuncio o1, Anuncio o2) {
					if (o1.getPuntuacion() == o2.getPuntuacion())
						return 0;
					return o1.getPuntuacion() > o2.getPuntuacion() ? -1 : 1;
				}
			});

			System.out.println("----------------------------------------");
			System.out.println("-----------------USUARIO----------------");
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).isRelevante()) {
					System.out.println("----------------------------------------");
					System.out.println("ID: " + lista.get(i).getId());
					System.out.println("Puntuación: " + lista.get(i).getPuntuacion());
					System.out.println("Descripción: " + lista.get(i).getDescription());
					System.out.println("Fotos: " + lista.get(i).getPictures().toString());
				}
			}
			System.out.println();
			break;
		}
	}
}
