package ProjetoStatic;

public class ProjetoStatic {
	
	// a vari?vel est?tica ? criada uma s? vez/ ela compartilha espa?o de mem?ria
	// o m?todo 
	
	public static void main(String[] args) {
		Teste x = new Teste();
		Teste.a = 1;
		x.b = 1;
		
		Teste y = new Teste();
		Teste.a = 2;
		y.b = 3;
		
		Teste z = new Teste();
		z.b = 6;
		
		System.out.println("Z.a:"+y.b);
		
		x.imprimirSemStatic(); //metodos estaticos dependem da instancia??o
		// quando tem coisas utilizadas dentro do programa que n?o dependem de vari?veis externas
		// entrada simples e saida simples -> estatico
		
		Teste.imprimirComStatic(); //metodos estados existem antes da instancioa??o
		
		System.out.println("tipo de salario:"+TiposDeSalario.calcularSalario(TiposDeSalario.SALARIOMINIMO));
		
		trabalhador t1 = new trabalhador();
		t1.nome = "t1";
		t1.start();
		
		trabalhador t2 = new trabalhador();
		t2.nome = "t2";
		t2.start(); //daemon
	}

}
