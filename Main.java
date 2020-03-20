public class Main {

	public static void main(String[] args) {
			
		Item x = new Item(10);
		Item y = new Item(20);
		Item n = new Item(40);
		Item m = new Item(60);
		
		Transacao t1 = new Transacao();
		Transacao t2 = new Transacao();
		
		
		Operacao opT1_0 = new Operacao("wlock", x, null, null, t1);
		Operacao opT1_1 = new Operacao("rlock", y, null, null, t1);
		Operacao opT1_2 = new Operacao("ler", x, null, null, t1);
		Operacao opT1_3 = new Operacao("ler", y, null, null, t1);
		Operacao opT1_4 = new Operacao("add", x, x, y, t1);
		Operacao opT1_5 = new Operacao("gravar", x, null, null, t1);
		Operacao opT1_6 = new Operacao("unlock", x, null, null, t1);
		
		//T2
		Operacao opT2_1 = new Operacao("wlock", x, null, null, t2);
		Operacao opT2_2 = new Operacao("ler", x, null, null, t2);
		Operacao opT2_3 = new Operacao("rlock", m, null, null, t2);
		Operacao opT2_4 = new Operacao("ler", m, null, null, t2);
		Operacao opT2_5 = new Operacao("sub", x, x, m, t2);
		Operacao opT2_52 = new Operacao("gravar", x, null, null, t2);
		Operacao opT2_6 = new Operacao("unlock", x, null, null, t2);
		Operacao opT2_7 = new Operacao("unlock", m, null, null, t2);
		
		//PLANO SERIAL
		System.out.println("Transacao 1");
		//wlock x
		opT1_0.execute();
		//rlock x
		opT1_1.execute();
		//ler(x)
		opT1_2.execute();
		//ler(y)
		opT1_3.execute();
		//x + y
		opT1_4.execute();
		//gravar x
		opT1_5.execute();
		//unlock x
		opT1_6.execute();
		
		System.out.println("Transacao 2");
		//wlock x
		opT2_1.execute();
		//ler x
		opT2_2.execute();
		//rlock m
		opT2_3.execute();
		//ler m
		opT2_4.execute();
		//x + m
		opT2_5.execute();
		//gravar x
		opT2_52.execute();
		//unlock x
		opT2_6.execute();
		//unlock m
		opT2_7.execute();
		
		
		//PLANO NÃO SERIAL (com erro)
		System.out.println("Transacao 1");
		//wlock x
		opT1_0.execute();
		//rlock x
		opT1_1.execute();
		//ler(x)
		opT1_2.execute();
		//ler(y)
		opT1_3.execute();
		//x + y
		opT1_4.execute();
		//gravar x
		opT1_5.execute();
		
		System.out.println("Transacao 2");
		//wlock x
		opT2_1.execute();
		//ler x
		opT2_2.execute();
		//rlock m
		opT2_3.execute();
		//ler m
		opT2_4.execute();
		//x + m
		opT2_5.execute();
		//gravar x
		opT2_52.execute();

		System.out.println("Transacao 1");		
		//unlock x
		opT1_6.execute();
		
		System.out.println("Transacao 2");
		//unlock x
		opT2_6.execute();
		//unlock m
		opT2_7.execute();
		
		
		//como saber se a transacao da operacao está em espera ou não
		//if( opT2_7.getTransacao().getModo() == Modo.ESPERA);
		
		
	}

}
