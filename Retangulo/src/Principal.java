
public class Principal {
	
	public static void main(String[] args) {
		Retangulo r = new Retangulo(0, 0, 10, 10);
		Retangulo s = new Retangulo(1, 1, 5, 5);
	
		System.out.println("Area: " + r.calcularAreaDaDiferenca(s));
		
	}	
	
}
