package ed.estructuras.nolineales;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ListIterator;
import ed.estructuras.ColeccionAbstracta;
import java.util.Arrays;


/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas no aceptan a <code>null</code> como elemento.</p>
 * @param <E>
 */
public class ListaDoblementeLigada<E>  extends ColeccionAbstracta<E> implements List<E>{

    @Override
    public int size() {
	return longitud;
    }

    @Override
    public boolean isEmpty() {
	return longitud ==0;
    }

    @Override
    public boolean contains(Object o) {
	Nodo i = this.cabeza;
	while (i != null) {
	    if(i.elemento != null){
		if (i.elemento.equals(o)) {
		    return true;
		}
	    }else{
		if(i.elemento == o){
		    return true;
		}
	    }
            i = i.siguiente;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
	Iterator<E> i = iterator();
	Object[] ar = new Object[longitud];
	int j =0;
	while(i.hasNext()){
	    ar[j++]=i.next();
	}
	return ar;
    }
    @Override
    public <T> T[] toArray(T[] a) {
	if(a == null)
	    throw new NullPointerException();
	T[] aux = a;
	if(a.length < longitud)
	    aux = Arrays.copyOf(a,longitud);
	Iterator<?> it = iterator();
	for(int i=0;i<longitud;i++){
	    aux[i]=(T) it.next();
	}
	return aux;
    }

    @Override
    public boolean add(E e) {
	Nodo a=cabeza;
	Nodo aux= new Nodo(e);
	if(this.cabeza==null){
	    this.cabeza=aux;
	    this.rabo=aux;
	}else{
	    this.rabo.siguiente=aux;
	    aux.anterior=rabo;
	    this.rabo=aux;
	}
	this.longitud+=1;
	return true;
    }
    private Nodo buscarElemento(E elemento) {
        Nodo i = cabeza;
        while (i != null) {
	    if(i.elemento == null && elemento == null){
		return i;
	    }else{
		if (i.elemento != null && i.elemento.equals(elemento)) 
		    return i;
	    }
            i = i.siguiente;
        }
        return i;
    }
    @Override
    public boolean remove(Object o) {
	@SuppressWarnings("unchecked") Nodo eliminado = this.buscarElemento((E)o);
        if (eliminado == null)
            return false;
        if (this.cabeza == this.rabo) {
            this.cabeza = null;
            this.rabo = null;
        } else if (this.cabeza == eliminado) {
            this.cabeza = this.cabeza.siguiente;
            this.cabeza.anterior = null;
        } else if (this.rabo == eliminado) {
            this.rabo = this.rabo.anterior;
            this.rabo.siguiente = null;
        } else {
            eliminado.anterior.siguiente = eliminado.siguiente;
            eliminado.siguiente.anterior = eliminado.anterior;
        }
	this.longitud -= 1;
	return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
	if (c==null)
	    throw new NullPointerException();
	if(c == this) return true;
	for (Object iterador: c ){
	    if (!this.contains(iterador))return false;
	}
	return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
	Iterator<?> i=c.iterator();
	if(c==this) throw new IllegalArgumentException();
	if(c.isEmpty()) throw new NullPointerException();
	while(i.hasNext()){
	    E o = null;
	    o = (E) i.next();
	    add(o);
	}
	return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
	if(c == null)
	    throw new NullPointerException();
	if(index < 0){
	    throw new IndexOutOfBoundsException();
	}
	for(E elemento:c)
	    this.add(index++,elemento);
	return true;
	    
    }

    @Override
    public boolean removeAll(Collection<?> c) {
       	if(c == null)
	    throw new NullPointerException();
	if(c.size()==0)
	    return false;
	for(Object elemento: c)
	    this.remove(elemento);
	return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
	if(c == null)
	    throw new NullPointerException();
	Iterator<E> i= iterator();
	Iterator<?> i2= c.iterator();
	int tama=longitud;
	if(c.isEmpty()){
	    this.clear();
	    return true;
	}
	if (c == this){
	    return false;
	}else{
	    while(i.hasNext()){
		if (!c.contains(i.next())){
		    i.remove();
		}
	    }
	}
	return longitud != tama;
    }

    @Override
    public void clear() {
        this.cabeza=this.rabo=null;
	this.longitud = 0;
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void add(int index, E element) {
	if(index < 0){
	    throw new IndexOutOfBoundsException();
	}
        if(index == 0){
	    this.agregaInicio(element);
	    return;
	}
	if(index >= longitud-1){
	    this.agregaFinal(element);
	    return;
	}
	Nodo a = new Nodo(element);
	Nodo aux=cabeza;
	for(int j=1;j<index;j++){
	    aux=aux.siguiente;
	}
	a.siguiente=aux.siguiente;
	a.anterior=aux;
	aux.siguiente=a;
	longitud+=1;
    }
    public void eliminaPrimero(){
	if(longitud==1){
	    this.clear();
	    return;
	}else{
	    this.cabeza=this.cabeza.siguiente;
	    this.cabeza.anterior=null;
	}
	this.longitud-=1;
    }
    public void eliminaUltimo(){
	if(this.longitud==1){
	    this.cabeza=this.rabo=null;
	}else{
	    this.rabo.anterior = rabo;
	    this.rabo.siguiente=null;
	}
	this.longitud-=1;
    }
    public E get(int i) {
        
	if(i<0 || i>=this.longitud){
	    throw new IndexOutOfBoundsException();
	}
	Nodo aux = this.cabeza;
	int j=0;
	while(j!=i){
	    aux=aux.siguiente;
	    j++;
	}
	return aux.elemento;
    }
    @Override
    public E set(int index, E element) {
      	if(index == longitud){
	    E el =  rabo.elemento;
	    this.eliminaUltimo();
	    this.agregaFinal(element);
	    return el;
	}
	    
	if(index < 0 || index > longitud){
	    throw new IndexOutOfBoundsException();
	}
	E elemento = this.get(index);
        this.remove(index);
	this.add(index,element);
	return elemento;
    }
    @Override
    public E remove(int index) {
	if(index < 0 || index > longitud -1)
	    throw new IndexOutOfBoundsException();
	Nodo aux = cabeza;
        for(int i=0;i<index;i++){
	    aux=aux.siguiente;
	}
	E elemento = aux.elemento;
	if(index == 0){
	    eliminaPrimero();
	    return elemento;
	}
	if(index == longitud-1){
	    eliminaUltimo();
	    return elemento;
	}
	aux.anterior.siguiente = aux.siguiente;
	aux.siguiente.anterior = aux.anterior;
	longitud--;
	return elemento;
    }

    @Override
    public int indexOf(Object o) {
       	Nodo aux=this.cabeza;
	int i=0;
	while(aux!=null){
	    if(aux.elemento==null && null == o)
		return i;
	    if(aux.elemento != null && o != null){
		if(aux.elemento.equals(o)){
		    return i;
		}
	    }
	    i++;
	    aux=aux.siguiente;
	}
	return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
	if(this.longitud == 0)
	    return -1;
	Nodo aux=this.rabo;
	int i=longitud-1;
	while(aux!=null){
	    if(aux.elemento == null && null == o)
		return i;
	    if(aux.elemento != null && o != null){
		if(aux.elemento.equals(o)){
		return i;
		}
	    }
	    i--;
	    aux=aux.anterior;
	    }
	return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
	return new Iterador();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
	if(index < 0)
	    throw new IndexOutOfBoundsException();
	Iterador it = new Iterador();
	for(int i=0;i<index;i++)
	    it.next();
	return it;
    }

    /* Clase Nodo privada para uso interno de la clase Lista. */
    private class Nodo {
        /* El elemento del nodo. */
        public E elemento;
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nodo con un elemento. */
        public Nodo(E elemento) {
	    this.elemento=elemento;
        }
    }

    /* Clase Iterador privada para iteradores. */
    private class Iterador implements ListIterator<E> {
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nuevo iterador. */
        public Iterador() {
	      this.anterior=null;
	    this.siguiente=cabeza;
        }

        /* Nos dice si hay un elemento siguiente. */
          public boolean hasNext() {
	    return this.siguiente!=null;
        }

        /* Nos da el elemento siguiente. */
          public E next() {
	    if(siguiente==null){
		throw new NoSuchElementException();
	    }
	    anterior=siguiente;
	    siguiente=siguiente.siguiente;
	    return anterior.elemento;
        }

        /* Nos dice si hay un elemento anterior. */
          public boolean hasPrevious() {
	    return this.anterior != null;
        }

        /* Nos da el elemento anterior. */
          public E previous() {
	    if(anterior==null){
		throw new NoSuchElementException();
	    }
	    siguiente=anterior;
	    anterior=anterior.anterior;
	    return siguiente.elemento;
        }

        /* Mueve el iterador al inicio de la lista. */
          public void start() {
	       anterior =null;
	    siguiente=cabeza;
        }

        /* Mueve el iterador al final de la lista. */
          public void end() {
	      siguiente=null;
	    anterior=rabo;
        }
	public void add(E element){
	    agregaFinal(element);
	}
	public void set(E element){
	   
	}
	public void remove(){
	    this.siguiente.anterior=this.anterior.anterior;
	    this.anterior.anterior.siguiente=this.siguiente;
	    this.anterior=this.siguiente.anterior;
	}
	public int previousIndex(){
	    return indexOf((Object)previous());
	}
	public int nextIndex(){
	    return indexOf((Object)next());
	}
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;


    @Override public List<E> subList(int fromIndex, int toIndex){
	if(fromIndex < 0 || toIndex > longitud || fromIndex > toIndex)
	    throw new IndexOutOfBoundsException("Indice incorrecto");
        ListaDoblementeLigada<E> l = new ListaDoblementeLigada<>();
	for(int i=fromIndex;i<toIndex;i++){
	    l.add(this.get(i));
	}
	return l;
    }

    public void agregaFinal(E elemento) {
	this.add(elemento);
    }
    public void agregaInicio(E elemento) {
	Nodo aux = new Nodo(elemento);
	if(cabeza==null){
	    this.cabeza=aux;
	    this.rabo=aux;
	}else{
	   this. cabeza.anterior=aux;
	    aux.siguiente=this.cabeza;
	    this.cabeza=aux;
	}
	longitud+=1;
    }

      public String toString() {
		Nodo it = this.cabeza;
        String string_lista = "[";
        while (it != null) {
            string_lista += it.elemento;
            it = it.siguiente;
            if (it != null) {
                string_lista += ", ";
            }
        }
        string_lista += "]";
	return string_lista;
    }

      public boolean equals(Object o) {
	  if (o == null)
            return false;
	else{
	@SuppressWarnings("unchecked") ListaDoblementeLigada<E> lista = (ListaDoblementeLigada<E>)o;
	Nodo aux=this.cabeza;
	Nodo aux1=lista.cabeza;
	while(aux != null && aux1 != null){
	    if(aux.elemento!=null && aux1.elemento != null){
		if(!aux.elemento.equals(aux1.elemento)){
		    return false;
		}
	    }else{
		if(aux.elemento == null && aux1.elemento != null)
		    return false;
	    }
	    aux = aux.siguiente;
	    aux1 = aux1.siguiente;
	}
	return (aux1!=null) == (aux!=null);
	}

    }
    public Iterator<E> iterator() {
        return new Iterador();
    }
    public static void print(Object s){
	System.out.println(s.toString());
    }

}
