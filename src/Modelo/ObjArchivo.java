package Modelo;

public class ObjArchivo {

    private String nombreArchivo;
    private String extension;

    public ObjArchivo(){

    }

    public ObjArchivo(String nombreArchivo, String extension) {
        this.nombreArchivo = nombreArchivo;
        this.extension = extension;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
