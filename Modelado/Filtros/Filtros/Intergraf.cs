using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Drawing.Imaging;

namespace Filtros
{
    public partial class Intergraf : Form
    {
        /// <summary>
        /// Metodo que invoca al formulario
        /// </summary>
        public Intergraf()
        {
            InitializeComponent();
            this.CenterToScreen();
        }

        /// <summary>
        /// Metodo que llama a la clase del formulario
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Intergraf_Load(object sender, EventArgs e)
        {

        }

        /// <summary>
        /// Boton para seleccionar una imagen
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Button1_Click(object sender, EventArgs e)
        {
            openImage.ShowDialog();
            pictureBox1.ImageLocation = openImage.FileName;
            pictureBox2.ImageLocation = openImage.FileName;
        }

        /// <summary>
        /// Boton para cerrar la aplicacion
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        /// <summary>
        /// Boton para aplicar el filtro rojo
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Button3_Click(object sender, EventArgs e)
        {
            pictureBox2.Image = red();
        }

        /// <summary>
        /// Boton para aplicar el filtro verde
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Button4_Click(object sender, EventArgs e)
        {
            pictureBox2.Image = green();
        }

        /// <summary>
        /// Boton para aplicar el filtro azul
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Button5_Click(object sender, EventArgs e)
        {
            pictureBox2.Image = blue();
        }

        /// <summary>
        /// Boton para aplicar el filtro de escala de grises
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Button7_Click(object sender, EventArgs e)
        {
            pictureBox2.Image = grises();
        }

        /// <summary>
        /// Boton para aplicar el filtro mosaico
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Button6_Click(object sender, EventArgs e)
        {
            String aux = textBox1.Text;
            if (aux == "" || Int32.TryParse(textBox1.Text, out int numValue) == false) MessageBox.Show("Mete un valor valido");
            else
            {
                int tam = Int32.Parse(textBox1.Text);
                pictureBox2.Image = mosaico(tam);
            }
        }

        /// <summary>
        /// Filtro de escala de grises
        /// </summary>
        /// <returns>El bitmap con el filtro ya aplicado</returns>
        Image grises()
        {
            if (pictureBox1.Image == null) MessageBox.Show("Selecciona una imagen antes de usar un filtro");
            else
            {
                pictureBox2.Image = pictureBox1.Image;
                Bitmap bm = new Bitmap(pictureBox2.Image);
                progres.Maximum = bm.Width * bm.Height;
                for (int x = 0; x < bm.Width; x++)
                    for (int y = 0; y < bm.Height; y++)
                    {
                        Color c = bm.GetPixel(x, y);
                        int intensidad = (c.R + c.G + c.B) / 3;
                        Color nuevo = Color.FromArgb(intensidad, intensidad, intensidad);
                        bm.SetPixel(x, y, nuevo);
                        progres.Value++;
                    }
                progres.Value = 0;
                return bm;
            }
            return null;
        }

        /// <summary>
        /// Filtro de mosaico
        /// </summary>
        /// <param name="num">Tamaño de la cuadricula para el filtro</param>
        /// <returns>El bitmap con el filtro ya aplicado</returns>
        Image mosaico(int num)
        {
            if (pictureBox1.Image == null) MessageBox.Show("Selecciona una imagen antes de usar un filtro");
            else
            {
                pictureBox2.Image = pictureBox1.Image;
                Bitmap bm = new Bitmap(pictureBox2.Image);
                progres.Maximum = bm.Width * bm.Height;
                int scala = num;
                for (int x = scala; x <= bm.Width - scala; x++)
                    for (int y = scala; y <= bm.Height - scala; y++)
                    {
                        try
                        {
                            Color prevX = bm.GetPixel(x - scala, y);
                            Color nextX = bm.GetPixel(x + scala, y);
                            Color prevY = bm.GetPixel(x, y - scala);
                            Color nextY = bm.GetPixel(x, y + scala);

                            int avgR = (int)((prevX.R + nextX.R + prevY.R + nextY.R) / 4);
                            int avgG = (int)((prevX.G + nextX.G + prevY.G + nextY.G) / 4);
                            int avgB = (int)((prevX.B + nextX.B + prevY.B + nextY.B) / 4);

                            bm.SetPixel(x, y, Color.FromArgb(avgR, avgG, avgB));
                            progres.Value++;
                        }
                        catch (Exception) { }

                    }
                progres.Value = 0;
                return bm;
            }
            return null;
        }

        /// <summary>
        /// Filtro de escala de rojos
        /// </summary>
        /// <returns>El bitmap con el filtro ya aplicado</returns>
        Image red()
        {
            if (pictureBox1.Image == null) MessageBox.Show("Selecciona una imagen antes de usar un filtro");
            else
            {
                pictureBox2.Image = pictureBox1.Image;
                Bitmap bm = new Bitmap(pictureBox2.Image);
                progres.Maximum = bm.Width * bm.Height;
                for (int x = 0; x < bm.Width; x++)
                    for (int y = 0; y < bm.Height; y++)
                    {
                        Color c = bm.GetPixel(x, y);
                        Color nuevo = Color.FromArgb(c.R, 0, 0);
                        bm.SetPixel(x, y, nuevo);
                        progres.Value++;
                    }
                progres.Value = 0;
                return bm;
            }
            return null;
        }

        /// <summary>
        /// Filtro de escala de verdes
        /// </summary>
        /// <returns>El bitmap con el filtro ya aplicado</returns>
        Image green()
        {
            if (pictureBox1.Image == null) MessageBox.Show("Selecciona una imagen antes de usar un filtro");
            else
            {
                pictureBox2.Image = pictureBox1.Image;
                Bitmap bm = new Bitmap(pictureBox2.Image);
                progres.Maximum = bm.Width * bm.Height;
                for (int x = 0; x < bm.Width; x++)
                    for (int y = 0; y < bm.Height; y++)
                    {
                        Color c = bm.GetPixel(x, y);
                        Color nuevo = Color.FromArgb(0, c.G, 0);
                        bm.SetPixel(x, y, nuevo);
                        progres.Value++;
                    }
                progres.Value = 0;
                return bm;
            }
            return null;
        }

        /// <summary>
        /// Filtro de escala de azules
        /// </summary>
        /// <returns>El bitmap con el filtro ya aplicado</returns>
        Image blue()
        {
            if (pictureBox1.Image == null) MessageBox.Show("Selecciona una imagen antes de usar un filtro");
            else
            {
                pictureBox2.Image = pictureBox1.Image;
                Bitmap bm = new Bitmap(pictureBox2.Image);
                progres.Maximum = bm.Width * bm.Height;
                for (int x = 0; x < bm.Width; x++)
                    for (int y = 0; y < bm.Height; y++)
                    {
                        Color c = bm.GetPixel(x, y);
                        Color nuevo = Color.FromArgb(0, 0, c.B);
                        bm.SetPixel(x, y, nuevo);
                        progres.Value++;
                    }
                progres.Value = 0;
                return bm;
            }
            return null;
        }

    }
}
