import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Alberto Gil
 */
public class PruebaAnuncios {

	public static void main(String[] args) throws JSONException, IOException {

		ArrayList<Anuncio> listaAnuncios = cargarAnuncios("anuncios.json");
		//imprimirAnunciosUsuario(listaAnuncios);
		//imprimirAnunciosMaster(listaAnuncios);
		
	}

	// Carga un array de anuncios de un archivo JSON
	public static ArrayList<Anuncio> cargarAnuncios(String path) throws JSONException, IOException {
		ArrayList<Anuncio> listaAnuncios = new ArrayList<Anuncio>();

		JSONArray jsonArray = new JSONArray(readFile(path, StandardCharsets.ISO_8859_1));
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			
			// Transformo el JSONArray a un ArrayList de Strings
			JSONArray arr = jsonObject.getJSONArray("pictures");
			ArrayList<String> listaFotos = new ArrayList<String>();
			for (int x = 0; x < arr.length(); x++) {
				listaFotos.add(arr.getString(x));
			}
			// Creo un nuevo anuncio con los datos del fichero
			Anuncio anuncio = new Anuncio();
			anuncio.setId(jsonObject.getLong("id"));
			anuncio.setDescription(jsonObject.getString("description"));
			anuncio.setPictures(listaFotos);
			anuncio.valorarAnuncio();

			// Lo añado a la lista de anuncios
			listaAnuncios.add(anuncio);
		}
		return listaAnuncios;
	}

	// Lee un archivo y devuelve un String con su contenido y un charset
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
