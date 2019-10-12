package it.polito.oop.futsal;

public class Soci {

		private String nome;
		private String cognome;
		private String telefono;
		private int codice;
		public Soci(String nome, String cognome, String telefono, int codice) {
			this.nome = nome;
			this.cognome = cognome;
			this.telefono = telefono;
			this.codice = codice;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getCognome() {
			return cognome;
		}
		public void setCognome(String cognome) {
			this.cognome = cognome;
		}
		public String getTelefono() {
			return telefono;
		}
		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
		public int getCodice() {
			return codice;
		}
		public void setCodice(int codice) {
			this.codice = codice;
		}
		
		
		
}
