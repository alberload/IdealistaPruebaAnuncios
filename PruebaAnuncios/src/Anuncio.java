import java.time.LocalDate;
import java.util.ArrayList;

public class Anuncio {

	private long id;
	private String description;
	private ArrayList<String> pictures;
	private int puntuacion;
	private boolean relevante;
	private LocalDate fechaIrrelevante;
	
	public Anuncio() {
		
	}
	
	public void valorarAnuncio() {
		this.puntuacion = 0;
		if (this.pictures.size() > 0) {
			this.puntuacion += Constantes.POINTS_PICTURE;
		}
		if (!this.description.isEmpty()) {
			this.puntuacion += Constantes.POINTS_DESCRIPTION;
		}
		for (int i = 0; i < Constantes.KEY_WORDS.length; i++) {
			if (this.description.toLowerCase().contains(Constantes.KEY_WORDS[i].toLowerCase())) {
				this.puntuacion += Constantes.POINTS_KEY_WORD;
			}
		}
		// Configuro la relevancia
		if (this.puntuacion >= Constantes.RELEVANCE_GAP) {
			this.relevante = true;
			this.fechaIrrelevante = null;
		} else {
			this.relevante = false;
			this.fechaIrrelevante = LocalDate.now();
		}
		// Ajusto el maximo de puntuacion
		if (this.puntuacion > Constantes.POINTS_MAX) {
			this.puntuacion = Constantes.POINTS_MAX;
		}
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<String> getPictures() {
		return pictures;
	}
	public void setPictures(ArrayList<String> pictures) {
		this.pictures = pictures;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
}
