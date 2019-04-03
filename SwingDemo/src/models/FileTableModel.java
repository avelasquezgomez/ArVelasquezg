/*
 * Licencia GPL.
 * Desarrollado por: William Sánchez
 * El software se proporciona "TAL CUAL", sin garantía de ningún tipo,
 * expresa o implícita, incluyendo pero no limitado a las garantías de
 * comerciabilidad y adecuación para un particular son rechazadas.
 * En ningún caso el autor será responsable por cualquier reclamo,
 * daño u otra responsabilidad, ya sea en una acción de contrato,
 * agravio o cualquier otro motivo, de o en relación con el software
 * o el uso u otros tratos en el software.
 * 
 */
package models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author William Sanchez
 */
public class FileTableModel extends AbstractTableModel {
    private String[][] files;
    private final Object columnames[]= {"Nombre de archivo","Ruta","Fecha de creación"};
    public FileTableModel(){
        files = new String [][]{
                {null,null,null}
        };
    }
    public FileTableModel(File f){
        File files[] = f.listFiles();
        String dataFile[][] = new String[files.length][3];
        int c=0;
        for (File file:files){
            try{
                dataFile[c][0] = file.getName();
                dataFile[c][1] = file.getAbsolutePath();
                BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                dataFile[c][2] = attr.creationTime().toString();
                c++;
            }
            catch(IOException ex){
                Logger.getLogger(FileTableModel.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        setDataModel(dataFile);
    }
    public FileTableModel(String b[][]){
        files = b;
    }
    public void setDataModel(String b[][]){
        files = b;
    }
    public TableModel getModel(){
        TableModel model = new DefaultTableModel(
                files,
                columnames
        );
        return model;
    }
    @Override
    public int getRowCount() {
        return files.length;
    }

    @Override
    public int getColumnCount() {
        return columnames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return files[rowIndex][columnIndex];
    }
    
}
