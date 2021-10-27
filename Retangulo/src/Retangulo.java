
public class Retangulo {     //define apenas 1 retangulo
	int x1,y1,x2,y2;        //4 dimenções do retangulo
	
	    /* construtor // parametros dentro do próprio construtor */
	public Retangulo(int x1, int y1, int x2, int y2){
		
		/* A palavra reservada this permite que o método acesse os atributos (e método também) da propria instância*/
		
		/***** atributos da classe Retangulo *****/
		
		this.x1 = x1;          
		this.x2 = x2;
		this.y2 = y2;
		this.y2 = y2;	
	}
	
	public int getArea() { //método
		int a = x2 - x1;
		int b = y2 - y1;
		return a * b;
	}
	
	public int calcularAreaDaDiferenca(Retangulo ret) { //método onde é passado um objeto para o outro
		int result = this.getArea() - ret.getArea();  
		return result;
	}
	
}
