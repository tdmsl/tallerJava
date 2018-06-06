package es.tdmsl.obd.datos;

public class Conexion {
    int velocidad;
    int dataBits;
    int stopBits;
    int paridad;
    String nombrePuerto;
    String protocolo;
    String tipoConexion;

    public Conexion(int velocidad, int dataBits, int stopBits, int paridad, String nombrePuerto,String protocolo,String tipoConexion) {
        this.velocidad = velocidad;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.paridad = paridad;
        this.nombrePuerto = nombrePuerto;
        this.protocolo = protocolo;
        this.tipoConexion = tipoConexion;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getDataBits() {
        return dataBits;
    }

    public void setDataBits(int dataBits) {
        this.dataBits = dataBits;
    }

    public int getStopBits() {
        return stopBits;
    }

    public void setStopBits(int stopBits) {
        this.stopBits = stopBits;
    }

    public int getParidad() {
        return paridad;
    }

    public void setParidad(int paridad) {
        this.paridad = paridad;
    }

    public String getNombrePuerto() {
        return nombrePuerto;
    }

    public void setNombrePuerto(String nombrePuerto) {
        this.nombrePuerto = nombrePuerto;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getTipoConexion() {
        return tipoConexion;
    }

    public void setTipoConexion(String tipoConexion) {
        this.tipoConexion = tipoConexion;
    }

    @Override
    public String toString() {
        return "Conexion{" +
                "velocidad=" + velocidad +
                ", dataBits=" + dataBits +
                ", stopBits=" + stopBits +
                ", paridad=" + paridad +
                ", nombrePuerto='" + nombrePuerto + '\'' +
                ", protocolo='" + protocolo + '\'' +
                ", tipoConexion='" + tipoConexion + '\'' +
                '}';
    }
}
