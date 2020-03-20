
public class Transacao {

	private Modo modo;
	private int timestamp; 
	private static int count = 0;
	
	public Transacao(){
		timestamp = count++;
		modo = Modo.ATIVA;
	}
	
	
	public void rlock(Item a) {
		
		if(a.getEstado() == Estado.UNLOCK ) {
			
			a.setEstado(Estado.RLOCK);
			a.setnLeitores(1);
			a.gettAtual().add(this);
			
		}else if(a.getEstado() == Estado.RLOCK ) {
		
			a.addnLeitores();
			a.gettAtual().add(this);
		
		}else {
			//aguarde
		}
	}

	public void wlock(Item a) {
		
		if(a.getEstado() == Estado.UNLOCK) {
			a.setEstado( Estado.WLOCK );
			a.gettAtual().add(this);
			
		}else {
			//aguarde
		}
		
	}

	public void unlock(Item a) {
		
		if(a.getEstado() == Estado.WLOCK) {
			
			a.setEstado( Estado.UNLOCK );
			a.gettAtual().remove(this);
			
			//acordar uma transacao
		}else if(a.getEstado() == Estado.RLOCK) {
			
			a.subnLeitores();
			
			if(a.getnLeitores() == 0) {
				a.setEstado( Estado.UNLOCK );
				a.gettAtual().remove(this);
				//acordar uma transacao
				
			}
			
		}
	}


	public Modo getModo() {
		return modo;
	}


	public void setModo(Modo modo) {
		this.modo = modo;
	}
	
	
	
}
