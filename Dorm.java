public class Dorm {
    
    private String ubicacionOfrecida;
    private float costoVivienda;
    private int baniosVivienda;
    private int cantPersonasCuarto;
    private String uCompartida;

    

    public Dorm(String ubicacionOfrecida, float costoVivienda, int baniosVivienda, int cantPersonasCuarto,
            String uCompartida) {
        this.ubicacionOfrecida = ubicacionOfrecida;
        this.costoVivienda = costoVivienda;
        this.baniosVivienda = baniosVivienda;
        this.cantPersonasCuarto = cantPersonasCuarto;
        this.uCompartida = uCompartida;
    }
    public void setCostoVivienda(float costoVivienda) {
        this.costoVivienda = costoVivienda;
    }
    public void setBaniosVivienda(int baniosVivienda) {
        this.baniosVivienda = baniosVivienda;
    }
    public void setCantPersonasCuarto(int cantPersonasCuarto) {
        this.cantPersonasCuarto = cantPersonasCuarto;
    }
    public void setuCompartida(String uCompartida) {
        this.uCompartida = uCompartida;
    }
    public String getUbicacionOfrecida() {
        return ubicacionOfrecida;
    }
    public float getCostoVivienda() {
        return costoVivienda;
    }
    public int getBaniosVivienda() {
        return baniosVivienda;
    }
    public int getCantPersonasCuarto() {
        return cantPersonasCuarto;
    }
    public String getuCompartida() {
        return uCompartida;
    }
    public void setUbicacionOfrecida(String ubicacionOfrecida) {
        this.ubicacionOfrecida = ubicacionOfrecida;
    }

}
