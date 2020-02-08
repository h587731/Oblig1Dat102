package no.hvl.dat102;

public class LinearNodo<T> {
	
	private LinearNodo<T> neste;
	private T element;
	
	public LinearNodo(T element){
		this.element = element;
		neste = null;
		
	}
	
	
	public LinearNodo<T> getNeste() {
		return neste;
	}

	public T getElement() {
		return element;
	}

	public void setNeste(LinearNodo<T> neste) {
		this.neste = neste;
	}

	
}
