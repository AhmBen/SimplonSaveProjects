import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.Method;

public class ClassWithAnnotation {
	
	
	@MonAnnotation(name="methode1",lib="moi j'ai un libell�",run=false)
	public void methode1() {		
		System.out.println("M�thode 1 : lanc�e");	
	}
	
	@MonAnnotation(name="methode2",lib="moi j'ai un libell�",run=true)
	public void methode2() {
		System.out.println("M�thode 2 : lanc�e");
	}
}
