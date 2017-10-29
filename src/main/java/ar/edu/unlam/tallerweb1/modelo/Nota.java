package ar.edu.unlam.tallerweb1.modelo;

public class Nota {
	private Double nota;
	
	public Nota(Double nota) throws Exception {
		if(nota!=null)
		{				
			this.nota = nota;
				if(nota>=0.0){
					this.nota = nota;
					}else{
						throw new Exception();
						 }
		}else{
			throw new Exception();
		 }
	}

	public Double getNota() {
		return nota;
	}

	public void setRecuperarNota(Double nota) {
		this.nota = nota;
	}

	// Pongo seter puedo modificar valor

	public Boolean estaAprobado(){
		 if (nota > 4){ 
			 boolean v = true;
			 return v;
		    }else{
		    	boolean f = false;
				 return f;
		    }   	
		
	}
	
	public Boolean estaPromocionado(){
		 if (nota > 7){ 
			 boolean v = true;
			 return v;
		    }else{
		    	boolean f = false;
				 return f;
		    }   
	}

	
}
