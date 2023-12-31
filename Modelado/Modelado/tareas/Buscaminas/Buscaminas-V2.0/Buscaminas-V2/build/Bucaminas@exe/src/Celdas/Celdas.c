/* Celdas.c generated by valac 0.40.15, the Vala compiler
 * generated from Celdas.vala, do not modify */



#include <glib.h>
#include <glib-object.h>
#include <stdlib.h>
#include <string.h>


#define TYPE_CELDAS (celdas_get_type ())
#define CELDAS(obj) (G_TYPE_CHECK_INSTANCE_CAST ((obj), TYPE_CELDAS, Celdas))
#define CELDAS_CLASS(klass) (G_TYPE_CHECK_CLASS_CAST ((klass), TYPE_CELDAS, CeldasClass))
#define IS_CELDAS(obj) (G_TYPE_CHECK_INSTANCE_TYPE ((obj), TYPE_CELDAS))
#define IS_CELDAS_CLASS(klass) (G_TYPE_CHECK_CLASS_TYPE ((klass), TYPE_CELDAS))
#define CELDAS_GET_CLASS(obj) (G_TYPE_INSTANCE_GET_CLASS ((obj), TYPE_CELDAS, CeldasClass))

typedef struct _Celdas Celdas;
typedef struct _CeldasClass CeldasClass;
typedef struct _CeldasPrivate CeldasPrivate;
enum  {
	CELDAS_0_PROPERTY,
	CELDAS_NUM_PROPERTIES
};
static GParamSpec* celdas_properties[CELDAS_NUM_PROPERTIES];
#define _g_free0(var) (var = (g_free (var), NULL))

struct _Celdas {
	GObject parent_instance;
	CeldasPrivate * priv;
};

struct _CeldasClass {
	GObjectClass parent_class;
};

struct _CeldasPrivate {
	gboolean descubierta;
	gboolean marcada;
	gchar* nombre;
	gboolean minada;
	gint conteo;
};


static gpointer celdas_parent_class = NULL;

GType celdas_get_type (void) G_GNUC_CONST;
#define CELDAS_GET_PRIVATE(o) (G_TYPE_INSTANCE_GET_PRIVATE ((o), TYPE_CELDAS, CeldasPrivate))
Celdas* celdas_new (gboolean mina);
Celdas* celdas_construct (GType object_type,
                          gboolean mina);
void celdas_minar (Celdas* self,
                   gboolean mina);
void celdas_revelar (Celdas* self);
void celdas_marcar (Celdas* self);
void celdas_setNombre (Celdas* self,
                       const gchar* nombre_nuevo);
gchar* celdas_getNombre (Celdas* self);
gboolean celdas_revelada (Celdas* self);
gboolean celdas_estaMarcada (Celdas* self);
gboolean celdas_estaMinada (Celdas* self);
void celdas_numerar (Celdas* self);
gint celdas_getConteo (Celdas* self);
gchar* celdas_to_string (Celdas* self);
static void celdas_finalize (GObject * obj);


/**
     * Metodo constructor
     * @param mina True si la celda conteendra una mina y False en otro caso
     */
Celdas*
celdas_construct (GType object_type,
                  gboolean mina)
{
	Celdas * self = NULL;
#line 22 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	self = (Celdas*) g_object_new (object_type, NULL);
#line 23 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	self->priv->minada = mina;
#line 22 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	return self;
#line 86 "Celdas.c"
}


Celdas*
celdas_new (gboolean mina)
{
#line 22 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	return celdas_construct (TYPE_CELDAS, mina);
#line 95 "Celdas.c"
}


/**
     * Metodo para minar una Celda
     * @param mina True si contiene una mina
     */
void
celdas_minar (Celdas* self,
              gboolean mina)
{
#line 31 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	g_return_if_fail (self != NULL);
#line 32 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	self->priv->minada = mina;
#line 111 "Celdas.c"
}


/**
     * Metodo para revelar la celda indistintamente si esta minada o no
     */
void
celdas_revelar (Celdas* self)
{
#line 38 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	g_return_if_fail (self != NULL);
#line 39 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	self->priv->descubierta = TRUE;
#line 125 "Celdas.c"
}


/**
     * Metodo para marcar la celda indistintamente si esta minada o no
     */
void
celdas_marcar (Celdas* self)
{
	gboolean _tmp0_;
#line 45 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	g_return_if_fail (self != NULL);
#line 46 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	_tmp0_ = self->priv->marcada;
#line 46 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	if (_tmp0_) {
#line 47 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
		self->priv->marcada = FALSE;
#line 144 "Celdas.c"
	} else {
#line 49 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
		self->priv->marcada = TRUE;
#line 148 "Celdas.c"
	}
}


/**
     * Metodo para asignar el nombre a una Celda
     * @param nombre_nuevo Coordenadas de la posicion de la celda dentro del tablero
     */
void
celdas_setNombre (Celdas* self,
                  const gchar* nombre_nuevo)
{
	gchar* _tmp0_;
#line 57 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	g_return_if_fail (self != NULL);
#line 57 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	g_return_if_fail (nombre_nuevo != NULL);
#line 58 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	_tmp0_ = g_strdup (nombre_nuevo);
#line 58 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	_g_free0 (self->priv->nombre);
#line 58 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	self->priv->nombre = _tmp0_;
#line 172 "Celdas.c"
}


/**
     * Metodo para obtener el nombre de una Celda
     * @return String.- Coordenadas con la posicion de la celda dentro del tablero
     */
gchar*
celdas_getNombre (Celdas* self)
{
	gchar* result = NULL;
	const gchar* _tmp0_;
	gchar* _tmp1_;
#line 65 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	g_return_val_if_fail (self != NULL, NULL);
#line 66 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	_tmp0_ = self->priv->nombre;
#line 66 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	_tmp1_ = g_strdup (_tmp0_);
#line 66 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	result = _tmp1_;
#line 66 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	return result;
#line 196 "Celdas.c"
}


/**
     * Metodo para saber si ya fue revelada una celda
     * @return True si ya fue revelada y False en otro caso
     */
gboolean
celdas_revelada (Celdas* self)
{
	gboolean result = FALSE;
	gboolean _tmp0_;
#line 73 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	g_return_val_if_fail (self != NULL, FALSE);
#line 74 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	_tmp0_ = self->priv->descubierta;
#line 74 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	result = _tmp0_;
#line 74 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	return result;
#line 217 "Celdas.c"
}


/**
     * Metodo para saber si ya fue marcada una Celda
     * @return True si esta marcada y False en otro caso
     */
gboolean
celdas_estaMarcada (Celdas* self)
{
	gboolean result = FALSE;
	gboolean _tmp0_;
#line 81 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	g_return_val_if_fail (self != NULL, FALSE);
#line 82 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	_tmp0_ = self->priv->marcada;
#line 82 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	result = _tmp0_;
#line 82 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	return result;
#line 238 "Celdas.c"
}


/**
     *  Metodo para saber que la Celda tiene una mina
     * @return True si tiene mina y False en otro caso
     */
gboolean
celdas_estaMinada (Celdas* self)
{
	gboolean result = FALSE;
	gboolean _tmp0_;
#line 89 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	g_return_val_if_fail (self != NULL, FALSE);
#line 90 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	_tmp0_ = self->priv->minada;
#line 90 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	result = _tmp0_;
#line 90 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	return result;
#line 259 "Celdas.c"
}


/**
     * Metodo para aumentar el numero de minas alrededor de la celda
     */
void
celdas_numerar (Celdas* self)
{
	gint _tmp0_;
#line 96 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	g_return_if_fail (self != NULL);
#line 97 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	_tmp0_ = self->priv->conteo;
#line 97 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	self->priv->conteo = _tmp0_ + 1;
#line 276 "Celdas.c"
}


/**
     * Metodo para obtener el numero de minas alrededor de la Celda
     * @return Numero de minas que tiene a su alrededor
     */
gint
celdas_getConteo (Celdas* self)
{
	gint result = 0;
	gint _tmp0_;
#line 104 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	g_return_val_if_fail (self != NULL, 0);
#line 105 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	_tmp0_ = self->priv->conteo;
#line 105 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	result = _tmp0_;
#line 105 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	return result;
#line 297 "Celdas.c"
}


/**
     * Metodo para imprimir las celdas en sus distintas formas, ya sean las minadas
     * o las que no contienen minas
     * @return String de las celdas
     */
gchar*
celdas_to_string (Celdas* self)
{
	gchar* result = NULL;
	gboolean _tmp0_;
#line 113 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	g_return_val_if_fail (self != NULL, NULL);
#line 114 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	_tmp0_ = self->priv->minada;
#line 114 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	if (_tmp0_) {
#line 317 "Celdas.c"
		gboolean _tmp1_;
#line 115 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
		_tmp1_ = self->priv->descubierta;
#line 115 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
		if (_tmp1_) {
#line 323 "Celdas.c"
			gchar* _tmp2_;
#line 116 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			_tmp2_ = g_strdup ("   💣  ");
#line 116 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			result = _tmp2_;
#line 116 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			return result;
#line 331 "Celdas.c"
		} else {
			gboolean _tmp3_;
#line 118 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			_tmp3_ = self->priv->marcada;
#line 118 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			if (_tmp3_) {
#line 338 "Celdas.c"
				gchar* _tmp4_;
#line 119 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
				_tmp4_ = g_strdup ("  🚩   ");
#line 119 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
				result = _tmp4_;
#line 119 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
				return result;
#line 346 "Celdas.c"
			} else {
				gchar* _tmp5_;
#line 121 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
				_tmp5_ = celdas_getNombre (self);
#line 121 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
				result = _tmp5_;
#line 121 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
				return result;
#line 355 "Celdas.c"
			}
		}
	} else {
		gboolean _tmp6_;
#line 124 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
		_tmp6_ = self->priv->descubierta;
#line 124 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
		if (_tmp6_) {
#line 364 "Celdas.c"
			gint _tmp7_;
			gchar* _tmp8_;
			gchar* _tmp9_;
			gchar* _tmp10_;
			gchar* _tmp11_;
			gchar* _tmp12_;
			gchar* _tmp13_;
#line 125 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			_tmp7_ = self->priv->conteo;
#line 125 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			_tmp8_ = g_strdup_printf ("%i", _tmp7_);
#line 125 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			_tmp9_ = _tmp8_;
#line 125 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			_tmp10_ = g_strconcat ("  ", _tmp9_, NULL);
#line 125 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			_tmp11_ = _tmp10_;
#line 125 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			_tmp12_ = g_strconcat (_tmp11_, "  ", NULL);
#line 125 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			_tmp13_ = _tmp12_;
#line 125 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			_g_free0 (_tmp11_);
#line 125 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			_g_free0 (_tmp9_);
#line 125 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			result = _tmp13_;
#line 125 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			return result;
#line 394 "Celdas.c"
		} else {
			gboolean _tmp14_;
#line 127 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			_tmp14_ = self->priv->marcada;
#line 127 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
			if (_tmp14_) {
#line 401 "Celdas.c"
				gchar* _tmp15_;
#line 128 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
				_tmp15_ = g_strdup ("  🚩   ");
#line 128 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
				result = _tmp15_;
#line 128 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
				return result;
#line 409 "Celdas.c"
			} else {
				gchar* _tmp16_;
#line 130 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
				_tmp16_ = celdas_getNombre (self);
#line 130 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
				result = _tmp16_;
#line 130 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
				return result;
#line 418 "Celdas.c"
			}
		}
	}
}


static void
celdas_class_init (CeldasClass * klass)
{
#line 5 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	celdas_parent_class = g_type_class_peek_parent (klass);
#line 5 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	g_type_class_add_private (klass, sizeof (CeldasPrivate));
#line 5 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	G_OBJECT_CLASS (klass)->finalize = celdas_finalize;
#line 434 "Celdas.c"
}


static void
celdas_instance_init (Celdas * self)
{
	gchar* _tmp0_;
#line 5 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	self->priv = CELDAS_GET_PRIVATE (self);
#line 8 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	self->priv->descubierta = FALSE;
#line 10 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	self->priv->marcada = FALSE;
#line 12 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	_tmp0_ = g_strdup ("");
#line 12 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	self->priv->nombre = _tmp0_;
#line 14 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	self->priv->minada = FALSE;
#line 16 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	self->priv->conteo = 0;
#line 456 "Celdas.c"
}


static void
celdas_finalize (GObject * obj)
{
	Celdas * self;
#line 5 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	self = G_TYPE_CHECK_INSTANCE_CAST (obj, TYPE_CELDAS, Celdas);
#line 12 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	_g_free0 (self->priv->nombre);
#line 5 "/home/spartanonix/Documentos/Modelado/tareas/Buscaminas/Buscaminas-V2.0/Buscaminas-V2/src/Celdas/Celdas.vala"
	G_OBJECT_CLASS (celdas_parent_class)->finalize (obj);
#line 470 "Celdas.c"
}


/**
 * Clase para englobar los metodos que tienen en conjunto las Celdas con y sin minas
 */
GType
celdas_get_type (void)
{
	static volatile gsize celdas_type_id__volatile = 0;
	if (g_once_init_enter (&celdas_type_id__volatile)) {
		static const GTypeInfo g_define_type_info = { sizeof (CeldasClass), (GBaseInitFunc) NULL, (GBaseFinalizeFunc) NULL, (GClassInitFunc) celdas_class_init, (GClassFinalizeFunc) NULL, NULL, sizeof (Celdas), 0, (GInstanceInitFunc) celdas_instance_init, NULL };
		GType celdas_type_id;
		celdas_type_id = g_type_register_static (G_TYPE_OBJECT, "Celdas", &g_define_type_info, 0);
		g_once_init_leave (&celdas_type_id__volatile, celdas_type_id);
	}
	return celdas_type_id__volatile;
}



