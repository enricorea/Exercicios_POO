package ProjetoStatic;

public class TiposDeSalario {
	
	static int SALARIOMINIMO = 0;
	static int SALARIOMEDIO = 1;
	
	public static String calcularSalario(int tiposDeSalario) {
		if(tiposDeSalario == SALARIOMINIMO) {
			return "1100";
		}else {
			return "2200";
		}
	}

}
