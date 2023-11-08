namespace Buscaminas.test{

	public class TestBuscaminasMain{

	    public static int Main(string [] args){

	        GLib.Test.init(ref args);
	        GLib.TestSuite.get_root().add_suite(new TestBuscaminas().get_suite());
	        return GLib.Test.run();
	    }
	}
}