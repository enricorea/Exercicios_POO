package interface01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class interface01 {
	
	public static void main(String[] args) {
		JFrame form = new JFrame("Exemplo");
		form.setSize(400, 400); //define o tamanho da janela
		
		JButton botao = new JButton("Click me");
		botao.setBounds(50, 80, 85, 40);
		botao.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String nome = JOptionPane.showInputDialog(null, "Digite algo");
				System.out.println(nome);
			}
		});
		
		JLabel label = new JLabel("Etiqueta");
		label.setBounds(20, 30, 100, 40);
		
		//adiciona botao ao formulario
		form.add(botao);
		
		//adiciona label ao formulario
		form.add(label);
		
		
		
		form.setLayout(null); //layout
		form.setVisible(true); //visibilidade
		
		//existem diversas outras funcionalidades "cosméticas" que é possível colocar
		
	}

}

class Teste implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showConfirmDialog(null, "Olá mundo!");
	}
		
		
		
	}




