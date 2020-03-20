public class Operacao {
	
	private Item a, b, c;
	private String nome; //ler, gravar, sub, add
	private Transacao transacao;
	
	
	public Operacao(String nome, Item a, Item b, Item c, Transacao t) {
		this.nome = nome.toLowerCase();
		this.a = a;
		this.b = b;
		this.c = c;
		this.transacao = t;
	}
	
	public void execute() {
		
		if(this.nome == "ler")
			ler();
		else if(this.nome == "gravar")
			gravar();
		else if(this.nome == "add")
			add();
		else if(this.nome == "sub")
			sub();
		if(this.nome == "rlock")
			rlock();
		else if(this.nome == "wlock")
			wlock();
		else if(this.nome == "unlock")
			unlock();
	}
	
	public void rlock() {
		transacao.rlock(a);	
	}
	
	public void wlock() {
		transacao.wlock(a);	
	}
	
	public void unlock() {
		transacao.unlock(a);
	}
	
	public void ler() {
		
		if( ((a.getEstado() == Estado.RLOCK) || 
			(a.getEstado() == Estado.WLOCK)) &&
			a.gettAtual().contains(transacao) 
		  ){
			a.setValorTemp( a.getValor() );
			 System.out.println("Leitura realizada: " + a.getValor());
		 }else
			 System.out.println("Erro de leitura!!!");
	}
	
	public void gravar() {
		
		
		if( (a.getEstado() == Estado.WLOCK) &&
			a.gettAtual().contains(transacao) 
		   ){
				a.setValor( a.getValorTemp() );
				System.out.println("Gravação realizada: " + a.getValor());
				
		}else {
			
			System.out.println("Erro de gravacao!!!");
		}
	}
	
	public void add() {	
		if( ((a.getEstado() == Estado.RLOCK) || 
			(a.getEstado() == Estado.WLOCK)) &&
			a.gettAtual().contains(transacao) 
			){
			
			a.setValorTemp( b.getValor() + c.getValor());
			
			System.out.println("Operacao realizada " + a.getValorTemp());
		
		}else {
			
			System.out.println("Erro de operacao!!!");
		}
	}
	
	public void sub() {	
		if( ((a.getEstado() == Estado.RLOCK) || 
				(a.getEstado() == Estado.WLOCK)) &&
				a.gettAtual().contains(transacao) 
			  ){
		a.setValorTemp( b.getValor() - c.getValor());
		}else {
			
			System.out.println("Erro de operacao!!!");
		}
	}
	

	public Transacao getTransacao() {
		return transacao;
	}

	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}

}
