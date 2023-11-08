namespace Buscaminas.test{

    using Buscaminas.CeldasTablero;
    using Buscaminas.Tablero;
    using Buscaminas.test;

    public class TestBuscaminas : TestCase{

        public TestBuscaminas(){
            base("TestBuscaminas");

            add_test("test_celdas_uno", test_celdas_uno);
        }


        public void test_celdas_uno(){
            Celdas celda = new Celdas(false);
            celda.marcar();
            assert((string)celda.revelada() == "false");
        }


    }
}