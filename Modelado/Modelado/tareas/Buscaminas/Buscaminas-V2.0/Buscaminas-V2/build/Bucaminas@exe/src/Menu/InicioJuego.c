/* InicioJuego.c generated by valac 0.40.15, the Vala compiler
 * generated from InicioJuego.vala, do not modify */



#include <glib.h>
#include <glib-object.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


#define TYPE_MAIN (main_get_type ())
#define MAIN(obj) (G_TYPE_CHECK_INSTANCE_CAST ((obj), TYPE_MAIN, Main))
#define MAIN_CLASS(klass) (G_TYPE_CHECK_CLASS_CAST ((klass), TYPE_MAIN, MainClass))
#define IS_MAIN(obj) (G_TYPE_CHECK_INSTANCE_TYPE ((obj), TYPE_MAIN))
#define IS_MAIN_CLASS(klass) (G_TYPE_CHECK_CLASS_TYPE ((klass), TYPE_MAIN))
#define MAIN_GET_CLASS(obj) (G_TYPE_INSTANCE_GET_CLASS ((obj), TYPE_MAIN, MainClass))

typedef struct _Main Main;
typedef struct _MainClass MainClass;
typedef struct _MainPrivate MainPrivate;

#define TYPE_TABLERO (tablero_get_type ())
#define TABLERO(obj) (G_TYPE_CHECK_INSTANCE_CAST ((obj), TYPE_TABLERO, Tablero))
#define TABLERO_CLASS(klass) (G_TYPE_CHECK_CLASS_CAST ((klass), TYPE_TABLERO, TableroClass))
#define IS_TABLERO(obj) (G_TYPE_CHECK_INSTANCE_TYPE ((obj), TYPE_TABLERO))
#define IS_TABLERO_CLASS(klass) (G_TYPE_CHECK_CLASS_TYPE ((klass), TYPE_TABLERO))
#define TABLERO_GET_CLASS(obj) (G_TYPE_INSTANCE_GET_CLASS ((obj), TYPE_TABLERO, TableroClass))

typedef struct _Tablero Tablero;
typedef struct _TableroClass TableroClass;

#define TYPE_JUEGODE_PARTIDA (juegode_partida_get_type ())
#define JUEGODE_PARTIDA(obj) (G_TYPE_CHECK_INSTANCE_CAST ((obj), TYPE_JUEGODE_PARTIDA, JuegodePartida))
#define JUEGODE_PARTIDA_CLASS(klass) (G_TYPE_CHECK_CLASS_CAST ((klass), TYPE_JUEGODE_PARTIDA, JuegodePartidaClass))
#define IS_JUEGODE_PARTIDA(obj) (G_TYPE_CHECK_INSTANCE_TYPE ((obj), TYPE_JUEGODE_PARTIDA))
#define IS_JUEGODE_PARTIDA_CLASS(klass) (G_TYPE_CHECK_CLASS_TYPE ((klass), TYPE_JUEGODE_PARTIDA))
#define JUEGODE_PARTIDA_GET_CLASS(obj) (G_TYPE_INSTANCE_GET_CLASS ((obj), TYPE_JUEGODE_PARTIDA, JuegodePartidaClass))

typedef struct _JuegodePartida JuegodePartida;
typedef struct _JuegodePartidaClass JuegodePartidaClass;
enum  {
	MAIN_0_PROPERTY,
	MAIN_NUM_PROPERTIES
};
static GParamSpec* main_properties[MAIN_NUM_PROPERTIES];
#define _g_object_unref0(var) ((var == NULL) ? NULL : (var = (g_object_unref (var), NULL)))
#define _g_free0(var) (var = (g_free (var), NULL))

struct _Main {
	GObject parent_instance;
	MainPrivate * priv;
};

struct _MainClass {
	GObjectClass parent_class;
};

struct _MainPrivate {
	Tablero* tab;
	JuegodePartida* juega;
};


static gpointer main_parent_class = NULL;

GType main_get_type (void) G_GNUC_CONST;
GType tablero_get_type (void) G_GNUC_CONST;
GType juegode_partida_get_type (void) G_GNUC_CONST;
#define MAIN_GET_PRIVATE(o) (G_TYPE_INSTANCE_GET_PRIVATE ((o), TYPE_MAIN, MainPrivate))
void main_submenu (Main* self);
Tablero* tablero_new (gint fila,
                      gint columna,
                      gint minas);
Tablero* tablero_construct (GType object_type,
                            gint fila,
                            gint columna,
                            gint minas);
JuegodePartida* juegode_partida_new (Tablero* tab);
JuegodePartida* juegode_partida_construct (GType object_type,
                                           Tablero* tab);
gchar* tablero_to_string (Tablero* self);
void main_main (gchar** args,
                int args_length1);
Main* main_new (void);
Main* main_construct (GType object_type);
static void main_finalize (GObject * obj);


void
main_submenu (Main* self)
{
	gint opcion_2 = 0;
	FILE* _tmp0_;
	FILE* _tmp1_;
	FILE* _tmp2_;
	gint _tmp3_ = 0;
	gint _tmp4_;
#line 6 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	g_return_if_fail (self != NULL);
#line 7 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	opcion_2 = 0;
#line 8 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	_tmp0_ = stdout;
#line 8 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	fprintf (_tmp0_, "1.- Partida normal\n");
#line 9 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	_tmp1_ = stdout;
#line 9 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	fprintf (_tmp1_, "2.- Partida personalizada\n");
#line 10 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	_tmp2_ = stdin;
#line 10 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	fscanf (_tmp2_, "%d", &_tmp3_);
#line 10 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	opcion_2 = _tmp3_;
#line 11 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	_tmp4_ = opcion_2;
#line 11 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	switch (_tmp4_) {
#line 11 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
		case 1:
#line 125 "InicioJuego.c"
		{
			{
				Tablero* _tmp5_;
				Tablero* _tmp6_;
				JuegodePartida* _tmp7_;
#line 15 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				g_print ("El tablero se construirá de 8x8 con 8 minas");
#line 16 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp5_ = tablero_new (8, 8, 8);
#line 16 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_g_object_unref0 (self->priv->tab);
#line 16 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				self->priv->tab = _tmp5_;
#line 17 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp6_ = self->priv->tab;
#line 17 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp7_ = juegode_partida_new (_tmp6_);
#line 17 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_g_object_unref0 (self->priv->juega);
#line 17 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				self->priv->juega = _tmp7_;
#line 18 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				break;
#line 149 "InicioJuego.c"
			}
		}
#line 11 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
		case 2:
#line 154 "InicioJuego.c"
		{
			{
				gboolean repetidor_error = FALSE;
				gint fila = 0;
				gint columna = 0;
				gint cant_mins = 0;
				gint _tmp20_;
				gint _tmp21_;
				gint _tmp22_;
				Tablero* _tmp23_;
				Tablero* _tmp24_;
				JuegodePartida* _tmp25_;
				FILE* _tmp26_;
				Tablero* _tmp27_;
				gchar* _tmp28_;
				gchar* _tmp29_;
#line 22 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				repetidor_error = TRUE;
#line 23 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				fila = 0;
#line 23 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				columna = 0;
#line 23 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				cant_mins = 0;
#line 24 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				while (TRUE) {
#line 181 "InicioJuego.c"
					gboolean _tmp8_;
					FILE* _tmp9_;
					gint _tmp10_ = 0;
					FILE* _tmp11_;
					gint _tmp12_ = 0;
					FILE* _tmp13_;
					gint _tmp14_ = 0;
					gboolean _tmp15_ = FALSE;
					gboolean _tmp16_ = FALSE;
					gint _tmp17_;
#line 24 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					_tmp8_ = repetidor_error;
#line 24 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					if (!_tmp8_) {
#line 24 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
						break;
#line 198 "InicioJuego.c"
					}
#line 26 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					g_print ("Ingresa el numero de filas :\n");
#line 27 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					_tmp9_ = stdin;
#line 27 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					fscanf (_tmp9_, "%d", &_tmp10_);
#line 27 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					fila = _tmp10_;
#line 28 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					g_print ("Ingresa el numero de columnas: \n");
#line 29 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					_tmp11_ = stdin;
#line 29 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					fscanf (_tmp11_, "%d", &_tmp12_);
#line 29 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					columna = _tmp12_;
#line 30 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					g_print ("Ingresa la cantidad de minas que quieres: \n");
#line 31 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					_tmp13_ = stdin;
#line 31 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					fscanf (_tmp13_, "%d", &_tmp14_);
#line 31 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					cant_mins = _tmp14_;
#line 32 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					_tmp17_ = cant_mins;
#line 32 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					if (_tmp17_ < 8) {
#line 32 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
						_tmp16_ = TRUE;
#line 230 "InicioJuego.c"
					} else {
						gint _tmp18_;
#line 32 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
						_tmp18_ = fila;
#line 32 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
						_tmp16_ = _tmp18_ < 8;
#line 237 "InicioJuego.c"
					}
#line 32 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					if (_tmp16_) {
#line 32 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
						_tmp15_ = TRUE;
#line 243 "InicioJuego.c"
					} else {
						gint _tmp19_;
#line 32 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
						_tmp19_ = columna;
#line 32 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
						_tmp15_ = _tmp19_ < 8;
#line 250 "InicioJuego.c"
					}
#line 32 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
					if (_tmp15_) {
#line 33 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
						g_print ("Datos incorrectos, ninguna opcion puede ser menor que 8\n");
#line 256 "InicioJuego.c"
					} else {
#line 35 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
						repetidor_error = FALSE;
#line 260 "InicioJuego.c"
					}
				}
#line 38 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp20_ = fila;
#line 38 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp21_ = columna;
#line 38 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp22_ = cant_mins;
#line 38 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp23_ = tablero_new (_tmp20_, _tmp21_, _tmp22_);
#line 38 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_g_object_unref0 (self->priv->tab);
#line 38 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				self->priv->tab = _tmp23_;
#line 39 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp24_ = self->priv->tab;
#line 39 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp25_ = juegode_partida_new (_tmp24_);
#line 39 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_g_object_unref0 (self->priv->juega);
#line 39 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				self->priv->juega = _tmp25_;
#line 40 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp26_ = stdout;
#line 40 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp27_ = self->priv->tab;
#line 40 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp28_ = tablero_to_string (_tmp27_);
#line 40 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp29_ = _tmp28_;
#line 40 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				fprintf (_tmp26_, "%s", _tmp29_);
#line 40 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_g_free0 (_tmp29_);
#line 41 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				break;
#line 297 "InicioJuego.c"
			}
		}
		default:
		{
			{
				FILE* _tmp30_;
#line 45 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp30_ = stdout;
#line 45 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				fprintf (_tmp30_, "Elige una opcion valida");
#line 46 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				break;
#line 310 "InicioJuego.c"
			}
		}
	}
}


void
main_main (gchar** args,
           int args_length1)
{
	gint opcion_1 = 0;
	FILE* _tmp0_;
	FILE* _tmp1_;
	Main* m = NULL;
	Main* _tmp2_;
	FILE* _tmp3_;
	gint _tmp4_ = 0;
	gint _tmp5_;
#line 53 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	opcion_1 = 0;
#line 56 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	g_print ("\033[H\033[2J");
#line 57 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	_tmp0_ = stdout;
#line 57 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	fprintf (_tmp0_, "Bienvenido al Buscaminas V2\n");
#line 58 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	_tmp1_ = stdout;
#line 58 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	fprintf (_tmp1_, "%s", "Elija lo que quiera hacer\n" "1.- Jugar un nuevo juego\n" "2.- Salir\n");
#line 59 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	_tmp2_ = main_new ();
#line 59 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	m = _tmp2_;
#line 60 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	_tmp3_ = stdin;
#line 60 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	fscanf (_tmp3_, "%d", &_tmp4_);
#line 60 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	opcion_1 = _tmp4_;
#line 61 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	_tmp5_ = opcion_1;
#line 61 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	switch ((gint) _tmp5_) {
#line 61 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
		case 1:
#line 357 "InicioJuego.c"
		{
			{
				Main* _tmp6_;
#line 66 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp6_ = m;
#line 66 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				main_submenu (_tmp6_);
#line 68 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				break;
#line 367 "InicioJuego.c"
			}
		}
#line 61 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
		case 2:
#line 372 "InicioJuego.c"
		{
			{
#line 73 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				g_print ("Hasta luego");
#line 74 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				break;
#line 379 "InicioJuego.c"
			}
		}
		default:
		{
			{
				FILE* _tmp7_;
#line 78 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				_tmp7_ = stdout;
#line 78 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				fprintf (_tmp7_, "Eliga una opcion valida");
#line 79 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
				break;
#line 392 "InicioJuego.c"
			}
		}
	}
#line 52 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	_g_object_unref0 (m);
#line 398 "InicioJuego.c"
}


int
main (int argc,
      char ** argv)
{
#line 52 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	main_main (argv, argc);
#line 52 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	return 0;
#line 410 "InicioJuego.c"
}


Main*
main_construct (GType object_type)
{
	Main * self = NULL;
#line 1 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	self = (Main*) g_object_new (object_type, NULL);
#line 1 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	return self;
#line 422 "InicioJuego.c"
}


Main*
main_new (void)
{
#line 1 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	return main_construct (TYPE_MAIN);
#line 431 "InicioJuego.c"
}


static void
main_class_init (MainClass * klass)
{
#line 1 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	main_parent_class = g_type_class_peek_parent (klass);
#line 1 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	g_type_class_add_private (klass, sizeof (MainPrivate));
#line 1 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	G_OBJECT_CLASS (klass)->finalize = main_finalize;
#line 444 "InicioJuego.c"
}


static void
main_instance_init (Main * self)
{
#line 1 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	self->priv = MAIN_GET_PRIVATE (self);
#line 453 "InicioJuego.c"
}


static void
main_finalize (GObject * obj)
{
	Main * self;
#line 1 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	self = G_TYPE_CHECK_INSTANCE_CAST (obj, TYPE_MAIN, Main);
#line 2 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	_g_object_unref0 (self->priv->tab);
#line 3 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	_g_object_unref0 (self->priv->juega);
#line 1 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Menu/InicioJuego.vala"
	G_OBJECT_CLASS (main_parent_class)->finalize (obj);
#line 469 "InicioJuego.c"
}


GType
main_get_type (void)
{
	static volatile gsize main_type_id__volatile = 0;
	if (g_once_init_enter (&main_type_id__volatile)) {
		static const GTypeInfo g_define_type_info = { sizeof (MainClass), (GBaseInitFunc) NULL, (GBaseFinalizeFunc) NULL, (GClassInitFunc) main_class_init, (GClassFinalizeFunc) NULL, NULL, sizeof (Main), 0, (GInstanceInitFunc) main_instance_init, NULL };
		GType main_type_id;
		main_type_id = g_type_register_static (G_TYPE_OBJECT, "Main", &g_define_type_info, 0);
		g_once_init_leave (&main_type_id__volatile, main_type_id);
	}
	return main_type_id__volatile;
}



