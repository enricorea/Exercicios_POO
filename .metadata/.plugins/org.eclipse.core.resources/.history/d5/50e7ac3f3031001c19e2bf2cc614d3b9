package ProjetoStatic;

public class trabalhador extends Thread{
	public String nome = "";
	public static int i = 0;
	
	public synchronized void somar() {
		i++;
	}
	
	@Override
	public void run() {
		int i = 0;
		while(i < 10000) {
			somar();
			System.out.println("Nome "+nome+"  "+i);
		}
	}

}
