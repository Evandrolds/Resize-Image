package com.project.resizeimagem;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

/**
 *
 * @author Evandro
 */
public class ResizeImagem {

    public static void main(String[] args) throws IOException {
        
        Path source = Paths.get("C:\\Users\\Evandro\\Desktop\\imagens\\fonte-evga.jpg");
        Path target = Paths.get("C:\\Users\\Evandro\\Desktop\\imagens\\");
         
        
        // Passando o caminho da imagem e o destino onde ficará as novas imagens 
         resizeImage(source, target, 100, 100);
         resizeImage(source, target, 200, 200);
         resizeImage(source, target, 1024, 1024);
        System.out.println( resizeImage(source, target, 100, 100));
    }
    
    private static String resizeImage(Path source, Path target,
            int width, int height) throws IOException {
            
            // Carregando o imagem vinda do cource
            InputStream originalPath = new FileInputStream(source.toFile());
            BufferedImage originalImage = ImageIO.read(originalPath);
            
            // Aplicando o redimensionamento na imagem
            Image resized = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            
         // Convertendo o valor passado no parametro do metodo para String
         // Esse valor será o nome da Imagem
            String nome = String.valueOf(width);
            
            // Pegando o nome da imagem e extensao
           String subPath = source.getFileName().toString();
           
           // Pagando a extensao da imagem
           String extensao = subPath.substring(subPath.lastIndexOf(".")+0);
           
            // Criando um novo caminho com o destino, nome e a extensao do arquivo
            Path newPath = Paths.get(target.toString(),nome+extensao);
            
           // Escrevendo a nova imagem
              ImageIO.write(
                    converteImagem(resized),
                    "png",
                    new File(newPath.toString()));
             // Retnoando o caminho da imagem
            return newPath.toString();
    }
    //Metodo para converter a imagem para BufferedImage 
    public static BufferedImage converteImagem(Image img) {

        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        //Criando o buffer da imagem com o fundo transparente
        BufferedImage bi = new BufferedImage(
                img.getWidth(null), img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        
        // Criando os graficos da imagem
        Graphics2D graphics2D = bi.createGraphics();
        graphics2D.drawImage(img, 0, 0, null);
        graphics2D.dispose();

        return bi;
    }
  
  
}
